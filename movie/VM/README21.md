# 21 -일반화(generalization) 상속 기법을 사용하여 ArrayList와 LinkedList의 공통점 분리하기
      -generalization을 통해 추출한 수퍼 클래스를 추상 클래스로 정의하기
      -인터페이스를 활용하여 객체 사용 규칙 정의하기


### 1. 추상 클래스에서 추상 메서드를 추출하여 인터페이스를 정의하기.

- List.java
    - AbstractList 추상 클래스에 있는 추상 메서드를 추출하여 따로 메서드 사용 규칙을 정의한다.
- AbstractList.java
    - 추상 메서드를 List 인터페이스로 옮긴다.
    - List 규칙을 따른다.
