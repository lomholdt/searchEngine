
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
	//	public static void main(String[] args) throws IOException {
	//        String word;
	//        
	//        //Check that a filename has been given as argument
	//        if (args.length != 1) {
	//            System.out.println("Usage: java SearchCmd <datafile>");
	//            System.exit(1);
	//        }
	//
	//        long startTime = System.currentTimeMillis();
	//        // Read the file and create the linked list
	//        HTMLlist l = Searcher.readHtmlList(args[0]);
	//        long stopTime = System.currentTimeMillis();
	//
	//        // Ask for a word to search
	//        //BufferedReader inuser = new BufferedReader(new InputStreamReader (System.in));        
	//        BufferedReader inuser = new BufferedReader(new InputStreamReader(System.in, "UTF-8")); // UTF-8 capable input reader
	//        
	//        System.out.println("Hit return to exit.");
	//        System.out.println("File load time: " + (stopTime - startTime));
	//        while(true) {
	//        	HTMLlist tmp = l; // make new temp front pointer, so we can search several times
	//            System.out.print("Search for: ");
	//            word = inuser.readLine(); // Read a line from the terminal
	//            if (word == null || word.length() == 0) {
	//                return;
	//            }
	//            else{
	//            	Searcher.getWordUrls(word, tmp);
	//            }
	
	//            else {
	//            	Searcher.existsOnPage(l, word);
	//            }
	//            else if (Searcher.exists (l, word)) {
	//                System.out.println ("The word \""+word+"\" has been found.");
	//            }
	//            else {
	//                System.out.println ("The word \""+word+"\" has NOT been found.");
	//            }
	//        }
	//    }
	
	public static void main(String[] args) throws IOException{
		if (args.length != 1) {
			System.out.println("Usage: java SearchCmd <datafile>");
			System.exit(1);
		}
		// Read the file and create the hashed array
		long startTime = System.currentTimeMillis();
		HTMLlist[] hashedArray = HashTable.createArray(args[0]);
		long stopTime = System.currentTimeMillis();			
		
		// show quick statistics
		System.out.println("Hit return to exit.");
		System.out.printf("File load time:\t %d%n", (stopTime - startTime));
		printStatistics();
		startSearcher(hashedArray);
		System.out.println("Exiting");
	}
	
	/**
	 * Prints small statistics about the array created in HashTable class
	 */
	public static void printStatistics(){
		System.out.printf("Load factor:\t %f @ %d%n", HashTable.getLoadFactor(), HashTable.getArraySize());
		System.out.printf("Array elements:\t %d%n", HashTable.getArrayCount());
		System.out.printf("Words:\t\t %d%n", HashTable.getWordCount());
		System.out.printf("URL's:\t\t %d%n", HashTable.getUrlCount());
	}
	
	/**
	 * Starts the search program until user quits 
	 * 
	 * @param hashedArray the hashed array
	 * @throws IOException
	 */
	public static void startSearcher(HTMLlist[] hashedArray) throws IOException{ //FIXME Will this bastard copy the array or just copy the pointer???
		String word;
		int wordIndex;
		HTMLlist front;
		
		BufferedReader inuser = new BufferedReader(new InputStreamReader(System.in, "UTF-8")); // UTF-8 capable input reader
		
		while(true){
			System.out.print("Hash search for: ");
			word = inuser.readLine();

			if(word == null || word.length() == 0){
				break;
			}
			else{
				wordIndex = HashTable.getWordIndex(word);
				front = hashedArray[wordIndex];
				if(front == null){
					System.out.println("No result");
				}
				else{
					Searcher.getWordUrls(word, front);
				}
			}
		}
	}
}
