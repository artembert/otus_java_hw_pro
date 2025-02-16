# HW 10 - JPQL

## How to run
- Run a local database in docker
- Run web-server
- Open a web-browser and go to the `http://localhost:8080/`

### How to run a local database

- Run docker desktop

```shell
cd ./docker
./runDb.src
```

if you faced the error `zsh: permission denied: ./runDb.src`, you need to give the permission to execute the file:

```shell
chmod +x ./runDb.src
```

## How to run a Web Server
- open the project in your IDE
- run the `main` method in the `com.homework.webserver.WebServerWithFilterBasedSecurityDemo` class
- open the browser and go to the `http://localhost:8080/`