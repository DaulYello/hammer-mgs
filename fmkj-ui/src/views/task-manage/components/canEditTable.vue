<style lang="less">

@import '../../../styles/table/editable-table.less';

</style>

<template>

<div>
    <Table :ref="refs" :columns="columnsList" :data="thisTableData" border disabled-hover></Table>
</div>

</template>

<script>

import {
    getTaskList,
    addTask
} from 'api/task/task';
//编辑按钮
const editButton = (vm, h, index) => {
    return h('Button', {
        props: {
            /*type: currentRow.editting ? 'success' : 'primary',*/
            type: 'primary',
        },
        style: {
            margin: '0 5px'
        },
        on: {
            'click': () => {
                vm.$emit('on-modelShow', vm.thisTableData[index]);
            }
        }
    }, '编辑');
};

//详情
const showDetail = (vm, h, index) => {
    return h('Button', {
        props: {
            type:  'success',
            placement: 'top'
        },
        style: {
            margin: '0 5px'
        },
        on: {
            'click': () => {
                vm.$emit('on-detailShow', vm.thisTableData[index]);
            }
        }
    },'温馨提示');
};

//显示任务头像图片
const imageShow = (vm, h, index,item) => {
    return h('div', [
        h('Button',{
            props:{
                type:'primary',
                size:'small'
            },
            on:{
                click: () => {
                    vm.$emit('on-show', vm.thisTableData[index],item);
                }
            }
        },"预览")
    ])
};
export default {
    name: 'canEditTable',
    props: {
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
            edittingStore: []
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
                                        type: 'text',
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
                            //let currentRowData = this.thisTableData[param.index];
                            let children = [];
                            item.handle.forEach(item => {
                                if (item === 'edit') {
                                    children.push(editButton(this, h, param.index));
                                } else if (item === 'detail'){
                                    children.push(showDetail(this, h, param.index));
                                } else if (item === 'logoShow') {
                                    children.push(imageShow(this, h, param.index,item));
                                }else if (item === 'detalShow') {
                                    children.push(imageShow(this, h, param.index,item));
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
            }
    },
    watch: {
        value(data) {
            this.init();
        }
    }
};

</script>
