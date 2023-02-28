<template>
    <p> {{ succeededCards }}  | {{ maxCards }}</p>
</template>
  
<script>  
  export default {
    name: 'InfoCardCounter',
    props: {
        cardType: String
    },
    data() {
        return {
           maxCards: 0,
           succeededCards: 0,
        };
    },
    mounted() { 
      this.getCards();  
    },
    methods:{
        getCards(){
            fetch(`/api/infocard/getData`)
            .then(response => response.text())
            .then(data => {       
                const jsonData = JSON.parse(data)
                const filtered = jsonData.filter(x => x.type == this.cardType)
                this.maxCards = filtered.length

                const succeded = filtered.filter(x => x.check == true)
                this.succeededCards = succeded.length
             }); 
         }
    }
  }

</script>
  
<style>
textarea{
    width: 100%;
}
</style>