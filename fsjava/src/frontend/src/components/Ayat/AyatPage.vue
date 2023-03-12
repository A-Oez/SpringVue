<template>
    <div v-if="ayatSelected == false" class="navigation">
      <button class="btn btn-dark" @click="previousPageSurah()" :disabled="pageSurah === 0"> ⇐ </button>
      <button class="btn btn-dark" @click="nextPageSurah()" :disabled="pageSurah === maxPageSurah"> ⇒ </button>
    </div>
    <select v-if="ayatSelected == false" id="surahSelector" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" @change="getSelectedSurah()">
      <option>-</option>  
      <option v-for="surah in itemsToShowSurah" v-bind:id="surah.number">{{ surah.number }} ~ {{ surah.name }}</option>
    </select>

    <div v-if="surahSelected == true" class="surahContent">
      <a style="text-align: left;">Select Ayat: ({{ this.surahSelectedNumber }}:Ayat)</a>
      <textarea id="ayatSelector">{{this.surahSelectedNumber}}:</textarea>
      <button @click="displaySurah(message)">SELECT</button>
      <button @click="selectSurah()">SELECTSURAH</button>
      <p>Ayat: {{ this.surahAyat }}</p>
      <textarea v-if="ayatSelected == true"> {{ this.surahBody }}</textarea>
    </div>

</template>

<script>
import axios from 'axios';

export default {
  name: 'AyatPage',
  data() {
    return {
      //SurahSelector
      arrSurah:[],
      surahSelectedNumber:0,
      itemsPerPageSurah: 19,
      pageSurah: 0,
      surahSelected: false,
      surahBody: "",
      
      //AyatSelector
      surahAyat: 0,
      ayatSelected: false,
    }
  },
  mounted() {
    this.getSurahs();
  },
  computed: {
    maxPageSurah() {
      return Math.ceil(this.arrSurah.length / this.itemsPerPageSurah) - 1;
    },
    itemsToShowSurah() {
      const startIndex = this.pageSurah * this.itemsPerPageSurah;
      return this.arrSurah.slice(startIndex, startIndex + this.itemsPerPageSurah);
    }
  },
  methods:{
    getSurahs(){
      const configJson = require('./SurahMapping.json');
      for(let i = 0; i < 114; i++){
        const surahObject = { name: configJson.Surah[i].Name, number: configJson.Surah[i].Number, numberAyahs: configJson.Surah[i].NumberOfAyahs};   
        this.arrSurah.push(surahObject)
      }
    },
    getSelectedSurah(){
      this.surahSelected = true;
      this.ayatSelected = true;

      const selectedSurah = document.getElementById('surahSelector')
      const surahNumber = selectedSurah.value.split('~')
      this.surahSelectedNumber = surahNumber[0].replace(/\s+/g, '')

      const filterSurahArr = this.arrSurah.filter(x => x.number == this.surahSelectedNumber)
      this.surahAyat = filterSurahArr[0].numberAyahs
    },
    displaySurah(){
      const ayat = document.getElementById('ayatSelector')
      const body = ayat.value
      const splittedBody = body.split(':')
      
      if(splittedBody[0] == this.surahSelectedNumber){
        axios.get(`http://api.alquran.cloud/v1/ayah/${body}/en.asad`)
        .then(response => {
          this.surahBody = response.data.data.text
        })
        .catch(error => {
          console.log(error);
        });  
      }
      else{
        window.alert('surah not equal to selected surah (' + this.surahSelectedNumber + ')' + 'ore the input has the wrong format')
      }
      
    },
    selectSurah(){
      this.surahSelected = false;
      this.ayatSelected = false;
      this.surahBody = " "
    },
    previousPageSurah() {
      this.pageSurah--;
    },
    nextPageSurah() {
      this.pageSurah++;
    }
  }
}
</script>