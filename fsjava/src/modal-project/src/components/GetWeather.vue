<template>
    <img :src="image"/> 
    <a>{{temperature}} °C</a>
</template>

<script>
export default {
  name: 'GetWeather',
  props: {
    place: String,
    time: String
  },
  data() {
    return {
      image: '',
      temperature: ''
    }
  },
  mounted() {
      fetch(`/api/weather/data?place=${this.place}&time=${this.time}`)
        .then(response => response.text())
        .then(data => {
          const jsonData = JSON.parse(data);
          this.image = jsonData.image;
          this.temperature = jsonData.temperature;
        });
  }
}
</script>