<style lang="less">

@import '../../styles/common.less';
@import '../../styles/table/table.less';

</style>

<template>

<div>
    <Row class="margin-top-10">
        <Col>
            <Card>
                <p slot="title" style="height:32px;line-height:32px;">
                    <Icon type="android-remove"></Icon>
                    角色管理
                    <Button type="info" style="float:right" icon="android-add" @click="handleAdd" >新增角色</Button>
                </p>
                <Row>
                    <Col span="18">
                        <div>
                            <can-edit-table :loading="loading" @on-change="handleChange" @on-error="handleError" @on-select="handleSelect" @on-delete="handleDelete" refs="table2" v-model="pageData" :columns-list="columns"></can-edit-table>
                        </div>
                        <Page style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.current" size="small" show-elevator show-total></Page>
                    </Col>
                    <Col span='6' class="padding-left-10">
                        <!-- :load-data="loadPermission" -->
                        <Tree ref="tree" :data="systemData" show-checkbox></Tree>
                        <Button type="info" :disabled="roleId === '' || roleId === undefined" :loading="loadingPermission" @click="handleSave">保存权限</Button>
                    </Col>
                </Row>

            </Card>
        </Col>

    </Row>
    <Modal v-model="modelShow" title="角色添加" ok-text="添加" :loading="loading" @on-ok="ok" @on-cancel="cancel">
        <Form ref="newRole" :model="newRole" :rules="newRoleRules" :label-width="120">
            <FormItem label="角色名称：" prop="name">
                <Input v-model="newRole.name" type="text"></Input>
            </FormItem>
            <FormItem label="角色标题：">
                <Input v-model="newRole.title" type="text"></Input>
            </FormItem>
            <FormItem label="角色描述：">
                <Input v-model="newRole.description" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入角色描述..."></Input>
            </FormItem>
        </Form>
    </Modal>
</div>

</template>

<script>

import canEditTable from './components/canEditTable_role.vue';
import columns from './components/columns_role.js';
import formatDate from 'utils/time';
import {
    addRole,
    getRolePage,
    delRole,
    getPermissionByRoleId,
    addPermissionByRoleId
}
from 'api/roleuser/role';
import {
    getPermissionBySystemId
}
from 'api/permission/permission';
import {
    getAllSystem
}
from 'api/system/system';
export default {
    name: 'editable-table',
    components: {
        canEditTable
    },
    data() {
        return {
            loading: false,
            loadingPermission: false,
            page: 1,
            size: 10,
            columns: [],
            pageInfo: '',
            pageData: [],
            systemData: [],
            curPermissionData: [],
            roleId: '',
            modelShow: false,
            newRole: {},
            newRoleRules: {
                name: [{
                    required: true,
                    message: '请输入角色名称',
                    trigger: 'blur'
                }]
            }
        };
    },
    methods: {
        initColumns() {
                this.columns = columns;
                getAllSystem().then(data => {
                    if (data.status === 200) {
                        data.data.forEach((child) => {
                            let item = {
                                title: child.name,
                                id: child.systemId,
                                loading: false,
                                children: []
                            }
                            this.systemData.push(item);
                            this.loadPermission(item, function(children) {
                                item.children = children;
                            })
                        });

                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('服务器异常' + error);
                });
            },
            getData(page) {
                this.page = page;
                this.loading = true;
                this.roleId = '';
                getRolePage(page, this.size).then(data => {
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
            delSystem(val, index) {
                console.error(this.pageData[index].buildId);
                delBuild(this.pageData[index].buildId).then(data => {
                    console.error(data);
                    if (data.status === 200) {
                        this.$Message.success('删除成功');
                        this.getData(this.page);
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('服务器异常' + error);
                });
                //this.$Message.success('删除了第' + (index + 1) + '行数据');
            },
            handleCellChange(val, index, key) {
                this.$Message.success('修改了第 ' + (index + 1) + ' 行列名为 ' + key + ' 的数据');
            },
            handleChange(val, index) {
                this.$Message.success('修改了第' + (index + 1) + '行数据');
            },
            handleError(message) {
                this.getData(this.page);
                this.$Message.error(message);
            },
            handleSelect(currentRow, oldCurrentRow) {
                this.roleId = currentRow.roleId;
                getPermissionByRoleId(currentRow.roleId).then(data => {
                    if (data.status === 200) {
                        this.curPermissionData = data.data;
                        this.setPermission();
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('服务器异常' + error);
                });
            },
            handleDelete(currentRow,callback) {
                console.error(currentRow);
                this.loadingPermission = true;
                delRole(currentRow.roleId).then(data => {
                    this.loadingPermission = false;
                    callback();
                    if (data.status === 200) {
                        this.getData(this.page);
                        this.$Message.success('删除成功');
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('服务器异常' + error);
                });

            },
            handleSave() {
                let permissionIdList = [];
                this.$refs.tree.getCheckedNodes().forEach(item => {
                    if (item.permissionId != undefined) {
                        permissionIdList.push(item.permissionId);
                    }
                });
                let ppp = this.$refs.tree.flatState.filter(obj => obj.node.indeterminate).map(obj => obj.node);
                ppp.forEach(item => {
                    if (item.permissionId != undefined) {
                        permissionIdList.push(item.permissionId);
                    }
                });
                //console.error(this);
                addPermissionByRoleId(this.roleId , permissionIdList).then(data => {
                  if (data.status === 200) {
                      this.$Message.success(data.message);
                  } else {
                      this.$Message.error(data.message);
                  }
                }).catch(error => {
                    this.$Message.error('服务器异常' + error);
                });
            },
            loadPermission(item, callback) {
                getPermissionBySystemId(item.id).then(data => {
                    if (data.status === 200) {
                        let permissionData1 = [];
                        let permissionData2 = [];
                        let permissionData3 = [];
                        data.data.forEach(item => {
                            let child = {
                                title: item.name + '(' + (item.type === 3 ? '权限' : '菜单') + ')',
                                permissionId: item.permissionId,
                                pid: item.pid,
                                disabled: item.status == 1 ? false : true,
                                children: []
                            }
                            switch (item.type) {
                                case 1:
                                    permissionData1.push(child);
                                    break;
                                case 2:
                                    permissionData2.push(child);
                                    break;
                                case 3:
                                    permissionData3.push(child);
                                    break;
                            }
                        });
                        permissionData3.forEach(item3 => {
                            permissionData2.forEach(item2 => {
                                if (item3.pid == item2.permissionId) {
                                    item2.children.push(item3);
                                }
                            });
                            permissionData1.forEach(item1 => {
                                if (item3.pid == item1.permissionId) {
                                    item1.children.push(item3);
                                }
                            });
                        });
                        permissionData2.forEach(item2 => {
                            permissionData1.forEach(item1 => {
                                if (item2.pid == item1.permissionId) {
                                    item1.children.push(item2);
                                }
                            });
                        });
                        callback(permissionData1);
                        this.setPermission();
                    } else {
                        this.$Message.error(data.message);
                    }
                }).catch(error => {
                    this.$Message.error('服务器异常' + error);
                });
            },
            setPermission() {
                this.$refs.tree.data.forEach(sitem => {
                    this.$refs.tree.handleCheck({
                        checked: false,
                        nodeKey: sitem.nodeKey
                    });
                });

                let ppp = this.$refs.tree.flatState.filter(obj => obj.node.children.length === 0).map(obj => obj.node);
                this.curPermissionData.forEach(citem => {
                    ppp.forEach(sitem => {
                        if (sitem.permissionId === citem.permissionId) {
                            this.$refs.tree.handleCheck({
                                checked: true,
                                nodeKey: sitem.nodeKey
                            });
                        }
                    });
                });
            },
            handleAdd() {
                this.newRole = {};
                this.modelShow = true;
            },
            ok() {
                this.$refs['newRole'].validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        addRole(this.newRole).then(data => {
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
                //this.$Message.error(JSON.stringify(this.newPermission));

                //this.$Message.info('Clicked ok');
            },
            cancel() {
                //this.$Message.info('Clicked cancel');
            }
    },
    created() {
        this.initColumns();
        this.getData(1);
    }
};

</script>
