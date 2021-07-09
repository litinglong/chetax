import Axios from 'axios'
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
      searchingTag: false,
      dialogFormVisible: false,
      dialogFormVisible2: false,
      dialogFormVisible3: false,
      dialogFormVisible4: false,
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
      currentForm: {
        id: '',
        cron: ''
      },
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
    handleClick (row, operation) {
      var _this = this
      if (operation === 'view') {
        this.currentRow = row
        this.dialogFormVisible = true
      } else if (operation === 'delete') {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var url = `/schedule-apis/sys/scheduleInfoController/deleteScheduleInfoById/${row.id}`
          Axios.get(url).then((response) => {
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
      } else if (operation === 'execute') {
        var url = `/schedule-apis/sys/scheduleInfoController/executeJob/${row.id}`
        Axios.get(url).then((response) => {
          _this.refrashTable()
        }).catch((error) => {
          console.log(error)
        })
      } else if (operation === 'changeCron') {
        this.currentForm.id = row.id
        this.currentForm.cron = row.cron
        this.dialogFormVisible2 = true
      } else if (operation === 'changeStatus') {
        var url2 = `/schedule-apis/sys/scheduleInfoController/changeStatus/${row.id}`
        Axios.get(url2).then((response) => {
          _this.refrashTable()
        }).catch((error) => {
          console.log(error)
        })
      } else if (operation === 'result') {
        this.dialogFormVisible4 = true
        this.$refs.sysScheduleResultList.setsCurSysScheduleInfoId(row.id)
        this.$refs.sysScheduleResultList.refrashTable()
      }
    },
    handleSubmit () {
      this.dialogFormVisible2 = false
      var _this = this
      var url = `/schedule-apis/sys/scheduleInfoController/updateScheduleInfoCron/${this.currentForm.id}?cron=${this.currentForm.cron}`
      // , { cron: _this.currentForm.cron }
      Axios.post(url).then((response) => {
        _this.refrashTable()
      }).catch((error) => {
        console.log(error)
      })
    },
    handleClickTest () {
      this.$refs.sysScheduleInfoForm.save()
    },
    onSaveCompleted () {
      this.dialogFormVisible3 = false
      this.refrashTable()
    },
    refrashTable () {
      var _this = this
      var url = `/schedule-apis/sys/scheduleInfoController/findSysScheduleInfoPage/${this.page.currentPage}/${this.page.pageSize}`
      _this.searchingTag = true
      Axios.post(url, _this.form).then((response) => {
        _this.tableData = response.data.list
        _this.page.total = response.data.total
        _this.searchingTag = false
      }).catch((error) => {
        console.log(error)
        _this.searchingTag = false
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
    }
  }
}
