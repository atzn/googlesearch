googlesearch
============

Example BDD project on Google Search webpage

To use Chrome, ensure that ChromeDriver is downloaded and correctly specified in SharedDriver.java (Line 38)

Then pass in -Dbrowser=chrome via VM args to use Chrome.

Alternatively you can specify -DpathToDriver via VM args to ChromeDriver's path if you wish.

The same has to be done with InternetExplorerDriver (Although the path is not coded into the SharedDriver)

What the test does
==================

Runs Google search, sample search tests, testing pagination via Cucumber-JVM

Credits
============

To Cucumber-JVM author Aslak for the SharedDriver implementation. This allows WebDriver instance to stay alive after the execution of each scenario.

Known Issues
============

Running the test on IntelliJ does not pass (?) because of Background usage (It'll prompt "Not started"); more investigation needed...

Works with Eclipse.

Tested on Windows 7 and Mac OS X 10.8
