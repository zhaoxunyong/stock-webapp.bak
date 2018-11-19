#!/bin/sh
#cnpm cache clean --force
cnpm install --save
#2.0.0-rc.6
cnpm install bootstrap-vue --save
cnpm install node-sass --save
cnpm install axios --save
cnpm install vue-confirm --save
cnpm install open-iconic --save
cnpm install sortablejs --save
cnpm install stock-auto-complete --save
cnpm install vue-message --save
#cnpm install mathjs --save
#cnpm install decimal.js --save
# resloved:Vue BrowserslistError: Unknown browser query `dead`
#https://github.com/browserslist/browserslist/issues/276
#Downgraded bootstrap to 4.1.0 fixes the issue for me
#cnpm uninstall bootstrap --save
#cnpm install bootstrap@4.1.0 --save

npm install echarts --registry=https://registry.npm.taobao.org
#cnpm install vue-echarts --save

cnpm install http-server -g

#cnpm install stylus stylus-loader style-loader --save-dev
#cnpm install --save bollinger-bands

cnpm install --save technicalindicators@1.1.13

#npm uninstall node-sass
#npm i node-sass --sass_binary_site=https://npm.taobao.org/mirrors/node-sass/
#https://nodejs.org/download/release/v8.9.4/
#npm config set registry=https://registry.npmjs.org/
#删除build/webpack.prod.conf.js中的uglifyjs内容

git config --global core.filemode false
git config --global core.autocrlf false

