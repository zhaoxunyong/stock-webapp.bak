docker run -d  -p 3306:3306 --restart=always --name mysql \
-e MYSQL_ROOT_PASSWORD=Aa123456 \
-e MYSQL_DATABASE=stock \
-e MYSQL_USER=stock \
-e MYSQL_PASSWORD=Aa123456 \
mysql:5.6