import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

/**
 * Test cases for the first part of the assignment
 * 
 * @author Niels, Mikkel, Jonas, Jonas
 * @version 1.0
 */
public class SearchCmdTest {
	/** Constant */ 
	private static final String ERROR = "Should not return ";

	/**
	 * Checks that exists() does not return true
	 * if null is passed, in either parameter.
	 */
	@Test
	public void existsReturnsFalse() {
		HTMLlist l = new HTMLlist("IT", null);
		if(Searcher.exists(null, null)){
			fail(ERROR);
		}	
		else if(Searcher.exists(l, null)){
			fail(ERROR);
		}
	}

	/**
	 * Checks that readHtmlList throws exception 
	 * if invalid file is inserted.
	 * 
	 * @throws IOException
	 */
	@Test(expected = IOException.class)
	public void shouldThrowException() throws IOException{
		Searcher.readHtmlList("res/itcwww-none.txt"); // should not exist
		fail("Should throw IOException");
	}

	/**
	 * Checks that readHtmlList reads the file properly
	 * and that exists is able to find the word in the list
	 */
	@Test
	public void shouldReadList(){
		try {
			HTMLlist l = Searcher.readHtmlList("res/itcwww-test.txt"); // should read filename
			if(!Searcher.exists(l, "Stillinger")){
				fail("Should be able to find word");
			}
		} catch (Exception e) {
			fail("should pass");
		}
	}
	
	/**
	 * Checks that the HTMLlist objects can be 
	 * properly linked, and that the parameters 
	 * are correct.
	 */
	@Test
	public void htmlListIsLinked(){
		HTMLlist val1 = new HTMLlist("String", null);
		HTMLlist val2 = new HTMLlist("Word", null);
		
		val1.next = val2;
		
		if (!val1.next.str.equals("Word")){
			fail("Should pass with word");
		}
	}
}