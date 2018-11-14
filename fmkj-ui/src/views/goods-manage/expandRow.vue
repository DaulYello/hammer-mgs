<style lang="less">
    /*@import '../../styles/advanced-router.less';*/
</style>

<template>
    <div>
        <Row class="advanced-router">
            <Table :data="oderInfo" :columns="columns"></Table>
        </Row>
    </div>
</template>
<script>
    //import TableBody from "iview/src/components/table/table-body";
    import {
        formatDateByLong,
        formatDate
    } from 'utils/time';
    export default {
        props: {
            oderInfo: {
                type:Array,
                default:[]
            }
        },
        data () {
            return {
                /*loading:true,*/
                columns: [
                    {
                        title: '订单编号',
                        align: 'center',
                        key: 'orderNo'
                    },
                    {
                        title: '下单用户',
                        align: 'center',
                        key: 'buyers'
                    },
                    {
                        title: '订单类型',
                        align: 'center',
                        key: 'orderType',
                        render: (h,params) => {//订单类型1、买入; 2、卖出
                            const text = params.row.orderType == 1 ? "卖出" : params.row.orderType == 2 ? "买入" : "错误数据";
                            return h("div",text);
                        }
                    },
                    {
                        title: '交易数量',
                        align: 'center',
                        key: 'tradeNum'
                    },
                    {
                        title: '订单状态',
                        align: 'center',
                        key: 'orderStatus',
                        render: (h,params) => {//订单状态0、未付款; 1、已付款或已付P; 2、订单取消; 3、交易成功; -1、删除订单
                            const text = params.row.orderStatus == 0 ? "未付款" : params.row.orderStatus == 1 ? "交易中"
                                : params.row.orderStatus == 2 ? "订单取消" : params.row.orderStatus == 3 ? "交易成功" : "删除订单";
                            return h("div",text);
                        }
                    },
                    {
                        title: '支付总金额',
                        align: 'center',
                        key: 'payment'
                    },
                    {
                        title: '支付方式',
                        align: 'center',
                        key: 'paymentType',
                        render: (h,params) => {//买家支付方式1、微信; 2、支付宝;
                            const text = params.row.paymentType == 1 ? "微信" : params.row.paymentType == 2 ? "支付宝" : "错误数据";
                            return h("div",text);
                        }
                    },
                    {
                        title: '下单时间',
                        align: 'center',
                        key: 'createTime',
                        width:100,
                        render: (h,params) => {
                            return h("div",formatDateByLong(params.row.createTime,"yyyy-MM-dd hh:mm:ss"))
                        }
                    },
                    {
                        title: '修改时间',
                        align: 'center',
                        key: 'updateTime',
                        width:100,
                        render: (h,params) => {
                            return h("div",formatDateByLong(params.row.updateTime,"yyyy-MM-dd hh:mm:ss"))
                        }
                    },
                    {
                        title: '支付时间',
                        align: 'center',
                        key: 'paymentTime',
                        width:100,
                        render: (h,params) => {
                            return h("div",formatDateByLong(params.row.paymentTime,"yyyy-MM-dd hh:mm:ss"))
                        }
                    },
                    {
                        title: '完成时间',
                        align: 'center',
                        key: 'endTime',
                        width:100,
                        render: (h,params) => {
                            return h("div",formatDateByLong(params.row.endTime,"yyyy-MM-dd hh:mm:ss"))
                        }
                    },
                    {
                        title: '付款确认',
                        align: 'center',
                        key: 'isPay',
                        render: (h,params) => {//付款确认0、未付款(金额);1、买家已付款(金额);2、确认付款（卖家）
                            const text = params.row.isPay == 0 ? "未付款" : params.row.isPay == 1 ? "交易中"
                                : params.row.isPay == 2 ? "已付款" : "错误数据";
                            return h("div",text);
                        }
                    }
                ]
            };
        },
        methods: {

        }
    };
</script>