package com.hyperion.dashdroid.news.rss;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by Valdrin on 12/05/2016.
 */
public class RSSFeed implements Serializable {

    private static final long serialVersionUID = 1L;
    private int itemcount = 0;
    private List<RSSItem> itemList;

    RSSFeed(){
        itemList = new Vector<RSSItem>(0);
    }

    void addItem(RSSItem item){
        itemList.add(item);
        itemcount++;
    }

    public RSSItem getItem(int location){
        return itemList.get(location);
    }

    public int getItemCount(){
        return itemcount;
    }
}
