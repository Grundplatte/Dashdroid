package test;

import com.hyperion.dashdroid.news.rss.DOMParser;
import com.hyperion.dashdroid.news.rss.RSSFeed;
import com.hyperion.dashdroid.news.rss.RssFeedUrlEnum;

import junit.framework.TestCase;

/**
 * Created by infinity on 09-Jun-16.
 */
public class NewsTest extends TestCase {

	public void testRssParser() {
		DOMParser parser = new DOMParser();
		RSSFeed rssFeed = parser.parseXml(RssFeedUrlEnum.NEWS_HOME.getUrl());
		assertTrue("Failed to parse.", (rssFeed != null && rssFeed.getItemCount() > 0));
	}
}
