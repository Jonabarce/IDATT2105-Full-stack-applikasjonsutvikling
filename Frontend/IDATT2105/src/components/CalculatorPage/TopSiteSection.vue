<template>
    <div class="TopSite">
        <h1>Calculator</h1>
        <button @click="changeRoute('HomePage')">Home</button>
        <button @click="changeRoute('ContactPage')">Contact</button>
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

button{
    width: 200px;
    height: 40px;
    font-size: 30px;
    color: black;
}

h1{
    color: white;
    font-size: 50px;
}
    
    
</style>