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
    /*{
        title: '任务编号',
        type: 'index',
        key: 'jobId',
        align: 'center'
    },*/
    {
        title: '任务标题',
        align: 'center',
        key: 'title',
        editable: true
    },
    {
        title: '任务目标',
        align: 'center',
        key: 'task_target',
        editable: true
    },
    {
        title: '二级描述',
        align: 'center',
        key: 'sub_desc',
        editable: true
    },
    {
        title: '任务奖励',
        align: 'center',
        key: 'reward',
        editable: true
    },
    {
        title: '审核周期',
        align: 'center',
        key: 'audit_cycle',
        editable: true
    },
    {
        title: '类型',
        align: 'center',
        key: 'type',
        editable: true
    },
    {
        title: '打开地址',
        align: 'center',
        key: 'down_url',
        editable: true
    },
    {
        title: '创建时间',
        align: 'center',
        key: 'create_date',
        render:(h,param)=>{
            if (param.row.createDate != null){
                return h('div',formatDateByLong(params.row.createDate,"yyyy-MM-dd h:m:s"));
            }
        }
    },
    {
        title: '更新时间',
        align: 'center',
        key: 'update_date',
        render:(h,param)=>{
            if (param.row.updateDate != null){
                return h('div',formatDateByLong(params.row.updateDate,"yyyy-MM-dd h:m:s"));
            }
        }
    },
    {
        title: '开始时间',
        align: 'center',
        key: 'statrt_date',
        render:(h,param)=>{
            if (param.row.statrtDate != null){
                return h('div',formatDateByLong(params.row.statrtDate,"yyyy-MM-dd h:m:s"));
            }
        }
    },
    {
        title: '结束时间',
        align: 'center',
        key: 'end_date',
        render:(h,param)=>{
            if (param.row.endDate != null){
                return h('div',formatDateByLong(params.row.endDate,"yyyy-MM-dd h:m:s"));
            }
        }
    },
    /*{
        title: '执行策略',
        align: 'center',
        key: 'misfirePolicy',
        render: (h, params) => {
            let row = params.row;
            let text = '';
            if(row.misfirePolicy === 1){
                text = '继续'
            } else if (row.misfirePolicy === 2) {
                text = '等待'
            } else if (row.misfirePolicy === 3) {
                text = '放弃'
            } else {
                text = '默认'
            }
            return h('span',text);
        }
    },*/
    {
        title: '任务状态',
        align: 'center',
        key: 'status',
        render: (h, params) => {
            //const color = params.row.status === '0' ? 'green' : 'red';
            const text = params.row.status === '0' ? '正常': '删除';
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
        width: 350,
        key: 'handle',
        handle: ['info', 'run', 'edit']
    }
];

export default columns;
