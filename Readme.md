## Telegram bot

### Project setup

* create .env file with properties:

```
TELEGRAM.BOT.USERNAME=bot+username
TELEGRAM.BOT.TOKEN=app_token
```

### Telegram API

1. Register new app in https://my.telegram.org/auth
2. After registration save telegram_token and register a new bot
3. add bot username and telegram_token to .env file

### How to create Telegram Bot and send messages to your group

1) Create Telegram bot:

   Search for user @BotFather in Telegram app. Type /help in BotFather chat and wait for the reply. Type in the chat:

   `/newbot`

   or select /newbot command from Help text. Answer few setup questions:

    - Name of your bot? Write anything you like, that info will be shown in contact details. For example:

   `Dead Parrot`

    - Username for your bot? Must have _bot at the end, use only Latin characters,
      numbers or underscore sign, for example:

   `deadparrot_bot`

   BotFather will give you HTTP API token, remember it and keep **SECRET**!
   Example:

   `1234567890:xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`

2) Create new Telegram group in web or mobile application and add the new bot in your group.

   https://web.telegram.org/

3) Send dummy message into your group, replace botname_bot with actual bot name:

   `/my_id @botname_bot`

4) Find Chat ID of your group:
   Type this URL in the browser - change {HTTP_API_TOKEN} to actual value

   `https://api.telegram.org/bot{HTTP_API_TOKEN}/getUpdates`

   From JSON result, get "chat_id" of last message in your group,
   including minus sign in front of it, that's Group Chat ID.

   If JSON is empty, repeat steps 3 and 4.

5) That's it, now use the bot. For all available methods, read doc from:

   https://telegram-bot-sdk.readme.io/reference

   Example for Send Text Message method:

   `https://api.telegram.org/bot{HTTP_API_TOKEN}/sendMessage?chat_id={CHAT_ID}&text={MESSAGE_TEXT}`

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