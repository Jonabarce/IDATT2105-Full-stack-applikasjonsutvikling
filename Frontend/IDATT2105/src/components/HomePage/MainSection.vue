<template>
    <div class="Main">
    <p>
        Welcome to the Calculator <br>
        To start calculation press the button below <br>
    </p>
    <button @click="changeRoute('CalculatorPage')">Start</button>
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
    
    
    .Main{
        align-items: center;
        background-color: black;
    }

    p{
    font-size: 40px;
    color: whitesmoke;
}

    button{
        width: 120px;
        height: 40px;
        font-size: 20px;
    }
    
    
    
    
    </style>