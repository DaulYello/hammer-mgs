import {
  formatDateByLong
} from 'utils/time';
export const columns = [
    {
        title: '序号',
        type: 'index',
        width: 80,
        key: 'jobId',
        align: 'center'
    },
    {
        title: '任务编号',
        type: 'index',
        key: 'jobId',
        align: 'center'
    },
    {
        title: '任务名称',
        align: 'center',
        key: 'jobName',
        editable: true
    },
    {
        title: '任务组名',
        align: 'center',
        key: 'jobGroup',
        editable: true
    },
    {
        title: '方法名称',
        align: 'center',
        key: 'methodName',
        editable: true
    },
    {
        title: '方法参数',
        align: 'center',
        key: 'methodParams',
        editable: true
    },
    {
        title: '执行表达式',
        align: 'center',
        key: 'cronExpression',
        editable: true
    },
    {
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
    },
    {
        title: '任务状态',
        align: 'center',
        key: 'status',
        render: (h, params) => {
            const color = params.row.status === '0' ? 'green' : 'red';
            const text = params.row.status === '0' ? '正常': '暂停';
            return h('Tag', {
                props: {
                    color: color
                }
            }, text);
        }
    },
    {
        title: '创建时间',
        align: 'center',
        width: 150,
        key: 'createTime',
        render: (h, params) => {
            return h('div',formatDateByLong(params.row.createTime,"yyyy-MM-dd h:m:s"));
        }
    },
    {
        title: '备注',
        align: 'center',
        width: 150,
        key: 'remark',
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
