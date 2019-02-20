<style lang="less">
  @import "./spuList.less";
</style>

<template>
  <div class="search">
    <Row>
      <Col>
        <Card>
          <!-- 搜索框 -->
          <Row>
            <Form ref="spuSearchForm" :model="spuSearchForm" inline :label-width="70" class="search-form">
              <Form-item label="商品名称" prop="goodsName">
                <Input type="text" v-model="spuSearchForm.goodsName" clearable placeholder="商品名称"
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
            <Button @click="addSpuModal" type="primary" icon="plus-round">添加</Button>
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


    <Modal  :width="600" :title="modalTitle" v-model="spuModalVisible" :mask-closable='false'>
      <Form ref="addSpuForm" :model="addSpuForm" :label-width="70" :rules="addSpuFormValidate">

        <FormItem label="商品名称" prop="goodsName" style="width: 300px" >
          <Input v-model="addSpuForm.goodsName"/>
        </FormItem>
        <FormItem label="标题" prop="title"  style="width: 300px" >
          <Input v-model="addSpuForm.title"/>
        </FormItem>
        <FormItem label="商品分类" prop="categoryName"  style="width: 300px" >
          <Row>
            <Col span="18">
              <Input disabled v-model="addSpuForm.categoryName"/>
            </Col>
            <Col span="5" offset="1">
              <Button @click="chooseCategory">选择</Button>
            </Col>
          </Row>

        </FormItem>
        <FormItem label="商品属性" prop="props"  style="width: 300px" >
          <Input v-model="addSpuForm.props"/>
        </FormItem>

        <FormItem label="品牌" prop="brandId" style="width: 300px" >
          <Input v-model="addSpuForm.brandId"/>
        </FormItem>
        <FormItem label="图片" prop="picPath"  >
          <Input v-model="addSpuForm.picPath"/>
        </FormItem>
        <Form-item label="状态" prop="spuStatus" style="width: 300px">
          <Select v-model="addSpuForm.spuStatus" placeholder="请选择" clearable  >
            <Option value="ON_SALE">出售中</Option>
            <Option value="IN_STOCK">仓库中</Option>
          </Select>
        </Form-item>

        <div v-for="(item ,index) in this.addSpuForm.skus">
          <card style="width: 360px">
            <FormItem  :label="'sku名称'+index"
                        :prop="'skus.' + index + '.skuName' "
                        :rules="{required: true, message: 'sku名称'+ index +'不能为空', trigger: 'blur'}">
              <Input v-model="item.skuName"/>
            </FormItem>

            <FormItem   :label="'sku属性'+index"
                      :prop="'skus.' + index + '.props' "
                      :rules="{required: true, message: 'sku属性'+ index +'不能为空', trigger: 'blur'}">
              <Input v-model="item.props"/>
            </FormItem>

            <FormItem :label="'sku价格'+index"
                      :prop="'skus.' + index +'.price' "
                      :rules="{required: true, message: 'sku价格'+ index +'不能为空', trigger: 'blur'}" >
              <Input v-model="item.price"/>
            </FormItem>

            <FormItem :label="'sku库存'+index"
                      :prop="'skus.' + index + '.quantity' "
                      :rules="{required: true, message: 'sku库存'+ index +'不能为空', trigger: 'blur'}"
                      >
              <Input v-model="item.quantity"/>
            </FormItem>
          </card>
        </div>

        <FormItem style="width: 400px">
          <Button type="primary" @click="addSku">添加规格</Button>
        </FormItem>


        <FormItem label="详情">
          <Input v-model="addSpuForm.detail" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                 placeholder="商品详情"></Input>
        </FormItem>

      </Form>
      <div slot="footer">
        <Button type="text" @click="cancelAddSpuForm">取消</Button>
        <Button type="primary" :loading="submitLoading" @click="submitAddSpuForm">提交</Button>
      </div>
    </Modal>


    <Modal :title="modalTitle" v-model="categoryModalVisible" :mask-closable='false'>

      <Col>
        <Card>
          <!-- 列表显示 -->
          <Row class="margin-top-10 searchable-table-con1">
            <Table border :columns="categoryData.columns" :data="categoryData.data"
                   @on-row-click="submitCategory" ref="table"></Table>
          </Row>
        </Card>
      </Col>

    </Modal>


    <Modal :title="modalTitle" v-model="editSpuModalVisible" :mask-closable='false'>
      <div>{{spuDetail.spu}}</div>

      <div v-for="item in spuDetail.skus">
        <hr>
        <div> {{item}}</div>
      </div>
    </Modal>

  </div>
</template>


<script>
  const pageNo = 1;   //当前页
  const pageSize = 10;   //一页大小
  export default {
    name: "spu-manage",
    data() {

      return {
        categoryData: {
          columns: [
            {
              title: "分类名称",
              key: "categoryName"
            }
          ],
          data: []
        },

        //load图
        loading: false,
        selectList: [],
        selectCount: 0,
        page: {
          pageNo: pageNo,
          pageSize: pageSize
        },
        spuSearchForm: {
          goodsName: "",
        },
        spuModalVisible: false,
        categoryModalVisible: false,
        editSpuModalVisible: false,
        modalTitle: "",
        spuDetail: {},
        addSpuForm: {
          goodsName: "",
          categoryId: "",
          categoryName: "",
          brandId: "",
          title: "",
          detail: "",
          picPath: "https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/goods/aaa.jpg",
          props: "",
          skus: [],
          spuStatus: ""
        },
        addSpuFormValidate: {
          goodsName: [{required: true, message: "商品名称不能为空", trigger: "blur"}],
          brandId: [{required: true, message: "请选择品牌", trigger: "blur"}],
          categoryId: [{required: true, message: "请选择分类", trigger: "blur"}],
          categoryName: [{required: true, message: "请选择分类", trigger: "blur"}],
          picPath: [{required: true, message: "请上传图片", trigger: "blur"}],
          spuStatus: [{required: true, message: "请选择状态", trigger: "blur"}],
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
            render: (h, params) => {
              return h('div', params.row.spu.id)
            }
          },
          {
            title: "名称",
            render: (h, params) => {
              return h('div', params.row.spu.goodsName)
            }
          },
          {
            title: "图片",
            width: 100,
            render: (h, params) => {
              return h("img", {
                domProps: {
                  src: params.row.spu.picPath + "?x-oss-process=image/resize,m_fixed,h_80,w_80",
                  style: 'width:80px;height: 80px;padding-right:18px;padding-top: 10px;'
                }
              });
            }
          },
          {
            title: "分类",
            key: "categoryName"
          },
          {
            title: "品牌",
            key: "brandName"
          },
          {
            title: "属性",
            render: (h, params) => {
              return h('div', params.row.spu.propsStr)
            }
          },
          {
            title: "创建时间",
            width: 150,
            render: (h, params) => {
              let re = this.DateTimeUtils().dateTimeFormat(params.row.spu.createTime, "yyyy-MM-dd HH:mm:ss");
              return h("div", re);
            }
          },
          {
            title: "更新时间",
            width: 150,
            render: (h, params) => {
              let re = this.DateTimeUtils().dateTimeFormat(params.row.spu.updateTime, "yyyy-MM-dd HH:mm:ss");
              return h("div", re);
            }
          },
          {
            title: "状态",
            width: 150,
            key: "spu.status",
            render: (h, params) => {
              let re = "未知";
              if (params.row.spu.status == "ON_SALE") {
                return h("div", [
                  h(
                    "Tag",
                    {
                      props: {
                        type: "dot",
                        color: "green"
                      }
                    },
                    "出售中"
                  )
                ]);
              } else if (params.row.spu.status == "IN_STOCK") {
                return h("div", [
                  h(
                    "Tag",
                    {
                      props: {
                        type: "dot",
                        color: "yellow"
                      }
                    },
                    "仓库中"
                  )
                ]);
              }
              return h("div", re);
            }
          },
          {
            title: "操作",
            width: 200,
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
              ,  h(
                  "Button",
                  {
                    props: {
                      type: "success",
                      size: "small"
                    },
                    style: {
                      marginRight: "5px"
                    },
                    on: {
                      click: () => {
                        this.syncEs(params.row);
                      }
                    }
                  },
                  "同步ES"
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
        this.getSpuList();
      },
      changePage(v) {
        this.page.pageNo = v;
        this.getSpuList();
      },
      changePageSize(v) {
        this.page.pageSize = v;
        this.getSpuList();
      },
      chooseCategory() {
        this.getCategoryList();
        this.modalTitle = "选择分类";
        this.categoryModalVisible = true;
      },
      submitCategory(e, index) {
        this.addSpuForm.categoryId = e.id;
        this.addSpuForm.categoryName = e.categoryName;
        this.categoryModalVisible = false;
      },
      handleAdd() {
        this.index++;
        this.addSpuForm.items.push({
          value: '',
          index: this.index,
          status: 1
        });
      },
      handleRemove(index) {
        this.addSpuForm.items[index].status = 0;
      },
      getSpuList() {
        this.loading = true;
        //调用服务端的账户查询接口
        this.postJsonRequest("/goods/spu/page", {
          pageNo: this.page.pageNo,
          pageSize: this.page.pageSize,
          request: this.spuSearchForm
        }).then(res => {
          this.loading = false;
          if (res.success === true) {
            this.data = res.result.response;
            this.total = res.result.totalCount;
          }
        });
      },
      getCategoryList() {
        this.postJsonRequest("/goods/category/page", {
          pageNo: this.page.pageNo,
          pageSize: this.page.pageSize,
          request: {}
        }).then(res => {
          if (res.success === true) {
            this.categoryData.data = res.result.response;
          }
        });
      },

      handleSearch() {
        this.page.pageNo = pageNo;
        this.page.pageSize = pageSize;
        this.init();
      },
      handleReset() {
        this.$refs.spuSearchForm.resetFields();
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
      look: function (row) {
        alert('todo')
      },
      syncEs:function(row) {

        this.postJsonRequest("/goods/sku/syncEs", {
          request: {spuId: row.spu.id}
        }).then(res => {
          if (res.success === true) {
            this.$Message.success(res.result.content);
          }
        });
      },
      edit: function (row) {
        this.getSpuDetail(row.spu.id);
        this.modalTitle = "编辑商品";
        this.editSpuModalVisible = true;
      },

      getSpuDetail(id) {
        this.postJsonRequest("/goods/spu/detail", {
          request: {id: id}
        }).then(res => {
          if (res.success === true) {
            this.spuDetail = res.result.response;
          }
        });
      },

      addSpuModal() {
        this.modalTitle = "添加商品";
        this.$refs.addSpuForm.resetFields();
        this.spuModalVisible = true;
        this.addSpuForm.skus = [{}]
      },


      cancelAddSpuForm() {
        this.spuModalVisible = false;
      },

      submitAddSpuForm() {
        this.$refs.addSpuForm.validate(
          valid => {
            if (valid) {

              this.submitLoading = true;
              this.postJsonRequest('/goods/spu/save', {
                request: this.addSpuForm
              }).then(res => {
                this.submitLoading = false;
                if (res.success === true && res.result.code == '000000') {
                  this.$Message.success(res.result.content);
                  this.init();
                  this.spuModalVisible = false;
                } else {
                  this.$Message.error(res.result.content)
                }
              });
            }
          }
        )
      },
      addSku() {
        this.addSpuForm.skus.push({
          props: "",
          quantity: "",
          price: "",
        })
      }
    },
    mounted() {
      this.init();
    }
  };
</script>


