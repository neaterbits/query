package com.neaterbits.query.test.model;

import java.util.Collection;

import com.neaterbits.query.test.model.feed.FeedType;

public class AnimalType extends Enumerated {

	private Collection<FeedType> feed;

	public Collection<FeedType> getFeed() {
		return feed;
	}

	public void setFeed(Collection<FeedType> feed) {
		this.feed = feed;
	}
	
}
