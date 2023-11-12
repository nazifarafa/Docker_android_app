FROM openjdk:8-jdk

WORKDIR /app

# Install Android SDK
RUN apt-get --quiet update --yes \
    && apt-get --quiet install --yes wget tar unzip lib32stdc++6 lib32z1 \
    && wget --quiet --output-document=android-sdk.zip https://dl.google.com/android/repository/commandlinetools-linux-7302050_latest.zip \
    && unzip -d android-sdk-linux android-sdk.zip \
    && rm android-sdk.zip

ENV ANDROID_HOME /app/android-sdk-linux
ENV PATH $PATH:$ANDROID_HOME/cmdline-tools/tools/bin

# Install required SDK components
RUN yes | sdkmanager --licenses \
    && sdkmanager "platform-tools" "platforms;android-30" "build-tools;30.0.2"

COPY . .

# Build the Android app
RUN ./gradlew assembleDebug
