import java.io.*;

/**
 * The Searcher class contains several methods for creating
 * a linked list and searching through it.
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 3.0
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
     * Finds the URLlist object that matches the url parameter if present
     * otherwise it returns the last object in the list.
     * 
     * @param front the pointer to the front of the list
     * @param url the url to search for
     * @return returns the URLlist object
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
     * The new readHtmlList method, creates a linked list of HTMLlist objects, 
     * that includes URLlist objects to store the URLs.
     * 
     * @param filename file to read
     * @return pointer to front of list
     * @throws IOException
     */
    public static HTMLlist readHtmlList(String filename) throws IOException{
    	String line, currentUrl;
    	URLlist tmpUrl, endOfUrlList;
    	HTMLlist start, current, tmp, tmp2, endOfList;
    	
    	BufferedReader infile = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8")); // UTF-8 capable file reader
    	
    	line = infile.readLine(); // first line in file
    	
    	start = new HTMLlist(null, null, null); // first node pointer
    	current = start;
    	currentUrl = "";
    	
    	while (line != null){ // while not end of file
    		if(line.startsWith(PREFIX_STRING)){ // it's a URL
    			currentUrl = line.substring(PREFIX_STRING.length()); // remove prefix from URL
    		}
    		else{ //  it's a word
				if (current.word == null){ // if first run
					current.word = line;
					current.urls = new URLlist(currentUrl, null);
				}
    			tmp = start; // use temp as start pointer
    			tmp2 = HtmlListExists(tmp, line);
    			
    			if(!tmp2.word.equals(line)){ // it has not been seen before
    				// ADD HTMLList
					endOfList = tmp2;
					tmpUrl = new URLlist(currentUrl, null);
					// Add current to end of list
					tmp = new HTMLlist(line, tmpUrl, null);
					endOfList.next = tmp;
					current = tmp;
    			}
    			else{ // it has been seen
    				// go to HTMLlist object with the word
    				current = tmp2;
    				tmpUrl = UrlListExists(current.urls, currentUrl);
    				if (!tmpUrl.url.equals(currentUrl)){ // if URL is not already added to the word
    					// go to end of URL list
    					endOfUrlList = tmpUrl; // maybe remove and pull together with last line
    					
    					// add url to the list
    					endOfUrlList.next = new URLlist(currentUrl, null);
    				}
    			}
    		}
    		line = infile.readLine();
    	}
    	infile.close();
    	return start;
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