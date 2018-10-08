import {
  formatDateByLong
} from 'utils/time';

export const columns = [
  {
      title: '图片名称',
      key: 'name',
      align: 'center',
      editable: true
  },  {
        title: '显示顺序',
        key: 'orderValue',
        align: 'center',
        editable: true
    },  {
          title: '跳转链接',
          key: 'link',
          align: 'center',
          editable: true
      },{
                  title: '图片',
                  key: 'imagePath',
                  align: 'center',
                  render: (h, params) => {
                                     return h('div', [
                                         h('img', {
                                            // 正常的 HTML 特性
                                            attrs: {
                                              src: params.row.imagePath
                                            }
                                         }),
                                     ]);
                                 }
              },{
        title: '显示的开始时间',
        key: 'showStartTime',
        align: 'center',
        render: (h, params) => {
                           return h('div',formatDateByLong(params.row.showStartTime,"yyyy-MM-dd h:m:s"));
                       }
    },{
      title: '显示的结束时间',
      key: 'showEndTime',
      align: 'center',
      render: (h, params) => {
                         return h('div',formatDateByLong(params.row.showEndTime,"yyyy-MM-dd h:m:s"));
                     }
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
