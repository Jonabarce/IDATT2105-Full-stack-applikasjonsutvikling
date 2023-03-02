<template>
    <div class="TopSite">
        <h1>Contact me</h1>
        <button @click="changeRoute('HomePage')">Home</button>
        <button @click="changeRoute('CalculatorPage')">Calculator</button>
    </div>
    
    
    
    
    
    </template>
    
<script>

export default {
    methods: {
        changeRoute(string){
            this.$router.push({name:string})
        }
    }
}


</script>  
    
    
<style scoped>
    
    
    .TopSite{
        align-items: center;
    }

    h1{
        color: whitesmoke;
        font-size: 60px;
    }

    button{
        background-color:white;
        color: black;
        width: 200px;
        height: 40px;
        font-size: 20px;
    }
    
    
</style>