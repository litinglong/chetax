<template>
  <div class="block">
    <el-form ref="form" :model="form" :inline="true">
      <el-form-item label="名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="分组">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="是否并发">
        <el-select v-model="form.concurrentTag" placeholder="请选择是否并发">
          <el-option label="全部" value=""></el-option>
          <el-option label="非并发" value="0"></el-option>
          <el-option label="并发" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="运行状态">
        <el-select v-model="form.status" placeholder="请选择运行状态">
          <el-option label="全部" value=""></el-option>
          <el-option label="未运行" value="0"></el-option>
          <el-option label="运行中" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="refrashTable">搜索</el-button>
        <el-button>重置</el-button>
        <el-button type="primary" icon="el-icon-edit" @click="dialogFormVisible3 = true">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      style="width: 100%"
      stripe
      border
      :row-class-name="tableRowClassName"
      max-height="450">
      <el-table-column prop="id" label="主键" width="160"></el-table-column>
      <el-table-column prop="jobName" label="名称" width="160"></el-table-column>
      <el-table-column prop="groupName" label="分组" width="160"></el-table-column>
      <el-table-column prop="description" label="摘要" width="160"></el-table-column>
      <el-table-column prop="concurrentTag" label="是否并发" width="120">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.concurrentTag === 1 ? '是' : '否' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="120">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.status === 1 ? '运行中' : '已停止' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="cron" label="CRON表达式" width="160"></el-table-column>
      <el-table-column prop="url" label="链接" width="240"></el-table-column>
      <el-table-column fixed="right" label="操作" width="480">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row,'view')" type="primary" size="small">查看</el-button>
          <el-button @click="handleClick(scope.row,'execute')" size="small">执行</el-button>
          <el-button @click="handleClick(scope.row,'changeCron')" size="small">CRON变更</el-button>
          <el-button @click="handleClick(scope.row,'changeStatus')" size="small" :type="scope.row.status === 1 ? 'warning' : 'success'">{{ scope.row.status === 1 ? '停止' : '开始' }}</el-button>
          <el-button @click="handleClick(scope.row,'delete')" type="danger" size="small">删除</el-button>
          <el-button @click="handleClick(scope.row,'result')" type="success" size="small">结果</el-button>
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
    <el-dialog title="查看调度详情" :visible.sync="dialogFormVisible">
      <el-form :model="currentRow">
        <el-form-item label="名称：" :label-width="40">
          <span style="margin-left: 10px">{{ currentRow.jobName }}</span>
        </el-form-item>
        <el-form-item label="分组：" :label-width="40">
          <span style="margin-left: 10px">{{ currentRow.groupName }}</span>
        </el-form-item>
        <el-form-item label="摘要：" :label-width="40">
          <span style="margin-left: 10px">{{ currentRow.description }}</span>
        </el-form-item>
        <el-form-item label="CRON表达式：" :label-width="40">
          <span style="margin-left: 10px">{{ currentRow.cron }}</span>
        </el-form-item>
        <el-form-item label="链接：" :label-width="40">
          <span style="margin-left: 10px">{{ currentRow.url }}</span>
        </el-form-item>
        <el-form-item label="输入参数：" :label-width="40">
          <span style="margin-left: 10px">{{ currentRow.requestBody }}</span>
        </el-form-item>
        <el-form-item label="是否并发：" :label-width="40">
          <span style="margin-left: 10px">{{ currentRow.concurrentTag === 1 ? '是' : '否' }}</span>
        </el-form-item>
        <el-form-item label="状态：" :label-width="40">
          <span style="margin-left: 10px">{{ currentRow.status === 1 ? '运行中' : '已停止' }}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改cron调度详情" :visible.sync="dialogFormVisible2">
      <el-form :model="currentForm">
        <el-form-item label="CRON表达式：" :label-width="40">
          <el-input v-model="currentForm.cron"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="handleSubmit()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="新增调度计划" :visible.sync="dialogFormVisible3">
      <SysScheduleInfoForm ref="sysScheduleInfoForm" @onSaveCompleted="onSaveCompleted()">
      </SysScheduleInfoForm>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible3 = false">取 消</el-button>
        <el-button type="primary" @click="handleClickTest()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="调度结果" :visible.sync="dialogFormVisible4" width="80%">
      <SysScheduleResultList ref="sysScheduleResultList">
      </SysScheduleResultList>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible4 = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Axios from 'axios'
import SysScheduleInfoForm from '@/components/SysScheduleInfoForm'
import SysScheduleResultList from '@/components/SysScheduleResultList'
export default {
  name: 'SysScheduleInfoList',
  components: {
    // 组件局部注册
    SysScheduleInfoForm,
    SysScheduleResultList
  },
  data () {
    return {
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
        jobName: '',
        groupName: '',
        description: '',
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
      this.dialogFormVisible3 = false
      this.$refs.sysScheduleInfoForm.save()
    },
    onSaveCompleted () {
      this.refrashTable()
    },
    refrashTable () {
      var _this = this
      var url = `/schedule-apis/sys/scheduleInfoController/findPage/${this.page.currentPage}/${this.page.pageSize}`
      Axios.post(url).then((response) => {
        _this.tableData = response.data.list
        _this.page.total = response.data.total
      }).catch((error) => {
        console.log(error)
      })
    },
    tableRowClassName ({row, rowIndex}) {
      if (row.status === 1) {
        return 'success-row'
      }
      return ''
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
