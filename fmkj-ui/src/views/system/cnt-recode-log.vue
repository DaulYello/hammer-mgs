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
                        <Input v-model="query.nickname" placeholder="获得CNT用户昵称。。。" style="width: 200px;margin-left: 50px;" />
                        <Form :label-width="80" :inline="inline" style="float: left;">
                            <FormItem label="获取渠道 : " prop="sendValue">
                                <Select style="width:200px" v-model="query.sendValue" :clearable="true">
                                    <Option v-for="item in stateList" :value="item.value" :key="item.value" name="sendValue">
                                        {{item.label }}
                                    </Option>
                                </Select>
                            </FormItem>
                        </Form>
                        <Form :label-width="80" :inline="inline" style="float: left;">
                            <FormItem label="所属类型 : " prop="typeValue">
                                <Select style="width:200px" v-model="query.typeValue" :clearable="true">
                                    <Option v-for="item in typeList" :value="item.value" :key="item.value" name="typeValue">
                                        {{item.label }}
                                    </Option>
                                </Select>
                            </FormItem>
                        </Form>
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
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
    /*import canEditTable from './components/canEditTable.vue';*/
    import {
        formatDateByLong,
        formatDate
    } from 'utils/time';
    import {
        getFmRecyleLogs
    } from 'api/system/system';
    export default {
        name: "cnt-recode-log",
        components: {
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
                inline:false,
                stateList: [
                    {
                        value: '0',
                        label: '用户自己收取'
                    },
                    {
                        value: '1',
                        label: '定时任务回收'
                    },
                    {
                        value: '2',
                        label: '释放CNT时没有用户回收'
                    },
                    {
                        value: '3',
                        label: '未中锤奖励'
                    },
                    {
                        value: '4',
                        label: '周排行奖励'
                    },
                    {
                        value: '5',
                        label: '被用户偷取'
                    },
                    {
                        value: '6',
                        label: '参与活动扣除'
                    },
                    {
                        value: '7',
                        label: '邀请好友获得'
                    },
                    {
                        value: '8',
                        label: '确认收货后获取'
                    },
                    {
                        value: '9',
                        label: '活动失败后返回CNT'
                    },
                    {
                        value: '10',
                        label: '完成任务奖励'
                    },
                    {
                        value: '11',
                        label: '公司账户自然增长'
                    }
                ],
                typeList:[
                    {
                        value: '1',
                        label: 'CNT'
                    },
                    {
                        value: '2',
                        label: 'R积分'
                    }
                ],
                value:'',
                multipleSelection:[],
                columns:[
                    {
                        title: '序号',
                        align: 'center',
                        key: 'id'
                    },
                    {
                        title: '获得CNT用户',
                        align: 'center',
                        key: 'nickname'
                    },
                    {
                        title: '失去CNT用户',
                        align: 'center',
                        key: 'nicknamef'
                    },
                    {
                        title: 'CNT/R数量',
                        align: 'center',
                        key: 'takeNum'
                    },
                    {
                        title: '收取时间',
                        align: 'center',
                        key: 'takeDate',
                        render: (h, params) => {
                            return h('div',formatDateByLong(params.row.takeDate,'yyyy-MM-dd h:s:m'));
                        }
                    },
                    {
                        title: '获取渠道',
                        align: 'center',
                        key: 'takeType',
                        render:(h,params) => {
                            //获取渠道:0、用户自己收取;1、定时任务回收;2、释放CNT时没有用户回收;3、未中锤奖励;4;周排行奖励;5被用户偷取;6参与活动扣除;7邀请好友获得;8确认收货后获取;
                            const text = params.row.takeType == 0 ? "用户自己收取" : params.row.takeType == 1 ? "定时任务回收"
                                : params.row.takeType == 2 ? "释放CNT时没有用户回收" : params.row.takeType == 3 ? "未中锤奖励"
                                : params.row.takeType == 4 ? "周排行奖励" : params.row.takeType == 5 ? "被用户偷取"
                                : params.row.takeType == 6 ? "参与活动扣除" : params.row.takeType == 7 ? "邀请好友获得"
                                : params.row.takeType == 8 ? "确认收货后获取" : params.row.takeType == 9 ? "活动失败后返回CNT"
                                : params.row.takeType == 10 ? "完成任务奖励" : params.row.takeType == 11 ? "公司账户自然增长" : '未知渠道'
                            return h('div',text);

                        }
                    },
                    {
                        title: '所属类型',
                        align: 'center',
                        width: 200,
                        key: 'recyleType',
                        render: (h,params) => {//所属类型1、CNT; 2、R积分
                            const text = params.row.recyleType == 1 ? "CNT" : "R积分";
                            return h('div',text);
                        }
                    },
                    {
                        title: '详细描述',
                        align: 'center',
                        key: 'takeMsg',
                    }
                ]
            };
        },
        methods:{
            getData (page) {
                this.page = page;
                this.loading = true;
                getFmRecyleLogs(page,this.size,this.query).then(data => {
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
