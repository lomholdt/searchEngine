

import java.io.*;
import java.util.HashSet;

/**
 * HashTable class can create an array of HMTLlist pointers
 * with words indexed using a hash function. 
 */
public class HashTable {
	/* Constants */
	private static final int ARRAY_SIZE = 5000000;
	private static final String PREFIX_STRING = "*PAGE:";
	private static final String LOADING_FILE = "Loading file...";
	private static final String CARRIAGE_RETURN = "\r";
	
	/* Data fields */
	private static int wordCount;
	private static int urlCount;
	private static int arrayCount;
	private static HTMLlist[] hashTable = new HTMLlist[ARRAY_SIZE];
	
	/**
	 * Retrieves the calculated index for the word
	 * 
	 * @param word the word to find a index for
	 * @return an integer index
	 */
	public static int getWordIndex(String word){
		return Math.abs(word.hashCode()) % ARRAY_SIZE;
	}
	
	/**
	 * Returns how many unique words are in the array
	 * 
	 * @return number of unique words
	 */
	public static int getWordCount(){
		return wordCount;
	}
	
	/**
	 * Returns how many unique urls are in the array
	 * 
	 * @return number of unique urls
	 */
	public static int getUrlCount(){
		return urlCount;
	}
	
	/**
	 * Returns how many elements are stored in the array
	 * 
	 * @return elements stored in array
	 */
	public static int getArrayCount(){
		return arrayCount;
	}
	
	/**
	 * Returns the load factor for the array/table
	 * 
	 * @return the load factor for the table
	 */
	public static double getLoadFactor(){
		return (double)getArrayCount() / (double)ARRAY_SIZE;
	}
	
	/**
	 * Returns the initialized size of the array
	 * 
	 * @return the size of the array
	 */
	public static int getArraySize(){
		return ARRAY_SIZE;
	}
	
	/**
	 * Appends a HTMLlist object to the element
	 * passed in, which should be the last element 
	 * of a linked list of HTML objects.
	 * 
	 * @param word the word to add to the HTMLlist object
	 * @param url the url where the word was found
	 * @param lastElementInList pointer to the last HTMLlist object in the list
	 */
	private static void appendToHtmlList(String word, String url, HTMLlist lastElementInList){
		HashSet<String> urls = new HashSet<String>();
		urls.add(url);
		lastElementInList.next = new HTMLlist(word, urls, null);
	}
	
	/**
	 * Appends a URLlist object to the element
	 * passed in, which should be the last element 
	 * of a linked list of HTML objects.
	 * 
	 * @param url the url to be added
	 * @param lastElementInList pointer to the last URLlist object in the list
	 */
	private static void appendToUrlList(String url, URLlist lastElementInList){
		lastElementInList.next = new URLlist(url, null);
	}
	
	/**
	 * Adds a HTMLlist object to the array at the given index
	 * with the given word and URL.
	 * 
	 * @param word the word to be added to the HTMLlist object
	 * @param url the URL to be added to the URLlist object
	 * @param index the index position to add the object
	 */
	private static void addToArray(String word, String url, int index){
		HashSet<String> urls = new HashSet<String>();
		urls.add(url);
		HTMLlist tmpHtml = new HTMLlist(word, urls, null);
		hashTable[index] = tmpHtml;
	}
	
	/**
	 * Creates an HTMLlist array with pointers to the words
	 * that has been indexed. Uses getWordIndex hash function
	 * to get index value.
	 * 
	 * @param filename the path to the file to load
	 * @return an array of HTMLlist pointers to the words
	 * @throws IOException
	 */
	public static HTMLlist[] createArray(String filename) throws IOException{
		String line, currentUrl;
		URLlist tmpUrl;
		HTMLlist front, tmpHtml; 
		int wordIndex;
		
		System.out.print(LOADING_FILE); // tell user that file is loading
		
		BufferedReader infile = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8")); // UTF-8 capable file reader
		line = infile.readLine(); // read first line
		currentUrl = "";
		
		while (line != null){ // loop over each line in file
			if(line.startsWith(PREFIX_STRING)){ // it's a URL
				currentUrl = line.substring(PREFIX_STRING.length()); // remove prefix from URL
				urlCount++;
			}
			else{ // it's a word
				wordIndex = getWordIndex(line); // get index for word
				if (hashTable[wordIndex] == null){ // index is empty
					addToArray(line, currentUrl, wordIndex);
					arrayCount++;
					wordCount++;
				}
				else{ // index is not empty
					front = hashTable[wordIndex];
					tmpHtml = Searcher.HtmlListExists(front, line);
					if (!tmpHtml.word.equals(line)){ // it's a new word (not already present), append to back of list
						appendToHtmlList(line, currentUrl, tmpHtml);
						wordCount++;
					}
					else{ // word is already in list
						tmpHtml.urls.add(currentUrl); // we don't care if url is already present, HashSet will ignore if that's the case
					}
				}
			}
			line = infile.readLine();
		}
		infile.close();
		System.out.print(CARRIAGE_RETURN); // return terminal cursor to start
		return hashTable;
	}
}


