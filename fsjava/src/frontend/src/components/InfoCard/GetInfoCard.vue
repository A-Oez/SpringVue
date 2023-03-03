<template>
  <div>
    <ul v-for="task in itemsToShow">
      <div>
        <a style="font-size: 16px;">{{ task.value }}</a>
        <div>
          <select size="2" v-bind:id="task.ID">
            <option :value="true" :selected="task.check == true" style="color: green;" @click="this.hasSelectedOption = true">succeeded</option>
            <option :value="false" :selected="task.check == false" style="color: red;" @click="this.hasSelectedOption = true">failed</option>
          </select>
          <img class="deletionImage" @click="deleteCard(task.ID)" src="https://img.icons8.com/small/32/null/delete-forever.png"/>
        </div>
      </div>
    </ul>
    <div>
      <button @click="previousPage" :disabled="page === 0">Previous</button>
      <button @click="nextButtonMethods(itemsToShow)" :disabled="page === maxPage">Next</button>
    </div>
    <button @click="checkValues(itemsToShow)">SAVE</button>
    <button v-if="this.hasSelectedOption == true" @click="resetChanges(itemsToShow)">RESET CHANGES</button>
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
      hasSelectedOption: false,
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
      this.hasSelectedOption = false;
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
    getCards(){
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
    deleteCard(cardID){
      axios.post('/api/infocard/deleteData', cardID, {
        headers: {
          'Content-Type': 'text/plain'
        }
      })
      .then(response => {
        location.replace(location.href);
      })
      .catch(error => {
        console.log(error);
      });
    },
    nextButtonMethods(arr){
      if(this.checkSelectedOptions(arr) == true){
        this.nextPage();
      }     
    },
    checkSelectedOptions(arr){
      for (let i = 0; i <= arr.length - 1; i++) {
        const select = document.getElementById(arr[i].ID)
        if(select.value.toString() != arr[i].check.toString()){
          this.hasSelectedOption = true
        }
      }

      if(this.hasSelectedOption == true){
        window.alert("save the changes before paging the content")
        return false;
      }

      return true;
    },
    resetChanges(arr){
      for (let i = 0; i <= arr.length - 1; i++) {
        const select = document.getElementById(arr[i].ID)
        select.value = arr[i].check
      }
      this.hasSelectedOption = false;
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
  width: 80%;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}

.deletionImage:hover{
  background-color: #d1c9ca;
}

</style>