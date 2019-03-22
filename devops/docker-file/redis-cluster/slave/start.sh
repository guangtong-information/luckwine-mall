#!/bin/sh
cd /etc/redis/
exec redis-server /etc/redis/redis.conf --protected-mode no