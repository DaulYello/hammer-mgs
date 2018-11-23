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
                    <Input v-model="query.title" placeholder="请输入任务名称..." style="width: 200px" />
                    <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    <span @click="addPrompt" style="margin: 0 10px;"><Button type="primary" icon="android-add">新增</Button></span>
                </Row>
                <div class="margin-top-10">
                    <can-edit-table :loading="loading" refs="multipleSelection" v-model="pageData" :columns-list="columns" @input="handleInput" @on-delete="handleDel"></can-edit-table>

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
        getPartPage,
        auditPart
    } from 'api/task/task';
    export default {
        name: 'task-prompt',
        components: {
            canEditTable
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
                multipleSelection: [],
                columns: [
                    {
                        title: '序号',
                        width: 80,
                        key: 'id',
                        align: 'center'
                    },
                    {
                        title: '任务编号',
                        align: 'center',
                        key: 'tid'
                    },
                    {
                        title: '任务名称',
                        align: 'center',
                        key: 'title'
                    },
                    {
                        title: '操作',
                        align: 'center',
                        key: 'handle',
                        handle: ['edit', 'delete']
                    }
                ]
            };
        },
        methods: {
            getData (page) {
                this.page = page;
                this.loading = true;
                getPartPage(page,this.size,this.query).then(data => {
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
            handleInfo (query) {
            },
            handleSearch(){
                this.getData(1);
            },
            handleDel (val, index) {
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
                    tid: null,
                    title: null
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
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }

        },
        created () {
            this.getData(1);
        }
    };
</script>
