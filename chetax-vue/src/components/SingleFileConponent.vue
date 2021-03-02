<template>
    <div>
        <h2 class="red">SingleFileConponent...{{msg}}:{{count}}</h2>
        <button v-on:click="vOnClickTest()">点击计数</button>
    </div>
</template>

<script>
export default {
  name: 'SingleFileConponent',
  props: {
    msg: {
      type: String,
      default: 'SingleFileConponent msg'
    },
    // 基础的类型检查 (`null` 和 `undefined` 会通过任何类型验证)
    propA: Number,
    // 多个可能的类型
    propB: [String, Number],
    // 必填的字符串
    propC: {
      type: String,
      required: true
    },
    // 带有默认值的数字
    propD: {
      type: Number,
      default: 100
    },
    // 带有默认值的对象
    propE: {
      type: Object,
      // 对象或数组默认值必须从一个工厂函数获取
      default: function () {
        return { message: 'hello' }
      }
    },
    // 自定义验证函数
    propF: {
      validator: function (value) {
        // 这个值必须匹配下列字符串中的一个
        return ['success', 'warning', 'danger'].indexOf(value) !== -1
      }
    }
  },
  methods: {
    vOnClickTest: function () {
      this.count++
      this.$emit('clicknow', this.count) // 暴露事件,并给监听该事件的方法传递一个参数
    }
  },
  data () {
    return {
      // 可拿props中的值对data中的属性进行初始化赋值
      count: 0
    }
  },
  computed: {
    // 可拿props中的值用于computed中函数的运算
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.red{color: #FF0000;}
</style>
