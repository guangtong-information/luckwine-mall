FROM jetty
USER root
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
USER jetty
COPY xboot-1.0-SNAPSHOT.war /var/lib/jetty/webapps/ROOT.war