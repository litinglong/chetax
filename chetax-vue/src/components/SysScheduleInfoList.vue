<template>
  <div class="block">
<el-form ref="form" :model="form" :inline="true" class="demo-form-inline">
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
    <el-button type="primary" @click="onSubmit">搜索</el-button>
    <el-button>重置</el-button>
    <el-button type="primary" icon="el-icon-edit">新增</el-button>
  </el-form-item>
</el-form>
  <el-table
    :data="tableData"
    style="width: 100%"
    stripe
    border
    max-height="450">
    <el-table-column prop="jobName" label="名称" width="160"></el-table-column>
    <el-table-column prop="groupName" label="分组" width="160"></el-table-column>
    <el-table-column prop="description" label="摘要" width="160"></el-table-column>
    <el-table-column prop="concurrentTag" label="是否并发" width="120"></el-table-column>
    <el-table-column prop="status" label="状态" width="120"></el-table-column>
    <el-table-column prop="cron" label="CRON表达式" width="160"></el-table-column>
    <el-table-column prop="url" label="链接" width="240"></el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="280">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        <el-button type="text" size="small">删除</el-button>
        <el-button type="text" size="small">执行</el-button>
        <el-button type="text" size="small">CRON变更</el-button>
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
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  name: 'SysScheduleInfoList',
  data () {
    return {
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
      this.onSubmit()
    },
    handleCurrentChange (val) {
      this.page.currentPage = val
      this.onSubmit()
    },
    onSubmit () {
      var _this = this
      var url = `/schedule-apis/sys/scheduleInfoController/findPage/${this.page.currentPage}/${this.page.pageSize}`
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
