## Telegram bot

### Project setup

* create .env file with properties:

```
TELEGRAM.BOT.USERNAME=bot+username
TELEGRAM.BOT.TOKEN=app_token
MONGODB_DATABASE=db_name
MONGODB_URI=uri_to_mongo_db
```

### Telegram API

1. Register new app in https://my.telegram.org/auth
2. After registration save telegram_token and register a new bot
3. add bot username and telegram_token to .env file

### How to create Telegram Bot and send messages to your group

* Create Telegram bot: <br>
    1. Search for user @BotFather in Telegram app and type in the chat `/newbot` command <br>
    2. @BotFather will give you HTTP API token, remember it and
       keep `1234567890:xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx` <br><br>

* How to add bot to my group.<br>
    1. Create new group in web or mobile application
    2. Go to your group settings and pick “Add Members” option, then type @you_bot_name in search field. <br>
       link: https://telegram-bot.app/learning-centre/how-to-add-bot-to-my-group/

### Gradle Versions Plugin

Displays a report of the project dependencies that are up-to-date, exceed the latest version found, have upgrades, or
failed to be resolved

info: https://github.com/ben-manes/gradle-versions-plugin

command:

```
gradle dependencyUpdates
```

### Java code style

google-java-format <br>
link: https://github.com/google/google-java-format/blob/master/README.md#intellij-jre-config