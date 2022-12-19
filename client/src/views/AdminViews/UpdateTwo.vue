<template>
  <div>
    <div class="title">
      <div class="left">请填写经理个人信息</div>
      <br/>
    </div>
    <el-form style="width: 600px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
             class="demo-ruleForm">
      <el-form-item label="经理账户" prop="id">
        <el-input v-model="ruleForm.id" readonly></el-input>
      </el-form-item>
      <el-form-item style="width: 49%" label="姓名" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item style="width: 60%" label="联系方式" prop="telephone">
        <el-input v-model="ruleForm.telephone"></el-input>
      </el-form-item>
      <el-form-item style="width: 60%" label="电子邮箱" prop="email">
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>
      <el-form-item style="width: 60%" label="住址" prop="address">
        <el-input v-model="ruleForm.address"></el-input>
      </el-form-item>
      <el-form-item style="width: 49%" label="基本工资" prop="baseSalary">
        <el-input v-model="ruleForm.baseSalary"></el-input>
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
        name: '',
        sex: '',
        telephone: '',
        email: '',
        address: '',
      },
      rules: {

        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],

      }
    };
  },
  created(){
    const _this = this
    let myData = {"id": _this.$route.query.id};
    axios.post('http://localhost:9091/getOneManagerById',myData).then(function (resp){
      _this.ruleForm = resp.data
    })

  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:9091/updateManager',this.ruleForm).then(function(resp){
            _this.$notify({
              title: '提示',
              message: '修改成功',
              duration: 1500,
              type: 'success'
            });
            _this.$router.push('/ManageTwo')
          })
          console.log(_this.ruleForm)
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
.title {
  margin-bottom: 20px;
}

.left {
  /*margin-left: 5px;*/
  float: left;
  font-weight: bold;
}
</style>