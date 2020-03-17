package remember.controller;


import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.slf4j.Slf4j;
import remember.BO.KakaoAPI;
import remember.BO.KakaoAuth;
import remember.DTO.GameDto;

@Slf4j
@RestController
public class RestApiController {
	
	@Autowired
	private KakaoAPI kakao;
	
	@Autowired
	private KakaoAuth login;
	
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public ResponseEntity<?> Test() {
		log.info("Test");
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	//Auth
	@RequestMapping(value="/main", method= RequestMethod.GET)
	public RedirectView Main() {
		RedirectView redirectView = new RedirectView();
		System.out.println("KEY : " + login.getURL());
		redirectView.setUrl(login.getURL());
		return redirectView;
	}
	
	//login
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void KaKaoLogin(@RequestParam("code") String code, HttpServletResponse response ) {
		
		List<String> tokens = kakao.getAccessToken(code);
		log.info("code  : " + code +"\n"+ "Aceess : " + tokens.get(0)+"\n" + "Refresh : " + tokens.get(1));
		
		//��Ű ����
		Cookie acces_token = new Cookie("access_token", tokens.get(0));
		Cookie refresh_token = new Cookie("refresh_token", tokens.get(1));
		
		acces_token.setMaxAge(60*60*12);
		refresh_token.setMaxAge(60*60*24*12);
		
		acces_token.setPath("/");
		refresh_token.setPath("/");
		
		response.addCookie(acces_token);
		response.addCookie(refresh_token);
		
	}
	
	//Game Password Create
	@RequestMapping(value="/game/create", method = RequestMethod.POST)
	public void GameSave(@RequestBody GameDto game, @CookieValue("access_token") String access_token) {
		kakao.SaveGameInfo(access_token, game);
	}
	
	
	

}
