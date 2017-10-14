package com.backBenchers.twitter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.backBenchers.twitter.service.TwitterAPIService;


@RunWith(MockitoJUnitRunner.class)
public class TwitterAPIPostTweetBeanTest {
	
	@Mock
	TwitterAPIService twitterAPIServiceMock;
	
	@InjectMocks
	TwitterAPIPostTweetBean twitterAPIPostTweetBean;
	
	@Test
	public void test() {
		
		when(twitterAPIServiceMock.postTweet(anyString())).thenReturn("message sent");
		
		assertEquals("message sent",twitterAPIPostTweetBean.postTweet("test"));
	}

}

