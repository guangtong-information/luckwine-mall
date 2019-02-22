import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/'
import VueLazyload from 'vue-lazyload'
import infiniteScroll from 'vue-infinite-scroll'
import VueCookie from 'vue-cookie'
import { userInfo } from './api'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import Tabs from 'vue-tabs-component'

Vue.use(VueAwesomeSwiper)
Vue.use(infiniteScroll)
Vue.use(VueCookie)
Vue.use(Tabs)
Vue.use(VueLazyload, {
  // preLoad: 1.3,
  // error: 'dist/error.png',
  loading: '/static/images/load.gif'
  // attempt: 1
})
Vue.config.productionTip = false

// 不需要登陆的页面 => 白名单
const whiteList = ['/home', '/goods', '/login', '/goodsDetails', '/favorable']
router.beforeEach(function (to, from, next) {
  store.commit('RECORD_USERINFO', {info: null})
  userInfo().then(res => {
    // 没登录
    console.log(JSON.stringify(res))
    if (res.data.code === 200) {
      store.commit('RECORD_USERINFO', {info: res.data.result})
      //  跳转到
      if (to.path === '/login') {
        next({path: '/'})
      }
      next()
    } else {
      // 白名单

      if (whiteList.indexOf(to.path) !== -1 || to.path.indexOf('/goods') !== -1) {
        next()
      } else {
        next('/login')
      }
    }
  }).catch(reason => {
    if (whiteList.indexOf(to.path) !== -1 || to.path.indexOf('/goods') !== -1) {
      next()
    } else {
      next('/login')
    }
  })
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
