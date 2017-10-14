package com.backBenchers.twitter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backBenchers.twitter.TwitterAPIConfig;

import twitter4j.DirectMessage;
import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterAPIServiceImpl extends TwitterAPIConfig implements TwitterAPIService {

	private static final Logger LOG = LoggerFactory.getLogger(TwitterAPIServiceImpl.class);

	public String getTweet(String param) {
		try {

			StringBuffer sb = new StringBuffer();

			Twitter twitter = new TwitterFactory().getInstance();

			AccessToken at = new AccessToken(accessToken, accessTokenSecret);
			twitter.setOAuthConsumer(consumerKey, consumerSecret);
			twitter.setOAuthAccessToken(at);

			try {
				Query query = new Query(param);
				QueryResult result;
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					sb.append("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + "\n");
					// System.out.println("@" + tweet.getUser().getScreenName() + " - " +
					// tweet.getText());
				}
			} catch (TwitterException te) {
				te.printStackTrace();
				// System.out.println("Failed to search tweets: " + te.getMessage());
				System.exit(-1);
			}
			LOG.info(sb.toString());
			return sb.toString();
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
			// ex.printStackTrace();
			return "Error !!!Pl. try again";
		}
	}

	public String getGeoCode(String param) {
		try {

			StringBuffer sb = new StringBuffer();

			Twitter twitter = new TwitterFactory().getInstance();

			AccessToken at = new AccessToken(accessToken, accessTokenSecret);
			twitter.setOAuthConsumer(consumerKey, consumerSecret);
			twitter.setOAuthAccessToken(at);

			try {
				String cordinates[] = param.split(":");
				GeoQuery query = new GeoQuery(
						new GeoLocation(Double.parseDouble(cordinates[0]), Double.parseDouble(cordinates[1])));
				ResponseList<Place> places;
				places = twitter.searchPlaces(query);

				for (Place place : places) {
					sb.append(place.getName() + ":" + place.getCountry() + ":" + place.getCountryCode() + "\n");
				}

			} catch (TwitterException te) {
				te.printStackTrace();
				// System.out.println("Failed to search tweets: " + te.getMessage());
				System.exit(-1);
			}
			LOG.info(sb.toString());
			return sb.toString();
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
			// ex.printStackTrace();
			return "Error !!!Pl. try again";
		}
	}

	public String postTweet(String param) {

		try {

			StringBuffer sb = new StringBuffer();

			Twitter twitter = new TwitterFactory().getInstance();

			AccessToken at = new AccessToken(accessToken, accessTokenSecret);
			twitter.setOAuthConsumer(consumerKey, consumerSecret);
			twitter.setOAuthAccessToken(at);

			try {
				Status status = twitter.updateStatus(param);
				sb.append(status.getText());

			} catch (TwitterException te) {
				te.printStackTrace();
				// System.out.println("Failed to search tweets: " + te.getMessage());
				System.exit(-1);
			}
			LOG.info(sb.toString());
			return sb.toString();
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
			// ex.printStackTrace();
			return "Error !!!Pl. try again";
		}
	}

	@Override
	public String getTrends(String param) {
		StringBuffer sb = new StringBuffer();
		Twitter twitter = new TwitterFactory().getInstance();
		AccessToken at = new AccessToken(accessToken, accessTokenSecret);
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(at);

		/*
		 * ResponseList<Location> locations = null; try { locations =
		 * twitter.getAvailableTrends(); } catch (TwitterException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * for(Location location:locations) { sb.append(location.getWoeid() + ":" +
		 * location.getCountryName() + ":" + location.getCountryCode() + "\n"); }
		 */

		Trends trends = null;
		try {
			trends = twitter.getPlaceTrends(2383489);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < trends.getTrends().length; i++) {
			sb.append(trends.getTrends()[i].getName());
		}

		LOG.info(sb.toString());
		if (sb.toString().isEmpty()) {
			return "no tweets found";
		} else {
			return sb.toString();
		}

	}

	@Override
	public String directMessage(String param) {
		// TODO Auto-generated method stub
		String[] values = param.split("-");
		String recipientId = values[0];
		String message = values[1];
		StringBuffer sb = new StringBuffer();
		Twitter twitter = new TwitterFactory().getInstance();
		AccessToken at = new AccessToken(accessToken, accessTokenSecret);
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(at);
		DirectMessage status = null;
		try {
			status = twitter.sendDirectMessage(recipientId, message);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values[0] + " sent to" + values[1] + " with status " + status;
	}

	@Override
	public String getTimeline(String param) {
		StringBuffer sb = new StringBuffer();
		Twitter twitter = new TwitterFactory().getInstance();
		AccessToken at = new AccessToken(accessToken, accessTokenSecret);
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(at);
		List<Status> statuses = null;
		try {
			statuses = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Showing home timeline.");
		for (Status status : statuses) {
			sb.append(status.getUser().getName() + ":" + status.getText());
		}

		if (sb.toString().isEmpty()) {
			return "no tweets found";
		} else {
			return sb.toString();
		}
	}

}
