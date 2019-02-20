FROM java:8
MAINTAINER luckwine
ENV JAVA_OPTIONS=""
RUN echo 'Asia/Shanghai' >/etc/timezone
RUN mkdir /tools; mkdir -p /data/template; mkdir -p /data/file; mkdir -p /data/log; chmod 777 /tools; chmod 777 /data;
EXPOSE 8002/tcp
VOLUME ["/tools", "/data"]
ADD root.jar /tools/app.jar
ADD boot.conf /tools/boot.conf
RUN bash -c 'touch /tools/app.jar'
RUN bash -c 'touch /tools/boot.conf'
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTIONS -jar /tools/app.jar