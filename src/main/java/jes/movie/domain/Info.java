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
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Info other = (Info) obj;
    if (actor == null) {
      if (other.actor != null)
        return false;
    } else if (!actor.equals(other.actor))
      return false;
    if (director == null) {
      if (other.director != null)
        return false;
    } else if (!director.equals(other.director))
      return false;
    if (genre == null) {
      if (other.genre != null)
        return false;
    } else if (!genre.equals(other.genre))
      return false;
    if (kmrb == null) {
      if (other.kmrb != null)
        return false;
    } else if (!kmrb.equals(other.kmrb))
      return false;
    if (movieTitle == null) {
      if (other.movieTitle != null)
        return false;
    } else if (!movieTitle.equals(other.movieTitle))
      return false;
    if (no != other.no)
      return false;
    if (openDate == null) {
      if (other.openDate != null)
        return false;
    } else if (!openDate.equals(other.openDate))
      return false;
    if (runningTime != other.runningTime)
      return false;
    if (summary == null) {
      if (other.summary != null)
        return false;
    } else if (!summary.equals(other.summary))
      return false;
    return true;
  }
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
