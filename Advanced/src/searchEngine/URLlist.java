package searchEngine;

/**
 * 
 * @author Lomholdt
 *
 */
public class URLlist {
	String url;
	URLlist next;
	
	/**
	 * 
	 * @param theUrl
	 * @param nextPointer
	 */
	public URLlist(String url, URLlist next) {
		this.url = url;
		this.next = next;
	}
}
