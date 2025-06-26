# =================================================================
# ステージ1: ビルド環境 (Mavenを使ってJavaコードをコンパイル)
# =================================================================
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /build

ARG CHROME_VERSION="126.0.6478.126"
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    wget unzip ca-certificates jq \
    libglib2.0-0 libnss3 libfontconfig1 libatk-bridge2.0-0 libgtk-3-0 libgbm1 libasound2t64 \
    && rm -rf /var/lib/apt/lists/*
RUN CHROME_URL=$(curl -s "https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json" | jq -r ".versions[] | select(.version == \"${CHROME_VERSION}\") | .downloads.chrome[0].url") && \
    wget -q "${CHROME_URL}" -O chrome-linux64.zip && unzip chrome-linux64.zip && mv chrome-linux64 /opt/chrome && rm chrome-linux64.zip
RUN CHROMEDRIVER_URL=$(curl -s "https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json" | jq -r ".versions[] | select(.version == \"${CHROME_VERSION}\") | .downloads.chromedriver[0].url") && \
    wget -q "${CHROMEDRIVER_URL}" -O chromedriver-linux64.zip && unzip chromedriver-linux64.zip && mv chromedriver-linux64/chromedriver /usr/local/bin/chromedriver && rm chromedriver-linux64.zip && rm -rf chromedriver-linux64
ENV PATH="/opt/chrome:${PATH}"

# pom.xmlを先にコピーして、依存関係ライブラリをダウンロードする
# ソースコードが変わっても、pom.xmlが変わらなければキャッシュが効きビルドが高速になる
COPY pom.xml .
RUN mvn dependency:go-offline

# ソースコードをコピー
COPY src ./src

# Mavenでアプリケーションをコンパイルし、単一のJARファイルを作成
RUN mvn package


# =================================================================
# ステージ2: 実行環境 (実際にアプリケーションを動かす環境)
# =================================================================
FROM eclipse-temurin:17-jdk-jammy

ARG CHROME_VERSION="126.0.6478.126"

# 必要なライブラリとChrome/ChromeDriverをインストール
# (この部分は以前のDockerfileと同じ)
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    wget \
    unzip \
    ca-certificates \
    jq \
    libglib2.0-0 libnss3 libfontconfig1 libatk-bridge2.0-0 libgtk-3-0 libgbm1 libasound2t64 \
    && rm -rf /var/lib/apt/lists/*
RUN CHROME_URL=$(curl -s "https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json" | \
    jq -r ".versions[] | select(.version == \"${CHROME_VERSION}\") | .downloads.chrome[0].url") && \
    wget -q "${CHROME_URL}" -O chrome-linux64.zip && unzip chrome-linux64.zip && mv chrome-linux64 /opt/chrome && rm chrome-linux64.zip
RUN CHROMEDRIVER_URL=$(curl -s "https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json" | \
    jq -r ".versions[] | select(.version == \"${CHROME_VERSION}\") | .downloads.chromedriver[0].url") && \
    wget -q "${CHROMEDRIVER_URL}" -O chromedriver-linux64.zip && unzip chromedriver-linux64.zip && mv chromedriver-linux64/chromedriver /usr/local/bin/chromedriver && rm chromedriver-linux64.zip && rm -rf chromedriver-linux64

# PATHの設定
ENV PATH="/opt/chrome:${PATH}"

WORKDIR /app

# ビルドステージから、完成したJARファイルのみをコピー
COPY --from=builder /build/target/*-jar-with-dependencies.jar ./application.jar

# アプリケーションの実行
CMD ["java", "-jar", "application.jar"]
