package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Member;

public class MemberHandler {

  static final int MEMBER_SIZE = 100;
  public Scanner keyboard;

  int memberCount = 0;
  Member[] members;

  public MemberHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.members = new Member[MEMBER_SIZE];
  }

  public void addMember() {
    Member member = new Member();

    System.out.print("번호? ");
    member.setNo(Integer.parseInt(keyboard.nextLine()));

    System.out.print("이름? ");
    member.setName(keyboard.nextLine());

    System.out.print("이메일? ");
    member.setEmail(keyboard.nextLine());

    System.out.print("암호? ");
    member.setPassword(keyboard.nextLine());

    System.out.print("사진? ");
    member.setPhoto(keyboard.nextLine());

    System.out.print("전화? ");
    member.setTel(keyboard.nextLine());

    member.setRegisterDate(new Date(System.currentTimeMillis()));

    this.members[this.memberCount++] = member;
    System.out.println("저장되었습니다.");

  }

  public void listMember() {
    for (int i = 0; i < this.memberCount; i++) {
      Member m = this.members[i];
      System.out.printf("%d, %s, %s, %s, %s\n", 
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisterDate());
    }
  }
}
