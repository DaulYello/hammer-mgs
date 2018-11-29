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
                    </Row>
                    <Row class="margin-top-10">
                        <span @click="showAdd"><Button type="primary" icon="android-add">新增</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <Table :loading="loading"  @on-selection-change="handleSelectionChange" @on-select-all="handleSelectionChange" @on-select="handleSelectionChange" @on-delete="handleDel" @on-router="handleInfo" @on-change="handleChange" @on-error="handleError"  refs="multipleTable" :data="pageData" :columns="columns"></Table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
        <Modal title="攻略图片" v-model="showDialog">
            <div class="demo-upload-list" v-for="(item,index) in picturePathList">
                <template>
                    <span style="font-weight: bold">攻略图片-{{index + 1}}</span>
                    <img :src="item"  style="width: 100%" title="index">
                </template>
            </div>
        </Modal>
        <Modal v-model="modelShow" title="任务攻略" ok-text="保存" :loading="loading" @on-ok="ok" @on-cancel="cancel">
            <Form ref="strategyForm" :model="strategyData" :rules="strategyRules" :label-width="120">
                <FormItem label="任务名称 : " prop="tid">
                    <Select v-model="strategyData.tid" placeholder="请选择任务">
                        <Option v-for="item in taskList" :value="item.id" :key="item.id" name="tid">
                            {{item.title }}
                        </Option>
                    </Select>
                </FormItem>
                <FormItem label="攻略内容：" prop="strategy">
                    <textarea v-model="strategyData.strategy" type="text" style="width: 100%; height: 200px"></textarea>
                </FormItem>
                <FormItem label="展示顺序：" prop="orderNum">
                    <Input v-model="strategyData.orderNum"  placeholder="只能输入数字" number></Input>
                </FormItem>
               <FormItem label="攻略图片：" prop="strategyImage">
                    <Alert show-icon>注：多张图片必须按照显示的顺序上传</Alert>
                    <task-upload @uploadSuccess="uploadOk"></task-upload>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>

<script>
import canEditTable from './components/canEditTable.vue';
import {
    formatDateByLong,
    formatDate
} from 'utils/time';
import {
  getStrategyPage,
  deleteStrategy,
  saveStrategy
} from 'api/task/task';
import taskUpload from "../my-components/file-upload/task-upload.vue";
export default {
    name: 'strategy',
    components: {
        taskUpload,
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
            showDialog:false,
            modelShow: false,
            picturePathList: [],
            page: 1,
            size: 20,
            pageInfo: '',
            imageIdStr: '',
            pageData: [],
            taskList:[],
            id: 0,
            strategyData: {},
            strategyRules: {
                strategy: [{
                    required: true,
                    message: '请输入攻略内容',
                    trigger: 'blur'
                }],
                orderNum: [{
                    required: true,
                    type: 'number',
                    message: '请输入展示顺序',
                    trigger: 'blur'
                }]
            },
            query:{},
            multipleSelection: [],
            columns: [
                {
                    title: '序号',
                    type: 'selection',
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
                    title: '顺序',
                    align: 'center',
                    key: 'orderNum'
                },
                {
                    title: '攻略',
                    align: 'center',
                    key: 'strategy'
                },
                {
                    title: '攻略图片',
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
                                            this.picturePathList=params.row.imageUrl.split(",");
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
                    title: '修改时间',
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
                                        this.id = params.row.id;
                                        this.strategyData.id = params.row.id;
                                        this.strategyData.tid = params.row.tid;
                                        this.strategyData.strategy = params.row.strategy;
                                        this.strategyData.orderNum = params.row.orderNum;
                                        this.imageIdStr = '';
                                        this.modelShow = true;
                                    }
                                }
                            }, '编辑'),
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
                                        deleteStrategy(params.row.id).then(data => {
                                            if (data.status === 200) {
                                                this.$Message.success(data.message);
                                                this.getData(this.page, this.userStatus);
                                            } else {
                                                this.$Message.error(data.message);
                                                this.getData(this.page, this.userStatus);
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
    methods: {
        getData (page) {
            this.page = page;
            this.loading = true;
            getStrategyPage(page,this.size,this.query).then(data => {
            this.loading = false;
            if (data.status === 200) {
              this.pageInfo = data.data.strategy;
              this.pageData = data.data.strategy.dataList;
              this.taskList = data.data.task;
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
            this.$refs['strategyForm'].validate((valid) => {
                if (valid) {
                    this.loading = true;
                    this.strategyData.id = this.id;
                    this.strategyData.imageIdStr = this.imageIdStr;
                    saveStrategy(this.strategyData).then(data => {
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
        uploadOk(val){
            if(this.imageIdStr){
                this.imageIdStr = this.imageIdStr + ',' + val.data
            }else{
                this.imageIdStr = val.data
            }
        },
        showAdd() {
            this.id = 0;
            this.imageIdStr = '';
            this.strategyData = {};
            this.modelShow = true;
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
