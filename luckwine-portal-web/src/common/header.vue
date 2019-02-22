<style lang="scss" rel="stylesheet/scss" scoped>
  @import "./header.scss";
</style>
<template>
  <div class="header-box">
    <div>
      <header>
        <div class="w-header">
          <div class="w w-header-top">
            <em>欢迎来到酒缘网！</em> &nbsp;
            <span v-if="userInfo.info === null">
            <a href="/login?type=loginPage">请登录</a> &nbsp;
            <a href="/login?type=registered">免费注册</a> &nbsp;
            </span>
            <span v-else>
              <router-link to="/user/information">{{userInfo.info.nickname}}</router-link> &nbsp;
              <a v-on:click="_loginOut">【退出】</a> &nbsp;
            </span>
            <ul>
              <li>
                <router-link to="/cart"><a>购物车<strong>0</strong>件 &nbsp; &nbsp;</a></router-link>
              </li>
              <li>客服热线:<a>444-888-8888</a></li>
            </ul>
          </div>
          <div class="w-header-bottom">
            <div class="nav-banner">
              <h1>
                <router-link to="/" title="酒缘网"></router-link>
              </h1>
            </div>
          </div>
        </div>
        <div class="w w-box">
          <!--<div class="nav-logo"></div>-->
          <router-link to="/" title="酒缘网"><img src="/static/images/logo.png"  style="width: initial!important;"/></router-link> &nbsp; &nbsp;
          <img src="/static/images/u2288.gif"  style="width: initial!important;"/>
          <div class="fr img-w-box"><img src="/static/images/u2291.png" style="width: initial!important;"/></div>
          <div class="fr img-w-box"><img src="/static/images/u2290.png" style="width: initial!important;;"/> &nbsp;</div>
          <div class="mallSearch">
            <div style="border: solid #d30019;">
              <div class="dropdown s-combobox">
                <button class="dropbtn btnSelect">{{this.selectType}}</button>
                <div class="dropdown-content">
                  <router-link :to="'/goods/50-0'+'?search='+searchVal">
                    <button v-on:click="_selectType('葡萄酒', 50)" class="btn-item">葡萄酒</button>
                  </router-link>
                  <router-link :to="'/goods/54-0'+'?search='+searchVal">
                    <button v-on:click="_selectType('洋酒', 54)" class="btn-item">洋酒</button>
                  </router-link>
                  <router-link :to="'/goods/49-0'+'?search='+searchVal">
                    <button v-on:click="_selectType('白酒', 49)" class="btn-item">白酒</button>
                  </router-link>
                  <router-link :to="'/goods/0-0'+'?search='+searchVal">
                    <button v-on:click="_selectType('全部', '0-0')" class="btn-item">全部</button>
                  </router-link>
                </div>
              </div>
              <div class="s-combobox" @keydown.enter="_search">
                <input type="text" v-model="searchVal">
              </div>
              <router-link :to="'/goods/'+selectId+'-0?search='+searchVal">
                <button class="btnSearch" type="button">搜索<s></s></button>
              </router-link>
            </div>
            <ul class="hot-query">
              <li><router-link :to="'/goods/49'+'?search=茅台'">茅台</router-link></li>
              <li class="hot-query-highlight"><router-link :to="'/goods/49'+'?search=五粮液'">五粮液</router-link></li>
              <li class="hot-query-highlight"><router-link :to="'/goods/54'+'?search=迁尼斯XO'">迁尼斯XO</router-link></li>
              <li class="hot-query-highlight"><router-link :to="'/goods/49'+'?search=剑南春'">剑南春</router-link></li>
              <li class="hot-query-highlight"><router-link :to="'/goods/49'+'?search=汾酒'">汾酒</router-link></li>
              <li class="hot-query-highlight"><router-link :to="'/goods/49'+'?search=水井坊'">水井坊</router-link></li>
              <li class="hot-query-highlight"><router-link :to="'/goods/50'+'?search=拉菲'">拉菲</router-link></li>
              <li class="hot-query-highlight"><router-link :to="'/goods/49'+'?search=古井贡'">古井贡</router-link></li>
            </ul>
          </div>
        </div>
      </header>
      <div class="nav-sub">
        <div class="nav-sub-bg"></div>
        <div class="nav-sub-wrapper">
          <div class="w">
          </div>
        </div>
      </div>

      <!--底部导航-->
      <div class="nav-submenu">
        <div class="w nav-submenu-box">
          <div class="menudown s-combobox">
            <button class="menubtn  btnMain" type="button">全部商品分类</button>
            <div class="menudown-content" :class="{show: '/home' === $route.path}">
              <div class="menudown-item">
                <div class="item">
                  △ <router-link :to="'/goods/49-0'">白酒</router-link>
                  <div>
                    <span><router-link :to="'/goods/49-0'+'?search=茅台'">茅台</router-link></span>
                    <span><router-link :to="'/goods/49-0'+'?search=五粮液'">五粮液</router-link></span>
                    <span><router-link :to="'/goods/49-0'+'?search=剑南春'">剑南春</router-link></span>
                    <span><router-link :to="'/goods/49-0'+'?search=汾酒'">汾酒</router-link></span>
                  </div>
                  <div>
                    <span><router-link :to="'/goods/49-0'+'?search=浓香型'">浓香型</router-link></span>
                    <span><router-link :to="'/goods/49-0'+'?search=酱香型'">酱香型</router-link></span>
                    <span><router-link :to="'/goods/49-0'+'?search=贵州'">贵州</router-link></span>
                    <span><router-link :to="'/goods/49-0'+'?search=四川'">四川</router-link></span>
                  </div>
                </div>
                <div class="menudown-item-content">
                  <div style="width: 100%;border: 1px solid #d9d9d9;" class="searchItem">
                    <div>
                      <div class="fl" style="width: 98px;">
                        <span style="color: #CF010E;text-align: center">品牌</span>
                      </div>
                    </div>
                    <div class="fl" style="width: 800px;background-color: rgb(255,255,255);">
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=茅台'">茅台</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=五粮液'">五粮液</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=泸州老窖'">泸州老窖</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=汾酒'">汾酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=牛栏山'">牛栏山</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=西凤'">西凤</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=其他'">其他</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=洋河'">洋河</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=郎酒'">郎酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=习酒'">习酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=水井坊'">水井坊</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=汾酒集团'">汾酒集团</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=古井贡'">古井贡</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=红星'">红星</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=全兴'">全兴</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=董酒'">董酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=剑南春'">剑南春</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=杜康'">杜康</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=孔府家'">孔府家</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=景芝'">景芝</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=杏花村'">杏花村</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=茅台集团'">茅台集团</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=扳倒井'">扳倒井</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=酒鬼酒'">酒鬼酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=汾阳王'">汾阳王</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=伊力特'">伊力特</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=酒鬼'">酒鬼</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=金六福'">金六福</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=古井'">古井</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=潭酒'">潭酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=金门高粱'">金门高粱</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=茅台白金酒'">茅台白金酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=宋河'">宋河</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=衡水老白干'">衡水老白干</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=五粮液系列'">五粮液系列</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=西凤酒'">西凤酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=河套'">河套</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=珍酒'">珍酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=保定百年'">保定百年</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=宝丰'">宝丰</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=国窖1573'">国窖1573</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=仰韶'">仰韶</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=今世缘'">今世缘</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=五岭洞藏'">五岭洞藏</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=古贝春'">古贝春</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=白水杜康'">白水杜康</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=永丰'">永丰</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=江小白'">江小白</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=四特'">四特</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=刘伶醉'">刘伶醉</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=国隆'">国隆</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=王祖烧坊'">王祖烧坊</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=桂林'">桂林</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=皇沟御酒'">皇沟御酒</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=小糊涂仙'">小糊涂仙</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=白云边'">白云边</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=双沟'">双沟</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=杏花'">杏花</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=兰陵王'">兰陵王</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=国台'">国台</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=金不换'">金不换</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=厚工坊'">厚工坊</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=沱牌'">沱牌</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=湄窖'">湄窖</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=口子窖'">口子窖</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=黄鹤楼'">黄鹤楼</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=鸭溪窖'">鸭溪窖</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=迎驾'">迎驾</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=壮泉'">壮泉</router-link></span>
                      <span><router-link target="_blank" :to="'/goods/49-0'+'?search=诗仙太白'">诗仙太白</router-link></span>
                    </div>
                  </div>

                </div>
              </div>
              <div class="menudown-item">
                <div class="item">
                  △ <router-link :to="'/goods/50-0'">葡萄酒</router-link>
                  <div>
                    <span><router-link :to="'/goods/50-0'+'?search=法国'">法国</router-link></span>
                    <span><router-link :to="'/goods/50-0'+'?search=澳大利亚'">澳大利亚</router-link></span>
                    <span><router-link :to="'/goods/50-0'+'?search=西班牙'">西班牙</router-link></span>
                    <span><router-link :to="'/goods/50-0'+'?search=中国'">中国</router-link></span>
                  </div>
                  <div>
                    <span><router-link :to="'/goods/50-0'+'?search=茉莉花'">茉莉花</router-link></span>
                    <span><router-link :to="'/goods/50-0'+'?search=酱香型'">酱香型</router-link></span>
                    <span><router-link :to="'/goods/50-0'+'?search=丁戈树'">丁戈树</router-link></span>
                  </div>
                </div>
                <div class="menudown-item-content">
                  <div style="width: 100%;border: 1px solid #d9d9d9;" class="searchItem">
                  <div>
                    <div class="fl" style="width: 98px;">
                      <span style="color: #CF010E;text-align: center">品牌</span>
                    </div>
                  </div>
                  <div class="fl" style="width: 800px;background-color: rgb(255,255,255);">
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=拉菲'">拉菲</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=拉马克'">拉马克</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=长城'">长城</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=拉斐'">拉斐</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=CASTEL'">CASTEL</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=奔富'">奔富</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=张裕'">张裕</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=香格里拉'">香格里拉</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=丁闻'">丁闻</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=黄尾袋鼠'">黄尾袋鼠</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=波尔多城堡'">波尔多城堡</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=莱菲堡'">莱菲堡</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=通天'">通天</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=路易拉菲'">路易拉菲</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=五洲海购'">五洲海购</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=路易'">路易</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=藴妮'">藴妮</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=澜爵'">澜爵</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=欧瑞安'">欧瑞安</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=黎明骑士'">黎明骑士</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=盛纳'">盛纳</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=富隆'">富隆</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=丰收'">丰收</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=格拉芙'">格拉芙</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=木桐'">木桐</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=尼雅葡萄酒'">尼雅葡萄酒</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=茉莉花'">茉莉花</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=怡园酒庄'">怡园酒庄</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=1855梅多克二级庄'">1855梅多克二级庄</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=拉蒙'">拉蒙</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=ASC'">ASC</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=教皇新堡'">教皇新堡</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=威龙'">威龙</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=卡斯特'">卡斯特</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=禾富'">禾富</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=王朝'">王朝</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=蒙特斯'">蒙特斯</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=西班牙'">西班牙</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=仙露酒城'">仙露酒城</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=红魔鬼'">红魔鬼</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=肯爱'">肯爱</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=詹姆士'">詹姆士</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=丁戈树'">丁戈树</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=龙徽'">龙徽</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=民权'">民权</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=中酿波尔多酒业'">中酿波尔多酒业</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=奔富BIN系列'">奔富BIN系列</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=1855梅多克五级庄'">1855梅多克五级庄</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=罄玉酒庄'">罄玉酒庄</router-link></span>
                    <span><router-link target="_blank" :to="'/goods/50-0'+'?search=南山庄园'">南山庄园</router-link></span>
                  </div>
                  </div>
                </div>
              </div>
              <div class="menudown-item">
                <div class="item">
                  △ <router-link :to="'/goods/54-0'">洋酒</router-link>
                  <div>
                    <span><router-link :to="'/goods/54-0'+'?search=威士忌'">威士忌</router-link></span>
                    <span><router-link :to="'/goods/54-0'+'?search=白兰地'">白兰地</router-link></span>
                    <span><router-link :to="'/goods/54-0'+'?search=人头马'">人头马</router-link></span>
                  </div>
                  <div>
                    <span><router-link :to="'/goods/54-0'+'?search=马爹利'">马爹利</router-link></span>
                    <span><router-link :to="'/goods/54-0'+'?search=百龄坛'">百龄坛</router-link></span>
                  </div>
                </div>
                <div class="menudown-item-content">
                  <div style="width: 100%;border: 1px solid #d9d9d9;" class="searchItem">
                    <div>
                      <div class="fl" style="width: 98px;">
                        <span style="color: #CF010E;text-align: center">品牌</span>
                      </div>
                    </div>
                    <div class="fl" style="width: 800px;background-color: rgb(255,255,255);">
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=人头马'">人头马</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=百加得'">百加得</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=轩尼诗'">轩尼诗</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=马爹利'">马爹利</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=芝华士'">芝华士</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=深蓝'">深蓝</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=真露'">真露</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=绝对'">绝对</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=百龄坛'">百龄坛</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=尊尼获加'">尊尼获加</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=杰克丹尼'">杰克丹尼</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=红广场'">红广场</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=卡慕'">卡慕</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=百人城'">百人城</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=拿破仑'">拿破仑</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=百利'">百利</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=帝龙'">帝龙</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=玛丽'">玛丽</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=獭祭'">獭祭</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=RIO'">RIO</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=水晶头骨'">水晶头骨</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=国王路易'">国王路易</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=格兰菲迪'">格兰菲迪</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=波士'">波士</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=布赫拉迪'">布赫拉迪</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=麦卡伦'">麦卡伦</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=派斯顿'">派斯顿</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=帝王'">帝王</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=东名驰'">东名驰</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=馥莱俐'">馥莱俐</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=必得利'">必得利</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=君度'">君度</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=TAKI鸡尾酒'">TAKI鸡尾酒</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=路易老爷'">路易老爷</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=康沛尼'">康沛尼</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=皇家礼炮'">皇家礼炮</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=斯米诺'">斯米诺</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=伏特加'">伏特加</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=雷米诺'">雷米诺</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=豪帅'">豪帅</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=路易马西尼'">路易马西尼</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=马谛氏'">马谛氏</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=冰凝'">冰凝</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=泰斯卡'">泰斯卡</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=皇家'">皇家</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=高朗'">高朗</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=诗贝'">诗贝</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=洋酒城'">洋酒城</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=皇家贝斯'">皇家贝斯</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=苏格登'">苏格登</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=鹰勇'">鹰勇</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=红方'">红方</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=旗舰'">旗舰</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=米锐'">米锐</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=韩国乐天'">韩国乐天</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=圣雷米'">圣雷米</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=俄罗斯酒业'">俄罗斯酒业</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=苏连红'">苏连红</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=拉弗格'">拉弗格</router-link></span>
                      <span><router-link  target="_blank"  :to="'/goods/54-0'+'?search=香奈'">香奈</router-link></span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="menudown-item">
                <div class="item">
                  △ <span><router-link :to="'/goods/57-0'">啤酒</router-link></span> /
                  <span><router-link :to="'/goods/51-0'">黄酒</router-link></span> /
                  <span><router-link :to="'/goods/52-0'">养生酒</router-link></span>

                  <div>
                    <span><router-link :to="'/goods/0-0'+'?search=埃丝伯爵'">埃丝伯爵</router-link></span>
                    <span><router-link :to="'/goods/0-0'+'?search=青岛'">青岛</router-link></span>
                    <span><router-link :to="'/goods/0-0'+'?search=1664'">1664</router-link></span>
                    <span><router-link :to="'/goods/0-0'+'?search=劲牌'">劲牌</router-link></span></div>
                  <div>
                    <span><router-link :to="'/goods/0-0'+'?search=猎鹰'">猎鹰</router-link></span>
                    <span><router-link :to="'/goods/0-0'+'?search=狮虎争霸'">狮虎争霸</router-link></span>
                  </div>
                </div>
                <!--<div class="menudown-item-content">-->
                  <!--洋酒-->
                <!--</div>-->
              </div>
            </div>

          </div>
          <router-link to="/home">
            <button class="btnItem" type="button">首页</button>
          </router-link>
          <router-link to="/favorable">
            <button class="btnItem" type="button">领券优惠</button>
          </router-link>
          <router-link to="/goods/49-0">
            <button class="btnItem" type="button">白酒馆</button>
          </router-link>
          <router-link to="/goods/50-0">
            <button class="btnItem" type="button">葡萄酒馆</button>
          </router-link>
          <router-link to="/goods/54-0">
            <button class="btnItem" type="button">洋酒馆</button>
          </router-link>
          <router-link to="/user">
            <button class="btnItem" type="button">个人中心</button>
          </router-link>
        </div>
      </div>

    </div>
  </div>
</template>
<script>
  import YButton from '/components/YButton'
  import {mapMutations, mapState} from 'vuex'
  import {cartDel, getCartList} from '/api/goods'
  import {loginOut} from '/api'
  import {removeStore, setStore} from '/utils/storage'

  export default {
    props: {
      showNav: {
        default: true,
        type: Boolean
      }
    },
    data () {
      return {
        selectItems: {'58': '收藏酒', '57': '啤酒', '56': '名庄酒', '55': '果酒', '54': '洋酒', '53': '其他酒类', '52': '保健酒', '51': '黄酒', '50': '葡萄酒', '49': '白酒', '0': '全部'},
        user: {},
        // 列表
        navList: [{
          text: '我的订单',
          link: '/user/orderList'
        }, {
          text: '账号资料',
          link: '/user/information'
        }, {
          text: '收货地址',
          link: '/user/addressList'
        }, {
          text: '售后服务',
          link: '/user/support'
        }, {
          text: '我的优惠',
          link: '/user/coupon'
        }],
        st: false,
        selectType: '白酒',
        selectId: 49,
        searchVal: '',
        // 头部购物车显示
        cartShow: false,
        timerCartShow: null // 定时隐藏购物车
      }
    },
    computed: {
      ...mapState([
        'cartList', 'login', 'receiveInCart', 'showCart', 'userInfo'
      ]),
      // 计算价格
      totalPrice () {
        let totalPrice = 0
        this.cartList.length && this.cartList.forEach(item => {
          totalPrice += (item.productNum * item.productPrice)
        })
        return totalPrice
      },
      // 计算数量
      totalNum () {
        let totalNum = 0
        this.cartList.length && this.cartList.forEach(item => {
          totalNum += (item.productNum)
        })
        return totalNum
      }
    },
    methods: {
      ...mapMutations(['ADD_CART', 'INIT_BUYCART', 'ADD_ANIMATION', 'SHOW_CART', 'REDUCE_CART', 'RECORD_USERINFO', 'EDIT_CART']),
      // 购物车显示
      cartShowState (state) {
        this.SHOW_CART({showCart: state})
      },
      // 登陆时获取一次购物车商品
      _getCartList () {
        getCartList().then(res => {
          if (res.status === '1') {
            setStore('buyCart', res.result)
          }
          // 重新初始化一次本地数据
        }).then(this.INIT_BUYCART)
      },
      // 删除商品
      delGoods (productId) {
        if (this.login) { // 登陆了
          cartDel({productId}).then(res => {
            this.EDIT_CART({productId})
          })
        } else {
          this.EDIT_CART({productId})
        }
      },
      toCart () {
        this.$router.push({path: '/cart'})
      },
      // 控制顶部
      navFixed () {
        if (this.$route.path === '/goods' ||
          this.$route.path === '/home' ||
          this.$route.path === '/goodsDetails') {
          // 计算是否吸顶
          if (this.showNav) {
            let st = document.documentElement.scrollTop || document.body.scrollTop
            st >= 100 ? this.st = true : this.st = false
          }
          // 计算小圆当前位置
          // let num = this.$refs.num
          // const {left, top} = num.getBoundingClientRect()
          // this.ADD_ANIMATION({cartPositionL: left, cartPositionT: top})
        }
      },
      // 退出登陆
      _loginOut () {
        loginOut().then(res => {
          removeStore('buyCart')
          window.location.href = '/'
        })
      },
      _selectType (str, id) {
        this.selectType = str
        this.selectId = id
      },
      _search () {
        this.$router.push({path: '/goods/' + this.selectId + '-0?search=' + this.searchVal})
      },
      init () {
        let params = this.$route.params
        if (params.categoryId) {
          if (this.selectItems[params.categoryId] !== undefined) {
            this.selectType = this.selectItems[params.categoryId]
            this.selectId = params.categoryId
          } else {
            this.selectType = this.selectItems['0']
            this.selectId = params.categoryId
          }
        }
      }
    },
    mounted () {
      if (this.login) {
        this._getCartList()
      } else {
        this.INIT_BUYCART()
      }
      setTimeout(() => {
        this.navFixed()
      }, 300)
      window.addEventListener('scroll', this.navFixed)
      window.addEventListener('resize', this.navFixed)
    },
    destroyed () {
      window.removeEventListener('scroll', this.navFixed)
      window.removeEventListener('resize', this.navFixed)
    },
    components: {
      YButton
    },
    created () {
      this.init()
    },
    watch: {
      '$route': 'init'
    }
  }
</script>
