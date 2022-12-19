<template>

    <el-container>
      <el-card class="box-card1" shadow="never">
        <div slot="header" class="clearfix">
          <span style="font-weight: bold;font-size: 20px">文件上传说明：</span>
        </div>
        <div>
          （1）文件数量限制为20个<br/><br/>
          （2）上传文件仅支持pdf格式<br/><br/>
          （3）单个文件大小请勿超过100MB<br/><br/>
          （4）请勿上传和工作内容无关的文件
        </div>
      </el-card>
      <el-card class="box-card2" shadow="never">
        <div class="wide">
          <el-upload
              drag
              action="http://localhost:9091/upload/"
              :headers="headers"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              :on-success="successfn"
              multiple
              accept=".pdf"
              :limit="10"
              :on-exceed="handleExceed"
              :file-list="fileList">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div slot="tip" class="el-upload__tip">只能上传pdf文件，且不超过10MB</div>
          </el-upload>
        </div>
      </el-card>
    </el-container>
</template>

<script>
export default {
  data() {
    return {
      headers:{
        Authorization:localStorage.token
      },
      fileList: []
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      window.open(file.response.url);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
  }
}
</script>

<style scoped>
.box-card1 {
  margin-left: 10px;
  width: 470px;
  height: 600px;
}
.box-card2 {
  margin-left: 20px;
  width: 560px;
  height: 600px;
}
.wide{
  margin:0 auto;
  width: 380px;
}
</style>