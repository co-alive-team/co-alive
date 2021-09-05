<template>
  <Navbar title="co-alive" />
  <Section />
  <div class="album py-5 bg-light">
    <div class="container">
      <Card content="hellooo" username="sayho" time="9분 전"/>
    </div>
  </div>
  <Footer />
</template>

<script>
import Navbar from "./components/Navbar.vue";
import Card from "./components/Card.vue";
import Footer from "./components/Footer.vue";
import Section from "./components/Section.vue";

export default {
  name: "App",
  components: {
    Navbar,
    Card,
    Footer,
    Section
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
