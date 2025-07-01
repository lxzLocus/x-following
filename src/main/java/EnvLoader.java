import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
  private final String username;
  private final String password;

  public EnvLoader() {
    Dotenv dotenv = Dotenv.configure()
            .directory("/app/")
            .filename(".env") // カスタムファイル名を指定
            .load();
    this.username = dotenv.get("USER_NAME");
    this.password = dotenv.get("USER_PASSWORD");
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
