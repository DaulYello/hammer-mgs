import {
  formatDateByLong
} from 'utils/time';
/*const logo = '';
const image = '';*/
export const columns = [
    {
        title: '序号',
        type: 'index',
        width: 80,
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
        key: 'reward',
        editable: true
    },
    {
        title: '审核周期',
        align: 'center',
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
        title: 'logo图片',
        align: 'center',
        render:(h,params)=>{
            return h('Modal',[
                h('Button',[
                    h('',{
                        props:{
                            type:'primary',
                            size:'small',
                            showDialog:false
                        },
                        attrs: {
                            src: params.row.imageUrl
                        },
                        on:{
                            click: () =>{
                                this.showDialog = true;
                            }
                        }
                    })
                ],"预览")
            ])
        }
    },
    {
        title: '详情图片',
        align: 'center',
        render:(h,params)=>{
            return h('div',[
                h('Button',{
                    props:{
                        type:'primary',
                        size:'small'
                    },
                    on:{
                        click: () =>{
                            this.showDialog = true;
                            this.picturePathImage = params.row.imageUrl;
                        }
                    }
                },"预览")
            ])
        }
    },
    {
        title: '创建时间',
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
        key: 'updateDate',
        render:(h,params)=>{
            if (params.row.updateDate != null){
                return h('div',formatDateByLong(params.row.updateDate,"yyyy-MM-dd hh:m:s"));
            }
        }
    },
    {
        title: '开始时间',
        align: 'center',
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
        key: 'endDate',
        render:(h,params)=>{
            if (params.row.endDate != null){
                return h('div',formatDateByLong(params.row.endDate,"yyyy-MM-dd hh:mm:ss"));
            }
        }
    },
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
        width: 250,
        key: 'handle',
        handle: ['info', 'run', 'edit']
    }
];


export function ok(data) {
    return data;
}
export default columns;

export let picturePathLogo;
export let showDialog;
export let picturePathImage;
