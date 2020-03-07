# 30_9 - 파일에 데이터를 저장할 때 JSON 형식을 사용하기

## 작업 목표

- Gson 라이브러리를 이용하여 JSON 형식의 데이터로 저장할 수 있다.

## 작업 소스 및 결과

- src/main/java/jes/movie/dao/json 패키지 추가
- src/main/java/jes/movie/dao/json/AbstractJsonFileDao.java 추가
- src/main/java/jes/movie/dao/json/InfoJsonFileDao.java 추가
- src/main/java/jes/movie/dao/json/MemberJsonFileDao.java 추가
- src/main/java/jes/movie/dao/json/ReviewJsonFileDao.java 추가
- src/main/java/jes/movie/servlet/InfoXxxServlet.java 변경
- src/main/java/jes/movie/servlet/MemberXxxServlet.java 변경
- src/main/java/jes/movie/servlet/ReviewXxxServlet.java 변경
- src/main/java/jes/movie/DataLoaderListener.java 변경
- src/main/java/jes/movie/ServerApp.java 변경


### 작업 1: JSON 형식으로 데이터를 저장하고 로딩할 수퍼 클래스를 정의하기.

- jes.movie.dao.json 패키지를 생성한다.
- jes.movie.dao.json.AbstractJsonFileDao 클래스를 생성한다.


### 작업 2: InfoObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하기.

- jes.movie.dao.InfoObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.


### 작업 3: MemberObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하기.

- jes.movie.dao.MemberObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.


### 작업 4: ReviewObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하기.

- jes.movie.dao.ReviewObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

  
### 작업 5: XxxObjectFileDao 대신 XxxJsonFileDao를 사용하도록 서블릿 클래스를 변경하기.

- jes.movie.servlet.InfoXxxServlet 변경한다.
- jes.movie.servlet.MemberXxxServlet 변경한다.
- jes.movie.servlet.ReviewXxxServlet 변경한다.


### 작업 6: 애플리케이션이 시작할 때 XxxObjectFileDao 대신 XxxJsonFileDao를 준비하기.

- jes.movie.DataLoaderListener 변경한다.


### 작업 7: XxxObjectFileDao 대신 XxxJsonFileDao를 서블릿 객체에 주입하기.

- jes.movie.ServerApp 변경한다.
 



  
  