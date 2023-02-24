<template>
  <ul v-for="task in json">
    <div>
      <a style="font-size: 16px;">{{ task.value }}</a>
      <select size="2" v-bind:id="task.ID">
        <option :value="true" :selected="task.check == true" style="color: green;">true</option>
        <option :value="false" :selected="task.check == false" style="color: red;">false</option>
      </select>
    </div>
  </ul>

  <button @click="checkValues()">SAVE</button>
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
      updatedProp: false,
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
  methods:{
    async getCards(){
        fetch(`/api/infocard/getData`)
        .then(response => response.text())
        .then(data => {       
          const jsonData = JSON.parse(data);
          console.log(this.cardType)
          const filtered = jsonData.filter(x => x.type == this.cardType)
          this.json = filtered;
        }); 
    },
    checkValues(){
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

select{
  margin-left: 1%;
}
</style>