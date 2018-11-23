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
                        任务列表
                    </p>
                    <Row>
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
                        <Form :label-width="80" style="float: left;margin-right: 10px;">
                            <FormItem label="下载状态 : " prop="typeValue">
                                <Select style="width:200px" v-model="query.typeValue" :clearable="true">
                                    <Option v-for="item in typeList" :value="item.value" :key="item.value" name="typeValue">
                                        {{item.label }}
                                    </Option>
                                </Select>
                            </FormItem>
                        </Form>
                        <Input v-model="query.title" placeholder="请输入任务标题..." style="width: 200px" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    </Row>
                    <Row class="margin-top-10">
                        <span @click="addTask"><Button type="primary" icon="android-add">新增</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <can-edit-table :loading="loading" @input="handleInput" @on-modelShow="editModel" @on-detailShow="detailList" @on-delete="handleDel" @on-show="showLogo" @on-router="handleInfo" @on-change="handleChange" @on-run="handleRun" @on-start="handleStart" @on-error="handleError"  refs="multipleSelection" v-model="pageData" :columns-list="columns"></can-edit-table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
        <Modal v-model="modelShow" title="任务" ok-text="保存" :loading="loading" @on-ok="ok" @on-cancel="cancel">
            <Form ref="quartzForm" :model="quartzData" :rules="quartzRules" :label-width="120">
                <FormItem label="任务标题：" prop="title">
                    <Input v-model="quartzData.title" type="text"></Input>
                </FormItem>
                <FormItem label="任务目标：" prop="taskTarget">
                    <Input v-model="quartzData.taskTarget" type="text"></Input>
                </FormItem>
                <FormItem label="开始时间：" prop="startDate">
                    <div>
                        <DatePicker v-model="quartzData.startDate" type="datetime" style="width:200px;" placeholder="选择开始日期和时间" ></DatePicker>
                    </div>
                </FormItem>
                <FormItem label="结束时间：" prop="endDate">
                    <div>
                        <DatePicker v-model="quartzData.endDate" type="datetime" style="width:200px;" placeholder="选择结束日期和时间" ></DatePicker>
                    </div>
                </FormItem>

                <FormItem label="二级描述：" prop="subDesc">
                    <Input v-model="quartzData.subDesc"  type="text"></Input>
                </FormItem>
                <FormItem label="任务头像：" prop="logoid">
                    <Row type="flex" align="middle" class="height-100">
                        <task-upload @uploadSuccess="uploadLogo"></task-upload>
                    </Row>
                </FormItem>
                <FormItem label="详情图片：" prop="imageid">
                    <Row type="flex" align="middle" class="height-100">
                        <task-upload @uploadSuccess="uploadImage"></task-upload>
                    </Row>
                </FormItem>
                <FormItem label="任务奖励：" prop="reward">
                    <Input v-model="quartzData.reward"  type="text"></Input>
                </FormItem>
                <FormItem label="审核周期：" prop="auditCycle">
                    <Input v-model="quartzData.auditCycle" type="text"></Input>
                </FormItem>
                <FormItem label="下载地址：" prop="downUrl">
                    <Input v-model="quartzData.downUrl" type="text"></Input>
                </FormItem>
                <FormItem label="任务状态：" prop="status">
                    <RadioGroup v-model="quartzData.status"  @on-change="statusValChange">
                        <Radio label="0">正常</Radio>
                        <Radio label="-1">删除</Radio>
                    </RadioGroup>
                </FormItem>

                <FormItem label="是否下载APP：" prop="type">
                    <RadioGroup v-model="quartzData.type"  @on-change="typeValChange">
                        <Radio label="0">不需要</Radio>
                        <Radio label="1">需要</Radio>
                    </RadioGroup>
                </FormItem>
            </Form>
        </Modal>

        <Modal v-model="detalShow" title="提示信息" ok-text="保存" :loading="loading" @on-ok="ok" @on-cancel="cancel">

        </Modal>
        <Modal title="预览图片" v-model="showDialog">
            <img :src="picturePath"  style="width: 100%">
        </Modal>
    </div>
</template>

<script>
    import canEditTable from './components/canEditTable.vue';
    import {columns} from './components/task_data.js';
    import formatDate from 'utils/time';
    import {
        getTaskList,
        taskAddAndModify,
        updateTask,
        deleteTask
    } from 'api/task/task';
    import TaskUpload from "../my-components/file-upload/task-upload";
    export default {
        name: 'task-list',
        components: {
            TaskUpload,
            canEditTable
        },
        data () {
            return {
                showDialog:false,
                loading:true,
                page: 1,
                size: 20,
                columns: [],
                pageInfo: '',
                pageData: [],
                query:{},
                typeList:[
                    {
                        value: '0',
                        label: '不需要'
                    },
                    {
                        value: '1',
                        label: '需要'
                    }
                ],
                statusList:[
                    {
                        value: '0',
                        label: '正常'
                    },
                    {
                        value: '-1',
                        label: '删除'
                    }
                ],
                modelShow: false,
                detalShow: false,
                picturePath:'',
                quartzData: {
                    logoid: '',
                    imageid: '',
                    type: 1,
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
                getTaskList(page,this.size,this.query).then(data => {
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
            addTask() {
                this.modelShow = true;
            },
            misfirePolicyValChange (val) {
                this.quartzData.misfirePolicy = val;
            },
            statusValChange (val) {
                this.quartzData.status = val;
            },
            typeValChange (val) {
                this.quartzData.type = val;
            },
            editModel(val){
                this.quartzData.id = val.id;
                this.quartzData.title = val.title;
                this.quartzData.taskTarget = val.taskTarget;
                this.quartzData.subDesc = val.subDesc;
                this.quartzData.reward = val.reward;
                this.quartzData.auditCycle = val.auditCycle;
                this.quartzData.downUrl = val.downUrl;
                this.quartzData.startDate = val.startDate;
                this.quartzData.endDate = val.endDate;
                this.quartzData.status = val.status;
                this.quartzData.type = val.type;
                this.modelShow = true;
            },
            detailList(){
                this.detalShow = true;
            },
            ok() {
                this.$refs['quartzForm'].validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        this.showDialog = false;
                        taskAddAndModify(this.quartzData).then(data => {
                            this.loading = false;
                            if (data.status === 200) {
                                this.cancel();
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
            uploadFile(){
                console.log("就开始======")
            },
            cancel() {
                this.quartzData.id = '';
                this.quartzData.title = '';
                this.quartzData.taskTarget = '';
                this.quartzData.subDesc = '';
                this.quartzData.reward = '';
                this.quartzData.auditCycle = '';
                this.quartzData.downUrl = '';
                this.quartzData.startDate = '';
                this.quartzData.endDate = '';
                this.quartzData.status = 0;
                this.quartzData.type = 1;
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
                console.log("要删除的任务id："+val.id);
                deleteTask(val.id).then(data => {
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
            showLogo(val,item){
                if (item === "logoShow") {
                    this.picturePath = val.logoUrl;
                }
                if (item === "detalShow") {
                    this.picturePath = val.imageUrl;
                }
                this.showDialog = true;
            },
            handleCellChange (val, index, key) {
                this.getData(this.page);
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
            uploadLogo(val){
                this.quartzData.logoid=val.data
            },
            uploadImage(val){
                this.quartzData.imageid=val.data
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
