#Simple AWS Lambda function for Alexa
A simple [AWS Lambda](http://aws.amazon.com/lambda) function that demonstrates a basic skill for the Amazon Echo using the Alexa SDK.

## Concepts
This shows how to create a Lambda function for handling Alexa Skill requests that:

 - Web service: communicate with an external web service to get random philosophy quotes (plato.stanford.edu)
 - SSML: Using SSML tags to control how Alexa renders the text-to-speech.

## Setup
To run this example skill you need to do two things. The first is to deploy the example code in lambda, and the second is to configure the Alexa skill to use Lambda.

### AWS Lambda Setup
1. Go to the AWS Console and click on the Lambda link. Note: ensure you are in us-east or you wont be able to use Alexa with Lambda.
2. Click on the Create a Lambda Function or Get Started Now button.
3. Skip the blueprint
4. Name the Lambda Function "Encyclopedia-Of-Philosophy-Skill".
5. Select the runtime as Java 8
6. Go to the the samples/ directory containing pom.xml, and run 'mvn assembly:assembly -DdescriptorId=jar-with-dependencies package'. This will generate a zip file named "alexa-skills-kit-samples-1.0-jar-with-dependencies.jar" in the target directory.
7. Select Code entry type as "Upload a .ZIP file" and then upload the "alexa-skills-kit-samples-1.0-jar-with-dependencies.jar" file from the build directory to Lambda
8. Set the Handler as encyclopediaofphilosophy.EncyclophiaOfPhilosophySpeechletRequestStreamHandler (this refers to the Lambda RequestStreamHandler file in the zip).
9. Create a basic execution role and click create.
10. Leave the Advanced settings as the defaults.
11. Click "Next" and review the settings then click "Create Function"
12. Click the "Event Sources" tab and select "Add event source"
13. Set the Event Source type as Alexa Skills kit and Enable it now. Click Submit.
14. Copy the ARN from the top right to be used later in the Alexa Skill Setup.

### Alexa Skill Setup
1. Go to the [Alexa Console](https://developer.amazon.com/edw/home.html) and click Add a New Skill.
2. Set "Encyclopedia of Philosophy" as the skill name and "Encyclopedia of Philosophy" as the invocation name, this is what is used to activate your skill. For example you would say: "Alexa, Ask Encyclopedia of Philosophy for a quote.."
3. Select the Lambda ARN for the skill Endpoint and paste the ARN copied from above. Click Next.
4. Copy the Intent Schema from the included IntentSchema.json.
5. Copy the Sample Utterances from the included SampleUtterances.txt. Click Next.
6. Go back to the skill Information tab and copy the appId. Paste the appId into the PhilosophyEncyclopediaSpeechletRequestStreamHandler.java file for the variable supported ApplicationIds,
   then update the lambda source zip file with this change and upload to lambda again, this step makes sure the lambda function only serves request from authorized source.
7. You are now able to start testing your sample skill! You should be able to go to the [Echo webpage](http://echo.amazon.com/#skills) and see your skill enabled.
8. In order to test it, try to say some of the Sample Utterances from the Examples section below.
9. Your skill is now saved and once you are finished testing you can continue to publish your skill.

## Examples
### One-shot model:
  User: "Alexa, ask Encyclopedia of Philosophy  for a quote."
  Alexa: "Pragmatism was a philosophical tradition that originated in the United States around 1870 ..."
