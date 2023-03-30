<template>
    <div class="surahSelection">
      <h3>Surah:</h3>
      <select id="surahSelector" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" @change="getSelectedSurah()">
         <option>-</option>  
         <option v-for="surah in itemsToShowSurah" v-bind:id="surah.number">{{ surah.number }} ~ {{ surah.name }}</option>
      </select>
      <div class="navigation">
        <button class="btn btn-dark" @click="previousPageSurah()" :disabled="pageSurah === 0"> ⇐ </button>
        <button class="btn btn-dark" @click="nextPageSurah()" :disabled="pageSurah === maxPageSurah"> ⇒ </button>
      </div>
    </div>
</template>

<script>
import mitt from 'mitt';

export default {
  name: 'SurahSelection',
  data() {
    return {
      //SurahSelector
      arrSurah:[],
      itemsPerPageSurah: 19,
      pageSurah: 0,
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
        const configJson = require('/C:/Users/Ahmet Ö/Coding/fsjava/fsjava/src/frontend/src/components/Ayat/SurahMapping.json');
        for(let i = 0; i < 114; i++){
          const surahObject = { name: configJson.Surah[i].Name, number: configJson.Surah[i].Number, numberAyahs: configJson.Surah[i].NumberOfAyahs};   
          this.arrSurah.push(surahObject)
        }
      }
    },
    getSelectedSurah(){
      this.surahSelected = true;

      const selectedSurah = document.getElementById('surahSelector')
      const surahData = selectedSurah.value.split('~')
      const surahSelectedNumber = surahData[0].replace(/\s+/g, '')
      const surahSelectedName = surahData[1]

      const filterSurahArr = this.arrSurah.filter(x => x.number == surahSelectedNumber)
      const surahAyat = filterSurahArr[0].numberAyahs
      this.createJsonData(this.surahSelected, surahSelectedNumber,surahSelectedName,surahAyat)
      const emitter = mitt();
      emitter.emit('surahSelectedEvent', this.surahJsonData);
    },
    createJsonData(selected,number,name,ayat){
      const surah = {selectedSurah: selected, numberSurah: number, nameSurah: name, ayatSurah: ayat};   
      this.surahJsonData = JSON.stringify(surah);
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
<style>
</style>