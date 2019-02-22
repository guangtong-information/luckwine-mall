<template>
  <div>
    <div class="banner">
      <div class="bg" ref="bg">
        <swiper :options="swiperOption" v-if="banners['100001'].length > 1">
          <swiper-slide v-for="(item, index) in banners['100001']" :key="index">
            <a v-if="item.jumpUrl!==null" :href="item.jumpUrl" target="_blank">
              <img style="height: 450px;width: 100%" :src="item.imageUrl"/>
            </a>
            <a v-else>
              <img style="height: 450px;width: 100%" :src="item.imageUrl"/>
            </a>
          </swiper-slide>
          <div class="swiper-pagination" slot="pagination"></div>
        </swiper>
      </div>
    </div>

    <section class="w mt30 clearfix">
      <y-shelf title="">
        <div slot="content" class="hot">
          <!--<mall-goods :msg="item" v-for="(item,i) in hot" :key="i"></mall-goods>-->
          <tabs>
            <tab name="疯狂抢购">
              <div>
                <div v-for="item in banners['100005']" style="display: inline-table; margin: 2px">
                  <a :href="item.jumpUrl" target="_blank">
                    <div><img style="width: 200px" :src="item.imageUrl"/></div>
                    <div style="text-align: center;color: #666666">{{item.title}}</div>
                    <div style="text-align: center;color: red">￥{{item.price}}</div>
                  </a>
                </div>
              </div>
            </tab>
            <tab name="整箱特惠">
              <div>
                <div v-for="item in banners['100006']" style="display: inline-table; margin: 2px">
                  <a :href="item.jumpUrl" target="_blank">
                    <div><img style="width: 200px" :src="item.imageUrl"/></div>
                    <div style="text-align: center;color: #666666">{{item.title}}</div>
                    <div style="text-align: center;color: red">￥{{item.price}}</div>
                  </a>
                </div>
              </div>
            </tab>
            <tab name="口粮聚惠">
              <div>
                <div v-for="item in banners['100007']" style="display: inline-table; margin: 2px">
                  <a :href="item.jumpUrl" target="_blank">
                    <div><img style="width: 200px" :src="item.imageUrl"/></div>
                    <div style="text-align: center;color: #666666">{{item.title}}</div>
                    <div style="text-align: center;color: red">￥{{item.price}}</div>
                  </a>
                </div>
              </div>
            </tab>
            <tab name="大牌特卖">
              <div>
                <div v-for="item in banners['100008']" style="display: inline-table; margin: 2px">
                  <a :href="item.jumpUrl" target="_blank">
                    <div><img style="width: 200px" :src="item.imageUrl"/></div>
                    <div style="text-align: center;color: #666666">{{item.title}}</div>
                    <div style="text-align: center;color: red">￥{{item.price}}</div>
                  </a>
                </div>
              </div>
            </tab>
          </tabs>
        </div>
      </y-shelf>
    </section>

    <section class="w mt30 clearfix">
      <shelf title="白酒馆">
        <div slot="content" style="display: -webkit-box">
          <div style="width: 200px;">
            <swiper :options="swiperOption" v-if="banners['100002'].length > 1" style="width: 200px">
              <swiper-slide v-for="(item, index) in banners['100002']" :key="index">
                <a v-if="item.jumpUrl!==null" :href="item.jumpUrl" target="_blank">
                  <img :src="item.imageUrl"/>
                </a>
                <a v-else>
                  <img :src="item.imageUrl"/>
                </a>
              </swiper-slide>
              <div class="swiper-pagination" slot="pagination"></div>
            </swiper>
            <div><span style="color: red">美酒品牌</span></div>
            <div>
              <span><router-link :to="'/goods/49-0'+'?search=茅台'" target="_blank">茅台</router-link></span>
              <span><router-link :to="'/goods/49-0'+'?search=五粮液'" target="_blank">五粮液</router-link></span>
              <span><router-link :to="'/goods/49-0'+'?search=泸州老窖'" target="_blank">泸州老窖</router-link></span>
              <span><router-link :to="'/goods/49-0'+'?search=汾酒'" target="_blank">汾酒</router-link></span>
              <span><router-link :to="'/goods/49-0'+'?search=牛栏山'" target="_blank">牛栏山</router-link></span>
              <span><router-link :to="'/goods/49-0'+'?search=西凤'" target="_blank">西凤</router-link></span>
            </div>
          </div>
          <div style="width: 1024px">
            <div v-for="item in banners['100009']" style="display: inline-table; margin: 2px">
              <a :href="item.jumpUrl" target="_blank">
                <div><img style="width: 200px" :src="item.imageUrl"/></div>
                <div style="text-align: center;color: #666666">{{item.title}}</div>
                <div style="text-align: center;color: red">￥{{item.price}}</div>
              </a>
            </div>
          </div>
        </div>
      </shelf>
    </section>
    <section class="w mt30 clearfix">
      <shelf title="洋馆馆">
        <div slot="content" style="display: -webkit-box">
          <div style="width: 200px;">
            <swiper :options="swiperOption" v-if="banners['100004'].length > 1" style="width: 200px">
              <swiper-slide v-for="(item, index) in banners['100004']" :key="index">
                <a v-if="item.jumpUrl!==null" :href="item.jumpUrl" target="_blank">
                  <img :src="item.imageUrl"/>
                </a>
                <a v-else>
                  <img :src="item.imageUrl"/>
                </a>
              </swiper-slide>
              <div class="swiper-pagination" slot="pagination"></div>
            </swiper>
            <div><span style="color: red">美酒品牌</span></div>
            <div>
              <span><router-link :to="'/goods/54-0'+'?search=人头马'" target="_blank">人头马</router-link></span>
              <span><router-link :to="'/goods/54-0'+'?search=百加得'" target="_blank">百加得</router-link></span>
              <span><router-link :to="'/goods/54-0'+'?search=轩尼诗'" target="_blank">轩尼诗</router-link></span>
              <span><router-link :to="'/goods/54-0'+'?search=马爹利'" target="_blank">马爹利</router-link></span>
              <span><router-link :to="'/goods/54-0'+'?search=芝华士'" target="_blank">芝华士</router-link></span>
              <span><router-link :to="'/goods/54-0'+'?search=深蓝'" target="_blank">深蓝</router-link></span>
              <span><router-link :to="'/goods/54-0'+'?search=真露'" target="_blank">真露</router-link></span>
            </div>
          </div>
          <div style="width: 1024px">
            <div v-for="item in banners['100011']" style="display: inline-table; margin: 2px">
              <a :href="item.jumpUrl" target="_blank">
                <div><img style="width: 200px" :src="item.imageUrl"/></div>
                <div style="text-align: center;color: #666666">{{item.title}}</div>
                <div style="text-align: center;color: red">￥{{item.price}}</div>
              </a>
            </div>
          </div>
        </div>
      </shelf>
    </section>
    <section class="w mt30 clearfix">
      <shelf title="葡萄酒馆">
        <div slot="content" style="display: -webkit-box">
          <div style="width: 200px;">
            <swiper :options="swiperOption" v-if="banners['100003'].length > 1" style="width: 200px">
              <swiper-slide v-for="(item, index) in banners['100003']" :key="index">
                <a v-if="item.jumpUrl!==null" :href="item.jumpUrl" target="_blank">
                  <img :src="item.imageUrl"/>
                </a>
                <a v-else>
                  <img :src="item.imageUrl"/>
                </a>
              </swiper-slide>
              <div class="swiper-pagination" slot="pagination"></div>
            </swiper>
            <div><span style="color: red">美酒品牌</span></div>
            <div>
              <span><router-link :to="'/goods/50-0'+'?search=拉菲'" target="_blank">拉菲</router-link></span>
              <span><router-link :to="'/goods/50-0'+'?search=拉马克'" target="_blank">拉马克</router-link></span>
              <span><router-link :to="'/goods/50-0'+'?search=长城'" target="_blank">长城</router-link></span>
              <span><router-link :to="'/goods/50-0'+'?search=拉斐'" target="_blank">拉斐</router-link></span>
              <span><router-link :to="'/goods/50-0'+'?search=CASTEL'" target="_blank">CASTEL</router-link></span>
              <span><router-link :to="'/goods/50-0'+'?search=奔富'" target="_blank">奔富</router-link></span>
              <span><router-link :to="'/goods/50-0'+'?search=张裕'" target="_blank">张裕</router-link></span>
              <span><router-link :to="'/goods/50-0'+'?search=香格里拉'" target="_blank">香格里拉</router-link></span>
            </div>
          </div>
          <div style="width: 1024px">
            <div v-for="item in banners['100010']" style="display: inline-table; margin: 2px">
              <a :href="item.jumpUrl" target="_blank">
                <div><img style="width: 200px" :src="item.imageUrl"/></div>
                <div style="text-align: center;color: #666666">{{item.title}}</div>
                <div style="text-align: center;color: red">￥{{item.price}}</div>
              </a>
            </div>
          </div>
        </div>
      </shelf>
    </section>

    <section class="w mt30 clearfix" v-for="(item,i) in floors" :key="i">
      <y-shelf :title="item.title">
        <div slot="content" class="floors">
          <div class="imgbanner">
            <img v-lazy="floors[i].image.image" :alt="item.title">
          </div>
          <mall-goods :msg="tab" v-for="(tab,i) in item.tabs" :key="i"></mall-goods>
        </div>
      </y-shelf>
    </section>



  </div>

</template>
<script>
  import 'swiper/dist/css/swiper.css'
  import {swiper, swiperSlide} from 'vue-awesome-swiper'
  import {queryAdContentS} from '/api'
  import YShelf from '/components/shelfno'
  import Shelf from '/components/shelf'
  import product from '/components/product'

  export default {
    data () {
      return {
        banners: {},
        bgOpt: {
          offsetLeft: 0,
          offsetTop: 0,
          offsetWidth: 0,
          offsetHeight: 0
        },
        floors: [],
        hot: [],
        swiperOption: {
          pagination: {
            el: '.swiper-pagination'
          },
          initialSlide: 0,
          loop: true,
          autoplay: {
            delay: 3000,
            disableOnInteraction: false
          },
          observer: true,
          observeParents: true
        }
      }
    },
    methods: {
      // 鼠标移入
      bgOver (event) {
        // 获取移入时的位置
        const {offsetLeft, offsetTop, offsetWidth, offsetHeight} = event
        this.bgOpt.offsetLeft = offsetLeft
        this.bgOpt.offsetTop = offsetTop
        this.bgOpt.offsetWidth = offsetWidth
        this.bgOpt.offsetHeight = offsetHeight
      },
      // 鼠标移动
      bgMove (dom, eve) {
        const {offsetLeft, offsetTop, offsetWidth, offsetHeight} = this.bgOpt
        let X, Y
        let mouseX = eve.pageX - offsetLeft
        let mouseY = eve.pageY - offsetTop
        if (mouseX > offsetWidth / 2) {
          X = mouseX - (offsetWidth / 2)
        } else {
          X = mouseX - (offsetWidth / 2)
        }
        if (mouseY > offsetHeight / 2) {
          Y = offsetHeight / 2 - mouseY
        } else {
          Y = offsetHeight / 2 - mouseY
        }
        dom.style['-webkit-transform'] = `rotateY(${X / 50}deg) rotateX(${Y / 50}deg)`
        dom.style.transform = `rotateY(${X / 50}deg) rotateX(${Y / 50}deg)`
      },
      // 鼠标移除
      bgOut (dom) {
        dom.style.transform = 'rotateY(0deg) rotateX(0deg)'
        dom.style['-webkit-transform'] = 'rotateY(0deg) rotateX(0deg)'
      }
    },
    mounted () {
      queryAdContentS({'pageId': '10'}).then(res => {
        console.log(res.data.result)
        this.banners = res.data.result
        if (this.banners['100001'] == null) {
          this.banners['100001'] = [{jumpUrl: ''}]
        }
      })
    },
    components: {
      YShelf,
      Shelf,
      product,
      swiper,
      swiperSlide
    }
  }
</script>
<style>
  .tabs-component {
    /*margin: 4em 0;*/
  }

  .tabs-component-tabs {
    margin-bottom: 5px;
    margin-top: -56px;
    z-index: 100;
    position: relative;
  }

  .tabs-component-tab {
    color: #999;
    font-size: 18px;
    font-weight: 600;
    margin-right: 0;
    list-style: none;
    display: inline-table;
    text-align: center;
  }

  .tabs-component-tab:hover {
    color: #c81400;
    border-top: 3px solid #c81400;
  }

  .tabs-component-tab a:hover {
    color: #c81400;
  }

  .tabs-component-tab.is-active {
    color: #c81400;
    border-top: 3px solid #c81400;
  }

  .tabs-component-tab.is-disabled * {
    color: #cdcdcd;
    cursor: not-allowed !important;
  }

  .tabs-component-tab-a {
    align-items: center;
    color: inherit;
    display: flex;
    padding: .75em 117.3px;
    text-decoration: none;
  }

  .tabs-component-panels {
    /*padding: 4em 0;*/
  }
</style>

<style lang="scss" rel="stylesheet/scss" scoped>
  .banner, .banner span, .banner div {
    font-family: "Microsoft YaHei";
    transition: all .3s;
    -webkit-transition: all .3s;
    transition-timing-function: linear;
    -webkit-transition-timing-function: linear;
  }

  .banner {
    perspective: 3000px;
    position: relative;
    z-index: 19;
  }

  .bg {
    position: relative;
    width: 100%;
    height: 435px;
    /*margin: 20px auto;*/
    /*background: url("/static/images/banner-3d-item.png") center no-repeat;*/
    background-size: 100% 100%;
    border-radius: 10px;
    transform-style: preserve-3d;
    -webkit-transform-origin: 50% 50%;
    -webkit-transform: rotateY(0deg) rotateX(0deg);
  }

  .img {
    display: block;
    position: absolute;
    width: 100%;
    height: 100%;
    bottom: 5px;
    left: 0;
    /*background: url("/static/images/banner-3d.png") center no-repeat;*/
    background-size: 95% 100%;
  }

  .text {
    position: absolute;
    top: 20%;
    right: 10%;
    font-size: 30px;
    color: #fff;
    text-align: right;
    font-weight: lighter;
  }

  .copyright {
    position: absolute;
    bottom: 10%;
    right: 10%;
    font-size: 10px;
    color: #fff;
    text-align: right;
    font-weight: lighter;
  }

  .a {
    -webkit-transform: translateZ(40px);
  }

  .b {
    -webkit-transform: translateZ(20px);
  }

  .c {
    -webkit-transform: translateZ(0px);
  }
  a {
    padding: 0px!important;
    color: rgba(0, 0, 0, 0.78) !important;
  }
  a:hover{
    color: #CF010E!important;
  }

  .sk_item {
    width: 170px;
    height: 225px;
    padding: 0 14px 0 15px;
    > div {
      width: 100%;
    }
    a {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      transition: all .3s;
      &:hover {
        transform: translateY(-5px);
      }
    }
    img {
      width: 130px;
      height: 130px;
      margin: 17px 0;
    }
    .sk_item_name {
      color: #999;
      display: block;
      max-width: 100%;
      _width: 100%;
      overflow: hidden;
      font-size: 12px;
      text-align: left;
      height: 32px;
      line-height: 16px;
      word-wrap: break-word;
      word-break: break-all;
    }
    .sk_item_price {
      padding: 3px 0;
      height: 25px;
    }
    .price_new {
      font-size: 18px;
      font-weight: 700;
      margin-right: 8px;
      color: #f10214;
    }
    .price_origin {
      color: #999;
      font-size: 12px;
    }
  }

  .box {
    overflow: hidden;
    position: relative;
    z-index: 0;
    margin-top: 29px;
    box-sizing: border-box;
    border: 1px solid rgba(0, 0, 0, .14);
    border-radius: 8px;
    background: #fff;
    box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);

  }

  ul.box {
    display: flex;
    li {
      flex: 1;
      img {
        display: block;
        width: 305px;
        height: 200px;
      }
    }
  }

  .mt30 {
    margin-top: 30px;
  }

  .hot {
    display: flex;
    > div {
      flex: 1;
      width: 25%;
    }
  }

  .floors {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    .imgbanner {
      width: 50%;
      height: 430px;
    }
    img {
      display: block;
      width: 100%;
      height: 100%;
    }
  }
</style>
