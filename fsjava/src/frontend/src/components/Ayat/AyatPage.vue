<template>
    <div class="navigation">
      <button class="btn btn-dark" @click="previousPage()" :disabled="page === 0"> ⇐ </button>
      <button class="btn btn-dark" @click="nextPage()" :disabled="page === maxPage"> ⇒ </button>
    </div>
    <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
        <option v-for="surah in itemsToShow" v-bind:id="surah.Number">{{surah.Name}}</option>
    </select>
</template>

<script>
export default {
  name: 'AyatPage',
  data() {
    return {
      arrSurah:[],
      itemsPerPage: 19,
      page: 0
    }
  },
  mounted() {
    this.getSurahs();
  },
  computed: {
    maxPage() {
      return Math.ceil(this.arrSurah.length / this.itemsPerPage) - 1;
    },
    itemsToShow() {
      const startIndex = this.page * this.itemsPerPage;
      return this.arrSurah.slice(startIndex, startIndex + this.itemsPerPage);
    }
  },
  methods:{
    getSurahs(){
      const configJson = require('./SurahMapping.json');
      for(let i = 0; i < 114; i++){
        const surahObject = { Name: configJson.Surah[i].Name, Number: configJson.Surah[i].Number};   
        this.arrSurah.push(surahObject)
      }
    },
    previousPage() {
      this.page--;
    },
    nextPage() {
      this.page++;
    }
  }
}
</script>