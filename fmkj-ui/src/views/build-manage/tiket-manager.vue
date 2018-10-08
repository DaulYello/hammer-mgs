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
                        门票信息
                    </p>
                    <Row>
                        <Input v-model="query.type" placeholder="请输入门票类型..." style="width: 200px" />
                        <Input v-model="query.name" placeholder="请输入门票名称..." style="width: 200px" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    </Row>
                    <Row class="margin-top-10">
                        <span @click="addTicket"><Button type="primary" icon="android-add">新建票种</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <can-edit-table :loading="loading" @input="handleInput" @on-delete="handleDel" @on-router="handleInfo" @on-change="handleChange" @on-error="handleError"  refs="table2" v-model="pageData" :columns-list="columns"></can-edit-table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
        <Modal v-model="modelShow" title="新建门票" ok-text="保存" :loading="loading" @on-ok="ok" @on-cancel="cancel">
            <Form ref="tiketForm" :model="ticketData" :rules="ticketRules" :label-width="120">
                <FormItem label="门票类型：" prop="type">
                    <Input v-model="ticketData.type" type="text"></Input>
                </FormItem>
                <FormItem label="门票名称：" prop="name">
                    <Input v-model="ticketData.name" type="text"></Input>
                </FormItem>
                <FormItem label="门票单价：" prop="price">
                    <Input v-model="ticketData.price"  placeholder="只能输入数字" number></Input>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>

<script>
import canEditTable from './components/canEditTable.vue';
import columns from './components/tiket_data.js';
import formatDate from 'utils/time';
import {
  getTicketPage,
  add,
  deleteTiket
} from 'api/hammer/ticket';
export default {
    name: 'TickeManager',
    components: {
        canEditTable
    },
    data () {
        return {
            loading:true,
            page: 1,
            size: 20,
            columns: [],
            pageInfo: '',
            pageData: [],
            query:{},
            modelShow: false,
            ticketData: {},
            ticketRules: {
                type: [{
                    required: true,
                    message: '请输入门票类型',
                    trigger: 'blur'
                }],
                name: [{
                    required: true,
                    message: '请输入门票名称',
                    trigger: 'blur'
                }],
                price: [{
                    required: true,
                    type: 'number',
                    message: '请输入门票单价',
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
          getTicketPage(page,this.size,this.query).then(data => {
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
        addTicket() {
            this.ticketData = {};
            this.modelShow = true;
        },
        ok() {
            this.$refs['tiketForm'].validate((valid) => {
                if (valid) {
                    this.loading = true;
                    add(this.ticketData).then(data => {
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
            deleteTiket(val.id).then(data => {
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
        handleCellChange (val, index, key) {
            this.$Message.success('修改了第 ' + (index + 1) + ' 行列名为 ' + key + ' 的数据');
        },
        handleChange (val, index) {
            this.$Message.success('修改了第' + (index + 1) + '行数据');
        },
        handleInput (val) {
        },
        handleError (message) {
            this.getData(this.page);
            this.$Message.error(message);
        }
    },
    created () {
        this.initColumns();
        this.getData(1);
    }
};
</script>
