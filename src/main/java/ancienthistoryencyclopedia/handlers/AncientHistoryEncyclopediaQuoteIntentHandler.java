/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package ancienthistoryencyclopedia.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.Image;

import ahe.lucene.SearchResult;
import quote.GetQuote;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

import static com.amazon.ask.request.Predicates.intentName;

public class AncientHistoryEncyclopediaQuoteIntentHandler implements RequestHandler {
    private static final Logger logger = LogManager.getLogger(AncientHistoryEncyclopediaQuoteIntentHandler.class);
    private GetQuote getQuote = new GetQuote();

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GetQuote"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String speechText;
		SearchResult searchResult = null ;
		Image cardImage = null; 
		try {
			searchResult = getQuote.getRandomQuote();
		} catch (IOException | ParseException e) {
			logger.error(e);
		}
		if ( searchResult == null || searchResult.preamble.isEmpty()) {
			speechText = "There is a problem connecting to the Ancient History Encyclopedia at this time."
					+ "Please try again later.";
			logger.error("There is a problem connecting to the Ancient History Encyclopedia at this time.");
	        return input.getResponseBuilder()
	                .withSpeech(speechText)
	                .build();
		} else {
			speechText = "Random entry for "+searchResult.subject+". " + searchResult.preamble;
			cardImage = Image.builder().withSmallImageUrl("https://www.ancient.eu"+searchResult.imgSrc).build();
			logger.info("Random entry for "+searchResult.subject + "=" + searchResult.url);
		}

        return input.getResponseBuilder()
                .withSpeech(speechText + "<p>You can ask for another quote or do a search.</p>")
                .withStandardCard("Ancient History Encyclopedia",  speechText, cardImage)
                .withReprompt("You can search for an entry, ask for a quote, or stop.")
                .withShouldEndSession(false)
                .build();
    }
}
