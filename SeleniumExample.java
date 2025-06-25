import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumExample {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        // ヘッドレスモードを有効にする
        options.addArguments("--headless");
        // Dockerコンテナ内で推奨されるオプション
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080"); // 必要に応じてウィンドウサイズを指定

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.google.com");
            System.out.println("Page title is: " + driver.getTitle());
            // ここにWeb操作のコードを記述
        } finally {
            driver.quit();
        }
    }
}
