#!/bin/bash
docker compose exec ansible-control bash -c "cp /root/.ssh/id_rsa.pub /usr/share/cp-ansible-sandbox/id_rsa.pub"
docker compose exec zookeeper1 bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec zookeeper2 bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec zookeeper3 bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec kafka1 bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec kafka2 bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec kafka3 bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec connect bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec schema-registry bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec ksqldb bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec control-center bash -c "cat /usr/share/cp-ansible-sandbox/id_rsa.pub >> /root/.ssh/authorized_keys"
docker compose exec zookeeper1 bash -c "service ssh start"
docker compose exec zookeeper2 bash -c "service ssh start"
docker compose exec zookeeper3 bash -c "service ssh start"
docker compose exec kafka1 bash -c "service ssh start"
docker compose exec kafka2 bash -c "service ssh start"
docker compose exec kafka3 bash -c "service ssh start"
docker compose exec connect bash -c "service ssh start"
docker compose exec schema-registry bash -c "service ssh start"
docker compose exec ksqldb bash -c "service ssh start"
docker compose exec control-center bash -c "service ssh start"
