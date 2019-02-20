<style lang="less">
  @import "smsLogList.less";
</style>

<template>

  <div class="adModuleList">
    <Row>
      <Col>
        <Card>
          <!-- 搜索框-->
          <Row>
            <Form ref="smsLogSearchForm" :model="smsLogSearchForm" inline :label-width="70" class="search-form">
              <Form-item label="模板编号:" prop="smsId">
                <Input type="text" v-model="smsLogSearchForm.smsId" clearable placeholder="请输入短信模板编号"  style="width: 200px"/>
              </Form-item>

              <Form-item label="手机号:" prop="mobile" >
                <Input type="text" v-model="smsLogSearchForm.mobile" clearable placeholder="请输入手机号"  style="width: 200px"/>
              </Form-item>

              <Form-item label="响应描述:" prop="resultMsg" >
                <Input type="text" v-model="smsLogSearchForm.resultMsg" clearable placeholder="请输入返回响应描述"  style="width: 200px"/>
              </Form-item>

              <Form-item label="发送时间:" prop="createTime" >
                <DatePicker type="daterange" format="yyyy-MM-dd" clearable @on-change="selectDateRange" placeholder="选择起始时间" style="width: 200px"></DatePicker>
              </Form-item>

              <Form-item style="margin-left:-35px;">
                <Button @click="handleSearch" type="primary" icon="search">搜索</Button>
                <Button @click="handleReset" type="ghost">重置</Button>
              </Form-item>
            </Form>
          </Row>

          <!-- 列表显示-->
          <Row class="margin-top-10 searchable-table-con1">
            <Table :loading="loading" border :columns="columns" :data="data" ref="table"></Table>
          </Row>

          <!-- 翻页-->
          <Row type="flex" justify="end" class="code-row-bg page">
            <Page :current="this.smsLogSearchForm.pageNo" :total="total" :page-size="this.smsLogSearchForm.pageSize"
                  @on-change="changePage" @on-page-size-change="changePageSize" :page-size-opts="[10,20,50,100]"
                  size="small" show-total show-elevator show-sizer></Page>
          </Row>

        </Card>
      </Col>
    </Row>

  </div>


</template>

<script>
   import { getStore } from "../../../utils/storage";

    export default {

      name: "smsLogList",

      data() {
          return {
            //load图
            loading: false,

            //搜索框
            smsLogSearchForm:{
              smsId:"",
              mobile:"",
              resultMsg:"",
              createTimeStart:"",
              createTimeEnd:"",

              //当前页
              pageNo: 1,
              //一页大小
              pageSize: 5,
            },

            //table列名
            columns: [
              /*{
                title: "编号",
                key: "id",
                width: 80
              },*/
              {
                title: "短信模板编号",
                key: "smsId",
                width: 150
              },
              {
                title: "手机号（用户登录号）",
                key: "mobile",
                width: 220
              },
              {
                title: "短信发送内容",
                key: "content",
                width: 220
              },
              {
                title: "短信供应商-返回响应码",
                key: "resultCode",
                width: 220
              },
              {
                title: "短信供应商-返回响应描述",
                key: "resultMsg",
                width: 220
              },
              {
                title: "发送时间",
                key: "createTime",
                width: 220
              }
            ],

            //填充table列表数据
            data: [],

            //数据总条数
            total: 0,

          }
        },

      methods:{

          //时间控件选择日期填充
          selectDateRange(v) {
            if (v) {
              this.smsLogSearchForm.createTimeStart = v[0];
              this.smsLogSearchForm.createTimeEnd = v[1];
            }
          },

          // 搜索
          handleSearch() {
            this.smsLogSearchForm.pageNo = 1;
            this.smsLogSearchForm.pageSize = 10;
            this.init();
          },

          // 重置
          handleReset() {

          },

          //页面初始化,账户数据
          init() {
            this.getSmsLogListList();
          },

          //更换页数
          changePage(v) {
            this.smsLogSearchForm.pageNo = v;
            this.getSmsLogListList();
          },

          //更换一页大小
          changePageSize(v) {
            this.smsLogSearchForm.pageSize = v;
            this.getSmsLogListList();
          },

          //调用服务端短信模块查询接口
          getSmsLogListList(){
            this.loading = true;
            //调用服务端的账户查询接口
            this.postRequest("/smslog/info/page", this.smsLogSearchForm).then(res => {
              this.loading = false;
              if (res.success === true) {
                this.data = res.result.response;
                this.total = res.result.totalCount;
              }
            })
          },

        },

      mounted() {
        this.init();
      }

    }
</script>
