import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

public class Cookies {
    
    WebDriver driver = new ChromeDriver();

    public void addCookie(String url) {
        driver.get(url);
        // Add cookie into current browser context
        Set<Cookie> cookies = driver.manage().getCookies();
        driver.manage().addCookie(new Cookie(cookies.getName(), cookies.getValue()));
        driver.quit();
    }

    public void getNamedCookie(String url) {

        driver.get(url);
        // Add cookie into current browser context
        driver.manage().addCookie(new Cookie("foo", "bar"));
        // Get cookie details with named cookie 'foo'
        Cookie cookie = driver.manage().getCookieNamed("foo");
        Assertions.assertEquals(cookie.getValue(), "bar");

        driver.quit();
    }
}
