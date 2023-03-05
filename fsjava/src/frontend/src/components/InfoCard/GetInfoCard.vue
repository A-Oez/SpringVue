<template>
  <a v-if="this.json.length == 0"> no items!</a>
  <div>
    <ul v-for="task in itemsToShow">
      <div>
        <a id="contentValue">{{ task.value }}</a>
        <div class="selections">
          <select size="2" v-bind:id="task.ID">
            <option :value="true"  :selected="task.check == true"  @click="this.hasSelectedOption = true">succeeded</option>
            <option :value="false" :selected="task.check == false" @click="this.hasSelectedOption = true">failed</option>
          </select>
          <img class="deletionImage" @click="deleteCard(task.ID)" src="https://img.icons8.com/small/32/null/delete-forever.png" title="delete"/>
        </div>
      </div>
    </ul>
    <div class="navigation">
      <button class="btn btn-dark" @click="controlButtonMethods(itemsToShow,'previous')" :disabled="page === 0"> ⇐ </button>
      <button class="btn btn-dark" @click="controlButtonMethods(itemsToShow,'next')" :disabled="page === maxPage"> ⇒ </button>
    </div>
    <button class="btn btn-success" @click="checkValues(itemsToShow)" title="save">⇄</button>
    <button v-if="this.hasSelectedOption == true" class="btn btn-danger" @click="resetChanges(itemsToShow)" title="reset">↶</button>
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
      itemsPerPage: 3,
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
    controlButtonMethods(arr,type){
      if(this.checkSelectedOptions(arr) == true){
        
        if(type == "next"){
          this.nextPage();
        }
        else{
          this.previousPage()
        }
        
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
@import 'https://fonts.googleapis.com/css?family=Playfair+Display:400,700i,900i';

#contentValue{
  font-size: 19.5px;
  font-family: Avenir, Helvetica, Arial, sans-serif;
}

.navigation {
  display: flex;
  justify-content: center;
  align-items: center;
}

select{
  width: 60%;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: #273036;
  font-family: Avenir, Helvetica, Arial, sans-serif;
}

select option{
  color: #d1c9ca;
}

select:focus {
  outline: none;
}

select option:checked {
  background-color: #d1c9ca;
  color: #000;
}

.deletionImage:hover{
  background-color: #d1c9ca;
}

.deletionImage{
  padding: 3%;
  vertical-align: middle;
  margin-top: -36px;
}


</style>