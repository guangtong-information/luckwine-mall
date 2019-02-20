<style lang="less">
  @import "./userInfo.less";
</style>

<template>
    <div class="user-info">
      <div class="search">
        <Row>
          <Col>
            <Card>
              <Row>
                <Form ref="userInfoForm" :model="userInfoForm" inline :label-width="70" class="search-form">
                  <Form-item label="登陆账号" prop="loginAccount">
                    <Input type="text" v-model="userInfoForm.loginAccount" clearable placeholder="请输入用户名" style="width: 200px"/>
                  </Form-item>
                  <Form-item label="真实姓名" prop="realname">
                    <Input type="text" v-model="userInfoForm.realname" clearable placeholder="请输入手机号" style="width: 200px"/>
                  </Form-item>
                  <Form-item label="身份证" prop="identity">
                    <Input type="text" v-model="userInfoForm.identity" clearable placeholder="请输入手机号" style="width: 200px"/>
                  </Form-item>

                  <FormItem label="客户等级" prop="custLevel">
                    <Select v-model="userInfoForm.custLevel" placeholder="全部" style="width: 200px">
                      <Option :value="0">全部</Option>
                      <Option :value="1">普通用户</Option>
                      <Option :value="2">普通实名</Option>
                    </Select>
                  </FormItem>

                  <Form-item label="注册时间" prop="status">
                    <DatePicker type="daterange" format="yyyy-MM-dd" clearable @on-change="selectDateRange" placeholder="选择起始时间" style="width: 200px"></DatePicker>
                  </Form-item>
                  <Form-item style="margin-left:-35px;">
                    <Button @click="handleSearch" type="primary" icon="search">搜索</Button>
                    <Button @click="handleReset" type="ghost" >重置</Button>
                  </Form-item>
                </Form>
              </Row>
              <Row class="operation">
                <Button @click="addUser" type="primary" icon="plus-round">添加用户</Button>
                <Button @click="delAll" type="ghost" icon="trash-a">批量删除</Button>
              </Row>
              <Row>
                <Alert show-icon>
                  已选择 <span class="select-count">{{selectCount}}</span> 项
                  <a class="select-clear" @click="clearSelectAll">清空</a>
                </Alert>
              </Row>
              <Row class="margin-top-10 searchable-table-con1">
                <Table height="500" :loading="loading" border :columns="columns" :data="data" sortable="custom" @on-sort-change="changeSort" @on-selection-change="showSelect" ref="table"></Table>
                <Table :columns="columns" :data="exportData" ref="exportTable" style="display:none"></Table>
              </Row>
              <Row type="flex" justify="end" class="code-row-bg page">
                <Page :current="this.userInfoForm.pageNo" :total="total" :page-size="this.userInfoForm.pageSize" @on-change="changePage" @on-page-size-change="changePageSize" :page-size-opts="[10,20,50,100]" size="small" show-total show-elevator show-sizer></Page>
              </Row>
            </Card>
          </Col>
        </Row>
      </div>

      <Modal :title="modalTitle" v-model="userInfoModalVisible" :mask-closable='false' :width="800" style="height: 1000px">
        <Form ref="createUserForm" :model="createUserForm" :label-width="70" :rules="createUserFormValidate">
          <Layout>
            <Content style="background: #ffffff">
              <FormItem label="登陆账号" prop="loginAccount" style="width: 400px">
                <Input v-model="createUserForm.loginAccount" maxlength="11" />
              </FormItem>
              <FormItem label="真实姓名" prop="realname" style="width: 400px">
                <Input v-model="createUserForm.realname"/>
              </FormItem>
              <FormItem label="身份证" prop="identity" style="width: 400px">
                <Input v-model="createUserForm.identity"/>
              </FormItem>
              <FormItem label="客户等级" prop="custLevel" style="width: 400px">
                <Select v-model="createUserForm.custLevel" placeholder="普通用户" >
                  <Option :value="1">普通用户</Option>
                  <Option :value="2">普通实名</Option>
                </Select>
              </FormItem>
              <FormItem label="昵称" prop="nickname" style="width: 400px">
                <Input v-model="createUserForm.nickname"/>
              </FormItem>
              <FormItem label="性别" prop="sex">
                <RadioGroup v-model="createUserForm.sex">
                  <Radio :label="1">男</Radio>
                  <Radio :label="2">女</Radio>
                </RadioGroup>
              </FormItem>
              <Form-item label="生日时间" prop="status" style="width: 400px">
                <DatePicker type="date" format="yyyy-MM-dd" clearable @on-change="selectBirthdayDateRange" placeholder="选择起始时间" ></DatePicker>
              </Form-item>
            </Content>

            <Sider hide-trigger style="background: #ffffff">
              <FormItem label="用户头像">
                <div class="upload-list" v-for="item in uploadList" :key="item.url">
                  <template v-if="item.status === 'finished'">
                    <img :src="item.url">
                    <div class="upload-list-cover">
                      <Icon type="ios-eye-outline" @click.native="handleView(item.url)"></Icon>
                      <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
                    </div>
                  </template>
                  <template v-else>
                    <Progress v-if="item.showProgress" :percent="item.percentage" hide-info></Progress>
                  </template>
                </div>
                <Upload
                  ref="upload"
                  :show-upload-list="false"
                  :default-file-list="defaultList"
                  :on-success="handleSuccess"
                  :format="['jpg','jpeg','png']"
                  :max-size="5120"
                  :on-format-error="handleFormatError"
                  :on-exceeded-size="handleMaxSize"
                  :before-upload="handleBeforeUpload"
                  type="drag"
                  action="/oss/upload/file"
                  :headers="accessToken"
                  style="display: inline-block;width:58px;">
                  <div style="width: 58px;height:58px;line-height: 58px;">
                    <Icon type="camera" size="20"></Icon>
                  </div>
                </Upload>
              </FormItem>
            </Sider>

          </Layout>
        </Form>
        <div slot="footer">
          <Button type="text" @click="cancelUser">取消</Button>
          <Button type="primary" :loading="submitLoading" @click="submitUser">提交</Button>
        </div>
      </Modal>
      <Modal title="图片预览" v-model="viewImage">
        <img :src="imgUrl" v-if="viewImage" style="width: 80%;margin: 0 auto;display: block;">
      </Modal>

    </div>
</template>

<script>
    import { getStore } from "../../../utils/storage";
    export default {
        name: "user-info",
        data() {
          const validatePassword = (rule, value, callback) => {
            if (value.length < 6) {
              callback(new Error("密码长度不得小于6位"));
            } else {
              callback();
            }
          };
          const validateMobile = (rule, value, callback) => {
            var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (!reg.test(value)) {
              callback(new Error("手机号格式错误"));
            } else {
              callback();
            }
          };
          return {
            loading: true,
            selectCount: 0,
            selectList: [],
            userInfoModalVisible: false,
            modalTitle: "",
            modalType: "",
            createUserForm: {
              loginAccount: "",
              realname: "",
              nickname: "",
              headerimg: "",
            },
            submitLoading: false,
            createUserFormValidate: {
              loginAccount: [
                { required: true, message: "账号不能为空", trigger: "blur" },
                { validator: validateMobile, trigger: "blur" }
              ],
              mobile: [
                { required: true, message: "手机号不能为空", trigger: "blur" },
                { validator: validateMobile, trigger: "blur" }
              ],
              email: [
                { required: true, message: "请输入邮箱地址" },
                { type: "email", message: "邮箱格式不正确" }
              ]
            },
            defaultList: [
              {
                url: ""
              }
            ],
            uploadList: [],
            ossUrlPath: "",
            viewImage: false,
            imgUrl: "",
            accessToken: {},
            userInfoForm: {
              loginAccount: "",
              realname: "",
              identity: "",
              custLevel: 0,
              createStartDate: "",
              createEndDate: "",
              pageNo:1,
              pageSize:10,
              status:""
            },
            exportData: [],
            total: 0,
            columns: [
              {
                type: "selection",
                width: 60,
                align: "center"
              },
              {
                title: "登陆账号",
                key: "loginAccount",
                width: 150,
              },
              {
                title: "昵称",
                key: "nickname",
                width: 110,
              },
              {
                title: "真实姓名",
                key: "realname",
                width: 200,
              },
              {
                title: "身份证",
                key: "identity",
                width: 200,
              },
              {
                title: "客户等级",
                key: "custLevel",
                width: 100,
                align: "center",
                render: (h, params) => {
                  let re = "";
                  if (params.row.custLevel === 1) {
                    re = "普通用户(1)";
                  } else if (params.row.custLevel === 2) {
                    re = "普通实名(2)";
                  }
                  return h("div", re);
                }
              },
              {
                title: "注册时间",
                key: "createTime",
                sortable: true,
                sortType: "desc",
                width: 150,
                render: (h, params) => {
                  let re = this.DateTimeUtils().dateTimeFormat(params.row.createTime,"yyyy-MM-dd HH:mm:ss");
                  return h("div", re);
                }
              },
              {
                title: "更新时间",
                key: "updateTime",
                sortable: true,
                width: 150,
                render: (h, params) => {
                  let re = this.DateTimeUtils().dateTimeFormat(params.row.updateTime,"yyyy-MM-dd HH:mm:ss");
                  return h("div", re);
                }
              },
              {
                title: "操作",
                key: "action",
                width: 200,
                align: "center",
                fixed: 'right',
                render: (html, params) => {
                    return html("div", [
                      html(
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
                      html(
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
                      ),
                      html(
                        "Button",
                        {
                          props: {
                            type: "error",
                            size: "small"
                          },
                          on: {
                            click: () => {
                              this.setPasswd(params.row);
                            }
                          }
                        },
                        "重置密码"
                      )
                    ]);
                }
              }
            ],
            data: [],
          }
        },
        methods: {
          init: function () {
            this.queryCustomerInfoPage();
            this.accessToken = {
              accessToken: getStore("accessToken")
            };
          },
          queryCustomerInfoPage: function () {
            this.loading = true;
            if(this.userInfoForm.custLevel == 0) {
              this.userInfoForm.custLevel = "";
            }
            this.postRequest('/customer/info/page', this.userInfoForm).then(resp=>{
              this.loading = false;
              if (resp.success === true) {
                this.data = resp.result.response;
                this.total = resp.result.totalCount;
              }
            })
          },
          handleSearch() {
            this.userInfoForm.pageNo = 1;
            this.userInfoForm.pageSize = 10;
            this.init();
          },
          handleReset() {
            this.$refs.userInfoForm.resetFields();
            this.userInfoForm.createEndDate = "";
            this.userInfoForm.createStartDate = "";

            this.userInfoForm.pageNo = 1;
            this.userInfoForm.pageSize = 10;
            // 重新加载数据
            this.init();
          },
          addUser: function() {
            this.modalType = 0;
            this.modalTitle = "添加用户";
            this.$refs.createUserForm.resetFields();
            this.userInfoModalVisible = true;
          },
          delAll: function() {

          },
          look: function(json) {
            alert(JSON.stringify(json));
          },
          edit: function() {
            this.modalType = 1;
            this.modalTitle = "编辑用户";
            this.$refs.createUserForm.resetFields();
            this.userInfoModalVisible = true;
          },
          setPasswd: function(row) {
              alert(JSON.stringify(row));

              this.postRequest('/customer/action/setLoginPasswd', {loginAccount: row['loginAccount'], loginPw: '123456'}).then(resp => {

              });
          },
          cancelUser: function() {
            this.userInfoModalVisible = false;
          },
          submitUser: function() {


          },
          clearSelectAll() {
            this.$refs.table.selectAll(false);
          },
          selectDateRange: function(v) {
            if (v) {
              this.userInfoForm.createStartDate = v[0];
              this.userInfoForm.createEndDate = v[1];
            }
          },
          selectBirthdayDateRange: function(v) {

          },
          handleSuccess: function (res, file) {
            if (res.success === true) {
              file.url = res.result;
              this.createUserForm.headerimg = res.result;
              this.defaultList[0].url = res.result;
              //this.$refs.upload.fileList.splice(0, 1);
            } else {
              this.$Message.error(res.message);
            }
          },
          handleView(imgUrl) {
            this.imgUrl = imgUrl;
            this.viewImage = true;
          },
          handleRemove(file) {
            const fileList = this.$refs.upload.fileList;
            this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
          },
          handleFormatError: function (file) {
            this.$Notice.warning({
              title: "不支持的文件格式",
              desc:
                "所选文件‘ " + file.name + " ’格式不正确, 请选择 .jpg 或 .png格式文件"
            });
          },
          handleMaxSize: function (file) {
            this.$Notice.warning({
              title: "文件大小过大",
              desc: "所选文件‘ " + file.name + " ’大小过大, 不得超过 5M."
            });
          },
          handleBeforeUpload: function(){
            const check = this.uploadList.length < 10;
            if (!check) {
              this.$Notice.warning({
                title: "最多只能上传 1 张图片"
              });
            }
            return check;
          },
          changePage(v) {
            this.userInfoForm.pageNo = v;
            this.queryCustomerInfoPage();
          },
          changePageSize(v) {
            this.userInfoForm.pageSize = v;
            this.queryCustomerInfoPage();
          },
          changeSort(e) {
            this.userInfoForm.sort = e.key;
            this.userInfoForm.order = e.order;
            if (e.order === "normal") {
              this.userInfoForm.order = "";
            }
            this.init();
          },
          showSelect(e) {
            alert(JSON.stringify(e));
            this.exportData = e;
            this.selectList = e;
            this.selectCount = e.length;
          },

        },
        watch: {
          userInfoModalVisible: function (curVal,oldVal) {
          }
        },
        mounted() {
          this.uploadList = this.$refs.upload.fileList;
          this.$refs.upload.fileList.splice(0, 1);
          this.init();
        }
    }
</script>


