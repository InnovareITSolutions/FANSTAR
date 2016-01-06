package com.innovare.fanstar;

public class YourFanModel {
	private String id;
	private String yourfan_image,yourfan_name;

	public YourFanModel() {
	}

	public YourFanModel(String id, String yourfan_image, String yourfan_name) {
		super();
		this.id = id;
		this.yourfan_image = yourfan_image;
		this.yourfan_name = yourfan_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYourfan_image() {
		return yourfan_image;
	}

	public void setYourfan_image(String yourfan_image) {
		this.yourfan_image = yourfan_image;
	}

	public String getYourfan_name() {
		return yourfan_name;
	}

	public void setYourfan_name(String yourfan_name) {
		this.yourfan_name = yourfan_name;
	}
}
