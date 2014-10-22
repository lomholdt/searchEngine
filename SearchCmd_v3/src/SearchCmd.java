import java.io.*;

/**
 * The SearchCmd class containing the main method
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 3.0
 */
public class SearchCmd {

	/**
	 * Main method in the program, for running the program.
	 * 
	 * @param args path to datafile to open
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String word;

		//Check that a filename has been given as argument
		if (args.length != 1) {
			System.out.println("Usage: java SearchCmd <datafile>");
			System.exit(1);
		}

		long startTime = System.currentTimeMillis(); // set a start time 
		HTMLlist l = Searcher.readHtmlList(args[0]); // Read the file and create the linked list
		long stopTime = System.currentTimeMillis(); // set a end time

		// Ask for a word to search   
		BufferedReader inuser = new BufferedReader(new InputStreamReader(System.in, "UTF-8")); // UTF-8 capable input reader

		System.out.println("Hit return to exit.");
		System.out.println("File load time: " + (stopTime - startTime));
		while(true) {
			HTMLlist tmp = l; // make new temp front pointer, so we can search several times
			System.out.print("Search for: ");
			word = inuser.readLine(); // Read a line from the terminal
			if (word == null || word.length() == 0) {
				return; // Returns from main method - thus terminating the program
			}
			else{
				Searcher.getWordUrls(word, tmp);
			}
		}
	}
}