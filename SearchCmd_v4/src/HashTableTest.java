import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class HashTableTest {

	/**
	 * Test that creates array from input itcwww-test.txt
	 * there are 7 distinct words in the test file
	 * Thus fail if
	 * getWordCount != 7
	 * 
	 * @throws IOException
	 */
	@Test
	public void createArrayWords() throws IOException {
		HashTable.createArray("res/itcwww-test.txt");

		int wordCount = HashTable.getWordCount();
		System.out.println(wordCount);

		if(wordCount != 7){
			fail("# of words does not match # of words in the file");
		}
	}

	/**
	 * there are 2 distinct url's in the test file
	 * Thus fail if
	 * getUrlCount != 2
	 * @throws IOException
	 */
	@Test
	public void createArrayUrls() throws IOException {

		int urlCount = HashTable.getUrlCount();
		System.out.println(urlCount);

		if(urlCount != 2){
			fail("# of Url's does not match # of url's in the file");
		}
	}

	/**
	 * Tests if two instances of the same word gets the same index value
	 */
	@Test
	public void sameWordIndex(){

		int wordindex1 = HashTable.getWordIndex("Hurra");
		int wordindex2 = HashTable.getWordIndex("Hurra");

		if(wordindex1 != wordindex2){
			fail("Same word does not hash to same distinct int for index purposes");

		}
	}
	
	/**
	 * Tests if two diffent words not gets the same index value
	 */
	@Test
	public void distinctWordIndex() {
	
		int wordindex1 = HashTable.getWordIndex("Hurra");
		int wordindex2 = HashTable.getWordIndex("Jubii");

		if(wordindex1 == wordindex2){
			fail("Different words does not hash to distinct int for index purposes");
		}
	}	
}
