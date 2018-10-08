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
                        停车场信息
                    </p>
                    <div >
                        <can-edit-table :loading="loading" @on-delete="handleDel" @on-router="handleInfo" @on-change="handleChange" @on-error="handleError"  refs="table2" v-model="pageData" :columns-list="columns"></can-edit-table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.current" size="small" show-elevator show-total></Page>
                </Card>
            </Col>

        </Row>
        <Modal v-model="modelShow" title="停车场详情" ok-text="编辑" :loading="loading" @on-ok="ok" @on-cancel="cancel">
            <Form ref="newCarpark" :model="newCarpark" :rules="newCarparkRules" :label-width="120">
                <FormItem label="角色名称：" prop="roleName">
                    <Input v-model="newCarpark.roleName" type="text"></Input>
                </FormItem>
                <FormItem label="角色描述：">
                    <Input v-model="newCarpark.description" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入角色描述..."></Input>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>

<script>
import canEditTable from './components/canEditTable.vue';
import columns from './components/columns_data.js';
import formatDate from 'utils/time';
import {
  getCarparkPage,
  delCarpark
} from 'api/carpark/carpark';
export default {
    name: 'editable-table',
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
            modelShow: false,
            newCarpark: {},
            newCarparkRules: {
                roleName: [{
                    required: true,
                    message: '请输入角色名称',
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
          getCarparkPage(page,this.size).then(data => {
            this.loading = false;
            if (data.status === 200) {
              this.pageInfo = data.data;
              this.pageData = data.data.records;
            } else {
              this.$Message.error(data.message);
            }
          }).catch(error => {
              this.$Message.error('服务器异常' + error);
          });
        },
        //
        handleInfo (query) {
          this.$router.push({
              name: 'carpark_info',
              query: query
          });
        },
        handleDel (val, 门票管理) {
            delCarpark(this.pageData[门票管理].carparkId).then(data => {
              if (data.status === 200) {
                this.$Message.success('删除成功');
                this.getData(this.page);
              } else {
                this.$Message.error(data.message);
              }
            }).catch(error => {
              reject(error);
            });
            //this.$Message.success('删除了第' + (门票管理 + 1) + '行数据');
        },
        handleCellChange (val, 门票管理, key) {
            this.$Message.success('修改了第 ' + (门票管理 + 1) + ' 行列名为 ' + key + ' 的数据');
        },
        handleChange (val, index) {
            this.$Message.success('修改了第' + (index + 1) + '行数据');
        },
        handleError (message) {
            this.getData(this.page);
            this.$Message.error(message);
        },
        ok() {
            // this.$refs['newRole'].validate((valid) => {
            //     if (valid) {
            //         this.loading = true;
            //         addRole(this.newRole).then(data => {
            //             this.loading = false;
            //             if (data.status === 200) {
            //                 this.$Message.success(data.message);
            //                 this.getData(this.page);
            //             } else {
            //                 this.$Message.error(data.message);
            //             }
            //         }).catch(error => {
            //             this.loading = false;
            //             this.$Message.error('服务器异常' + error);
            //         });
            //     } else {
            //         setTimeout(() => {
            //             this.modelShow = true;
            //         }, 100);
            //         this.$Message.error('请输入完整信息');
            //     }
            // });
            //this.$Message.error(JSON.stringify(this.newPermission));

            //this.$Message.info('Clicked ok');
        },
        cancel() {
            //this.$Message.info('Clicked cancel');
        }
    },
    created () {
        this.initColumns();
        this.getData(1);
    }
};
</script>
