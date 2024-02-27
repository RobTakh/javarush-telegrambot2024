FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test.test_javarush_robert_bot
ENV BOT_TOKEN=6710049391:AAHKjiOsLMaXnKO4GiSJRPBRAI6I7TisIdE
ENV BOT_DB_USERNAME=robert
ENV BOT_DB_PASSWORD=qwerty
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-DSpring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-DSpring.datasource.username=${BOT_DB_USERNAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]