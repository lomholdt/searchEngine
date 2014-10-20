

import java.util.HashSet;

/**
 * This is the HTMLlist node object 
 * @author Lomholdt
 *
 */
public class HTMLlist {
    String word;
    HashSet<String> urls;
    HTMLlist next;
    
    /**
     * The HTMLlist node constructor
     * @param s
     * @param n
     */
    HTMLlist (String w, HashSet<String> u, HTMLlist n) {
        word = w;
        urls = u;
        next = n;
    }
}
