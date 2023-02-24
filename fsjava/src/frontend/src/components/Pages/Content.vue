<template>
    <div class ="content1"> 
        <div>
        <GetPrayersTime timeCacheKey="timeSunrise"/>
        </div> 
        <div>
        <GetWeather place="Neudenau" timeCacheKey="timeSunrise"/>
        </div>
        <p>Imsak</p>
        <img :src="writeImage" @click="setModal('Imsak','write')" style="margin-right: 10px;"/>
        <img :src="readImage" @click="setModal('Imsak','read')" style="margin-right: 10px;"/>
        <p>0/0</p>
    </div>
    
    <div class="content2">
        <div>
        <GetPrayersTime timeCacheKey="timeMorning"/>
        </div> 
        <div>
        <GetWeather place="Neudenau" timeCacheKey="timeMorning"/>
        </div>
        <p>Sabah</p>
        <img :src="writeImage" @click="setModal('Sabah','write')" style="margin-right: 10px;"/>
        <img :src="readImage" @click="setModal('Sabah','read')" style="margin-right: 10px;"/>
        <p>0/0</p>
    </div>
    
    <div class="content3"> 
        <div>
        <GetPrayersTime timeCacheKey="timeNoon"/>
        </div> 
        <div>
        <GetWeather place="Neudenau" timeCacheKey="timeNoon"/>
        </div>
        <p>Öglen</p>
        <img :src="writeImage" @click="setModal('Öglen','write')" style="margin-right: 10px;"/>
        <img :src="readImage" @click="setModal('Öglen','read')" style="margin-right: 10px;"/>
        <p>0/0</p>
    </div>
    
    <div class="content4"> 
        <div>
        <GetPrayersTime timeCacheKey="timeAfternoon"/>
        </div> 
        <div>
        <GetWeather place="Neudenau" timeCacheKey="timeAfternoon"/>
        </div>
        <p>Ikindi</p>
        <img :src="writeImage" @click="setModal('Ikindi','write')" style="margin-right: 10px;"/>
        <img :src="readImage" @click="setModal('Ikindi','read')" style="margin-right: 10px;"/>
        <p>0/0</p>
    </div>
    
    <div class="content5">
        <div>
        <GetPrayersTime timeCacheKey="timeEvening"/>
        </div> 
        <div>
        <GetWeather place="Neudenau" timeCacheKey="timeEvening"/>
        </div>
        <p>Aksam</p>
        <img :src="writeImage" @click="setModal('Aksam','write')" style="margin-right: 10px;"/>
        <img :src="readImage" @click="setModal('Aksam','read')" style="margin-right: 10px;"/>
        <p>0/0</p>
    </div>
    
    <div class="content6">
        <div>
        <GetPrayersTime timeCacheKey="timeNight"/>
        </div> 
        <div>
        <GetWeather place="Neudenau" timeCacheKey="timeNight"/>
        </div>
        <p>Yatsi</p>
        <img :src="writeImage" @click="setModal('Yatsi','write')" style="margin-right: 10px;"/>
        <img :src="readImage" @click="setModal('Yatsi','read')" style="margin-right: 10px;"/>
        <p>0/0</p>
    </div>
    
    <div class="modals">
        <!--MODAL WRITE-->
        <Modal v-if="modalContent != ''" :open="isOpenWrite" @close="isOpenWrite = !isOpenWrite">
        <div style = "text-align: left;">
            <a style="color: black;font-size: 18px">Goal:</a>
            <input form="text" style="margin-left: 10px;"/>
        </div>
        </Modal>

        <!--MODAL READ-->
        <Modal v-if="modalContent != ''" :open="isOpenRead" @close="isOpenRead = !isOpenRead">
        <div style = "text-align: left;">
            <GetInfoCard :cardType="modalContent"/>
        </div>
        </Modal>
    </div>
</template>

<script>
import GetPrayersTime from '../Time/GetPrayersTime.vue';
import GetWeather from '../Weather/GetWeather.vue'
import CacheTime from '../Time/CacheTime.vue';
import Modal from '../InfoCard/Modal.vue';
import GetInfoCard from '../InfoCard/GetInfoCard.vue';
import readImage from "@/assets/open-book.png";
import writeImage from "@/assets/write_icon_237016.png";
import {ref} from "vue";


export default {
  name: 'Content',
  components: {
    GetPrayersTime,
    GetWeather,
    CacheTime,
    Modal,
    GetInfoCard
  },
  data() {
    return {
      readImage,
      writeImage,
      modalContent: ""
    };
  },
  setup(){
    const isOpenWrite = ref(false)
    const isOpenRead = ref(false)
    return { isOpenRead, isOpenWrite}
  },
  methods:{
    setModal(type, modal){
      if(modal == "read"){
        this.isOpenRead = true;
      }
      else{
        this.isOpenWrite = true;
      }
      this.modalContent = type; 
    }
  }
}
</script>