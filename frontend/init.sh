#!/bin/sh
#cnpm cache clean
cnpm install --save
cnpm install bootstrap-vue --save
cnpm install node-sass --save
cnpm install axios --save
cnpm install vue-confirm --save
cnpm install open-iconic --save
cnpm install sortablejs --save
cnpm install stock-auto-complete --save
cnpm install sortablejs --save
cnpm install vue-message --save
# resloved:Vue BrowserslistError: Unknown browser query `dead`
#https://github.com/browserslist/browserslist/issues/276
#Downgraded bootstrap to 4.1.0 fixes the issue for me
cnpm uninstall bootstrap --save
cnpm install bootstrap@4.1.0 --save

#cnpm install echarts --save
cnpm install vue-echarts --save

cnpm install http-server -g

cnpm install stylus stylus-loader style-loader --save-dev
