<template>
  <CacheTime/>
  <Header/>
  <Content/>
</template>

<script>
import CacheTime from './components/Time/CacheTime.vue';
import Header from './components/Pages/Header.vue';
import Content from './components/Pages/Content.vue';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

export default {
  name: 'App',
  components: {
    CacheTime,
    Header,
    Content
  },
  mounted(){
    setInterval(() => {
      location.replace(location.href);
    }, 900000) 
  }
}
</script>

<style>
@import 'https://fonts.googleapis.com/css?family=Playfair+Display:400,700i,900i';

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: white;
  background-color: #273036;
  margin-top: 60px;

  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-template-rows: repeat(3, 1fr);
  grid-column-gap: 10px;
  grid-row-gap: 100px;
  
  font-family: 'Playfair Display', serif;
  font-size: 20px;
}
</style>