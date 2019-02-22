<template>
  <div class="login v2">
    <div class="jx-top-header">
      <div class="w jx-top-head">
        <a href="/"><img src="../../../static/images/u16619.png"/> </a> &nbsp; &nbsp;
        <img src="../../../static/images/u16615.jpg"/>
        <div class="jx-top-right">欢迎来到酒緣网！<a href="javascript:;" style="padding: 0 5px" @click="loginPage = false">免费注册</a></div>
      </div>
    </div>
    <div  v-bind:style="imageUrl" class="wrapper">
      <div class="dialog dialog-shadow" style="display: block; margin-top: -220px;">
        <div class="title" v-if="loginPage">
          <h4>账户登陆</h4></div>
        <div v-if="loginPage" class="content">
          <ul class="common-form">
            <li class="username border-1p">
              <div class="input">
                <input type="text" v-model="ruleForm.username" placeholder="账号">
              </div>
            </li>
            <li>
              <div class="input">
                <input type="password" v-model="ruleForm.password" @keyup.enter="login" placeholder="密码">
              </div>
            </li>
            <li>
              <div class="input">
                <input type="text" style="margin-right:10px" v-model="ruleForm.code" @keyup.enter="login" placeholder="验证码">
                <img :src="imgUrl" v-on:click="captcha"/>
              </div>
            </li>
            <li style="text-align: right;padding-bottom: 15px;" class="pr">
              <span class="pa" style="top: 0;left: 0;color: #d44d44">{{ruleForm.errMsg}}</span>
            </li>
          </ul>
          <!--登陆-->
          <div>
            <y-button text="登陆" :classStyle="isLoginOk" @btnClick="login" class="btn"></y-button>
          </div>
        </div>
        <div class="registered" v-else>
          <h4>账户注册</h4>
          <div class="content" style="margin-top: 20px;">
            <ul class="common-form">
              <li class="username border-1p">
                <div class="input">
                  <input type="text"
                         v-model="registered.loginAccount" placeholder="账号"
                         @keyup="registered.loginAccount = registered.loginAccount.replace(/[^\w\.\/]/ig,'')">
                </div>
              </li>
              <li>
                <div class="input">
                  <input type="text" style="margin-right:10px" v-model="ruleForm.code"  placeholder="验证码">
                  <img :src="registerImgUrl" v-on:click="registercaptcha"/>
                </div>
              </li>
              <li>
                <div class="input">
                  <input type="text" style="margin-right:10px" v-model="ruleForm.code"  placeholder="校验码">
                  <y-button :classStyle="isRegOk" text="获取短信验证码" class="btn" style="padding: initial;" @btnClick="regist"></y-button>
                </div>
              </li>
              <li>
                <div class="input">
                  <input type="password"
                         v-model="registered.loginPw"
                         placeholder="密码">
                </div>
              </li>
              <li>
                <div class="input">
                  <input type="password" v-model="registered.loginPw2" placeholder="重复密码">
                </div>
              </li>
            </ul>
            <div>
              <y-button :classStyle="isRegOk" text="注册" class="btn" @btnClick="regist"></y-button>
            </div>
            <ul class="common-form pr">
              <li class="pa" style="left: 0;top: 0;margin: 0;color: #d44d44">{{registered.errMsg}}</li>
              <li style="text-align: center;line-height: 48px;margin-bottom: 0;">
                <span>如果您已拥有账户，则可在此</span>
                <a href="javascript:;"
                   style="margin: 0 5px"
                   @click="loginPage = true">登陆</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="cop_box">
      <div class="cop"><p> 科技有限公司  ©  酒缘网   2011  www.luckwine.com  All   Rights Reserved   粤ICP备88888888号   粤公网安备88888888</p></div>
      <div class="cop"><p>统一社会信用代码  88888888   食品流通许可证编号  JY88888888 </p></div>
      <div class="cop"><p>地址：广东省广州市经济技术开发区创业园8栋8号～88号</p></div>
      <div class="cop"><p style="font-weight: bold; color: red">购买前请确认达到法定饮酒年龄！酒缘网不销售任何含酒精产品给18岁以下人士！</p></div>
    </div>
  </div>
</template>
<script>
  import YFooter from '/common/footer'
  import YButton from '/components/YButton'
  import {captchaInit, register, userLogin} from '/api/index'
  import {addCartBatch} from '/api/goods'
  import {getStore, removeStore} from '/utils/storage'
  import {queryAdContentS} from '/api'

  export default {
    data () {
      return {
        cart: [],
        loginPage: true,
        imgUrl: '',
        registerImgUrl: '',
        ruleForm: {
          username: '',
          password: '',
          captchaId: '',
          code: '',
          errMsg: ''
        },
        registered: {
          loginAccount: '',
          loginPw: '',
          loginPw2: '',
          errMsg: ''
        },
        banners: [],
        imageUrl: { background: 'url(/static/images/u16599.png) no-repeat center top', 'background-size': '100% 100%' }
      }
    },
    computed: {
      // 可点击注册
      isRegOk (rules) {
        const {loginPw, loginPw2, loginAccount} = this.registered
        return loginPw && loginPw2 && loginAccount ? 'main-btn' : 'disabled-btn'
      },
      isLoginOk () {
        const {password, username, code} = this.ruleForm
        return password && username && code ? 'main-btn' : 'disabled-btn'
      }
    },
    methods: {
      // 登陆时将本地的添加到用户购物车
      login_addCart () {
        let cartArr = []
        let locaCart = JSON.parse(getStore('buyCart'))
        if (locaCart && locaCart.length) {
          cartArr = locaCart.map(item => {
            return {
              'productId': item.productId,
              'productNum': item.productNum
            }
          })
        }
        this.cart = cartArr
      },
      login () {
        const {username, password, code, captchaId} = this.ruleForm
        if (!username || !password) {
          this.ruleForm.errMsg = '账号或者密码不能为空!'
        } else if (!code) {
          this.ruleForm.errMsg = '验证码不能为空!'
        } else {
          let params = {username, password, code, captchaId}
          userLogin(params).then(res => {
            if (res.data.code === 200) {
              if (this.cart.length) {
                addCartBatch({productMsg: this.cart}).then(res => {
                  if (res.status === '1') {
                    removeStore('buyCart')
                  }
                }).then(this.$router.go(-1))
              } else {
                this.$router.go(-1)
              }
            } else {
              this.ruleForm.errMsg = res.data.message
            }
          })
        }
      },
      regist () {
        const {loginAccount, loginPw, loginPw2} = this.registered
        if (!loginAccount || !loginPw || !loginPw2) {
          this.registered.errMsg = '账号密码不能为空'
          return false
        }
        if (loginPw2 !== loginPw) {
          this.registered.errMsg = '两次输入的密码不相同'
          return false
        }
        register({loginAccount, loginPw}).then(res => {
          this.registered.errMsg = res.data.message
          if (res.status === '0') {
            setTimeout(() => {
              this.ruleForm.errMsg = ''
              this.registered.errMsg = ''
              this.loginPage = true
            }, 500)
          } else {
            return false
          }
        })
      },
      captcha () {
        captchaInit().then(res => {
          if (res.data.code === 200) {
            this.ruleForm.captchaId = res.data.result['captchaId']
            this.imgUrl = '/portal/common/captcha/draw/' + res.data.result['captchaId']
          }
        })
      },
      registercaptcha () {
        captchaInit().then(res => {
          if (res.data.code === 200) {
            this.registered.captchaId = res.data.result['captchaId']
            this.registerImgUrl = '/portal/common/captcha/draw/' + res.data.result['captchaId']
          }
        })
      },
      init () {
        let query = this.$route.query
        if (query.type === 'registered') {
          this.loginPage = false
        } else {
          this.loginPage = true
        }
        queryAdContentS({'pageId': '9'}).then(res => {
          this.banners = res.data.result
          this.imageUrl = { background: 'url(' + this.banners['100012'][0]['imageUrl'] + ') no-repeat center top', 'background-size': '100% 100%' }
          console.log(this.imageUrl)
        })
      }
    },
    mounted () {
      this.login_addCart()
      this.captcha()
      this.registercaptcha()
    },
    created () {
      this.init()
    },
    components: {
      YFooter,
      YButton
    },
    watch () {

    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  * {
    box-sizing: content-box;
  }

  .login {
    background-color: white;
    overflow-x: hidden;
    overflow-y: hidden;
    .input {
      height: 50px;
      display: flex;
      align-items: center;
      input {
        font-size: 16px;
        width: 100%;
        height: 100%;
        padding: 10px 15px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 6px;
      }
    }
    .wrapper {
      /*background: url(/static/images/u16599.png)  no-repeat center top;*/
      background-size: 100% 100%;
      height: 600px;
    }
    .btn {
      margin: 0;
      width: 100%;
      height: 48px;
      font-size: 18px;
      line-height: 48px
    }
  }

  .v2 .dialog {
    width: 350px;
    border: 1px solid #dadada;
    /*border-radius: 10px;*/
    top: 50%;
    left: 75%;
    margin-left: -175px;
    position: absolute;
    .title {
      overflow: visible;
      position: relative;
      /*background-size: 100px;*/
      /*height: 92px;*/
      margin: 50px 0 80px;
      /*padding: 75px 0 0;*/
      /*box-shadow: none;*/
      h4 {
        padding: 0;
        text-align: center;
        border-bottom: 1px solid #dcdcdc;
        position: absolute;
        bottom: 0;
        width: 100%;
        margin: 0;
        border-bottom: 0;
        box-shadow: none;
        line-height: 1em;
        height: auto;
        color: #333;
        font-weight: 400;
      }
    }
    .content {
      padding: 0 40px 22px;
      height: auto;
      .common-form {
        li {
          clear: both;
          margin-bottom: 15px;
          position: relative;
        }
      }
    }
  }

  .dialog-shadow, .v2 .bbs .dialog-shadow, .v2 .dialog-shadow {
    -webkit-box-shadow: 0 9px 30px -6px rgba(0, 0, 0, .2), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 10px 20px -10px rgba(0, 0, 0, .04);
    -moz-box-shadow: 0 9px 30px -6px rgba(0, 0, 0, .2), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 10px 20px -10px rgba(0, 0, 0, .04);
    box-shadow: 0 9px 30px -6px rgba(0, 0, 0, .2), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 10px 20px -10px rgba(0, 0, 0, .04);
  }

  @media screen and (min-width: 737px), screen and (-webkit-max-device-pixel-ratio: 1.9) and (max-width: 736px) and (min-device-width: 737px) {
    .wrapper {
      background: url(/static/images/con-bg_04f25dbf8e.jpg) repeat-x;
      position: absolute;
      top: 88px;
      bottom: 0;
      left: 0;
      right: 0;
    }
    .dialog {
      background: url(/static/images/dialog-gray-bg.png) #fff bottom repeat-x;
      /*border-radius: 12px;*/
      display: none;
      margin: -163px 0 0 -218px;
      width: 436px;
      position: fixed;
      left: 50%;
      top: 50%;
    }
    .dialog .title h4 {
      border-bottom: #d1d1d1 solid 1px;
      box-shadow: 0 2px 6px #d1d1d1;
      color: #666;
      font-size: 20px;
      height: 61px;
      line-height: 61px;
      padding: 0 0 0 35px;
    }
    .common-form li {
      clear: both;
      margin-bottom: 15px;
      position: relative;
    }
  }

  .registered {
    h4 {
      padding: 0;
      text-align: center;
      color: #666;
      border-bottom: 1px solid #dcdcdc;
      -webkit-box-shadow: none;
      -moz-box-shadow: none;
      box-shadow: none;
      font-weight: 700;
      font-size: 20px;
      height: 60px;
      line-height: 60px;
    }
  }

  .cop_box{
    width: 100%;
    padding-top: 650px;
    .cop{
      text-align: center;
      margin: 10px 0;
    }
  }
  .jx-top-header{
    width: 100%;
    background: #fff;
    height: 88px;
    box-shadow: 1px 2px 6px #e1e1e1;
    z-index: 1;
    position: relative;
  }
  .jx-top-head{
    padding-top: 10px;
  }
  .jx-top-right{
    float: right;
    height: 78px;
    line-height: 68px;
  }


</style>
