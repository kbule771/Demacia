package com.web.blog.model.dto.post;

import java.time.LocalDateTime;

public class PostDetail {
	private int postNo;
	private int userNo;
	private LocalDateTime postDate;
	private int view;
	private String title;
	private String content;
	private String video;
	private String thumbnail;
	private String option1;
	private String option2;
	private String userNickname;
	private String userImage;
	private LocalDateTime deadLine;
	private int totalLikes;
	private int mileage;
	private int isFinished;

	public PostDetail() {

	}

	public PostDetail(int postNo, int userNo, LocalDateTime postDate, int view, String title, String content,
			String video, String thumbnail, String option1, String option2, String userNickname, String userImage,
			LocalDateTime deadLine, int totalLikes, int mileage, int isFinished) {
		this.postNo = postNo;
		this.userNo = userNo;
		this.postDate = postDate;
		this.view = view;
		this.title = title;
		this.content = content;
		this.video = video;
		this.thumbnail = thumbnail;
		this.option1 = option1;
		this.option2 = option2;
		this.userNickname = userNickname;
		this.userImage = userImage;
		this.deadLine = deadLine;
		this.totalLikes = totalLikes;
		this.mileage = mileage;
		this.isFinished = isFinished;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public LocalDateTime getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(LocalDateTime deadLine) {
		this.deadLine = deadLine;
	}

	public int getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}

	public int getMileage() {
		return this.mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}

	@Override
	public String toString() {
		return "PostDetail [content=" + content + ", deadLine=" + deadLine + ", isFinished=" + isFinished + ", mileage="
				+ mileage + ", option1=" + option1 + ", option2=" + option2 + ", postDate=" + postDate + ", postNo="
				+ postNo + ", thumbnail=" + thumbnail + ", title=" + title + ", totalLikes=" + totalLikes
				+ ", userImage=" + userImage + ", userNickname=" + userNickname + ", userNo=" + userNo + ", video="
				+ video + ", view=" + view + "]";
	}

}