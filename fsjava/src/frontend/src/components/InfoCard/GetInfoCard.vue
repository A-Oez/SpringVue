<template>
  <div>
    <ul v-for="task in itemsToShow">
      <div>
        <a style="font-size: 16px;">{{ task.value }}</a>
        <select size="2" v-bind:id="task.ID">
          <option :value="true" :selected="task.check == true" style="color: green;">succeeded</option>
          <option :value="false" :selected="task.check == false" style="color: red;">failed</option>
        </select>
      </div>
    </ul>
    <div>
      <button @click="previousPage" :disabled="page === 0">Previous</button>
      <button @click="nextPage" :disabled="page === maxPage">Next</button>
    </div>
    <button @click="checkValues(itemsToShow)">SAVE</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'GetInfoCard',
  props: {
    cardType: String
  },
  data() {
    return {
      json: [],
      updatedProp: false,
      itemsPerPage: 5,
      page: 0,
    }
  },
  mounted() { 
    if(this.updatedProp == false){
      this.getCards();  
    }
  },
  watch: {
    cardType: function (newVal) {
      this.getCards();
      this.updatedProp = true;
    }
  },
  computed: {
    maxPage() {
      return Math.ceil(this.json.length / this.itemsPerPage) - 1;
    },
    itemsToShow() {
      const startIndex = this.page * this.itemsPerPage;
      return this.json.slice(startIndex, startIndex + this.itemsPerPage);
    },
  },
  methods:{
    async getCards(){
        fetch(`/api/infocard/getData`)
        .then(response => response.text())
        .then(data => {       
          const jsonData = JSON.parse(data);
          const filtered = jsonData.filter(x => x.type == this.cardType)
          this.json = filtered;
        }); 
    },
    checkValues(arr){
      let counter = 0;
      for (let i = 0; i <= arr.length - 1; i++) {
        const select = document.getElementById(arr[i].ID)

        if(select.value != arr[i].check.toString()){
          this.postData(arr[i].ID, select.value, arr[i].value)
        }
        else{counter++}
      }

      if(counter == arr.length){window.alert("no data to update!")}
    },
    postData(ID, check, content){
      const infoCard = { ID: ID, type: this.cardType, value: content, check: check};   
      const jsonString = JSON.stringify(infoCard);

      axios.post('/api/infocard/updateData', jsonString, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        location.replace(location.href);
      })
      .catch(error => {
        console.log(error);
      });
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

<style>
ul{
  color: black;
}

select{
  width: 100%;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}
</style>