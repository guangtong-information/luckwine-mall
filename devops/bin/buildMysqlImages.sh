#!/usr/bin/env bash
# 需要安装docker环境
# 构建包含应用数据结构的mysql image的脚本

#有DATABASE的应用
db_json=(
'luckwine-acct'
'luckwine-bgw'
'luckwine-customer'
'luckwine-goods'
'luckwine-marketing'
'luckwine-oss-service'
'luckwine-pgw'
'luckwine-synthesize'
'luckwine-trade'
)
log_dir="";

#切到项目根路径
function gotodir() {
    filepath=$(cd "$(dirname "$0")"; pwd)
    cd $filepath/../../;
    rootpath=$(pwd)
    log_dir=$rootpath"/devops/logs";
    mkdir -p $log_dir;
}

#目录遍历
function traversing() {
    mkdir -p target/mysql;
    cp -r devops/docker-file/mysql/*  target/mysql/;
    for file in ./*
    do
        filename=${file/'./'/}
        # 匹配有DATABASE子项目
        for element in ${db_json[@]}
        do
            if [ "$filename" == "$element" ]; then
                echo $filename
                copySqlFile $filename
            fi;
        done;
    done
}

function copySqlFile() {
    filename="$1"
    cp -r $filename"/DATABASE/"$filename".sql"  target/mysql/;
}

function build() {
    cd  target/mysql/;
    exec docker build -t luckwine-mysql .>$log_dir"/luckwine-mysql.log"&
    gotodir;
}

gotodir
traversing
build