<style lang="less">

@import '../../styles/common.less';

</style>

<template>

<div>
    <Row>
        <Card>
            <p slot="title">
                <Icon type="compose"></Icon>
                系统权限
            </p>
            <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}">
                <Layout>
                    <Sider hide-trigger :style="{background: '#fff'}">
                        <Table :loading="systemLoading" highlight-row ref="currentRow" :columns="columns" @on-current-change="handleSelectSystem" :data="systemData"></Table>
                    </Sider>
                    <Sider hide-trigger :style="{background: '#fff'}">
                        <Table :loading="permissionLoading" highlight-row maxheight="300" ref="currentRow1" @on-current-change="handleSelect" :columns="columns1" :data="permissionData1"></Table>
                        <Button :disabled="permission.type !== 0" type="info" long icon="android-add" @click="handleAdd(1)">新建一级菜单</Button>
                    </Sider>
                    <Sider hide-trigger :style="{background: '#fff'}">
                        <Table :loading="permissionLoading" highlight-row maxheight="300" ref="currentRow2" @on-current-change="handleSelect" :columns="columns2" :data="permissionData2"></Table>
                        <Button :disabled="permission.type !== 1" type="primary" long icon="android-add" @click="handleAdd(2)">新建二级菜单</Button>
                    </Sider>
                    <Sider hide-trigger :style="{background: '#fff'}">
                        <Table :loading="permissionLoading" highlight-row maxheight="300" ref="currentRow3" @on-current-change="handleSelect" :columns="columns3" :data="permissionData3"></Table>
                        <Button :disabled="permission.type !== 2" type="success" long icon="android-add" @click="handleAdd(3)">新建操作权限</Button>
                    </Sider>
                    <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
                        <Form ref="permission" :model="permission" :label-width="120">
                            <FormItem label="权限ID：">
                                <Input :disabled="true" v-model="permission.permissionId" type="text"></Input>
                            </FormItem>
                            <FormItem label="权限名称：">
                                <Input v-model="permission.name" type="text"></Input>
                            </FormItem>
                            <FormItem label="权限码：">
                                <Input :disabled="true" v-model="permission.permissionValue" type="text"></Input>
                            </FormItem>
                            <FormItem label="权限类型：">
                                <Input :disabled="true" v-model="permission.type" type="text"></Input>
                            </FormItem>
                            <FormItem label="权限图标：">
                                <Input v-model="permission.icon" type="text"></Input>
                            </FormItem>
                            <FormItem label="资源路径：">
                                <Input :disabled="true" v-model="permission.uri" type="text"></Input>
                            </FormItem>
                            <FormItem label="方法类型：">
                                <Input :disabled="true" v-model="permission.method" type="text"></Input>
                            </FormItem>
                            <FormItem label="权限状态：">
                                <i-switch :disabled="!permission.permissionId" v-model="permissionStatus"></i-switch>
                            </FormItem>
                            <FormItem>
                                <Button :disabled="!permission.permissionId" type="primary" :loading="loading" @click="handleSubmit()">修改</Button>
                                <Button :disabled="!permission.permissionId" type="error" :loading="loading" @click="handleDel()">删除</Button>
                            </FormItem>
                        </Form>
                    </Content>
                </Layout>
            </Content>
        </Card>
    </Row>
    <Modal v-model="model.show" title="系统权限添加" ok-text="添加" :loading="loading" @on-ok="ok" @on-cancel="cancel">
        <Form ref="system" :model="system" :label-width="120">
            <FormItem label="系统ID：">
                <Input :disabled="true" v-model="system.systemId" type="text"></Input>
            </FormItem>
            <FormItem label="系统名称：">
                <Input :disabled="true" v-model="system.name" type="text"></Input>
            </FormItem>
        </Form>
        <Form ref="newPermission" :model="newPermission" :rules="newPermissionRules" :label-width="120">
            <FormItem label="权限(菜单)名称：">
                <Input v-model="newPermission.name" type="text"></Input>
            </FormItem>
            <FormItem label="权限码：">
                <Input v-model="newPermission.permissionValue" type="text"></Input>
            </FormItem>
            <FormItem label="权限类型：">
                <Input :disabled="true" v-model="newPermission.type" type="text"></Input>
            </FormItem>
            <FormItem label="权限图标：">
                <Input v-model="newPermission.icon" type="text"></Input>
            </FormItem>
            <FormItem label="资源路径：">
                <Input v-model="newPermission.uri" type="text"></Input>
            </FormItem>
            <FormItem label="方法类型：" prop="method">
                <RadioGroup v-model="newPermission.method">
                    <Radio label="GET">
                        <span>GET</span>
                    </Radio>
                    <Radio label="POST">
                        <span>POST</span>
                    </Radio>
                    <Radio label="DELETE">
                        <span>DELETE</span>
                    </Radio>
                    <Radio label="PUT">
                        <span>PUT</span>
                    </Radio>
                </RadioGroup>
            </FormItem>
            <FormItem label="是否生成默认权限：">
                <Checkbox :disabled="newPermission.type !== 1 && newPermission.type !== 2" v-model="newPermission.auto">增删改查</Checkbox>
            </FormItem>
        </Form>
    </Modal>
</div>

</template>

<script>

import {
    getAllSystem
}
from 'api/system/system';
import {
    getPermissionBySystemId,
    addPermissionAuto,
    delPermission,
    putPermission
}
from 'api/permission/permission';
export default {
    data() {
            return {
                columns: [{
                    title: '系统名称',
                    key: 'name',
                    align: 'center'
                }],
                columns1: [{
                    title: '一级菜单',
                    key: 'name',
                    align: 'center'
                }],
                columns2: [{
                    title: '二级菜单',
                    key: 'name',
                    align: 'center'
                }],
                columns3: [{
                    title: '资源权限',
                    key: 'name',
                    align: 'center'
                }],
                systemData: [],
                permissionData: [],
                permissionData1: [],
                permissionData2: [],
                permissionData3: [],
                model: {
                    show: false,
                    title: ''
                },
                loading: false,
                systemLoading: false,
                permissionLoading: false,
                permission: {},
                newPermission: {},
                system: {},
                newPermissionRules: {
                    method: [{
                        required: true,
                        message: '请选择方法类型',
                        trigger: 'blur'
                    }]
                }
            };
        },
        computed: {
            permissionStatus: {
                get: function() {
                    return this.permission.status === 1 ? true : false;
                },
                set: function(value) {
                    if (value) {
                        this.permission.status = 1;
                    } else {
                        this.permission.status = 0;
                    }
                }
            }
        },
        created() {
            this.init();
        },
        methods: {
            init() {
                    let vm = this;
                    this.systemLoading = true;
                    getAllSystem().then(data => {
                        this.systemLoading = false;
                        if (data.status === 200) {
                          //data.data.fro
                            this.systemData = data.data;
                        } else {
                            this.$Message.error(data.message);
                        }
                    }).catch(error => {
                        this.$Message.error('服务器异常' + error);
                    });
                },
                initPermissionData(id) {
                    this.permissionLoading = true;
                    this.permissionData = [];
                    this.permissionData1 = [];
                    this.permissionData2 = [];
                    this.permissionData3 = [];

                    getPermissionBySystemId(id).then(data => {
                        this.permissionLoading = false;
                        if (data.status === 200) {
                            this.permissionData = data.data;
                            this.permissionData.forEach(item => {
                                if (item.type === 1) {
                                    this.permissionData1.push(item);
                                }
                            });
                        } else {
                            this.$Message.error(data.message);
                        }
                    }).catch(error => {
                        this.$Message.error('服务器异常' + error);
                    });
                },
                handleSelect(currentRow, oldCurrentRow) {
                    console.error(currentRow);
                    this.permission = currentRow;
                    if (currentRow.type === 1) {
                        this.permissionData2 = [];
                        this.permissionData3 = [];
                        this.permissionData.forEach(item => {
                            if (item.pid === currentRow.permissionId && item.type === 2) {
                                this.permissionData2.push(item);
                            }
                        });
                    } else if (currentRow.type === 2) {
                        this.permissionData3 = [];
                        this.permissionData.forEach(item => {
                            if (item.pid === currentRow.pid && item.type === 3) {
                                this.permissionData3.push(item);
                            } else if (item.pid === currentRow.permissionId && item.type === 3) {
                                this.permissionData3.push(item);
                            }
                        });
                    }
                    //this.$refs.currentRow3.clearCurrentRow();
                },
                handleSelectSystem(currentRow, oldCurrentRow) {
                    //this.$refs.currentRow1.clearCurrentRow();
                    //this.$refs.currentRow2.clearCurrentRow();
                    //this.$refs.currentRow3.clearCurrentRow();
                    this.system = currentRow;
                    this.permission = {
                        pid: 0,
                        type: 0
                    };
                    this.initPermissionData(currentRow.systemId);
                },
                handleAdd(type) {
                    console.error(type);
                    this.newPermission = {};
                    this.model.show = true;
                    this.newPermission.type = type;
                    this.newPermission.systemId = this.system.systemId;
                    this.newPermission.pid = this.permission.permissionId ? this.permission.permissionId : 0;
                    this.newPermission.permissionValue = this.permission.permissionValue ? this.permission.permissionValue : this.system.basepath;
                },
                handleSubmit(from) {
                    this.loading = true;
                    putPermission(this.permission).then(data => {
                        if (data.status === 200) {
                            this.$Message.success(data.message);
                            this.initPermissionData(this.system.systemId);
                        } else {
                            this.$Message.error(data.message);
                        }
                    }).catch(error => {
                        this.$Message.error('服务器异常' + error);
                    });
                    this.loading = false;
                },
                handleDel() {
                    this.$Modal.confirm({
                        title: '警告',
                        content: '<p>删除之后不可还原</p><p>请问是否继续?</p>',
                        loading: true,
                        onOk: () => {
                            this.loading = true;
                            delPermission(this.permission.permissionId).then(data => {

                                if (data.status === 200) {
                                    this.$Message.success(data.message);
                                    this.initPermissionData(this.system.systemId);
                                } else {
                                    this.$Message.error(data.message);
                                }
                            }).catch(error => {
                                this.$Message.error('服务器异常' + error);
                            });
                            this.loading = false;
                            this.$Modal.remove()
                        },
                        onCancel: () => {
                            this.$Message.info('Clicked cancel');
                        }
                    });
                },
                handleReset(name) {
                    this.$refs[name].resetFields();
                },
                ok() {
                    this.$refs['newPermission'].validate((valid) => {
                        if (valid) {
                            this.loading = true;
                            addPermissionAuto(this.newPermission).then(data => {
                                this.loading = false;
                                if (data.status === 200) {
                                    this.$Message.success(data.message);
                                    this.initPermissionData(this.system.systemId);
                                } else {
                                    this.$Message.error(data.message);
                                }
                            }).catch(error => {
                                this.loading = false;
                                this.$Message.error('服务器异常' + error);
                            });
                        } else {
                            setTimeout(() => {
                                this.model.show = true;
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
        }
};

</script>
