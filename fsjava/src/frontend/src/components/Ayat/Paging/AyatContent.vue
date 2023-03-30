<template>
      <div v-if="surahSelected == true" class="surahContent">
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
          <div v-if="surahSelected == true" class="form-outline mb-4">
            <textarea class="form-control" id="displayAyat" rows="3" :lang="detectLanguage()">{{ this.ayatBody }}</textarea>
          </div>    
          <div v-if="surahSelected == true" class="navigation">
             <button id="buttonPreviousAyat" class="btn btn-dark" @click="previousAyat()" :disabled="this.selectedAyat === 1 || buttonAyatPaging"> ⇐ </button>
             <button id="buttonNextAyat" class="btn btn-dark" @click="nextAyat()" :disabled="this.selectedAyat == this.surahAyat || buttonAyatPaging"> ⇒ </button>
             <audio controls>
              <source :src="`https://cdn.islamic.network/quran/audio-surah/128/ar.abdulazizazzahrani/${this.surahSelectedNumber}.mp3`" type="audio/mpeg">
              </audio> 
          </div>
  
        </div>
      </div>
  </template>
  
  <script>
  import axios from 'axios';
  import SurahSelection from './SurahSelection.vue';
  import mitt from 'mitt';

  
  export default {
    name: 'AyatContent',
    components:{
      SurahSelection
    },
    data() {
      return {
        surahSelectedNumber: 0, //
        surahSelectedName: "",  // Daten kommen von Json String Event aus SurahSelection Vue
        surahSelected: false,   //
        ayatBody: "",
        surahAyat: 0,
        selectedAyat: 1,
        ayatSearch: false,
        buttonAyatPaging: false,
        translation: false
      }
    },
    mounted() {
      //this.displaySurahAyat();
      const emitter = mitt();
      emitter.on('surahSelectedEvent', (surahData) => {
        console.log(surahData);
      });
    },
    methods:{
      displaySurahAyat(link){
        this.ayatBody = GetAyatCall.displaySurahAyat(null,this.surahSelectedNumber,this.selectedAyat)
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
      selectSurah(){
        this.surahSelected = false;
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
      }
    }
  }
  </script>
  <style>
  </style>