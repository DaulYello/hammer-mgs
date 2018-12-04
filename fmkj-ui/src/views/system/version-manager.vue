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
                        版本信息
                    </p>
                    <!--<Row>
                        <Input v-model="query.jobName" placeholder="请输入任务名称..." style="width: 200px" />
                        <Input v-model="query.methodName" placeholder="请输入方法名..." style="width: 200px" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    </Row>-->
                    <Row class="margin-top-10">
                        <span @click="addVersion"><Button type="primary" icon="android-add">新增</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <Table :loading="loading" @on-delete="handleDel" :data="pageData" :columns="columns"></Table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
        <Modal v-model="modelShow" title="版本信息" ok-text="保存" :loading="loading" @on-ok="ok" @on-cancel="cancel">
            <Form ref="quartzForm" :model="quartzData" :rules="quartzRules" :label-width="120">
                <FormItem label="下载链接：" prop="apkUrl">
                    <Input v-model="quartzData.apkUrl" placeholder="http://baidu.com" type="text"></Input>
                </FormItem>
                <FormItem label="版本号：" prop="apkVersion">
                    <Input v-model="quartzData.apkVersion" placeholder="1.0.1" type="text"></Input>
                </FormItem>
                <FormItem label="内容说明：" prop="upgradeMsg">
                    <textarea v-model="quartzData.upgradeMsg" style="width: 100%;height: 120px;"  type="text"></textarea>
                </FormItem>
            </Form>
        </Modal>

        <Modal title="提示信息" v-model="hintInfo" ok-text="确定" @on-ok="yes">
            <div>
                <Alert show-icon>注：确定要删除这个版本信息吗？</Alert>
            </div>
        </Modal>
    </div>
</template>

<script>
    import {formatDateByLong,formatDate} from 'utils/time';

    import {
        getVersionPage,
        saveVersion,
        deleteVersion
    } from 'api/system/system';
    export default {
        name: 'version-manager',
        components: {
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
                hintInfo:false,
                page: 1,
                size: 20,
                pageInfo: '',
                pageData: [],
                query:{},
                modelShow: false,
                multipleSelection: [],
                vid: '',
                quartzData: {
                    id:0
                },
                quartzRules: {
                    apkUrl: [{
                        required: true,
                        message: '请输入下载链接',
                        trigger: 'blur'
                    }],
                    apkVersion: [{
                        required: true,
                        message: '请输入版本号',
                        trigger: 'blur'
                    }],
                    upgradeMsg: [{
                        required: true,
                        message: '请输入版本信息说明',
                        trigger: 'blur'
                    }]
                },
                columns: [
                    {
                        title: '序号',
                        type: 'index',
                        width: 80,
                        key: 'id',
                        align: 'center'
                    },
                    {
                        title: '下载地址',
                        key: 'apkUrl',
                        align: 'center'
                    },
                    {
                        title: '版本号',
                        align: 'center',
                        key: 'apkVersion'
                    },
                    {
                        title: '内容说明',
                        align: 'center',
                        key: 'upgradeMsg'
                    },
                    {
                        title: '创建时间',
                        align: 'center',
                        width: 150,
                        key: 'createTime',
                        render: (h, params) => {
                            if (params.row.createTime != null) {
                                return h('div',formatDateByLong(params.row.createTime,"yyyy-MM-dd h:m:s"));
                            }
                        }
                    },
                    {
                        title: '修改时间',
                        align: 'center',
                        width: 150,
                        key: 'updateTime',
                        render: (h, params) => {
                            if (params.row.updateTime != null) {
                                return h('div', formatDateByLong(params.row.updateTime, "yyyy-MM-dd h:m:s"));
                            }
                        }
                    },
                    {
                        title: '操作',
                        align: 'center',
                        width: 150,
                        key: 'handle',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'info',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.quartzData.id = params.row.id;
                                            this.quartzData.apkUrl = params.row.apkUrl;
                                            this.quartzData.apkVersion = params.row.apkVersion;
                                            this.quartzData.upgradeMsg = params.row.upgradeMsg;
                                            this.modelShow = true;
                                        }
                                    }
                                }, '编辑'),
                                h('Button',{
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.vid = params.row.id;
                                            this.hintInfo = true;
                                        }
                                    }
                                },"删除")
                            ]);
                        }
                    }
                ]
            };
        },
        methods: {
            getData (page) {
                this.page = page;
                this.loading = true;
                getVersionPage(page,this.size,this.query).then(data => {
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
            addVersion() {
                this.ticketData = {};
                this.modelShow = true;
            },
            misfirePolicyValChange (val) {
                this.quartzData.misfirePolicy = val;
            },
            statusValChange (val) {
                this.quartzData.status = val;
            },
            yes() {
                deleteVersion(this.vid).then(data => {
                    if (data.status === 200) {
                        this.$Message.success('删除成功！');
                        this.getData(this.page, this.userStatus);
                    } else {
                        this.$Message.error(data.message);
                        this.getData(this.page, this.userStatus);
                    }
                }).catch(error => {
                    this.$Message.error('删除出现异常：' + error);
                });
            },
            ok() {
                this.$refs['quartzForm'].validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        saveVersion(this.quartzData).then(data => {
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
                this.quartzData.id = 0;
                this.quartzData.apkUrl = '';
                this.quartzData.apkVersion = '';
                this.quartzData.upgradeMsg = '';
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
            handleInput (val) {
            },
            handleError (message) {
                this.getData(this.page);
                this.$Message.error(message);
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }
        },
        created () {
            /*this.initColumns();*/
            this.getData(1);
        }
    };
</script>