package jes.movie;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jes.movie.context.ApplicationContextListener;
import jes.movie.dao.InfoDao;
import jes.movie.dao.MemberDao;
import jes.movie.dao.ReviewDao;
import jes.movie.servlet.InfoAddServlet;
import jes.movie.servlet.InfoDeleteServlet;
import jes.movie.servlet.InfoDetailServlet;
import jes.movie.servlet.InfoListServlet;
import jes.movie.servlet.InfoUpdateServlet;
import jes.movie.servlet.MemberAddServlet;
import jes.movie.servlet.MemberDeleteServlet;
import jes.movie.servlet.MemberDetailServlet;
import jes.movie.servlet.MemberListServlet;
import jes.movie.servlet.MemberUpdateServlet;
import jes.movie.servlet.ReviewAddServlet;
import jes.movie.servlet.ReviewDeleteServlet;
import jes.movie.servlet.ReviewDetailServlet;
import jes.movie.servlet.ReviewListServlet;
import jes.movie.servlet.ReviewUpdateServlet;
import jes.movie.servlet.Servlet;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  Map<String, Servlet> servletMap = new HashMap<>();

  ExecutorService executorService = Executors.newCachedThreadPool();

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

  public void service() {
    notifyApplicationInitailized();

    InfoDao infoDao = (InfoDao) context.get("infoDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");
    ReviewDao reviewDao = (ReviewDao) context.get("reviewDao");

    servletMap.put("/info/list", new InfoListServlet(infoDao));
    servletMap.put("/info/detail", new InfoDetailServlet(infoDao));
    servletMap.put("/info/add", new InfoAddServlet(infoDao));
    servletMap.put("/info/update", new InfoUpdateServlet(infoDao));
    servletMap.put("/info/delete", new InfoDeleteServlet(infoDao));

    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/update", new MemberUpdateServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));

    servletMap.put("/review/list", new ReviewListServlet(reviewDao));
    servletMap.put("/review/detail", new ReviewDetailServlet(reviewDao));
    servletMap.put("/review/add", new ReviewAddServlet(reviewDao));
    servletMap.put("/review/update", new ReviewUpdateServlet(reviewDao));
    servletMap.put("/review/delete", new ReviewDeleteServlet(reviewDao));


    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        executorService.submit(() -> {
          processRequest(socket);
          System.out.println("-------------------------");
        });
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생");
    }

    notifyApplicationDestroyed();
    executorService.shutdown();
  }

  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.printf("=> %s\n", request);

      // if (request.equalsIgnoreCase("/server/stop")) {
      // return 9;
      // }
      //
      // Servlet servlet = servletMap.get(request);
      // if (servlet != null) {
      // try {
      // servlet.service(in, out);
      //
      // } catch (Exception e) {
      // out.writeUTF("FAIL");
      // out.writeUTF(e.getMessage());
      //
      // System.out.println("클라이언트 요청 처리 중 오류 발생:");
      // e.printStackTrace();
      // }
      // } else {
      // notFound(out);
      // }

      out.flush();
      System.out.println("클라이언트에게 응답하였음!");
      return 0;

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
      return -1;
    }
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  public static void main(String[] args) {
    System.out.println("서버 수업관리 시스템");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();

  }
}
