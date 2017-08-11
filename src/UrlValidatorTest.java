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
        fct.testAnyOtherUnitTest();
        fct.testUrlCombinations(true,true);
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

       int count = 0;
       int testcount = 0;

       if(urlVal.isValid("http://www.amazon.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(urlVal.isValid("https://www.amazon.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(urlVal.isValid("ftp://www.amazon.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(urlVal.isValid("h3t://www.amazon.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(!urlVal.isValid("http:/www.amazon.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(!urlVal.isValid("http://www.amazon")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(!urlVal.isValid("http://www.amazon:85")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(!urlVal.isValid("http://www.amazon.com:a")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(!urlVal.isValid("http://www.amazon.com:86v")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(urlVal.isValid("http://www.amazon.com:55")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(urlVal.isValid("http://amazon.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(urlVal.isValid("http://ww.amazon.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(!urlVal.isValid("http://www.amazon_.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(!urlVal.isValid("http//www.amazon.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(!urlVal.isValid("http:/www.amazon.com")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(!urlVal.isValid("http://256.256.256.256:80")){
           count++;
       }
       testcount++;
       if(!urlVal.isValid("http://1.2.3.4.5:80")){
           count++;
       }
       testcount++;
       report(count,testcount);
       if(urlVal.isValid("http://255.255.255.255:80")){
           count++;
       }
       testcount++;


       System.out.printf("Tests Passed: %d     Tests Failed: %d\n", count, testcount-count);


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


    private void report(int i, int j){
        if(i<j){
            System.out.printf("Test %d Failed \n", j);
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
     */
    public void testUrlCombinations(boolean showPassing, boolean showFailing) {

        // Create validator
        UrlValidator urlVal = new UrlValidator();

        // Get total number of url combinations to be tested
        int totalUrlCombinations = 1;
        int totalPassingUrls = 0;

        for (ResultPair[] segments : testSegments) {
            totalUrlCombinations *= segments.length;
        }

        // Append scheme
        for (ResultPair scheme : schemes) {
            for (ResultPair host : hosts) {
                for (ResultPair port : ports) {
                    for (ResultPair path : paths) {
                        for (ResultPair query : querys) {
                            for (ResultPair fragment : fragments) {

                                // Create URL
                                String testUrl =
                                        scheme.item + host.item + port.item + path.item + query.item + fragment.item;

                                // Get whether URL is or is not valid
                                boolean expectedOutput =
                                        scheme.valid && host.valid && port.valid && path.valid && query.valid && fragment.valid;

                                // Get result of isValid function
                                boolean actualOutput = urlVal.isValid(testUrl);

                                if (expectedOutput != actualOutput) {
                                    if (expectedOutput)
                                        System.out.println("isValid: FAIL, expected: PASS - " + testUrl);
                                    else
                                        System.out.println("isValid: PASS, expected: FAIL - " + testUrl);
                                }

                            }
                        }
                    }
                }
            }
        }
    }

   private ResultPair[] schemes = {
           new ResultPair("http://", true),
           new ResultPair("htp://", false),
           new ResultPair("https://", true),
           new ResultPair("ftp://", true),
           new ResultPair("mailto://", true),
           new ResultPair("file://", true),
           new ResultPair("data://", true),
           new ResultPair("irc://", true),
           new ResultPair("helloWorld://", false),
           new ResultPair("youShallNotPass://", false),
           new ResultPair("http:/", false),
           new ResultPair("http//", false),
           new ResultPair("http", false)
    };

   private ResultPair[] hosts = {
           new ResultPair("192.168.1.1", true),
           new ResultPair("www.google.com", true),
           new ResultPair("helloWorld", false),
           new ResultPair("&;", false),
           new ResultPair("youShallNotPass", false)
   };

   private ResultPair[] ports = {
           new ResultPair("", true),
           new ResultPair(":2000", true),
           new ResultPair(":", false),
           new ResultPair(":NotAPort", false),
           new ResultPair("ReallyNotAPort", false)
   };

    private ResultPair[] paths = {
            new ResultPair("", true),
            new ResultPair("/a", true),
            new ResultPair("/a/b/c/d", true),
            new ResultPair("/1000", true),
            new ResultPair("/a-b-c-d", true),
            new ResultPair("/a_b_c_d", true),
            new ResultPair("//a", false),
            new ResultPair("/a b c d/a", false),
            new ResultPair("/&", false),
            new ResultPair("/;", false)
    };

    private ResultPair[] querys = {
            new ResultPair("?q=test", true),
            new ResultPair("?q=test&para=78", true),
            new ResultPair("?q=hello+world&para=78", true),
            new ResultPair("?q=test&para=78", true),
            new ResultPair("?a b c d", false)
    };


    private ResultPair[] fragments = {
            new ResultPair("", true),
            new ResultPair("#test", true),
            new ResultPair("#a b c d", false),
            new ResultPair("#&&", false)
    };

    private ResultPair[][] testSegments = {schemes, hosts, ports, paths, querys, fragments};
}
