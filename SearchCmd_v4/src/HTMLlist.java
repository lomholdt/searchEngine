/**
 * This is the HTMLlist node object 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 4.0
 */
public class HTMLlist {
    String word;
    URLlist urls;
    HTMLlist next;
    
    /**
     * The HTMLlist node constructor
     * 
     * @param w the word to store
     * @param u pointer to the next URLlist node
     * @param n pointer to the next HTMLlist node
     */
    HTMLlist (String w, URLlist u, HTMLlist n) {
        word = w;
        urls = u;
        next = n;
    }
}