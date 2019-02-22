<template>
  <div class="good-item">
    <div>
      <a target="_blank" :href="'/goodsDetails?productId='+msg.spuId + '&skuId=' + msg.skuId">
        <div class="good-img">
          <img :src="msg.picPath+'?x-oss-process=image/resize,m_fixed,h_206,w_206'" :alt="msg.goodsName">
        </div>
      </a>
      <h6 class="good-title">{{msg.brandName}}</h6>
      <h3 class="sub-title ellipsis">{{msg.skuName}}</h3>
      <div class="good-price pr">
        <div class="ds pa">
          <a target="_blank" :href="'/goodsDetails?productId='+msg.spuId + '&skuId=' + msg.skuId">
            <y-button text="查看详情" style="margin: 0 5px"/>
          </a>
          <y-button text="加入购物车"
                    style="margin: 0 5px"
                    @btnClick="addCart(msg.skuId,msg.price,msg.goodsName,msg.picPath,msg.categoryName)"
                    classStyle="main-btn"
          ></y-button>
        </div>
        <p><span style="font-size: 16px">￥</span>
          {{msg.price}}</p>
      </div>
    </div>
  </div>
</template>
<script>
  import YButton from '/components/YButton'
  import {addCart} from '/api/goods'
  import {mapMutations, mapState} from 'vuex'

  export default {
    props: {
      msg: {type: [Object, Array]}
    },
    data () {
      return {}
    },
    methods: {
      ...mapMutations(['ADD_CART', 'ADD_ANIMATION', 'SHOW_CART']),
      addCart (id, price, name, img, categoryName) {
        if (!this.showMoveImg) {     // 动画是否在运动
          if (this.login) { // 登录了 直接存在用户名下
            addCart({productId: id}).then(res => {
              // 并不重新请求数据
              this.ADD_CART({productId: id, productPrice: price, productName: name, productImg: img, categoryName: categoryName})
            })
          } else { // 未登录 vuex
            this.ADD_CART({productId: id, productPrice: price, productName: name, productImg: img, categoryName: categoryName})
          }
          // 加入购物车动画
          let dom = event.target
          // 获取点击的坐标
          let elLeft = dom.getBoundingClientRect().left + (dom.offsetWidth / 2)
          let elTop = dom.getBoundingClientRect().top + (dom.offsetHeight / 2)
          // 需要触发
          this.ADD_ANIMATION({moveShow: true, elLeft: elLeft, elTop: elTop, img: img})
          if (!this.showCart) {
            this.SHOW_CART({showCart: true})
          }
        }
      }
    },
    computed: {
      ...mapState(['login', 'showMoveImg', 'showCart'])
    },
    mounted () {
    },
    components: {
      YButton
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "../assets/style/mixin";
  @import "../assets/style/theme";

  .good-item {
    background: #fff;
    width: 25%;
    transition: all .5s;
    height: 430px;
    &:hover {
      transform: translateY(-3px);
      box-shadow: 1px 1px 20px #999;
      .good-price p {
        display: none;
      }
      .ds {
        display: flex;
      }
    }
    .ds {
      @extend %block-center;
      width: 100%;
      display: none;
    }

    .good-img {
      img {
        margin: 50px auto 10px;
        @include wh(206px);
        display: block;
      }
    }
    .good-price {
      margin: 15px 0;
      height: 30px;
      text-align: center;
      line-height: 30px;
      color: #e4393c;
      font-size: 20px;
    }
    .good-title {
      line-height: 1.2;
      font-size: 16px;
      color: #424242;
      margin: 0 auto;
      padding: 0 14px;
      text-align: center;
      overflow: hidden;
    }
    h3 {
      text-align: center;
      line-height: 1.2;
      font-size: 12px;
      color: #d0d0d0;
      padding: 10px;
    }

  }
</style>
