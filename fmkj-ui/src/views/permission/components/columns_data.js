export const columns = [
    {
        title: '权限ID',
        width: 80,
        key: 'permissionId',
        align: 'center'
    },
    {
        title: '所属系统ID',
        width: 80,
        key: 'systemId',
        align: 'center'
    },
    {
        title: '权限名称',
        align: 'center',
        key: 'name',
        editable: true
    },
    {
        title: '权限码',
        align: 'center',
        key: 'permissionValue',
        editable: true
    },
    {
        title: '权限类型',
        align: 'center',
        key: 'type',
        editable: true
    },
    {
        title: '父级ID',
        align: 'center',
        key: 'pid',
        editable: true
    },
    {
        title: '创建时间',
        align: 'center',
        key: 'ctime'
    },
    {
        title: '方法类型',
        align: 'center',
        key: 'method',
        editable: true
    },
    {
        title: '图标',
        align: 'center',
        key: 'icon',
        editable: true
    },
    {
        title: '权限状态',
        align: 'center',
        key: 'status',
        editable: true
    },
    {
        title: '操作',
        align: 'center',
        width: 260,
        key: 'handle',
        handle: ['edit','delete']
    }
];

export default columns;
