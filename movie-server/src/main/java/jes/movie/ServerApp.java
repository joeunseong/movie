package jes.movie;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {
  public static void main(String[] args) {
    System.out.println("서버 수업관리 시스템");
    try (
        // 서버에 연결 준비
        ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        // 서버에 대기하고 있는 클라이언트와 연결
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        // 클라이언트 요청 처리
        processRequest(socket);
        System.out.println("----------------------");
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생");
      return;
    }
  }

  static void processRequest(Socket clientSocket) {
    try (
        // 요청 처리가 끝난 후 클라이언트와의 연결 자동으로 끊기
        Socket socket = clientSocket;

        // 클라이언트의 메시지를 수신하고 전송할 입출력 도구 준비
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {
      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      // 클라이언트가 보낸 메시지를 수신
      String message = in.nextLine();
      System.out.println("클라이언트가 보낸 메시지를 수신하였음!");
      System.out.println("클라이언트" + message);

      // 클라이언트에게 메시지를 전송
      out.print("Hi(조은성)");
      System.out.println("클라이언트로 메시지를 전송하였음!");

    } catch (Exception e) {
      System.out.println("예외발생:");
      e.printStackTrace();
    }

  }
}
