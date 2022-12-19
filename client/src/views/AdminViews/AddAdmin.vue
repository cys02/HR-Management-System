<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="管理员账户" style="width: 50%" prop="id">
        <el-input v-model="ruleForm.id"></el-input>
      </el-form-item>
      <el-form-item label="管理员密码" style="width: 50%" prop="password">
        <el-input v-model="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {

    return {
      ruleForm: {
        id: '',
        password:'',
      },
      rules: {
        id: [
          {required: true, message: '请输入管理员账户', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入管理员密码', trigger: 'blur'},
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.ruleForm)
          axios.post('http://localhost:9091/addAdmin',this.ruleForm).then(function(resp){
            // console.log(resp)
            _this.$notify({
              title: '提示',
              message: '添加成功',
              duration: 1500,
              type: 'success'
            });
            _this.$router.push('/DataRecord')
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>

</style>