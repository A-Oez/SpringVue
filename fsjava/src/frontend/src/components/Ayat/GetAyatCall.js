import axios from 'axios';

export default {
    displaySurahAyat(link,surah,ayat){
        if(link == null){
          link = `http://api.alquran.cloud/v1/ayah/${surah}:${ayat}`
        }
        axios.get(link)
        .then(response => {
          return response.data.data.text
        })
        .catch(error => {
          console.log(error);
          window.alert("ayat does not exists ore the service has a problem, please try it again")
          this.selectedAyat = 1
        });  
      }
  }