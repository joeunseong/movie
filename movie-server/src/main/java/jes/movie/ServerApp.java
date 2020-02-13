package jes.movie;

import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import jes.movie.context.ApplicationContextListener;
import jes.movie.domain.Info;
import jes.movie.domain.Member;
import jes.movie.domain.Review;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitailized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  @SuppressWarnings("unchecked")
  public void service() {
    notifyApplicationInitailized();

    List<Info> infoList = (List<Info>) context.get("infoList");
    List<Member> memberList = (List<Member>) context.get("memberList");
    List<Review> reviewList = (List<Review>) context.get("reviewList");

    notifyApplicationDestroyed();
  }


  public static void main(String[] args) {
    System.out.println("서버 수업관리 시스템");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
    // try (
    // // 서버에 연결 준비
    // ServerSocket serverSocket = new ServerSocket(9999)) {
    // System.out.println("클라이언트 연결 대기중...");
    //
    // while (true) {
    // // 서버에 대기하고 있는 클라이언트와 연결
    // Socket socket = serverSocket.accept();
    // System.out.println("클라이언트와 연결되었음!");
    //
    // // 클라이언트 요청 처리
    // processRequest(socket);
    // System.out.println("----------------------");
    // }
    // } catch (Exception e) {
    // System.out.println("서버 준비 중 오류 발생");
    // return;
    // }
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
