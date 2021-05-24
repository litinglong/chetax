<template>
<el-container>
  <el-header>
    <el-row :gutter="10">
      <el-col :span="12">
        <div class="grid-content bg-purple">
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <div class="hello">
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <h1>{{ msg }}</h1>
        </div>
      </el-col>
    </el-row>
  </el-header>
  <el-main>
    <el-row :gutter="10">
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-input v-model="searchCondition" placeholder="" v-on:keyup.enter="vOnClickTest('shijian', $event)"></el-input>
        </div>
      </el-col>
      <el-col :span="2">
        <div class="grid-content bg-purple-light">
          <el-button type="primary" icon="el-icon-search" v-on:click="vOnClickTest('shijian', $event)">搜索</el-button>
        </div>
      </el-col>
    </el-row>
  </el-main>
  <el-footer>
    <el-row :gutter="10" v-for="(item, index) in resultList" v-bind:item="item" v-bind:index="index" v-bind:key="item._id">
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-link type="primary" v-bind:href="item._source.url" target="_blank">{{ item._source.title }}</el-link>
        </div>
      </el-col>
    </el-row>
    <el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop>
  </el-footer>
</el-container>

</template>

<script>
import Axios from 'axios'
export default {
  name: 'SearchIndexPage',
  data () {
    return {
      msg: '欢迎使用塔斯克搜索',
      searchCondition: '',
      resultList: []
    }
  },
  beforeCreate: function () {
    // `this` 指向 vm 实例
    console.log('a is:beforeCreate ')
  },
  created: function () {
    // `this` 指向 vm 实例
    console.log('a is:created ')
  },
  methods: {
    vOnClickTest: function (message, event) {
      // 现在我们可以访问原生事件对象
      if (event) {
        event.preventDefault()
      }
      if ((this.searchCondition === null || this.searchCondition === '' || this.searchCondition === undefined)) {
        this.$message('请输入搜索条件')
        return
      }
      var url = '/apis/matrix/apis/searchController/search/' + this.searchCondition
      var _this = this
      Axios.get(url).then((response) => {
        console.log(_this.hasResult)
        this.resultList = response.data.hits.hits
      }).catch((error) => {
        console.log(error)
      })
    }
  },
  computed: {
    hasResult: function () {
      var isResultNull = this.resultList === []
      console.log(this.resultList)
      return isResultNull
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li{
  /* display: inline-block; */
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
