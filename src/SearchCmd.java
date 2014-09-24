import java.io.*;

/**
 * The SearchCmd class
 * @author Lomholdt
 * @author Niels
 */
public class SearchCmd {
	
	/**
	 * The main method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
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
            } else if (Searcher.exists (l, name)) {
                System.out.println ("The word \""+name+"\" has been found.");
            } else {
                System.out.println ("The word \""+name+"\" has NOT been found.");
            }
        }
    }
}
