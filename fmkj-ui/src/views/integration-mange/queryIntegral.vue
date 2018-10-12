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
                        <!--<Table :columns="columns" refs="multipleSelection" :data="pageData" border disabled-hover></Table>-->
                        <can-edit-table :loading="loading" @on-delete="handleDel" @on-change="handleChange"  @on-error="handleError"  refs="multipleSelection" v-model="pageData" :columns-list="columns"></can-edit-table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
        <Modal v-model="modelShow" title="年度积分" ok-text="保存" :loading="loading" @on-ok="ok" @on-cancel="cancel">
            <Form ref="integrateForm" :model="integtationData" :rules="integtateRules" :label-width="120">
                <FormItem label="投放年度：" prop="year">
                    <Input v-model="integtationData.year" type="text"></Input>
                </FormItem>
                <FormItem label="年度R积分投放：" prop="rintegralNum">
                    <Input v-model="integtationData.rintegralNum" type="text"></Input>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>

<script>
    import columns from './components/integration_data.js';
    import canEditTable from './components/canEditTable.vue';
    import formatDate from 'utils/time';
    import {
        queryIntegral,addIntegrate,dropIntegration
    } from 'api/integration/integration.js';

    export default {
        name: 'IntegralPool',
        components: {
            canEditTable
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
                integtationData: {
                    misfirePolicy: 0,
                    status: 0
                },
                integtateRules: {
                    year: [{
                        required: true,
                        message: '投放年度',
                        trigger: 'blur'
                    }],
                    rintegralNum: [{
                        required: true,
                        message: '年度R积分投放',
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
                this.integtationData.misfirePolicy = val;
            },
            statusValChange () {
                this.integtationData.status = val;
            },
            ok() {
                console.debug("1.come in~");
                this.$refs['integrateForm'].validate((valid) => {
                    console.debug("2.come in~");
                    if (valid) {
                        console.debug("3.come in~");
                        this.loading = true;
                        addIntegrate(this.integtationData).then(data => {
                            console.debug("4.come in~");
                            this.loading = false;
                            if (data.status === 200) {
                                this.$Message.success(data.message);
                                getData(this.page);
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
                dropIntegration(val.id).then(data => {
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
                console.log("xxxx:"+val);
                this.getData(this.page);
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
