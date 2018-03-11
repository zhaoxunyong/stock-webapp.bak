docker run -d  -p 3306:3306 --restart=always --name mysql \
-e MYSQL_ROOT_PASSWORD=Aa123456 \
-e MYSQL_DATABASE=wenchun \
-e MYSQL_USER=wenchun \
-e MYSQL_PASSWORD=Aa123456 \
mysql:5.6 \
--character-set-server=utf8 --collation-server=utf8_general_ci

grant all privileges on *.* to root@'localhost' identified by 'Aa654321';
CREATE DATABASE `wenchun` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci; 

docker run -d -p 6379:6379 --restart=always --name redis redis

http://localhost:8080/fetch/index
http://localhost:8080/fetch/index2

http://120.79.159.6:8082