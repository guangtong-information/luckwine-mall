<template>
  <div class="favorable">
    <div  v-bind:style="imageUrl" class="wrapper">
    </div>
  </div>
</template>

<script>
  import {queryAdContentS} from '/api'

  export default {
    name: 'favorable',
    data () {
      return {
        banners: [],
        imageUrl: {background: 'url(/static/images/u16599.png) no-repeat center top', 'background-size': '100% 100%'}
      }
    },
    methods: {
      init: function () {
        queryAdContentS({'pageId': '8'}).then(res => {
          this.banners = res.data.result
          this.imageUrl = {
            background: 'url(' + this.banners['100013'][0]['imageUrl'] + ') no-repeat center top',
            'background-size': '100% 100%'
          }
          console.log(this.imageUrl)
        })
      }
    },
    created () {
      this.init()
    }
  }
</script>

<style scoped>
  .wrapper {
    /*background: url(/static/images/u16599.png)  no-repeat center top;*/
    background-size: 100% 100%;
    height: 600px;
  }
</style>
