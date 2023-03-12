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
      select ayat:
    </a>
  </div>
  <Modal :open="this.isOpen" @close="this.isOpen = !isOpen">
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