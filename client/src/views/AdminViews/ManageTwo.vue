<template>
  <div>
    <div class="title">
      <div class="left">
        <el-button type="primary" @click="handleAdd()">添加经理</el-button>
      </div>
      <div class="right">
        <el-input id="input" placeholder="请输入内容" v-model="input" class="input-with-select">
          <el-select ref="optionRef" style="width: 110px;" v-model="select" slot="prepend" placeholder="请选择">
            <el-option label="经理ID" value="1"></el-option>
            <el-option label="经理姓名" value="2"></el-option>
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
            <el-form-item label="经理 ID">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="姓名">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="性别">
              <span>{{ props.row.sex?"男":"女" }}</span>
            </el-form-item>
            <el-form-item label="职位">
              <span>{{ "经理" }}</span>
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
          label="经理 ID"
          prop="id">
      </el-table-column>
      <el-table-column
          label="姓名"
          prop="name">
      </el-table-column>
      <el-table-column
          label="联系方式"
          prop="telephone">
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
      console.log(index, row);
      this.$router.push({
        path:'/UpdateTwo',
        query:{
          id:row.id,
        }
      })
    },
    handleDelete(index, row) {
      var _this = this;
      this.$confirm('此操作将删除该经理, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let myData = {"id": row.id};
        axios.post('http://localhost:9091/judgeManagerIdInDepartment',myData).then(function(resp1){
          var back = resp1.data;
          if(back === true){
            axios.post('http://localhost:9091/deleteManagerById',myData).then(function(resp2){
              window.location.reload();
            })
          }
          else{
            _this.$notify.error({
              title: '提示',
              message: '该经理正在管理部门，请先将部门的管理权转让给他人！',
              duration: 4000,
            });
          }
        })
      }).catch(() => {

      });
    },
    handleAdd() {
      this.$router.push('/AddManager')
    },
    search() {
      if(this.$refs.optionRef.selected.value==="1"){
        // alert(document.getElementById('input').value);
        let myData={"id":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getManagerById',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
      else{
        let myData={"name":document.getElementById('input').value}
        console.log(myData)
        const _this = this
        axios.post('http://localhost:9091/getManagerByName',myData).then(function (resp) {
          _this.tableData = resp.data;
        })
      }
    }//search
  },//methods
  created(){
    const _this = this
    axios.get('http://localhost:9091/getManager').then(function(resp){
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
        sex: '',
        type: '',
        telephone: '',
        email: '',
        address: ''
      }
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