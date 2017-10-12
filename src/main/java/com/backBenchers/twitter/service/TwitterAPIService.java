package com.backBenchers.twitter.service;

public interface TwitterAPIService {
	public String getTweet(String param);
	public String getGeoCode(String param);
	public String postTweet(String param);
	
}
