<template>
    <div>
        <Row class="margin-top-10">
            <Col>
                <Card>
                    <Row style="text-align: right">
                        <Button type="success" @click="publish">发布公告</Button>
                    </Row>
                    <div align="center">
                        <span style="font-weight: bold">标题:</span>
                        <Input v-model="title" placeholder="请输入标题..." style="width: 1000px" />
                    </div>
                    <br>
                    <div>
                        <editor
                                class="editor"
                                :value="content"
                                :setting="editorSetting"
                                @show="editors"
                                :url              = "Url"
                                :max-size         = "MaxSize"
                                :accept           = "Accept"
                                :with-credentials = "withCredentials"
                                @on-upload-fail         = "onEditorReady"
                                @on-upload-success= "onEditorUploadComplete"></editor>
                    </div>
                </Card>
            </Col>
        </Row>
    </div>
</template>
<script type="text/ecmascript-6">
    import editor from "../my-components/text-editor/editor";
    import {
        publishNotice,
        editNotify
    } from './components/configurate.js';
    export default {
        props: {
            beforePush: {
                type: Function,
                default: (item) => {
                    return true;
                }
            }
        },
        data() {
            return {
                title: '',
                id: '',
                query: this.$route.query,
                editorSetting: { // 配置富文本编辑器高
                    height: 500
                },
                Url: 'http://localhost:8080/backManger/upload/singleUpload', // 图片对应的上传地址
                MaxSize: 75765, // 文件大小
                Accept: 'image/jpeg, image/png', // 文件格式
                withCredentials: true,
                content: '' // 富文本编辑器双向绑定的内容
            }
        },
        components: { // 引入组件
            editor
        },
        methods: {
            editors(content) { // editor组件传过来的值赋给content
                this.content = content
            },
            onEditorReady(ins, ina) { // 上传失败的函数
                console.log('ins')
                console.log(ins)
                console.log(ina)
            },
            onEditorUploadComplete(json) { // 处理上传图片后返回数据，添加img标签到编辑框内
                console.log('json')
                console.log(json)
                console.log(json[0].data.filePath)
                this.content = this.content + '<img src=' + json[0].data.filePath + '>'
            },
            publish (){
                if(this.title.trim() == ""){
                    this.$Message.error("标题不能为空！");
                    return;
                }
                if(this.id == ""){
                    publishNotice(this.content,this.title).then(data=>{
                        if (data.status==200){
                            this.$Message.success("发布成功！");
                            this.$router.push({name: 'noticeSys'});
                            this.closePage('addNotice');
                        }else {
                            this.$Message.error(data.message);
                        }
                    });
                }else{
                    editNotify(this.id,this.title,this.content).then(data=>{
                        if (data.status==200){
                            this.$Message.success("修改成功！");
                            this.$router.push({name: 'noticeSys'});
                            this.closePage('addNotice');
                        }else {
                            this.$Message.error(data.message);
                        }
                    });
                }
            },
            closePage (name) {
                let pageOpenedList = this.$store.state.app.pageOpenedList;
                let lastPageObj = pageOpenedList[0];
                let len = pageOpenedList.length;
                for (let i = 1; i < len; i++) {
                    if (pageOpenedList[i].name === name) {
                        if (i < (len - 1)) {
                            lastPageObj = pageOpenedList[i + 1];
                        } else {
                            lastPageObj = pageOpenedList[i - 1];
                        }
                        break;
                    }
                }
                this.$store.commit('removeTag', name);
                this.$store.commit('closePage', name);
                pageOpenedList = this.$store.state.app.pageOpenedList;
                localStorage.pageOpenedList = JSON.stringify(pageOpenedList);
                this.linkTo(lastPageObj);
            },
            linkTo (item) {
                let routerObj = {};
                routerObj.name = item.name;
                if (item.argu) {
                    routerObj.params = item.argu;
                }
                if (item.query) {
                    routerObj.query = item.query;
                }
                if (this.beforePush(item)) {
                    this.$router.push(routerObj);
                }
            }
        },
        mounted () {
            if(this.query.id !== undefined){
                this.id = this.query.id;
                this.title = this.query.title;
                this.content = this.query.content;
            }
        }
    }
</script>
<style scoped >

</style>
