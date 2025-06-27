import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.cdimascio.dotenv.Dotenv;

public class XFolloing{

    public void execute() {
        /*
        * 設定
        */
        Dotenv dotenv = Dotenv.load(); // .envファイルを読み込む

        final String URL = "https://x.com/i/flow/login"; // XのログインページのURL
        final String USER_NAME = dotenv.get("USER_NAME"); // .envファイルから取得したXのユーザー名
        final String PASSWORD = dotenv.get("USER_PASSWORD"); // .envファイルから取得したXのパスワード

        // --- WebDriverのセットアップ ---
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        System.out.println("処理を開始します。");

        try {
            // --- ログイン処理 ---
            driver.get(URL);
            LoginPage loginPage = new LoginPage(driver);
            // loginメソッドは成功するとUserPageオブジェクトを返す
            UserPage userPage = loginPage.login(USER_NAME, PASSWORD);

            // --- ログイン後の処理 ---
            userPage.doSomethingAfterLogin(driver, USER_NAME); // 例：タイムラインの最初のツイートを取得

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
