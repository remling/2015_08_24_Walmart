# 2015_08_24_Walmart
Jason's Homework

for wm001.java

Roswitha Remling
r.remling@att.net
650-796-9689


Test Problem # 1:
=================

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
=====================

- Import wm001.java into favorite Java IDE into 'src' folder (using 1.8.0_60)
- place chromedriver.exe into 'browser' folder (at same level as 'src')
             I used chromedriver_win32 on 64-bit processor
- Ensure 'External Libraries' includes
   - junit-4.12.jar
   - hamcrest-cor-1.3.jar
   - selenium-java-2.47.1-srcs (and corresponding libs)
- Ensure good internet connection
- compile and run



Motivation:
=========== 

Why Java (IntelliJ Idea), Webdriver and Chrome Driver?
   These are the technologies I know best for web testing. 
   I started exploratory testing with Selenium, but it proved not very useful technically

Trade-offs:
- using wait times instead of different assertions for robustness
- using new desktop (needed Jave install) instead of my established but very slow laptop

What I may have left out:
- exception handling and different assertions (instead of wait times)


What I would do differently if I spent more time on the project:
- put each search term into separate test for more resolution on results (requires resturcturing)
- call methods for search 
- "add one item to cart" could be separate test, too
- DDT and config-type file for input data, expected and actual results



Rough Chronology and Background:
--------------------------------

- Test problem 1, since more familiar with web testing, though very interested in mobile
- Start with Selenium then optimize and modify as required in Java (IntelliJ Idea CE 14(.1.4),java version 1.8.0_40  and or 1.8.0_60)
   - since I got new desktop, much faster than my little old completely maxed out laptop I installed java and IntelliJ Idea there, gained speed for delay of getting it all installed  (I hope a good trade-off in the long run)
   - of course I'll need a lot of externak libraries, too will come across those
   - 16:09 Java runs Hello World is done

- Create account porttest2015@gmail.com pw 654321

- log in and log out
   -- had a lot of trouble to log in and to search until sendkeys return provided a way around it
   -- log out is mainly for testing clarity, no UI testing seems required

Different search areas have different expected results
------------------------------------------------------
  
tv:     assert title  and / or  "New Samsung TVs"
        need to click 2x on top item before being able to click add to cart (in staying in top area)
        but if scrolling down, one click to get to screen with "add to cart" and 
        Then need to know what I added to cart to be able to assert it in cart
        many divs down to get a title (description) even more to get a price

socks:  assert with word "socks" on page title or "Women's Socks" in specific xpath location
        seems to be simplest lay out, will use 'Socks' for adding item for that reason

dvd:    blank, Movies&TV (assert?)   and lots of players
        under movies & tv lots of options and sections
        blank dvds, vertical list   h4 title heading still variable for cart  (2 clicks to "add to cart")

toys:   below one large area, ribbon of items
        Assert by age h2 "Shop by gender & age" keeps alternating at same xpath location with "Top Characters"
        Also, even if searching for 'toys', the page title becomes 'Toys' (upper case T, other search terms do not change)
        also changes Search Bar 'Origin' to 'toys' (other search terms seem to stay neutrally at 'All')

iPhone: bar of selection groups on top  (two clicks to "add to cart"-button)
        list of phones (one click to "add to cart"-button)
        page seems more complex than others (-> add different item, even if repeating after searching for all terms)


Design:
-------

Once at beginning: open window go to walmart and log in 

Main test:
1. search for search terms given in spec (one after another; confirm title and product
2. assert that page loaded
3. id an item to put into shopping cart (from socks)
4. put item in the cart
5. open shopping cart
6. check what is in shopping cart (assert originally planned)
7. ensure nothing else in the cart  (1 item? and expected product (by id))
8. remove item  <button..> Remove

Once at the end: log out and quit chrome driver






