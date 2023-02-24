<template>
   <div class="headerLeftSection"> 
    <div class = "showCity">
      <a>Neudenau</a>
    </div>
    <div class = "showTime">
      <GetTimeHeader/>
    </div>
    <div class = "showWeather">
      <GetWeatherHeader place="Neudenau"/>
    </div> 
  </div>
  
  <div class="headerRightSection"> 
    <a id="headerText">
      This is the Book about which there is no doubt, a guidance for those conscious of Allah – Baqarah Ayat 2
    </a>
   </div>
</template>

<script>
import GetTimeHeader from '../Time/GetTimeHeader.vue';
import GetWeatherHeader from '../Weather/GetWeatherHeader.vue';

export default {
  name: 'Header',
  components: {
    GetTimeHeader,
    GetWeatherHeader
  }
}
</script>