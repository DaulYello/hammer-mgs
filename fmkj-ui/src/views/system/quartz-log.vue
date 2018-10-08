<style lang="less">
    @import '../../styles/common.less';
    @import '../../styles/table/table.less';
</style>

<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card showHead="false">
                    <Row >
                        <Input v-model="query.jobName" placeholder="请输入任务名称..." style="width: 200px" />
                        <Input v-model="query.methodName" placeholder="请输入方法名称..." style="width: 200px" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    </Row>
                    <Row class="margin-top-10">
                        <span @click="deleteQuartz"><Button type="error" icon="android-remove">删除</Button></span>
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
import canEditTable from './components/canEditTable.vue';
import {
    formatDateByLong,
    formatDate
} from 'utils/time';
import {
  getQuartLogPage,
  deleteQuartzLog
} from 'api/system/system';
export default {
    name: 'QuartLog',
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
            pageData: [],
            query:{},
            multipleSelection: [],
            columns: [
                {
                    title: '序号',
                    type: 'selection',
                    width: 80,
                    key: 'jobLogId',
                    align: 'center'
                },
                {
                    title: '任务日志编号',
                    align: 'center',
                    key: 'jobLogId'
                },
                {
                    title: '任务名称',
                    align: 'center',
                    key: 'jobName'
                },
                {
                    title: '任务组名',
                    align: 'center',
                    key: 'jobGroup'
                },
                {
                    title: '方法名称',
                    align: 'center',
                    key: 'methodName'
                },
                {
                    title: '方法参数',
                    align: 'center',
                    key: 'methodParams'
                },
                {
                    title: '日志信息',
                    align: 'center',
                    width: 200,
                    key: 'jobMessage'
                },
                {
                    title: '状态',
                    align: 'center',
                    key: 'status',
                    render: (h, params) => {
                        const color = params.row.status === '0' ? 'green' : 'red';
                        const text = params.row.status === '0' ? '正常': '失败';
                        return h('Tag', {
                            props: {
                                color: color
                            }
                        }, text);
                    }
                },
                {
                    title: '创建时间',
                    align: 'center',
                    key: 'createTime',
                    render: (h, params) => {
                        return h('div',formatDateByLong(params.row.createTime,"yyyy-MM-dd h:m:s"));
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
                                    type: 'error',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        deleteQuartzLog(params.row.jobLogId).then(data => {
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
            getQuartLogPage(page,this.size,this.query).then(data => {
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
        deleteQuartz () {
            // 转字符串到后台
            let ids = this.multipleSelection.map(item => item.jobLogId).join()
            deleteQuartzLog(ids).then(data => {
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
