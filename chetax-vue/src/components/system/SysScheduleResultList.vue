<template>
  <div class="block">
    <el-table
      :data="tableData"
      style="width: 100%"
      stripe
      border
      max-height="450">
      <el-table-column type="index" :index="1" fixed="left"></el-table-column>
      <el-table-column prop="id" label="主键" width="80" v-if="false"></el-table-column>
      <el-table-column prop="url" label="URL" width="560">
        <template slot-scope="scope">
          <el-input
            v-model="scope.row.url"
            :disabled="true">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="usedTimeFormated" label="任务耗时" width="180"></el-table-column>
      <el-table-column prop="startTime" label="开始时间" width="180"></el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="180"></el-table-column>
      <el-table-column fixed="right" label="操作" width="320">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row.requestBody, '输入参数')" size="small" round :type="scope.row.requestBody === null || scope.row.requestBody === '' ? '' : 'success'">输入参数</el-button>
          <el-button @click="handleClick(scope.row.exceptionMsg, '异常信息')" size="small" round :type="scope.row.exceptionMsg === null || scope.row.exceptionMsg === '' ? '' : 'warning'">异常信息</el-button>
          <el-button @click="handleClick(scope.row.resultMsg, '结果信息')" size="small" round :type="scope.row.resultMsg === null || scope.row.resultMsg === '' ? '' : 'success'">结果信息</el-button>
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
      width="80%"
      :title="textareaTitle"
      :visible.sync="innerVisible"
      append-to-body>
      <el-input
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 16}"
        v-model="textareaValue">
      </el-input>
      <el-row>
        <el-col :span="24" style="text-align: right; font-size: 12px">
          <el-button @click="innerVisible = false">取消</el-button>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'SysScheduleResultList',
  props: {
    curSysScheduleInfoId: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
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
  updated () {
    this.refrashTable()
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
    refrashTable () {
      var _this = this
      var url = `/schedule-apis/system/sysScheduleResultController/findSysScheduleResultPage/${this.page.currentPage}/${this.page.pageSize}?id=${this.curSysScheduleInfoId}`
      this.$axios.post(url).then((response) => {
        _this.tableData = response.data.list
        _this.page.total = response.data.total
      }).catch((error) => {
        console.log(error)
      })
    },
    clearData () {
      this.tableData = []
      this.page.total = 0
    }
  }
}
</script>
