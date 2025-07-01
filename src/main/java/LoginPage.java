import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Scanner;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ページ上の要素 (X.comのログインフローに合わせて変更)
    private By usernameInput = By.xpath("//*[@id=\"layers\"]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/div[4]/label/div/div[2]/div/input");
    private By nextButton = By.xpath("//*[@id=\"layers\"]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/button[2]/div");
    private By userEmail = By.xpath("//*[@id=\"react-root\"]/div/div/div/main/div/div/div/div[2]/div[2]/div[1]/div/div/div[2]/label/div/div[2]/div/input");
    private By nextUserEmail = By.xpath("//*[@id=\"react-root\"]/div/div/div/main/div/div/div/div[2]/div[2]/div[2]/div/div/div/div/button/div");
    private By passwordInput = By.xpath("//input[@type='password']");
    private By nextLoginButton = By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/button");
    private By twoFactor = By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/label/div/div[2]/div/input");
    private By twoFactorButton = By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div/button/div");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 待機時間を少し長めに設定
    }

    /**
     * ログインの一連の操作をまとめたメソッド
     * 
     * @param username ユーザー名
     * @param password パスワード
     * @return ログイン成功後に表示されるUserPageのインスタンス
     */
    public UserPage login(String username, String password) {

        Scanner scanner = new Scanner(System.in);

        // ユーザー名入力
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        userField.sendKeys(username);
        System.out.printf("入力ユーザ名：%s\n", username);

        // 「次へ」ボタンをクリック
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

        // パスワード入力
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passField.sendKeys(password);
        System.out.printf("入力パスワード：%s\n", password);

        // 「ログイン」ボタンをクリック
        wait.until(ExpectedConditions.elementToBeClickable(nextLoginButton)).click();

        // 2段階認証が必要な場合の処理
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(twoFactor)).isDisplayed()) {
            while( !wait.until(ExpectedConditions.visibilityOfElementLocated(twoFactor)).isDisplayed()) {
                // 2段階認証のフィールドが表示されるまで待機

                System.out.println("2段階認証コードを入力してください:");
                String twoFactorCode = scanner.nextLine();
                WebElement twoFactorField = wait.until(ExpectedConditions.visibilityOfElementLocated(twoFactor));
                twoFactorField.sendKeys(twoFactorCode);
                wait.until(ExpectedConditions.elementToBeClickable(twoFactorButton)).click();
            }
        }

        // ログインが完了し、ホームページに遷移したことを前提に
        // UserPageのインスタンスを生成して返す
        scanner.close();
        System.out.println("ログインが完了しました");
        return new UserPage(driver);
    }
}
