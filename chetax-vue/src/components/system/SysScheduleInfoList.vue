<template>
  <div class="block">
    <el-collapse accordion>
      <el-collapse-item name="1">
        <template slot="title">
          查询条件<i class="header-icon el-icon-info"></i>
        </template>
        <el-form ref="form" :model="form" :inline="true">
          <el-form-item label="调度名称">
            <el-input v-model="form.jobName"></el-input>
          </el-form-item>
          <el-form-item label="调度分组">
            <el-input v-model="form.groupName"></el-input>
          </el-form-item>
          <el-form-item label="调度摘要">
            <el-input v-model="form.description"></el-input>
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
        </el-form>
      </el-collapse-item>
    </el-collapse>
    <br />
    <el-row>
      <el-col :span="12">
        <el-tooltip content="新增" placement="top">
            <el-button type="primary" icon="el-icon-plus" @click="prepareInsert()"></el-button>
        </el-tooltip>
      </el-col>
      <el-col :span="12" style="text-align: right; font-size: 12px">
        <el-button @click="resetSearchCondition">重置</el-button>
        <el-button type="primary" @click="refrashTable" :loading="checkIsSearching">搜索</el-button>
      </el-col>
    </el-row>
    <el-divider></el-divider>
    <el-table
      :data="tableData"
      style="width: 100%"
      border
      :row-class-name="tableRowClassName"
      max-height="450">
      <el-table-column type="index" :index="1" fixed="left"></el-table-column>
      <el-table-column prop="id" label="主键" width="160" v-if="false"></el-table-column>
      <el-table-column prop="jobName" label="调度名称" width="160" fixed="left">
        <template slot-scope="scope">
          <el-input
            v-model="scope.row.jobName"
            :disabled="true">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="groupName" label="调度分组" width="160">
        <template slot-scope="scope">
          <el-input
            v-model="scope.row.groupName"
            :disabled="true">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="调度摘要" width="160">
        <template slot-scope="scope">
          <el-input
            v-model="scope.row.description"
            :disabled="true">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="concurrentTag" label="是否并发" width="120">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.concurrentTag === 1 ? '是' : '否' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="cron" label="CRON表达式" width="160"></el-table-column>
      <el-table-column prop="url" label="链接" width="360">
        <template slot-scope="scope">
          <el-input
            v-model="scope.row.url"
            :disabled="true">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="380" fixed="right">
        <template slot-scope="scope">
          <el-tooltip content="查看" placement="top">
            <el-button @click="prepareView(scope.row.id)" type="primary" size="small" icon="el-icon-search"></el-button>
          </el-tooltip>
          <el-tooltip content="立即执行" placement="top">
            <el-button @click="doExecuteTask(scope.row.id)" size="small" icon="el-icon-video-play"></el-button>
          </el-tooltip>
          <el-tooltip :content="scope.row.status === 1 ? '运行中。点击停止运行' : '已停止。点击开始运行'" placement="top">
            <el-button @click="doChangeStatusOfSysScheduleInfo(scope.row.id)" size="small" :icon="scope.row.status === 1 ? 'el-icon-open' : 'el-icon-turn-off'" :type="scope.row.status === 1 ? 'success' : 'info'"></el-button>
          </el-tooltip>
          <el-tooltip content="删除" placement="top">
            <el-button @click="prepareDelete(scope.row.id)" type="danger" size="small" icon="el-icon-delete"></el-button>
          </el-tooltip>
          <!-- <el-tooltip content="修改CRON表达式" placement="top">
            <el-button @click="prepareUpdateCronExpression(scope.row)" size="small">CRON</el-button>
          </el-tooltip> -->
          <el-tooltip content="查看运行日志" placement="top">
            <el-button @click="prepareQueryTaskExecuteResult(scope.row)" type="success" size="small">日志</el-button>
          </el-tooltip>
          <el-tooltip content="修改" placement="top" v-if="scope.row.status === 1 ? false : true">
            <el-button @click="prepareUpdate(scope.row.id)" size="small"   icon="el-icon-edit"></el-button>
          </el-tooltip>
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
    <!-- <el-dialog title="修改cron" :visible.sync="updateCronExpressionDialogVisible">
      <el-form :model="currentForm">
        <el-form-item label="" :label-width="40">
          <el-input v-model="currentForm.cron"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSubmit()">确 定</el-button>
        <el-button @click="updateCronExpressionDialogVisible = false">取 消</el-button>
      </div>
    </el-dialog> -->
    <SysScheduleInfoForm ref="sysScheduleInfoForm" baseTitle="调度计划" @onSaveCompleted="onSaveCompleted()">
    </SysScheduleInfoForm>
    <el-dialog title="调度结果" :visible.sync="taskExecuteResultDialogVisible" width="80%" @close="onTaskExecuteResultDialogClose">
      <SysScheduleResultList ref="sysScheduleResultList" :curSysScheduleInfoId="currentRow.id">
      </SysScheduleResultList>
      <div slot="footer" class="dialog-footer">
        <el-button @click="taskExecuteResultDialogVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script src="@/components/system/SysScheduleInfoList.js">
</script>
<style src="@/components/system/SysScheduleInfoList.css">
</style>
