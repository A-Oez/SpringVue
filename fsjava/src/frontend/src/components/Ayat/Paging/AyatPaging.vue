<template>
    <div v-if="surahSelected == false">
      <SurahSelection @selectedSurahStatus="started"/>
    </div>
    <div v-if="surahSelected == true">
      <AyatContent @selectedSurahStatus="execute"/>
    </div>
</template>

<script>
import SurahSelection from './SurahSelection.vue';
import AyatContent from './AyatContent.vue';

export default {
  name: 'AyatPaging',
  components:{
    SurahSelection,
    AyatContent
  },
  data() {
    return {
      surahSelected: false,
      ayatSearch: false,
      translation: false
    }
  },
  methods:{
    started(){
      this.surahSelected = true;
    },
    execute(){
      this.surahSelected = false;
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

#displayAyat[lang="ar"]{
  font-size: 35px;
}
</style>