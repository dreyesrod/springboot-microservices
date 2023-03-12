# URL
```
https://www.youtube.com/playlist?list=PL145AyWAbMDhwUbBL74s1D2ZV9EqBaQ1t
```
---
# Commands

### MongoDB
```
docker run -it --name mongodb -p 27017:27017 -v D:\docker\volumes\mongodb:/data/db mongo:4.4.19-rc2
```
### MySQL80
```
docker run -it --rm --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -v D:\docker\volumes\mysql:/var/lib/mysql mysql:8.0.32
```
### Eureka
```
http://localhost:8761/
```
### Vault
```
docker run -it --name vault -p 8200:8200 --cap-add=IPC_LOCK -e VAULT_DEV_ROOT_TOKEN_ID=00000000-0000-0000-0000-000000000000 -e VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200 -v D:\volumes\vault:/data vault:1.12.3

-- vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"
vault kv put secret/order-service @order-service.json
vault kv put secret/product-service @product-service.json
vault kv put secret/stock-service @stock-service.json

http://127.0.0.1:8200
```
### RabbitMQ
```
docker run -it --name rabbitmq -p 5672:5672 -p 15672:15672 -v D:\docker\volumes\rabbitmq:/var/lib/rabbitmq rabbitmq:3.11-management

http://localhost:15672/
```
### Keycloak
```
docker run -it --name keycloak -p 9090:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -v D:\docker\volumes\keycloak:/opt/jboss/keycloak/standalone/data/ quay.io/keycloak/keycloak:17.0.0 start-dev
```
---
# TODO
- Docker Desktop
- COTS to containers
- Actualizar versiones de todo
- Enable Security with keycloak and spring boot 3
- Enable Trazing with open telemetry and spring boot 3