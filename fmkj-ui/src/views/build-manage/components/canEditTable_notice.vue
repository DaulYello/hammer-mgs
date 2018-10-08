<style lang="less">

@import '../../../styles/table/editable-table.less';

</style>

<template>

<div>
    <Table :ref="refs" :columns="columnsList" :data="thisTableData" :loading="tableLoading" border disabled-hover></Table>
</div>

</template>

<script>

import {
    putNotice,
    delNotice
}
from 'api/build/notice';
//编辑按钮
const editButton = (vm, h, currentRow, index) => {
    return h('Button', {
        props: {
            type: currentRow.editting ? 'success' : 'primary',
            loading: currentRow.saving
        },
        style: {
            margin: '0 5px'
        },
        on: {
            'click': () => {
                if (!currentRow.editting) {
                    if (currentRow.edittingCell) {
                        for (let name in currentRow.edittingCell) {
                            currentRow.edittingCell[name] = false;
                            vm.edittingStore[index].edittingCell[name] = false;
                        }
                    }
                    vm.edittingStore[index].editting = true;
                    vm.thisTableData = JSON.parse(JSON.stringify(vm.edittingStore));
                } else {
                    vm.edittingStore[index].saving = true;
                    vm.thisTableData = JSON.parse(JSON.stringify(vm.edittingStore));
                    putNotice(vm.thisTableData[index]).then(data => {
                        if (data.status === 200) {
                            let edittingRow = vm.edittingStore[index];
                            edittingRow.editting = false;
                            edittingRow.saving = false;
                            vm.thisTableData = JSON.parse(JSON.stringify(vm.edittingStore));
                            vm.$emit('input', vm.handleBackdata(vm.thisTableData));
                            vm.$emit('on-change', vm.handleBackdata(vm.thisTableData), index);
                        } else {
                            let edittingRow = vm.thisTableData[index];
                            edittingRow.editting = false;
                            edittingRow.saving = false;
                            vm.thisTableData = JSON.parse(JSON.stringify(vm.thisTableData));
                            vm.$emit('on-error', data.message);
                        }
                    }).catch(error => {
                        vm.$emit('on-error', '服务器异常' + error);
                    });
                }
            }
        }
    }, currentRow.editting ? '保存' : '编辑');
};
//删除按钮
const deleteButton = (vm, h, currentRow, index) => {
    return h('Poptip', {
        props: {
            confirm: true,
            title: '您确定要删除这条数据吗?',
            transfer: true
        },
        on: {
            'on-ok': () => {
                //vm.$emit('on-error', '暂不支持删除');
                // vm.thisTableData.splice(index, 1);
                // vm.$emit('input', vm.handleBackdata(vm.thisTableData));
                // vm.$emit('on-delete', vm.handleBackdata(vm.thisTableData), index);
                vm.loading = true;
                vm.$emit('on-delete', vm.thisTableData[index],function functionName() {
                   vm.loading = false;
                });
            }
        }
    }, [
        h('Button', {
            style: {
                margin: '0 5px'
            },
            props: {
                type: 'error',
                placement: 'top',
                loading:vm.loading
            }
        }, '删除')
    ]);
};
//详情按钮
const infoButton = (vm, h, currentRow, index) => {
    return h('Button', {
        props: {
            type: 'text',
            size: 'small'
        },
        on: {
            click: () => {

                let query = vm.thisTableData[index];
                delete query.salt;
                delete query.editting;
                delete query.edittingCell;
                delete query.ctime;
                delete query.password;
                delete query.locked;
                vm.$emit('on-router', query);
            }
        }
    }, [
        h('Button', {
            props: {
                type: 'info',
                placement: 'top'
            }
        }, '详情')
    ]);
};
const incellEditBtn = (vm, h, param) => {
    if (vm.hoverShow) {
        return h('div', {
            'class': {
                'show-edit-btn': vm.hoverShow
            }
        }, [
            h('Button', {
                props: {
                    type: 'text',
                    icon: 'edit'
                },
                on: {
                    click: (event) => {
                        vm.edittingStore[param.index].edittingCell[param.column.key] = true;
                        vm.thisTableData = JSON.parse(JSON.stringify(vm.edittingStore));
                    }
                }
            })
        ]);
    } else {
        return h('Button', {
            props: {
                type: 'text',
                icon: 'edit'
            },
            on: {
                click: (event) => {
                    vm.edittingStore[param.index].edittingCell[param.column.key] = true;
                    vm.thisTableData = JSON.parse(JSON.stringify(vm.edittingStore));
                }
            }
        });
    }
};
const saveIncellEditBtn = (vm, h, param) => {
    return h('Button', {
        props: {
            type: 'text',
            icon: 'checkmark'
        },
        on: {
            click: (event) => {
                vm.edittingStore[param.index].edittingCell[param.column.key] = false;
                vm.thisTableData = JSON.parse(JSON.stringify(vm.edittingStore));
                vm.$emit('input', vm.handleBackdata(vm.thisTableData));
                vm.$emit('on-cell-change', vm.handleBackdata(vm.thisTableData), param.index, param.column.key);
            }
        }
    });
};
const cellInput = (vm, h, param, item) => {
    return h('Input', {
        props: {
            type: 'text',
            value: vm.edittingStore[param.index][item.key]
        },
        on: {
            'on-change' (event) {
                let key = item.key;
                vm.edittingStore[param.index][key] = event.target.value;
            }
        }
    });
};
export default {
    name: 'canEditTable',
    props: {
        tableLoading: Boolean,
        refs: String,
        columnsList: Array,
        value: Array,
        url: String,
        editIncell: {
            type: Boolean,
            default: false
        },
        hoverShow: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            columns: [],
            thisTableData: [],
            edittingStore: [],
            loading:false
        };
    },
    created() {
        this.init();
    },
    methods: {
        init() {
                let vm = this;
                let editableCell = this.columnsList.filter(item => {
                    if (item.editable) {
                        if (item.editable === true) {
                            return item;
                        }
                    }
                });
                let cloneData = JSON.parse(JSON.stringify(this.value));
                let res = [];
                res = cloneData.map((item, index) => {
                    let isEditting = false;
                    if (this.thisTableData[index]) {
                        if (this.thisTableData[index].editting) {
                            isEditting = true;
                        } else {
                            for (const cell in this.thisTableData[index].edittingCell) {
                                if (this.thisTableData[index].edittingCell[cell] === true) {
                                    isEditting = true;
                                }
                            }
                        }
                    }
                    if (isEditting) {
                        return this.thisTableData[index];
                    } else {
                        this.$set(item, 'editting', false);
                        let edittingCell = {};
                        editableCell.forEach(item => {
                            edittingCell[item.key] = false;
                        });
                        this.$set(item, 'edittingCell', edittingCell);
                        return item;
                    }
                });
                this.thisTableData = res;
                this.edittingStore = JSON.parse(JSON.stringify(this.thisTableData));
                this.columnsList.forEach(item => {
                    if (item.editable) {
                        item.render = (h, param) => {
                            let currentRow = this.thisTableData[param.index];
                            if (!currentRow.editting) {
                                if (this.editIncell) {
                                    return h('Row', {
                                        props: {
                                            type: 'flex',
                                            align: 'middle',
                                            justify: 'center'
                                        }
                                    }, [
                                        h('Col', {
                                            props: {
                                                span: '22'
                                            }
                                        }, [!currentRow.edittingCell[param.column.key] ? h('span', currentRow[item.key]) : cellInput(this, h, param, item)]),
                                        h('Col', {
                                            props: {
                                                span: '2'
                                            }
                                        }, [
                                            currentRow.edittingCell[param.column.key] ? saveIncellEditBtn(this, h, param) : incellEditBtn(this, h, param)
                                        ])
                                    ]);
                                } else {
                                    return h('span', currentRow[item.key]);
                                }
                            } else {
                                return h('Input', {
                                    props: {
                                        type: 'textarea',
                                        value: currentRow[item.key]
                                    },
                                    on: {
                                        'on-change' (event) {
                                            let key = param.column.key;
                                            vm.edittingStore[param.index][key] = event.target.value;
                                        }
                                    }
                                });
                            }
                        };
                    }
                    if (item.handle) {
                        item.render = (h, param) => {
                            let currentRowData = this.thisTableData[param.index];
                            let children = [];
                            item.handle.forEach(item => {
                                if (item === 'edit') {
                                    children.push(editButton(this, h, currentRowData, param.index));
                                } else if (item === 'delete') {
                                    children.push(deleteButton(this, h, currentRowData, param.index));
                                } else if (item === 'info') {
                                    children.push(infoButton(this, h, currentRowData, param.index));
                                }

                            });
                            return h('div', children);
                        };
                    }
                });
            },
            handleBackdata(data) {
                let clonedData = JSON.parse(JSON.stringify(data));
                clonedData.forEach(item => {
                    delete item.editting;
                    delete item.edittingCell;
                    delete item.saving;
                });
                return clonedData;
            },
            handleSelect(currentRow, oldCurrentRow) {
              this.$emit('on-select', currentRow, oldCurrentRow);
            }
    },
    watch: {
        value(data) {
            this.init();
        }
    }
};

</script>
