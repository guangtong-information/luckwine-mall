<!--商品详情-->
<template>
  <div class="w store-content">
    <div class="crumbs">
      <router-link to="/home"> 首页</router-link>
      >
      <router-link :to="'/goods/'+product.spu.categoryId+'-0'"> {{product.categoryName}}</router-link>
      >
      <router-link :to="'/goods/'+product.spu.categoryId+'-' + product.spu.brandId"> {{product.brandName}}</router-link>
      >
      {{sku.skuName}}
    </div>
    <div class="gray-box">
      <div class="gallery-wrapper">
        <div class="gallery">
          <div class="thumbnail">
            <ul>
              <li v-for="(item, i) in small" :key="i" :class="{on:big===item}" @click="big=item">
                <img v-lazy="item">
              </li>
            </ul>
          </div>
          <div class="thumb">
            <div class="big">
              <img :src="big" :alt="spu.goodsName">
            </div>
          </div>
        </div>
      </div>
      <!--右边-->
      <div class="banner">
        <div class="sku-custom-title">
          <h4>{{sku.skuName}}</h4>
          <h6>
            <span>{{spu.title}}</span>
            <span class="price">
              <em>¥</em><i>{{sku.price}}</i></span>
          </h6>
        </div>

        <div class="num" v-if="product.skus.length > 1">
          <div>
            选择：
          </div>
          <div style="padding-left: 10px;">
            <div class="skuspan" :class="item.id === sku.id ? 'choose' : ''" v-for="(item, i) in product.skus">
              <router-link tag="div" :to="'/goodsDetails?productId='+spu.id+'&skuId='+item.id">
                <a style="color:#000">
                  <div>{{item.propsStr}}</div>
                </a>
              </router-link>
            </div>
          </div>
        </div>

        <div class="num">
          <span class="params-name">数量</span>
          <buy-num @edit-num="editNum" :limit="Number(limit_num)"></buy-num>
        </div>
        <div class="buy">
          <y-button text="加入购物车"
                    @btnClick="addCart(sku.id,sku.price,sku.skuName,big,product.categoryName)"
                    classStyle="main-btn"
                    style="width: 145px;height: 50px;line-height: 48px"></y-button>
          <y-button text="现在购买"
                    @btnClick="checkout(sku.id)"
                    style="width: 145px;height: 50px;line-height: 48px"></y-button>
        </div>
      </div>
    </div>
    <!--产品信息-->
    <div class="item-info">
      <y-shelf title="商品详情">
        <div slot="content">
          <div v-if="spu.detail || spuPropsStr.length > 0">
            <div style="padding-left: 24px;width: 50%;display:inline-table;margin-top: 5px;margin-bottom: 5px;" v-for="(item, i) in spuPropsStr">
              {{item}}
            </div>
            <hr style=" height:1px;border:none;border-top:1px dotted #a0a0a0;" />
            <div style="padding: 24px;" class="spudetail" v-html="spu.detail">
            </div>
          </div>
          <div class="no-info" v-else>
            该产品暂无内容
          </div>
        </div>
      </y-shelf>
      <y-shelf title="温馨提示">
        <div slot="content" style="padding: 24px;">
          <p>
            根据新修订的《商标法》及国家工商总局文件要求，2014年5月1日之后不得将"驰名商标"字样用于商品宣传，酒仙网依法对商品图片中含"驰名商标"字样做马赛克处理；同时，涉及厂家正在按照新规定逐步更换包装，在此期间，我们将对新旧包装货品随机发货，请以实际收到的货物为准。
          </p>
        </div>
      </y-shelf>

    </div>
  </div>
</template>
<script>
  import {addCart, productDet} from '/api/goods'
  import {mapMutations, mapState} from 'vuex'
  import YShelf from '/components/shelf'
  import BuyNum from '/components/buynum'
  import YButton from '/components/YButton'

  export default {
    data () {
      return {
        small: [],
        sku: {},
        spu: {},
        spuPropsStr: [],
        big: '',
        product: {
          skus: [],
          spu: {}
        },
        productNum: 1,
        limit_num: 1
      }
    },
    computed: {
      ...mapState(['login', 'showMoveImg', 'showCart'])
    },
    methods: {
      ...mapMutations(['ADD_CART', 'ADD_ANIMATION', 'SHOW_CART']),
      _productDet (id, skuId) {
        productDet({id}).then(res => {
          let result = res.data.result.response
          this.product = result
          this.chooseSku(skuId)
          this.spu = res.data.result.response.spu
          this.small = this.spu.picPath.split(',')
          this.big = this.small[0]
          this.limit_num = this.sku.quantity
          if (this.spu.propsStr) {
            this.spuPropsStr = this.spu.propsStr.split(';')
          }
        })
      },
      chooseSku (id) {
        let skus = this.product.skus
        let f = true
        for (let index in skus) {
          if (id && skus[index].id.toString() === id.toString()) {
            this.sku = skus[index]
            f = false
            break
          }
        }
        if (f) {
          this.sku = skus[0]
        }
      },
      addCart (id, price, name, img, categoryName) {
        if (!this.showMoveImg) {     // 动画是否在运动
          if (this.login) { // 登录了 直接存在用户名下
            addCart({productId: id, productNum: this.productNum}).then(res => {
              // 并不重新请求数据
              this.ADD_CART({
                productId: id,
                productPrice: price,
                productName: name,
                productImg: img,
                productNum: this.productNum,
                categoryName: categoryName
              })
            })
          } else { // 未登录 vuex
            this.ADD_CART({
              productId: id,
              productPrice: price,
              productName: name,
              productImg: img,
              productNum: this.productNum,
              categoryName: categoryName
            })
          }
          // 加入购物车动画
          var dom = event.target
          // 获取点击的坐标
          let elLeft = dom.getBoundingClientRect().left + (dom.offsetWidth / 2)
          let elTop = dom.getBoundingClientRect().top + (dom.offsetHeight / 2)
          // 需要触发
          this.ADD_ANIMATION({moveShow: true, elLeft: elLeft, elTop: elTop, img: img})
          if (!this.showCart) {
            this.SHOW_CART({showCart: true})
          }
        }
      },
      checkout (productId) {
        this.$router.push({path: '/checkout', query: {productId, num: this.productNum}})
      },
      editNum (num) {
        this.productNum = num
      },
      init () {
        let id = this.$route.query.productId
        let skuId = this.$route.query.skuId
        this._productDet(id, skuId)
      }
    },
    components: {
      YShelf, BuyNum, YButton
    },
    created () {
      this.init()
    },
    watch: {
      '$route': 'init'
    }
  }
</script>
<style>

</style>
<style lang="scss" scoped>
  @import "../../assets/style/mixin";

  .crumbs {
    padding: 15px 0px 0px 0px;
    a {
      color: #3d3d3d;
    }
  }

  .store-content {
    clear: both;
    width: 1230px;
    min-height: 600px;
    padding: 0 0 25px;
    margin: 0 auto;
  }

  .gray-box {
    display: flex;
    padding: 40px;
    margin: 20px 0;
    border-radius: 0px !important;
    .gallery-wrapper {
      .gallery {
        display: flex;
        width: 460px;
        .thumbnail {
          li:first-child {
            margin-top: 0px;
          }
          li {
            @include wh(80px);
            margin-top: 10px;
            padding: 12px;
            border: 1px solid rgba(0, 0, 0, .06);
            cursor: pointer;
            &.on {
              padding: 10px;
              border: 3px solid rgba(0, 0, 0, .2);
            }
            img {
              display: block;
              @include wh(100%);
            }
          }
        }
        .thumb {
          .big {
            margin-left: 20px;
          }
          img {
            display: block;
            @include wh(360px)
          }
        }
      }
    }

    // 右边
    .banner {
      width: 700px;
      margin-left: 10px;
      h4 {
        font-size: 24px;
        line-height: 1.25;
        color: #000;
        margin-bottom: 13px;
      }
      h6 {
        font-size: 14px;
        line-height: 1.5;
        color: #bdbdbd;
        display: flex;
        align-items: center;
        justify-content: space-between;
      }
      .sku-custom-title {
        overflow: hidden;
        padding: 8px 8px 18px 10px;
        position: relative;
      }
      .params-name {
        padding-right: 20px;
        font-size: 14px;
        color: #8d8d8d;
        line-height: 36px;
      }
      .num {
        padding: 29px 0 8px 10px;
        border-top: 1px solid #ebebeb;
        display: flex;
        align-items: center;
      }
      .buy {
        position: relative;
        border-top: 1px solid #ebebeb;
        padding: 30px 0 0 10px;
      }
      .skuspan {
        border: 1px solid #e0e0e0;
        padding: 10px 8px 8px 10px;
        margin: 10px;
      }
      .skuspan:hover {
        border: 1px solid #F00;
      }

      .choose {
        border: 1px solid #F00;
      }

    }
  }

  .item-info {

    .gray-box {
      padding: 0;
      display: block;
    }
    .img-item {
      width: 1230px;
      img {
        width: 100%;
        height: auto;
        display: block;
      }
    }
  }

  .no-info {
    padding: 200px 0;
    text-align: center;
    font-size: 30px;
  }

  .price {
    display: block;
    color: #d44d44;
    font-weight: 700;
    font-size: 16px;
    line-height: 20px;
    text-align: right;
    i {
      padding-left: 2px;
      font-size: 24px;
    }

  }

</style>
