# 30_10 - 인터페이스를 이용하여 DAO 호출 규칙을 통일하기 

## 작업 목표

- 인터페이스를 정의하고 호출 규칙 통일하기

## 실습 소스 및 결과

- src/main/java/jes/movie/dao/InfoDao.java 인터페이스 추가
- src/main/java/jes/movie/dao/InfoObjectFileDao.java 변경
- src/main/java/jes/movie/dao/json/InfoJsonFileDao.java 변경
- src/main/java/jes/movie/dao/MemberDao.java 인터페이스 추가
- src/main/java/jes/movie/dao/MemberObjectFileDao.java 변경
- ssrc/main/java/jes/movie/dao/json/MemberJsonFileDao.java 변경
- src/main/java/jes/movie/dao/ReviewDao.java 인터페이스 추가
- src/main/java/jes/movie/dao/ReviewObjectFileDao.java 변경
- src/main/java/jes/movie/dao/json/ReviewJsonFileDao.java 변경
- src/main/java/jes/movie/servlet/InfoXxxServlet.java 변경
- src/main/java/jes/movie/servlet/MemberXxxServlet.java 변경
- src/main/java/jes/movie/servlet/ReviewXxxServlet.java 변경
- src/main/java/jes/movie/DataLoaderListener.java 변경
- src/main/java/jes/movie/ServerApp.java 변경

## 실습  

### 훈련 1: InfoXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현하기.

- jes.movie.dao.InfoDao 인터페이스 생성한다.
- jes.movie.dao.InfoObjectFileDao 클래스를 변경한다.
  - InfoDao 인터페이스를 구현한다.
- jes.movie.dao.json.InfoJsonFileDao 클래스를 변경한다.
  - InfoDao 인터페이스를 구현한다.
- jes.movie.servlet.InfoXxxServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 InfoDao 인터페이스로 변경한다.
- jes.movie.DataLoaderListener 변경한다.
- jes.movie.ServerApp 변경한다.
 

### 훈련 2: MemberXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현하기.

- jes.movie.dao.MemberDao 인터페이스 생성한다.
- jes.movie.dao.MemberObjectFileDao 클래스를 변경한다.
  - MemberDao 인터페이스를 구현한다.
- jes.movie.dao.json.MemberJsonFileDao 클래스를 변경한다.
  - MemberDao 인터페이스를 구현한다.
- jes.movie.servlet.MemberXxxServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 MemberDao 인터페이스로 변경한다.
- jes.movie.DataLoaderListener 변경한다.
- jes.movie.ServerApp 변경한다.

 
### 훈련 3: ReviewXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현하기.

- jes.movie.dao.ReviewDao 인터페이스 생성한다.
- jes.movie.dao.ReviewObjectFileDao 클래스를 변경한다.
  - ReviewDao 인터페이스를 구현한다.
- jes.movie.dao.json.ReviewJsonFileDao 클래스를 변경한다.
  - ReviewDao 인터페이스를 구현한다.
- jes.movie.servlet.ReviewXxxServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 ReviewDao 인터페이스로 변경한다.
- jes.movie.DataLoaderListener 변경한다.
- jes.movie.ServerApp 변경한다.

  
  