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
        <Modal v-model="showDialog" title="阅读图片" ok-text="取消" cancel-text="" :loading="loading" @on-cancel="cancel">
            <Form ref="tiketForm" :label-width="120">
                <div>
                    <img width="100" :src="picturePath"  />
                </div>
            </Form>
        </Modal>
    </div>
</template>

<script>
    import {formatDateByLong} from 'utils/time';
    import {getUserRealInfo,identityCardAudit,identityCardRefuse} from "./components/userRealname";
    const url = window.location.origin;

    export default {
        name: "user-realname",
        components:{
        },
        data(){
            return{
                showDialog:false,
                loading:true,
                page:1,
                size:20,
                pageInfo:'',
                pageData:[],
                query:{},
                picturePath:'',
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
                                            this.picturePath=url+params.row.fullPhoto;
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
                                            this.picturePath=url+params.row.reversePhoto;
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
                                            this.picturePath=url+params.row.alipayAccount;
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
                                            this.picturePath=url+params.row.wechatPhoto;
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
                            return h('div',formatDateByLong(params.row.time,"yyyy-MM-dd hh-mm-ss"));
                        }
                    },
                    {
                        title: '审核状态',
                        align: 'center',
                        key: 'status',
                        editable: true
                    },
                    {
                        title: '操作',
                        align: 'center',
                        width: 260,
                        key: 'handle',
                        render: (h, params) => {
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
                                                            this.$Message.success('审核成功');
                                                            this.getData(this.page, this.tabIndex);
                                                        } else {
                                                            this.$Message.error(data.message);
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
                                            this.$Modal.confirm({
                                                title: '提示',
                                                content: '确定执行驳回操作吗?',
                                                width: 400,
                                                onOk: () => {
                                                    identityCardAudit(params.row.id,2).then(data => {
                                                        if (data.status === 200) {
                                                            this.$Message.success('驳回成功');
                                                            this.getData(this.page, this.tabIndex);
                                                        } else {
                                                            this.$Message.error(data.message);
                                                        }
                                                    }).catch(error => {
                                                        this.$Message.error('驳回出现异常：' + error);
                                                    });
                                                },
                                                onCancel: () => {
                                                }
                                            })
                                        }
                                    }
                                }, '驳回')
                            ])
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
            /*clickCancel(){
                this.showDialog=false;
            },*/
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
