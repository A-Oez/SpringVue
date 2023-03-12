<template>
    <img :src="image"/> 
    <a>{{temperature}} °C</a>
</template>

<script>
export default {
  name: 'GetWeatherHeader',
  props: {
    place: String
  },
  data() {
    return {
      image: '',
      temperature: '',
      weatherStatus:''
    }
  },
  mounted() {      
    this.getWeather();

    setInterval(() => {
      this.getWeather();
    }, 900000) 
  },
  methods:{
    getWeather(){
        fetch(`/api/weather/data?place=${this.place}&time=jetzt`)
        .then(response => response.text())
        .then(data => {
          const jsonData = JSON.parse(data);
          this.weatherStatus = jsonData.status.replace(/\s+/g, '')
          this.getWeatherImage(jsonData.status.replace(/\s+/g, ''))
          this.temperature = jsonData.temperature;
        }); 
    },
    getWeatherImage(status){
        const weatherImages = require('./configWeather.json');
        this.image = weatherImages[status];
    }
  }
}
</script>