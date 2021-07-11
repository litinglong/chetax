<template>
  <div class="test">
    <h2>一次生成一个表达式</h2>
    <div>
      <el-button @click="open">打开第一种cron表达式选择</el-button>
      <!-- 2.注册并且使用 @cronResult="xx" 事件可以接收到最终的表达式-->
      <CronUi ref="CronUi" @cronResult="resultValue"></CronUi>
    </div>
    <p>最终生成的表达式为：{{ result }}</p>
    <h2>一次生成两个表达式</h2>
    <div>
      <el-button @click="open2">打开第二种cron表达式选择</el-button>
      <CronUiSecond ref="CronUiSecond" @cronResult="confirmCron"></CronUiSecond>
      <p>最终生成的开始时间表达式为：{{ resultForm.cpmEveryStartCron }}</p>
      <p>最终生成的结束时间表达式为：{{ resultForm.cpmEveryEndCron }}</p>
    </div>
  </div>
</template>
<script>
// 1.需要./cron/cron-ui/index这个文件
import CronUi from '@/components/common/cronRoot/cron/cron-ui/index.vue'
import CronUiSecond from '@/components/common/cronRoot/cron-second/cron-ui/index.vue'
export default {
  components: {
    CronUi,
    CronUiSecond
  },
  data () {
    return {
      result: '0,2,3,5 0,5 0,5,6 LW 9,10 ? 2001-2020',
      resultForm: {
        cpmEveryStartCron: '',
        cpmEveryEndCron: ''
      }
    }
  },
  methods: {
    open () {
      this.$refs['CronUi'].dialogVisible = true
    },
    open2 () {
      this.$refs['CronUiSecond'].dialogVisible = true
    },
    // 一次一个表达式---最终产生出来的cron表达式
    resultValue (data) {
      console.log(data)
      this.result = data
    },
    // 一次两个表达式---规则配置-确定
    confirmCron (data) {
      console.log('cron结果', data)
      this.resultForm.cpmEveryStartCron = data.result
      this.resultForm.cpmEveryEndCron = data.resultEnd
    }
  }
}
</script>
