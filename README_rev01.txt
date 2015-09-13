README_rev01.txt              2015-09-13

for wm001_rev01.java

Roswitha Remling
r.remling@att.net
650-796-9689


Please note: 
------------
Rev 01 became necessary, since some elements changed in the last 2-3 weeks.



Test Problem # 1:
-----------------

(Originally submitted 2015-08-24)

Automate an end-to-end user e-commerce transaction flow using any open source tool for www.walmart.com with an existing customer on Chrome or Safari browser.

Scenario to automate:
1. Login using existing account
2. Perform a search on home page from a pool of key words given below
3. Identify an item from the result set that you can add to cart
4. Add the item to cart
5. Validate that item added is present in the cart and is the only item in the cart

Test Data:
• Account details: create your own account
• Search terms: tv, socks, dvd, toys, iPhone

Testing tools and Programming language to be utilized: 
Any open source testing tool such as Webdriver, Watir etc. would work. Programming language for the test can be of your choice.

Attributes of a good test:
* Readability and clarity of intent
* Re-usability of code to implement more tests
* Eliminate duplication
* Robustness in test execution

* Put the code in github and please just send back the github link.

* Make sure you have a clear README file: 
  Reasoning behind your technical choices. Trade-offs you might have made, anything you left out, or what you might do differently if you were to spend additional time on the project.

* We’ll review your code based on:
  - First of all, you solution needs to run smoothly following the instructions in README
  - Clarity: does the README clearly explain the problem and solution?
  - Correctness: does the application do what was asked? If there is anything missing, does the README explain why it is missing?
  - Code quality: is the code simple, easy to understand, and maintainable?



Instructions for use:
---------------------

- Import wm001.java into favorite Java IDE into 'src' folder (using 1.8.0_60)
- place chromedriver.exe into 'browser' folder (at same level as 'src')
             I used chromedriver_win32 on 64-bit processor
- Ensure 'External Libraries' includes
   - junit-4.12.jar
   - hamcrest-cor-1.3.jar
   - selenium-java-2.47.1-srcs (and corresponding libs)
- Ensure good internet connection ( > 50 Mbps)
- compile and run
- if not in production environment, create account porttest2015@gmail.com pw 654321



Motivation:
-----------

Why Java (IntelliJ Idea), Webdriver and Chrome Driver?
   These are the technologies I know best for web testing. 
   I started exploratory testing with Selenium, but it proved not very useful technically


Trade-offs:
- using wait times instead of different assertions for robustness
- using new desktop (needed Java install) instead of my established but very slow laptop
- posting on Github with main file and instructions (still need to install fully on desktop for synch options)


What I may have left out:
- exception handling and different assertions (instead of wait times) for more defensive code
- possibly should have asked for test requirements (i.e. what should be used to confirm success)


What I would do differently if I spent more time on the project:
- put each search term into separate test for more resolution on results (requires restructuring)
- call methods for search 
- "add one item to cart" could be separate test, too
- DDT and config-type file for input data, expected and actual results
- no hardwired Username and Password
- make tests more robust with exception handling



Rough Chronology and Background:
--------------------------------

- Test problem 1, since more familiar with web testing, though very interested in mobile
- Start with Selenium then optimize and modify as required in Java (IntelliJ Idea CE 14(.1.4),java version 1.8.0_40  and or 1.8.0_60) 
   - Selenium really mainly helped for exploratory testing, since on different browser than final test
   - since I got new desktop, I installed java and IntelliJ Idea and gained speed for delay of getting it all installed  
- Created account porttest2015@gmail.com pw 654321


Different search areas have different expected results  {original submission}
------------------------------------------------------
  
- tv:     assert title and check that first product has tv in link test { was "New Samsung TVs" }
        need to click 2x on top item before being able to click add to cart (in staying in top area)
        but if scrolling down, one click to get to screen with "add to cart" and 
        Then need to know what I added to cart to be able to assert it in cart
        many divs down to get a title (description) even more to get a price

- socks:  assert with word "socks" on page title and check that first product has "socks" in link test  
        {Was "Women's Socks" in specific xpath location}
        seems to be simplest lay out, will use 'Socks' for adding item for that reason

- dvd:    assert with word "dvd" on page title and check that first product has "dvd" in link test 
          {was blank, Movies & TV (assert?)
        under movies & tv lots of options and sections
        blank dvds, vertical list   h4 title heading still variable for cart  (2 clicks to "add to cart") }

- toys:   differs in layout, assert with word "toys" on page title and check that comment on top of page includes "toys" 
        { below one large area, ribbon of items; 
        Assert by age or "Shop by gender & age" keeps alternating at same xpath location with "Top Characters" 
        Also, even if searching for 'toys', the page title becomes 'Toys' (upper case T, other search terms do not change)
        also changes Search Bar 'Origin' to 'toys' (other search terms seem to stay neutrally at 'All') }

- iPhone: bar of selection groups on top  (two clicks to "add to cart"-button) {no change from previous submission}
        list of phones (one click to "add to cart"-button)
        page seems more complex than others (-> add different item, even if repeating after searching for all terms)


Design:
-------

- Once at beginning: open window go to Walmart and log in 

- Main test:
1) search for search terms given in spec (one after another; confirm title and product/comment/link on page)
2) assert that page loaded
3) id an item to put into shopping cart (from socks)
4) put item in the cart
5) open shopping cart
6) check what is in shopping cart (assert originally planned)
7) ensure nothing else in the cart  (1 item? and expected product (by id))
8) remove item  with button 

- Once at the end: log out and quit chrome driver


