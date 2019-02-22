# LuckWine Mall 

### 1、开发阶段，jvm调优
```
 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
```
### 2. 打包
```
mvn clean install -DskipTests
```