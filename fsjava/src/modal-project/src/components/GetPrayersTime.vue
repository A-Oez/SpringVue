<template>
    <a> {{ time }}</a>
</template>

<script>
    export default {
    name: 'GetPrayersTime',

    props: {
        timeCacheKey: String
    },

    data(){
        return{
        time: ''   
        }
    },
    mounted(){
        const cacheTime = JSON.parse(localStorage.getItem(this.timeCacheKey));
        this.time = cacheTime.value; 
    }
    }
</script>