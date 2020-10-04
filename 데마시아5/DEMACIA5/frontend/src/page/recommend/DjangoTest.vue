<template>
  <div>
    안녕
  </div>
</template>

<script>
import axios from 'axios'
export default {
  created() {
    this.test()
  },
  methods: {
    test() {
      axios.get('http://j3a304.p.ssafy.io/django/api/champions/')
      .then(res => {
        console.log(res.data)
      })
      .catch(err => console.log(err.response))
    }

  }
}
</script>

<style>

</style>