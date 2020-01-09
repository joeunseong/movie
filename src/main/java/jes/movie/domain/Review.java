package jes.movie.domain;

import java.sql.Date;

public class Review {
  private int no;
  private String movieTitle;
  private String reviewSummary;
  private Date updateDay;
  private int viewCount;
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Review other = (Review) obj;
    if (movieTitle == null) {
      if (other.movieTitle != null)
        return false;
    } else if (!movieTitle.equals(other.movieTitle))
      return false;
    if (no != other.no)
      return false;
    if (reviewSummary == null) {
      if (other.reviewSummary != null)
        return false;
    } else if (!reviewSummary.equals(other.reviewSummary))
      return false;
    if (viewCount != other.viewCount)
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
  public String getReviewSummary() {
    return reviewSummary;
  }
  public void setReviewSummary(String reviewSummary) {
    this.reviewSummary = reviewSummary;
  }
  public Date getUpdateDay() {
    return updateDay;
  }
  public void setUpdateDay(Date updateDay) {
    this.updateDay = updateDay;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  
  
}
