import {
  formatDateByLong
} from 'utils/time';
export const columns = [
    {
        title: '序号',
        type: 'index',
        width: 80,
        key: 'buildId',
        align: 'center'
    },
    {
        title: '电话号码',
        align: 'center',
        key: 'name',
        editable: true
    },
    {
        title: '昵称',
        align: 'center',
        key: 'address',
        editable: true
    },
    {
        title: '邮箱',
        align: 'center',
        key: 'contact',
        editable: true
    },
    {
        title: '名字',
        align: 'center',
        key: 'contactWay',
        editable: true
    },
    {
        title: '证件号',
        align: 'center',
        key: 'contactWay',
        editable: true
    },
    {
        title: '资产',
        align: 'center',
        key: 'park',
        editable: true
    },
    {
        title: '积分',
        align: 'center',
        key: 'park',
        editable: true
    },
    {
        title: '等级',
        align: 'center',
        key: 'park',
        editable: true
    },
    {
        title: '身份验证',
        align: 'center',
        key: 'park',
        editable: true
    },
    {
        title: '原住居民',
        align: 'center',
        key: 'park',
        editable: true
    },
    {
        title: '锤宝ID',
        align: 'center',
        key: 'park',
        editable: true
    },
    {
        title: '操作',
        align: 'center',
        width: 260,
        key: 'handle',
        handle: ['info','edit']
    }
];

export default columns;
