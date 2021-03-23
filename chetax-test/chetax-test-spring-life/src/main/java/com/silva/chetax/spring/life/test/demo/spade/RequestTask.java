package com.silva.chetax.spring.life.test.demo.spade;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RequestTask implements Runnable {

	private ConcurrentLinkedQueue<String> queue;
	private Set<String> set;
	private List<Map<String, String>> list;

	RequestTask(ConcurrentLinkedQueue<String> queue, Set<String> set, List<Map<String, String>> list) {
		// TODO Auto-generated method stub
		this.queue = queue;
		this.set = set;
		this.list = list;
	}

	@Override
	public void run() {
		while (true) {
			String uri = queue.poll();
//			System.out.println("queue:" + queue);
//			System.out.println("uri:" + uri);
			if (uri != null) {
				try {
					Connection conn = Jsoup.connect(uri);
					Document doc = conn.timeout(3000).get();
					String title = doc.getElementsByTag("title").first().text();
					Map<String, String> map = new HashMap<String, String>();
					map.put(uri, title);
					list.add(map);
					Elements as = doc.getElementsByTag("a");
					for (Element element : as) {
						String href = element.attr("href");
						if (!set.contains(href) && (href.startsWith("http://") || href.startsWith("https://"))) {
							queue.add(href);
							set.add(uri);
						}
					}
				} catch (Exception e) {
					continue;
				}
			}

		}
	}

}
