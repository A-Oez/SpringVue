<template>
    <p> {{ msg }}</p>
</template>

<script>
    export default {
    name: 'GetTime',

    data(){
        return{
            msg: ''   
        }
    },
    mounted() {
        this.updateTime();
        setInterval(() => {
            this.updateTime();
        }, 1000);
    },
    methods:{
        updateTime(){
            let time = new Date();
            this.msg = time.toLocaleTimeString();
        }
    }
    }
</script>