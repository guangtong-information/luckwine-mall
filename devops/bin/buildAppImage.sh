#!/usr/bin/env bash
# 需要java、maven、docker环境
# 构建应用docker image的脚本
#$(date +%Y%m%d%H%M%S)

#java应用
java_json=(
'luckwine-acct'
'luckwine-bgw'
'luckwine-customer'
'luckwine-goods'
'luckwine-marketing'
'luckwine-oss-service'
'luckwine-pgw'
'luckwine-portal-service'
'luckwine-synthesize'
'luckwine-trade'
)
#nodejs应用
web_json=(
'luckwine-portal-web'
'luckwine-oss-web'
)
rootPath="";

#切到项目根路径
function gotodir() {
    filepath=$(cd "$(dirname "$0")"; pwd)
    cd $filepath"/../../";
    rootPath=$(pwd);
}

#项目编译
function build() {
    echo $(pwd)
    mvn clean install -DskipTests;
}

#目录遍历
function traversing() {
    for file in ./*
    do
        filename=${file/'./'/}
        # 匹配java子项目
        for element in ${java_json[@]}
        do
            if [ "$filename" == "$element" ]; then
                echo $filename
                buildJavaDockerImages $filename
            fi;
        done;
        # 匹配nodejs子项目
        for element in ${web_json[@]}
        do
            if [ "$filename" == "$element" ]; then
                echo $filename
                buildWebDockerImages $filename
            fi;
        done;
    done
}

# 构建java应用docker image
function buildJavaDockerImages() {
    filename="$1"
    cp -r devops/docker-file/java-app/*  $filename/target/;
    cd $rootPath"/"$filename/target;
    #exec docker build -t $filename . >$log_dir/$filename".log"&
    docker rmi -f $filename;
    docker build -t $filename .;
    cd $rootPath;
}

# 构建java应用docker image
function buildWebDockerImages() {
    filename="$1"
    cp -r devops/docker-file/nginx-app/*  $filename/target/;
    cp -r $filename/dist  $filename/target/;
    cd $rootPath"/"$filename/target;
    #exec docker build -t $filename . >$log_dir/$filename".log"&
    docker rmi -f $filename;
    docker build -t $filename .;
    cd $rootPath;
}


gotodir
build
traversing
