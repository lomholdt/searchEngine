package searchEngine;

import java.io.*;

public class HashTable {

	private static final int ARRAY_SIZE = 5000000;
	private static HTMLlist[] ARRAY = new HTMLlist[ARRAY_SIZE];
	private static final String PREFIX_STRING = "*PAGE:";

	public static int getWordIndex(String word){
		return Math.abs(word.hashCode()) % ARRAY_SIZE;
	}
	
	public static HTMLlist[] createArray(String filename) throws IOException{
		String line, currentUrl;
		URLlist tmpUrl;
		HTMLlist front, tmpHtml, tmpHtml2;
		int wordIndex;
		
		BufferedReader infile = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8")); // UTF-8 capable file reader
		line = infile.readLine(); // read first line
		currentUrl = "";
		

		while (line != null){ // loop over each line in file
			if(line.startsWith(PREFIX_STRING)){ // it's a URL
				currentUrl = line.substring(PREFIX_STRING.length()); // remove prefix from URL
			}
			else{ // it's a word
				wordIndex = getWordIndex(line); // get index for word

				if (ARRAY[wordIndex] == null){ // INDEX IS EMPTY
					tmpUrl = new URLlist(currentUrl, null);
					tmpHtml = new HTMLlist(line, tmpUrl, null);
					ARRAY[wordIndex] = tmpHtml;
				}
				else{ // INDEX IS NOT EMPTY 
					front = ARRAY[wordIndex];
					tmpHtml = Searcher.HtmlListExists(front, line); 
					// check if word exists
					if (!tmpHtml.word.equals(line)){ // it's a new word (not already present), append to back of list
						tmpUrl = new URLlist(currentUrl, null);
						tmpHtml2 = new HTMLlist(line, tmpUrl, null);
						tmpHtml.next = tmpHtml2;
					}
					else{ // word is already in list
						// check if url exist in urls
						tmpUrl = Searcher.UrlListExists(tmpHtml.urls, currentUrl);
						if (!tmpUrl.url.equals(currentUrl)){ // if url is not already there
							tmpUrl.next = new URLlist(currentUrl, null);
						}
					}
				}
			}
			line = infile.readLine();
		}
		infile.close();
		return ARRAY;
	}
}
