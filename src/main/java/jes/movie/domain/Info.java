package jes.movie.domain;

import java.sql.Date;

public class Info {
  private int no;
  private String movieTitle;
  private String genre;
  private String summary;
  private String director;
  private String actor;
  private String kmrb;
  private Date openDate;
  private int runningTime;
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getMovieTitle() {
    return movieTitle;
  }
  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }
  public String getGenre() {
    return genre;
  }
  public void setGenre(String genre) {
    this.genre = genre;
  }
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
  public String getDirector() {
    return director;
  }
  public void setDirector(String director) {
    this.director = director;
  }
  public String getActor() {
    return actor;
  }
  public void setActor(String actor) {
    this.actor = actor;
  }
  public String getKmrb() {
    return kmrb;
  }
  public void setKmrb(String kmrb) {
    this.kmrb = kmrb;
  }
  public Date getOpenDate() {
    return openDate;
  }
  public void setOpenDate(Date openDate) {
    this.openDate = openDate;
  }
  public int getRunningTime() {
    return runningTime;
  }
  public void setRunningTime(int runningTime) {
    this.runningTime = runningTime;
  }
  
  
  
}
