import java.io.*;

/**
 * The SearchCmd class containing the main method 
 * to run the program. 
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 2.0
 */
public class SearchCmd {

	/**
	 * The main method
	 * 
	 * @param args path to datafile to open
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String name;

		// Check that a filename has been given as argument
		if (args.length != 1) {
			System.out.println("Usage: java SearchCmd <datafile>");
			System.exit(1);
		}

		// Read the file and create the linked list
		HTMLlist l = Searcher.readHtmlList(args[0]);

		// Ask for a word to search
		BufferedReader inuser = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Hit return to exit.");
		while (true) {
			System.out.print("Search for: ");
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