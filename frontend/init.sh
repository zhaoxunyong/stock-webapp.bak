#!/bin/sh
#npm cache clean
npm install --save
npm install bootstrap-vue --save
npm install node-sass --save
npm install axios --save
npm install vue-confirm --save
npm install open-iconic --save
npm install sortablejs --save
npm install stock-auto-complete --save
npm install sortablejs --save
npm install vue-message --save
# resloved:Vue BrowserslistError: Unknown browser query `dead`
#https://github.com/browserslist/browserslist/issues/276
#Downgraded bootstrap to 4.1.0 fixes the issue for me
npm uninstall bootstrap --save
npm install bootstrap@4.1.0 --save

#npm install vue-echarts
npm install echarts --save

npm install http-server -g

npm install stylus stylus-loader style-loader --save-dev
