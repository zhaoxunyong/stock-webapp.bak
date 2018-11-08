docker run -d  -p 3306:3306 --restart=always --name mysql \
-e MYSQL_ROOT_PASSWORD=Aa654321 \
-e MYSQL_DATABASE=wenchun \
-e MYSQL_USER=wenchun \
-e MYSQL_PASSWORD=Aa123456 \
mysql:5.7.21 \
--character-set-server=utf8 --collation-server=utf8_general_ci --lower_case_table_names=1

sudo apt-cache policy mysql-server
sudo apt-get install mysql-server=5.7.21-1

yum repolist all
yum --showduplicates list mysql-community-server
#yum install mysql-community-server-5.7.18-1.el7
yum install mysql-community-server-5.7.21-1.el7

#choco install mysql --version 5.7.18

在my.ini中加入:
character-set-server=utf8
collation-server=utf8_general_ci
lower_case_table_names=1

#5.7.21不需要添加
sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION'

#https://www.cnblogs.com/conanwang/p/6001196.html

mysql_upgrade -u root -pAa654321
mysql -u root -pAa654321

grant all privileges on *.* to root@'%' identified by 'Aa123#@!' WITH GRANT OPTION;
grant all privileges on *.* to root@'localhost' identified by 'Aa654321';
#grant all privileges on *.* to wenchun@'localhost' identified by 'Aa654321';
CREATE DATABASE `wenchun` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci; 
FLUSH PRIVILEGES; 

echo "0 */2 * * * /works/app/stock-webapp/sql_backup.sh" > /var/spool/cron/root  

#cp -a stock.service /usr/lib/systemd/system/

git config --global core.autocrlf false
git config --global core.safecrlf warn
git config --global core.filemode false
git config --global core.whitespace cr-at-eol

docker run -d -p 6379:6379 --restart=always --name redis redis

http://localhost:8080/fetch/index
http://localhost:8080/fetch/index2

http://120.79.159.6:8082