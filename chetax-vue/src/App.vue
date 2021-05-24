<template>
  <div id="app">
    <el-row :gutter="40">
      <el-col :span="18">
        <div class="grid-content bg-purple">
          <img src="./assets/search_logo.png">
          <router-view/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
