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
   public class TestPair {
	   public TestPair(String urlPart, boolean isValid) {
		   this.urlPart = urlPart;
		   this.isValid = isValid;
	   }
	   public String urlPart;
	   public boolean isValid;
   };
   
   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   System.out.println("MANUAL TESTING");
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

       //  hypertext transfer protocol
	   System.out.println("hypertext transfer protocol");
	   System.out.println(urlVal.isValid("http://google.com"));

       //  www hostname prefix
	   System.out.println("www hostname prefix");
	   System.out.println(urlVal.isValid("http://www.google.com"));

       //  secure hypertext transfer protocol
	   System.out.println("secure hypertext transfer protocol");
	   System.out.println(urlVal.isValid("https://www.google.com"));

       //  Sub-domain - path character
	   System.out.println("Sub-domain - path character");
	   System.out.println(urlVal.isValid("https://www.google.com/"));

       //  Sub-domain - resolution without extension defintion
	   System.out.println("Sub-domain - resolution without extension defintion");
	   System.out.println(urlVal.isValid("https://search.google.com/test/mobile-friendly"));

       //  Sub-domain - resolution with file extension definition
	   System.out.println("Sub-domain - resolution with file extension definition");
	   System.out.println(urlVal.isValid("http://www.brainjar.com/java/host/test.html"));

       //  Sub-domain - resolution of query
	   System.out.println("Sub-domain - resolution of query");
	   System.out.println(urlVal.isValid("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_intro"));

       //  Reference translation - hostname & top-level domain
	   System.out.println("Reference translation - hostname & top-level domain");
	   System.out.println(urlVal.isValid("http://www.GOOGLE.COM"));

       //  Reference translation - all caps
	   System.out.println("Reference translation - all caps");
	   System.out.println(urlVal.isValid("HTTP://WWW.GOOGLE.COM"));

       //  Typo - protocol
	   System.out.println("Typo - protocol");
	   System.out.println(urlVal.isValid("httpp://www.google.com"));

       //  Typo - hostname prefix
	   System.out.println("Typo - hostname prefix");
	   System.out.println(urlVal.isValid("http://wwww.google.com"));

       //  Typo - top-level domain
	   System.out.println("Typo - top-level domain");
	   System.out.println(urlVal.isValid("http://www.google.comm"));

       //  Typo - unsupported character "¶" (pilcrow sign)
	   System.out.println("Typo - unsupported character ¶ (pilcrow sign)");
	   System.out.println(urlVal.isValid("http://www.¶google.comm"));

       //  Space - protocol
	   System.out.println("Space - protocol");
	   System.out.println(urlVal.isValid("ht tp://www.google.com"));

       //  Space - hostname prefix
	   System.out.println("Space - hostname prefix");
	   System.out.println(urlVal.isValid("http://w ww.google.com"));

       //  Space - hostname
	   System.out.println("Space - hostname");
	   System.out.println(urlVal.isValid("http://www. google.com"));

       //  Space - top-level domain
	   System.out.println("Space - top-level domain");
	   System.out.println(urlVal.isValid("http://www.google. com"));

       //  Tab - protocol
	   System.out.println("Tab - protocol");
	   System.out.println(urlVal.isValid("ht    tp://www.google.com"));

       //  Tab - hostname prefix
	   System.out.println("Tab - hostname prefix");
	   System.out.println(urlVal.isValid("http://w  ww.google.com"));

       //  Tab - hostname
	   System.out.println("Tab - hostname");
	   System.out.println(urlVal.isValid("http://www.   google.com"));

       //  Tab - top-level domain
	   System.out.println("Tab - top-level domain");
	   System.out.println(urlVal.isValid("http://www.google.    com"));

       //  Repeat entry - no 2nd protocol signal (if parse recognizes)
	   System.out.println("Repeat entry - no 2nd protocol signal (if parse recognizes)");
	   System.out.println(urlVal.isValid("http://www.google.com.google.com"));

       //  Repeat entry - with 2nd protocol signal (if parse recognizes)
	   System.out.println("Repeat entry - with 2nd protocol signal (if parse recognizes)");
	   System.out.println(urlVal.isValid("http://www.google.comhttp://www.google.com"));

       //  Repeat entry - with space between addresses (if parse recognizes)
	   System.out.println("Repeat entry - with space between addresses (if parse recognizes)");
	   System.out.println(urlVal.isValid("http://www.google.com http://www.google.com"));

       //  Port - (known) hypertext transfer protocol
	   System.out.println("Port - (known) hypertext transfer protocol");
	   System.out.println(urlVal.isValid("http://www.google.com:80"));

       //  Port - (known) daemon responsive
	   System.out.println("Port - (known) daemon responsive");
	   System.out.println(urlVal.isValid("http://www.google.com:8080"));

       //  Port - out-of-bounds (too high)
	   System.out.println("Port - out-of-bounds (too high)");
	   System.out.println(urlVal.isValid("http://www.google.com:80808080"));

       //  IP address - out-of-bounds (low, too few int-byte refs)
	   System.out.println("IP address - out-of-bounds (low, too few int-byte refs)");
	   System.out.println(urlVal.isValid("http://8.8.8"));

       //  IP address - (known) Google DNS
	   System.out.println("IP address - (known) Google DNS");
	   System.out.println(urlVal.isValid("http://8.8.8.8"));

       //  IP address - out-of-bounds (high, too many int-byte refs)
	   System.out.println("IP address - out-of-bounds (high, too many int-byte refs)");
	   System.out.println(urlVal.isValid("http://8.8.8.8.8"));

       //  IP address - malform syntax (trailing period)
	   System.out.println("IP address - malform syntax (trailing period)");
	   System.out.println(urlVal.isValid("http://8.8.8.8."));

       //  IP address - malform syntax (errant top-level-domain ref)
	   System.out.println("IP address - malform syntax (errant top-level-domain ref)");
	   System.out.println(urlVal.isValid("http://8.8.8.8.com"));

       //  UTF-8 - W3C reserved character ":" (colon)
	   System.out.println("UTF-8 - W3C reserved character : (colon)");
	   System.out.println(urlVal.isValid("http://www.google.com%3a80"));

       //  UTF-8 - unsupported character "^" (circumflex accent)
	   System.out.println("UTF-8 - unsupported character ^ (circumflex accent)");
	   System.out.println(urlVal.isValid("http://www.%5egoogle.com"));

       //  UTF-8 - space (protocol)
	   System.out.println("UTF-8 - space (protocol)");
	   System.out.println(urlVal.isValid("ht%20tp://www.google.com"));

       //  UTF-8 - space (hostname prefix)
	   System.out.println("UTF-8 - space (hostname prefix)");
	   System.out.println(urlVal.isValid("http://w%20ww.google.com"));

       //  UTF-8 - space (hostname)
	   System.out.println("UTF-8 - space (hostname)");
	   System.out.println(urlVal.isValid("http://www.%20google.com"));

       //  UTF-8 - space (top-level domain)
	   System.out.println("UTF-8 - space (top-level domain)");
	   System.out.println(urlVal.isValid("http://www.google.%20com"));

       // Empty string (no data) passed to isValid()
	   System.out.println("Empty string (no data) passed to isValid()");
	   System.out.println(urlVal.isValid(""));

       // File - unspecified
	   System.out.println("File - unspecified");
	   System.out.println(urlVal.isValid("file://"));

   }
   
   //strings that are long
   public void testPartition1()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String longUrl = "http://www.";
	   for(int i = 0; i < 100000; ++i) {
		   longUrl += "a";
	   }
	   longUrl += ".com";
	   System.out.println("Partition 1: Long URL");
	   System.out.println("Long Url Test (100,000 character URL): " + urlVal.isValid(longUrl));
   }
   
   //invalid schemes
   public void testPartition2(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "/://www.amazon.com",
			   "://www.amazon.com",
			   "/www.amazon.com",
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
   
   //invalid path
   public void testPartition4(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "http://www.amazon.com///",
			   "http://www.amazon.com///test",
			   "http://www.amazon.com/{"
	   };
	   System.out.println("Partition 4: Invalid Path (should all be false)");
	   for(int i = 0; i < testStrings.length; ++i) {
		   System.out.println(testStrings[i] + " " + urlVal.isValid(testStrings[i]));
	   }
   }
   
   //invalid port
   public void testPartition5(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "http://www.amazon.com:-1",
			   "http://www.amazon.com:a",
			   "http://www.amazon.com:123456"
	   };
	   System.out.println("Partition 5: Invalid Port (should all be false)");
	   for(int i = 0; i < testStrings.length; ++i) {
		   System.out.println(testStrings[i] + " " + urlVal.isValid(testStrings[i]));
	   }
   }
   
   //invalid query
   public void testPartition6(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "http://www.amazon.com?\n",
	   };
	   System.out.println("Partition 6: Invalid Query (should all be false)");
	   for(int i = 0; i < testStrings.length; ++i) {
		   System.out.println(testStrings[i] + " " + urlVal.isValid(testStrings[i]));
	   }
   }
   
   //valid urls without path, query, or port
   public void testPartition7(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "http://www.amazon.com",
			   "http://www.amazon.ac",
			   "http://www.amazon.zw",
			   "http://1.1.1.1",
			   "http://255.255.255.255",
	   };
	   System.out.println("Partition 7: Valid URLs with scheme/authority (should all be true)");
	   for(int i = 0; i < testStrings.length; ++i) {
		   System.out.println(testStrings[i] + " " + urlVal.isValid(testStrings[i]));
	   }
   }
   
   //valid urls with port
   public void testPartition8(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "http://www.amazon.com:1",
			   "http://www.amazon.com:65000",
			   
	   };
	   System.out.println("Partition 8: Valid URLs with port (should all be true)");
	   for(int i = 0; i < testStrings.length; ++i) {
		   System.out.println(testStrings[i] + " " + urlVal.isValid(testStrings[i]));
	   }
   }
   
   //valid urls with path
   public void testPartition9(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "http://www.amazon.com/test/",
			   "http://www.amazon.com/test/test",
			   
	   };
	   System.out.println("Partition 9: Valid URLs with path (should all be true)");
	   for(int i = 0; i < testStrings.length; ++i) {
		   System.out.println(testStrings[i] + " " + urlVal.isValid(testStrings[i]));
	   }
   }
   
   //valid urls with query
   public void testPartition10(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String testStrings[] = {
			   "http://www.amazon.com?test",
			   "http://www.amazon.com?test=1&test2=3",
			   
	   };
	   System.out.println("Partition 10: Valid URLs with query (should all be true)");
	   for(int i = 0; i < testStrings.length; ++i) {
		   System.out.println(testStrings[i] + " " + urlVal.isValid(testStrings[i]));
	   }
   }
   
   public void testIsValid()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   TestPair schemePairs[] = {
			new TestPair("http://", true),
			new TestPair("", false),
			new TestPair("://", false),
			new TestPair("ftp://", true),
			new TestPair("/", false),
			new TestPair("http", false),
			new TestPair("https://", true),
			
	   };
	   
	   TestPair authorityPairs[] = {
			new TestPair("", false),
			new TestPair("www.amazon.aa", false),
			new TestPair("..com", false),
			new TestPair("www.amazon..com", false),
			new TestPair("1.1.1.1.1", false),
			new TestPair("1.1.1", false),
			new TestPair("256.256.256.256", false),
			new TestPair("www.amazon.com", true),
			new TestPair("www.amazon.ac", true),
			new TestPair("www.amazon.zw", true),
			new TestPair("1.1.1.1", true),
			new TestPair("255.255.255.255", true),
	   };
	   
	   TestPair portPairs[] = {
			new TestPair(":65000", true),
			new TestPair(":111111", false),
			new TestPair(":-1", false),
			new TestPair(":1111", false),
			new TestPair(":1", true),
			new TestPair("", true)
	   };
	   
	   TestPair pathPairs[] = {
			new TestPair("", true),
			new TestPair("/test/", true),
			new TestPair("/test/test", true),
			new TestPair("//test/test", true),
			new TestPair("///test/test", false),
			new TestPair("/{", false),
	   };
	   
	   TestPair queryPairs[] = {
			new TestPair("?test", true),
			new TestPair("?test=1&test2=3", true),
			new TestPair("", true),
	   };
	   System.out.println("UNIT TESTING\n");
	   for(int a = 0; a < schemePairs.length; ++a) {
		   TestPair schemePair = schemePairs[a];
		   if(!schemePair.isValid) {
		   }
		   for(int b = 0; b < authorityPairs.length; ++b) {
			   TestPair authorityPair = authorityPairs[b];
			   if(!authorityPair.isValid) {
			   }
			   for(int c = 0; c < portPairs.length; ++c) {
				   TestPair portPair = portPairs[c];
				   if(!portPair.isValid) {
				   }
				   for(int d = 0; d < pathPairs.length; ++d) {
					   TestPair pathPair = pathPairs[d];
					   if(!pathPair.isValid) {
					   }
					   for(int e = 0; e < queryPairs.length; ++e) {
						   TestPair queryPair = queryPairs[e];
						   if(!queryPair.isValid) {
						   }
						   String url = schemePair.urlPart + 
								   authorityPair.urlPart + 
								   portPair.urlPart + 
								   pathPair.urlPart +
								   queryPair.urlPart;
						   boolean isValidUrl = schemePair.isValid &
								   authorityPair.isValid &
								   portPair.isValid &
								   pathPair.isValid &
								   queryPair.isValid;
						   boolean testResult = urlVal.isValid(url);
						   if(testResult != isValidUrl) {
							   System.out.println(url + " incorrectly reported as " + testResult);
						   }
					   }
				   }
			   }
		   }
	   }
   }
   

}