# 기억하기 귀찮아프로젝트 (개인 프로젝트)  
[![Build Status](https://travis-ci.org/lastvirgo266/Project-CantRemember_API-Server.svg?branch=master)](https://travis-ci.org/lastvirgo266/Project-CantRemember_API-Server)
--- 

  비밀번호를 기억하기 만들어서 만들어보는 프로젝트


&nbsp;
&nbsp;
&nbsp;  

## 프로젝트 구조
  - 목적 : 게임 패스워드를 RSA 공개키를 이용하여 패스워드화 시키고 그것을 카카오 API 스토리지에 저장시켜서 필요할때마다 사용함
  - 구조 : 해쉬화된 패스워드를 카카오 스토리지에 저장 --> 가져올때는 일단 해쉬화된것을 가져오고 클라이언트에서 해독시킨다음 보여주고 바로 캐쉬삭제
  - 사용언어 : 자바
  - 사용프레임워크 : 스프링 부트
  - 사용 툴 : STS
  - 사용 데이터베이스 : 카카오 자체 API 이용
  - 인증 방식 : 액세스 토큰을 주고 받는 방식


&nbsp;
&nbsp;

## 프로젝트 상황
  - 우선 DB가 어떻게 될지 모르니 기본적으로 하이버네이트를 이용한 JPA 셋팅
  - 각각 컨트롤러, BO, DTO 구조 제작
  - 카카오 API 클래스 제작
  - 인증 코드 받기 --> 인증코드를 이용해 액세스 토큰 받기 --> 액세스토큰 로컬스토리지 저장(아직 구현안함) --> 액세스 토큰일 이용해 정보를 저장하거나 불러옴
  - AWS이용해서 데이터베이스 연결함
  - 디비 스키마 변경 --> 카카오톡 API는 단일정보저장밖에 안되는 한계때문에 변경함( MySQL <--> JPA )
  - 리포지터리 구성완료
  - 로그인 구조 변경 --> 쿠키 이용해서 받고 user의 id를 쿠키로 또한 등록시켜서 userEntity 사용 안하고 카카오 API 단일 저장과 병행하기로 함
  - create 구조 추가 --> 기존의 메서드를 삭제하기 보다는 추가 하고 나중에 문제가 없으면 삭제하기 위해 이런 방법을 선택함

&nbsp;
&nbsp;

## CI
  - Travis를 이용  
  AWS 코드빌더를 쓰려고 했으나 학생계정이라고 해도 어떻게 될지 모르니 크레딧을 아끼고 싶고 CI구조에 대해 학습을 하기위해서 Travis가 좋다고 판단하여 사용
  - Travis 안쓰기로함 : 너무 많은 버그가 발생함
  - 다시 리트라이 --> 성공함. 버그가 날 것으로 예상된 프로퍼티 에러는 지금 처리중
  - 한글관련 파일 지우고 lombok 의존성 수정한 다음에 오류해결 1
  - 파일암호화 시킬려고했는데 안되서 자세히봤더니 리눅스에서 암호화안해서 구름클라우드켜서 리눅스 환경 만들고 암호화해서 오류해결 2
  - 파일암호화 시키고 파일 조정다해서 오류해결 3
  - 계속 오류나서 봤더니 프로퍼티 소스 애너테이션에 classpath 안붙여줘서 오류남. 오류해결 4

## 참고
  - AWS 에 올리기 : <https://jojoldu.tistory.com/259>