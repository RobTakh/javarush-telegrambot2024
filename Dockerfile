FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test.test_javarush_robert_bot
ENV BOT_TOKEN=6710049391:AAHKjiOsLMaXnKO4GiSJRPBRAI6I7TisIdE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]