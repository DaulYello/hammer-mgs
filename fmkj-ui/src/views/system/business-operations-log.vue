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
                        <Input v-model="query.module" placeholder="请输入系统模块。。。" style="width: 200px;" />
                        <Input v-model="query.nickname" placeholder="请输入操作人姓名。。。" style="width: 200px;" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    </Row>
                    <Row class="margin-top-10">
                        <span @click="deleteOperation"><Button type="error" icon="android-remove">删除</Button></span>
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
    import canEditTable from './components/canEditTable.vue';
    import {
        formatDateByLong,
        formatDate
    } from 'utils/time';
    import {
        getQueryOperaLog,
        deleteOperaLog
    } from 'api/system/system';
    export default {
        name: "business-operations-log",
        components: {
            canEditTable
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
                query:{},
                multipleSelection:[],
                columns:[
                    {
                        title: '序号',
                        type: 'selection',
                        width: 80,
                        key: 'id',
                        align: 'center'
                    },
                    {
                        title: '序号',
                        align: 'center',
                        key: 'id'
                    },
                    {
                        title: '系统模块',
                        align: 'center',
                        key: 'module'
                    },
                    {
                        title: '操作用户',
                        align: 'center',
                        key: 'nickname'
                    },
                    {
                        title: '操作时间',
                        align: 'center',
                        key: 'createTime'
                    },
                    {
                        title: '操作类型',
                        align: 'center',
                        key: 'operateType'
                    },
                    {
                        title: '操作内容',
                        align: 'center',
                        width: 200,
                        key: 'content'
                    },
                    {
                        title: '操作',
                        align: 'center',
                        key: 'handle',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            deleteOperaLog(params.row.id).then(data => {
                                                if (data.status === 200) {
                                                    this.$Message.success(data.message);
                                                    this.getData(this.page);
                                                } else {
                                                    this.$Message.error(data.message);
                                                    this.getData(this.page);
                                                }
                                            }).catch(error => {
                                                this.$Message.error('删除异常：' + error);
                                            });
                                        }
                                    }
                                }, '删除')
                            ])
                        }
                    }
                ]
            };
        },
        methods:{
            getData (page) {
                this.page = page;
                this.loading = true;
                getQueryOperaLog(page,this.size,this.query).then(data => {
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
