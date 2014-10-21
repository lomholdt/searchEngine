/**
 * This is the HTMLlist node object
 * modified for version 3 with a URLlist
 * datafield to store linked list of urls
 *  
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 3.0
 */
public class HTMLlist {
    String word;
    URLlist urls;
    HTMLlist next;
    
    /**
     * The HTMLlist node constructor
     * 
     * @param w word to store
     * @param u pointer to URLlist node object
     * @param n pointer to HTMLlist node object
     */
    HTMLlist (String w, URLlist u, HTMLlist n) {
        word = w;
        urls = u;
        next = n;
    }
}
