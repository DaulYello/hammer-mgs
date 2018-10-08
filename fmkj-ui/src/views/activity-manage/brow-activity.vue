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
                        <Input v-model="query.id" placeholder="请输入活动ID..." style="width: 200px" />
                        <Input v-model="query.pname" placeholder="请输入产品名称..." style="width: 200px" />
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
import {
    formatDateByLong,
    formatDate
} from 'utils/time';
import {
    queryActivityPage
} from 'api/hammer/race-activit';
export default {
    name: 'BrowAcitivity',
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
            page: 1,
            size: 20,
            pageInfo: '',
            pageData: [],
            query:{},
            tabIndex: 0,
            tabStatus: 1,
            columns: [
                {
                    title: '序号',
                    type: 'selection',
                    key: 'id',
                    align: 'center'
                },
                {
                    title: '活动ID',
                    key: 'id',
                    align: 'center'
                },
                {
                    title: '发起人',
                    align: 'center',
                    key: 'nickname1',
                    editable: true
                },
                {
                    title: '发起时间',
                    align: 'center',
                    key: 'time',
                    editable: true,
                    render: (h, params) => {
                        return h('div',formatDateByLong(params.row.time,"yyyy-MM-dd hh:mm:ss"));
                    }
                },
                {
                    title: '发起地点',
                    align: 'center',
                    key: 'price',
                    editable: true
                },
                {
                    title: '门票类型',
                    align: 'center',
                    key: 'typeid',
                    render: (h, params) => {
                        const row = params.row;
                        const text = row.typeid === 1 ? 'A类型' : row.typeid === 2 ? 'B类型' : 'C类型';
                        return h('span',text);
                    }
                },
                {
                    title: '保证金',
                    align: 'center',
                    key: 'bond',
                    editable: true
                },
                {
                    title: '活动状态',
                    align: 'center',
                    key: 'status',
                    render: (h, params) => {
                        let text = '';
                        if (params.row.status === 0) {
                            text = '未开始'
                        } else if (params.row.status === 1) {
                            text = '已上线'
                        } else if (params.row.status === 2) {
                            text = '已下线'
                        } else if (params.row.status === 3) {
                            text = '审核不通过'
                        } else if (params.row.status === 4) {
                            text = '待参与'
                        } else if (params.row.status === 5) {
                            text = '待竞锤'
                        } else if (params.row.status === 6) {
                            text = '待公布'
                        } else if (params.row.status === 7) {
                            text = '竞锤成功'
                        } else {
                            text = 'No Identify !'
                        }
                        return h('span',text);
                    }
                },
                {
                    title: '活动类型',
                    align: 'center',
                    key: 'activitytype',
                    editable: true
                },
                {
                    title: '产品名称',
                    align: 'center',
                    key: 'pname',
                    editable: true
                },
                {
                    title: '产品数量',
                    align: 'center',
                    key: 'price',
                    editable: true
                },
                {
                    title: '产品溢价',
                    align: 'center',
                    key: 'premium',
                    editable: true
                },
                {
                    title: '图片查看',
                    align: 'center',
                    key: 'picture',
                    editable: true
                },
                {
                    title: '审核状态',
                    align: 'center',
                    key: 'ispass',
                    render: (h, params) => {
                        const row = params.row;
                        const text = row.ispass === 1 ? '已通过' : row.ispass === 2 ? '未通过' : '未审核';
                        return h('span',text);
                    }
                }
            ]
        };
    },
    methods: {
        getData (page, index) {
          this.tabIndex = index;
          this.tabStatus = index + 1;
          console.log('马克思达到');
          console.log(this.tabIndex);
          console.log(this.tabStatus);
          this.page = page;
          this.loading = true;
          queryActivityPage(page,this.size, this.tabStatus, this.query).then(data => {
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
        handleSearch () {
            this.getData(1, this.tabIndex);
        }
    }
};
</script>
