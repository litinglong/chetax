<template>
  <el-dialog :title="recordFormDialogTitle" :visible.sync="recordFormDialogVisible" @close="onClose">
    <div class="block">
      <el-form ref="form" :model="form" label-position="right" label-width="120px" :rules="curOperation ==='view' ? {} : rules" :disabled="curOperation ==='view' ? true : false">
        <el-form-item label="主键" v-if="false">
          <el-input v-model="form.id"></el-input>
        </el-form-item>
        <el-form-item label="调度名称" prop="jobName">
          <el-input v-model="form.jobName" :disabled="curOperation === 'view' || curOperation ==='update' ? true : false"></el-input>
        </el-form-item>
        <el-form-item label="调度分组" prop="groupName">
          <el-input v-model="form.groupName" :disabled="curOperation === 'view' || curOperation ==='update' ? true : false"></el-input>
        </el-form-item>
        <el-form-item label="调度描述" prop="description">
          <el-input v-model="form.description" :disabled="curOperation === 'view'? true : false"></el-input>
        </el-form-item>
        <el-form-item label="CRON表达式" prop="cron">
          <el-input v-model="form.cron" :disabled="curOperation === 'view' ? true : false">
            <el-button slot="append" icon="el-icon-search" @click="openCornEditDialog()" :disabled="curOperation === 'view'? true : false"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="是否并发">
          <el-select v-model="form.concurrentTag" placeholder="请选择是否并发" :disabled="curOperation === 'view' || curOperation === 'update' ? true : false">
            <el-option label="非并发" value="0"></el-option>
            <el-option label="并发" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="运行状态">
          <el-select v-model="form.status" placeholder="请选择运行状态" :disabled="curOperation === 'view' || curOperation === 'update' ? true : false">
            <el-option label="未运行" value="0"></el-option>
            <el-option label="运行中" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="任务链接" prop="url">
          <el-input v-model="form.url" :disabled="curOperation === 'view'? true : false"></el-input>
        </el-form-item>
        <el-form-item label="任务参数" prop="requestBody">
          <el-row>
            <el-col :span="24" style="text-align: right; font-size: 12px">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 8}"
                v-model="form.requestBody" :disabled="curOperation === 'view'? true : false">
              </el-input>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="handleConfirm()" v-if="curOperation === 'insert' || curOperation === 'update' ? true : false">确 定</el-button>
      <el-button @click="closeRecordFormDialog()">取 消</el-button>
    </div>
    <CronUi ref="CronUi" @cronResult="onReturnedCronResult"></CronUi>
  </el-dialog>
</template>

<script>
import CronUi from '@/components/common/cronRoot/cron/cron-ui/index.vue'

export default {
  name: 'SysScheduleInfoForm',
  components: {
    CronUi
  },
  props: {
    baseTitle: {
      type: String,
      default: 'baseTitle'
    }
  },
  data () {
    return {
      curOperation: 'view',
      recordFormDialogTitle: '调度计划',
      recordFormDialogVisible: false,
      savingingTag: false,
      checkIsFormValid: false,
      form: {
        id: '',
        jobName: '',
        groupName: '',
        description: '',
        cron: '',
        url: '',
        requestBody: '',
        status: 1,
        concurrentTag: 0
      },
      defaultForm: {
        id: '',
        jobName: '',
        groupName: '',
        description: '',
        cron: '',
        url: '',
        requestBody: '',
        status: 1,
        concurrentTag: 0
      },
      rules: {
        jobName: [
          { required: true, message: '请输入调度名称', trigger: 'blur' },
          { min: 4, max: 128, message: '长度在 4 到 128 个字符', trigger: 'blur' }
        ],
        groupName: [
          { required: true, message: '请输入调度分组', trigger: 'blur' },
          { min: 4, max: 64, message: '长度在 4 到 64 个字符', trigger: 'blur' }
        ],
        description: [
          { message: '请输入调度描述', trigger: 'blur' },
          { min: 0, max: 256, message: '长度在 0 到 256 个字符', trigger: 'blur' }
        ],
        cron: [
          { required: true, message: '请输入CRON表达式', trigger: 'blur' },
          { min: 10, max: 32, message: '长度在 10 到 32 个字符', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请输入任务链接', trigger: 'blur' },
          { min: 10, max: 256, message: '长度在 10 到 256 个字符', trigger: 'blur' }
        ],
        requestBody: [
          { message: '请输入任务参数', trigger: 'blur' },
          { min: 0, max: 512, message: '长度在 0 到 512 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    openRecordFormDialog (title, operation) {
      this.recordFormDialogTitle = `${this.baseTitle}${title}`
      this.recordFormDialogVisible = true
      this.curOperation = operation
    },
    onClose () {
      this.form = this.defaultForm
      this.$refs['form'].clearValidate()
    },
    closeRecordFormDialog () {
      this.recordFormDialogVisible = false
    },
    prepareInsert () {
      this.openRecordFormDialog('【新增】', 'insert')
    },
    prepareUpdate (id) {
      this.openRecordFormDialog('【修改】', 'update')
      var _this = this
      var url = `${this.$scheduleApis}/system/scheduleInfoController/getScheduleInfoById/${id}`
      this.$axios.get(url).then((response) => {
        _this.form = response.data
      }).catch((error) => {
        console.log(error)
      })
    },
    prepareView (id) {
      this.openRecordFormDialog('【查看】', 'view')
      var _this = this
      var url = `${this.$scheduleApis}/system/scheduleInfoController/getScheduleInfoById/${id}`
      this.$axios.get(url).then((response) => {
        _this.form = response.data
      }).catch((error) => {
        console.log(error)
      })
    },
    handleConfirm () {
      var _this = this
      this.$refs['form'].validate((valid) => {
        _this.checkIsFormValid = valid
        return valid
      })
      if (!_this.checkIsFormValid) {
        return
      }
      if (this.curOperation === 'insert') {
        this.handleConfirmInsert()
      }
      if (this.curOperation === 'update') {
        this.handleConfirmUpdate()
      }
    },
    handleConfirmInsert () {
      var _this = this
      var url = `${this.$scheduleApis}/system/scheduleInfoController/insertSysScheduleInfoEntity`
      this.$axios.post(url, _this.form).then((response) => {
        console.log(response)
        _this.closeRecordFormDialog()
        _this.$emit('onSaveCompleted')
      }).catch((error) => {
        console.log(error)
      })
    },
    handleConfirmUpdate () {
      var _this = this
      var url = `${this.$scheduleApis}/system/scheduleInfoController/updateSysScheduleInfoEntity`
      this.$axios.post(url, _this.form).then((response) => {
        console.log(response)
        _this.closeRecordFormDialog()
        _this.$emit('onSaveCompleted')
      }).catch((error) => {
        console.log(error)
      })
    },
    openCornEditDialog () {
      this.$refs['CronUi'].dialogVisible = true
    },
    onReturnedCronResult (cron) {
      this.form.cron = cron
    }
  }
}
</script>
