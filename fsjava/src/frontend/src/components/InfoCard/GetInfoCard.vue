<template>
  <ul v-for="task in json">
    {{ task.value }}| Check: {{ task.check }}
  </ul>
</template>

<script>
export default {
  name: 'GetInfoCard',
  props: {
    cardType: String
  },
  data() {
    return {
      json: '',
    }
  },
  mounted() {      
    this.getWeather();
  },
  methods:{
    async getWeather(){
        fetch(`/api/infocard/getData`)
        .then(response => response.text())
        .then(data => {       
          const jsonData = JSON.parse(data);
          this.json = jsonData;
          console.log(jsonData);
        }); 
    }
  }
}
</script>

<style>

ul{
  color: black;
}


</style>