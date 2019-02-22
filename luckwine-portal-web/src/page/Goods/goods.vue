<template>
  <div class="goods">
    <div class="nav">
      <div class="w " style="margin-top: 10px;margin-bottom: 10px;">
        <div style="width: 100%;border: 1px solid #d9d9d9;" class="searchItem">
          <div>
            <div class="fl" style="width: 194px;">
              <span style="color: #CF010E;text-align: center">分类</span>
            </div>
          </div>
          <div class="fl" style="width: 1034px;background-color: rgb(255,255,255);">
            <span v-for="( item, i ) in aggs['CATEGORY']" >
              <router-link :to="searchItemUrl+'/'+item.key +'-'+ params.brandId">{{item.name}}</router-link>
            </span>
            <span  >
              <router-link :to="searchItemUrl+'/0-'+params.brandId">全部分类</router-link>
            </span>
          </div>
        </div>
      </div>
      <div class="w " style="margin-top: 10px;margin-bottom: 10px;">
        <div style="width: 100%;border: 1px solid #d9d9d9;" class="searchItem">
          <div>
            <div class="fl" style="width: 194px;">
              <span style="color: #CF010E;text-align: center">品牌</span>
            </div>
          </div>
          <div class="fl" style="width: 1034px;background-color: rgb(255,255,255);">
            <span v-for="( item, i ) in aggs['BRAND']" >
              <router-link :to="searchItemUrl+'/'+ params.categoryId +'-'+item.key">{{item.name}}</router-link>
            </span>
            <span  >
              <router-link :to="searchItemUrl+'/'+ params.categoryId +'-0'">全部品牌</router-link>
            </span>
          </div>
        </div>
      </div>
      <div class="w" style="margin-top: 10px;margin-bottom: 10px;">
        <a href="javascript:;" :class="{active:sortType===1}" @click="sort('')">综合排序</a>
        <a href="javascript:;" @click="sort('PRICE_ASC')" :class="{active:sortType===2}">价格从低到高</a>
        <a href="javascript:;" @click="sort('PRICE_DESC')" :class="{active:sortType===3}">价格从高到低</a>
        <div class="price-interval">
          <input type="number" class="input" placeholder="价格" v-model="params.min">
          <span style="margin: 0 5px"> - </span>
          <input type="number" placeholder="价格" v-model="params.max">
          <y-button text="确定" classStyle="main-btn" @btnClick="price_range()" style="margin-left: 10px;"></y-button>
        </div>
      </div>
    </div>

    <!--商品-->
    <div class="goods-box w" style="padding-left: 194px;">
      <div style="width: 194px;position: absolute;margin-left: -194px;">
        <div>热卖推荐</div>
      </div>
      <mall-goods v-for="(item,i) in computer" :key="i" :msg="item"></mall-goods>
      <y-pagination :total="total" :current-page='params.pageNo' :display='params.pageSize' @pagechange="pagechange"></y-pagination>
    </div>
    <div v-show="!busy"
         class="w load-more"
         v-infinite-scroll="loadMore"
         infinite-scroll-disabled="busy"
         infinite-scroll-distance="100">
      正在加载中...
    </div>
  </div>
</template>
<script>
  import {getComputer} from '/api/goods'
  import mallGoods from '/components/mallGoods'
  import YButton from '/components/YButton'
  import YPagination from '/components/pagination'
  export default {
    data () {
      return {
        computer: [],
        busy: false,
        timer: null,
        sortType: 1,
        windowHeight: null,
        windowWidth: null,
        aggs: [],
        total: 0,
        searchItemUrl: '/goods',
        params: {
          pageNo: 1,  // 页码
          pageSize: 24,
          categoryId: 0,
          brandId: 0,
          text: '',
          searchSort: '', // 排序
          min: '',  // 最小价格
          max: ''
        }
      }
    },
    methods: {
      _getComputer (flag) {
        const {pageNo, pageSize, searchSort, min, max, categoryId, brandId, text} = this.params
        let params = {
          pageNo,
          pageSize,
          text,
          searchSort,
          priceGte: min,
          priceLte: max
        }
        if (categoryId !== 0 && categoryId !== '0') {
          params['categoryId'] = categoryId
        }
        if (brandId !== 0 && brandId !== '0') {
          params['brandId'] = brandId
        }
        getComputer(params).then(res => {
          if (res.data.result.totalCount) {
            let data = res.data.result.response
            this.total = res.data.result.totalCount
            this.aggs = res.data.result.aggregationInfoMap
            if (flag) {
              this.computer = this.computer.concat(data)
            } else {
              this.computer = data
            }
          } else {
            clearTimeout(this.timer)
            this.busy = true
          }
        })
      },
      price_range () {
        if (this.params.min && this.params.max && this.params.min > this.params.max) {
          alert('结束价格不能小于起始价格')
          this.params.max = ''
        }
        this.params.pageNo = 1
        this.busy = false
        this._getComputer()
      },
      // 价格排序
      sort (v) {
        if (v === 'PRICE_ASC') {
          this.sortType = 2
        } else if (v === 'PRICE_DESC') {
          this.sortType = 3
        } else {
          this.sortType = 1
        }
        this.params.searchSort = v
        this.params.pageNo = 1
        this.busy = false
        this._getComputer()
      },
      // 加载更多
      loadMore () {
        this.busy = true
      //   this.timer = setTimeout(() => {
      //     this.params.pageNo++
      //     this._getComputer(true)
      //     this.busy = false
      //   }, 500)
      },
      pagechange (pageNo) {
        this.params.pageNo = pageNo
        this._getComputer()
      },
      init () {
        let query = this.$route.query
        let params = this.$route.params
        if (query.pageNo) {
          this.params.pageNo = query.pageNo
        }
        if (query.search) {
          this.params.text = query.search
        }
        if (params.categoryId && params.categoryId !== 0) {
          this.params.categoryId = params.categoryId
        } else {
          this.params.categoryId = 0
        }
        if (params.brandId && params.brandId !== 0) {
          this.params.brandId = params.brandId
        } else {
          this.params.brandId = 0
        }
        this._getComputer(false)
      }
    },
    created () {
      this.init()
    },
    mounted () {
      this.windowHeight = window.innerHeight
      this.windowWidth = window.innerWidth
    },
    components: {
      mallGoods,
      YButton,
      YPagination
    },
    watch: {
      '$route': 'init'
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "../../assets/style/mixin";
  @import "../../assets/style/theme";

  .searchItem{
    background-color: rgba(242, 242, 242, 1);
    span {
      padding: 5px;
      display: inline-table;
      width: 100px;
    }
    .router-link-exact-active{
      color: #CF010E;
    }
    a {
      padding: 0px!important;
    }
    a:hover{
      color: #CF010E!important;
    }
  }

  .nav {
    /*height: 60px;*/
    /*line-height: 60px;*/
    > div {
      display: flex;
      align-items: center;
      a {
        padding: 0 15px;
        height: 100%;
        @extend %block-center;
        font-size: 12px;
        color: #999;
        &.active {
          color: #5683EA;
        }
        &:hover {
          color: #5683EA;
        }
      }
      input {
        @include wh(80px, 30px);
        border: 1px solid #ccc;
      }
      input + input {
        margin-left: 10px;
      }
    }
    .price-interval {
      padding: 0 15px;
      @extend %block-center;
      input[type=number] {
        border: 1px solid #ccc;
        text-align: center;
        background: none;
        border-radius: 5px;
      }
    }
  }
  .load-more {
    text-align: center;

  }
  .goods-box {
    > div {
      float: left;
      border: 2px solid #efefef;
    }
  }


</style>
