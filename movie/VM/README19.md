# 19 - 스택 자료구조 구현과 활용


## 실습

### 1. 스택 자료구조를 구현한다.

- Stack.java
    - 제네릭을 적용한다.
    - 객체 복제가 가능하도록 Cloneable 인터페이스를 구현한다.


### 2. 사용자가 입력한 명령어를 스택에 보관한다.

- App.java
    - 사용자가 입력한 명령어를 Stack에 보관한다.


### 3. 사용자가 입력한 명령을 최신순으로 출력하는 `history` 명령을 추가한다.

- App.java
    - 명령어 내역을 출력하는 printCommandHistory()를 정의한다.
    - `history` 명령을 처리하는 분기문을 추가한다.

#### 실행 결과

```
명령> history
history
/review/detail
/member/list
/info/add
/info/list
:  <== 키보드에서 ‘q’가 아닌 다른 문자키를 누른다.
/review/add
/member/list
/member/list
/info/add
/info/add
:q  <== 키보드에서 ‘q’ 키를 누른다.
명령>

```
