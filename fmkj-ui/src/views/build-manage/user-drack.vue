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
                        <Input v-model="query.telephone" placeholder="请输入用户电话..." style="width: 200px" />
                        <Input v-model="query.nickname" placeholder="请输入用户昵称..." style="width: 200px" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜用户</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <can-edit-table :loading="loading"  @on-delete="handleDel" @on-router="handleInfo" @on-change="handleChange" @on-error="handleError"  refs="table2" v-model="pageData" :columns-list="columns"></can-edit-table>
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
  getUserPage,
  delBuild,
  backUser
} from 'api/user/user';
import {
    formatDateByLong,
    formatDate
} from 'utils/time';
export default {
    name: 'UserDrack',
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
            userStatus: 0,
            columns: [
                {
                    title: '序号',
                    type: 'index',
                    width: 80,
                    key: 'id',
                    align: 'center'
                },
                {
                    title: '电话号码',
                    align: 'center',
                    key: 'telephone',
                    editable: true
                },
                {
                    title: '昵称',
                    align: 'center',
                    key: 'nickname',
                    editable: true
                },
                {
                    title: '邮箱',
                    align: 'center',
                    key: 'email',
                    editable: true
                },
                {
                    title: '名字',
                    align: 'center',
                    key: 'name',
                    editable: true
                },
                {
                    title: '证件号',
                    align: 'center',
                    key: 'cardnum',
                    editable: true
                },
                {
                    title: '资产',
                    align: 'center',
                    key: 'myP',
                    editable: true
                },
                {
                    title: '积分',
                    align: 'center',
                    key: 'score',
                    editable: true
                },
                {
                    title: '等级',
                    align: 'center',
                    key: 'gradeId',
                    editable: true
                },
                {
                    title: '身份验证',
                    align: 'center',
                    key: 'cardStatus',
                    render: (h, params) => {
                        const row = params.row;
                        const text = row.cardStatus === 1 ? '已验证' : '未验证';
                        return h('span',text);
                    }
                },
                {
                    title: '原住居民',
                    align: 'center',
                    key: 'isboong',
                    render: (h, params) => {
                        const row = params.row;
                        const text = row.isboong === 1 ? '是' : '否';
                        return h('span',text);
                    }
                },
                {
                    title: '锤宝ID',
                    align: 'center',
                    key: 'cdbid',
                    editable: true
                },
                {
                    title: '操作',
                    align: 'center',
                    key: 'handle',
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
                                        backUser(params.row.id).then(data => {
                                            if (data.status === 200) {
                                                this.$Message.success('恢复成功');
                                                this.getData(this.page, this.userStatus);
                                            } else {
                                                this.$Message.error(data.message);
                                                this.getData(this.page, this.userStatus);
                                            }
                                        }).catch(error => {
                                            this.$Message.error('恢复名单出现异常：' + error);
                                        });
                                    }
                                }
                            }, '恢复')
                        ])
                    }
                }
            ]
        };
    },
    methods: {
        getData (page, index) {
          this.userStatus = index;
          this.page = page;
          this.loading = true;
            getUserPage(page,this.size,this.userStatus,this.query).then(data => {
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
        //
        handleInfo (query) {
          this.$router.push({
              name: 'build_info',
              query: query
          });
        },
        handleDrack (val) {
            this.$Message.success('拉入黑名单成功');
            this.getData(this.page, val);
        },
        handleDrackErro (msg, val) {
            this.getData(this.page, val);
            this.$Message.error(message);
        },
        handleSearch(){
            this.getData(1, this.userStatus);
        },
        handleDel (val, index) {
            delBuild(this.pageData[index].buildId).then(data => {
              if (data.status === 200) {
                this.$Message.success('删除成功');
                this.getData(this.page);
              } else {
                this.$Message.error(data.message);
              }
            }).catch(error => {
              reject(error);
            });
            //this.$Message.success('删除了第' + (index + 1) + '行数据');
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
        }
    },
};
</script>
