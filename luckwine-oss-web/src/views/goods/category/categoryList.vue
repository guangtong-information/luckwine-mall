<style lang="less">
  @import "./categoryList.less";
</style>

<template>
  <div class="search">
    <Row>
      <Col>
        <Card>
          <!-- 搜索框 -->
          <Row>
            <Form ref="categorySearchForm" :model="categorySearchForm" inline :label-width="70" class="search-form">
              <Form-item label="分类名称" prop="categoryName">
                <Input type="text" v-model="categorySearchForm.categoryName" clearable placeholder="分类名称"
                       style="width: 200px"/>
              </Form-item>
              <Form-item style="margin-left:-35px;">
                <Button @click="handleSearch" type="primary" icon="search">搜索</Button>
                <Button @click="handleReset" type="ghost">重置</Button>
              </Form-item>
            </Form>
          </Row>
          <!-- 操作按钮 -->
          <Row class="operation">
            <Button @click="addBrandModal" type="primary" icon="plus-round">添加</Button>
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



    <Modal :title="modalTitle" v-model="categoryModalVisible" :mask-closable='false'   >
      <Form ref="addBrandForm" :model="addBrandForm" :label-width="70" :rules="addBrandFormValidate" >
        <Layout>
          <Content style="background: #ffffff">
            <FormItem label="分类名称"   prop="categoryName"    style="width: 400px">
              <Input v-model="addBrandForm.categoryName"/>
            </FormItem>
          </Content>
        </Layout>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancelAddBrandForm">取消</Button>
        <Button type="primary" :loading="submitLoading" @click="submitAddBrandForm">提交</Button>
      </div>
    </Modal>


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
        categorySearchForm: {
          categoryName: "" ,
        },
        categoryModalVisible: false,
        modalTitle: "",
        addBrandForm: {
          categoryName:"",
        },
        addBrandFormValidate:{
          categoryName: [{required: true, message: "分类名称不能为空", trigger: "blur"}]
        },

        submitLoading: false,
        //table列名
        columns: [
          {
            type: "selection",
            width: 60,
            align: "center"
          },
          {
            title: "id",
            key: "id",
          },
          {
            title: "分类名称",
            key: "categoryName",
          },
          {
            title: "创建时间",
            key: "createTime",
            render: (h, params) => {
              let re = this.DateTimeUtils().dateTimeFormat(params.row.createTime,"yyyy-MM-dd HH:mm:ss");
              return h("div", re);
            }
          },
          {
            title: "更新时间",
            width: 200,
            key: "updateTime",
            render: (h, params) => {
              let re = this.DateTimeUtils().dateTimeFormat(params.row.updateTime,"yyyy-MM-dd HH:mm:ss");
              return h("div", re);
            }
          },
          {
            title: "操作",
            key: "action",
            align: "center",
            render: (h, params) => {
              return h("div", [
                h(
                  "Button",
                  {
                    props: {
                      type: "primary",
                      size: "small"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.look(params.row);
                      }
                    }
                  },
                  "查看"
                ),
                h(
                  "Button",
                  {
                    props: {
                      type: "ghost",
                      size: "small"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.edit(params.row);
                      }
                    }
                  },
                  "编辑"
                )
              ]);
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
        this.getBrandList();
      },
      changePage(v) {
        this.page.pageNo = v;
        this.getBrandList();
      },
      changePageSize(v) {
        this.page.pageSize = v;
        this.getBrandList();
      },
      getBrandList() {
        this.loading = true;
        this.postJsonRequest("/goods/category/page", {
          pageNo: this.page.pageNo,
          pageSize: this.page.pageSize,
          request :this.categorySearchForm
        } ).then(res => {
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
        this.$refs.categorySearchForm.resetFields();
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
      },

      look: function() {
        alert('todo')
      },
      edit: function() {
        alert('todo')
      },

      addBrandModal() {
        this.modalTitle = "添加分类";
        this.$refs.addBrandForm.resetFields();
        this.categoryModalVisible = true;
      },

      cancelAddBrandForm() {
        this.categoryModalVisible = false;
      },

      submitAddBrandForm() {
        this.$refs.addBrandForm.validate(
          valid => {
            if (valid) {
              this.submitLoading = true;
              this.postJsonRequest('/goods/category/save', {
                request :this.addBrandForm
              }).then(res => {
                this.submitLoading = false;
                if (res.success === true && res.result.code == '000000' ) {
                  this.$Message.success(res.result.content);
                  this.init();
                  this.categoryModalVisible = false;
                } else {
                  this.$Message.error(res.result.content)
                }
              });
            }
          }
        )
      }
    },
    mounted() {
      this.init();
    }
  };
</script>


