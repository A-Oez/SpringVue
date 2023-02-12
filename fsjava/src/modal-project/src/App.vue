<template>
  <!--<HelloWorld msg="Welcome to Your Vue.js App"/>-->
  <CacheTime/>
  <div class="headerLeftSection"> 
    <div class = "showCity">
      <a>Neudenau</a>
    </div>
    <div class = "showTime">
      <GetTimeHeader/>
    </div>
    <div class = "showWeather">
      <GetWeatherHeader place="Neudenau"></GetWeatherHeader>
    </div> 
  </div>
  
  <div class="headerRightSection"> 
    <a id="headerText">
      This is the Book about which there is no doubt, a guidance for those conscious of Allah – Baqarah Ayat 2
    </a>
  </div>
  
  <div class="content1"> 
    <div>
      <GetPrayersTime timeCacheKey="timeSunrise"/>
    </div> 
    <div>
      <GetWeather place="Neudenau" timeCacheKey="timeSunrise"></GetWeather>
    </div>
    <p>Imsak</p>
  </div>
  
  <div class="content2">
    <div>
      <GetPrayersTime timeCacheKey="timeMorning"/>
    </div> 
    <div>
      <GetWeather place="Neudenau" timeCacheKey="timeMorning"></GetWeather>
    </div>
    <p>Sabah</p>
  </div>
  
  <div class="content3"> 
    <div>
      <GetPrayersTime timeCacheKey="timeNoon"/>
    </div> 
    <div>
      <GetWeather place="Neudenau" timeCacheKey="timeNoon"></GetWeather>
    </div>
    <p>Öglen</p>
  </div>
  
  <div class="content4"> 
    <div>
      <GetPrayersTime timeCacheKey="timeAfternoon"/>
    </div> 
    <div>
      <GetWeather place="Neudenau" timeCacheKey="timeAfternoon"></GetWeather>
    </div>
    <p>Ikindi</p>
  </div>
  
  <div class="content5">
    <div>
      <GetPrayersTime timeCacheKey="timeEvening"/>
    </div> 
    <div>
      <GetWeather place="Neudenau" timeCacheKey="timeEvening"></GetWeather>
    </div>
    <p>Aksam</p>
  </div>
  
  <div class="content6">
    <div>
      <GetPrayersTime timeCacheKey="timeNight"/>
    </div> 
    <div>
      <GetWeather place="Neudenau" timeCacheKey="timeNight"></GetWeather>
    </div>
    <p>Yatsi</p>
  </div>
</template>

<script>
import GetPrayersTime from './components/GetPrayersTime.vue';
import GetTimeHeader from './components/GetTimeHeader.vue';
import GetWeather from './components/GetWeather.vue'
import GetWeatherHeader from './components/GetWeatherHeader.vue';
import CacheTime from './components/CacheTime.vue';

export default {
  name: 'App',
  components: {
    GetPrayersTime,
    GetWeather,
    GetTimeHeader,
    CacheTime,
    GetWeatherHeader
  },
  mounted(){
    setInterval(() => {
      location.reload;
    }, 900000) 
  }
}
</script>

<style>
@import 'https://fonts.googleapis.com/css?family=Playfair+Display:400,700i,900i';

*{
  box-sizing: border-box;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: white;
  margin-top: 60px;

  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-template-rows: repeat(3, 1fr);
  grid-column-gap: 10px;
  grid-row-gap: 100px;
  
  font-family: 'Playfair Display', serif;
  font-size: 22px;
}

.wrap{
  perspective-origin: 50% 50%;
}


.showCity, .showTime{
  position: absolute;
  margin-left: 5%;
  font-size: 20px;
}

.showTime{
  margin-top: 35px;
  border: 2px solid #636866;
}

a,p{
  font-size: 20px;
}

#headerText{
  background-color:#d1c9ca;
  color: #413637;
}

.headerLeftSection { grid-area: 1 / 1 / 2 / 4;}
.headerRightSection { grid-area: 1 / 4 / 2 / 8; border: 1px solid #636866;transition: background-color 0.5s ease;}
.content1 { grid-area: 2 / 1 / 4 / 2;transition: background-color 0.5s ease;}
.content2 { grid-area: 2 / 2 / 4 / 3;transition: background-color 0.5s ease;}
.content3 { grid-area: 2 / 3 / 4 / 4;transition: background-color 0.5s ease;}
.content4 { grid-area: 2 / 5 / 4 / 6;transition: background-color 0.5s ease;}
.content5 { grid-area: 2 / 6 / 4 / 7;transition: background-color 0.5s ease;}
.content6 { grid-area: 2 / 7 / 4 / 8;transition: background-color 0.5s ease;}

.headerRightSection:hover, .content1:hover, .content2:hover, .content3:hover, .content4:hover, .content5:hover, .content6:hover{
  background-color: wheat;cursor: pointer;
}

.content1,.content2,.content3,.content4,.content5,.content6{
  box-shadow:
  1.6px 1.8px 0.3px rgba(0, 0, 0, 0.18),
  3.4px 3.9px 0.7px rgba(0, 0, 0, 0.159),
  5.8px 6.5px 1.2px rgba(0, 0, 0, 0.161),
  8.7px 9.8px 1.8px rgba(0, 0, 0, 0.167),
  12.5px 14.1px 2.6px rgba(0, 0, 0, 0.173),
  17.7px 20px 3.7px rgba(0, 0, 0, 0.179),
  25.1px 28.4px 5.3px rgba(0, 0, 0, 0.182),
  36.5px 41.2px 7.7px rgba(0, 0, 0, 0.179),
  56.3px 63.6px 11.8px rgba(0, 0, 0, 0.168),
  100px 113px 21px rgba(0, 0, 0, 0.14);
}
</style>