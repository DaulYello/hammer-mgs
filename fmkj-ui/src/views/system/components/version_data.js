import {
    formatDateByLong
} from 'utils/time';
export const columns = [
    {
        title: '序号',
        type: 'index',
        width: 80,
        key: 'id',
        align: 'center'
    },
    {
        title: '下载地址',
        key: 'apkUrl',
        align: 'center'
    },
    {
        title: '版本号',
        align: 'center',
        key: 'apkVersion'
    },
    {
        title: '内容说明',
        align: 'center',
        key: 'upgradeMsg'
    },
    {
        title: '创建时间',
        align: 'center',
        width: 150,
        key: 'createTime',
        render: (h, params) => {
            return h('div',formatDateByLong(params.row.createTime,"yyyy-MM-dd h:m:s"));
        }
    },
    {
        title: '修改时间',
        align: 'center',
        width: 150,
        key: 'updateTime',
        render: (h, params) => {
            return h('div', formatDateByLong(params.row.updateTime, "yyyy-MM-dd h:m:s"));
        }
    },
    {
        title: '操作',
        align: 'center',
        width: 150,
        key: 'handle',
        render: (h, params) => {
            return h('Poptip', {
                props: {
                    confirm: true,
                    title: '您确定要删除这条数据吗?'
                },
                on: {
                    'on-ok': () => {
                        vm.$emit('on-delete', vm.thisTableData[index],function functionName() {
                            vm.loading = false;
                        });
                    }
                }
            });
        }
    }
];

export default columns;
