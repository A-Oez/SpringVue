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
  
  <div class="headerRightSection" @click="this.isOpen = true"> 
    <AyatGenerator/>
  </div>
  <Modal :open="this.isOpen" @close="this.isOpen = !isOpen">
    <AyatPaging/>
  </Modal>
</template>

<script>
import GetTimeHeader from '../Time/GetTimeHeader.vue';
import GetWeatherHeader from '../Weather/GetWeatherHeader.vue';
import AyatPaging from '../Ayat/Paging/AyatPaging.vue';
import AyatGenerator from '../Ayat/AyatGenerator.vue';
import Modal from '../InfoCard/Modal.vue'
import {ref} from "vue";


export default {
  name: 'Header',
  components: {
    GetTimeHeader,
    GetWeatherHeader,
    AyatPaging,
    AyatGenerator,
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
  font-size: 14px;
}
</style>