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
                    <Page  style="text-align:center;margin-top:20px" @on-change="getDataPage" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
            <Modal title="阅览图片" v-model="showDialog">
                <img :src="picturePath" style="width:100%"/>
            </Modal>
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
    name: 'OfflineAcitivity',
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
            showDialog:false,
            loading:true,
            page: 1,
            size: 20,
            pageInfo: '',
            pageData: [],
            query:{},
            tabIndex: 0,
            picturePath:'',
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
                    key: 'startid',
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
                    title: '保证金',
                    align: 'center',
                    key: 'bond',
                    editable: true
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
                    key: 'pnumber',
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
                    key: 'imageurl',
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
                                        this.picturePath=params.row.imageurl;
                                        this.showDialog = true;
                                    }
                                }
                            },"阅览")
                        ])
                    }
                },
                {//活动(竟锤)的状态 0:待审核  1:驳回 2:活动中 3：已结束 4：活动异常 5：活动失败
                    title: '活动状态',
                    align: 'center',
                    key: 'status',
                    render: (h, params) => {
                        let text = '';
                        if (params.row.status === 0) {
                            text = '未审核'
                        } else if (params.row.status === 1) {
                            text = '驳回审核'
                        } else if (params.row.status === 2) {
                            text = '正在进行'
                        } else if (params.row.status === 3) {
                            text = '活动结束'
                        } else if (params.row.status === 4) {
                            text = '活动下线'
                        } else if (params.row.status === 5) {
                            text = '活动失败'
                        } else {
                            text = 'No Identify !'
                        }
                        return h('span',text);
                    }
                }
            ]
        };
    },
    methods: {
        getDataPage(page){
            this.getData(page,index)
        },
        getData (page, index) {
          this.tabIndex = index;
          this.tabStatus = index + 1;
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
        onCancel(){},
        handleSearch () {
            this.getData(1, this.tabIndex);
        }
    }
};
</script>
