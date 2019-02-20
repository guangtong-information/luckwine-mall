<template>
    
</template>

<template>
  <div class="search">
    <Row>
      <Col>
        <Card>
          <!-- 搜索框 -->
          <Row>
            <Form ref="esSearchForm" :model="esSearchForm" inline :label-width="70" class="search-form">
              <Form-item label="商品名称" prop="goodsName">
                <Input type="text" v-model="esSearchForm.goodsName" clearable placeholder="商品名称"
                       style="width: 200px"/>
              </Form-item>
              <Form-item label="品牌id" prop="brandId">
                <Input type="text" v-model="esSearchForm.brandId" clearable placeholder="品牌id"
                       style="width: 200px"/>
              </Form-item>
              <Form-item style="margin-left:-35px;">
                <Button @click="handleSearch" type="primary" icon="search">搜索</Button>
                <Button @click="handleReset" type="ghost">重置</Button>
              </Form-item>
            </Form>
          </Row>

          <Row>
            <Alert show-icon>
              已选择 <span class="select-count">{{selectCount}}</span> 项
              <a class="select-clear" @click="clearSelectAll">清空</a>
            </Alert>
          </Row>
          <!-- 列表显示 -->
          <Row class="margin-top-10 searchable-table-con1">
            <Table :loading="loading" border :columns="columns" :data="data"
                   @on-selection-change="showSelect" ref="table"></Table>
          </Row>
          <!-- 翻页 -->
          <Row type="flex" justify="end" class="code-row-bg page">
            <Page :current="this.page.pageNo" :total="total" :page-size="this.page.pageSize"
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
  const pageSize = 10;   //一页大小
  export default {
    name: "category-manage",
    data() {

      return {
        //load图
        loading: false,
        selectList: [],
        selectCount: 0,
        page :{
          pageNo : pageNo,
          pageSize : pageSize
        },
        esSearchForm: {
          goodsName: "" ,
          brandId:""
        },
        categoryModalVisible: false,
        modalTitle: "",


        submitLoading: false,
        //table列名
        columns: [
          {
            type: "selection",
            width: 60,
            align: "center"
          },
          {
            title: "skuId",
            key: "skuId",
          },
          {
            title: "brandId",
            key: "brandId",
          },
          {
            title: "sku名称",
            key: "skuName",
          },{
            title :"data",
             render: (h, params) => {
              return h("div", JSON.stringify(params.row));
            }
          }
        ],
        //填充table列表数据
        data: [],
        //数据总条数
        total: 0
      };
    },
    methods: {
      init() {
        this.getEsList();
      },
      changePage(v) {
        this.page.pageNo = v;
        this.getEsList();
      },
      changePageSize(v) {
        this.page.pageSize = v;
        this.getEsList();
      },
      getEsList() {
        this.loading = true;
        this.postJsonRequest("/goods/sku/search", {
          pageNo: this.page.pageNo,
          pageSize: this.page.pageSize,
          request: this.esSearchForm
        }).then(res => {
          this.loading = false;
          if (res.success === true) {
            this.data = res.result.response;
            this.total = res.result.totalCount;
          }
        });
      },
      handleSearch() {
        this.page.pageNo = pageNo;
        this.page.pageSize = pageSize;
        this.init();
      },
      handleReset() {
        this.$refs.esSearchForm.resetFields();
        this.page.pageNo = pageNo;
        this.page.pageSize = pageSize;
        this.init();
      },
      clearSelectAll() {
        this.$refs.table.selectAll(false);
      },
      showSelect(e) {
        this.selectList = e;
        this.selectCount = e.length;
      }
    },
    mounted() {
      this.init();
    }
  };
</script>


