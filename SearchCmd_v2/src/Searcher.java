import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Searcher class contains methods to create a LinkedList
 * of HTMLlist node objects.
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 2.0
 */
class Searcher {
	/* Constants */
	public static final String PREFIX_URL = "*PAGE:";

	/**
	 * Searches through a linked list of HTMLlist objects, and
	 * prints the url where a given word is found. If the word 
	 * does not exist at all, it prints a message to the console.
	 * 
	 * @param l a pointer to the front of the list
	 * @param word a word to search for
	 */
	public static void exists(HTMLlist l, String word) {
		boolean wordAlreadySeen = false;
		boolean wordExists = false;
		System.out.println("The word: " + word);
		String url ="";
		while (l != null) {
			if(l.str.startsWith(PREFIX_URL)){
				url = l.str.substring(PREFIX_URL.length());
				wordAlreadySeen = false;
			}
			else if(l.str.equals(word) && !wordAlreadySeen) {
				wordAlreadySeen = true;
				wordExists = true;
				System.out.println("Exist on page: " + url);
			}
			l = l.next;
		}
		if(!wordExists){
			System.out.println("- does not exist");
		}
	}

	/**
	 * Reads a file, and creates a linkedlist of it. 
	 * 
	 * @param filename full path to the file to open
	 * @return pointer to front of list
	 * @throws IOException
	 */
	public static HTMLlist readHtmlList(String filename) throws IOException {
		String name;
		HTMLlist start, current, tmp;

		// Open the file given as argument
		BufferedReader infile = new BufferedReader(new FileReader (filename));

		name = infile.readLine(); // Read the first line
		start = new HTMLlist(name, null);
		current = start;
		name = infile.readLine(); // Read the next line
		while (name != null) {    // Exit if there is none
			tmp = new HTMLlist(name, null);
			current.next = tmp;
			current = tmp;            // Update the linked list
			name = infile.readLine(); // Read the next line
		}
		infile.close(); // Close the file

		return start;
	}
}