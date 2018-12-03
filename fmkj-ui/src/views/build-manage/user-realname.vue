<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card>
                    <p slot="title">
                        <Icon type="android-remove"></Icon>
                        用户实名认证
                    </p>
                    <Row class="margin-bottom-10">
                        <Input v-model="query.telephone" placeholder="请输入电话号码..." style="width: 200px" />
                        <Input v-model="query.cardnum" placeholder="请输入证件号码..." style="width: 200px" />
                        <Input v-model="query.nickname" placeholder="请输入昵称..." style="width: 200px" />
                        <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    </Row>
                    <div>
                        <Table :loading="loading" :data="pageData" :columns="columns"></Table>
                    </div>
                    <Page  style="text-align:center;margin-top:20px" @on-change="getData" :total="pageInfo.total" :page-size="size" :current="pageInfo.pageNo" size="small" show-elevator show-total></Page>
                </Card>
            </Col>
        </Row>
        <Modal title="预览图片" v-model="showDialog">
            <img :src="picturePath"  style="width: 100%">
        </Modal>
        <Modal title="提示" v-model="showAduitDialog" @on-ok="ok">
            <div class="ivu-modal-confirm-body">
                <div class="ivu-modal-confirm-body-icon ivu-modal-confirm-body-icon-confirm" style="">
                    <span><i class="ivu-icon ivu-icon-help-circled"></i></span>
                </div>
                <span>确定执行审核操作吗?</span>
                <div>
                    <input v-model="query.rejectReason" type="text"
                           style="margin-top: 20px;width: 300px;border: none;border-bottom: #9E9E9E 1px solid;" placeholder="请输入驳回原因..." />
                </div>
            </div>
        </Modal>
    </div>
</template>

<script>
    import {formatDateByLong} from 'utils/time';
    import {getUserRealInfo,identityCardAudit,identityCardRefuse} from "./components/userRealname";
    const url = window.location.origin;
    const payImages = "/payImages/";

    export default {
        name: "user-realname",
        components:{
        },
        data(){
            return{
                showDialog:false,
                showAduitDialog:false,
                loading:true,
                page:1,
                size:20,
                pageInfo:'',
                pageData:[],
                query:{},
                picturePath:'',
                rowID:'',
                columns:[
                    {
                        title: '序号',
                        type: 'index',
                        width: 80,
                        key: 'id',
                        align: 'center'
                    },
                    {
                        title: '昵称',
                        align: 'center',
                        key: 'nickname',
                        editable: true
                    },
                    {
                        title: '名字',
                        align: 'center',
                        key: 'name',
                        editable: true
                    },
                    {
                        title: '电话号码',
                        align: 'center',
                        key: 'telephone',
                        editable: true
                    },
                    {
                        title: '证件号',
                        width:164,
                        align: 'center',
                        key: 'cardnum',
                        editable: true
                    },
                    {
                        title: '证件正面',
                        align: 'center',
                        key: 'fullPhoto',
                        render:(h,params)=>{
                            return h('div',[
                                h('Button',{
                                    props:{
                                        type: 'primary',
                                        size:'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.picturePath=params.row.fullPhoto;
                                            this.showDialog = true;
                                        }
                                    }
                                },"阅览")
                            ])
                        }
                    },
                    {
                        title: '证件反面',
                        align: 'center',
                        key: 'reversePhoto',
                        render:(h,params)=>{
                            return h('div',[
                                h('Button',{
                                    props:{
                                        type: 'primary',
                                        size:'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.picturePath=params.row.reversePhoto;
                                            this.showDialog = true;
                                        }
                                    }
                                },"阅览")
                            ])
                        }
                    },
                    {
                        title: '支付宝账号',
                        align: 'center',
                        key: 'alipayAccount',
                        editable: true
                    },
                    {
                        title: '支付宝收款码',
                        align: 'center',
                        key: 'alipayPhoto',
                        render:(h,params)=>{
                            return h('div',[
                                h('Button',{
                                    props:{
                                        type: 'primary',
                                        size:'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.picturePath=params.row.alipayPhoto;
                                            this.showDialog = true;
                                        }
                                    }
                                },"阅览")
                            ])
                        }
                    },
                    {
                        title: '微信账号',
                        align: 'center',
                        key: 'wechatAccount',
                        editable: true
                    },
                    {
                        title: '微信收款码',
                        align: 'center',
                        key: 'wechatPhoto',
                        render:(h,params)=>{
                            return h('div',[
                                h('Button',{
                                    props:{
                                        type: 'primary',
                                        size:'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.picturePath=params.row.wechatPhoto;
                                            this.showDialog = true;
                                        }
                                    }
                                },"阅览")
                            ])
                        }
                    },
                    {
                        title: '提交时间',
                        align: 'center',
                        width:100,
                        key: 'time',
                        render:(h,params)=>{
                            return h('div',formatDateByLong(params.row.time,"yyyy-MM-dd hh:mm:ss"));
                        }
                    },
                    {
                        title: '审核状态',
                        align: 'center',
                        key: 'cardStatus',
                        render:(h,params)=>{
                            let text = "";
                            const cardStatus = params.row.cardStatus;
                            if (cardStatus === 0) {
                                text = "身份未认证";
                            }
                            if (cardStatus === 1) {
                                text = "未审核";
                            }
                            if (cardStatus === 2) {
                                text = "审核通过";
                            }
                            if (cardStatus === -1) {
                                text = "已驳回";
                            }
                            return h('span',text);
                        }
                    },
                    {
                        title: '操作',
                        align: 'center',
                        width: 180,
                        key: 'handle',
                        render: (h, params) => {
                            if (params.row.cardStatus == 1)
                            {
                                return h('div', [
                                    h('Button', {
                                        props: {
                                            type: 'primary',
                                            size: 'small'
                                        },
                                        style: {
                                            marginRight: '5px'
                                        },
                                        on: {
                                            click: () => {
                                                this.$Modal.confirm({
                                                    title: '提示',
                                                    content: '确定执行审核操作吗?',
                                                    width: 400,
                                                    onOk: () => {
                                                        identityCardAudit (params.row.id,1).then(data => {
                                                            if (data.status === 200) {
                                                                this.$Message.success(data.data.message);
                                                                this.getData(this.page, this.tabIndex);
                                                            } else {
                                                                this.$Message.error(data.data.message);
                                                            }
                                                        }).catch(error => {
                                                            this.$Message.error('审核出现异常：' + error);
                                                        });
                                                    },
                                                    onCancel: () => {
                                                    }
                                                })
                                            }
                                        }
                                    }, '审核'),
                                    h('Button', {
                                        props: {
                                            type: 'error',
                                            size: 'small'
                                        },
                                        on: {
                                            click: () => {
                                                this.showAduitDialog=true;
                                                this.rowID = params.row.id;
                                            }
                                        }
                                    }, '驳回')
                                ])
                            } else if (params.row.cardStatus == -1)
                            {
                                return h('div',[
                                    h('Button', {
                                        props: {
                                            type: 'primary',
                                            size: 'small'
                                        },
                                        style: {
                                            marginRight: '5px'
                                        },
                                        on: {
                                            click: () => {
                                                this.$Modal.confirm({
                                                    title: '提示',
                                                    content: '确定执行审核操作吗?',
                                                    width: 400,
                                                    onOk: () => {
                                                        identityCardAudit (params.row.id,1).then(data => {
                                                            if (data.status === 200) {
                                                                this.$Message.success(data.data.message);
                                                                this.getData(this.page, this.tabIndex);
                                                            } else {
                                                                this.$Message.error(data.data.message);
                                                            }
                                                        }).catch(error => {
                                                            this.$Message.error('审核出现异常：' + error);
                                                        });
                                                    },
                                                    onCancel: () => {
                                                    }
                                                })
                                            }
                                        }
                                    }, '审核'),
                                    h('Button',{
                                        props: {
                                            type: 'error',
                                            size: 'small',
                                            disabled: true
                                        },
                                        style: {

                                        },
                                        on: {
                                            click: () => {
                                                this.showAduitDialog=true;
                                                this.rowID = params.row.id;
                                            }
                                        }
                                    },'驳回')
                                ])
                            }
                        }
                    }
                ]
            }
        },
        methods:{
            getData(page){
                this.page=page,
                this.loading=true,
                getUserRealInfo(page,this.size,this.query).then(data=>{
                    this.loading=false;
                    if(data.status===200){
                        console.log(data.data);
                        this.pageInfo=data.data;
                        this.pageData=data.data.dataList;
                    }else{
                        this.$Message.error(data.$Message);
                    }
                }).catch(error=>{
                   this.$Message.error("服务器异常"+error)
                });
            },
            handleSearch(){
                this.getData(1);
            },
            ok(){
                identityCardAudit(this.rowID,2,this.query).then(data => {
                    if (data.status === 200) {
                        this.$Message.success(data.data.message);
                        this.getData(this.page, this.tabIndex);
                    } else {
                        this.$Message.error(data.data.message);
                    }
                }).catch(error => {
                    this.$Message.error('驳回出现异常：' + error);
                });
            },
            cancel() {
                this.showDialog=false;
                this.title="";
                this.content="";
                this.getData(1);
            },
        },
        created(){
           /* this.initColumns();*/
            this.getData(1);
        }
    }
</script>

<style scoped>

</style>
