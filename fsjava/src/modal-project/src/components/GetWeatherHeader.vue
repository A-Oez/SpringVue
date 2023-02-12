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
      temperature: ''
    }
  },
  mounted() {      
    this.getWeather();

    setInterval(() => {
      this.getWeather();
    }, 900000) 
  },
  methods:{
    async getWeather(){
        fetch(`/api/weather/data?place=${this.place}&time=now`)
        .then(response => response.text())
        .then(data => {
          const jsonData = JSON.parse(data);
          this.image = jsonData.image;
          this.temperature = jsonData.temperature;
        }); 
    }
  }
}
</script>