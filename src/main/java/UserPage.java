import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class UserPage {
    private WebDriver driver;

    private By retweetMark = By.xpath("//*[@id=\"id__jqbsnd327k8\"]")

    public UserPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Perform actions after login.
     * This is a stub method. Implement actual logic as needed.
     */
    public void doSomethingAfterLogin(WebDriver driver, String userName) {
        String userNameNonAtMark = userName.startsWith("@") ? userName.substring(1) : userName; // @を除去
        driver.get("https://x.com/home/" + userNameNonAtMark); // ユーザーページにアクセス


        ([1].innerText === 'You retweeted');
    }
}
