<style lang="less">

@import '../../styles/common.less';

</style>

<template>

<div>
    <Row>
        <Card>
            <Form ref="carpark" :model="carpark" :rules="carparkRules" :label-width="120">
                <FormItem label="停车场名称：" prop="name">
                    <Input v-model="carpark.name" type="text" placeholder="请输入停车场名称..."></Input>
                </FormItem>
                <FormItem label="停车场坐标：">
                    <div id="container" style="width:800px;height:300px"></div>
                </FormItem>
                <FormItem label="停车场地址：" prop="address">
                    <Input v-model="carpark.address" type="text" placeholder="请输入停车场地址..."></Input>
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
                    <Input :max="100000" :min="1" v-model="carpark.number"></Input>
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
                    <Input :max="10000" :min="1" v-model="carpark.temporaryNumber" @on-blur="countTemporaryNumber"></Input>
                </FormItem>
                <FormItem label="固定停车位总数量：">
                    <Input :max="10000" :min="1" v-model="carpark.fixedNumber"></Input>

                </FormItem>
                <FormItem>
                    <Button type="primary" :loading="loading" @click="handleSubmit('carpark')">提交</Button>
                    <Button type="ghost" @click="handleReset('carpark')" style="margin-left: 8px">Reset</Button>
                </FormItem>
            </Form>
        </Card>
    </Row>
</div>

</template>

<script>
import {
  addCarpark
} from 'api/carpark/carpark';
export default {
    name: 'workflow',
    data() {
        return {
            map: {},
            loading:false,
            carpark: {
              name: '',
              address: '',
              number: 1,
              open: 1,
              monthFee:1,
              yearFee:1,
              hourFee:1,
              temporaryNumber:1,
              fixedNumber:1,
              latitude: '',
              longitude: ''
            },
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
            }
        };
    },
    methods: {
        handleSubmit(from) {
            this.$refs[from].validate((valid) => {
                if (valid) {
                  this.loading = true;
                    addCarpark(this.carpark).then(data => {
                      this.loading = false;
                      if (data.status === 200) {
                        this.$refs[from].resetFields();
                        this.$Message.success('添加成功');
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
            handleReset (name) {
                this.$refs[name].resetFields();
            },
            myFun (result) {
              var cityName = result.name;
              this.map.setCenter(cityName);
            },
            countTemporaryNumber() {
              if (this.carpark.number == undefined || this.carpark.number == undefined) {
                  return;
              }
              this.carpark.fixedNumber = this.carpark.number - this.carpark.temporaryNumber;
            }
    },
    mounted() {
      this.map = new BMap.Map("container");
      var point = new BMap.Point(116.404, 39.915);
      this.map.centerAndZoom(point, 15);
      this.map.enableScrollWheelZoom(true)
      var myCity = new BMap.LocalCity();
      myCity.get(this.myFun);
      var geoc = new BMap.Geocoder();
      var that = this
      var marker;
      this.map.addEventListener("click", function(e){
        //通过点击百度地图，可以获取到对应的point, 由point的lng、lat属性就可以获取对应的经度纬度
        var pt = e.point;
        geoc.getLocation(pt, function(rs){
            //addressComponents对象可以获取到详细的地址信息
            if (marker !== undefined) {
              that.map.removeOverlay(marker)
            }
            var addComp = rs.addressComponents;
            var addSurr = rs.surroundingPois;
            var site = addComp.province + "-" + addComp.city + "-" + addComp.district ;
            if (addComp.street != '' ) {
                site = site + "-" + addComp.street;
            }
            if (addComp.streetNumber != '') {
                site = site + "-" + addComp.streetNumber;
            }
            if (addSurr.length > 0) {
                site = site + "-" +addSurr[0].title;
            }
            that.carpark.address = site
            that.carpark.latitude = pt.lat;
            that.carpark.longitude = pt.lng;
            var point = new BMap.Point(pt.lng, pt.lat); //将标注点转化成地图上的点
            marker = new BMap.Marker(point); //将点转化成标注点
            // marker.enableDragging()
            that.map.addOverlay(marker);
        });
    });
    }
};

</script>
