import java.io.*;

/**
 * The Searcher class
 * @author Lomholdt
 * @version 0.1
 */
public class Searcher {
	
	private static final String PREFIX_STRING = "*PAGE";
	
	/**
	 * The exists method
	 * @param l
	 * @param word
	 * @return If a given word exists in a list
	 */
    public static boolean exists (HTMLlist l, String word) {
        while (l != null) {
            if (l.str.equals (word)) {
                return true;
            }
            l = l.next;
        }
        return false;
    }

    /**
     * The readHtmlList method
     * @param filename
     * @return A htmllist with the start pointer to the linked list
     * @throws IOException
     */
    public static HTMLlist readHtmlList (String filename) throws IOException {
        String name;
        HTMLlist start, current, tmp;

        // Open the file given as argument
        BufferedReader infile = new BufferedReader (new FileReader (filename));

        name = infile.readLine(); //Read the first line
        start = new HTMLlist (name, null);
        current = start;
        name = infile.readLine(); // Read the next line
        while (name != null) {    // Exit if there is none
            tmp = new HTMLlist (name, null);
            current.next = tmp;
            current = tmp;            // Update the linked list
            name = infile.readLine(); // Read the next line
        }
        infile.close(); // Close the file

        return start;
    }
}
