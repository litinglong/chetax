import SysScheduleInfoForm from '@/components/system/SysScheduleInfoForm.vue'
import SysScheduleResultList from '@/components/system/SysScheduleResultList.vue'

export default {
  name: 'SysScheduleInfoList',
  components: {
    // 组件局部注册
    SysScheduleInfoForm,
    SysScheduleResultList
  },
  data () {
    return {
      checkIsSearching: false,
      // updateCronExpressionDialogVisible: false,
      taskExecuteResultDialogVisible: false,
      currentRow: {
        id: '',
        jobName: '',
        groupName: '',
        description: '',
        cron: '',
        url: '',
        requestBody: '',
        status: '',
        concurrentTag: ''
      },
      // currentForm: {
      //   id: '',
      //   cron: ''
      // },
      tableData: [],
      page: {
        currentPage: 1,
        pageSize: 20,
        pageSizes: [20, 50, 100, 500],
        total: 0
      },
      form: {
        jobName: null,
        groupName: null,
        description: null,
        status: '',
        concurrentTag: ''
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
    prepareInsert () {
      this.$refs['sysScheduleInfoForm'].prepareInsert()
    },
    prepareUpdate (id) {
      this.$refs['sysScheduleInfoForm'].prepareUpdate(id)
    },
    prepareView (id) {
      this.$refs['sysScheduleInfoForm'].prepareView(id)
    },
    prepareDelete (id) {
      var _this = this
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var url = `${this.$scheduleApis}/system/scheduleInfoController/deleteScheduleInfoById/${id}`
        this.$axios.get(url).then((response) => {
          _this.$message({
            type: 'success',
            message: '删除成功!'
          })
          _this.refrashTable()
        }).catch((error) => {
          console.log(error)
          _this.refrashTable()
        })
      }).catch(() => {
      })
    },
    prepareView (id) {
      this.$refs['sysScheduleInfoForm'].prepareView(id)
    },
    doExecuteTask (id) {
      var _this = this
      var url = `${this.$scheduleApis}/system/scheduleInfoController/executeTask/${id}`
      this.$axios.get(url).then((response) => {
        _this.refrashTable()
      }).catch((error) => {
        console.log(error)
      })
    },
    // prepareUpdateCronExpression (row) {
    //   this.currentForm.id = row.id
    //   this.currentForm.cron = row.cron
    //   this.updateCronExpressionDialogVisible = true
    // },
    doChangeStatusOfSysScheduleInfo (id) {
      var _this = this
      var url = `${this.$scheduleApis}/system/scheduleInfoController/changeStatusOfSysScheduleInfo/${id}`
      this.$axios.get(url).then((response) => {
        _this.refrashTable()
      }).catch((error) => {
        console.log(error)
      })
    },
    prepareQueryTaskExecuteResult (row) {
      this.taskExecuteResultDialogVisible = true
      this.currentRow = row
    },
    // handleSubmit () {
    //   var _this = this
    //   this.updateCronExpressionDialogVisible = false
    //   var url = `/schedule-apis/sys/scheduleInfoController/updateScheduleInfoCron/${this.currentForm.id}?cron=${this.currentForm.cron}`
    //   // , { cron: _this.currentForm.cron }
    //   this.$axios.post(url).then((response) => {
    //     _this.refrashTable()
    //   }).catch((error) => {
    //     console.log(error)
    //   })
    // },
    onSaveCompleted () {
      this.refrashTable()
    },
    refrashTable () {
      var _this = this
      var url = `${this.$scheduleApis}/system/scheduleInfoController/findSysScheduleInfoPage/${this.page.currentPage}/${this.page.pageSize}`
      _this.checkIsSearching = true
      this.$axios.post(url, _this.form).then((response) => {
        _this.tableData = response.data.list
        _this.page.total = response.data.total
        _this.checkIsSearching = false
      }).catch((error) => {
        console.log(error)
        _this.checkIsSearching = false
      })
    },
    resetSearchCondition () {
      this.form = {
        jobName: null,
        groupName: null,
        description: null,
        status: '',
        concurrentTag: ''
      }
    },
    tableRowClassName ({row, rowIndex}) {
      if (row.status === 1) {
        return 'success-row'
      }
      return ''
    },
    onTaskExecuteResultDialogClose () {
      this.$refs.sysScheduleResultList.clearData()
    }
  }
}
