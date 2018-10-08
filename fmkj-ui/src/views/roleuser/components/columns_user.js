export const columns = [
    {
        title: '用户ID',
        key: 'userId',
        align: 'center'
    }, {
        title: '头像',
        key: 'avatar',
        align: 'center',
        //<Avatar :src="user.avatar" style="background: #619fe7;margin-left: 10px;"></Avatar>
        render: (h, params) => {
                           return h('div', [
                               h('Avatar', {
                                   props: {
                                       src: params.row.avatar,
                                       style: "background: #619fe7;"
                                   }
                               })
                           ]);
                       }
    }, {
        title: '账号',
        key: 'username',
        align: 'center'
    }, {
        title: '真实姓名',
        key: 'realname',
        align: 'center',
        editable: true
    }, {
        title: '性别',
        key: 'sex',
        align: 'center',
        editable: true
    }, {
        title: 'Email',
        key: 'email',
        align: 'center',
        editable: true
    }, {
        title: 'TEL',
        key: 'phone',
        align: 'center',
        editable: true
    }, {
        title: '锁定',
        key: 'locked',
        align: 'center',
        editable: true
    }, {
        title: '创建时间',
        key: 'ctime',
        align: 'center'
    },
    {
        title: '操作',
        align: 'center',
        width: 260,
        key: 'handle',
        handle: ['info','edit','delete']
    }
];

export default columns;
