package com.shortener.urlshortner;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UrlShorterRestController {

	private Map<String, ShortenUrl> shortenUrlList = new HashMap<>();
	
	@RequestMapping(value="/shortenurl", method=RequestMethod.POST)
	public ResponseEntity<Object> getShortenUrl(@RequestBody ShortenUrl shortenUrl) throws MalformedURLException {
		String randomChar = getRandomChars();
		setShortUrl(randomChar, shortenUrl);
		return new ResponseEntity<Object>(shortenUrl, HttpStatus.OK);
	}
	
	@RequestMapping(value="/s/{randomstring}", method=RequestMethod.GET)
	public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
        incrementAccessCount(randomString);
		response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
	}

    @RequestMapping(value="/stats/s/{shortenedUrl}", method=RequestMethod.GET)
	public ResponseEntity<Object> getStats( @PathVariable("shortenedUrl") String shortenedUrl) throws IOException {
		Object url = shortenUrlList.get(shortenedUrl);

        return new ResponseEntity<Object>(url, HttpStatus.OK);
	}

	@RequestMapping(value="/urls", method=RequestMethod.GET)
	public Map<String, ShortenUrl> getUrls()  {
		return shortenUrlList;
	}

	private void setShortUrl(String randomChar, ShortenUrl shortenUrl) throws MalformedURLException {
		 shortenUrl.setShort_url("http://localhost:8080/s/"+randomChar);
		 shortenUrlList.put(randomChar, shortenUrl);
	}

	private String getRandomChars() {
		String randomStr = "";
		String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < 5; i++)
			randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
		return randomStr;
	}

    public void incrementAccessCount(String shortenedUrl) {
        int acess_count = shortenUrlList.get(shortenedUrl).getAcess_count();
        shortenUrlList.get(shortenedUrl).setAcess_count(acess_count + 1);
    }
		
}