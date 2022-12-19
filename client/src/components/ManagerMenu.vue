<template>
  <div>
    <el-container>
      <aside>
        <el-menu mode="horizontal">

          <div class="left"><i class="el-icon-s-custom"></i>经理客户端</div>
        </el-menu>
      </aside>
      <el-container>
        <el-menu router :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="/ManagerInformation" style="margin-left: 180px">
            个人信息查询
          </el-menu-item>
          <el-menu-item index="/ManagerAttendance">本人考勤情况</el-menu-item>
          <el-menu-item index="/AttendanceManage">部门考勤管理</el-menu-item>
          <el-submenu index="2">
            <template slot="title">本月工薪结算</template>
            <el-menu-item index="/ManagerSalary">本人工薪结算</el-menu-item>
            <el-menu-item index="/DepartmentSalary">部门工薪情况</el-menu-item>
          </el-submenu>
<!--          <el-menu-item index="/ManagerDocument">文件下载</el-menu-item>-->
          <el-menu-item index="/ManagerMail">公司信箱</el-menu-item>
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

