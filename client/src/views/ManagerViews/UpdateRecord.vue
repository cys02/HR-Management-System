<template>
  <div>
    <el-card class="wide">
      <div class="title">
        <div class="left">请修改员工出勤情况</div>
        <br/>
      </div>
      <el-form style="width: 600px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
               class="demo-ruleForm">
        <el-form-item label="员工账户" >
          <el-input v-model="ruleForm.id" readOnly></el-input>
        </el-form-item>
        <el-form-item style="width: 49%" label="姓名" prop="name">
          <el-input v-model="ruleForm.name" readonly></el-input>
        </el-form-item>
        <el-form-item style="width:40%" label="迟到次数" prop="lateNumber">
          <el-input v-model.number="ruleForm.lateNumber"></el-input>
        </el-form-item>
        <el-form-item style="width: 40%" label="早退次数" prop="bunkNumber">
          <el-input v-model.number="ruleForm.bunkNumber"></el-input>
        </el-form-item>
        <el-form-item style="width: 40%" label="请假次数" prop="offNumber">
          <el-input v-model.number="ruleForm.offNumber"></el-input>
        </el-form-item>
        <el-form-item style="width: 40%" label="加班次数" prop="overNumber">
          <el-input v-model.number="ruleForm.overNumber"></el-input>
        </el-form-item>
        <el-form-item style="width: 40%" label="出差次数" prop="evecNumber">
          <el-input v-model.number="ruleForm.evecNumber"></el-input>
        </el-form-item>


        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
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
        name: '',
        lateNumber:'',
        bunkNumber:'',
        offNumber:'',
        overNumber:'',
        evecNumber:'',
      },

      rules: {
        lateNumber:[
          { required: true, message: '请输入迟到次数'},
          { type: 'number', message: '迟到次数必须为数字值'}
        ],
        bunkNumber:[
          { required: true, message: '请输入早退次数'},
          { type: 'number', message: '早退次数必须为数字值'}
        ],
        offNumber:[
          { required: true, message: '请输入请假次数'},
          { type: 'number', message: '请假次数必须为数字值'}
        ],
        overNumber:[
          { required: true, message: '请输入加班次数'},
          { type: 'number', message: '加班次数必须为数字值'}
        ],
        evecNumber:[
          { required: true, message: '请输入出差次数'},
          { type: 'number', message: '出差次数必须为数字值'}
        ],
      }
    };
  },
  created(){
    const _this = this
    let myData = {"id": _this.$route.query.id};
    axios.post('http://localhost:9091/getOneStaffById',myData).then(function (resp){
      _this.ruleForm = resp.data
    })

  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.ruleForm)
          axios.post('http://localhost:9091/updateStaffRecord', this.ruleForm).then(function (resp) {
            _this.$notify({
              title: '提示',
              message: '修改成功',
              duration: 1500,
              type: 'success'
            });
            _this.$router.push('/AttendanceManage')
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
.wide {
  margin: 0 auto;
  width: 60%;
}

.title {
  margin-bottom: 20px;
}

.left {
  /*margin-left: 5px;*/
  float: left;
  font-weight: bold;
}
</style>