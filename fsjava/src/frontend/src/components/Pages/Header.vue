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
    <a id="headerText" @click="this.isOpen = true">
      ayat of the day:
    </a>
  </div>
  <Modal :open="this.isOpen" @close="this.isOpen = !isOpen">
      <!-- <p style="text-align: left;">GOD - there is no deity save Him, the Ever-Living, the Self-Subsistent Fount of All Being. Neither slumber overtakes Him, nor sleep. His is all that is in the heavens and all that is on earth. Who is there that could intercede with Him, unless it be by His leave? He knows all that lies open before men and all that is hidden from them, whereas they cannot attain to aught of His knowledge save that which He wills [them to attain]. His eternal power overspreads the heavens and the earth, and their upholding wearies Him not. And he alone is truly exalted, tremendous</p>
      <p>Al-Baqara ~ 2:255</p> -->
      <AyatPage/>
  </Modal>
</template>

<script>
import GetTimeHeader from '../Time/GetTimeHeader.vue';
import GetWeatherHeader from '../Weather/GetWeatherHeader.vue';
import AyatPage from '../Ayat/AyatPage.vue';
import Modal from '../InfoCard/Modal.vue'
import {ref} from "vue";


export default {
  name: 'Header',
  components: {
    GetTimeHeader,
    GetWeatherHeader,
    AyatPage,
    Modal
  },
  setup(){
    const isOpen = ref(false)
    return { isOpen }
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

#headerText{
  background-color:#d1c9ca;
  color: #413637;
}
</style>