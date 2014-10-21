import java.io.*;

/**
 * The SearchCmd class containing the main method
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 4.0
 */
public class SearchCmd {

	/**
	 * The main method for running the program
	 * 
	 * @param args filename 
	 * @throws IOException
	 */
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
	public static void startSearcher(HTMLlist[] hashedArray) throws IOException{
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