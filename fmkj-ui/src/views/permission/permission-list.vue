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
                        系统权限信息
                    </p>
                    <div >
                        <can-edit-table :loading="loading" @on-change="handleChange" @on-error="handleError"  refs="table2" v-model="pageData" :columns-list="columns"></can-edit-table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.current" size="small" show-elevator show-total></Page>
                </Card>
            </Col>

        </Row>
    </div>
</template>

<script>
import canEditTable from './components/canEditTable.vue';
import columns from './components/columns_data.js';
import formatDate from 'utils/time';
import {
  getPermissionPage,
  delPermission
} from 'api/permission/permission';
export default {
    name: 'editable-table',
    components: {
        canEditTable
    },
    data () {
        return {
            loading:true,
            page: 1,
            size: 10,
            columns: [],
            pageInfo: '',
            pageData: []
        };
    },
    methods: {
        initColumns () {
            this.columns = columns;
        },
        getData (page) {
          console.error("p:",page);
          this.page = page;
          this.loading = true;
          getPermissionPage(page,this.size).then(data => {
            console.error(data);
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
        delPermission (val, index) {
          console.error(this.pageData[index].buildId);
            delPermission(this.pageData[index].buildId).then(data => {
              console.error(data);
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
