package com.example;

import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class EditRssFeed {
    public static void main(String[] args) {
        try {
            // URL of the RSS feed to read
            String rssFeedUrl = "https://www.keralalottery.info/feeds/posts/default/-/win-win-lottery-results";
            URL url = new URL(rssFeedUrl);

            // Read the RSS feed
            SyndFeedInput input = new SyndFeedInput();
            InputStream is = url.openStream();
            SyndFeed feed = input.build(new XmlReader(is));

            // Edit the content of the RSS feed by removing specific links
            List<SyndEntry> entries = feed.getEntries();
            Iterator<SyndEntry> iterator = entries.iterator();

            while (iterator.hasNext()) {
                SyndEntry entry = iterator.next();
                // Condition to remove links (e.g., based on the title or link)
                if (entry.getLink().contains("some-unwanted-link")) {
                    iterator.remove();
                }
            }

            // Output the modified RSS feed to a new file
            SyndFeedOutput output = new SyndFeedOutput();
            try (FileWriter writer = new FileWriter("modified_rss_feed.xml")) {
                output.output(feed, writer);
            }

            System.out.println("Modified RSS feed successfully created!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
