import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserPage {
    private WebDriver driver;

    final private String timelineArticleXpath = "//*[@id='react-root']/div/div/div[2]/main/div/div/div/div/div/div[3]/div/div/section/div/div/div[1]";
    private By tweetsArticleDiv = By.xpath(timelineArticleXpath); // タイムラインの記事を含むdiv
    
    private By retweetMark = By.xpath("//*[@id=\"id__jqbsnd327k8\"]");

    public UserPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Perform actions after login.
     * This is a stub method. Implement actual logic as needed.
     */
    public void doSomethingAfterLogin(WebDriver driver, String userName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 待機時間を少し長めに設定

        String userNameNonAtMark = userName.startsWith("@") ? userName.substring(1) : userName; // @を除去
        driver.get("https://x.com/home/" + userNameNonAtMark); // ユーザーページにアクセス
        wait.until(ExpectedConditions.visibilityOfElementLocated(tweetsArticleDiv)); // タイムラインの記事

        // Tweetsカラム
        // *[@id="react-root"]/div/div/div[2]/main/div/div/div/div/div/div[3]/div/div/section/div/div

        // タイムラインの最初の記事を取得
        // *[@id="react-root"]/div/div/div[2]/main/div/div/div/div/div/div[3]/div/div/section/div/div/div[1]

        // *[@id="react-root"]/div/div/div[2]/main/div/div/div/div/div/div[3]/div/div/section/div/div/div[3]/div/div/article/div/div/div[1]/div/div/div/div/div[2]/div/div/div
        //二つに分かれる
        // div/div/div/div/div[2]/div/div/div


        

        // *[@id="react-root"]/div/div/div[2]/main/div/div/div/div/div/div[3]/div/div/section/div/div/div[3]/div/div/article/div/div/div[2]
        WebElement firstArticle = driver.findElement(tweetsArticleDiv);
    }
}
