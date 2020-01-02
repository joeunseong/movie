package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Member;

public class MemberHandler {

  public Scanner keyboard;
  ArrayList memberList;

  public MemberHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    memberList = new ArrayList();
  }
  
  public MemberHandler(Scanner keyboard, int capacity) {
    this.keyboard = keyboard;
    memberList = new ArrayList();
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

    memberList.add(member);
    System.out.println("저장되었습니다.");

  }

  public void listMember() {
    Object[] arr = memberList.toArray();
    for (Object obj : arr) {
      Member m = (Member) obj;
      System.out.printf("%d, %s, %s, %s, %s\n", 
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisterDate());
    }
  }
}
