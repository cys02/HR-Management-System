<template>
  <div>
    <div class="title">
      <div class="left">
        <el-button type="primary" @click="clear()">结算本月工薪</el-button>
      </div>
      <div class="right">
        <el-input id="input" placeholder="请输入内容" v-model="input3" class="input-with-select">
          <el-select ref="optionRef" style="width: 110px;" v-model="select" slot="prepend" placeholder="请选择">
            <el-option label="员工ID" value="1"></el-option>
            <el-option label="员工姓名" value="2"></el-option>
            <el-option label="所属部门" value="3"></el-option>
          </el-select>
          <el-button style="width: 70px;background-color: #409eff;color: white" slot="append"
                     icon="el-icon-search" @click="search()"></el-button>
        </el-input>
      </div>
    </div>
    <el-table
        :data="tableData"
        height="600"
        border
        stripe
        style="width: 100%; ">
      <el-table-column
          prop="id"
          label="员工ID">
      </el-table-column>
      <el-table-column
          prop="name"
          label="员工姓名">
      </el-table-column>
      <el-table-column
          prop="baseSalary"
          label="基本工薪">
      </el-table-column>
      <el-table-column
          prop="reward"
          label="本月奖励金额">
      </el-table-column>
      <el-table-column
          prop="punish"
          label="本月惩罚金额">
      </el-table-column>
      <el-table-column
          prop="finalSalary"
          label="本月总薪酬">
      </el-table-column>

    </el-table>
  </div>

</template>

<script>
export default {
  methods: {
    clear() {
      var _this = this;
      this.$confirm('此操作将重置考勤记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.post('http://localhost:9091/clearUserSalary').then(function (resp) {
          // _this.$notify({
          //   title: '提示',
          //   message: '结算成功',
          //   duration: 1500,
          //   type: 'success'
          // });
          window.location.reload();
        })
      }).catch(() => {

      });
    },

    search() {
      if(this.$refs.optionRef.selected.value==="1"){
        // alert(document.getElementById('input').value);
        let myData={"id":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getStaffSalaryById',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
      else if(this.$refs.optionRef.selected.value==="2"){
        let myData={"name":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getStaffSalaryByName',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
      else {
        let myData={"departmentName":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getStaffSalaryByDepartmentName',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
    }
  },//methods
  created() {
    var _this = this;
    axios.get('http://localhost:9091/getStaffSalary').then(function (resp) {
      _this.tableData = resp.data;
    })
  },
  data() {
    return {
      input3: '',
      select: '',
      tableData: [{
        id: '',
        name: '',
        baseSalary: '',
        reward: '',
        punish: '',
        finalSalary: ''
      }]
    }
  }
}
</script>

<style scoped>
.title {
  height: 45px;
}


.left {
  margin-left: 10px;
  float: left;
  font-weight: bold;
}

.right {
  float: right;
}
</style>