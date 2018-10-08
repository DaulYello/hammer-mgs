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
                        用户管理
                    </p>
                    <div >
                        <can-edit-table :loading="loading" @on-change="handleChange" @on-error="handleError"  refs="table2" v-model="pageData" @on-router="handleInfo" :columns-list="columns"></can-edit-table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.current" size="small" show-elevator show-total></Page>
                </Card>
            </Col>

        </Row>
    </div>
</template>

<script>
import canEditTable from './components/canEditTable_user.vue';
import columns from './components/columns_user.js';
import formatDate from 'utils/time';
import {
  getUserPage,
  delUser
} from 'api/roleuser/user';
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
          this.page = page;
          this.loading = true;
          getUserPage(page,this.size).then(data => {
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
        delUser (val, index) {

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
        handleInfo (query) {
          this.$router.push({
              name: 'user_info',
              query: query
          });
        }
    },
    created () {
        this.initColumns();
        this.getData(1);
    }
};
</script>
