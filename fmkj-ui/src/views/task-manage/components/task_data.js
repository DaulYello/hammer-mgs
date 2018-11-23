import {
  formatDateByLong
} from 'utils/time';
const picturePathLogo = '';
const showDialog = '';
const picturePathImage = '';
export const columns = [
    {
        title: '序号',
        type: 'index',
        width: 60,
        key: 'id',
        align: 'center'
    },
    {
        title: '任务标题',
        align: 'center',
        key: 'title',
        editable: true
    },
    {
        title: '任务目标',
        align: 'center',
        key: 'taskTarget',
        editable: true
    },
    {
        title: '二级描述',
        align: 'center',
        key: 'subDesc',
        editable: true
    },
    {
        title: '任务奖励',
        align: 'center',
        width: 70,
        key: 'reward',
        editable: true
    },
    {
        title: '审核周期',
        align: 'center',
        width: 70,
        key: 'auditCycle',
        editable: true
    },
    {
        title: '是否下载APP',
        align: 'center',
        key: 'type',
        render:(h,params)=>{
            const text = params.row.type == '0' ? '不需要': params.row.type == '1' ? '需要': '错误状态';
            return h('div',text);
        }
    },
    {
        title: '打开地址',
        align: 'center',
        key: 'downUrl',
        editable: true
    },
    {
        title: '详情图片',
        align: 'center',
        width: 80,
        key: 'imageUrl',
        handle: ['detalShow']
    },
    {
        title: 'logo图片',
        align: 'center',
        width: 80,
        key: 'logoUrl',
        handle: ['logoShow']
    },
    {
        title: '开始时间',
        align: 'center',
        width: 100,
        key: 'startDate',
        render:(h,params)=>{
            if (params.row.startDate != null){
                return h('div',formatDateByLong(params.row.startDate,"yyyy-MM-dd hh:mm:ss"));
            }
        }
    },
    {
        title: '结束时间',
        align: 'center',
        width: 100,
        key: 'endDate',
        render:(h,params)=>{
            if (params.row.endDate != null){
                return h('div',formatDateByLong(params.row.endDate,"yyyy-MM-dd hh:mm:ss"));
            }
        }
    },
    {
        title: '创建时间',
        width: 100,
        align: 'center',
        key: 'createDate',
        render:(h,params)=>{
            if (params.row.createDate != null){
                return h('div',formatDateByLong(params.row.createDate,"yyyy-MM-dd hh:mm:ss"));
            }
        }
    },
    {
        title: '更新时间',
        align: 'center',
        width: 100,
        key: 'updateDate',
        render:(h,params)=>{
            if (params.row.updateDate != null){
                return h('div',formatDateByLong(params.row.updateDate,"yyyy-MM-dd hh:m:s"));
            }
        }
    },
    {
        title: '任务状态',
        align: 'center',
        key: 'status',
        render: (h, params) => {
            //const color = params.row.status === '0' ? 'green' : 'red';
            const text = params.row.status == '0' ? '正常' : params.row.status == '-1' ? '删除' : '错误数据';
            /*return h('Tag', {
                props: {
                    color: color
                }
            }, text);*/
            return h('div',text);
        }
    },
    {
        title: '操作',
        align: 'center',
        width: 170,
        key: 'handle',
        handle: ['edit','detail']
    }
];



export default columns;