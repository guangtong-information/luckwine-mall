#!/bin/sh
cd /etc/redis/


CONFIG="/etc/redis/sentinel.conf"
echo "sentinel monitor mymaster $Monitor" >> "$CONFIG"
echo "sentinel config-epoch mymaster 0" >> "$CONFIG"
echo "sentinel leader-epoch mymaster 0" >> "$CONFIG"

for server in $KnownSlave; do
    echo "sentinel known-slave mymaster  $server 6379" >> "$CONFIG"
done
for server in $KnownSentinel; do
    echo "sentinel known-sentinel mymaster $server 26379" >> "$CONFIG"
done
echo "sentinel current-epoch 0" >> "$CONFIG"



exec docker-entrypoint.sh redis-sentinel /etc/redis/sentinel.conf --sentinel