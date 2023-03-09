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
        <InfoCardCounter cardType="Imsak"/>
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
        <InfoCardCounter cardType="Sabah"/>
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
        <InfoCardCounter cardType="Öglen"/>
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
        <InfoCardCounter cardType="Ikindi"/>
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
        <InfoCardCounter cardType="Aksam"/>
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
        <InfoCardCounter cardType="Yatsi"/>
    </div>
    
    <div class="modals">
        <!--MODAL WRITE-->
        <Modal v-if="modalContent != ''" :open="isOpenWrite" @close="isOpenWrite = !isOpenWrite">
        <div style = "text-align: left;">
            <PostInfoCard :cardType="modalContent"/>
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
import PostInfoCard from '../InfoCard/PostInfoCard.vue';
import readImage from "@/assets/open-book.png";
import writeImage from "@/assets/write_icon_237016.png";
import InfoCardCounter from '../InfoCard/InfoCardCounter.vue';
import {ref} from "vue";


export default {
  name: 'Content',
  components: {
    GetPrayersTime,
    GetWeather,
    CacheTime,
    Modal,
    GetInfoCard,
    PostInfoCard,
    InfoCardCounter
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
    setModal(timeType, modal){
      if(modal == "read"){
        this.isOpenRead = true;
      }
      else{
        this.isOpenWrite = true;
      }
      this.modalContent = timeType; 
    }
  }
}
</script>
<style>
.headerLeftSection { grid-area: 1 / 1 / 2 / 4;}
.headerRightSection { grid-area: 1 / 4 / 2 / 8; border: 1px solid #636866;transition: background-color 0.5s ease;}
.content1 { grid-area: 2 / 1 / 4 / 2;transition: background-color 0.5s ease;}
.content2 { grid-area: 2 / 2 / 4 / 3;transition: background-color 0.5s ease;}
.content3 { grid-area: 2 / 3 / 4 / 4;transition: background-color 0.5s ease;}
.content4 { grid-area: 2 / 5 / 4 / 6;transition: background-color 0.5s ease;}
.content5 { grid-area: 2 / 6 / 4 / 7;transition: background-color 0.5s ease;}
.content6 { grid-area: 2 / 7 / 4 / 8;transition: background-color 0.5s ease;}

.headerRightSection:hover, .content1:hover, .content2:hover, .content3:hover, .content4:hover, .content5:hover, .content6:hover{
  background-color: wheat;
  cursor: pointer;
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