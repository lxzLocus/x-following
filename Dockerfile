# ベースイメージとしてJavaがインストールされたイメージを選択
FROM eclipse-temurin:17-jdk

# OSをアップデートし、必要なパッケージをインストール
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    # Chromeのインストールに必要なライブラリ
    libglib2.0-0 \
    libnss3 \
    libfontconfig1 \
    libxcb1 \
    && rm -rf /var/lib/apt/lists/*

# Install Chrome binary
RUN curl -O https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt-get install -yq --no-install-recommends ./google-chrome-stable_current_amd64.deb && \
    apt-get clean && rm google-chrome-stable_current_amd64.deb

# Install Chrome Driver (Get Latest Version) 
RUN curl -O https://chromedriver.storage.googleapis.com/$(curl -s https://chromedriver.storage.googleapis.com/LATEST_RELEASE)/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/local/bin/ && \
    rm chromedriver_linux64.zip

# アプリケーションのコードをコピー
WORKDIR /app
COPY . .

# Mavenを利用する場合（例）
# RUN mvn dependency:go-offline
# RUN mvn package

# アプリケーションの実行
#CMD ["java", "-jar", "your-application.jar"]
