<template>
  <div class='img'>
    <el-card class="box-card">
      <div slot="header" class="title">
        人力资源管理系统
      </div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="id">
          <el-input style="width:90%" v-model="ruleForm.id"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input style="width:90%" v-model="ruleForm.password"></el-input>
        </el-form-item>

        <el-form-item label="用户身份" prop="usertype">
          <el-select v-model="ruleForm.usertype" placeholder="请选择用户身份">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 90px" type="primary" @click="submitForm('ruleForm')">登录</el-button>
          <el-button style="width: 90px" @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>

      </el-form>
    </el-card>

  </div>
</template>


<script>
export default {
  data() {
    return {
      ruleForm: {
        id: '',
        password: '',
        usertype: ''
      },
      rules: {
        id: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        usertype: [
          {required: true, message: '请选择用户身份', trigger: 'change'}
        ]
      },
      options: [{
        value: '1',
        label: '管理员'
      }, {
        value: '2',
        label: '经理'
      }, {
        value: '3',
        label: '员工'
      },],
      value: '1'
    };
  },
  methods: {
    submitForm(formName) {
      var _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // console.log(_this.ruleForm)
          if (_this.ruleForm.usertype === "1") {//Admin
            console.log(_this.ruleForm)
            axios.post("http://localhost:9091/adminLogin", _this.ruleForm)
                .then(function (resp) {
                  var back = resp.data;
                  console.log(back)

                  if (back === false) {
                    _this.$notify.error({
                      title: '提示',
                      message: '用户名或密码错误',
                      duration: 1500,
                    });
                  } else if (back === true) {
                    //将用户的id存储下来
                    // this.$store.commit("userLogin", this.ruleForm.userName);
                    // this.$store.state.userStore.usernum;
                    localStorage.setItem("id", _this.ruleForm.id)
                    // localStorage.setItem("userType", _this.value)
                    _this.$notify({
                      title: '提示',
                      message: '登录成功',
                      duration: 1500,
                      type: 'success'
                    });
                    _this.$router.push({path: "/DataRecord"});
                  }
                });
          } else if (_this.ruleForm.usertype === "2") {
            console.log(_this.ruleForm)
            axios.post("http://localhost:9091/managerLogin", _this.ruleForm)
                .then(function (resp) {
                  var back = resp.data;
                  console.log(back)

                  if (back === false) {
                    _this.$notify.error({
                      title: '提示',
                      message: '用户名或密码错误',
                      duration: 1500,
                    });
                  } else if (back === true) {
                    //将用户的id存储下来
                    // this.$store.commit("userLogin", this.ruleForm.userName);
                    // this.$store.state.userStore.usernum;
                    localStorage.setItem("id", _this.ruleForm.id)
                    // localStorage.setItem("userType", _this.value)
                    _this.$notify({
                      title: '提示',
                      message: '登录成功',
                      duration: 1500,
                      type: 'success'
                    });
                    _this.$router.push({path: "/ManagerInformation"});
                  }
                });
          } else if (_this.ruleForm.usertype === "3") { // 员工
            console.log(_this.ruleForm)
            axios.post("http://localhost:9091/staffLogin", _this.ruleForm)
                .then(function (resp) {
                  var back = resp.data;
                  console.log(back)

                  if (back === false) {
                    _this.$notify.error({
                      title: '提示',
                      message: '用户名或密码错误',
                      duration: 1500,
                    });
                  } else if (back === true) {
                    //将用户的id存储下来
                    // this.$store.commit("userLogin", this.ruleForm.userName);
                    // this.$store.state.userStore.usernum;
                    localStorage.setItem("id", _this.ruleForm.id)
                    // localStorage.setItem("userType", _this.value)
                    _this.$notify({
                      title: '提示',
                      message: '登录成功',
                      duration: 1500,
                      type: 'success'
                    });
                    _this.$router.push({path: "/StaffInformation"});
                  }
                });
          }
          // else if(){}
        } else {
          console.log('error submit!!');
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

.box-card {
  width: 520px;
  margin-left: 780px;
  margin-top: 140px;
}

.title {
  margin-top: 5px;
  margin-bottom: 5px;
  font-size: 24px;
  text-align: center;
  font-weight: 600;
  color: #409eff;
}


.img {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(../assets/xx.png);
  background-repeat: no-repeat;
  background-size: cover;
}

/*.text {*/
/*  font-size: 140px;*/
/*}*/

/*.item {*/
/*  padding: 18px 0;*/
/*}*/


</style>