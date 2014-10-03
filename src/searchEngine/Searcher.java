package searchEngine;
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
	 * The method traverses a HTMLlist and returns boolean true
	 * if the word passed in is already present in the list.
	 * 
	 * @param l pointer to front of list
	 * @param word the word to search for
	 * @return If a given word exists in a list
	 */
    public static boolean exists(HTMLlist l, String word) {
        while (l != null) {
            if (l.word != null && l.word.equals(word)) {
                return true;
            }
            l = l.next;
        }
        return false;
    }

    /**
     * The method traverses a URLlist and returns boolean true
     * if the URL passed in is already present in the list.
     * 
     * @param l pointer to front of list
     * @param url the url to search for
     * @return boolean true or false
     */
    public static boolean UrlExists(URLlist l, String url) {
        while (l != null) {
            if (l.url != null && l.url.equals(url)) {
                return true;
            }
            l = l.next;
        }
        return false;
    }

    /**
     * The existsOnPage method loops over a HTMLlist and searches
     * for the given PREFIX_STRING. If the prefix is found it is stored
     * so whenever you find a word, it will print the URL to the terminal
     * where the word was found, but only if the URL has not been printed before.
     * 
     * @param l a HTMLlist list node
     * @param word a string word to search for
     */
    public static void existsOnPage(HTMLlist l, String word){
    	String currentURL = "";
    	boolean isUsed = false;
    	
    	while(l != null){
    		if(l.word.startsWith(PREFIX_STRING)){
    			currentURL = l.word.substring(PREFIX_STRING.length());
    			isUsed = false;
    		}
    		else if(l.word.equals(word) && !isUsed){
    			System.out.println("Exists on " + currentURL);
    			isUsed = true;
    		}
    		l = l.next;
    	}
    }


    
    
    /**
     * The readHtmlList method
     * 
     * @param filename
     * @return a HTMLlist with the start pointer to the linked list
     * @throws IOException
     */
    /** =================================================================================
//    public static HTMLlist readHtmlList(String filename) throws IOException {
//        String word;
//        HTMLlist start, current, tmp;
//
//        // Open the file given as argument
//        BufferedReader infile = new BufferedReader(new FileReader (filename));
//
//        word = infile.readLine(); //Read the first line
//        start = new HTMLlist(word, null);
//        current = start;
//        word = infile.readLine(); // Read the next line
//        while(word != null) {    // Exit if there is none
//            tmp = new HTMLlist(word, null);
//            current.next = tmp;
//            current = tmp;            // Update the linked list
//            word = infile.readLine(); // Read the next line
//        }
//        infile.close(); // Close the file
//
//        return start;
//    }
    
    =================================================================================*/
    
    
    
    
    /**
     * The new readHtmlList method
     * 
     * @param filename
     * @return pointer to front of list
     * @throws IOException
     */
    public static HTMLlist readHtmlList(String filename) throws IOException{
    	String line, currentUrl;
    	URLlist tmpUrl;
    	HTMLlist start, current, tmp;
    	
    	BufferedReader infile = new BufferedReader(new FileReader(filename)); // open the file
    	line = infile.readLine(); // first line in file
    	
    	start = new HTMLlist(null, null, null); // first node pointer
    	current = start;
    	currentUrl = "";
    	
    	while (line != null){ // while not end of file
    		if(line.startsWith(PREFIX_STRING)){ // it's a URL
    			String url = line.substring(PREFIX_STRING.length()); // remove prefix from URL
    			currentUrl = url;
    		}
    		else{ //  it's a word
    			tmp = start; // use temp as start pointer
    			if(!exists(tmp, line)){ // it has not been seen before
    
    				// ADD HTMLList
    				if (current.word == null){ // first run
    					current.word = line;
    					tmpUrl = new URLlist(currentUrl, null);
    					current.urls = tmpUrl;
    				}
    				else{
    					HTMLlist endOfList = getEndOfList(tmp);
    					tmpUrl = new URLlist(currentUrl, null);
    					// Add current to end of list
    					tmp = new HTMLlist(line, null, tmpUrl);
    					endOfList.next = tmp;
    					current = tmp;
    				}
    			}
    			else{ // it has been seen
    				// go to HTMLlist object with the word
    				current = getListObjectPosition(tmp, line);
    				
    				if (!UrlExists(current.urls, currentUrl)){ // if URL is not already added to the word
    					// go to end of URL list
    					URLlist endOfList = getEndOfList(current.urls);
    					
    					// add url to the list
    					tmpUrl = new URLlist(currentUrl, null);
    					endOfList.next = tmpUrl;
    				}
    			}
    		}
    		line = infile.readLine();
    	}
    	infile.close();
    
    	return start;
    }
    
    /**
     * Returns a pointer to the last node in a HTMLlist
     * 
     * @param front
     * @return pointer to last node in list
     */
    private static HTMLlist getEndOfList(HTMLlist front){
    	HTMLlist previous = front;
    	while(front != null){
    		previous = front;
    		front = front.next;
    	}
    	return previous;
    }
    
    /**
     * Returns a pointer to the last node in a URLlist
     * 
     * @param front pointer to front of list
     * @return pointer to last node in list
     */
    public static URLlist getEndOfList(URLlist front){
    	URLlist previous = front;
    	while(front != null){
    		previous = front;
    		front = front.next;
    	}
    	return previous;
    }
    
    /**
     * Returns a pointer to the first HTMLlist object with a matching word
     * 
     * @param front pointer to front of list
     * @param word the word to search for
     * @return a pointer to the node with matching word
     */
    public static HTMLlist getListObjectPosition(HTMLlist front, String word){
    	while(front != null){
    		if (front.word.equals(word)){
    			return front;
    		}
    		front = front.next;
    	}
    	return null;
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
    	while (l != null){
    		if (l.word.equals(word)){
    			URLlist passMe = l.urls; // must copy to keep pointer
    			while(passMe != null){
    				System.out.println(passMe.url);
    				passMe = passMe.next; 
    			}
    		}
    		l = l.next;
    	}
    }
}
