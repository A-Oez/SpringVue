<template>
    <a id="headerText">{{ this.ayatBody }}</a>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AyatGenerator',
  data() {
    return {
        arrSurah:[],
        ayatBody:''
    }
  },
  mounted() {
    this.getSurahs();
    this.randomGenerator();
  },
  methods:{
    getSurahs(){
      if(this.arrSurah.length == 0){
        const configJson = require('./SurahMapping.json');
        for(let i = 0; i < 114; i++){
            const surahObject = { number: configJson.Surah[i].Number, numberAyahs: configJson.Surah[i].NumberOfAyahs};   
            this.arrSurah.push(surahObject)
        }
      }
    },
    randomGenerator(){
        let randomValue = Math.floor(Math.random() * 114);
        const choosedSurahObject = this.arrSurah[randomValue] 
        let randomAyatValue = Math.floor(Math.random() * choosedSurahObject.numberAyahs) + 1 //+1 to prevent 0 as random number
        this.displaySurahAyat(choosedSurahObject.number,randomAyatValue)
    },
    displaySurahAyat(surah,ayat){
        axios.get(`http://api.alquran.cloud/v1/ayah/${surah}:${ayat}/en.asad`)
        .then(response => {
            const content = response.data.data.text + ` | ${surah}:${ayat}`
            this.checkContentLength(content)
        })
        .catch(error => {
            console.log(error);
        }); 
    },
    checkContentLength(body){
        if(body.length > 150){
            this.randomGenerator()
        }
        else{
            this.ayatBody = body
        }
    }   
  }
}
</script>