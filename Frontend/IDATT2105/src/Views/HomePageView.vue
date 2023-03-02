<template>
<div class ="background">
    <div class="website">
        <footer-section></footer-section>
        <main-section></main-section>
        <TopSiteSection></TopSiteSection>
</div>



</div>



</template>




<script>


import MainSection from '../components/HomePage/MainSection.vue';
import TopSiteSection from '../components/HomePage/TopSiteSection.vue';
import FooterSection from '../components/HomePage/FooterSection.vue';


export default{
    name: 'HomePageView',
    components: {
        MainSection,
        TopSiteSection,
        FooterSection
    },
};

</script>




<style scoped>

.background{
    background-color: black;
    width: 100%;
    height: 100vh;
}


.website {
  position: relative;
  padding: 25px;
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-rows: 1fr 3fr 2fr;
  grid-template-areas: 
    "TopSite TopSite TopSite"
    "Main Main Main"
    "Footer Footer  Footer";
  row-gap: 20px;
}


.TopSite{
    grid-area: TopSite;
}

.Main{
    grid-area: Main;
}

.Footer{
    grid-area: Footer;
}


</style>