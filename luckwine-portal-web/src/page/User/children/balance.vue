<template>
  <div>
    <y-shelf title="我的余额">
      <div slot="content">
        <div style="padding-left: 30px;padding-bottom: 20px; border-bottom: 1px solid #999999">
          <div style="width: 200px;display: inline-table;text-align: center">
            <span><STRONG>我的余额</STRONG></span><br/>
            <span style="color: red;font-size: 20px"><span>￥</span>0.00</span>
          </div>
          <y-button text="充值" style="margin: 0;margin-top: 20px;" ></y-button>
          <y-button text="提现" style="margin: 0;margin-top: 20px;" ></y-button>
        </div>


        <div>
          <div class="gray-box" style="margin-bottom: 0px;">
            <div class="title">
              <h2>收支明细</h2>
              </div>
            </div>
          <div>
            <table width="100%">
              <tr style="height: 30px;">
                <th>订单号</th>
                <th>时间</th>
                <th>类型</th>
                <th>金额</th>
                <th>备注</th>
              </tr>
            </table>

            <y-pagination :total="total" :current-page='pageNo' :display='pageSize' @pagechange="pagechange"></y-pagination>
          </div>
        </div>
      </div>
    </y-shelf>
  </div>
</template>
<script>
  import YShelf from '/components/shelf'
  import YButton from '/components/YButton'
  import YPagination from '/components/pagination'
  export default {
    components: {
      YShelf,
      YButton,
      YPagination
    },
    data () {
      return {
        total: 1,
        pageNo: 1,
        pageSize: 1
      }
    },
    methods: {
      pagechange (pageNo) {

      }
    }
  }
</script>
