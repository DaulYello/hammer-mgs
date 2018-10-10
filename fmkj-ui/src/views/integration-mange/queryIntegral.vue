<style lang="less">
    @import '../../styles/common.less';
    @import '../../styles/table/table.less';
</style>

<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card>
                    <p slot="title">
                        <Icon type="android-remove"></Icon>
                        年度积分
                    </p>
                    <Row class="margin-top-10">
                        <span @click="addIntegral"><Button type="primary" icon="android-add">新增</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <Table :ref="refs" :columns="columns" refs="multipleSelection" :data="thisTableData" border disabled-hover></Table>
                        <!--<can-edit-table :loading="loading" @on-delete="handleDel"  @on-error="handleError"  refs="multipleSelection" :data="pageData" :columns-list="columns"></can-edit-table>-->
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
        <Modal v-model="modelShow" title="任务" ok-text="保存" :loading="loading" @on-ok="ok" @on-cancel="cancel">
            <Form ref="quartzForm" :model="quartzData" :rules="quartzRules" :label-width="120">
                <FormItem label="任务名称：" prop="jobName">
                    <Input v-model="quartzData.jobName" type="text"></Input>
                </FormItem>
                <FormItem label="任务组名：" prop="jobGroup">
                    <Input v-model="quartzData.jobGroup" type="text"></Input>
                </FormItem>
                <FormItem label="方法名称：" prop="methodName">
                    <Input v-model="quartzData.methodName"  type="text"></Input>
                </FormItem>
                <FormItem label="方法参数：" prop="methodParams">
                    <Input v-model="quartzData.methodParams"  type="text"></Input>
                </FormItem>
                <FormItem label="cron表达式：" prop="cronExpression">
                    <Input v-model="quartzData.cronExpression" type="text"></Input>
                </FormItem>
                <FormItem label="执行策略：" prop="misfirePolicy">
                    <RadioGroup v-model="quartzData.misfirePolicy"  @on-change="misfirePolicyValChange">
                        <Radio label="0">默认</Radio>
                        <Radio label="1">继续执行</Radio>
                        <Radio label="2">等待执行</Radio>
                        <Radio label="3">放弃执行</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem label="状态：" prop="status">
                    <RadioGroup v-model="quartzData.status"  @on-change="statusValChange">
                        <Radio label="0">正常</Radio>
                        <Radio label="1">暂停</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem label="备注：" prop="remark">
                    <Input v-model="quartzData.remark"  type="text"></Input>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>

<script>
    import columns from './components/integration_data.js';
    import formatDate from 'utils/time';
    import {
        queryIntegral
    } from './components/integration.js';

    export default {
        name: 'IntegralPool',
        components: {
        },
        data () {
            return {
                loading:true,
                page: 1,
                size: 20,
                columns: [],
                pageInfo: '',
                pageData: [],
                modelShow: false,
                multipleSelection: [],
                quartzData: {
                    misfirePolicy: 0,
                    status: 0
                },
                quartzRules: {
                    jobName: [{
                        required: true,
                        message: '请输入任务名称',
                        trigger: 'blur'
                    }],
                    jobGroup: [{
                        required: true,
                        message: '请输入任务组名',
                        trigger: 'blur'
                    }],
                    methodName: [{
                        required: true,
                        message: '请输入方法名称',
                        trigger: 'blur'
                    }],
                    cronExpression: [{
                        required: true,
                        message: '请输入表达式',
                        trigger: 'blur'
                    }]
                }
            };
        },
        methods: {
            initColumns () {
                this.columns = columns;
            },
            getData (page) {
                this.page = page;
                this.loading = true;
                queryIntegral(page,this.size).then(data => {
                    this.loading = false;
                    if (data.status === 200) {
                        this.pageInfo = data.data;
                        this.pageData = data.data.dataList;
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('服务器异常' + error);
                });
            },
            addIntegral() {
                this.modelShow = true;
            },
            misfirePolicyValChange (val) {
                this.quartzData.misfirePolicy = val;
            },
            statusValChange () {
                this.quartzData.status = val;
            },
            ok() {
                this.$refs['quartzForm'].validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        addQuartz(this.quartzData).then(data => {
                            this.loading = false;
                            if (data.status === 200) {
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
                        this.$Message.error('请输入完整信息');
                    }
                });
            },
            cancel() {
            },
            handleInfo (query) {
                this.$router.push({
                    name: 'build_info',
                    query: query
                });
            },
            handleSearch(){
                this.getData(1);
            },
            handleDel (val, index) {
                deleteQuartz(val.jobId).then(data => {
                    if (data.status === 200) {
                        this.$Message.success('删除成功');
                        this.getData(this.page);
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error(data.message);
                });
            },
            handleCellChange (val, index, key) {
                this.$Message.success('修改了第 ' + (index + 1) + ' 行列名为 ' + key + ' 的数据');
            },
            handleChange (val, index) {
                this.$Message.success('修改了第' + (index + 1) + '行数据');
            },
            handleStart (val, index, changeValue) {
                this.getData(this.page);
                if(changeValue === 0){
                    this.$Message.success('任务启用');
                }else{
                    this.$Message.success('任务暂停');
                }

            },
            handleInput (val) {
            },
            handleError (message) {
                this.getData(this.page);
                this.$Message.error(message);
            },
            handleRun (val, index) {
                this.$Message.success('执行成功！');
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }
        },
        created () {
            this.initColumns();
            this.getData(1);
        }
    };
</script>
