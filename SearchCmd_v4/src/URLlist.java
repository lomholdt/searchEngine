/**
 * The URLlist node object class
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 3.0
 */
public class URLlist {
	String url;
	URLlist next;
	
	/**
	 * The URLlist node constructor
	 * 
	 * @param url to store
	 * @param pointer to next URLlist object
	 */
	public URLlist(String url, URLlist next) {
		this.url = url;
		this.next = next;
	}
}