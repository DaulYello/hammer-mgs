<style lang="less">
    @import '../../styles/common.less';
    @import '../../styles/table/table.less';
</style>

<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card :showHead="false">
                    <Row>
                        <Input v-model="query.cdbid" placeholder="请输入锤多宝ID..." style="width: 200px" />
                        <Input v-model="query.telephone" placeholder="请输入电话号码..." style="width: 200px" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <Table :loading="loading"  refs="table2" :data="pageData" :columns="columns"></Table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>

        </Row>
    </div>
</template>

<script>
import formatDate from 'utils/time';
import {
  getBlackPage,
  undo
} from 'api/hammer/race-black';
export default {
    name: 'RaceBlack',
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
            query: {},
            tabIndex: 0,
            status: 1,
            columns: [
                {
                    title: '序号',
                    type: 'index',
                    width: 80,
                    key: 'id',
                    align: 'center'
                },
                {
                    title: '锤多宝ID',
                    key: 'cdbid',
                    align: 'center'
                },
                {
                    title: '手机号',
                    align: 'center',
                    key: 'telephone',
                    editable: true
                },
                {
                    title: '头像',
                    align: 'center',
                    key: 'logo',
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
                    title: '姓名',
                    align: 'center',
                    key: 'name',
                    editable: true
                },
                {
                    title: 'P能量',
                    align: 'center',
                    key: 'myP',
                    editable: true
                },
                {
                    title: '等级',
                    align: 'center',
                    key: 'gradeId',
                    editable: true
                },
                {
                    title: '是否原住民',
                    align: 'center',
                    key: 'isboong',
                    render: (h, params) => {
                        const row = params.row;
                        const text = row.isboong === 1 ? '是' : '否';
                        return h('span',text);
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
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        undo(params.row.id, this.status).then(data => {
                                            if (data.status === 200) {
                                                this.$Message.success('撤回成功');
                                                this.getData(this.page, this.tabIndex);
                                            } else {
                                                this.$Message.error(data.message);
                                                this.getData(this.page, this.tabIndex);
                                            }
                                        }).catch(error => {
                                            this.$Message.error('处理异常：' + error);
                                        });
                                    }
                                }
                            }, '撤回')
                        ])
                    }
                }
            ]
        };
    },
    methods: {
        getData (page, index) {
          // 根据页签的下标值得出后台查询参数status的值、1白名单  2黑名单
          this.tabIndex = index
          if (this.tabIndex === 0) {
              this.status = 1;
          }
          if (this.tabIndex === 1) {
              this.status = 2;
          }
          this.page = page;
          this.loading = true;
          getBlackPage(page,this.size, this.status, this.query).then(data => {
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

        handleSearch(){
            this.getData(1, this.tabIndex);
        }
    },
    created () {
        if (this.isLoad) {
            this.getData(1, 0);
        }
    }
};
</script>
