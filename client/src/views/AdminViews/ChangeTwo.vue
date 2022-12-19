<template>
  <div>
    <div class="title">
      <div class="right">
        <el-input id="input" placeholder="请输入内容" v-model="input3" class="input-with-select">
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
        height="600"
        border
        stripe
        style="width: 100%; ">
      <el-table-column
          prop="id"
          label="经理ID">
      </el-table-column>
      <el-table-column
          prop="name"
          label="经理姓名">
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
              type="danger"
              @click="handleEdit(scope.$index, scope.row)">降级为员工
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
      this.$confirm('此操作将降级该经理为员工, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let myData = {"id": row.id};
        axios.post('http://localhost:9091/judgeManagerIdInDepartment',myData).then(function(resp1){
          var back = resp1.data;
          if(back === true){
            axios.post('http://localhost:9091/changeOneManagerById',myData).then(function(resp2){
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
      input3: '',
      select: '',
      tableData: [{
        id: '',
        name: '',
        department:'',
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