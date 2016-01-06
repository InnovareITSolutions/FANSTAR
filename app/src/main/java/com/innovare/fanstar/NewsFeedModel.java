package com.innovare.fanstar;

public class NewsFeedModel {
	private String id;
	private String profileimage,newsfeedimage;
	private String description,profilename,timestamp;

	public NewsFeedModel() {
	}

	public NewsFeedModel(String id, String profilename, String profileimage, String newsfeedimage, String description, String timestamp) {
		super();
		this.id = id;
		this.profilename = profilename;
		this.timestamp = timestamp;
		this.description = description;
		this.profileimage = profileimage;
		this.newsfeedimage = newsfeedimage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfileimage() {
		return profileimage;
	}

	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}

	public String getNewsfeedimage() {
		return newsfeedimage;
	}

	public void setNewsfeedimage(String newsfeedimage) {
		this.newsfeedimage = newsfeedimage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfilename() {
		return profilename;
	}

	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
