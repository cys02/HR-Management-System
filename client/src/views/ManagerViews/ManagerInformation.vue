<template>
  <div>
    <el-card class="wide">
      <div class="title">
        <div class="left">经理个人信息</div>
      </div>
      <el-descriptions class="margin-top" title=" " :column="1" :size="size" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-document"></i>
            用户名
          </template>
          {{ this.tableData.id }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            姓名
          </template>
          {{ this.tableData.name }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-male"></i>
            性别
          </template>
          {{ (this.tableData.sex===true)?"男":"女" }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号
          </template>
          {{ this.tableData.telephone }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-message"></i>
            邮箱
          </template>
          {{ this.tableData.email }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-location-outline"></i>
            居住地
          </template>
          {{ this.tableData.address }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-collection-tag"></i>
            职位
          </template>
          公司经理
        </el-descriptions-item>

      </el-descriptions>
    </el-card>
    <el-card class="wide1">
      <div class="title">
        <div class="left">正在管理的部门</div>
      </div>
      <el-table
          :data="tableData1"
          border
          style="width: 100%;">
        <el-table-column
            prop="departmentName"
            label="部门名称">
        </el-table-column>
      </el-table>
    </el-card>
    <div class="wide">
      <el-button type="danger" @click="exit" class="exitButton">退出登录</el-button>
    </div>
  </div>
</template>

<script>
export default {
  methods:{
    exit() {
      // 清除token
      localStorage.removeItem("id");
      this.$router.push({ path: "/" });
      this.$notify({
        title: '提示',
        message: '成功退出登录',
        duration: 1500,
        type: 'success'
      });
    }
  },
  created(){
    var _this = this;
    _this.tableData.id = localStorage.getItem("id");
    console.log(_this.tableData.id);
    let myData = {"id": _this.tableData.id};
    axios.post("http://localhost:9091/getOneManagerById", myData)
        .then(resp=> {
          // console.log(resp);
          _this.tableData = resp.data;
        })
    axios.post("http://localhost:9091/getDepartmentNameById", myData)
        .then(resp=> {
          console.log(resp);
          _this.tableData1 = resp.data;
        })

  },
  data() {
    return {
      tableData:[{
        id: '',
        name: '',
        sex: '',
        telephone: '',
        email: '',
        address: '',
      },],
      tableData1:[{
        departmentName:''
      },],
    };
  }
}
</script>

<style scoped>
.wide {
  margin: 0 auto;
  width: 55%;
}
.wide1 {
  margin: 0 auto;
  margin-top: 5px;
  width: 55%;
}

.exitButton {
  margin-top: 10px;
  width: 100%;
}

.left {
  /*margin-left: 5px;*/
  float: left;
  font-weight: bold;
}

.title {
  margin-bottom: 20px;
}


</style>