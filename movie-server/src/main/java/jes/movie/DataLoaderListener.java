package jes.movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import jes.movie.context.ApplicationContextListener;
import jes.movie.dao.mariadb.InfoDaoImpl;
import jes.movie.dao.mariadb.MemberDaoImpl;
import jes.movie.dao.mariadb.ReviewDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {
  Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/moviedb", "movie", "1111");

      context.put("infoDao", new InfoDaoImpl(con));
      context.put("memberDao", new MemberDaoImpl(con));
      context.put("reviewDao", new ReviewDaoImpl(con));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    try {
      con.close();
    } catch (Exception e) {

    }
  }

}
