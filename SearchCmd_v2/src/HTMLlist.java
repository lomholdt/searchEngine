/**
 * The HTMLlist node object class
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 2.0
 */
class HTMLlist {
	String str;
	HTMLlist next;

	/**
	 * The HTMLlist node constructor
	 * 
	 * @param s the word to store
	 * @param n the pointer to the next HTMLlist node
	 */
	HTMLlist (String s, HTMLlist n) {
		str = s;
		next = n;
	}
}