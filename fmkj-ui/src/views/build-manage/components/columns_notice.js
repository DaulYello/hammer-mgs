import {
  formatDateByLong
} from 'utils/time';
export const columns = [
  {
      title: '署名',
      key: 'name',
      align: 'center',
      editable: true
  },  {
        title: '公告内容',
        key: 'content',
        align: 'center',
        editable: true
    },  {
          title: '额外备注',
          key: 'source',
          align: 'center',
          editable: true
      }, {
        title: '创建时间',
        key: 'ctime',
        align: 'center',
        render: (h, params) => {
                           return h('div',formatDateByLong(params.row.ctime,"yyyy-MM-dd h:m:s"));
                       }
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
