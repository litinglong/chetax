import Vue from 'vue'
import Router from 'vue-router'
import ChetaxTest from '@/components/ChetaxTest.vue'
import SearchIndexPage from '@/components/SearchIndexPage.vue'
import SysScheduleInfoList from '@/components/system/SysScheduleInfoList.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'SysScheduleInfoList',
      component: SysScheduleInfoList
    },
    {
      path: '/ChetaxTest',
      name: 'ChetaxTest',
      component: ChetaxTest
    },
    {
      path: '/SearchIndexPage',
      name: 'SearchIndexPage',
      component: SearchIndexPage
    },
    {
      path: '/SysScheduleInfoList',
      name: 'SysScheduleInfoList',
      component: SysScheduleInfoList
    }
  ]
})
