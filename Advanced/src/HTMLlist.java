import java.util.HashSet;

/**
 * This is the HTMLlist node object 
 * @author Lomholdt
 * @version Advanced
 */
public class HTMLlist {
	String word;
	HashSet<String> urls;
	HTMLlist next;

	/**
	 * The HTMLlist node constructor
	 * 
	 * @param w word to store
	 * @param u HashSet of URL strings
	 * @param n pointer to next HTMLlist node
	 */
	HTMLlist (String w, HashSet<String> u, HTMLlist n) {
		word = w;
		urls = u;
		next = n;
	}
}