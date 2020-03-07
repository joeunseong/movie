package jes.movie;

import java.util.Map;
import jes.movie.context.ApplicationContextListener;
import jes.movie.dao.json.InfoJsonFileDao;
import jes.movie.dao.json.MemberJsonFileDao;
import jes.movie.dao.json.ReviewJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");
    
    context.put("infoDao", new InfoJsonFileDao("./info.json"));
    context.put("memberDao", new MemberJsonFileDao("./member.json"));
    context.put("reviewDao", new ReviewJsonFileDao("./review.json"));
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
  }

}
