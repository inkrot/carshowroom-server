# carshowroom-server

It's a server application for Practical task.

# Getting Started
####Download server sources:
```
git clone https://github.com/inkrot/carshowroom-server.git
```
####Build sources:
```
./gradlew build
```
**or**
```
gradle build
```
####Build docker image:
```
docker build -f Dockerfile -t com.mera.inkrot.carshowroom/server:latest .
```
####Pull MS SQL Server image:
```
docker pull mcr.microsoft.com/mssql/server:2017-latest
```
####If check images list in docker we will see:
- **com.mera.inkrot.carshowroom/server:latest**
- **openjdk**
- **mcr.microsoft.com/mssql/server:2017-latest**
# Run
####Create and run server with database in docker containers
```
docker-compose up
```
####Connect to database from bash
1. Run docker bash: ```sudo docker exec -it db "bash"```
2. Connect to SQL Server: ```/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P wkFl!16cQd```
####Create new database
1. Write SQL query: ```CREATE DATABASE carshowroom```
2. Execude query: ```GO```
3. For create tables and populate them use ```schema.sql``` and ```data.sql``` (run in IDEA or sqlcmd)
4. Quit sqlcmd: ```QUIT```
5. Exit from docker bash: ```exit```

####For restart containers
```
docker-compose restart
```

####Server is available check:
```
curl http://localhost:9000
```

#Clients
##### REST-Client: https://github.com/inkrot/carshowroom-restclient
##### SOAP-Client: https://github.com/inkrot/carshowroom-soapclient

# Diagram of database:
![alt text](https://raw.githubusercontent.com/inkrot/carshowroom-server/master/carshowroom-er.png)
