package com.automation.utilities;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.testBase.TestBase;

import seleniumhybrid.PracticeTest;

public class FindLinks{
	private static final Logger log = LogManager.getLogger(FindLinks.class.getName());
	
	public void testFindLinks (WebDriver driver) {

	List<WebElement> linksList = clickableLinks(driver);
	for (WebElement link : linksList) {
		String href = link.getAttribute("href");
		try {
			log.info("URL " + href + " returned " + linkStatus(new URL(href)));
		}
		catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	}
	
	public static List<WebElement> clickableLinks(WebDriver driver) {
		List<WebElement> linksToClick = new ArrayList<WebElement>();
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		elements.addAll(driver.findElements(By.tagName("img")));
		
		for (WebElement ele : elements) {
			if (ele.getAttribute("href") != null) {
				linksToClick.add(ele);
			}
		}
		return linksToClick;
	}
	
	public static String linkStatus(URL url) {
		// http://download.java.net/jdk7/archive/b123/docs/api/java/net/HttpURLConnection.html#getResponseMessage%28%29
		try {
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.connect();
			String responseMessage = http.getResponseMessage();
			http.disconnect();
			return responseMessage;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}

}
