/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;

import java.util.Scanner;


/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

    /**
     * Only used to debug the unit tests.
     * @param argv
     */
    public static void main(String[] argv) {

        UrlValidatorTest fct = new UrlValidatorTest("url test");
        fct.testManualTest();
    }


   public UrlValidatorTest(String testName) {
      super(testName);
   }



   public void testManualTest()
   {
       // setup urlValidator
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       Scanner userInput = new Scanner(System.in);
       String url;

       System.out.println("Beginning Manual Testing");

       while(true)
       {
            //get user input
           System.out.print("q - to quit\n" +
                   "Type anything else to test it as a url\n" +
                   ">> ");

           url = userInput.next();

           // check if user entered 'q'
           if(url.length() == 1 && url.charAt(0) == 'q')
           {
               System.out.println("Ending Manual Testing");
               break;
           }

           // report the results
           if(urlVal.isValid(url))
           {
               System.out.println("Valid url");
           }
           else
           {
               System.out.println("Invalid url");
           }

       }




   }


   public void testYourFirstPartition()
   {

   }

   public void testYourSecondPartition(){

   }


   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {

	   }
   }

   public void testAnyOtherUnitTest()
   {

   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */


   private ResultPair[] scheme = {
           new ResultPair("http", true),
           new ResultPair("https", true),
           new ResultPair("ftp", true),
           new ResultPair("mailto", true),
           new ResultPair("file", true),
           new ResultPair("data", true),
           new ResultPair("irc", true),
           new ResultPair("helloWorld", false),
           new ResultPair("youShallNotPass", false)
    };

   private ResultPair[] host = {
           new ResultPair("192.168.1.1", true),
           new ResultPair("www.google.com", true),
           new ResultPair("ftp", true),
           new ResultPair("mailto", true),
           new ResultPair("file", true),
           new ResultPair("data", true),
           new ResultPair("irc", true),
           new ResultPair("helloWorld", false),
           new ResultPair("youShallNotPass", false)
   };


}
