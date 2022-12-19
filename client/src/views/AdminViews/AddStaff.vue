<template>
  <div>
    <div class="title">
      <div class="left">请填写员工个人信息</div>
      <br/>
    </div>
    <el-form style="width: 600px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
             class="demo-ruleForm">
      <el-form-item label="员工账户" prop="id">
        <el-input v-model="ruleForm.id"></el-input>
      </el-form-item>
      <el-form-item label="账户密码" prop="password">
        <el-input v-model="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item style="width: 49%" label="姓名" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="ruleForm.sex" placeholder="请选择性别">
          <el-option label="男" value="1"></el-option>
          <el-option label="女" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="width: 60%" label="所属部门" prop="departmentName">
        <el-input v-model="ruleForm.departmentName"></el-input>
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
    var judgeId = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入员工账户'));
      } else {
        // console.log(this.ruleForm);
        const _this = this;
        axios.post('http://localhost:9091/judgeId', this.ruleForm).then(function (resp) {
          // console.log(resp.data);
          var back = resp.data;
          if (back === false) {
            callback(new Error('该用户名已存在！'));
          } else callback();
        })
      }
    };
    var judgeDepartmentName = (rule, value, callback) => {
      if (value === '') {
        callback();
      }
      else {
        axios.post('http://localhost:9091/judgeDepartmentName', this.ruleForm).then(function (resp) {
          // console.log(resp.data);
          var back = resp.data;
          if (back === false) {
            callback(new Error('公司不存在该部门！'));
          } else callback();
        })
      }
    }
    return {
      ruleForm: {
        id: '',
        password: '',
        name: '',
        sex: '',
        departmentName: '',
        telephone: '',
        email: '',
        address: '',
      },

      rules: {
        id: [
          {required: true, message: '请输入员工账户', trigger: 'blur'},
          {validator: judgeId, trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入账户密码', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        sex: [
          {required: true, message: '请选择性别', trigger: 'change'}
        ],
        departmentName: [
          {required: true, message: '请输入部门名称', trigger: 'blur'},
          {validator: judgeDepartmentName, trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:9091/addStaff', this.ruleForm).then(function (resp) {
            _this.$notify({
              title: '提示',
              message: '添加成功',
              duration: 1500,
              type: 'success'
            });
            _this.$router.push('/ManageOne')
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