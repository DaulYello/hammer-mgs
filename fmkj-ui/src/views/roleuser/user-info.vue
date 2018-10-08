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
                    用户详情
                </p>
                <Row>
                    <Col span="6">
                        <Card>
                            <p slot="title" style="height:32px;line-height:32px;">
                                用户信息
                            </p>
                            <Form ref="user" :model="user" :label-width="120">
                                <FormItem label="用户ID：">
                                    <Input :disabled="true" v-model="user.userId" type="text"></Input>
                                </FormItem>
                                <FormItem label="账号：">
                                    <Input :disabled="true" v-model="user.username" type="text"></Input>
                                </FormItem>
                                <FormItem label="真实姓名：">
                                    <Input :disabled="true" v-model="user.realname" type="text"></Input>
                                </FormItem>
                            </Form>
                        </Card>
                    </Col>
                    <Col span="7" class="padding-left-10">
                        <Card>
                            <p slot="title" style="height:32px;line-height:32px;">
                                用户拥有角色
                            </p>
                            <Select v-model="roleList" multiple style="width:260px" placeholder="请选择角色" @on-change="selectRole">
                                <Option v-for="child in roleData" :value="child.roleId" :key="child.roleId">{{ child.name + "(" + child.title + ")" }}</Option>
                            </Select>
                            <Button class="margin-top-10" type="info" :disabled="userId === '' || userId === undefined" :loading="loadingPermission" @click="handleSaveRole">保存角色</Button>
                        </Card>
                    </Col>
                    <Col span='6' class="padding-left-10">
                        <Card>
                            <p slot="title" style="height:32px;line-height:32px;">
                                角色提供权限
                            </p>
                            <Tree ref="permissionTree" :data="systemData" show-checkbox></Tree>
                        </Card>
                    </Col>
                    <!-- <Col span='6' class="padding-left-10">
                        <Card>
                            <p slot="title" style="height:32px;line-height:32px;">
                                用户自身权限
                            </p>
                            <Tree ref="permissionTree" :data="systemUserData" show-checkbox></Tree>
                            <Button type="info" :disabled="userId === '' || userId === undefined" :loading="loadingPermission" @click="handleSave">保存权限</Button>
                        </Card>
                    </Col> -->
                </Row>

            </Card>
        </Col>

    </Row>
</div>

</template>

<script>

import formatDate from 'utils/time';
import {
    getUserById,
    getPermissionByUserId,
    getPermissionByUserIdWithOutRole,
    addRoleByUserId
}
from 'api/roleuser/user';
import {
    addRole,
    getRolePage,
    delRole,
    getPermissionByRoleId,
    getRoleAll,
    getRoleByUserId
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
    data() {
            return {
                social: ['facebook', 'github'],
                loading: false,
                userId: '',
                user: {},
                systemData: [],
                systemUserData: [],
                roleData: [],
                roleId: '',
                modelShow: false,
                loadingPermission: false,
                roleList: [],
                curRoleData: [],
                userPermissionData:[],
                curPermissionData: [],
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
            init() {
                    this.userId = this.$route.query.user_id.toString();
                    getUserById(this.userId).then(data => {
                        if (data.status === 200) {
                            this.user = data.data;
                        } else {
                            this.$Message.error(data.message);
                        }
                    }).catch(error => {
                        this.$Message.error('服务器异常' + error);
                    });
                    getRoleByUserId(this.userId).then(data => {
                        if (data.status === 200) {
                            //
                            this.curRoleData = data.data;
                            getRoleAll().then(data => {
                                if (data.status === 200) {
                                    this.roleData = data.data;
                                    this.roleData.forEach((sitem, index) => {
                                        getPermissionByRoleId(sitem.roleId).then(data => {
                                            if (data.status === 200) {
                                                sitem.permissionList = data.data;
                                            } else {
                                                this.$Message.error(data.message);
                                            }
                                        }).catch(error => {
                                            this.$Message.error('服务器异常' + error);
                                        });
                                    });
                                } else {
                                    this.$Message.error(data.message);
                                }
                            }).catch(error => {
                                this.$Message.error('服务器异常' + error);
                            });
                            this.roleList = this.curRoleData.map(obj => obj.roleId);

                        } else {
                            this.$Message.error(data.message);
                        }
                    }).catch(error => {
                        this.$Message.error('服务器异常' + error);
                    });

                    getAllSystem().then(data => {
                        if (data.status === 200) {
                            data.data.forEach((child) => {
                                let item = {
                                    title: child.name,
                                    id: child.systemId,
                                    loading: false,
                                    disabled: true,
                                    expand:true,
                                    children: []
                                }
                                this.systemData.push(item);
                                this.loadPermission(item, function(children) {
                                    item.children = children;
                                });
                            });
                            // getPermissionByUserIdWithOutRole(this.userId).then(data => {
                            //     if (data.status === 200) {
                            //         this.loadingPermission = false;
                            //         this.userPermissionData = data.data;
                            //         this.curPermissionData.concat(data.data);
                            //         this.setPermission();
                            //     } else {
                            //         this.$Message.error(data.message);
                            //     }
                            // }).catch(error => {
                            //     this.$Message.error('服务器异常' + error);
                            // });

                        } else {
                            this.$Message.error(data.message);
                        }
                    }).catch(error => {
                        this.$Message.error('服务器异常' + error);
                    });
                },
                selectRole(){
                  this.setPermission();
                },
                handleError(message) {
                    this.getData(this.page);
                    this.$Message.error(message);
                },
                handleSaveRole() {
                    this.loadingPermission = true;
                    addRoleByUserId(this.userId , this.roleList).then(data => {
                      if (data.status === 200) {
                          this.$Message.success(data.message);
                      } else {
                          this.$Message.error(data.message);
                      }
                      this.loadingPermission = false;
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
                                    disabled: true,
                                    expand:true,
                                    //disabled: item.status == 1 ? false : true,
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
                        } else {
                            this.$Message.error(data.message);
                        }
                    }).catch(error => {
                        this.$Message.error('服务器异常' + error);
                    });
                },
                setPermission() {
                  this.curPermissionData.splice(0,  this.curPermissionData.length);
                  this.roleList.forEach(item => {
                      this.roleData.forEach(role => {
                        if (item === role.roleId) {
                            role.permissionList.forEach(ppp => {
                              this.curPermissionData.push(ppp);
                            });
                        }
                      });
                  });
                    this.$refs.permissionTree.data.forEach(sitem => {
                        this.$refs.permissionTree.handleCheck({
                            checked: false,
                            nodeKey: sitem.nodeKey
                        });
                    });

                    let ppp = this.$refs.permissionTree.flatState.filter(obj => obj.node.children.length === 0).map(obj => obj.node);
                    this.curPermissionData.forEach(citem => {
                        ppp.forEach(sitem => {
                            if (sitem.permissionId === citem.permissionId) {
                                this.$refs.permissionTree.handleCheck({
                                    checked: true,
                                    nodeKey: sitem.nodeKey
                                });
                            }
                        });
                    });
                },
                task(){
                  let vm = this;
                  setTimeout(function () {
                    if (vm.roleData.length === 0) {
                      vm.task();
                    }else {
                      vm.setPermission();
                    }
                  }, 500);
                }
        },
        created() {
            this.init();
            this.task();
        }
};

</script>
