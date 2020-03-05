# 30_7 - 데이터 처리 코드를 별도의 클래스로 정의하여 객체화 시키기

## 작업 목표

- 데이터 처리 코드를 DAO로 분리할 수 있다. 

## 작업 소스 및 결과

- src/main/java/jes/movie/dao 패키지 생성
- src/main/java/jes/movie/dao/InfoObjectFileDao.java 추가
- src/main/java/jes/movie/dao/MemberObjectFileDao.java 추가
- src/main/java/jes/movie/dao/ReviewObjectFileDao.java 추가
- src/main/java/jes/movie/ServerApp.java 변경

## 실습  

### 훈련 1: 영화 정보 데이터를 처리하는 DAO 클래스를 정의하라.

- jes.movie.dao 패키지를 생성한다.
- jes.movie.InfoObjectFileDao 클래스를 정의한다.

### 훈련 2: InfoObjectFileDao 객체를 적용하라.

- jes.movie.DataLoaderListener 를 변경한다.
  - 영화 정보 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
  - 대신에 InfoObjectFileDao 객체를 생성한다.
- jes.movie.ServerApp 을 변경한다.
  - Map에서 InfoObjectFileDao를 꺼내 관련 커맨드 객체에 주입한다.
- InfoXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 InfoObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 InfoObjectFileDao 객체를 통해 처리한다.
  
  
### 훈련 3: 회원 데이터를 처리하는 DAO 클래스를 정의하고 적용하라.

- jes.movie.MemberObjectFileDao 클래스를 정의한다.
- jes.movie.DataLoaderListener 를 변경한다.
  - 회원 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
  - 대신에 MemberObjectFileDao 객체를 생성한다.
- jes.movie.ServerApp 을 변경한다.
  - Map에서 MemberObjectFileDao를 꺼내 관련 커맨드 객체에 주입한다.
- MemberXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 MemberObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 MemberObjectFileDao 객체를 통해 처리한다.
  
  
### 훈련 4: 리뷰 데이터를 처리하는 DAO 클래스를 정의하고 적용하라.

- jes.movie.ReviewObjectFileDao 클래스를 정의한다.
- jes.movie.DataLoaderListener 를 변경한다.
  - 리뷰 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
  - 대신에 ReviewObjectFileDao 객체를 생성한다.
- jes.movie.ServerApp 을 변경한다.
  - Map에서 ReviewObjectFileDao를 꺼내 관련 커맨드 객체에 주입한다.
- ReviewXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 ReviewObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 ReviewObjectFileDao 객체를 통해 처리한다.
