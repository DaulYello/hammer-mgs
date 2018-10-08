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
                        用户信息
                    </p>
                    <Row >
                        <Input v-model="query._name" placeholder="请输入用户电话..." style="width: 200px" />
                        <Input v-model="query._address" placeholder="请输入用户昵称..." style="width: 200px" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜用户</Button></span>
                    </Row>
                    <div class="margin-top-10">
                        <can-edit-table :loading="loading" @on-delete="handleDel" @on-router="handleInfo" @on-change="handleChange" @on-error="handleError"  refs="table2" v-model="pageData" :columns-list="columns"></can-edit-table>
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
  getBuildPage,
  delBuild
} from 'api/build/build';
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
            query:{}
        };
    },
    methods: {
        initColumns () {
            this.columns = columns;
        },
        getData (page) {
          this.page = page;
          this.loading = true;
          getBuildPage(page,this.size,this.query).then(data => {
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
              name: 'build_info',
              query: query
          });
        },
        handleSearch(){
            this.getData(1);
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
