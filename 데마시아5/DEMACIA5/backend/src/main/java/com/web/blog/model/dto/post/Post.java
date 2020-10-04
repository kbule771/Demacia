package com.web.blog.model.dto.post;

import java.sql.Timestamp;

public class Post {

    private int postNo;
    private int userNo;
    private Timestamp postDate;
    private int view;
    private String title;
    private String content;
    private String video;
    private String thumbnail;
    private String option1;
    private String option2;
    private Timestamp deadLine;
    private int deadLineFront;
    private int mileage;
    private int isFinished;

    public Post() {

    }

    public Post(int postNo, int userNo, Timestamp postDate, int view, String title, String content, String video,
            String thumbnail, String option1, String option2, Timestamp deadLine, int deadLineFront, int mileage, int isFinished) {
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
        this.deadLine = deadLine;
        this.deadLineFront = deadLineFront;
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

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
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

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
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

    public Timestamp getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Timestamp ts) {
        this.deadLine = ts;
    }

    public int getDeadLineFront() {
        return deadLineFront;
    }

    public void setDeadLineFront(int deadLineFront) {
        this.deadLineFront = deadLineFront;
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
        return "Post [content=" + content + ", deadLine=" + deadLine + ", deadLineFront=" + deadLineFront
                + ", isFinished=" + isFinished + ", mileage=" + mileage + ", option1=" + option1 + ", option2="
                + option2 + ", postDate=" + postDate + ", postNo=" + postNo + ", thumbnail=" + thumbnail + ", title="
                + title + ", userNo=" + userNo + ", video=" + video + ", view=" + view + "]";
    }

}