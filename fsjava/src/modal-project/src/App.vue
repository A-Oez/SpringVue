<template>
  <!--<HelloWorld msg="Welcome to Your Vue.js App"/>-->
  <div class="headerLeftSection"> 
    <div class = "showCity">
      <a>Neudenau</a>
    </div>
    <div class = "showTime">
      <GetTime/>
    </div>
    <div class = "showWeather">
      <img id="currentWeather" src="//ssl.gstatic.com/onebox/weather/64/cloudy.png" />
      <a>22°</a>
    </div>
  </div>
  
  <div class="headerRightSection"> 
    <a id="headerText">
      This is the Book about which there is no doubt, a guidance for those conscious of Allah – Baqarah Ayat 2
    </a>
  </div>
  
  <div class="content1"> 
    <div>
      <GetPrayersTime time-type="sunrise"/>
    </div> 
    <div>
      <img src="./assets/weather_sunny.png"/>
      <a>22°</a>
    </div>
    <p>Imsak</p>
  </div>
  
  <div class="content2">
    <div>
      <GetPrayersTime time-type="morning"/>
    </div> 
    <div>
      <img src="./assets/weather_sunny.png"/>
      <a>22°</a>
    </div>
    <p>Sabah</p>
  </div>
  
  <div class="content3"> 
    <div>
      <GetPrayersTime time-type="noon"/>
    </div> 
    <div>
      <img src="./assets/weather_sunny.png"/>
      <a>22°</a>
    </div>
    <p>Öglen</p>
  </div>
  
  <div class="content4"> 
    <div>
      <GetPrayersTime time-type="afternoon"/>
    </div> 
    <div>
      <img src="./assets/weather_sunny.png"/>
      <a>22°</a>
    </div>
    <p>Ikindi</p>
  </div>
  
  <div class="content5">
    <div>
      <GetPrayersTime time-type="evening"/>
    </div> 
    <div>
      <img src="./assets/weather_sunny.png"/>
      <a>22°</a>
    </div>
    <p>Aksam</p>
  </div>
  
  <div class="content6">
    <div>
      <GetPrayersTime time-type="night"/>
    </div> 
    <div>
      <img src="./assets/weather_sunny.png"/>
      <a>22°</a>
    </div>
    <p>Yatsi</p>
  </div>
</template>

<script>
import GetPrayersTime from './components/GetPrayersTime.vue';
import GetTime from './components/GetTime.vue';

export default {
  name: 'App',
  components: {
    GetPrayersTime,
    GetTime
  }
}
</script>

<style>
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
  grid-row-gap: 100px
}

.showCity, .showTime{
  position: absolute;
  margin-left: 5%;
  font-size: 20px;
}

.showTime{
  margin-top: 35px;
}

#currentWeather{
  width: 20%; 
  height: auto;
}

a,p{
  font-size: 20px;
}

#headerText{
  background-color:#d1c9ca;
  color: #413637;
}

.headerLeftSection { grid-area: 1 / 1 / 2 / 4;}
.headerRightSection { grid-area: 1 / 4 / 2 / 8; border: 1px solid #636866;}
.content1 { grid-area: 2 / 1 / 4 / 2;}
.content2 { grid-area: 2 / 2 / 4 / 3;}
.content3 { grid-area: 2 / 3 / 4 / 4;}
.content4 { grid-area: 2 / 5 / 4 / 6;}
.content5 { grid-area: 2 / 6 / 4 / 7;}
.content6 { grid-area: 2 / 7 / 4 / 8;}

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