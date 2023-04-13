<template>
        <h3>{{ this.surahSelectedNumber }} ~ {{ this.surahSelectedName }}</h3>
        <div class="ayatSearchFunction" v-if="this.ayatSearch == true">
          <div class="input-group mb-3">
             <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">{{this.surahSelectedNumber}}:</span>
             </div>  
                <input id="ayatSelector" type="number" class="form-control" placeholder="Ayat" aria-label="Ayat" aria-describedby="basic-addon1" min="1">
          </div>
          <button class="btn btn-success" @click="searchAyat()" title="display ayat">⇄</button>
        </div>
  
        <div class="contentInfoCenter">
          <div class="contentInfoText">
              <p id="selectedAyatCounterDisplay">Ayat: {{ this.selectedAyat }}</p>
              <p id="maxAyatDisplay">Ayat-max: {{ this.surahAyat }}</p>
          </div>
          <div class="contentInfoButtons">
            <button class="btn btn-danger" @click="selectSurah()" title="return">↶</button>
            <button class="btn btn-success" @click=" translate()" title="translate">↜</button>
            <button v-if="this.ayatSearch == false" class="btn btn-primary" @click="this.ayatSearch = true" title="search ayat">🌟</button>
          </div>
        </div>
  
        <div class="contentDisplayCenter">
          <div class="form-outline mb-4">
            <textarea class="form-control" id="displayAyat" rows="3" :lang="detectLanguage()">{{ this.ayatBody }}</textarea>
          </div>    
          <div class="navigation">
             <button id="buttonPreviousAyat" class="btn btn-dark" @click="previousAyat()" :disabled="this.selectedAyat === 1 || buttonAyatPaging"> ⇐ </button>
             <button id="buttonNextAyat" class="btn btn-dark" @click="nextAyat()" :disabled="this.selectedAyat == this.surahAyat || buttonAyatPaging"> ⇒ </button>
             <audio controls>
              <source :src="`https://cdn.islamic.network/quran/audio-surah/128/ar.abdulazizazzahrani/${this.surahSelectedNumber}.mp3`" type="audio/mpeg">
              </audio> 
          </div>
  
        </div>
  </template>
  
  <script>
  import SurahSelection from './SurahSelection.vue';
  import axios from 'axios';
  
  export default {
    name: 'AyatContent',
    components:{
      SurahSelection
    },
    emits:['selectedSurahStatus'],
    data() {
      return {
        surahSelectedNumber: "", //
        surahSelectedName: "",  // Daten kommen von Json String Event aus SurahSelection Vue
        ayatBody: "",
        surahAyat: 0,
        selectedAyat: 1,
        ayatSearch: false,
        buttonAyatPaging: false,
        translation: false
      }
    },
    mounted() {
      this.consumeJsonSurah();
      this.displaySurahAyat();
    },
    methods:{
      displaySurahAyat(link){
        if(link == null){
          link = `http://api.alquran.cloud/v1/ayah/${this.surahSelectedNumber}:${this.selectedAyat}`
        }
        axios.get(link)
        .then(response => {
          this.ayatBody = response.data.data.text
        })
        .catch(error => {
          console.log(error);
          window.alert("ayat does not exists ore the service has a problem, please try it again")
          this.selectedAyat = 1
        });  
      },
      translate(){
        if(this.translation == false){
          this.displaySurahAyat(`http://api.alquran.cloud/v1/ayah/${this.surahSelectedNumber}:${this.selectedAyat}/tr.diyanet`)
          this.translation = true
        }
        else{
          this.translation = false
          this.displaySurahAyat()
        }
      },
      detectLanguage(){
        if(this.translation == true){
          return 'tr'
        }
        return 'ar'
      },
      searchAyat(){
        const ayat = document.getElementById('ayatSelector')
        this.selectedAyat = ayat.value
        this.ayatSearch = false
        this.displaySurahAyat()   
      },
      selectSurah(){
        this.$emit('selectedSurahStatus')
        this.ayatBody = " "
        this.selectedAyat = 1
      },
      previousAyat(){
        this.buttonAyatPaging = true
        setTimeout(() => {
          this.buttonAyatPaging = false
        }, 100)
  
        this.translation = false
        this.selectedAyat--
        this.displaySurahAyat();
      },
      nextAyat(){
        this.buttonAyatPaging = true
        setTimeout(() => {
          this.buttonAyatPaging = false
        }, 100)
  
        this.translation = false
        this.selectedAyat++
        this.displaySurahAyat();
      },
      consumeJsonSurah(){
        const cachedSurah = JSON.parse(localStorage.getItem('surahCache'));
        this.surahSelectedNumber = cachedSurah.numberSurah
        this.surahSelectedName = cachedSurah.nameSurah
        this.surahAyat = cachedSurah.ayatSurah
      }
    }
  }
  </script>
  <style>
  </style>