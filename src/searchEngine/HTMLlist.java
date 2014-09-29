package searchEngine;
/**
 * This is the HTMLlist node object 
 * @author Lomholdt
 *
 */
public class HTMLlist {
    String str;
    HTMLlist next;
    
    /**
     * The HTMLlist node constructor
     * @param s
     * @param n
     */
    HTMLlist (String s, HTMLlist n) {
        str = s;
        next = n;
    }
}
