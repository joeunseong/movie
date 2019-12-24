package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Member;

public class MemberHandler {
   
  static final int MEMBER_SIZE = 100;
  static int memberCount = 0;
  static Member[] members = new Member[MEMBER_SIZE];
  public static Scanner keyboard;
  
  public static void addMember() {
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
    member.tel = keyboard.nextLine();

    member.registerDate = new Date(System.currentTimeMillis());

    members[memberCount++] = member;
    System.out.println("저장되었습니다.");

  }
  
  public static void listMember() {
    for (int i = 0; i < memberCount; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s, %s\n", 
          m.no, m.name, m.email, m.tel, m.registerDate);
    }
  }
}
