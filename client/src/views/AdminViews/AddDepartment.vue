<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="部门名称" style="width: 50%" prop="departmentName">
        <el-input v-model="ruleForm.departmentName"></el-input>
      </el-form-item>
      <el-form-item label="经理账户" style="width: 50%" prop="id">
        <el-input v-model="ruleForm.id"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">创建部门</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    var judgeDepartmentName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入部门名称'));
      }
      else {
        axios.post('http://localhost:9091/judgeDepartmentName', this.ruleForm).then(function (resp) {
          // console.log(resp.data);
          var back = resp.data;
          if (back === true) {
            callback(new Error('该部门已经存在'));
          } else callback();
        })
      }
    };
    var judgeManagerId = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入经理账户'));
      } else {
        // console.log(this.ruleForm);
        const _this = this;
        axios.post('http://localhost:9091/judgeManagerId', this.ruleForm).then(function (resp) {
          // console.log(resp.data);
          var back = resp.data;
          if (back === true) {
            callback(new Error('该经理不存在！'));
          } else callback();
        })
      }
    };
    return {
      ruleForm: {
        departmentName: '',
        id:'',
      },
      rules: {
        departmentName: [
          {required: true, message: '请输入部门名称', trigger: 'blur'},
          {validator: judgeDepartmentName, trigger: 'blur'}
        ],
        id: [
          {required: true, message: '请输入经理账户', trigger: 'blur'},
          {validator: judgeManagerId, trigger: 'blur'}
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
          axios.post('http://localhost:9091/addDepartment',this.ruleForm).then(function(resp){
            // console.log(resp)
            _this.$notify({
              title: '提示',
              message: '添加成功',
              duration: 1500,
              type: 'success'
            });
            _this.$router.push('/DepartmentManage')
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