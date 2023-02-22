<template>
  <ul v-for="task in json">
    {{ task.value }}| Check: {{ task.check }}
    <select size="2" v-bind:id="task.ID">
      <option>true</option>
      <option>false</option>
    </select>
  </ul>

  <button @click="getJSONTEST()">SAVE</button>
</template>

<script>

export default {
  name: 'GetInfoCard',
  props: {
    cardType: String
  },
  data() {
    return {
      json: '',
    }
  },
  mounted() {      
    this.getWeather();
  },
  methods:{
    async getWeather(){
        fetch(`/api/infocard/getData`)
        .then(response => response.text())
        .then(data => {       
          const jsonData = JSON.parse(data);
          this.json = jsonData;
        }); 
    },
    getJSONTEST(){
      let counter = 0;
      for (let i = 0; i <= this.json.length - 1; i++) {
        const select = document.getElementById(this.json[i].ID)

        if(select.value != this.json[i].check.toString()){
          this.postData(this.json[i].ID, select.value)
        }
        else{counter++}
      }

      if(counter == this.json.length){window.alert("no data to update!")}
    },
    async postData(ID, check){
      console.log("UNGLEICH")
    }
  }
}
</script>

<style>

ul{
  color: black;
}


</style>