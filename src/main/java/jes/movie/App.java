
package jes.movie;

import java.util.Scanner;
import jes.movie.handler.InfoHandler;
import jes.movie.handler.MemberHandler;
import jes.movie.handler.ReviewHandler;

public class App {
  static Scanner keyboard = new Scanner(System.in);


  public static void main(String[] args) {
    ReviewHandler reviewHandler1 = new ReviewHandler(keyboard);
    ReviewHandler reviewHandler2 = new ReviewHandler(keyboard);
    InfoHandler infoHandler1 = new InfoHandler(keyboard);
    MemberHandler memberHandler1 = new MemberHandler(keyboard);
    
    String command; 

    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      switch (command) {
        case "/info/add":
          infoHandler1.addInfo();
          break;
        case "/info/list":
          infoHandler1.listInfo();
          break;
        case "/member/add":
          memberHandler1.addMember();
          break;

        case "/member/list":
          memberHandler1.listMember();
          break;
          
        case "/review/add":
          reviewHandler1.addReview();
          break;

        case "/review/list":
          reviewHandler1.listReview();
          break;
          
        case "/review/detail":
          reviewHandler1.detailReview();
          break;
          
        case "/review2/add":
          reviewHandler2.addReview();
          break;

        case "/review2/list":
          reviewHandler2.listReview();
          break;
          
        case "/review2/detail":
          reviewHandler2.detailReview();
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


