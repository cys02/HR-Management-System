<template>
  <div>
    <div class="title">
      <div class="right">
        <el-input id="input" placeholder="请输入内容" v-model="input" class="input-with-select">
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
          prop="lateNumber"
          label="迟到次数">
      </el-table-column>
      <el-table-column
          prop="bunkNumber"
          label="早退次数">
      </el-table-column>
      <el-table-column
          prop="offNumber"
          label="请假次数">
      </el-table-column>
      <el-table-column
          prop="overNumber"
          label="加班次数">
      </el-table-column>
      <el-table-column
          prop="evecNumber"
          label="出差次数">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="primary"
              @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>

        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<script>
export default {
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
      this.$router.push({
        path:'/UpdateStaffRecord',
        query:{
          id:row.id,
        }
      })
    },

    search() {
      if(this.$refs.optionRef.selected.value==="1"){
        // alert(document.getElementById('input').value);
        let myData={"id":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getStaffById',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
      else if(this.$refs.optionRef.selected.value==="2"){
        let myData={"name":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getStaffByName',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
      else {
        let myData={"departmentName":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getStaffByDepartmentName',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
    }
  },//method
  created(){
    var _this = this;
    axios.get('http://localhost:9091/getStaff').then(function(resp){
      _this.tableData = resp.data;
    })
  },
  data() {
    return {
      input: '',
      select: '',
      tableData: [{
        id: '',
        name: '',
        lateNumber:'',
        bunkNumber:'',
        offNumber:'',
        overNumber:'',
        evecNumber:'',
      }]
    }
  }
}
</script>

<style scoped>
.title{
  height: 45px;
}

.right {
  float: right;
}
</style>