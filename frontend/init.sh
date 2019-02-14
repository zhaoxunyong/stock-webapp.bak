#!/bin/sh
#npm cache clean --force
npm install --save-dev compression-webpack-plugin@1.1.12 --registry=https://registry.npm.taobao.org
npm install --save --registry=https://registry.npm.taobao.org
#2.0.0-rc.6
# npm install bootstrap-vue --save --registry=https://registry.npm.taobao.org
npm install node-sass --save --registry=https://registry.npm.taobao.org
# npm install axios --save --registry=https://registry.npm.taobao.org
npm install vue-confirm --save --registry=https://registry.npm.taobao.org
# npm install open-iconic --save --registry=https://registry.npm.taobao.org
npm install sortablejs --save --registry=https://registry.npm.taobao.org
npm install stock-auto-complete --save --registry=https://registry.npm.taobao.org
npm install vue-message --save --registry=https://registry.npm.taobao.org
#npm install mathjs --save
#npm install decimal.js --save
# resloved:Vue BrowserslistError: Unknown browser query `dead`
#https://github.com/browserslist/browserslist/issues/276
#Downgraded bootstrap to 4.1.0 fixes the issue for me
#npm uninstall bootstrap --save
#npm install bootstrap@4.1.0 --save

# npm install echarts --registry=https://registry.npm.taobao.org
#npm install vue-echarts --save

npm install http-server -g --registry=https://registry.npm.taobao.org

#npm install stylus stylus-loader style-loader --save-dev
#npm install --save bollinger-bands

npm install --save technicalindicators@1.1.13 --registry=https://registry.npm.taobao.org
npm shrinkwrap
#npm uninstall node-sass
#npm i node-sass --sass_binary_site=https://npm.taobao.org/mirrors/node-sass/
#https://nodejs.org/download/release/v8.9.4/
#npm config set registry=https://registry.npmjs.org/
#删除build/webpack.prod.conf.js中的uglifyjs内容

#git config --global core.filemode false
#git config --global core.autocrlf false

