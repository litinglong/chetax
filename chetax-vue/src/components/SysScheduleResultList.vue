<template>
  <div class="block">
    <el-table
      :data="tableData"
      style="width: 100%"
      stripe
      border
      max-height="450">
      <el-table-column prop="id" label="主键" width="160"></el-table-column>
      <el-table-column prop="url" label="URL" width="160"></el-table-column>
      <el-table-column prop="requestBody" label="输入参数" width="160"></el-table-column>
      <el-table-column prop="exceptionMsg" label="异常信息" width="120"></el-table-column>
      <el-table-column prop="resultMsg" label="结果信息" width="120"></el-table-column>
      <el-table-column prop="startTime" label="开始时间" width="120"></el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="120"></el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page.currentPage"
      :page-sizes="page.pageSizes"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.total"
      :hide-on-single-page="this.page.pageSize === this.page.pageSizes[0]">
    </el-pagination>
  </div>
</template>

<script>
import Axios from 'axios'

export default {
  name: 'SysScheduleResultList',
  data () {
    return {
      curSysScheduleInfoId: '',
      tableData: [],
      page: {
        currentPage: 1,
        pageSize: 20,
        pageSizes: [20, 50, 100, 500],
        total: 0
      }
    }
  },
  methods: {
    handleSizeChange (val) {
      this.page.pageSize = val
      this.refrashTable()
    },
    handleCurrentChange (val) {
      this.page.currentPage = val
      this.refrashTable()
    },
    setsCurSysScheduleInfoId (val) {
      this.curSysScheduleInfoId = val
    },
    refrashTable () {
      var _this = this
      var url = `/schedule-apis/system/sysScheduleResultController/findSysScheduleResultPage/${this.page.currentPage}/${this.page.pageSize}?id=${this.curSysScheduleInfoId}`
      Axios.post(url).then((response) => {
        _this.tableData = response.data.list
        _this.page.total = response.data.total
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>
<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
