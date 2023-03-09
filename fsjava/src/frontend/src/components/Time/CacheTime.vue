<script>
    export default {
    name: 'CacheTime',

    data(){
        return{
        data: null   
        }
    },
    created(){
        this.fetchData();
        this.updataData();
    },
    methods:{
        async fetchData(){
            const cachedData = localStorage.getItem('dummyCache');

            if(!cachedData){
                this.setData();
            }
        },
        async updataData(){
            let date = new Date().getDate();
            const cacheDate = JSON.parse(localStorage.getItem('dummyCache'));
            
            if(date != cacheDate.createdAt){
                this.setData();
            }
        },
        setData(){
            fetch("/api/prayers/time")
                .then((response) => response.text())
                .then((data) => {
                    let jsonData = JSON.parse(data);
                    localStorage.setItem('dummyCache', JSON.stringify({ value: '', createdAt: new Date().getDate() }));
                    localStorage.setItem('timeSunrise', JSON.stringify({ value: jsonData.sunrise, createdAt: new Date().getDate() }));
                    localStorage.setItem('timeMorning', JSON.stringify({ value: jsonData.morning, createdAt: new Date().getDate() }));
                    localStorage.setItem('timeNoon', JSON.stringify({ value: jsonData.noon, createdAt: new Date().getDate() }));
                    localStorage.setItem('timeAfternoon', JSON.stringify({ value: jsonData.afternoon, createdAt: new Date().getDate() }));
                    localStorage.setItem('timeEvening', JSON.stringify({ value: jsonData.evening, createdAt: new Date().getDate() }));
                    localStorage.setItem('timeNight', JSON.stringify({ value: jsonData.night, createdAt: new Date().getDate() }));
                }) 
        }

    }

}

</script>