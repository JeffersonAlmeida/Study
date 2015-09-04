package com.example.study.data;

import java.util.ArrayList;
import java.util.List;

public class Content {

	public static final List<Item> items;
	private static final int SIZE = 100;
	
	static {
		items = new ArrayList<Item>(SIZE);
		for (int i = 1; i < SIZE; i++)
			items.add(new Item(i, "Content Item " + i));
	}
	
	public static class Item {
		
		private final int id;
		private final String content;
		
		protected Item(final int id, final String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return "Item " + id;
		}

		public String getContent() {
			return content;
		}
	}
}
