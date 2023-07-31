package com.shortener.urlshortner;

public class ShortenUrl {

	private String full_url;
	private String short_url;
	private int acess_count;

	public String getFull_url() {
		return full_url;
	}

	public void setFull_url(String full_url) {
		this.full_url = full_url;
	}

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}

	public int getAcess_count() {
		return acess_count;
	}

	public void setAcess_count(int acess_count) {
		this.acess_count = acess_count;
	}

}
