<template>
  <div>
    <el-card class="wide">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="信件主题" prop="title">
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>
        <el-form-item label="信件内容" prop="article" >
          <el-input type="textarea" v-model="ruleForm.article"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <div class="wide">
      <el-button type="danger" @click="exit" class="exitButton">退出登录</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        title: '',
        article: '',
      },
      rules: {
        title: [
          { required: true, message: '请填写信件主题', trigger: 'blur' },
        ],
        article: [
          { required: true, message: '请填写信件内容', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var _this = this
          axios.post('http://localhost:9091/addMail', this.ruleForm).then(function (resp) {
            _this.$notify({
              title: '提示',
              message: '提交成功',
              duration: 1500,
              type: 'success'
            });
          })
          console.log(_this.ruleForm)
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    exit() {
      // 清除token
      localStorage.removeItem("id");
      this.$router.push({ path: "/" });
      this.$notify({
        title: '提示',
        message: '成功退出登录',
        duration: 1500,
        type: 'success'
      });
    }
  },
}
</script>

<style scoped>
.wide {
  margin: 0 auto;
  width: 55%;
}

.exitButton {
  margin-top: 10px;
  width: 100%;
}
</style>