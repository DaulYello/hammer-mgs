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
                        <Input v-model="query.nickName" placeholder="请输入昵称..." style="width: 200px" />
                        <Input v-model="query.idKey" placeholder="请输入主键ID..." style="width: 200px" />
                        <div class="serachStyle">
                            <DatePicker v-model="query.starttime" type="datetime" style="width:200px;" placeholder="选择开始日期和时间" ></DatePicker>
                        </div>
                        <div style="margin-top: 6px;float: left;margin-left: 5px;margin-right: 5px"> 至 </div>
                        <div class="serachStyle">
                            <DatePicker v-model="query.endtime" type="datetime" style="width:200px;" placeholder="选择结束日期和时间" ></DatePicker>
                        </div>
                        <Form :label-width="80" style="float: left;">
                            <FormItem label="任务状态 : " prop="statusValue">
                                <Select style="width:200px" v-model="query.statusValue" :clearable="true">
                                    <Option v-for="item in statusList" :value="item.value" :key="item.value" name="statusValue">
                                        {{item.label }}
                                    </Option>
                                </Select>
                            </FormItem>
                        </Form>
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>

                    </Row>
                    <Row class="margin-top-10">
                        <Button type="primary" icon="ios-download" @click="handExport">
                            <span>导出</span>
                        </Button>
                    </Row>
                    <div class="margin-top-10">
                        <Table :loading="loading"  @on-selection-change="handleSelectionChange" @on-select-all="handleSelectionChange" @on-select="handleSelectionChange" @on-delete="handleDel" @on-router="handleInfo" @on-change="handleChange" @on-error="handleError"  refs="multipleTable" :data="pageData" :columns="columns"></Table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
        <Modal title="审核图片" v-model="showDialog">
            <div class="demo-upload-list" v-for="(item, index) in uploadList">
                <template>
                    <span style="font-weight: bold">审核图片-{{index + 1}}</span>
                    <img :src="item"  style="width: 100%">
                </template>
            </div>
        </Modal>
        <Modal v-model="modelShow" title="审核" :footerHide=true :loading="loading" @on-cancel="cancel">
            <Form ref="partForm" :model="auditData" :rules="auditRules" :label-width="120">
                <FormItem label="审核意见：" prop="auditOption">
                    <textarea v-model="auditData.auditOption" type="text" placeholder="请输入审核意见，如果是通过默认请写‘同意’" style="width: 100%; height: 200px"></textarea>
                </FormItem>
                <FormItem align="right">
                    <i-button type="primary" size="large"  @click.native="ok">通过</i-button>
                    <i-button type="error" size="large"  @click.native="rejected">驳回</i-button>
                </FormItem>
            </Form>
        </Modal>

        <Modal v-model="moreShow" title="更多审核内容" :loading="loading" :footerHide="true" :width="800">
            <task-part-msg ref="partMsg" ></task-part-msg>
        </Modal>

    </div>
</template>

<script>
import canEditTable from './components/canEditTable.vue';
import TaskPartMsg from "./task-part-msg";
import {
    formatDateByLong,
    formatDate
} from 'utils/time';
import {
  getPartPage,
  auditPart,
  exportPart
} from 'api/task/task';
export default {
    name: 'part',
    components: {
        canEditTable,
        TaskPartMsg
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
            showDialog:false,
            modelShow: false,
            moreShow: false,
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
            auditId: '',
            auditUid: '',
            auditTid: '',
            pageData: [],
            query:{},
            multipleSelection: [],
            statusList:[
                {
                    value: '0',
                    label: '未提交审核'
                },
                {
                    value: '1',
                    label: '待审核'
                },
                {
                    value: '2',
                    label: '审核通过'
                },
                {
                    value: '-1',
                    label: '已驳回'
                }
            ],
            columns: [
                {
                    title: '索引主键',
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
                    title: '任务奖励',
                    align: 'center',
                    key: 'reward'
                },
                {
                    title: '用户编号',
                    align: 'center',
                    key: 'uid'
                },
                {
                    title: '用户昵称',
                    align: 'center',
                    key: 'nickName'
                },
                {
                    title: '用户CNT',
                    align: 'center',
                    key: 'cnt'
                },
                {
                    title: '审核状态',
                    align: 'center',
                    width: 200,
                    key: 'auditStatus',
                    render: (h, params) => {
                        const row = params.row;
                        const color = row.auditStatus === 1 ? 'red' : row.auditStatus === 2 ? 'green' : row.auditStatus === -1 ? 'yellow' : 'gray';
                        const text = row.auditStatus === 1 ? '待审核' : row.auditStatus === 2 ? '审核通过' : row.auditStatus === -1 ? '已驳回' :'未提交审核';
                        return h('Tag', {
                            props: {
                                type: 'dot',
                                color: color
                            }
                        }, text);
                    }
                },
                {
                    title: '审核意见',
                    align: 'center',
                    key: 'auditOption'
                },
                {
                    title: '审核内容',
                    align: 'center',
                    key: 'telephone',
                    render: (h, params) => {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        this.moreShow = true;
                                        this.$refs.partMsg.getMartMsgData(params.row.id);
                                    }
                                }
                            }, '审核内容')
                        ])
                    }
                },
                {
                    title: '审核图片',
                    align: 'center',
                    key: 'imageUrl',
                    render:(h,params)=>{
                        return h('div',[
                            h('Button',{
                                props:{
                                    type: 'primary',
                                    size:'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        if(params.row.imageUrl){
                                            this.uploadList = params.row.imageUrl.split(",");
                                            this.showDialog = true;
                                        }else{
                                            this.$Message.error("没有图片");
                                        }
                                    }
                                }
                            },"阅览")
                        ])
                    }
                },
                {
                    title: '创建时间',
                    align: 'center',
                    key: 'createDate',
                    render: (h, params) => {
                        return h('div',formatDateByLong(params.row.createDate,"yyyy-MM-dd h:m:s"));
                    }
                },
                {
                    title: '审核时间',
                    align: 'center',
                    key: 'updateDate',
                    render: (h, params) => {
                        if(params.row.updateDate) {
                            return h('div',formatDateByLong(params.row.updateDate,"yyyy-MM-dd h:m:s"));
                        }
                    }
                },
                {
                    title: '操作',
                    align: 'center',
                    key: 'handle',
                    render: (h, params) => {
                        if(params.row.auditStatus === 1){
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'warning',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.modelShow = true;
                                            this.auditId = params.row.id;
                                            this.auditUid = params.row.uid;
                                            this.auditTid = params.row.tid;
                                        }
                                    }
                                }, '审核')
                            ])
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
        handExport () {
            this.$Modal.confirm({
                title: '导出提示',
                content: '<p>注：默认导出所有待审核的数据,如需按条件导出请自行选择查询条件!</p>',
                loading: true,
                okText: '导出',
                onOk: () => {
                    exportPart(this.query).then(data => {
                        if (data.status === 200) {
                            setTimeout(() => {
                                this.$Modal.remove();
                                this.$Message.info('导出成功!');
                            }, 1000);
                        } else {
                            this.$Modal.remove();
                            this.$Message.error(data.message);
                        }
                    }).catch(error => {
                        this.$Modal.remove();
                        this.$Message.error("服务器异常:" + error)
                    });
                }
            });
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
            this.getData(this.page, this.userStatus);
            this.$Message.error(message);
        },
        ok() {
            this.$refs['partForm'].validate((valid) => {
                if (valid) {
                    this.loading = true;
                    auditPart(this.auditData.auditOption, this.auditId, this.auditUid, this.auditTid, 2).then(data => {
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
        rejected(){
            this.$refs['partForm'].validate((valid) => {
                if (valid) {
                    this.loading = true;
                    auditPart(this.auditData.auditOption, this.auditId, this.auditUid,this.auditTid, -1).then(data => {
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
