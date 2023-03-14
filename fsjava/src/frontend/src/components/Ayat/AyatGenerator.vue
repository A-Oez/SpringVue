<template>
   

</template>

<script>
import axios from 'axios';

export default {
  name: 'AyatGenerator',
  data() {
    return {
        arrSurah:[]
    }
  },
  mounted() {
    this.getSurahs();
  },
  methods:{
    getSurahs(){
      const configJson = require('./SurahMapping.json');
      for(let i = 0; i < 114; i++){
        const surahObject = { number: configJson.Surah[i].Number, numberAyahs: configJson.Surah[i].NumberOfAyahs};   
        this.arrSurah.push(surahObject)
      }
    }
  }
}
</script>