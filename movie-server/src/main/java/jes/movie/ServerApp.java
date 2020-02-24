package jes.movie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jes.movie.context.ApplicationContextListener;
import jes.movie.domain.Info;
import jes.movie.domain.Member;
import jes.movie.domain.Review;
import jes.movie.sevlet.InfoAddServlet;
import jes.movie.sevlet.InfoDeleteServlet;
import jes.movie.sevlet.InfoDetailServlet;
import jes.movie.sevlet.InfoListServlet;
import jes.movie.sevlet.InfoUpdateServlet;
import jes.movie.sevlet.MemberAddServlet;
import jes.movie.sevlet.MemberDeleteServlet;
import jes.movie.sevlet.MemberDetailServlet;
import jes.movie.sevlet.MemberListServlet;
import jes.movie.sevlet.MemberUpdateServlet;
import jes.movie.sevlet.ReviewAddServlet;
import jes.movie.sevlet.ReviewDeleteServlet;
import jes.movie.sevlet.ReviewDetailServlet;
import jes.movie.sevlet.ReviewListServlet;
import jes.movie.sevlet.ReviewUpdateServlet;
import jes.movie.sevlet.Servlet;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  Map<String, Servlet> servletMap = new HashMap<>();
  List<Info> infos;
  List<Member> members;
  List<Review> reviews;

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

    infos = (List<Info>) context.get("infoList");
    members = (List<Member>) context.get("memberList");
    reviews = (List<Review>) context.get("reviewList");

    servletMap.put("/info/list", new InfoListServlet(infos));
    servletMap.put("/info/detail", new InfoDetailServlet(infos));
    servletMap.put("/info/add", new InfoAddServlet(infos));
    servletMap.put("/info/update", new InfoUpdateServlet(infos));
    servletMap.put("/info/delete", new InfoDeleteServlet(infos));

    servletMap.put("/member/list", new MemberListServlet(members));
    servletMap.put("/member/detail", new MemberDetailServlet(members));
    servletMap.put("/member/add", new MemberAddServlet(members));
    servletMap.put("/member/update", new MemberUpdateServlet(members));
    servletMap.put("/member/delete", new MemberDeleteServlet(members));

    servletMap.put("/review/list", new ReviewListServlet(reviews));
    servletMap.put("/review/detail", new ReviewDetailServlet(reviews));
    servletMap.put("/review/add", new ReviewAddServlet(reviews));
    servletMap.put("/review/update", new ReviewUpdateServlet(reviews));
    servletMap.put("/review/delete", new ReviewDeleteServlet(reviews));


    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        if (processRequest(socket) == 9) {
          break;
        }
        System.out.println("----------------------");
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생");
    }

    notifyApplicationDestroyed();
  }

  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      while (true) {
        String request = in.readUTF();
        System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

        switch (request) {
          case "quit":
            quit(out);
            return 0;
          case "/server/stop":
            quit(out);
            return 9;
        }

        Servlet servlet = servletMap.get(request);
        if (servlet != null) {
          try {
            servlet.service(in, out);
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());

            System.out.println("클라이언트 요청 처리 중 오류 발생:");
            e.printStackTrace();
          }
        } else {
          notFound(out);
        }
        out.flush();
        System.out.println("클라이언트에게 응답하였음!");
        System.out.println("----------------------------");
      }
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
