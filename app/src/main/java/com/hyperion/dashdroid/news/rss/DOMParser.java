package com.hyperion.dashdroid.news.rss;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Valdrin on 12/05/2016.
 */

public class DOMParser {

	private RSSFeed feed = new RSSFeed();

	public RSSFeed parseXml(String xml) {

		try {
			URL url = new URL(xml);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new InputSource(url.openStream()));
			doc.getDocumentElement().normalize();

			NodeList nl = doc.getElementsByTagName("item");
			int length = nl.getLength();

			for(int i = 0; i < length; i++) {
				Node currentNode = nl.item(i);
				RSSItem item = new RSSItem();

				NodeList nchild = currentNode.getChildNodes();

				for(int j = 0; j < nchild.getLength(); j = j + 1) {
					Node thisNode = nchild.item(j);
					String theString = thisNode.getTextContent();
					String nodeName = thisNode.getNodeName();

					if("title".equals(nodeName)) {
						item.setTitle(theString);
					} else if("description".equals(nodeName)) {
						item.setDescription(theString);
					} else if("link".equals(nodeName)) {
						item.setLink(theString);
					} else if("pubDate".equals(nodeName)) {
						item.setDate(theString);
					} else if("media:thumbnail".equals(nodeName) || "media:content".equals(nodeName)) {
						String url_image = thisNode.getAttributes().item(0).getTextContent();
						item.setImage(url_image);
					}
				}
				feed.addItem(item);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return feed;
	}
}
