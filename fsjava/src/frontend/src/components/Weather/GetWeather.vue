<template>
    <img :src="image"/> 
    <a>{{temperature}} °C</a>
</template>

<script>
export default {
  name: 'GetWeather',
  props: {
    place: String,
    timeCacheKey: String
  },
  data() {
    return {
      image: '',
      temperature: ''
    }
  },
  mounted() {
      const cacheTime = JSON.parse(localStorage.getItem(this.timeCacheKey));
      const time = cacheTime.value;
      
      fetch(`/api/weather/data?place=${this.place}&time=${time}`)
        .then(response => response.text())
        .then(data => {
          const jsonData = JSON.parse(data);
          this.getWeatherImage(jsonData.status.replace(/\s+/g, ''))
          this.temperature = jsonData.temperature;
        });   
  },
  methods:{
    getWeatherImage(status){
        const data = require('./configWeather.json');
        this.image = data[status];
    }
  }
}
</script>