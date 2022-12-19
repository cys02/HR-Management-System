<template>
  <div>
    <el-container>
      <aside>
        <el-menu  mode="horizontal">

          <div class="left"><i class="el-icon-user-solid"></i>员工客户端</div>
        </el-menu>
      </aside>
      <el-container>
        <el-menu router :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="/StaffInformation" style="margin-left: 240px">
            个人信息查询</el-menu-item>
          <el-menu-item index="/StaffAttendance">本月考勤情况</el-menu-item>
          <el-menu-item index="/StaffSalary">本月工薪结算</el-menu-item>
<!--          <el-menu-item index="/StaffDocument">文件下载</el-menu-item>-->
          <el-menu-item index="/StaffMail">公司信箱</el-menu-item>
        </el-menu>
      </el-container>
    </el-container>
    <router-view></router-view>
  </div>


</template>

<script>
export default {
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      this.activeIndex = key;
    }
  }
}
</script>

<style scoped>

.el-menu-demo {
  width: 1200px;
  display: flex;
  /*justify-content: center;*/
  margin-bottom: 20px;
}

.left-menu {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.left {
  /*margin-left: 5px;*/
  /*float: left;*/
  font-size: 23px;
  color: #409eff;
  font-weight: bold;
  width: 180px;
  height: 60px;
  text-align: center;
  line-height: 52px;
}

</style>

