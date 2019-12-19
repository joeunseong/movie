package jes.movie;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    class Member {
      int no;
      String name;
      String email;
      String password;
      String photo;
      String tel;
      Date registerDate;
      
    }
    int SIZE = 100;
    int count = 0;
    Member[] members = new Member[SIZE]; 
    for (int i = 0; i < SIZE; i++) {
      Member member = new Member();

      System.out.print("번호? ");
      member.no = Integer.parseInt(keyboard.nextLine());

      System.out.print("이름? ");
      member.name = keyboard.nextLine();

      System.out.print("이메일? ");
      member.email = keyboard.nextLine();

      System.out.print("암호? ");
      member.password = keyboard.nextLine();

      System.out.print("사진? ");
      member.photo = keyboard.nextLine();

      System.out.print("전화? ");
      member.tel= keyboard.nextLine();

      Date today = new Date(System.currentTimeMillis());
      member.registerDate = today;

      members[i] = member;
      count++;
      System.out.print("계속 등록하시겠습니까?(Y/n)");
      String answer = keyboard.nextLine();

      if (!answer.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();

    System.out.println();

    for (int i = 0; i < count; i++) {
      Member member = members[i];
      System.out.printf("%d, %s, %s, %s, %s\n", 
          member.no, member.name, member.email, member.tel, member.registerDate);
    }
  }
}
