#!/usr/bin/env bash
# 需要安装docker-compose
# curl -L https://github.com/docker/compose/releases/download/1.23.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
# chmod +x /usr/local/bin/docker-compose
#
log_dir="";
#切到项目根路径
function gotodir() {
    filepath=$(cd "$(dirname "$0")"; pwd)
    cd $filepath/../../;
    rootpath=$(pwd)
    log_dir=$rootpath"/devops/logs";
    mkdir -p $log_dir;
}

function build() {
    exec devops/bin/buildAppImage.sh >$log_dir"/buildAppImage.log"&
    exec devops/bin/buildMysqlImages.sh >$log_dir"/buildMysqlImages.log"&
}

gotodir
build
