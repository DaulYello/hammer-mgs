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
                        <span @click="addExtend" style="margin: 0 10px;"><Button type="primary" icon="android-add">新增</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <can-modify-table :loading="loading" v-model="pageData"
                                          :columns-list="columns" @input="handleInput" @on-delete="handleDel"></can-modify-table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
    </div>
</template>

<script>
    import canModifyTable from './components/canModifyTable.vue';
    import {
        formatDateByLong,
        formatDate
    } from 'utils/time';
    import {
        getExtendInfo,
        deleteExtendInfo
    } from 'api/task/task';
    import CanModifyTable from "./components/canModifyTable";
    export default {
        name: 'task-extend',
        components: {
            CanModifyTable
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
                auditData: {},
                tid: '',
                auditRules: {
                    auditOption: [{
                        required: true,
                        message: '审核意见必填',
                        trigger: 'blur'
                    }]
                },
                uploadList: [],
                auditId: 0,
                pageData: [],
                query:{},
                columns: [
                    /*{
                        title: '序号',
                        type: 'selection',
                        width: 80,
                        key: 'id',
                        align: 'center'
                    },*/
                    {
                        title: '序号',
                        type: 'index',
                        width: 60,
                        key: 'id',
                        align: 'center'
                    },
                    {
                        title: '列名',
                        align: 'center',
                        key: 'clounmKey',
                        editable: true
                    },
                    {
                        title: '列名名称',
                        align: 'center',
                        key: 'clounmName',
                        editable: true
                    },
                    {
                        title: '提示信息',
                        align: 'center',
                        key: 'clounmTip',
                        editable: true
                    },
                    {
                        title: '显示顺序',
                        align: 'center',
                        width: 70,
                        key: 'orderNum',
                        editable: true
                    },
                    {
                        title: '是否为空',
                        align: 'center',
                        width:70,
                        key: 'isEmpty',
                        editable: true
                    },
                    {
                        title: '注释',
                        align: 'center',
                        width: 170,
                        key: 'emptyHint',
                        editable: true
                    },
                    {
                        title: '操作',
                        align: 'center',
                        width: 180,
                        key: 'handle',
                        handle: ['edit2','delete']
                    }
                ]
            };
        },
        methods: {
            getExtendData(page,tid){
                this.tid = tid;
                this.query.tid = tid;
                this.getData (page);
            },
            getData (page) {
                this.page = page;
                this.loading = true;
                getExtendInfo(page,this.size,this.query).then(data => {
                    this.loading = false;
                    if (data.status === 200) {
                        this.pageInfo = data.data;
                        this.pageData = data.data.dataList;
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('查询任务扩展信息异常' + error);
                });
            },
            handleInfo (query) {
            },
            handleSearch(){
                this.getData(1);
            },
            handleDel (val, index) {
                console.log("删除数据。。。。"+val);
                deleteExtendInfo(val.id).then(data => {
                    this.loading = false;
                    if (data.status === 200) {
                        this.getData(1);
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('查询温馨提示信息异常' + error);
                });
            },
            handleInput (val) {
            },
            handleCellChange (val, index, key) {
                this.$Message.success('修改了第 ' + (index + 1) + ' 行列名为 ' + key + ' 的数据');
            },
            handleChange (val, index) {
                this.$Message.success('修改了第' + (index + 1) + '行数据');
            },
            handleError (message) {
                this.getData(this.page, this.userStatus);
                this.$Message.error(message);
            },
            addExtend(){
                this.pageData.push({
                    id: null,
                    tid: this.tid,
                    clounmKey: null,
                    clounmName: null,
                    clounmTip: null,
                    isEmpty: null,
                    emptyHint: null,
                    orderNum: null
                });
            },
            cancel() {

            }
        }
    };
</script>
