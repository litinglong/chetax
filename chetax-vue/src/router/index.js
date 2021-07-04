import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import ChetaxTest from '@/components/ChetaxTest'
import SearchIndexPage from '@/components/SearchIndexPage'
import SysScheduleInfoList from '@/components/SysScheduleInfoList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'SearchIndexPage',
      component: SearchIndexPage
    },
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
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
