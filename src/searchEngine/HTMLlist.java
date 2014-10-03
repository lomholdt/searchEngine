package searchEngine;
/**
 * This is the HTMLlist node object 
 * @author Lomholdt
 *
 */
public class HTMLlist {
    String word;
    URLlist urls;
    HTMLlist next;
    
    /**
     * The HTMLlist node constructor
     * @param s
     * @param n
     */
    HTMLlist (String w, HTMLlist n, URLlist u) {
        word = w;
        urls = u;
        next = n;
    }
}
