<template>
  <div class="layout-container">
    <y-header :showNav="false"></y-header>
    <div class="w">
      <div class="content">
        <div class="account-sidebar">
          <div class="avatar gray-box ">
            <div>
              <div class="title">
                <!--<img src="../../../static/images/u7918.png"/>-->
                <div class="img"></div>
                <span>我的酒缘网</span>
              </div>
              <div class="title_box">
                <div class="fl"><img :src="userInfo.info.headerimg"></div>
                <div style="height: 60px;line-height: 60px;color: red">{{userInfo.info.nickname}}</div>
              </div>
              <div class="title_box" v-on:click="tab({name: '账户余额',path: 'balance'})">
                <a>
                  <span>账户余额:</span><span style="color: red">200.00</span>
                </a>
              </div>
            </div>
            <div class="box-inner">

              <ul class="account-nav">
                <li v-for="(item,i) in nav" :key='i' :class="{current:item.name===title, item_title:item.path===''}"
                    @click="tab(item)">
                  <a href="javascript:;">
                    <div v-if="item.path===''" class="fl item_title_dev"><img :src="item.img"/></div>
                    {{item.name}}
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="account-content">
          <router-view></router-view>
        </div>
      </div>
    </div>
    <y-footer></y-footer>

  </div>

</template>
<script>
  import YFooter from '/common/footer'
  import YHeader from '/common/header'
  import { mapState } from 'vuex'
  export default {
    data () {
      return {
        title: '我的订单',
        nav: [
          {name: '交易管理', path: '', img: '../../../static/images/1.png'},
          {name: '我的订单 >', path: 'orderList'},
          {name: '我的收藏 >', path: 'support'},
          {name: '酒仙钱包', path: '', img: '../../../static/images/2.png'},
          {name: '我的优惠券 >', path: 'coupon'},
          {name: '我的账户', path: '', img: '../../../static/images/3.png'},
          {name: '基本信息 >', path: 'information'},
          {name: '收货地址 >', path: 'addressList'}
          // {name: '售后服务', path: 'support'},
          // {name: '以旧换新', path: 'aihuishou'}
        ],
        editAvatar: true
      }
    },
    computed: {
      ...mapState(['userInfo'])
    },
    methods: {
      tab (e) {
        if (e.path !== '') {
          this.$router.push({path: '/user/' + e.path})
          this.title = e.name
        }
      }
    },
    created () {
      let path = this.$route.path.split('/')[2]
      this.nav.forEach(item => {
        if (item.path === path) {
          this.title = item.name
        }
      })
    },
    components: {
      YFooter,
      YHeader
    },
    watch: {
      $route (to) {
        let path = to.path.split('/')[2]
        this.nav.forEach(item => {
          if (item.path === path) {
            this.title = item.name
          }
        })
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "../../assets/style/mixin";

  .w {
    padding-top: 40px;
  }

  .content {
    display: flex;
    height: 100%;
  }

  .account-sidebar {
    width: 194px;
    border-radius: 0px;
    .avatar {
      border-radius: 0px;
      text-align: center;
      .title{
        background-color: rgba(204, 0, 0, 1);
        color: white;
        height: 56px;
        line-height: 56px;
        font-weight: 400;
        font-size: 16px;
        .img{
          background-image: url(../../../static/images/u7918.png);
          height: 22px;
          width: 23px;
          position: absolute;
          margin-top: 14px;
          margin-left: 30px;
        }
      }
      .title_box{
        padding-top: 10px;
        padding-bottom:10px;
        background-color: rgb(254, 237, 236);
        a{
          color: #333333;
        }
      }
      img {
        width: 60px;
        height: 60px;
        margin-left: 5px;
      }
      h5 {
        font-size: 18px;
        line-height: 48px;
        font-weight: 700;
      }
    }
    .account-nav {

      li {
        position: relative;
        height: 48px;
        border-top: 1px solid #EBEBEB;
        line-height: 48px;
        &:hover {
          a {
            background-color: #98AFEE;
            color: #FFF;
          }
        }
        a {
          background-color:#fbfbfb;
          color: #000000;
          display: block;
        }
        &.current {
          a {
            background-color: #98AFEE;
            color: #FFF;
          }
        }
      }
      .item_title_dev{
        height:25px;
        padding-top:5px;
        margin-right: -60px;
        margin-left: 20px;
      }
      .item_title{
        img {
          width: 25px;
          height: 25px;
        }
        a {
          background-color:#fbfbfb;
          color: #000000;
          font-size: 18px;
          font-weight: bold;
          display: block;
        }
        &:hover {
          a {
            background-color: #fbfbfb;
            color: #000000;
          }
        }
      }

    }
  }

  .account-content {
    margin-left: 20px;
    flex: 1;
  }


</style>
