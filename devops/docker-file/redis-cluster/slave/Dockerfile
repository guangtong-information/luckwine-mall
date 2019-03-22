FROM redis
EXPOSE 6379
ADD redis.conf /etc/redis/redis.conf
ADD start.sh /etc/redis/start.sh
RUN chmod +x /etc/redis/start.sh
ENTRYPOINT ["/etc/redis/start.sh"]
CMD ["redis-server","/etc/redis/redis.conf"]