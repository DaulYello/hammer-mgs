<style lang="less">
    @import '../../styles/common.less';
    @import '../../styles/table/table.less';
</style>

<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card showHead="false">
                    <p slot="title">
                        <Icon type="android-remove"></Icon>
                        商品列表
                    </p>
                    <Row>
                        <div class="serachStyle">
                            <DatePicker v-model="query.starttime" style="width:200px;" placeholder="选择开始日期和时间" ></DatePicker>
                        </div>
                        <div style="margin-top: 16px;float: left;margin-left: 5px;margin-right: 5px"> 至 </div>
                        <div class="serachStyle">
                            <DatePicker v-model="query.endtime" style="width:200px;" placeholder="选择结束日期和时间" ></DatePicker>
                        </div>
                        <div class="serachStyle">
                            <Input v-model="query.productNo" placeholder="请输入商品编号..." style="width: 200px" />
                            <Input v-model="query.telephone" placeholder="请输入用户电话..." style="width: 200px" />
                            <!--<Input v-model="query.nickname" placeholder="请输入用户昵称..." style="width: 200px" />-->
                        </div>
                        <div class="serachStyle">
                            <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜用户</Button></span>
                        </div>
                    </Row>
                    <div class="margin-top-10">
                        <Table :loading="loading" :data="pageData" :on-expand="expandOrderInfo" :columns="columns"></Table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
    </div>
</template>

<script>
    /*import canEditTable from './components/canEditTable.vue';*/
    import expandRow from './expandRow.vue';
    import {
        formatDateByLong,
        formatDate
    } from 'utils/time';
    import {
        getGoodsPage
    } from 'api/goods/goods';
    export default {
        name: 'query-goods',
        components: {
            expandRow
        },
        props: {
            isLoad: {
                type: Boolean,
                default: false
            }
        },
        data () {
            return {
                loading:true,
                page: 1,
                size: 20,
                pageInfo: '',
                pageData: [],
                query:{},
                columns: [
                    {
                        /*title: '序号',*/
                        type: 'expand',
                        width: 60,
                        render: (h, params) => {
                            return h(expandRow, {
                                props: {
                                    oderInfo: params.row.orderInfos
                                }
                            });
                        }

                    },
                    {
                        title: '序号',
                        width: 80,
                        key: 'id',
                        align: 'center'
                    },
                    {
                        title: '商品编号',
                        align: 'center',
                        key: 'productNo'
                    },
                    {
                        title: '商品名称',
                        align: 'center',
                        key: 'productName'
                    },
                    {
                        title: '商品单价',
                        align: 'center',
                        key: 'productPrice'
                    },
                    {
                        title: '出售数量',
                        align: 'center',
                        key: 'productSum'
                    },
                    {
                        title: '商品创建人',
                        align: 'center',
                        key: 'nickname'
                    },
                    {
                        title: '创建人电话',
                        align: 'center',
                        key: 'telephone'
                    },
                    {
                        title: '库存',
                        align: 'center',
                        key: 'productStock'
                    },
                    {
                        title: '商品状态',
                        align: 'center',
                        key: 'status',
                        render: (h,params) => {//商品状态0、新建状态; 1、正常; 2、下架; -1、删除
                            const text = params.row.status == 0 ? "新建" : params.row.status == 1 ? "正常" : params.row.status == 2 ? "下架" : "删除";
                            return h("div",text);
                        }
                    },
                    {
                        title: '类目类型',
                        align: 'center',
                        key: 'categoryType',
                        render: (h,params) => {//类目类型1、CNT
                            const text = params.row.categoryType == 1 ? "CNT" : "其他";
                            return h("div",text);
                        }
                    },
                    {
                        title: '商品类型',
                        align: 'center',
                        key: 'productType',
                        render: (h,params) => {//商品类型1、卖出;2、买入
                            const text = params.row.productType == 1 ? "卖出" : "买入";
                            return h("div",text);
                        }
                    },
                    {
                        title: '币种',
                        align: 'center',
                        key: 'currency',
                        render: (h,params) => {//币种1、人民币
                            const text = params.row.currency == 1 ? "人民币" : "其他";
                            return h("div",text);
                        }
                    },
                    {
                        title: '创建时间',
                        align: 'center',
                        key: 'createTime',
                        width:100,
                        render: (h, params) => {
                            return h('div',formatDateByLong(params.row.createTime,"yyyy-MM-dd h:m:s"));
                        }
                    },
                    {
                        title: '修改时间',
                        align: 'center',
                        key: 'updateTime',
                        width:100,
                        render: (h, params) => {
                            if(params.row.updateTime != null){
                                return h('div',formatDateByLong(params.row.updateTime,"yyyy-MM-dd h:m:s"));
                            }
                        }
                    }
                ]
            };
        },
        methods: {
            getData (page) {
                this.page = page;
                this.loading = true;
                getGoodsPage(page,this.size,this.query).then(data => {
                    this.loading = false;
                    if (data.status === 200) {
                        this.pageInfo = data.data;
                        this.pageData = data.data.dataList;
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('查询用户信息服务器异常' + error);
                });
            },
            /*handleInfo (query) {
            },*/
            handleSearch(){
                this.getData(1);
            },
            clickUnfold(val){
                //vm.thisTableData[index]
                console.log("iuiooioioipopiopi"+val.id);
            },
            expandOrderInfo(val){
                //vm.thisTableData[index]
                console.log("人神共愤都很反感");
            },

            /*deleteQuartz () {
                // 转字符串到后台
                let ids = this.multipleSelection.map(item => item.jobLogId).join()
                deleteQuartzLog(ids).then(data => {
                    if (data.status === 200) {
                        this.$Message.success('批量删除成功');
                        this.getData(1);
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('批量删除异常' + error);
                });
            }*/

        },
        created () {
            this.getData(1);
        }
    };
</script>
