<template>
  <div>
    <el-card class="wide">
      <div class="title">
        <div class="left">考勤情况查询</div>
      </div>
      <el-descriptions class="margin-top" title=" " :column="1" :size="size" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-close"></i>
            迟到次数
          </template>
          {{ this.tableData.lateNumber }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-close"></i>
            早退次数
          </template>
          {{ this.tableData.bunkNumber}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-close"></i>
            请假次数
          </template>
          {{ this.tableData.offNumber }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-check"></i>
            加班次数
          </template>
          {{ this.tableData.overNumber }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-check"></i>
            出差次数
          </template>
          {{ this.tableData.evecNumber }}
        </el-descriptions-item>
      </el-descriptions>
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
  created() {
    var _this = this;
    _this.tableData.id = localStorage.getItem("id");
    // console.log(_this.tableData.id);
    let myData = {"id": _this.tableData.id};
    axios.post("http://localhost:9091/getOneStaffById", myData)
        .then(resp=> {
          // console.log(resp);
          _this.tableData = resp.data;
        })
  },
  data() {

    return {
      tableData: [{
        lateNumber: '',
        bunkNumber: '',
        offNumber: '',
        overNumber: '',
        evecNumber: '',
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