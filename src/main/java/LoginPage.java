import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ページ上の要素 (X.comのログインフローに合わせて変更)
    private By usernameInput = By.cssSelector("//*[@id=\"layers\"]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/div[4]/label/div/div[2]/div/input");
    private By nextButton = By.xpath("//*[@id=\"layers\"]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/button[2]/div");
    private By passwordInput = By.cssSelector("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[3]/div/label/div/div[2]/div[1]/input");
    private By nextLoginButton = By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/button");
    private By twoFactor = By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/label/div/div[2]/div/input")
    private By twoFactorButton = By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div/button/div")

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 待機時間を少し長めに設定
    }

    /**
     * ログインの一連の操作をまとめたメソッド
     * 
     * @param username ユーザー名
     * @param password パスワード
     * @return ログイン成功後に表示されるHomePageのインスタンス
     */
    public HomePage login(String username, String password) {
        // ユーザー名入力
        System.out.println("ユーザー名入力フィールドを待機しています...");
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        userField.sendKeys(username);

        // 「次へ」ボタンをクリック
        System.out.println("「次へ」ボタンをクリックします...");
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

        // パスワード入力
        System.out.println("パスワード入力フィールドを待機しています...");
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passField.sendKeys(password);

        // 「ログイン」ボタンをクリック
        System.out.println("「次へ」ボタンをクリックします...");
        wait.until(ExpectedConditions.elementToBeClickable(nextLoginButton)).click();

        // ログインが完了し、ホームページに遷移したことを前提に
        // HomePageのインスタンスを生成して返す
        return new HomePage(driver);
    }
}
