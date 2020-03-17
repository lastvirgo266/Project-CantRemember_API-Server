# 기억하기 귀찮아프로젝트 (개인 프로젝트)
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