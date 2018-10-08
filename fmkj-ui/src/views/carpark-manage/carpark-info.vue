<style lang="less">

@import '../../styles/common.less';

</style>

<template>

<div>
    <Row>
        <Card>
            <p slot="title">
                <Icon type="compose"></Icon>
                停车场详情 - {{carpark.name}}
            </p>
            <Form ref="carpark" :model="carpark" :rules="carparkRules" :label-width="120">
              <FormItem label="停车场名称：" prop="name">
                  <Input v-model="carpark.name" type="text" placeholder="请输入停车场名称..."></Input>
              </FormItem>
              <FormItem label="停车场地址：" prop="address">
                  <Input v-model="carpark.address" type="text" placeholder="请输入停车场地址..."></Input>
              </FormItem>
              <FormItem label="停车场坐标：">
                  <div id="container" style="width:800px;height:300px"></div>
              </FormItem>
              <FormItem label="联系人姓名：" prop="contact">
                  <Input v-model="carpark.contact" type="text" placeholder="请输入停车场联系人姓名..."></Input>
              </FormItem>
              <FormItem label="联系方式：" prop="contactWay">
                  <Input v-model="carpark.contactWay" type="text" placeholder="请输入联系方式..."></Input>
              </FormItem>
              <!-- <FormItem label="经度：" prop="longitude">
                  <Input v-model="carpark.longitude" type="text" placeholder="请输入经度..."></Input>
              </FormItem>
              <FormItem label="纬度：" prop="latitude">
                  <Input v-model="carpark.latitude" type="text" placeholder="请输入纬度..."></Input>
              </FormItem> -->
              <FormItem label="车位数量：">
                  <InputNumber :max="100000" :min="1" v-model="carpark.number"></InputNumber>
              </FormItem>
              <FormItem label="是否对外开放：">
                <RadioGroup v-model="carpark.open">
                    <Radio label="1">是</Radio>
                    <Radio label="2">否</Radio>
                </RadioGroup>
              </FormItem>
              <FormItem label="月卡单价：">
                  <InputNumber :max="10000" :min="1" v-model="carpark.monthFee"></InputNumber>
              </FormItem>
              <FormItem label="年卡单价：">
                  <InputNumber :max="10000" :min="1" v-model="carpark.yearFee"></InputNumber>
              </FormItem>
              <FormItem label="每小时费用：">
                  <InputNumber :max="10000" :min="1" v-model="carpark.hourFee"></InputNumber>
              </FormItem>
              <FormItem label="临时停车位总数量：">
                  <InputNumber :max="10000" :min="1" v-model="carpark.temporaryNumber"></InputNumber>
              </FormItem>
              <FormItem label="固定停车位总数量：">
                  <InputNumber :max="10000" :min="1" v-model="carpark.fixedNumber"></InputNumber>
              </FormItem>
              <FormItem>
                  <Button type="primary" :loading="loading" @click="handleSubmit('carpark')">修改</Button>
                  <Button type="ghost" @click="handleReset('carpark')" style="margin-left: 8px">Reset</Button>
              </FormItem>
            </Form>
        </Card>
        <Card class="margin-top-10">
          <p slot="title">
              <Icon type="compose"></Icon>
              停车场管理 - {{carpark.name}}
          </p>
          <Form :label-width="120">
            <FormItem label="停车场管理者：">
                <Table stripe :columns="columns" :data="carparkManage" @on-row-dblclick="handle2Click"></Table>
            </FormItem>
            <FormItem>
                <Button type="primary" :loading="loading" @click="handleSetManage">设置总管理</Button>
            </FormItem>
          </Form>
        </Card>
    </Row>
</div>

</template>

<script>

import {
    putCarpark,
    addCarparkManage,
    delCarparkManage,
    getCarparkManagesById
}
from 'api/carpark/carpark';
export default {
    name: 'build-info',
    data() {
        return {
            map: {},
            loading: true,
            userTel: '',
            oldCarpark:'',
            carpark: {},
            carparkRules: {
                name: [{
                    required: true,
                    message: '请输入停车场名称',
                    trigger: 'blur'
                }],
                address: [{
                    required: true,
                    message: '请输入停车场地址',
                    trigger: 'blur'
                }],
                contact: [{
                    required: true,
                    message: '请输入停车场联系人姓名',
                    trigger: 'blur'
                }],
                contactWay: [{
                    required: true,
                    message: '请输入联系方式',
                    trigger: 'blur'
                }],
                longitude: [{
                    required: true,
                    message: '请输入停车场经度',
                    trigger: 'blur'
                }],
                latitude: [{
                    required: true,
                    message: '请输入停车场纬度',
                    trigger: 'blur'
                }]
            },
            carparkManage: [],
            columns: [{
                title: '账号',
                key: 'username'
            }, {
                title: '真实名称',
                key: 'realname'
            }],
        };
    },
    methods: {
        init() {
              this.oldCarpark = this.$route.query;
              this.oldCarpark.fixedNumber = Number(this.oldCarpark.fixedNumber);
              this.oldCarpark.hourFee = Number(this.oldCarpark.hourFee);
              this.oldCarpark.monthFee = Number(this.oldCarpark.monthFee);
              this.oldCarpark.number = Number(this.oldCarpark.number);
              this.oldCarpark.open = Number(this.oldCarpark.open);
              this.oldCarpark.temporaryNumber = Number(this.oldCarpark.temporaryNumber);
              this.oldCarpark.yearFee = Number(this.oldCarpark.yearFee);
              this.carpark = JSON.parse(JSON.stringify(this.oldCarpark));;
              this.map = new BMap.Map("container");
              var point = new BMap.Point(this.carpark.longitude, this.carpark.latitude);
              this.map.centerAndZoom(point, 15);
              var marker = new BMap.Marker(point); //将点转化成标注点
              // marker.enableDragging()
              this.map.addOverlay(marker);
              getCarparkManagesById(this.oldCarpark.spaceId).then(data => {
                  this.loading = false;
                  if (data.status === 200) {
                      this.carparkManage = data.data;
                  } else {
                      this.$Message.error(data.message);
                  }
              }).catch(error => {
                  this.$Message.error('服务器异常' + error);
              });
            },
          handleReset (name) {
              this.carpark = JSON.parse(JSON.stringify(this.oldCarpark));;
        },
        handleSubmit(from) {
            this.$refs[from].validate((valid) => {
                if (valid) {
                  this.loading = true;
                    putCarpark(this.carpark).then(data => {
                      this.loading = false;
                      if (data.status === 200) {
                        //this.$refs[from].resetFields();
                        this.$Message.success('修改成功');
                      } else {
                        this.$Message.error(data.message);
                      }
                    }).catch(error => {
                        this.loading = false;
                        this.$Message.error('服务器异常' + error);
                    });
                } else {
                    this.$Message.error('请输入完整信息');
                }
            });
        },
        handleSetManage() {
                this.$Modal.confirm({
                    render: (h) => {
                        return h('Input', {
                            props: {
                                value: this.userTel,
                                autofocus: true,
                                placeholder: '请输入平台内用户账号（手机号）...',
                                loading: this.loading
                            },
                            on: {
                                input: (val) => {
                                    this.userTel = val;
                                }
                            },
                        })
                    },
                    onOk: () => {
                        addCarparkManage(this.oldCarpark.spaceId, this.userTel).then(data => {
                            if (data.status === 200) {
                                this.$Message.success('添加成功');
                                this.init();
                            } else {
                                this.$Message.error(data.message);
                            }
                        }).catch(error => {
                            this.$Message.error('服务器异常' + error);
                        });
                        this.userTel = '';
                    },
                    onCancel: () => {
                        this.userTel = '';
                    }
                })
            },
        handle2Click(data, index) {
                this.$Modal.confirm({
                    title: "警告",
                    content: "确认删除该管理员吗？不可恢复",
                    onOk: () => {
                        this.loading = true;
                        delCarparkManage(this.oldCarpark.spaceId,data.userId).then(data => {
                            if (data.status === 200) {
                                this.$Message.success('删除成功');
                                this.init();
                            } else {
                                this.$Message.error(data.message);
                            }
                        }).catch(error => {
                            this.$Message.error('服务器异常' + error);
                        });
                    },
                    onCancel: () => {

                    }
                });
        }
    },
    mounted() {
        this.init();
    },
    watch: {
        '$route' () {
            this.init();
        }
    }
};

</script>
