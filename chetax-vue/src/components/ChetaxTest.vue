<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <table>
      <thead>
        <tr><td>表头1</td><td>表头2</td></tr>
      </thead>
      <tbody>
        <tr><td>v-bind ：</td><td><a v-bind:href="urlInfo.path">{{ urlInfo.description }}</a></td></tr>
        <tr><td>v-on (请点击)：</td><td><a v-on:click="vOnTest()">{{ msg }}</a></td></tr>
        <tr><td>v-on缩写 (请点击)：</td><td><a @click="vOnShortTest()">{{ msg }}</a></td></tr>
        <tr><td>computed ：</td><td><h1>{{ now }}</h1></td></tr>
        <tr><td>watch （问题）：</td><td><input v-model="question"></td></tr>
        <tr><td>watch （答案）：</td><td>{{ answer }}</td></tr>
        <tr><td>v-bind 样式（条件语法）：</td><td class="static" v-bind:class="{ active: isActive, 'text-danger': hasError }">active:{{ isActive }},text-danger:{{ hasError }}</td></tr>
        <tr><td>v-bind 样式（数组语法）：</td><td class="static" v-bind:class="[dynamicClass.activeClass, dynamicClass.errorClass]">activeClass:{{ dynamicClass.activeClass }},errorClass:{{ dynamicClass.errorClass }}</td></tr>
        <tr><td>v-if ：</td><td><h1 v-if="vIf1.type === 'A'">A</h1><h1 v-else-if="vIf1.type === 'B'">B</h1><h1 v-else>Not A/B</h1></td></tr>
        <tr><td>v-show ：</td><td><h1 v-show="vShow1.tag">{{ vShow1.info }}</h1></td></tr>
        <tr><td>v-for ：</td><td><div v-for="(item, index) in vFor1.items" v-bind:item="item" v-bind:index="index" v-bind:key="item.id"> {{ index }}. {{ item.name }}: {{ item.value }} </div></td></tr>
        <tr><td>v-on:click 传递事件对象：</td><td><button v-on:click="vOnClickTest('事件修饰符，可追加在事件标识符后：.stop .prevent .capture .self .once .passive', $event)"> Submit </button></td></tr>
        <tr><td>v-on:keyup.enter：</td><td><input v-on:keyup.enter="msgAlert('enter')"/></td></tr>
        <tr><td>v-on:keyup.page-down：</td><td><input v-on:keyup.page-down="msgAlert('page-down')"/></td></tr>
        <tr><td>v-on:keyup.tab:</td><td><input v-on:keyup.tab="msgAlert('tab')"/></td></tr>
        <tr><td>v-on:keyup.delete： </td><td><input v-on:keyup.delete="msgAlert('delete')"/></td></tr>
        <tr><td>v-on:keyup.esc：</td><td><input v-on:keyup.esc="msgAlert('esc')"/></td></tr>
        <tr><td>v-on:keyup.space：</td><td><input v-on:keyup.space="msgAlert('space')"/></td></tr>
        <tr><td>v-on:keyup.up：</td><td><input v-on:keyup.up="msgAlert('up')"/></td></tr>
        <tr><td>v-on:keyup.down：</td><td><input v-on:keyup.down="msgAlert('down')"/></td></tr>
        <tr><td>v-on:keyup.left：</td><td><input v-on:keyup.left="msgAlert('left')"/></td></tr>
        <tr><td>v-on:keyup.right：</td><td><input v-on:keyup.right="msgAlert('right')"/></td></tr>
        <tr><td>v-on:click.left：</td><td><button v-on:click.left="msgAlert('left')"> left </button></td></tr>
        <tr><td>v-on:click.right：</td><td><button v-on:click.right="msgAlert('right')"> right </button></td></tr>
        <tr><td>v-on:click.middle：</td><td><button v-on:click.middle="msgAlert('middle')"> middle </button></td></tr>
        <tr><td>v-model：文本</td><td><input v-model="vModel1.message" placeholder="edit me"></td><td><p>Message is: {{ vModel1.message }}</p></td></tr>
        <tr><td>v-model：多行文本</td><td><textarea v-model="vModel1.message" placeholder="add multiple lines"></textarea></td><td><p>Message is: {{ vModel1.message }}</p></td></tr>
        <tr><td>v-model：单复选框</td><td><input type="checkbox" id="checkbox" v-model="vModel1.checked"><label for="checkbox">{{ vModel1.checked }}</label></td></tr>
        <tr><td>v-model：多复选框</td><td>
          <input type="checkbox" id="jack" value="Jack" v-model="vModel1.checkedNames">
          <label for="jack">Jack</label>
          <input type="checkbox" id="john" value="John" v-model="vModel1.checkedNames">
          <label for="john">John</label>
          <input type="checkbox" id="mike" value="Mike" v-model="vModel1.checkedNames">
          <label for="mike">Mike</label>
          </td><td><p>Message is: {{ vModel1.checkedNames }}</p></td></tr>
        <tr><td>v-model：单选按钮</td><td>
          <input type="radio" id="one" value="One" v-model="vModel1.picked">
          <label for="one">One</label>
          <input type="radio" id="two" value="Two" v-model="vModel1.picked">
          <label for="two">Two</label>
          </td><td><p>Picked is: {{ vModel1.picked }}</p></td></tr>
        <tr><td>v-model：单项选择框</td><td>
          <select v-model="vModel1.selected">
            <option disabled value="">请选择</option>
            <option>A</option>
            <option>B</option>
            <option>C</option>
          </select>
          </td><td><p>Selected is: {{ vModel1.selected }}</p></td></tr>
        <tr><td>v-model：多项选择框</td><td>
          <select v-model="vModel1.multipleSelected" multiple style="width: 50px;">
            <option>A</option>
            <option>B</option>
            <option>C</option>
          </select>
          </td><td><p>Selected is: {{ vModel1.multipleSelected }}</p></td></tr>
        <tr><td>v-for：渲染多项选择框</td><td>
          <select v-model="vModel1.multipleSelected" multiple style="width: 50px;">
            <option v-for="option in vFor1.options" v-bind:value="option.value" v-bind:key="option.value">
              {{ option.text }}
            </option>
          </select>
          </td><td><p>Selected is: {{ vModel1.multipleSelected }}</p></td></tr>
        <tr><td>SingleFileConponent属性静态赋值：</td><td><SingleFileConponent msg="属性静态赋值"></SingleFileConponent></td></tr>
        <tr><td>SingleFileConponent属性动态赋值：</td><td><SingleFileConponent v-bind:msg="vBind.singleFileConponentValue" v-on:clicknow="msgAlert"></SingleFileConponent></td></tr>
      </tbody>
    </table>
  </div>
</template>

<script>

import SingleFileConponent from '@/components/SingleFileConponent'
export default {
  name: 'ChetaxTest',
  data () {
    return {
      msg: 'Welcome to ChetaxTest',
      urlInfo: {
        description: '百度',
        path: 'http://www.baidu.com'
      },
      question: '',
      answer: 'I cannot give you an answer until you ask a question!',
      isActive: true,
      hasError: false,
      dynamicClass: {
        activeClass: 'active',
        errorClass: 'text-danger'
      },
      vIf1: {
        type: 'A'
      },
      vShow1: {
        info: 'dom元素必定存在，如果页面看不到渲染，那只是隐藏了，不支持template',
        tag: true
      },
      vFor1: {
        items: [
          { id: '0', name: 'title', value: 'How to do lists in Vue' },
          { id: '1', name: 'author', value: 'Jane Doe' },
          { id: '2', name: 'publishedAt', value: '2016-04-10' }
        ],
        options: [
          { text: 'One', value: 'A' },
          { text: 'Two', value: 'B' },
          { text: 'Three', value: 'C' }
        ]
      },
      vModel1: {
        message: '',
        checked: true,
        checkedNames: [],
        picked: '',
        selected: '',
        multipleSelected: []
      },
      vBind: {
        singleFileConponentValue: '属性动态赋值'
      }
    }
  },
  beforeCreate: function () {
    console.log('ChetaxTest is:beforeCreate')
  },
  created: function () {
    console.log('ChetaxTest is:created')
  },
  beforeMount: function () {
    console.log('ChetaxTest is:beforeMount')
  },
  mounted: function () {
    console.log('ChetaxTest is:mounted')
  },
  beforeUpdate: function () {
    console.log('ChetaxTest is:beforeUpdate')
  },
  updated: function () {
    console.log('ChetaxTest is:updated')
  },
  beforeDestroy: function () {
    console.log('ChetaxTest is:beforeDestroy')
  },
  destroyed: function () {
    console.log('ChetaxTest is:destroyed')
  },
  computed: {
    now: function () {
      return Date.now()
    }
  },
  methods: {
    vOnTest: function () {
      this.msg = 'vOnTest'
    },
    vOnShortTest: function () {
      this.msg = 'vOnShortTest'
    },
    vOnClickTest: function (message, event) {
      // 现在我们可以访问原生事件对象
      if (event) {
        event.preventDefault()
      }
      alert(message)
    },
    msgAlert: function (message) {
      alert(message)
    }
  },
  watch: {
    // 如果 `question` 发生改变，这个函数就会运行
    question: function (newQuestion, oldQuestion) {
      this.answer = 'old(' + oldQuestion + '):new(' + newQuestion + '):time(' + Date.now() + ')'
    }
  },
  components: {
    // 组件局部注册
    SingleFileConponent
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1{
  font-weight: normal;
}
td{
  float: left;
  border: 1px solid black
}
</style>
