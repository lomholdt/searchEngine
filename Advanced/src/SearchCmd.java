import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * The SearchCmd Class for advanced
 * 
 * @author Lomholdt
 * @version Advanced
 */
public class SearchCmd {
	private static final String SEARCH_FOR_MESSAGE = "Hash search for: ";
	private static final String HOW_TO_EXIT_MESSAGE = "Hit return to exit."; 
	private static final String FILE_LOAD_MESSAGE = "File load time:\t %d%n";
	private static final String END_PROGRAM_MESSAGE = "Exiting";
	private static final String USAGE_MESSAGE = "Usage: java SearchCmd <datafile>";
	
	private static final String INPUT_ENCODING_TYPE = "UTF-8";

	/**
	 * Main method for running the program
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		checkForInput(args);

		// Read the file and create the hashed array
		long startTime = System.currentTimeMillis();
		HTMLlist[] hashedArray = HashTable.createArray(args[0]);
		long stopTime = System.currentTimeMillis();			

		// show quick statistics
		System.out.println(HOW_TO_EXIT_MESSAGE);
		System.out.printf(FILE_LOAD_MESSAGE, (stopTime - startTime));
		printStatistics();
		startTokenSearcher(hashedArray);
		System.out.println(END_PROGRAM_MESSAGE);
	}

	/**
	 * Checks if argument is present, and exits if not.
	 * 
	 * @param args the input arguments
	 */
	private static void checkForInput(String[] args){
		if (args.length != 1) {
			System.out.println(USAGE_MESSAGE);
			System.exit(1);
		}
	}

	/**
	 * Prints small statistics about the array created in HashTable class
	 */
	private static void printStatistics(){
		System.out.printf("Load factor:\t %f @ %d%n", HashTable.getLoadFactor(), HashTable.getArraySize());
		System.out.printf("Array elements:\t %d%n", HashTable.getArrayCount());
		System.out.printf("Words:\t\t %d%n", HashTable.getWordCount());
		System.out.printf("URL's:\t\t %d%n", HashTable.getUrlCount());
	}
	
	/**
	 * startTokenSearcher
	 * 
	 * @param hashedArray
	 * @throws IOException
	 */
	private static void startTokenSearcher(HTMLlist[] hashedArray) throws IOException{
		String input, first, second, third;
		BufferedReader inuser = new BufferedReader(new InputStreamReader(System.in, INPUT_ENCODING_TYPE)); // UTF-8 capable input reader

		while(true){
			System.out.print(SEARCH_FOR_MESSAGE);
			input = inuser.readLine();
			StringTokenizer st = new StringTokenizer(input);

			if(input == null || input.length() == 0){
				break;
			}
			else if(st.hasMoreTokens()){
				int numberOfTokens = st.countTokens();
				if (numberOfTokens == 1){
					int wordIndex = HashTable.getWordIndex(input);
					HTMLlist front = hashedArray[wordIndex];
					if(front == null){
						System.out.println("No result");
					}
					else{
						Searcher.getWordUrlsInHashSet(front);
					}	
				}
				else if (numberOfTokens == 2){
					System.out.println("An error occured");
				}
				else if (numberOfTokens == 3){
					first = st.nextToken();
					second = st.nextToken().toUpperCase();
					third = st.nextToken();
					if(second.equals("AND")){
						tokenAndSearcher(first, third, hashedArray);
					}
					else if(second.equals("OR")){
						tokenOrSearcher(first, third, hashedArray);
					}
					else{
						System.out.println("3 tokens present, but error occured!");
					}
				}
				else{
					System.out.println("tokens error");
				} 
			}
		}
	}

	/**
	 * AND search
	 * 
	 * @param word1
	 * @param word2
	 * @param hashedArray
	 */
	private static void tokenAndSearcher(String word1, String word2, HTMLlist[] hashedArray){
		int wordIndex1 = HashTable.getWordIndex(word1);
		int wordIndex2 = HashTable.getWordIndex(word2);

		HTMLlist one = hashedArray[wordIndex1];
		HTMLlist two = hashedArray[wordIndex2];
		
		if (one == null || two == null){
			System.out.println("Both words does not occur together");
			return;
		}

		for (String url : one.urls) {
			for (String comparisonUrl : two.urls){
				if (url.equals(comparisonUrl)){
					System.out.println(url);
				}
			}
		}
	}
	
	/**
	 * OR search
	 * 
	 * @param word1
	 * @param word2
	 * @param hashedArray
	 */
	private static void tokenOrSearcher(String word1, String word2, HTMLlist[] hashedArray){
		int wordIndex1 = HashTable.getWordIndex(word1);
		int wordIndex2 = HashTable.getWordIndex(word2);

		HTMLlist one = hashedArray[wordIndex1];
		HTMLlist two = hashedArray[wordIndex2];
		
		HashSet<String> urls = new HashSet<>(); 
		
		if (one == null && two == null){
			System.out.println("Both words does not occur");
			return;
		}
		if(one != null){
			for (String url : one.urls){
				urls.add(url);
			}
		}
		if(two != null){
			for (String url : two.urls){
				urls.add(url);
			}			
		}
		for (String url : urls){
			System.out.println(url);
		}	
	}
}