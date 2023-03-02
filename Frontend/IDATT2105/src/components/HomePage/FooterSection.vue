<template>
<div class="Footer">
<p>Any suggestions?<br>
    Contact me by pressing the button below <br>
</p>
<button @click="changeRoute('ContactPage')">Press me :)</button>
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


.Footer{
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