<template>
  <div>
    <div class="title">
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
          prop="departmentName"
          label="所属部门">
      </el-table-column>
      <el-table-column
          prop="telephone"
          label="联系方式">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作">
        <template slot-scope="scope">
          <el-button
              type="success"
              @click="handleEdit(scope.$index, scope.row)">晋升为经理
          </el-button>

        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<script>
export default {
  methods:{
    handleEdit(index,row){
      var _this = this;
      this.$confirm('此操作将晋升该员工为经理, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let myData = {"id": row.id};
        console.log(myData);
        axios.post('http://localhost:9091/changeOneStaffById',myData).then(function(resp){
          // _this.$notify({
          //   title: '提示',
          //   message: '修改成功',
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
  created() {
    const _this = this
    axios.get('http://localhost:9091/getStaff').then(function(resp){
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
        departmentName:'',
        telephone:'',
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