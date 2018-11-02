<style lang="less">
    @import '../../styles/common.less';
    @import '../../styles/table/table.less';
</style>

<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card showHead="false">
                    <Row>
                        <Input v-model="query.nickname" placeholder="获得CNT用户昵称。。。" style="width: 200px;" />
                        <Input v-model="query.channels" placeholder="获取渠道。。。" style="width: 200px;" />
                        <!--<select v-model="myVal" v-model="query.takeType">-->
                            <!--<option v-for="item in options" name="takeType" :value="item.value">
                                {{item.name}}
                            </option>-->
                            <Form :model="query" ref="query" :label-width="80">
                                <FormItem label="所属类型 : " prop="sendValue">
                                    <Select style="width:200px" v-model="query.sendValue">
                                        <Option v-for="item in query.stateList" :value="item.value" :key="item.value" name="sendValue">{{
                                            item.label }}
                                        </Option>
                                    </Select>
                                </FormItem>
                            </Form>
                        <!--</select>-->
                        <Input v-model="query.recyleType" placeholder="所属类型。。。" style="width: 200px;" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <Table :loading="loading"  @on-selection-change="handleSelectionChange" @on-select-all="handleSelectionChange" @on-select="handleSelectionChange" @on-delete="handleDel" @on-router="handleInfo" @on-change="handleChange" @on-error="handleError"  refs="multipleTable" :data="pageData" :columns="columns"></Table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
    </div>
</template>

<script>
    /*import canEditTable from './components/canEditTable.vue';*/
    import {
        formatDateByLong,
        formatDate
    } from 'utils/time';
    import {
        getFmRecyleLogs
    } from 'api/system/system';
    export default {
        name: "cnt-recode-log",
        components: {
        },
        props:{
            isLoad:{
                type:Boolean,
                default:false
            }
        },
        data(){
            return{
                loading:true,
                page:1,
                size:20,
                pageInfo:"",
                pageData:[],
                query:{
                    stateList: [
                        {
                            value: '0',
                            label: '待分配'
                        },
                        {
                            value: '1',
                            label: '开发中'
                        },
                        {
                            value: '2',
                            label: '已完成'
                        },
                        {
                            value: '3',
                            label: '停用'
                        },
                        {
                            value: '4',
                            label: '已变更'
                        }
                    ]
                },
                value:'',
                multipleSelection:[],
                // label:['CNT','R积分'],
                /*labelInValue:true,*/
                /*disabled:false,*/
                // placeholder:'所属类型',
                // name:'所属类型,,',
                // elementId:'1',
               /* myVal:0, //默认选中第一项
                options:[
                    {
                        name:'所属类型',
                        value:0
                    },
                    {
                        name:'CNT',
                        value:1
                    },
                    {
                        name:'R积分',
                        value:2
                    },
                ],*/
                columns:[
                    /*{
                        title: '序号',
                        type: 'selection',
                        width: 80,
                        key: 'id',
                        align: 'center'
                    },*/
                    {
                        title: '序号',
                        align: 'center',
                        key: 'id'
                    },
                    {
                        title: '获得CNT用户',
                        align: 'center',
                        key: 'nickname'
                    },
                    {
                        title: '失去CNT用户',
                        align: 'center',
                        key: 'nicknamef'
                    },
                    {
                        title: 'CNT/R数量',
                        align: 'center',
                        key: 'takeNum'
                    },
                    {
                        title: '收取时间',
                        align: 'center',
                        key: 'takeDate',
                        render: (h, params) => {
                            return h('div',formatDateByLong(params.row.takeDate,'yyyy-MM-dd h:s:m'));
                        }
                    },
                    {
                        title: '获取渠道',
                        align: 'center',
                        key: 'takeType'
                    },
                    {
                        title: '所属类型',
                        align: 'center',
                        width: 200,
                        key: 'recyleType'
                    },
                    {
                        title: '详细描述',
                        align: 'center',
                        key: 'takeMsg',
                    }
                ]
            };
        },
        methods:{
            getData (page) {
                this.page = page;
                this.loading = true;
                getFmRecyleLogs(page,this.size,this.query).then(data => {
                    this.loading = false;
                    if (data.status === 200) {
                        this.pageInfo = data.data;
                        this.pageData = data.data.dataList;
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('查询业务日志信息服务器异常' + error);
                });
            },
            handleInfo (query) {
            },
            handleSearch(){
                this.getData(1);
            },
            handleDel (val, index) {
            },
            handleCellChange (val, index, key) {
                this.$Message.success('修改了第 ' + (index + 1) + ' 行列名为 ' + key + ' 的数据');
            },
            handleChange (val, index) {
                this.$Message.success('修改了第' + (index + 1) + '行数据');
            },
            handleError (message) {
                this.getData(this.page);
                this.$Message.error(message);
            },
            deleteOperation () {
                // 转字符串到后台
                let ids = this.multipleSelection.map(item => item.id).join()
                deleteOperaLog(ids).then(data => {
                    if (data.status === 200) {
                        this.$Message.success('批量删除成功');
                        this.getData(1);
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('批量删除异常' + error);
                });
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }

        },
        created () {
            this.getData(1);
        }
    };
</script>
