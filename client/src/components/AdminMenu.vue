<template>
  <div>
    <el-container style="height: 747px; border: 1px solid #eee">
      <el-aside width="isCollapse?'64px':'200px'" style="background-color: rgb(255, 255, 255)">
        <el-radio-group v-model="isCollapse" style="margin-bottom: 5px;margin-top: 8px;">
          <el-button type="text" class="button" @click="ChangeCollapse">
            <i class="el-icon-s-fold" v-if="!isCollapse"></i>
            <i class="el-icon-s-unfold" v-if="isCollapse"></i>
          </el-button>
        </el-radio-group>
        <el-menu router :default-openeds="[]" default-active="/DataRecord" class="el-menu-vertical-demo" @open="handleOpen"
                 @close="handleClose"
                 :collapse="isCollapse">

          <el-menu-item index="/DataRecord">
            <i class="el-icon-s-data"></i>
            <span slot="title">数据统计</span>
          </el-menu-item>

          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span slot="title">人员管理</span>
            </template>
              <el-menu-item index="/ManageOne">员工管理</el-menu-item>
              <el-menu-item index="/ManageTwo">经理管理</el-menu-item>
              <el-menu-item index="/AddAdmin">添加管理员</el-menu-item>
          </el-submenu>

          <el-menu-item index="/DepartmentManage">
            <i class="el-icon-office-building"></i>
            <span slot="title">部门管理</span>
          </el-menu-item>

          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-date"></i>
              <span slot="title">考勤记录</span>
            </template>
            <el-menu-item index="/RecordOne">员工考勤记录</el-menu-item>
            <el-menu-item index="/RecordTwo">经理考勤记录</el-menu-item>
          </el-submenu>

          <el-submenu index="5">
            <template slot="title">
              <i class="el-icon-coin"></i>
              <span slot="title">工薪评定</span>
            </template>
            <el-menu-item index="/EvaluateOne">员工工薪评定</el-menu-item>
            <el-menu-item index="/EvaluateTwo">经理工薪评定</el-menu-item>
          </el-submenu>

          <el-submenu index="6">
            <template slot="title">
              <i class="el-icon-sort"></i>
              <span slot="title">职位变动</span>
            </template>
            <el-menu-item index="/ChangeOne">晋升员工</el-menu-item>
            <el-menu-item index="/ChangeTwo">降级经理</el-menu-item>
          </el-submenu>

          <el-menu-item index="/DocumentShare">
            <i class="el-icon-folder-opened"></i>
            <span slot="title">文件共享</span>
          </el-menu-item>

          <el-menu-item index="/Mail">
            <i class="el-icon-message"></i>
            <span slot="title">公司信箱</span>
          </el-menu-item>

        </el-menu>
      </el-aside>

      <el-container>
        <el-header >
          <el-container>
            <div class="left"><i class="el-icon-s-tools"></i>管理员客户端</div>
            <div class="exit-button">
              <el-button type="danger" @click="exit" class="exitButton">退出登录</el-button>
            </div>
          </el-container>

        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    const item = {
    };
    return {
      tableData: Array(20).fill(item),
      isCollapse: true
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    ChangeCollapse() {
      this.isCollapse = !this.isCollapse
    },
    exit(){
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
  }

};
</script>

<style scoped>

.el-header {
  background-color: rgb(238, 241, 246);
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 680px;
}
.el-menu-vertical-demo{
  width: 64px;
  min-height: 680px;
}
.button {
  font-size:20px;
  line-height: 10px;
  margin-left: 20px;
  cursor: pointer;
}

.left {
  font-size: 23px;
  color: #409eff;
  font-weight: bold;
  width: 1200px;
  height: 60px;
  line-height: 58px;
}

.exit-button{
  /*display: flex;*/
  /*margin-left: 950px;*/
}

</style>