# 30_6 - 커맨드 패턴을 적용하여 요청 처리 메서드를 객체화 하기 

## 작업목표

- 커맨드 패턴을 코드에 적용하기 

- src/main/java/jes/movie/servlet 패키지 생성
- src/main/java/jes/movie/servlet/Servlet.java 추가
- src/main/java/jes/movie/servlet/InfoListServlet.java 추가
- src/main/java/jes/movie/servlet/InfoAddServlet.java 추가
- src/main/java/jes/movie/servlet/InfoDetailServlet.java 추가
- src/main/java/jes/movie/servlet/InfoUpdateServlet.java 추가
- src/main/java/jes/movies/servlet/InfoDeleteServlet.java 추가
- src/main/java/jes/movie/servlet/ReviewListServlet.java 추가
- src/main/java/jes/movie/servlet/ReviewAddServlet.java 추가
- src/main/java/jes/movie/servlet/ReviewDetailServlet.java 추가
- src/main/java/jes/movie/servlet/ReviewUpdateServlet.java 추가
- src/main/java/jes/movie/servlet/ReviewDeleteServlet.java 추가
- src/main/java/jes/movie/servlet/MemberListServlet.java 추가
- src/main/java/jes/movie/servlet/MemberAddServlet.java 추가
- src/main/java/jes/movie/servlet/MemberDetailServlet.java 추가
- src/main/java/jes/movie/servlet/MemberUpdateServlet.java 추가
- src/main/java/jes/movie/servlet/MemberDeleteServlet.java 추가
- src/main/java/jes/movie/ServerApp.java 변경


### 작업 1: 커맨드 패턴의 인터페이스 정의하기

- jes.movie.servlet 패키지를 생성한다.
- jes.movie.servlet.Servlet 인터페이스를 정의한다.

### 작업 2: 각각의 요청 처리 메서드를 인터페이스 규칙에 따라 클래스를 정의하라.
 
- listInfo()를 InfoListServlet 클래스로 정의한다.
- addInfo()를 InfoAddServlet 클래스로 정의한다.
- detailInfo()를 InfoDetailServlet 클래스로 정의한다.
- updateInfo()를 InfoUpdateServlet 클래스로 정의한다.
- deleteInfo()를 InfoDeleteServlet 클래스로 정의한다.
- listMember()를 MemberListServlet 클래스로 정의한다.
- addMember()를 MemberAddServlet 클래스로 정의한다.
- detailMember()를 MemberDetailServlet 클래스로 정의한다.
- updateMember()를 MemberUpdateServlet 클래스로 정의한다.
- deleteMember()를 MemberDeleteServlet 클래스로 정의한다.
- listReview()를 ReviewListServlet 클래스로 정의한다.
- addReview()를 ReviewAddServlet 클래스로 정의한다.
- detailReview()를 ReviewDetailServlet 클래스로 정의한다.
- updateReview()를 ReviewUpdateServlet 클래스로 정의한다.
- deleteReview() 를 ReviewDeleteServlet 클래스로 정의한다.
