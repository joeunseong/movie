# 26_2 - 파일 입출력 API의 활용 + CSV 문자열을 객체로 전환하는 기능을 도메인 객체로 이전 

### 작업 1: 영화 정보 데이터를 CSV 문자열로 다루는 코드를 Board 클래스로 옮겨라.  

- Info.java
  - CSV 문자열을 가지고 Info 객체를 생성하는 valueOf() 를 추가한다.
  - Info 객체를 가지고 CSV 문자열을 리턴하는 toCsvString() 를 추가한다.
- App.java
  - loadInfoData() 를 변경한다.
  - saveInfoData() 를 변경한다.
  
  
### 작업 2: 회원 데이터를 CSV 문자열로 다루는 코드를 Member 클래스로 옮겨라.  

- Member.java
  - CSV 문자열을 가지고 Member 객체를 생성하는 valueOf() 를 추가한다.
  - Member 객체를 가지고 CSV 문자열을 리턴하는 toCsvString() 를 추가한다.
- App.java
  - loadMemberData() 를 변경한다.
  - saveMemberData() 를 변경한다.
  
### 작업 3: 리뷰 데이터를 CSV 문자열로 다루는 코드를 Lesson 클래스로 옮겨라.  

- Review.java
  - CSV 문자열을 가지고 Review 객체를 생성하는 valueOf() 를 추가한다.
  - Review 객체를 가지고 CSV 문자열을 리턴하는 toCsvString() 를 추가한다.
- App.java
  - loadReviewData() 를 변경한다.
  - saveReviewData() 를 변경한다.