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





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   /*
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   System.out.println("Manual testing");
	   System.out.println(urlVal.isValid("http://www.amazon.com"));
	   System.out.println(urlVal.isValid("//www.amazon.com"));
	   System.out.println(urlVal.isValid("htp://www.amazon.com"));
	   System.out.println(urlVal.isValid("https://www.amazon.com"));
	   System.out.println(urlVal.isValid("://www.amazon.com"));
	   System.out.println(urlVal.isValid("www.amazon.com"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com"));
	   System.out.println(urlVal.isValid("http://www.amazon.comx"));
	   System.out.println(urlVal.isValid("http://www.amazon.ac"));
	   System.out.println(urlVal.isValid("http://www.amazon.zw"));
	   System.out.println(urlVal.isValid("http://www.amazon.com."));
	   System.out.println(urlVal.isValid("http://www.amazon.com.com"));
	   System.out.println(urlVal.isValid("http://www.amazon.com.1"));
	   System.out.println(urlVal.isValid("http://www.amazon.com/test"));
	   System.out.println(urlVal.isValid("http://www.amazon.com/test?test1"));
	   System.out.println(urlVal.isValid("http://1.1.1.1"));
	   System.out.println(urlVal.isValid("http://255.255.255.255"));
	   System.out.println(urlVal.isValid("http://256.256.256.256"));
	   */
   }
   
   //strings that are long
   public void testPartition1()
   {
	   /*
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String longUrl = "http://www.";
	   for(int i = 0; i < 100000; ++i) {
		   longUrl += "a";
	   }
	   longUrl += ".com";
	   System.out.println("Partition 1: Long URL");
	   System.out.println("Long Url Test: " + urlVal.isValid(longUrl));
	   */
   }
   //invalid schemes
   public void testPartition2(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "htp://www.amazon.com",
			   "://amazon.com",
			   "/amazon.com",
	   };
	   System.out.println("Partition 2: Invalid Scheme (should all be false)");
	   for(int i = 0; i < testStrings.length; ++i) {
		   System.out.println(testStrings[i] + " " + urlVal.isValid(testStrings[i]));
	   }

   }
   
   //invalid authority
   public void testPartition3(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "http://www.amazon.aa",
			   "http://..com",
			   "http://www.amazon..com",
			   "http://1.1.1.1.1",
			   "http://1.1.1",
			   "http://256.256.256.256",
	   };
	   System.out.println("Partition 3: Invalid Scheme (should all be false)");
	   for(int i = 0; i < testStrings.length; ++i) {
		   System.out.println(testStrings[i] + " " + urlVal.isValid(testStrings[i]));
	   }
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
   

}
