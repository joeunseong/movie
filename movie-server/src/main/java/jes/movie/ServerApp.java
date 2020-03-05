package jes.movie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import jes.movie.context.ApplicationContextListener;
import jes.movie.dao.InfoObjectFileDao;
import jes.movie.dao.MemberObjectFileDao;
import jes.movie.dao.ReviewObjectFileDao;
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
    
    InfoObjectFileDao infoDao = (InfoObjectFileDao) context.get("infoDao");
    MemberObjectFileDao memberDao = (MemberObjectFileDao) context.get("memberDao");
    ReviewObjectFileDao reviewDao = (ReviewObjectFileDao) context.get("reviewDao");

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
