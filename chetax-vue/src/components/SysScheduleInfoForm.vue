<template>
  <div class="block">
    <el-form ref="form" :model="form" :inline="true">
      <el-form-item label="主键" v-if="false">
        <el-input v-model="form.id"></el-input>
      </el-form-item>
      <el-form-item label="名称">
        <el-input v-model="form.jobName"></el-input>
      </el-form-item>
      <el-form-item label="分组">
        <el-input v-model="form.groupName"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="form.description"></el-input>
      </el-form-item>
      <el-form-item label="CRON表达式">
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
      <el-form-item label="任务链接">
        <el-input v-model="form.url"></el-input>
      </el-form-item>
      <el-form-item label="请求参数">
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
      }
    }
  },
  methods: {
    save () {
      var url = `/schedule-apis/sys/scheduleInfoController/insertSysScheduleInfoEntity`
      var _this = this
      Axios.post(url, _this.form).then((response) => {
        _this.$emit('onSaveCompleted')
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
