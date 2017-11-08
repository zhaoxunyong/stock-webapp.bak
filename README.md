docker run -d  -p 3306:3306 --restart=always --name mysql \
-e MYSQL_ROOT_PASSWORD=Aa123456 \
-e MYSQL_DATABASE=stock \
-e MYSQL_USER=stock \
-e MYSQL_PASSWORD=Aa123456 \
mysql:5.6 \
--character-set-server=utf8 --collation-server=utf8_general_ci

http://localhost:8080/fetch/index
http://localhost:8080/fetch/index2