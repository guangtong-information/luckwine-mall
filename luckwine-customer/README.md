# luckwine-customer (服务-客户中心)

---
### 1. 环境
* Java 8 
* Maven
* Mysql 5.7 
* Redis
* Zookeeper

---
### 2. 打jar包
```
mvn clean install -DskipTests
```
---
### 3. docker
```
docker build -t luckwine-customer .
docker run --name luckwine-customer -v /data/docker/luckwine-customer:/data -p 8002:8002 -e JAVA_OPTIONS="-Duser.timezone=GMT+8 -Xms2048m -Xmx4096m -Dspring.profiles.active=prod" -d luckwine-customer
```
