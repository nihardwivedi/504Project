package com.example.a504languagechecker;//package com.marcoJava;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CrawlerLeg {
    // We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    public Document htmlDocument;


    /**
     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
     * up all the links on the page. Perform a searchForWord after the successful crawl
     *
     * @param url
     *            - The URL to visit
     * @return whether or not the crawl was successful
     */
    public boolean crawl(String url)
    {
        try
        {
            //Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            //Document htmlDocument = connection.get();
            Document doc = Jsoup.connect(url).get();
            this.htmlDocument = doc;

            /*if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("**Failure** Retrieved something other than HTML");
                return false;
            }*/
            Elements linksOnPage = htmlDocument.select("a[href]");
//            System.out.println("Found (" + linksOnPage.size() + ") links");

            for(Element link : linksOnPage)
            {
                this.links.add(link.absUrl("href"));
            }

            return true;
        }
        catch(IOException io)
        {
          // We were not successful in our HTTP request
            return false;
        }
    }

    public List<String> getLinks()
    {
        return this.links;
    }
}
