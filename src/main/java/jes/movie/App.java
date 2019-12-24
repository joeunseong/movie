
package jes.movie;

import java.util.Scanner;
import jes.movie.handler.InfoHandler;
import jes.movie.handler.MemberHandler;
import jes.movie.handler.ReviewHandler;
import jes.movie.handler.ReviewHandler2;

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static String command; 


  public static void main(String[] args) {
    InfoHandler.keyboard = keyboard;
    MemberHandler.keyboard = keyboard;
    ReviewHandler.keyboard = keyboard;
    ReviewHandler2.keyboard = keyboard;

    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      switch (command) {
        case "/info/add":
          InfoHandler.addInfo();
          break;
        case "/info/list":
          InfoHandler.listInfo();
          break;
        case "/member/add":
          MemberHandler.addMember();
          break;

        case "/member/list":
          MemberHandler.listMember();
          break;
          
        case "/review/add":
          ReviewHandler.addReview();
          break;

        case "/review/list":
          ReviewHandler.listReview();
          break;
          
        case "/review/detail":
          ReviewHandler.detailReview();
          break;
          
        case "/review2/add":
          ReviewHandler2.addReview();
          break;

        case "/review2/list":
          ReviewHandler2.listReview();
          break;
          
        case "/review2/detail":
          ReviewHandler2.detailReview();
          break;
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("종료");

    keyboard.close();
  }
}


