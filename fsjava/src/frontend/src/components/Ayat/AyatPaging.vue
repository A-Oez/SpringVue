<template>
    <div v-if="ayatSelected == false" class="surahSelection">
      <h3>Surah:</h3>
      <select id="surahSelector" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" @change="getSelectedSurah()">
         <option>-</option>  
         <option v-for="surah in itemsToShowSurah" v-bind:id="surah.number">{{ surah.number }} ~ {{ surah.name }} ({{ surah.nameTranslated }})</option>
      </select>
      <div class="navigation">
        <button class="btn btn-dark" @click="previousPageSurah()" :disabled="pageSurah === 0"> ⇐ </button>
        <button class="btn btn-dark" @click="nextPageSurah()" :disabled="pageSurah === maxPageSurah"> ⇒ </button>
      </div>
    </div>

    <div v-if="surahSelected == true" class="surahContent">
      <h3>{{ this.surahSelectedName }}</h3>
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
          <button class="btn btn-primary" title="translate" @click="translateAyat()">↺</button>
          <button v-if="this.ayatSearch == false" class="btn btn-success" @click="this.ayatSearch = true" title="search ayat">🌟</button>
        </div>
      </div>

      <div class="contentDisplayCenter">
        <div v-if="ayatSelected == true" class="form-outline mb-4">
          <textarea class="form-control" id="textAreaExample6" rows="6">{{ this.ayatBody }}</textarea>
        </div>    
        <div v-if="ayatSelected == true" class="navigation">
           <button id="buttonPreviousAyat" class="btn btn-dark" @click="previousAyat()" :disabled="this.selectedAyat === 1 || buttonDisabled"> ⇐ </button>
           <button id="buttonNextAyat" class="btn btn-dark" @click="nextAyat()" :disabled="this.selectedAyat == this.surahAyat || buttonDisabled"> ⇒ </button>
        </div>
      </div>
    </div>

</template>

<script>
import axios from 'axios';

export default {
  name: 'AyatPaging',
  data() {
    return {
      //SurahSelector
      arrSurah:[],
      surahSelectedNumber:0,
      surahSelectedName: "",
      itemsPerPageSurah: 19,
      pageSurah: 0,
      surahSelected: false,
      ayatBody: "",
      
      //AyatSelector
      surahAyat: 0,
      ayatSelected: false,
      selectedAyat: 1,
      ayatSearch: false,
      buttonDisabled: false
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
      if(this.arrSurah == 0){
        const configJson = require('./SurahMapping.json');
        for(let i = 0; i < 114; i++){
          const surahObject = { name: configJson.Surah[i].Name, number: configJson.Surah[i].Number, numberAyahs: configJson.Surah[i].NumberOfAyahs, nameTranslated: configJson.Surah[i].NameTranslated};   
          this.arrSurah.push(surahObject)
        }
      }
    },
    getSelectedSurah(){
      this.surahSelected = true;

      const selectedSurah = document.getElementById('surahSelector')
      const surahNumber = selectedSurah.value.split('~')
      this.surahSelectedNumber = surahNumber[0].replace(/\s+/g, '')
      this.surahSelectedName = surahNumber[1]

      const filterSurahArr = this.arrSurah.filter(x => x.number == this.surahSelectedNumber)
      this.surahAyat = filterSurahArr[0].numberAyahs
      this.displaySurahAyat()
    },
    displaySurahAyat(){
      this.ayatSelected = true
      axios.get(`http://api.alquran.cloud/v1/ayah/${this.surahSelectedNumber}:${this.selectedAyat}/en.asad`)
      .then(response => {
        this.ayatBody = response.data.data.text
      })
      .catch(error => {
        console.log(error);
        window.alert("ayat does not exists ore the service has a problem, please try it again")
        this.selectedAyat = 1
      });  
    },
    searchAyat(){
      const ayat = document.getElementById('ayatSelector')
      this.selectedAyat = ayat.value
      this.ayatSearch = false
      this.displaySurahAyat()   
    },
    translateAyat(){

    },
    selectSurah(){
      this.surahSelected = false;
      this.ayatSelected = false;
      this.ayatBody = " "
      this.selectedAyat = 1
    },
    previousPageSurah() {
      this.pageSurah--;
    },
    nextPageSurah() {
      this.pageSurah++;
    },
    previousAyat(){
      this.buttonDisabled = true
      setTimeout(() => {
        this.buttonDisabled = false
      }, 1400)

      this.selectedAyat--
      this.displaySurahAyat();
    },
    nextAyat(){
      this.buttonDisabled = true
      setTimeout(() => {
        this.buttonDisabled = false
      }, 1400)

      this.selectedAyat++
      this.displaySurahAyat();
    }
  }
}
</script>
<style>
.contentInfoText{
  display: flex; 
  flex-direction: column; 
  align-items: flex-start;
}

#maxAyatDisplay,#selectedAyatCounterDisplay{
  margin: 0;
}

.contentInfoButtons{
  display: flex;
  justify-content: right;
  align-items: right;
}
</style>