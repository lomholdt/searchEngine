import java.io.*;

class HTMLlist {
	String str;
	HTMLlist next;

	HTMLlist (String s, HTMLlist n) {
		str = s;
		next = n;
	}
}

class Searcher {
	public static final String PREFIX_URL = "*PAGE:";

	public static void exists(HTMLlist l, String word) {
		boolean exist = false;
		System.out.println("The word: " + word);
		String url ="";
		while (l != null) {
			if(l.str.startsWith(PREFIX_URL)){
				url = l.str.substring(6);
				exist = false;
			}
			if (l.str.equals(word) && !exist) {
				exist = true;
				System.out.println("Exist on page: " + url);
			}
			l = l.next;
		}
	}

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

public class SearchCmd {

	public static void main (String[] args) throws IOException {
		String name;

		// Check that a filename has been given as argument
		if (args.length != 1) {
			System.out.println ("Usage: java SearchCmd <datafile>");
			System.exit (1);
		}

		// Read the file and create the linked list
		HTMLlist l = Searcher.readHtmlList (args[0]);

		// Ask for a word to search
		BufferedReader inuser =
				new BufferedReader (new InputStreamReader (System.in));

		System.out.println ("Hit return to exit.");
		while (true) {
			System.out.print ("Search for: ");
			name = inuser.readLine(); // Read a line from the terminal
			if (name == null || name.length() == 0) {
				return;
			} 
			else{ 
				Searcher.exists(l, name);

			}

		}
	}
}
