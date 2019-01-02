/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package ancienthistoryencyclopedia;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import ancienthistoryencyclopedia.handlers.CancelandStopIntentHandler;
import ancienthistoryencyclopedia.handlers.AncientHistoryEncyclopediaQuoteIntentHandler;
import ancienthistoryencyclopedia.handlers.AncientHistoryEncyclopediaSearchIntentHandler;
import ancienthistoryencyclopedia.handlers.FallbackIntentHandler;
import ancienthistoryencyclopedia.handlers.HelpIntentHandler;
import ancienthistoryencyclopedia.handlers.LaunchRequestHandler;
import ancienthistoryencyclopedia.handlers.SessionEndedRequestHandler;

public class AncientHistoryEncyclopediaStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new AncientHistoryEncyclopediaQuoteIntentHandler(),
                        new AncientHistoryEncyclopediaSearchIntentHandler(),
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler())
                // Add your skill id below
                .withSkillId("amzn1.ask.skill.0a4bff09-3f4a-406d-b1fc-2441c56f6144")
                .build();
    }

    public AncientHistoryEncyclopediaStreamHandler() {
        super(getSkill());
    }

}
