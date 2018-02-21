<template>
  <div>
    <Header></Header>
    <Lefter></Lefter>
    <slot></slot>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from '../components/header.vue'
import Lefter from '../components/lefter.vue'
import Footer from '../components/footer.vue'

  export default {
    components: {
      Header, Lefter, Footer
    }
  }
</script>
