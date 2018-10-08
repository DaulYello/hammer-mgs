export const columns = [
    {
        title: '角色ID',
        key: 'roleId',
        align: 'center'
    }, {
        title: '角色名称',
        key: 'name',
        align: 'center',
        editable: true
    }, {
        title: '角色标题',
        key: 'title',
        align: 'center',
        editable: true
    }, {
        title: '角色描述',
        key: 'description',
        align: 'center',
        editable: true
    }, {
        title: '创建时间',
        key: 'ctiome',
        align: 'center'
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
