#!/bin/sh
cd /etc/redis/

if [[ ! -f "/etc/redis/sentinel.conf" ]]; then
    CONFIG="/etc/redis/sentinel.conf"

    echo "sentinel monitor mymaster $Monitor" >> "$CONFIG"

    for server in $KnownSlave; do
        echo "sentinel known-slave mymaster  $server 6379" >> "$CONFIG"
    done
    for server in $KnownSentinel; do
        echo "sentinel known-sentinel mymaster $server 26379" >> "$CONFIG"
    done
fi

exec docker-entrypoint.sh redis-sentinel /etc/redis/sentinel.conf --sentinel