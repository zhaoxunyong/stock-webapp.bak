docker run -d  -p 3306:3306 --restart=always --name mysql \
-e MYSQL_ROOT_PASSWORD=Aa654321 \
-e MYSQL_DATABASE=wenchun \
-e MYSQL_USER=wenchun \
-e MYSQL_PASSWORD=Aa123456 \
mysql:5.7.24 \
--character-set-server=utf8 --collation-server=utf8_general_ci --lower_case_table_names=1

sudo apt-cache policy mysql-server
sudo apt-get install mysql-server=5.7.24-1.el7

yum repolist all
yum --showduplicates list mysql-community-server
#yum install mysql-community-server-5.7.18-1.el7
yum install mysql-community-server-5.7.24-1.el7

#choco install mysql --version 5.7.18

在my.ini中加入:
character-set-server=utf8
collation-server=utf8_general_ci
lower_case_table_names=1

#5.7.24不需要添加
sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION'

windows安装：
https://cdn.mysql.com//Downloads/MySQL-5.7/mysql-5.7.24-winx64.zip
https://www.cnblogs.com/lmh2072005/p/5656392.html
http://morecoder.com/article/1113936.html
my.ini
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8
 
[mysqld]
# 设置3306端口
port = 3306
# 设置mysql的安装目录
basedir=D:\Programs\mysql\current
# 设置mysql数据库的数据的存放目录
datadir=D:\Programs\mysql\data
# 允许最大连接数
max_connections=20
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8

# 创建新表时将使用的默认存储引擎
#default-storage-engine=INNODB

collation-server=utf8_general_ci
lower_case_table_names=1

安装：
#自动生成无密码的root用户
mysqld --initialize-insecure
安装服务：
mysqld install
启动：
net start mysql

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