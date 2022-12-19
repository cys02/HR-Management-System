<template>
  <div>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="信件内容">
              <span>{{ props.row.article }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
          label="主题"
          prop="title">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  created(){
    const _this = this
    axios.get('http://localhost:9091/getMail').then(function (resp) {
      console.log(resp);
      _this.tableData = resp.data;
    })
  },
  data() {
    return {
      tableData: [{
        title: '',
        article: '',
      }]
    }
  }
}
</script>

<style scoped>
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