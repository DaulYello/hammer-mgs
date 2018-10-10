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
        title: '投放年度',
        key: 'year',
        align: 'center',
        editable:true
    },
    {
        title: '年度R积分投放池',
        align: 'center',
        key: 'rintegralNum',
        editable: true
    },
    {
        title: '年度R积分回收池',
        align: 'center',
        key: 'recycleNum',
        editable: true
    },
    {
        title: '创建时间',
        align: 'center',
        key: 'createdate',
        editable: true
    },
    {
        title: '修改时间',
        align: 'center',
        key: 'updatedate',
        editable: true
    },
    {
        title: '操作',
        align: 'center',
        width: 350,
        key: 'handle',
        handle: ['info', 'run', 'edit', 'delete']
    }
];

export default columns;
