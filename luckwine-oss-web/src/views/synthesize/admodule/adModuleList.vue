<style lang="less">
  @import "adModuleList.less";
</style>

<template>
  <div class="adModuleList">
    <Row>
      <Col>
        <Card>
          <!--搜索框 -->
          <Row>
            <Form ref="adModuleSearchForm" :model="adModuleSearchForm" inline :label-width="70" class="search-form">

              <Form-item label="所属系统:" prop="systemId">
                <Select v-model="adModuleSearchForm.systemId" placeholder="请选择" clearable style="width: 200px">
                  <Option value="">全部</Option>
                  <Option value="0001">网站PC版</Option>
                </Select>
              </Form-item>

              <Form-item label="所属页面:" prop="pageId">
                <Select v-model="adModuleSearchForm.pageId" placeholder="请选择" clearable style="width: 200px">
                  <Option value="">全部</Option>
                  <Option value="0001">首页</Option>
                  <Option value="0001">子页</Option>
                </Select>
              </Form-item>

              <Form-item label="模块名称:" prop="name" >
                <Input type="text" v-model="adModuleSearchForm.name" clearable placeholder="请输入广告模块名称"  style="width: 200px"/>
              </Form-item>

              <Form-item label="所属状态:" prop="status">
                <Select v-model="adModuleSearchForm.status" placeholder="请选择" clearable style="width: 200px">
                  <Option value="">全部</Option>
                  <Option value="0001">启用</Option>
                  <Option value="0001">禁用</Option>
                </Select>
              </Form-item>

              <Form-item style="margin-left:-35px;">
                <Button @click="handleSearch" type="primary" icon="search">搜索</Button>
                <Button @click="handleReset" type="ghost">重置</Button>
              </Form-item>
            </Form>
          </Row>

          <!-- 列表显示 -->
          <Row class="margin-top-10 searchable-table-con1">
            <Table :loading="loading" border :columns="columns" :data="data" ref="table"></Table>
          </Row>

          <!-- 翻页 -->
          <Row type="flex" justify="end" class="code-row-bg page">
            <Page :current="this.adModuleSearchForm.pageNo" :total="total" :page-size="this.adModuleSearchForm.pageSize"
                  @on-change="changePage" @on-page-size-change="changePageSize" :page-size-opts="[10,20,50,100]"
                  size="small" show-total show-elevator show-sizer></Page>
          </Row>

        </Card>
      </Col>
    </Row>

  </div>

</template>

<script>
    const pageNo = 1;   //当前页
    const pageSize = 5;   //一页大小

    export default {
        name: "adModuleList",
        data(){
          return{
            //load图
            loading: false,

            //搜索框
            adModuleSearchForm:{
              name:"",
              systemId:"",
              pageId:"",
              status:"",

              pageNo: pageNo,
              pageSize: pageSize,
            },

            //table列名
            columns: [
              {
                title: "广告模块名称",
                key: "name",
                width: 220
              },
              {
                title: "编号",
                key: "number",
                width: 220
              },
              {
                title: "所属系统",
                key: "systemId",
                width: 220
              },
              {
                title: "所属页面",
                key: "acctCode",
                width: 220
              },
              {
                title: "页面URL",
                key: "pageId",
                width: 220
              },
              {
                title: "启用状态",
                key: "status",
                width: 220
              },
              {
                title: "备注",
                key: "remarks",
                width: 220
              }
            ],

            //填充table列表数据
            data: [],

            //数据总条数
            total: 0
          }
        },
        methods:{

          //页面初始化,账户数据
          init() {
            this.getAdModuleList();
          },

          //更换页数
          changePage(v) {
            this.adModuleSearchForm.pageNo = v;
            this.getAdModuleList();
          },

          //更换一页大小
          changePageSize(v) {
            this.adModuleSearchForm.pageSize = v;
            this.getAdModuleList();
          },

          //调用服务端广告模块查询接口
          getAdModuleList(){
            this.loading = true;
            //调用服务端的账户查询接口
            this.postRequest("/admodule/info/page", this.adModuleSearchForm).then(res => {
              this.loading = false;
              if (res.success === true) {
                this.data = res.result.response;
                this.total = res.result.totalCount;
              }
            })
          },

          // 搜索
          handleSearch() {
            this.adModuleSearchForm.pageNo = 1;
            this.adModuleSearchForm.pageSize = 10;
            this.init();
          },

          // 重置
          handleReset() {

          },

        },

      mounted() {
        this.init();
      }

    }
</script>
