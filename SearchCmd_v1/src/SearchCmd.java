import java.io.*;

/**
 * Original class by Rune Hansen, updated by Alexandre Buisse
 * HMTLlist node object
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 1.0
 */
class HTMLlist {
    String str;
    HTMLlist next;

    /**
     * HTMLlist constructor
     * 
     * @param s
     * @param n
     */
    HTMLlist (String s, HTMLlist n) {
        str = s;
        next = n;
    }
}


/**
 * Original Searcher class by Rune Hansen, updated by Alexandre Fuisse
 * 
 * @author Jonas, Tonny, Mikkel, Niels
 * @version 1.0
 */
class Searcher {
	
    /**
     * Exists method takes a HTMLlist object and a string as parameters.
     * The method compares HTMLlist.str with the string and returns true if they are equal.
     * 
     * @param l
     * @param word
     * @return
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
     * Reads a file, and creates a linkedlist of it.
     * 
     * @param filename
     * @return
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

/**
 * The SearchCmd class containing the main method 
 * to run the program. 
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 1.0
 */
public class SearchCmd {

    /**
     * The main method
     * 
     * @param args
     * @throws IOException
     */
    public static void main (String[] args) throws IOException {
        String name;

        // Check that a filename has been given as argument
        if (args.length != 1) {
            System.out.println ("Usage: java SearchCmd <datafile>");
            System.exit (1);
        }

        // Read the file and create the linked list
        long start = System.currentTimeMillis();
        HTMLlist l = Searcher.readHtmlList (args[0]);
        long end = System.currentTimeMillis();
        
        // Ask for a word to search
        BufferedReader inuser =
            new BufferedReader (new InputStreamReader (System.in));

        System.out.println ("Hit return to exit.");
        while (true) {
        	System.out.println("Load time: " + (end-start));
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