<style lang="less">
    @import '../../styles/common.less';
    @import '../../styles/table/table.less';
</style>

<template>
    <div>
        <div class="margin-top-10">
            <can-modify-table :loading="loading" v-model="pageData"
                              :columns-list="columns" @input="handleInput" @on-delete="handleDel"></can-modify-table>
        </div>
        <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>

    </div>
</template>

<script>
    import canModifyTable from './components/canModifyTable.vue';
    import {
        formatDateByLong,
        formatDate
    } from 'utils/time';
    import {
        getPartExtendPage
    } from 'api/task/task';
    import CanModifyTable from "./components/canModifyTable";
    export default {
        name: 'task-part-msg',
        components: {
            CanModifyTable
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
                tid: '',
                pid: '',
                uploadList: [],
                pageData: [],
                query:{},
                columns: [
                    {
                        title: '序号',
                        type: 'index',
                        width: 80,
                        key: 'id',
                        align: 'center'
                    },
                    {
                        title: '提交信息',
                        align: 'center',
                        key: 'clo'
                    },
                    {
                        title: '提交内容',
                        align: 'center',
                        key: 'orderNum'
                    }
                ]
            };
        },
        methods: {
            getMartMsgData(id){
                this.query.pid = id;
                this.getData (1);
            },
            getData (page) {
                this.page = page;
                this.loading = true;
                getPartExtendPage(page,this.size,this.query).then(data => {
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
            cancel() {

            }
        }
    };
</script>
