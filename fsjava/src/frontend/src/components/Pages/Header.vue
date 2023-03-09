<template>
   <div class="headerLeftSection"> 
    <div class = "showCityHeader">
      <a>Neudenau</a>
    </div>
    <div class = "showTimeHeader">
      <GetTimeHeader/>
    </div>
    <div class = "showWeather">
      <GetWeatherHeader place="Neudenau"/>
    </div> 
  </div>
  
  <div class="headerRightSection"> 
    <a class="headerText">
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
<style>
.showCityHeader, .showTimeHeader{
  position: absolute;
  margin-left: 5%;
  font-size: 20px;
}

.showTimeHeader{
  margin-top: 35px;
}

.headerText{
  background-color:#d1c9ca;
  color: #413637;
}
</style>