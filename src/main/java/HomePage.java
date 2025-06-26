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
    public void doSomethingAfterLogin() {
        System.out.println("ホームページのタイムラインが表示されるのを待っています...");
        // タイムラインの最初のツイートが表示されるまで待機
        WebElement firstTweet = wait.until(ExpectedConditions.visibilityOfElementLocated(timelineArticle));

        System.out.println("タイムラインの最初のツイートが取得できました。");
        System.out.println("ツイート内容: " + firstTweet.getText().substring(0, 50) + "..."); // 長すぎるので最初の50文字だけ表示
    }
}