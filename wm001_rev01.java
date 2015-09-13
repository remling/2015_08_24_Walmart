import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Roswitha on 8/24 2015 updated 9/12/2015.
 */
public class wm001_rev01 {

    // globally applicable
    String baseUrl = "http://www.walmart.com";     // remove http:// to meet spec
    String tempUrl;
    private WebDriver driver;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "browsers\\chromedriver.exe");
        driver = new ChromeDriver();
        tempUrl = baseUrl + "/account/login";

        driver.get(tempUrl);
        assertEquals("Walmart", driver.getTitle());
        Thread.sleep(2000);  // enables following test visually

        WebElement loginBox = driver.findElement(By.cssSelector("input[id=\"login-username\"]"));
        loginBox.sendKeys("porttest2015@gmail.com");
        WebElement pwdBox = driver.findElement(By.cssSelector("input[id=\"login-password\"]"));
        pwdBox.sendKeys("654321");
        pwdBox.submit();
        Thread.sleep(500);
        assertEquals("Walmart", driver.getTitle());
    }

    // Search for 5 terms and add one item to cart (socks seemed most straight forward)
    @Test
    public void testSearchManyAdd1()  throws InterruptedException {
        String [] searchTerms = {"tv",  "socks",  "dvd", "toys" , "iPhone", "Socks"};
        WebElement searchBox, firstProdLink;
        String actProd, firstProd, title;
        String toyPath = "html/body/div[1]/section/section[4]/div/div/div[3]/div[1]/div[1]";

        for ( String term : searchTerms) {
            Thread.sleep(500);
            searchBox = driver.findElement(By.cssSelector("input[type=\"text\"]"));
            searchBox.clear();
            Thread.sleep(500);
            searchBox.sendKeys(term);
            searchBox.submit();
            Thread.sleep(1000);

            // checking title includes search term
            title = driver.getTitle();
            assertThat(title.toLowerCase(), containsString(term.toLowerCase()));

            // confirm one appropriate item or link of product for search term
            if (term.equals("tv") || term.equals("socks") || term.equals("dvd") ) {
                firstProd = driver.findElement(By.cssSelector("a[class=\"js-product-title\"]")).getText();
                Thread.sleep(500);
                firstProdLink = driver.findElement(By.linkText(firstProd));
                actProd = firstProdLink.getAttribute("href");
                assertThat(actProd.toLowerCase(), containsString(term));
            }
            else if (term.equals("toys")) {    // toys site is different and seemed to alternate 8/23/15
                String toyActual = driver.findElement(By.xpath(toyPath)).getText();
                assertThat(toyActual.toLowerCase(), containsString(term));
                // toys change search bar so reset to walmart.com before moving on
                // (this may be a bug or intentional)
                Thread.sleep(500);
                driver.get(baseUrl);
            }
            else if (term.equals("iPhone")) {        // iPhones are different, too, will fail if nothing to click
                driver.findElement(By.linkText("iphone 6 plus")).click();
                Thread.sleep(500);
                driver.get(baseUrl);
            }
            // add one item to cart and delete it again (leave not trace)
            else if (term.equals("Socks")) {
                // determine expected result
                String firstSocks = driver.findElement(By.cssSelector("a[class=\"js-product-title\"]")).getText();
                Thread.sleep(500);
                WebElement firstSocksLink = driver.findElement(By.linkText(firstSocks));
                String linkLoc = firstSocksLink.getAttribute("href");
                String firstSockNum = linkLoc.substring((linkLoc.length() - 8), (linkLoc.length()));
                Thread.sleep(500);

                // select product and add to cart
                firstSocksLink.click();
                Thread.sleep(5000);
                driver.findElement(By.cssSelector("button[id=\"WMItemAddToCartBtn\"]")).click();
                Thread.sleep(5000);

                // review cart
                tempUrl = baseUrl + "/cart";
                driver.get(tempUrl);
                Thread.sleep(5000);

                // check Number of items in cart
                String cartItems = driver.findElement(By.cssSelector("h3[class=\"cart-list-title\"]")).getText();
                Thread.sleep(500);
                String itemsInCart = cartItems.substring(10, 13);
                assertEquals(" 1 ", itemsInCart);
                Thread.sleep(5000);

                // compare actual and expected Product ID
                WebElement cartSocksLink = driver.findElement(By.linkText(firstSocks));
                String cartLinkLoc = cartSocksLink.getAttribute("href");
                String actSockNum = cartLinkLoc.substring((cartLinkLoc.length() - 8), (cartLinkLoc.length()));
                Thread.sleep(500);
                assertEquals(firstSockNum, actSockNum);

                // remove item to leave no trace
                driver.findElement(By.cssSelector("button[id=\"CartRemItemBtn\"]")).click();
                Thread.sleep(500);
            }
            else {
                System.out.println("Search term not valid  ... ");
                assertEquals(0,1);    // test will fail if it comes here
            }
        }
    }

    @After
    public void tearDown() throws InterruptedException  {
        tempUrl = baseUrl + "/account/logout";
        driver.get(tempUrl);
        assertEquals("Walmart", driver.getTitle());
        driver.quit();
    }
}
