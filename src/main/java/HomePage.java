import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ホームページの要素（例: タイムラインの最初の記事）
    private By timelineArticle = By.cssSelector("article[data-testid='tweet']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // ログイン後はページの読み込みに時間がかかる場合がある
    }

    /**
     * ログイン後に行う何らかの処理
     */
    public void doSomethingAfterLogin(WebDriver driver, String userName) {
        String userNameNonAtMark = userName.startsWith("@") ? userName.substring(1) : userName; // @を除去
        driver.get("https://x.com/home/" + userNameNonAtMark); //ユーザーページにアクセス
        wait.until(ExpectedConditions.visibilityOfElementLocated(timelineArticle)); // タイムラインの記事
        WebElement firstArticle = driver.findElement(timelineArticle);
        if (firstArticle != null) {
            System.out.println("最初のツイート: " + firstArticle.getText());
        } else {
            System.out.println("タイムラインにツイートが見つかりませんでした。");
        }
    }
}