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
public class TwitterAPITimeLineBeanTest {
	
	@Mock
	TwitterAPIService twitterAPIServiceMock;
	
	@InjectMocks
	TwitterAPITimeLineBean twitterAPITimeLineBean;
	
	@Test
	public void test() {
		
		when(twitterAPITimeLineBean.getTimeline(anyString())).thenReturn("message posted");
		
		assertEquals("message posted",twitterAPITimeLineBean.getTimeline("test"));
	}

}

