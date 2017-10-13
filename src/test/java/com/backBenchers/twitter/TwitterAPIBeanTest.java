package com.backBenchers.twitter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.backBenchers.twitter.service.TwitterAPIService;

@RunWith(MockitoJUnitRunner.class)
public class TwitterAPIBeanTest {
	
	@Test
	public void test() {
		
		String tweet=new TwitterAPIBean(new TwitterAPIService() {

			@Override
			public String getTweet(String param) {
				// TODO Auto-generated method stub
				return "test backbenchers";
			}

			@Override
			public String getGeoCode(String param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String postTweet(String param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getTrends(String param) {
				// TODO Auto-generated method stub
				return null;
			}
			
		}).getTweet("test");
		
		assertEquals("test backbenchers",tweet);
	}

}
