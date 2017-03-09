package com.drugdu.bean;

import com.google.gson.Gson;

public class BannerBean {
	private String imageUrl;
	private String content;
	private String linkUrl;

	public BannerBean(String imageUrl,String content,String linkUrl){
		this.imageUrl = imageUrl;
		this.content = content;
		this.linkUrl = linkUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
