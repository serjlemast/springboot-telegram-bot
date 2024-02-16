# Telegram bot

1. register new app in https://my.telegram.org/auth and find oauth token
2. create new bot and get bot username
3. create .env file with properties:

```
TELEGRAM.BOT.USERNAME=bot+usernamr
TELEGRAM.BOT.TOKEN=app_token
```

### Gradle Versions Plugin

Displays a report of the project dependencies that are up-to-date, exceed the latest version found, have upgrades, or
failed to be resolved

info: https://github.com/ben-manes/gradle-versions-plugin

command:

```
gradle dependencyUpdates
```