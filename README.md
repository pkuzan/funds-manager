# Funds Manager

### Run Locally
To run the application locally, set a Spring Profile = local.

```
-Dspring.profiles.active=local
```

Run the following class in the app module in your IDE:

```
hellocloud.FundsManagerApplication
``` 

The application will start on port 8443.

To call the Swagger API enter the following URL into a browser.
As a self-signed cert has been used, the warning will have to be ignored.
```
https://localhost:8443/docApi/swagger-ui.html
```

### Run on AWS
Perform a mvn install on the root project this will build modules.

The Maven assembly plugin will construct a CodeDeploy zip file in the target dir of
the app module named funds-manager-angular-app.zip. This zip file contains startup scripts and appspec,yaml.

Deploy the zip file by copying to an S3 bucket and invloking CodeDeploy from the AWS Console.

Remember to uncomment the file appender in logback.xml.