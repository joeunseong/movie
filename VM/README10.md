# 10 - 클래스로 메서드를 분류하기

### 작업1) 영화 정보 데이터 처리와 관련된 메서드를 별도의 클래스로 분리하라.

- InfoHandler.java
    - 영화 정보 관리와 관련된 메서드를 담을 클래스를 만든다.
    - `App.java` 에서 영화 정보 등록과 관련된 변수와 메서드를 `InfoHandler` 클래스로 옮긴다.
- App.java (App.java.01)
    - `InfoHandler` 클래스 사용한다.


### 작업2) 회원 데이터 처리와 관련된 메서드를 별도의 클래스로 분리하라.

- MemberHandler.java
    - 회원 관리와 관련된 메서드를 담을 클래스를 만든다.
    - `App.java` 에서 회원관리와 관련된 변수와 메서드를 `MemberHandler` 클래스로 옮긴다.
- App.java (App.java.02)
    - `MemberHandler` 클래스 사용한다.


### 작업3) 리뷰글 데이터 처리와 관련된 메서드를 별도의 클래스로 분리하라.

- ReviewHandler.java
    - 리뷰글 관리와 관련된 메서드를 담을 클래스를 만든다.
    - `App.java` 에서 리뷰글관리와 관련된 변수와 메서드를 `ReviewHandler` 클래스로 옮긴다.
- App.java
    - `ReviewHandler` 클래스 사용한다.
