package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Member;
import jes.movie.util.ArrayList;

public class MemberHandler {

  public Scanner keyboard;
  ArrayList<Member> memberList;

  public MemberHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    memberList = new ArrayList<>();
  }

  public MemberHandler(Scanner keyboard, int capacity) {
    this.keyboard = keyboard;
    memberList = new ArrayList<>();
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
    Member[] arr = memberList.toArray(new Member[] {});
    for (Member m : arr) {
      System.out.printf("%d, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(), m.getTel(),
          m.getRegisterDate());
    }
  }

  public void detailMember() {
    System.out.print("인덱스?");
    int index = keyboard.nextInt();
    keyboard.nextLine();

    Member member = this.memberList.get(index);

    if (member == null) {
      System.out.println("유효한 인덱스 멤버는 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisterDate());
  }

  public void updateMember() {
    System.out.print("멤버 인덱스? ");
    int index = keyboard.nextInt();
    keyboard.nextLine();

    Member oldMember = this.memberList.get(index);

    if (oldMember == null) {
      System.out.println("유효한 멤버 인덱스가 없습니다.");
      return;
    }

    System.out.printf("이름(%s)? ", oldMember.getName());
    String name = keyboard.nextLine();

    if (name.length() == 0) {
      System.out.println("수정을 취소합니다.");
      return;
    }

    Member newMember = new Member();
    newMember.setNo(oldMember.getNo());
    newMember.setName(name);

    System.out.printf("이메일(%s)? ", oldMember.getEmail());
    newMember.setEmail(keyboard.nextLine());

    System.out.printf("암호(%s)? ", oldMember.getPassword());
    newMember.setPassword(keyboard.nextLine());

    System.out.printf("사진(%s)? ", oldMember.getPhoto());
    newMember.setPhoto(keyboard.nextLine());

    System.out.printf("전화(%s)? ", oldMember.getTel());
    newMember.setTel(keyboard.nextLine());

    newMember.setRegisterDate(new Date(System.currentTimeMillis()));
    this.memberList.set(index, newMember);
    System.out.println("멤버 정보가 변경되었습니다.");
  }

  public void deleteMember() {
    System.out.print("멤버 인덱스? ");
    int index = keyboard.nextInt();
    keyboard.nextLine();

    Member member = this.memberList.get(index);

    if (member == null) {
      System.out.println("해당 멤버 인덱스가 존재하지 않습니다.");
      return;
    }

    this.memberList.remove(index);
    System.out.println("해당 멤버 인덱스를 삭제했습니다.");
  }
}
