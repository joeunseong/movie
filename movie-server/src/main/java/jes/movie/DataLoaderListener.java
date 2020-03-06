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
    
    InfoJsonFileDao infoDao = new InfoJsonFileDao("./info.json");
    MemberJsonFileDao memberDao = new MemberJsonFileDao("./member.json");
    ReviewJsonFileDao reviewDao = new ReviewJsonFileDao("./review.json");

    context.put("infoDao", infoDao);
    context.put("memberDao", memberDao);
    context.put("reviewDao", reviewDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
  }

}
