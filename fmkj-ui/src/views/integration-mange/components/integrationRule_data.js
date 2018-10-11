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
        align: 'center'
    },
    {
        title: '飞羽区间最小值',
        align: 'center',
        key: 'fyMin',
        editable: true
    },
    {
        title: '飞羽区间最大值',
        align: 'center',
        key: 'fyMax',
        editable: true
    },
    {
        title: '权重',
        key: 'weight',
        align: 'center',
        editable:true
    },
    {
        title: '分配比例',
        align: 'center',
        key: 'allotPercent',
        editable: true
    },
    {
        title: '稀释比例',
        align: 'center',
        key: 'dilutPercent',
        editable: true
    },
    {
        title: '创建时间',
        align: 'center',
        key: 'createDate',
        render:(h,params) => {
            return h('div',formatDateByLong(params.row.createDate,"yyyy-MM-dd h:m:s"));
        }
    },
    {
        title: '更新时间',
        align: 'center',
        key: 'updateDate',
        render:(h,params)=>{
            if(params.row.updateDate != null){
                return h('div',formatDateByLong(params.row.updateDate,"yyyy-MM-dd h:m:s"));
            }
        }
    },
    {
        title: '总分配描述',
        align: 'center',
        key: 'allotDesc',
        editable: true
    },
    {
        title: '操作',
        align: 'center',
        width: 350,
        key: 'handle',
        handle: ['edit', 'delete']
    }
];

export default columns;
