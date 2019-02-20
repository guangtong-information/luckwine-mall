<style lang="less">
  @import "adContentList.less";
</style>

<template>
  <div class="adContentList">
    <Row>
      <Col>
        <Card>

          <!--搜索栏-->
          <Row>
            <Form ref="adContentSearchForm" :model="adContentSearchForm" inline :label-width="70" class="search-form">

              <Form-item label="所属系统:" prop="systemId">
                <Select v-model="adContentSearchForm.systemId" placeholder="请选择" clearable style="width: 200px">
                  <Option value="">全部</Option>
                  <Option value="1">酒缘网PC版</Option>
                  <Option value="2">酒缘网H5版</Option>
                </Select>
              </Form-item>

              <Form-item label="所属页面:" prop="pageId">
                <Select v-model="adContentSearchForm.pageId" placeholder="请选择" clearable style="width: 200px">
                  <Option value="">全部</Option>
                  <Option value="10">首页</Option>
                  <Option value="11">领劵中心</Option>
                  <Option value="12">白酒馆</Option>
                </Select>
              </Form-item>

              <Form-item label="标题:" prop="title" >
                <Input type="text" v-model="adModuleSearchForm.title" clearable placeholder="请输入标题"  style="width: 200px"/>
              </Form-item>

              <Form-item label="所属状态:" prop="status">
                <Select v-model="adModuleSearchForm.status" placeholder="请选择" clearable style="width: 200px">
                  <Option value="">全部</Option>
                  <Option value="0001">启用</Option>
                  <Option value="0001">禁用</Option>
                </Select>
              </Form-item>

            </Form>
          </Row>

          <!--操作按钮-->

          <!--列表-->

          <!--分页-->

        </Card>
      </Col>
    </Row>
  </div>
</template>

<script>
    export default {
        data(){
          return{
            //搜索框
            adContentSearchForm:{
              systemId:"",
              pageId:"",
              title:"",
              status:"",
            },

          }
        },
        methods:{

        },
    }
</script>
