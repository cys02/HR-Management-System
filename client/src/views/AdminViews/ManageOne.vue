<template>
  <div>
    <div class="title">
      <div class="left">
        <el-button type="primary" @click="handleAdd()">添加员工</el-button>
      </div>
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
        stripe
        style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="员工 ID">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="姓名">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="性别">
              <span>{{ props.row.sex ? "男" : "女" }}</span>
            </el-form-item>
            <el-form-item label="职位">
              <span>{{ "员工" }}</span>
            </el-form-item>
            <el-form-item label="所在部门">
              <span>{{ props.row.departmentName }}</span>
            </el-form-item>
            <el-form-item label="联系方式">
              <span>{{ props.row.telephone }}</span>
            </el-form-item>
            <el-form-item label="邮箱">
              <span>{{ props.row.email }}</span>
            </el-form-item>
            <el-form-item label="住址">
              <span>{{ props.row.address }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
          label="员工 ID"
          prop="id">
      </el-table-column>
      <el-table-column
          label="姓名"
          prop="name">
      </el-table-column>
      <el-table-column
          label="所属部门"
          prop="departmentName">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">修改
          </el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
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
        path: '/UpdateOne',
        query: {
          id: row.id,
        }
      })
    },
    handleDelete(index, row) {
      // console.log(row.id);
      var _this = this;
      this.$confirm('此操作将删除该员工, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let myData = {"id": row.id};
        // console.log(myData);
        axios.post('http://localhost:9091/deleteStaffById', myData).then(function (resp) {
          window.location.reload();
        })
      }).catch(() => {

      });

    },
    handleAdd() {
      this.$router.push('/AddStaff')
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
  },
  created() {
    const _this = this
    axios.get('http://localhost:9091/getStaff').then(function (resp) {
      _this.tableData = resp.data;
    })
  }
  ,
  data() {
    return {
      input: '',
      select: '',
      tableData: [{
        id: '',
        name: '',
        sex: '',
        userType: '',
        departmentName: '',
        telephone: '',
        email: '',
        address: ''
      },
      ],

    }
  }
}
</script>

<style scoped>


.left {
  margin-left: 10px;
  float: left;
  font-weight: bold;
}

.right {
  float: right;
}

.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>