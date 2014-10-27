import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import javax.swing.text.html.HTML;

import org.junit.Test;


public class SearcherTest {
	
	/**
	 * 
	 * When searching for a word, that exist in the list,
	 * the method must return the corresponding HtmlList in the list,
	 * that contains the word given as arg.
	 * 
	 */
	@Test
	public void testHtmlListExists1() {
		URLlist url1 = new URLlist("*Page1", null);
		URLlist url2 = new URLlist("*Page2", null);
		HTMLlist list1 = new HTMLlist("word1", url1, null);
		HTMLlist list2 = new HTMLlist("word2", url2, null);
		list1.next = list2;
		
		HTMLlist test1 = Searcher.HtmlListExists(list1, "word1");
		
		if(!test1.equals(list1)){
			fail("Fail, the word does exist");
		}
	}
	
	/**
	 * 
	 * When searching for a word, that does not exist in the list,
	 * the method must return the last element in the list (next = null)
	 * 
	 */
	@Test
	public void testHtmlListExists2() {
		URLlist url1 = new URLlist("*Page1", null);
		URLlist url2 = new URLlist("*Page2", null);
		HTMLlist list1 = new HTMLlist("word1", url1, null);
		HTMLlist list2 = new HTMLlist("word2", url2, null);
		list1.next = list2;
		
		HTMLlist test1 = Searcher.HtmlListExists(list1, "word3");
		
		if(test1.next != null){
			fail("The last element in the list is not returned");
		}
	}
	
	/**
	 * 
	 * When searching for an url, that does not exist in the list,
	 * the method must return the last element in the list (Url.next = null)
	 * 
	 */
	@Test
	public void testUrlListExists1() {
		URLlist url1 = new URLlist("*Page1", null);
		URLlist url2 = new URLlist("*Page2", null);
		URLlist url3 = new URLlist("*Page3", null);
		URLlist url4 = new URLlist("*Page4", null);
		url1.next = url2;
		url2.next = url3;
		url3.next = url4;		
		URLlist testURL = Searcher.UrlListExists(url1, "*Page5"); // Page 5 does not exist
		
		if(testURL.next != null){
			fail("The last element in the list is not returned in UrlList");
		}
	}
	
	/**
	 * 
	 * When searching for a word, that exist in the list,
	 * the method must return the corresponding URLlist in the list,
	 * that contains the url given as arg. 
	 */
	@Test
	public void testUrlListExists2() {
		URLlist url1 = new URLlist("*Page1", null);
		URLlist url2 = new URLlist("*Page2", null);
		URLlist url3 = new URLlist("*Page3", null);
		URLlist url4 = new URLlist("*Page4", null);
		url1.next = url2;
		url2.next = url3;
		url3.next = url4;		
		URLlist testURL = Searcher.UrlListExists(url1, "*Page3"); // Should return url 3
		
		if(!testURL.equals(url3)){
			fail("The UrlList does not return the existing UrlList ");
		}
	}	
	
	
	/**
	 * 
	 * Check if the first word contains an url
	 * 
	 * @throws IOException
	 */
	@Test
	public void testreadHtmlList() throws IOException{
		HTMLlist l = Searcher.readHtmlList("res/itcwww-test.txt");
		System.out.println(l.word);

		if(l.word == "*PAGE:http://www.it-c.dk/Internet"){
			fail("The first word is an url");
		}
	}
	
	/**
	 * Checks that readHtmlList throws exception 
	 * if invalid file is inserted.
	 * 
	 * @throws IOException
	 */
	@Test(expected = IOException.class)
	public void testreadHtmlList2() throws IOException{
		Searcher.readHtmlList("res/itcwww-none.txt"); // should not exist
		fail("Should throw IOException");
	}
	
	
}
