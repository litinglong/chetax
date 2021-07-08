<template>
  <div class="block">
    <el-form ref="form" :model="form" :inline="true">
      <el-form-item label="名称">
        <el-input v-model="form.jobName"></el-input>
      </el-form-item>
      <el-form-item label="分组">
        <el-input v-model="form.groupName"></el-input>
      </el-form-item>
      <el-form-item label="描述">
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
      <el-form-item>
        <el-button type="primary" @click="refrashTable" :loading="searchingTag">搜索</el-button>
        <el-button @click="resetSearchCondition">重置</el-button>
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
      <el-table-column prop="cron" label="CRON表达式" width="160"></el-table-column>
      <el-table-column prop="url" label="链接" width="360">
        <template slot-scope="scope">
          <el-input
            v-model="scope.row.url"
            :disabled="true">
          </el-input>
        </template>
      </el-table-column>
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
      <el-form ref="form" :model="currentRow" :inline="true" disabled>
        <el-form-item label="主键" v-if="false">
          <el-input v-model="currentRow.id"></el-input>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="currentRow.jobName"></el-input>
        </el-form-item>
        <el-form-item label="分组">
          <el-input v-model="currentRow.groupName"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="currentRow.description"></el-input>
        </el-form-item>
        <el-form-item label="CRON表达式">
          <el-input v-model="currentRow.cron"></el-input>
        </el-form-item>
        <el-form-item label="是否并发">
          <el-select v-model="currentRow.concurrentTag" placeholder="请选择是否并发">
            <el-option label="非并发" value="0"></el-option>
            <el-option label="并发" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="运行状态">
          <el-select v-model="currentRow.status" placeholder="请选择运行状态">
            <el-option label="未运行" value="0"></el-option>
            <el-option label="运行中" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="任务链接">
          <el-input v-model="currentRow.url"></el-input>
        </el-form-item>
        <el-form-item label="请求参数">
          <el-input v-model="currentRow.requestBody"></el-input>
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
<script src="system/SysScheduleInfoList.js"></script>
<style src="system/SysScheduleInfoList.css"></style>
