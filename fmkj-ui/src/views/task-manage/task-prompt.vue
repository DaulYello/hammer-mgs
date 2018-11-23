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
                    <!--<span>{{tid}}</span>-->
                    <!--<Input v-model="query.title" placeholder="请输入任务名称..." style="width: 200px" />-->
                    <!--<span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>-->
                    <span @click="addPrompt" style="margin: 0 10px;"><Button type="primary" icon="android-add">新增</Button></span>
                    <!--<span @click="addPrompt" style="margin: 0 10px;"><Button type="primary" icon="android-add">删除</Button></span>-->
                </Row>
                <div class="margin-top-10">
                    <!--<can-edit-table :loading="loading" refs="multipleSelection" v-model="pageData" :columns-list="columns" @input="handleInput" @on-delete="handleDel"></can-edit-table>-->
                    <can-modify-table :loading="loading" v-model="pageData" @on-select="handleSelect"
                                      :columns-list="columns" @input="handleInput" @on-delete="handleDel"></can-modify-table>
                </div>
                <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
            </Card>
            </Col>
        </Row>
    </div>
</template>

<script>
    // import canEditTable from './components/canEditTable.vue';
    import canModifyTable from './components/canModifyTable.vue';
    import {
        formatDateByLong,
        formatDate
    } from 'utils/time';
    import {
        getPromptInfo,
        deletePromptInfo
    } from 'api/task/task';
    import CanModifyTable from "./components/canModifyTable";
    export default {
        name: 'task-prompt',
        components: {
            CanModifyTable
        },
        props: {
            isLoad: {
                type: Boolean,
                default: false
            }
         /*   multipleSelection: []*/
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
                        width: 80,
                        key: 'id',
                        align: 'center'
                    },
                    {
                        title: '提示内容',
                        align: 'center',
                        key: 'promptText',
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
                        title: '操作',
                        align: 'center',
                        width: 180,
                        key: 'handle',
                        handle: ['edit','delete']
                    }
                ]
            };
        },
        methods: {
            getPromptData(page,tid){
                this.tid = tid;
                this.query.tid = tid;
                this.getData (page);
            },
            getData (page) {
                this.page = page;
                this.loading = true;
                getPromptInfo(page,this.size,this.query).then(data => {
                    this.loading = false;
                    if (data.status === 200) {
                        this.pageInfo = data.data;
                        this.pageData = data.data.dataList;
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('查询温馨提示信息异常' + error);
                });
            },
            handleInfo (query) {
            },
            handleSearch(){
                this.getData(1);
            },
            handleDel (val, index) {
                console.log("删除数据。。。。"+val);
                deletePromptInfo(val.id).then(data => {
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
            addPrompt(){
                this.pageData.push({
                    id: null,
                    tid: this.tid,
                    promptText: null,
                    orderNum: null
                });
            },
            ok() {
                this.$refs['partForm'].validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        auditPart(this.auditData.auditOption, this.auditId, 2).then(data => {
                            this.loading = false;
                            if (data.status === 200) {
                                this.modelShow = false;
                                this.$Message.success(data.message);
                                this.getData(this.page);
                            } else {
                                this.$Message.error(data.message);
                            }
                        }).catch(error => {
                            this.loading = false;
                            this.$Message.error('服务器异常' + error);
                        });
                    } else {
                        setTimeout(() => {
                            this.modelShow = true;
                        }, 100);
                        this.$Message.error('请输入审核意见');
                    }
                });
            },
            cancel() {

            },
            handleSelect(val){
                /*this.multipleSelection = val;*/
                console.log("删除数据。。。。"+val);
            },
           /* handleSelectionChange(val) {
                this.multipleSelection = val;
            }*/

        }/*,
        mounted:function(){
            Bus.$on("multipleSelection",(multipleSelection) =>{//子组件数据改变之后传递给父组件
                this.multipleSelection = multipleSelection;
            });
        },*/
        /*created () {
            this.getData(1);
        }*/
    };
</script>
