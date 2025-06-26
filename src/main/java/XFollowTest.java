import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class XFollowTest {

    public void execute() {
        /*
         * 設定
         */
        final String URL = "https://x.com/login"; // XのログインページのURL
        final String USER_NAME = "@Kurosuke_GTx_77"; // Xのユーザー名
        final String PASSWORD = "your_password"; // Xのパスワード

        // --- WebDriverのセットアップ ---
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        System.out.println("テストを開始します。");

        try {
            // --- ログイン処理 ---
            driver.get(URL);
            LoginPage loginPage = new LoginPage(driver);
            // loginメソッドは成功するとHomePageオブジェクトを返す
            HomePage homePage = loginPage.login(USER_NAME, PASSWORD);
            System.out.println("ログイン処理が完了しました。");

            // --- ログイン後の処理 ---
            System.out.println("ログイン後の操作を開始します。");
            homePage.doSomethingAfterLogin(); // 例：タイムラインの最初のツイートを取得

            System.out.println("ログイン後の操作が完了しました。");

        } catch (Exception e) {
            System.err.println("テスト中にエラーが発生しました。");
            e.printStackTrace();
        } finally {
            // --- 終了処理 ---
            if (driver != null) {
                System.out.println("ブラウザを終了します。");
                driver.quit();
            }
            System.out.println("テストを終了します。");
        }
    }
}
