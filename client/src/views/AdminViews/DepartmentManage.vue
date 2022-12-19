<template>
  <div>
    <div class="title">
      <div class="left">
        <el-button type="primary" @click="handleAdd()">添加部门</el-button>
      </div>
      <div class="right">
        <el-input id="input" placeholder="请输入内容" v-model="input" class="input-with-select">
          <el-select ref="optionRef" style="width: 110px;" v-model="select" slot="prepend" placeholder="请选择">
            <el-option label="部门名称" value="1"></el-option>
            <el-option label="经理姓名" value="2"></el-option>
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
          prop="departmentName"
          label="部门名称">
      </el-table-column>
      <el-table-column
          prop="managerName"
          label="经理姓名">
      </el-table-column>
      <el-table-column
          prop="staffCount"
          label="员工人数">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">修改</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<script>
export default {
  methods:{
    handleEdit(index, row) {
      // console.log(index, row);
      this.$router.push({
        path:'/UpdateDepartment',
        query:{
          departmentName:row.departmentName,
        }
      })
    },
    handleDelete(index, row) {
      var _this = this;
      this.$confirm('此操作将删除该部门, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let myData = {"departmentName": row.departmentName};
          if(row.staffCount === 0){
            axios.post('http://localhost:9091/deleteDepartmentByDepartmentName',myData).then(function(resp){
              window.location.reload();
            })
          }
          else{
            _this.$notify.error({
              title: '提示',
              message: '该部门下仍有员工，请先将该部门的员工转移至其他部门！',
              duration: 8000,
            });
          }

      }).catch(() => {

      });
    },
    handleAdd() {
      this.$router.push('/AddDepartment')
    },

    search() {
      if(this.$refs.optionRef.selected.value==="1"){
        // alert(document.getElementById('input').value);
        let myData={"departmentName":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getDepartmentInfoByDepartmentName',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
      else{
        let myData={"name":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getDepartmentInfoByManagerName',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
    }//search
  },//method
  created() {
    const _this = this
    axios.get('http://localhost:9091/getDepartmentInfo').then(function(resp){
      _this.tableData = resp.data;
    })
  },
  data() {
    return {
      input: '',
      select: '',
      tableData: [{
        departmentName:"",
        managerName:"",
        staffCount:""
      }]
    }
  }
}
</script>

<style scoped>
.title{
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