package searchEngine;
import java.io.*;

/**
 * The SearchCmd class
 * 
 */
public class SearchCmd {
	
	/**
	 * Main method in the program, for running the program.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
        String word;
        long startTime = System.currentTimeMillis();
        
        //Check that a filename has been given as argument
        if (args.length != 1) {
            System.out.println("Usage: java SearchCmd <datafile>");
            System.exit(1);
        }

        // Read the file and create the linked list
        HTMLlist l = Searcher.readHtmlList(args[0]);
        long stopTime = System.currentTimeMillis();

        // Ask for a word to search
        BufferedReader inuser = new BufferedReader(new InputStreamReader (System.in));        

        System.out.println("Hit return to exit.");
        System.out.println("File load time: " + (stopTime - startTime));
        while(true) {
        	HTMLlist passMe = l;
            System.out.print("Search for: ");
            word = inuser.readLine(); // Read a line from the terminal
            if (word == null || word.length() == 0) {
                return;
            }
            else{
            	Searcher.getWordUrls(word, passMe);
            }
            
//            else {
//            	Searcher.existsOnPage(l, word);
//            }
//            else if (Searcher.exists (l, word)) {
//                System.out.println ("The word \""+word+"\" has been found.");
//            }
//            else {
//                System.out.println ("The word \""+word+"\" has NOT been found.");
//            }
        }
    }
}
