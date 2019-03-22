#!/bin/sh
cd /etc/redis/

if [[ ! -f "/etc/redis/sentinel.conf" ]]; then
    CONFIG="/etc/redis/sentinel.conf"
    for server in $KnownSlave; do
        echo "$server" >> "$CONFIG"
    done
    for server in $KnownSentinel; do
        echo "$server" >> "$CONFIG"
    done
fi

exec docker-entrypoint.sh redis-sentinel /etc/redis/sentinel.conf --sentinel