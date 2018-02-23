import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '@/components/Home'
import About from '@/components/About'
import Document from '@/components/Document'
import Noweb from '@/components/404'
import Study from '@/views/Study'
import Work from '@/views/Work'
import Hobby from '@/views/Hobby'
import Slider from '@/views/Slider'

Vue.use(VueRouter)

let router = new VueRouter({
  mode:'history',//默认是hash模式
  linkActiveClass:'menvscode-active',
  routes:[
    {
      path:'/',
      components:{ // 多个视图的时候，默认渲染default的视图。
        default:Home,
        slider:Slider
      },
    },
    {
      path:'/home',
      name:'Home',//name代表当前的路由
      components:{ // 多个视图的时候，默认渲染default的视图。
        default:Home,
        slider:Slider
      },
      alias:'/index' //当访问'/index'的时候，就能匹配到当前的路由了。
    },
    {
      path:'/about',
      components:{ // 多个视图的时候，默认渲染default的视图。
        default:About,
        slider:Slider
      },
      children:[
        {
          path:'',//写''空值，代表默认子路由，相对于“/about”
          name:'About',
          component:Study,
        },
        {
          path:'/work',
          name:'Work',
          component:Work
        },
        {
          path:'/hobby',
          name:'Hobby',
          component:Hobby
        }
      ]
    },
    {
      path:'/document',
      name:'Document',
      components:{ // 多个视图的时候，默认渲染default的视图。
        default:Document,
        slider:Slider
      }
    },
    {
      path:'*',
      //component:Noweb
      //重定向
      //redirect:{path:'/home'}
      //redirect:{name:'Home'}
      redirect:(to)=>{
        console.log(to) //目标路由对象，就是访问路劲的路由信息
        if(to.path==='/123'){
          return './home'
        }else if(to.path==='/456'){
          return {path:'/Document'}
        }else{
          return {name:'About'}
        }
      }
    }
  ]
})
export default router