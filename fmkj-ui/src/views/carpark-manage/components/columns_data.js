import {
  formatDateByLong
} from 'utils/time';
export const columns = [
    {
        title: '序号',
        type: 'index',
        width: 80,
        key: 'spaceId',
        align: 'center'
    },
    {
        title: '停车场名称',
        align: 'center',
        key: 'name',
        editable: true
    },
    {
        title: '地址',
        align: 'center',
        key: 'address',
        editable: true
    },
    {
        title: '联系方式',
        align: 'center',
        key: 'contact',
        render: (h, params) => {
                           return h('div',params.row.contact+"-"+params.row.contactWay);
                       }
    },
    {
        title: '创建时间',
        align: 'center',
        key: 'ctime',
        render: (h, params) => {
                           return h('div',formatDateByLong(params.row.ctime,"yyyy-MM-dd h:m:s"));
                       }
    },
    {
        title: '操作',
        align: 'center',
        width: 180,
        key: 'handle',
        handle: ['info','delete']
    }
];

export default columns;
