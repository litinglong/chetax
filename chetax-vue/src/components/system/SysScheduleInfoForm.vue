<template>
  <div class="block">
    <el-form ref="form" :model="form" :inline="true"  :rules="rules">
      <el-form-item label="主键" v-if="false">
        <el-input v-model="form.id"></el-input>
      </el-form-item>
      <el-form-item label="任务名称" prop="jobName">
        <el-input v-model="form.jobName"></el-input>
      </el-form-item>
      <el-form-item label="任务分组" prop="groupName">
        <el-input v-model="form.groupName"></el-input>
      </el-form-item>
      <el-form-item label="任务描述" prop="description">
        <el-input v-model="form.description"></el-input>
      </el-form-item>
      <el-form-item label="CRON表达式" prop="cron">
        <el-input v-model="form.cron"></el-input>
      </el-form-item>
      <el-form-item label="是否并发">
        <el-select v-model="form.concurrentTag" placeholder="请选择是否并发">
          <el-option label="非并发" value="0"></el-option>
          <el-option label="并发" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="运行状态">
        <el-select v-model="form.status" placeholder="请选择运行状态">
          <el-option label="未运行" value="0"></el-option>
          <el-option label="运行中" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="任务链接" prop="url">
        <el-input v-model="form.url"></el-input>
      </el-form-item>
      <el-form-item label="任务参数" prop="requestBody">
        <el-input v-model="form.requestBody"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  name: 'SysScheduleInfoForm',
  data () {
    return {
      savingingTag: false,
      operation: 'view',
      form: {
        id: '',
        jobName: '',
        groupName: '',
        description: '',
        cron: '',
        url: '',
        requestBody: '',
        status: '1',
        concurrentTag: '0'
      },
      rules: {
        jobName: [
          { required: true, message: '请输入任务名称', trigger: 'blur' },
          { min: 4, max: 128, message: '长度在 4 到 128 个字符', trigger: 'blur' }
        ],
        groupName: [
          { required: true, message: '请输入任务分组', trigger: 'blur' },
          { min: 4, max: 64, message: '长度在 4 到 64 个字符', trigger: 'blur' }
        ],
        description: [
          { message: '请输入任务描述', trigger: 'blur' },
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
    save () {
      var _this = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          var url = `/schedule-apis/sys/scheduleInfoController/insertSysScheduleInfoEntity`
          Axios.post(url, _this.form).then((response) => {
            debugger
            _this.$emit('onSaveCompleted')
          }).catch((error) => {
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
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
