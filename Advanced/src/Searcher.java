import java.io.*;

/**
 * The Searcher class contains several methods for creating
 * a linked list and searching through it.
 * 
 * @author dawartz
 * @author tonny
 * @author buchvart
 * @author lomholdt
 * @version 0.1
 */
public class Searcher {
	
	/* Constants */
	private static final String PREFIX_STRING = "*PAGE:";
    
    /**
     * Finds the HTMLlist object that matches the word parameter if present
     * otherwise it returns the last object in the list.
     * 
     * @param front the pointer to the front of the list
     * @param word the word to search for
     * @return returns the HTMLlist object
     */
    public static HTMLlist HtmlListExists(HTMLlist front, String word){
    	HTMLlist previous = front;
    	while(front != null){
    		if (front.word != null && front.word.equals(word)){
    			return front;
    		}
    		previous = front;
    		front = front.next;
    	}
    	return previous;
    }
    
    /**
     * Method for retrieving urls from hashset
     * 
     * @param word
     * @param l
     */
    public static void getWordUrlsInHashSet(String word, HTMLlist l){
    	int count = 0;
    	for (String url : l.urls) {
    		System.out.println(url);
    		count++;	
		}
    	System.out.printf("Found %d results%n", count);
    }
}
