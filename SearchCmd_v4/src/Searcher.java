/**
 * The Searcher class contains several methods for searching
 * for words in a linked list.
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 4.0
 */
public class Searcher {
    
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
     * Finds the URLlist object that matches the url parameter if present
     * otherwise it returns the last object in the list.
     * 
     * @param front pointer to front of list
     * @param url to search for
     * @return pointer to the found object
     */
    public static URLlist UrlListExists(URLlist front, String url) {
    	URLlist previous = front;
        while (front != null) {
            if (front.url != null && front.url.equals(url)) {
                return front;
            }
            previous = front;
            front = front.next;
        }
        return previous;
    }
  
    /**
     * Prints all the URLs associated to a given HTMLlist node
     * The method searches through a HTMLlist and prints the 
     * URLs associated to the node that matches the word parameter.
     * 
     * @param word to search for in HTMLlist
     * @param l front pointer to front of list
     */
    public static void getWordUrls(String word, HTMLlist l){
    	int count = 0;
    	while (l != null){
    		if (l.word.equals(word)){
    			URLlist front = l.urls; // must copy to keep pointer
    			while(front != null){
    				System.out.println(front.url);
    				count++;
    				front = front.next; 
    			}
    		}
    		l = l.next;
    	}
    	System.out.printf("Found %d results%n", count);
    }
}