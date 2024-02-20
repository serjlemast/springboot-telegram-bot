### Project setup

create .env file with properties:

```
TELEGRAM.BOT.USERNAME=bot+username
TELEGRAM.BOT.TOKEN=app_token
MONGODB_DATABASE=db_name
MONGODB_URI=uri_to_mongo_db
```

### API documentation

Project uses OpenAPI (link: https://springdoc.org/) that will describe the API
of REST endpoints.

Configuration api documentation endpoint in *.yaml file

```
springdoc:
  swagger-ui:
    path: /api/documentation
```

API documentation endpoint:  <br>

* http://{URL}:{port}/api/documentation

### Telegram API

1. Register new app in https://my.telegram.org/auth
2. After registration save telegram_token and register a new bot
3. add bot username and telegram_token to .env file

#### How to create Telegram Bot and send messages to your group

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
failed to be resolved, info: https://github.com/ben-manes/gradle-versions-plugin

command:

```
gradle dependencyUpdates
```

### Spring Actuator

* [Spring Boot Actuator: Health check, Auditing, Metrics gathering and Monitoring](https://www.callicoder.com/spring-boot-actuator/#:~:text=You%20can%20enable%20or%20disable,the%20identifier%20for%20the%20endpoint)

Endpoint ID Description:

* info - Displays information about your application.
* health - Displays your application’s health status.

Enable info and health endpoint in *.yaml file

```
management:
  endpoints:
    web:
      exposure:
        include: health,info
```

Actuator endpoints:

* /actuator
* /actuator/health
* /actuator/info

### Atlas cloud MongoDb

* Cloud db: https://cloud.mongodb.com/
* [Working with Spring Data Repositories](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#repositories)

##### Transactional support in Reactive style of Mongo DB & Spring Boot:

* [Transactional Mongo DB & Spring Boot](https://stackoverflow.com/questions/56360094/calling-methods-in-two-different-reactivemongorepositorys-in-a-transaction-usin/61676211#61676211)

##### How to set up database:

1. Login to Mongo cluster
    * https://account.mongodb.com/account/login


2. Get or set new admin credentials:<br>
    * login
    * password
    * and set permission - Atlas admin


3. Create a Shared Cluster
    * FREE >> Sandbox (Shared RAM, 512 MB Storage)


4. Go to Quick Start Security

    * Set up a username and a new password


5. Set up network access for database:

    * Network Access tab >> Edit IP Access List Entry >>  Allow Access from Anywhere


6. Database Deployments Options <br>
   Go to: Database >> Database Deployments >> Connect button <br>
    * Select your driver and version: Java and latest version of driver <br>
    * Add your connection string into your application code <br>

   example: <br>
   <code>
   mongodb+srv://<username>:<password>@cluster0.8seexos.mongodb.net/?retryWrites=true&w=majority
   </code>


7. Create new DATABASES
    * Go to: DATABASE >> Cluster >> Collections >> Add My Own Data
    * Set up Database name (example: sampleDB ) and collection name (example: collectionDB)


8. Set up required ENV Variables for application:
    * MONGODB_DATABASE - Database name from DATABASE >> Cluster >> Collections  (example: sampleDB )
    * MONGODB_URI - mongodb+srv://<username>:<password>@cluster0.8seexos.mongodb.net/?retryWrites=true&w=majority

### Java code style

project: google-java-format,
link: https://github.com/google/google-java-format/blob/master/README.md#intellij-jre-config
