<template>
  <div class="block">
    <el-table
      :data="tableData"
      style="width: 100%"
      stripe
      border
      max-height="450">
      <el-table-column prop="id" label="主键" width="80"></el-table-column>
      <el-table-column prop="url" label="URL" width="560">
        <template slot-scope="scope">
          <el-input
            v-model="scope.row.url"
            :disabled="true">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="startTime" label="开始时间" width="180"></el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="180"></el-table-column>
      <el-table-column fixed="right" label="操作" width="320">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row.requestBody, '输入参数')" size="small">输入参数</el-button>
          <el-button @click="handleClick(scope.row.exceptionMsg, '异常信息')" size="small">异常信息</el-button>
          <el-button @click="handleClick(scope.row.resultMsg, '结果信息')" size="small">结果信息</el-button>
        </template>
      </el-table-column>
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
    <el-dialog
      width="30%"
      :title="textareaTitle"
      :visible.sync="innerVisible"
      append-to-body>
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        :autosize="{ minRows: 2, maxRows: 12}"
        v-model="textareaValue">
      </el-input>
    </el-dialog>
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
      },
      innerVisible: false,
      textareaValue: '',
      textareaTitle: ''
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
    handleClick (text, title) {
      this.textareaValue = text
      this.textareaTitle = title
      this.innerVisible = true
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
