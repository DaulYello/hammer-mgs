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
        title: '门票类型',
        align: 'center',
        key: 'type',
        editable: true
    },
    {
        title: '门票名称',
        align: 'center',
        key: 'name',
        editable: true
    },
    {
        title: '门票单价',
        align: 'center',
        key: 'price',
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
