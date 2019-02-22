<template>
  <div class="gray-box">
    <div class="title">
      <h2>{{title}}</h2>
      <div>
        <slot name="right"></slot>
      </div>
    </div>
    <!--内容-->
    <div>
      <slot name="content"></slot>
    </div>
  </div>
</template>
<script>
  export default {
    props: [
      'title'
    ]
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .gray-box {
    position: relative;
    margin-bottom: 30px;
    overflow: hidden;
    background: #fff;
    border-radius: 0px;
    border: 1px solid #dcdcdc;
    border-color: rgba(0, 0, 0, .14);
    box-shadow: 0 3px 8px -6px rgba(0, 0, 0, .1);
    .title {
      padding-left: 30px;
      position: relative;
      z-index: 10;
      height: 56px;
      padding: 0 10px 0 24px;
      border-bottom: 3px solid #c81400;
      border-radius: 0px;
      line-height: 30px;
      font-size: 18px;
      color: #333;
      display: flex;
      justify-content: space-between;
      align-items: center;
      h2 {
        font-size: 18px;
        font-weight: 400;
        color: #626262;
        display: inline-block;
        height: 30px;
        padding-left: 15px;
        border-left: 3px solid #c81400;
      }
    }
  }
</style>
