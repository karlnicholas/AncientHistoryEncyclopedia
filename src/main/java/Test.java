
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

import ahe.lucene.SearchFiles;
import ahe.lucene.SearchResult;

public class Test {
    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(Test.class);
    SearchFiles searchFiles;
    
	public static void main(String[] args) {
		new Test().run();
	}
	public Test() {
		searchFiles = new SearchFiles();
	}
	public void run() {
		List<SearchResult> searchResults;
		try {
			searchResults = searchFiles.query("pyramid");
			if ( searchResults.size() > 0 ) {
				System.out.println("Found results: " + searchResults.size());
				for ( SearchResult searchResult: searchResults ) {
					System.out.println(searchResult);
				}
				System.out.println("Best result\n"+searchResults.get(0).preamble);
				logger.info("Quote found: " + searchResults.get(0).subject);
				logger.info("Image at : " + searchResults.get(0).imgSrc);
		        // Write log to CloudWatch using LambdaLogger.
			}
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
