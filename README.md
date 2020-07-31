# LuckWine Mall 

### 1、技术栈
* Dubbo 2.7.7
* Redis 6.0
* Elasticsearch 7.7.0
* Seata 1.3.0

### 2. 开发环境
* Java 8 
* Maven
* Mysql 5.7 
* Redis
* Zookeeper
* Elasticsearch 7.7.0
* nacos 1.1.4
* seata 1.3.0

```
 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
```
### 3. 打包
```
mvn clean install -DskipTests
```

### 4. 避免扫描node_modules,dist
```
右键Make Directory is --> Excluede 
```

### 5. 欢迎加群

QQ群：806864360
