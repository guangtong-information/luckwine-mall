<style lang="less">
  @import "acctList.less";
</style>
<template>
  <div class="search">
    <Row>
      <Col>
        <Card>
          <!-- 搜索框 -->
          <Row>
            <Form ref="acctSearchForm" :model="acctSearchForm" inline :label-width="70" class="search-form">
              <Form-item label="账户号" prop="acctCode">
                <Input type="text" v-model="acctSearchForm.acctCode" clearable placeholder="请输入账户号"
                       style="width: 200px"/>
              </Form-item>
              <Form-item label="账户名称" prop="acctName">
                <Input type="text" v-model="acctSearchForm.acctName" clearable placeholder="请输入账户名"
                       style="width: 200px"/>
              </Form-item>
              <Form-item label="登录号" prop="loginAccount">
                <Input type="text" v-model="acctSearchForm.loginAccount" clearable placeholder="请输入账户名"
                       style="width: 200px"/>
              </Form-item>
              <span v-if="drop">
                              <Form-item label="账户能力" prop="abilityCode">
                                <Select v-model="acctSearchForm.abilityCode" placeholder="请选择" clearable
                                        style="width: 200px">
                                  <Option value="">全部</Option>
                                  <Option value="0001">消费</Option>
                                  <Option value="0003">充值</Option>
                                  <Option value="0002">提现</Option>
                                </Select>
                              </Form-item>

                              <Form-item label="状态" prop="stat">
                                <Select v-model="acctSearchForm.stat" placeholder="请选择" clearable style="width: 200px">
                                  <Option value="">全部</Option>
                                  <Option value="00">可用</Option>
                                  <Option value="01">冻结</Option>
                                  <Option value="02">销户</Option>
                                </Select>
                              </Form-item>

                              <Form-item label="开户时间" prop="status">
                                <DatePicker type="daterange" format="yyyy-MM-dd" clearable @on-change="selectDateRange"
                                            placeholder="选择起始时间" style="width: 200px"></DatePicker>
                              </Form-item>
                            </span>
              <Form-item style="margin-left:-35px;">
                <Button @click="handleSearch" type="primary" icon="search">搜索</Button>
                <Button @click="handleReset" type="ghost">重置</Button>
                <a class="drop-down" @click="dropDown">{{dropDownContent}}
                  <Icon :type="dropDownIcon"></Icon>
                </a>
              </Form-item>
            </Form>
          </Row>
          <!-- 操作按钮 -->
          <Row class="operation">
            <Button @click="freezeAll" type="primary" icon="plus-round">冻 结</Button>
            <Button @click="unfreezeAll" type="primary" icon="plus-round">解 冻</Button>
            <Button @click="configAbility" type="primary" icon="plus-round">能力配置</Button>

            <Dropdown @on-click="handleDropdown">
              <Button type="ghost">
                更多操作
                <Icon type="arrow-down-b"></Icon>
              </Button>
              <DropdownMenu slot="list">
                <DropdownItem name="refresh">刷新</DropdownItem>
                <DropdownItem name="exportData">导出所选数据</DropdownItem>
              </DropdownMenu>
            </Dropdown>
          </Row>
          <Row>
            <Alert show-icon>
              已选择 <span class="select-count">{{selectCount}}</span> 项
              <a class="select-clear" @click="clearSelectAll">清空</a>
            </Alert>
          </Row>
          <!-- 列表显示 -->
          <Row class="margin-top-10 searchable-table-con1">
            <Table :loading="loading" border :columns="columns" :data="data" sortable="acct"
                   @on-sort-change="changeSort" @on-selection-change="showSelect" ref="table"></Table>
            <Table :columns="columns" :data="exportData" ref="exportTable" style="display:none"></Table>
          </Row>
          <!-- 翻页 -->
          <Row type="flex" justify="end" class="code-row-bg page">
            <Page :current="this.acctSearchForm.pageNo" :total="total" :page-size="this.acctSearchForm.pageSize"
                  @on-change="changePage" @on-page-size-change="changePageSize" :page-size-opts="[10,20,50,100]"
                  size="small" show-total show-elevator show-sizer></Page>
          </Row>
        </Card>
      </Col>
    </Row>
    <!-- 账户能力配置弹框 -->
    <Modal :title="modalTitle" v-model="abilityModalVisible" :mask-closable='false' :width="500">
      <Form ref="abilityForm" :model="abilityForm" :label-width="70" :rules="abilityFormValidate">
        <FormItem label="选择能力" prop="abilityCodeList">
          <CheckboxGroup v-model="abilityForm.abilityCodeList">
            <Checkbox label="0001">消费</Checkbox>
            <Checkbox label="0002">充值</Checkbox>
            <Checkbox label="0003">提现</Checkbox>
          </CheckboxGroup>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancelAbilityForm">取消</Button>
        <Button type="primary" :loading="submitLoading" @click="addAbility">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  const pageNo = 1;   //当前页
  const pageSize = 5;   //一页大小
  export default {
    name: "acct-manage",
    data() {

      return {
        //load图
        loading: true,
        //搜索的展开按钮
        drop: false,
        dropDownContent: "展开",
        dropDownIcon: "chevron-down",
        selectCount: 0,
        //table多选按钮
        selectList: [],
        //搜索框
        acctSearchForm: {
          acctCode: "",
          acctName: "",
          loginAccount: "",
          stat: "",
          createTimeStart: "",
          createTimeEnd: "",
          abilityCode: "",

          pageNo: pageNo,
          pageSize: pageSize,
          sort: "createTime",
          order: "desc",
          status: ""
        },
        //账户能力配置弹框显示与否
        abilityModalVisible: false,
        //弹框标题
        modalTitle: "",
        //账户能力配置弹框里面的提交表单
        abilityForm: {
          abilityCodeList: [],
          acctCodeList: []
        },
        userRoles: [],
        roleList: [],
        errorPass: "",
        //账户能力弹框表单验证
        abilityFormValidate: {},
        //账户能力提交loading
        submitLoading: false,
        //table列名
        columns: [
          {
            type: "selection",
            width: 60,
            align: "center"
          },
          {
            title: "账户号",
            key: "acctCode",
            width: 220
          },
          {
            title: "账户名称",
            key: "acctName",
            width: 100
          },
          {
            title: "登录号",
            key: "loginAccount",
            width: 150
          },
          {
            title: "账户类型",
            key: "acctTypeCode",
            width: 100,
            render: (h, params) => {
              let re = "未知";
              if (params.row.acctTypeCode == "0001") {
                re = "余额账号";
              }
              return h("div", re);
            }
          },
          {
            title: "账户能力",
            key: "email",
            width: 100
          },
          {
            title: "余额",
            key: "availBal",
            width: 100,
            sortable: true,
            render: (h, params) => {
              let re = params.row.availBal.toFixed(2);
              return h("div", re);
            }
          },
          {
            title: "状态",
            key: "stat",
            width: 130,
            render: (h, params) => {
              let re = "未知";
              if (params.row.stat == "00") {
                return h("div", [
                  h(
                    "Tag",
                    {
                      props: {
                        type: "dot",
                        color: "green"
                      }
                    },
                    "可用"
                  )
                ]);
              } else if (params.row.stat == "01") {
                return h("div", [
                  h(
                    "Tag",
                    {
                      props: {
                        type: "dot",
                        color: "yellow"
                      }
                    },
                    "冻结"
                  )
                ]);
              } else if (params.row.stat == "02") {
                return h("div", [
                  h(
                    "Tag",
                    {
                      props: {
                        type: "dot",
                        color: "red"
                      }
                    },
                    "销户"
                  )
                ]);
              }
              return h("div", re);
            }
          },
          {
            title: "开户时间",
            key: "createTime",
            width: 160,
            sortable: true,
            sortType: "desc",
            render: (h, params) => {
              let re = this.DateTimeUtils().dateTimeFormat(params.row.createTime, "yyyy-MM-dd HH:mm:ss");
              return h("div", re);
            }
          }
        ],
        //填充table列表数据
        data: [],
        //导出表格数据
        exportData: [],
        //数据总条数
        total: 0
      };
    },
    methods: {
      //页面初始化,账户数据
      init() {
        this.getAcctList();
      },
      //更换页数
      changePage(v) {
        this.acctSearchForm.pageNo = v;
        this.getAcctList();
      },
      //更换一页大小
      changePageSize(v) {
        this.acctSearchForm.pageSize = v;
        this.getAcctList();
      },
      //时间控件选择日期填充
      selectDateRange(v) {
        if (v) {
          this.acctSearchForm.createTimeStart = v[0];
          this.acctSearchForm.createTimeEnd = v[1];
        }
      },
      //调用服务端账户查询接口
      getAcctList() {
        this.loading = true;
        //调用服务端的账户查询接口
        this.postRequest("/acct/info/queryList", this.acctSearchForm).then(res => {
          this.loading = false;
          if (res.success === true) {
            this.data = res.result.response;
            this.total = res.result.totalCount;
          }
        });
      },
      //搜索账户列表数据
      handleSearch() {
        this.acctSearchForm.pageNo = pageNo;
        this.acctSearchForm.pageSize = pageSize;
        this.init();
      },
      //重置搜索
      handleReset() {
        this.$refs.acctSearchForm.resetFields();
        this.acctSearchForm.pageNo = pageNo;
        this.acctSearchForm.pageSize = pageSize;
        this.init();
      },
      //排序操作
      changeSort(e) {
        this.acctSearchForm.sort = e.key;
        this.acctSearchForm.order = e.order;
        if (e.order === "normal") {
          this.acctSearchForm.order = "";
        }
        this.init();
      },
      //获取登录账户的角色
      getRoleList() {
        this.getRequest("/role/getAllList").then(res => {
          if (res.success === true) {
            this.roleList = res.result;
          }
        });
      },
      //更多操作的下拉按钮
      handleDropdown(name) {
        if (name === "exportData") {
          if (this.selectCount <= 0) {
            this.$Message.warning("您还未选择要导出的数据");
            return;
          }
          this.$Modal.confirm({
            title: "确认导出",
            content: "您确认要导出所选 " + this.selectCount + " 条数据?",
            onOk: () => {
              this.$refs.exportTable.exportCsv({
                filename: "用户数据"
              });
            }
          });
        } else if (name === "refresh") {
          this.getAcctList();
        }
      },
      selectRoles(v) {
      },
      //账户能力配置弹框
      configAbility() {
        this.modalTitle = "账户能力";
        this.$refs.abilityForm.resetFields();
        this.abilityModalVisible = true;
      },
      //取消能力配置弹框
      cancelAbilityForm() {
        this.abilityModalVisible = false;
      },
      //提交能力配置数据
      addAbility() {
        this.$refs.abilityForm.validate(valid => {
          if (valid) {
            let url = "/acct/manage/acctAbilityBatchConfig";

            //debugger;
            //校验选择的账户
            if (this.selectCount <= 0) {
              this.$Message.warning("您还未选择要配置的账户");
              return;
            }
            //校验选择的配置能力
            if (this.abilityForm.abilityCodeList.length <= 0) {
              this.$Message.warning("您还未选择要配置的能力");
              return;
            }
            //清空选择账户数据
            this.abilityForm.acctCodeList = [];
            //把选择的账户合并到弹框提交表单对象
            for (let i = 0; i < this.selectList.length; i++) {
              this.abilityForm.acctCodeList[this.abilityForm.acctCodeList.length] = this.selectList[i]['acctCode'];
            }

            //提交账户能力配置
            this.submitLoading = true;
            this.postRequest(url, this.abilityForm).then(res => {
              this.submitLoading = false;
              if (res.success === true) {
                this.$Message.success("操作成功");
                this.init();
                this.abilityModalVisible = false;
              }
            });
          }
        });
      },
      //搜索下拉展开
      dropDown() {
        if (this.drop) {
          this.dropDownContent = "展开";
          this.dropDownIcon = "chevron-down";
        } else {
          this.dropDownContent = "收起";
          this.dropDownIcon = "chevron-up";
        }
        this.drop = !this.drop;
      },
      //选择列表多选按钮
      showSelect(e) {
        this.exportData = e;
        this.selectList = e;
        this.selectCount = e.length;
      },
      //清空多选按钮
      clearSelectAll() {
        this.$refs.table.selectAll(false);
      },
      //冻结账户
      freezeAll() {
        if (this.selectCount <= 0) {
          this.$Message.warning("您还未选择要冻结的账户");
          return;
        }
        this.$Modal.confirm({
          title: "确认冻结",
          content: "您确认要冻结所选的 " + this.selectCount + " 条数据?",
          onOk: () => {
            let ids = "";
            this.selectList.forEach(function (e) {
              ids += e.id + ",";
            });
            ids = ids.substring(0, ids.length - 1);
            this.$Message.success("冻结成功");
            // this.deleteRequest("/user/", {ids: ids}).then(res => {
            //   if (res.success === true) {
            //     this.$Message.success("冻结成功");
            //     this.init();
            //   }
            // });
          }
        });
      },
      //解冻账户
      unfreezeAll() {
        if (this.selectCount <= 0) {
          this.$Message.warning("您还未选择要解冻的账户");
          return;
        }
        this.$Modal.confirm({
          title: "确认解冻",
          content: "您确认要解冻所选的 " + this.selectCount + " 条数据?",
          onOk: () => {
            let ids = "";
            this.selectList.forEach(function (e) {
              ids += e.id + ",";
            });
            ids = ids.substring(0, ids.length - 1);
            this.$Message.success("解冻成功");
            // this.deleteRequest("/user/", {ids: ids}).then(res => {
            //   if (res.success === true) {
            //     this.$Message.success("解冻成功");
            //     this.init();
            //   }
            // });
          }
        });
      }
    },
    mounted() {
      this.init();
      this.getRoleList();
    }
  };
</script>
