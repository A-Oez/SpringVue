<template>
    <a> {{ msg }}</a>
</template>

<script>
    export default {
    name: 'GetPrayersTime',

    props: {
        timeType: String
    },

    data(){
        return{
        msg: ''   
        }
    },
    mounted(){
        fetch("/api/prayers/time")
        .then((response) => response.text())
        .then((data) => {
            let jsonData = JSON.parse(data);

            if(this.timeType == "sunrise")  {this.msg = jsonData.sunrise;}
            if(this.timeType == "morning")  {this.msg = jsonData.morning;}
            if(this.timeType == "noon")     {this.msg = jsonData.noon;}
            if(this.timeType == "afternoon"){this.msg = jsonData.afternoon;}
            if(this.timeType == "evening")  {this.msg = jsonData.evening;}
            if(this.timeType == "night")    {this.msg = jsonData.night;}
        })
    }
    }
</script>