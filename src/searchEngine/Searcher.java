package searchEngine;
import java.io.*;

/**
 * The Searcher class
 * 
 * @author Lomholdt
 * @version 0.1
 */
public class Searcher {
	
	private static final String PREFIX_STRING = "*PAGE:";
	
	/**
	 * The exists method
	 * 
	 * @param l
	 * @param word
	 * @return If a given word exists in a list
	 */
    public static boolean exists(HTMLlist l, String word) {
    	
        while (l != null) {
        	//System.out.println("Checking " + l.word);
            if (l.word != null && l.word.equals(word)) {
                return true;
            }
            l = l.next;
        }
        return false;
    }
    

    /**
     * 
     * @param l
     * @param word
     * @return
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
     * @return A htmllist with the start pointer to the linked list
     * @throws IOException
     */
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
    
    /**
     * The new readHtmlList method
     * 
     * @param filename
     * @return
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
    			HTMLlist test1 = start;
    			if(!exists(test1, line)){ // it has not been seen before
    				
    				// GO TO END OF LIST,
    				HTMLlist test2 = start;
    				
    				//System.out.println("First run: " + endOfList.word);
    
    				// ADD HTMLList
    				if (current.word == null){ // first run
    					current.word = line;
    					tmpUrl = new URLlist(currentUrl, null);
    					current.urls = tmpUrl;
    					
    					
//    					System.out.println("Current is equal start");
    				}
    				else{
    					HTMLlist endOfList = getEndOfList(test2);
    					tmpUrl = new URLlist(currentUrl, null);
    					// Add current to end of list
    					tmp = new HTMLlist(line, null, tmpUrl);
    					endOfList.next = tmp;
    					current = tmp;
    					  
    					//System.out.println(endOfList.word);
    				}
    			}
    			else{ // it has been seen
//    				System.out.println(line + " already exists... ");
    				// go to HTMLlist object with the word
    				HTMLlist test3 = start;
    				current = getListObjectPosition(test3, line);
//    				System.out.println("WE NEED: " + line);
//    				System.out.println("WERE ON: " + current.word);
    				
    				if (!UrlExists(current.urls, currentUrl)){
    					// go to end of URL list
    					URLlist endOfList = getEndOfList(current.urls);
    					
    					// add url to the list
    					tmpUrl = new URLlist(currentUrl, null);
    					endOfList.next = tmpUrl;
    				}
    			}
    		}
    		line = infile.readLine();
//    		System.out.println(current.word); // TODO Remove this line
    	}
    	infile.close();
    
    	return start;
    }
    
    /**
     * 
     * @param front
     * @return
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
     * 
     * @param front
     * @return
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
     * 
     * @param front
     * @param word
     */
    public static HTMLlist getListObjectPosition(HTMLlist front, String word){ // FIXME if last object select previous
    	while(front != null){
    		if (front.word.equals(word)){
    			//System.out.println("FOUND IT\n" + front.word);
    			return front;
    		}
    		front = front.next;
    	}
    	System.out.println("her må vi ikke komme til");
    	//return front; // TODO somehow remove this...
    	return front;
    }
    
    
    public static void getWordUrls(String word, HTMLlist l){
    	while (l != null){
    		if (l.word.equals(word)){
    			while(l.urls != null){
    				System.out.println(l.urls.url);
    				l.urls = l.urls.next; 
    			}
    		}
    		l = l.next;
    	}
    }
}
