<template>
  <div class="myChartCSS">
    <el-row :gutter="24">
      <el-col :span="24">
        <el-card class = "myTitle">
          系统数据分析
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="12">
        <el-row>
          <el-card>
            <div id="myChart1" :style="{width: '500px', height: '400px'}"></div>
          </el-card>
        </el-row>
        <el-row>
          <el-card>
            <div id="myChart3" :style="{width: '500px', height: '400px'}"></div>
          </el-card>
        </el-row>
      </el-col>

      <el-col :span="12">
        <el-row>
          <el-card>
            <div id="myChart2" :style="{width: '500px', height: '300px'}"></div>
          </el-card>
        </el-row>
        <el-row>
          <el-card>
            <div id="myChart4" :style="{width: '500px', height: '400px'}"></div>
          </el-card>
        </el-row>
      </el-col>
    </el-row>


    <div>

    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: 'dataAnalysis',

  data() {
    return {

    }
  },

  mounted() {         //相当于created
    this.drawChart1();
    this.drawChart2();
    this.drawChart3();
    this.drawChart4();
  },

  methods: {
    drawChart1() {
      // 基于刚刚准备好的 DOM 容器，初始化 EChart 实例

      var adminCount;
      var managerCount;
      var staffCount;
      axios.get("http://localhost:9091/getUserCount")
          .then(resp=>{
            adminCount = resp.data.adminCount;
            managerCount = resp.data.managerCount;
            staffCount = resp.data.staffCount;

            let myChart = this.$echarts.init(document.getElementById('myChart1'))

            var option = {
              title: { text: '系统用户分布情况' },
              legend: {
                top: 'bottom'
              },
              toolbox: {
                show: true,
                feature: {
                  mark: { show: true },
                  // dataView: { show: true, readOnly: false },
                  // restore: { show: true },
                  // saveAsImage: { show: true }
                }
              },
              series: [
                {
                  name: 'Nightingale Chart',
                  type: 'pie',
                  radius: [20, 100],
                  center: ['50%', '50%'],
                  roseType: 'area',
                  itemStyle: {
                    borderRadius: 8
                  },
                  data: [
                    { value: adminCount, name: '管理员用户' },
                    { value: managerCount, name: '经理用户' },
                    { value: staffCount, name: '员工用户' },
                  ]
                }
              ]
            };

            myChart.setOption(option);
          })
    },

    drawChart2() {
      axios.get("http://localhost:9091/getStaffSalaryCount")
          .then(resp=>{
            let myChart = this.$echarts.init(document.getElementById('myChart2')),
                option = {
                  title: { text: '员工基本工资情况分布（元）' },
                  xAxis: {
                    type: 'category',
                    data: ['<6000', '6000 - 7000', '7000 - 8000', ' 8000 - 9000', '> 9000']
                  },
                  yAxis: {
                    type: 'value'
                  },
                  series: [
                    {
                      data: [resp.data.int1, resp.data.int2, resp.data.int3, resp.data.int4, resp.data.int5],
                      type: 'bar'
                    }
                  ]
                };
            myChart.setOption(option);
          })
    },

    drawChart3() {
      axios.get("http://localhost:9091/getStaffManagerCount")
          .then(resp=>{
            // console.log(resp.data.int1)
            let myChart = this.$echarts.init(document.getElementById('myChart3')),
                option = {
                  title: { text: '经理基本工资情况分布（元）' },
                  xAxis: {
                    type: 'category',
                    data: ['< 9000', '9000 - 10000', '10000 - 11000', ' 11000- 12000', '> 12000']
                  },
                  yAxis: {
                    type: 'value'
                  },
                  series: [
                    {
                      data: [resp.data.int1, resp.data.int2, resp.data.int3, resp.data.int4, resp.data.int5],
                      type: 'bar'
                    }
                  ]
                };

            myChart.setOption(option);
          })
    },

    drawChart4() {
      axios.get("http://localhost:9091/getStaffCountGroupByDepartment")
          .then(resp=>{
            let myChart = this.$echarts.init(document.getElementById('myChart4')),
                option = {
                  title: {
                    text: '部门员工数量统计',
                    left: 'center'
                  },
                  tooltip: {
                    trigger: 'item'
                  },
                  legend: {
                    orient: 'vertical',
                    left: 'left'
                  },
                  series: [
                    {
                      name: 'Access From',
                      type: 'pie',
                      radius: '50%',
                      data: [
                        { value: resp.data.int1, name: '0 人' },
                        { value: resp.data.int2, name: '1 人' },
                        { value: resp.data.int3, name: '2 人' },
                        { value: resp.data.int4, name: '3 人' },
                        { value: resp.data.int5, name: '> 3 人' }
                      ],
                      emphasis: {
                        itemStyle: {
                          shadowBlur: 10,
                          shadowOffsetX: 0,
                          shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                      }
                    }
                  ]
                };

            myChart.setOption(option);
          })
    },
  }
}

</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 20px;
}
.myChartCSS {
  width: 100%;
  margin: 0 auto;
}

.myTitle {
  font-weight: bolder;
  font-size: larger;
}

</style>