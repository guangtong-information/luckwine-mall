FROM mysql:5.7
ENV TZ "Asia/Shanghai"
ENV MYSQL_ROOT_PASSWORD=Aa123456
RUN cp /etc/mysql/mysql.conf.d/mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf.bak
ADD *.sql /docker-entrypoint-initdb.d/
ADD mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf