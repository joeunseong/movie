package jes.movie;

import java.util.Map;
import jes.movie.context.ApplicationContextListener;

public class GreetingListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("[영화 커뮤니티]에 오신걸 환영합니다!");

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("안녕히가세요!");

  }

}
