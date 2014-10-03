package searchEngine;

public class testClient {
	
	public static void main(String[] args){
		URLlist a = new URLlist("url 1", null);
		URLlist b = new URLlist("url 2", null); 
		URLlist c = new URLlist("url 3", null);
		
		HTMLlist d = new HTMLlist("Aktuelt", null, a);
		HTMLlist e = new HTMLlist("IT", null, b);
		HTMLlist f = new HTMLlist("Universitet", null, c);
		
		a.next = b;
		b.next = c;
		
		d.next = e;
		e.next = f;
		
		URLlist end = Searcher.getEndOfList(a);
		HTMLlist test = Searcher.getListObjectPosition(d, "Aktuelt");
		
		while(test.urls != null){
			System.out.println(test.urls.url);
			test.urls = test.urls.next;
		}
		
		
		//System.out.println(test.urls.url);
		
		System.out.println(end.url); // should print url 3
		
		System.out.println(Searcher.UrlExists(a, "url 2") ? "YES" : "NO"); // should be YES
	}

}
