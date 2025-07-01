import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

public class Cookies {

    WebDriver driver = new ChromeDriver();


    public void addCookie(String url) {
        driver.get(url);
        // すべてのCookieを表示
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("Cookie: " + cookie.getName() + " = " + cookie.getValue());
        }
        // サンプルで1つCookieを追加
        driver.manage().addCookie(new Cookie("sample", "value"));
        driver.quit();
    }

    public void getAuthCookies(String url) {
        driver.get(url);

        String[] cookieNames = {"authorization", "auth_token", "ct0"};
        for (String name : cookieNames) {
            Cookie cookie = driver.manage().getCookieNamed(name);
            if (cookie != null) {
                System.out.println("取得したCookie: " + cookie.getName() + " = " + cookie.getValue());
            } else {
                System.out.println("Cookie '" + name + "' は見つかりませんでした。");
            }
        }
        driver.quit();
    }

}
