<template>
  <div>
    <el-card class="wide">
      <div class="title">
        <div class="right">
          <el-input id="input" placeholder="请输入内容" v-model="input3" class="input-with-select">
            <el-select ref="optionRef" style="width: 110px;" v-model="select" slot="prepend" placeholder="请选择">
              <el-option label="员工ID" value="1"></el-option>
              <el-option label="员工姓名" value="2"></el-option>
            </el-select>
            <el-button style="width: 70px;background-color: #409eff;color: white" slot="append"
                       icon="el-icon-search" @click="search()"></el-button>
          </el-input>
        </div>
      </div>
      <el-table
          :data="tableData"
          height="570"
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
    </el-card>

  </div>
</template>

<script>
export default {
  methods: {
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
.title{
  height: 45px;
}

.wide {
  margin: 0 auto;
  width: 85%;
}

.right {
  float: right;
}
</style>