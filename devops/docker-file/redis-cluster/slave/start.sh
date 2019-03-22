#!/bin/sh
cd /etc/redis/
exec nohup redis-server /etc/redis/redis.conf --protected-mode no > redis.log 2>&1&