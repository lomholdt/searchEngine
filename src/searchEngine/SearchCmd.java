package searchEngine;
import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * The SearchCmd class
 * 
 */
public class SearchCmd {
	private static final String SEARCH_FOR_MESSAGE = "Hash search for: ";
	private static final String INPUT_ENCODING_TYPE = "UTF-8";

	public static void main(String[] args) throws IOException{
		checkForInput(args);

		// Read the file and create the hashed array
		long startTime = System.currentTimeMillis();
		HTMLlist[] hashedArray = HashTable.createArray(args[0]);
		long stopTime = System.currentTimeMillis();			

		// show quick statistics
		System.out.println("Hit return to exit.");
		System.out.printf("File load time:\t %d%n", (stopTime - startTime));
		printStatistics();
		startTokenSearcher(hashedArray);
		//		startSearcher(hashedArray);
		//		tokenCruncher("Hans AND Grethe");
		System.out.println("Exiting");
	}

	/**
	 * Checks if argument is present, and exits if not.
	 * 
	 * @param args the input arguments
	 */
	private static void checkForInput(String[] args){
		if (args.length != 1) {
			System.out.println("Usage: java SearchCmd <datafile>");
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
	 * Starts the search program until user quits 
	 * 
	 * @param hashedArray the hashed array
	 * @throws IOException
	 */
	private static void startSearcher(HTMLlist[] hashedArray) throws IOException{ //FIXME Will this bastard copy the array or just copy the pointer???
		String word;
		int wordIndex;
		HTMLlist front;

		BufferedReader inuser = new BufferedReader(new InputStreamReader(System.in, INPUT_ENCODING_TYPE)); // UTF-8 capable input reader

		while(true){
			System.out.print(SEARCH_FOR_MESSAGE);
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
					Searcher.getWordUrlsInHashSet(word, front);
				}
			}
		}
	}


	private static void startTokenSearcher(HTMLlist[] hashedArray) throws IOException{
		String input;
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
						Searcher.getWordUrlsInHashSet(input, front);
					}	
				}
				
				else if (numberOfTokens == 2){
					System.out.println("An error occured");
				}
				else if (numberOfTokens == 3){
					String first = st.nextToken();
					String second = st.nextToken().toUpperCase();
					String third = st.nextToken();
					if(second.equals("AND")){
						//System.out.printf("AND: %s %s", first, third);
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
