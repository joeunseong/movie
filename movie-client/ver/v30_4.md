# 30_4 - 서버에 데이터를 요청하는 기능을 추가하기

## 작업목표

- 서버에 요청하고 응답 결과를 받을 수 있다.  

### 작업 1: 31번 프로젝트에서 Info 클래스를 가져오라.

- jes.movie.domain 패키지 생성한다.
- Info.java 가져온다.

### 작업 2: 31번 프로젝트에서 게시물 관리를 처리하는 Command 객체 가져오라.

- jes.movie.handler 패키지 생성한다.
- InfoXxxCommand.java 클래스 가져온다. 

### 작업 3: Command 객체가 서버와 통신할 수 있도록 입출력 도구를 제공하라.

- ClientApp.java 변경
  - 서버와 연결하는 코드를 적용한다.
  - 서버와 통신할 수 있는 입출력 도구를 InfoXxxCommand 객체에 제공한다.
  
### 작업 4: InfoListCommand 가 작업할 때 서버와 통신하도록 처리하라.

- InfoListCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 작업 5: InfoAddCommand 가 작업할 때 서버와 통신하도록 처리하라.

- InfoAddCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 작업 6: InfoDetailCommand 가 작업할 때 서버와 통신하도록 처리하라.

- InfoDetailCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.
  
### 작업 7: InfoUpdateCommand 가 작업할 때 서버와 통신하도록 처리하라.

- InfoUpdateCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.
  
### 훈련 8:InfoDeleteCommand 가 작업할 때 서버와 통신하도록 처리하라.

- InfoDeleteCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.

### 작업 9: ReviewXxxCommand 가 작업할 때 서버와 통신하도록 처리하라.

- ReviewXxxCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.
  
### 작업 10: MemberXxxCommand 가 작업할 때 서버와 통신하도록 처리하라.

- MemberXxxCommand.java 변경
  - 서버의 입출력 도구를 받을 수 있도록 생성자를 변경한다.
  - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.