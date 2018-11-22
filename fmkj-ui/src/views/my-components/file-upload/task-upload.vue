<style lang="less">
    @import '../../../styles/common.less';
    @import 'upload.less';
</style>


<template>
    <Upload
            multiple
            action="/backManger/fmkj/image/upload"
            :format="['jpg','jpeg','png']"
            :on-format-error="handleFormatError"
            :before-upload="handleBeforeUpload"
            :on-progress="handleProgress"
            :on-success="handleSuccess"
            :on-error="handleError"
    >
        <Button type="ghost" icon="ios-cloud-upload-outline">上传文件</Button>
    </Upload>
</template>

<script>
export default {
    name: 'taskUpload',
    data () {
        return {
            imgName: '',
            visible: false,
            uploadList: []
        };
    },
    methods: {
        handleFormatError (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请选择图片文件。'
            });
        },
        handleBeforeUpload (file) {
            this.$Notice.warning({
                title: '文件准备上传',
                desc: '文件 ' + file.name + ' 准备上传。'
            });
        },
        handleProgress (event, file) {
            this.$Notice.info({
                title: '文件正在上传',
                desc: '文件 ' + file.name + ' 正在上传。'
            });
        },
        handleSuccess (evnet, file) {
            this.$emit('uploadSuccess', evnet);
            this.$Notice.success({
                title: '文件上传成功',
                desc: '文件 ' + file.name + ' 上传成功。'
            });
        },
        handleError (event, file) {
            this.$Notice.error({
                title: '文件上传成功',
                desc: '文件 ' + file.name + ' 上传失败。'
            });
        }
    }/*,
    mounted () {
        this.uploadList = this.$refs.upload.fileList;
    }*/
};
</script>
