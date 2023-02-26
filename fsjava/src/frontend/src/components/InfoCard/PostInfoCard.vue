<template>
    <h3 style="color: black;">{{ this.cardType }} | To-do</h3>
    <textarea maxlength="50" v-model="message"></textarea>
    <button v-if="message.length != 0" @click="postToDoMessage(message)">SAVE</button>
</template>
  
<script>  
import axios from 'axios';

  export default {
    name: 'PostInfoCard',
    props: {
        cardType: String
    },
    data() {
        return {
           message: "",
        };
    },
    watch: {
        cardType: function (newVal) {}
    },
    methods:{
        postToDoMessage(body){
            const infoCard = {type: this.cardType, value: body, check: false};   
            const jsonString = JSON.stringify(infoCard);

            axios.post('/api/infocard/postData', jsonString, {
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
        }
    }
  }

</script>
  
<style>
textarea{
    width: 100%;
}
</style>