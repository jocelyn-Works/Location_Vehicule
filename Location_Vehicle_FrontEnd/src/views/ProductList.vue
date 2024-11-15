<script>
  import Product from "@/components/Product.vue";

  export default {
    components: {Product},
    name: "ProductList",
    mounted: function() {
      this.getData()
    },
    data(){
      return {
        products: [],
        loading: false,
        error: null,
      };
    },
    methods: {
      getData() {
        this.loading = true;

        fetch("http://127.0.0.1:8080/api/vehicle")
            .then(res => res.json())
            .then(data => this.products = data, this.loading = false)
            .then(log => console.log(this.products))
            .catch(err => this.error = err);
      }
    }
  }
</script>

<template>
  <section class="ProductListTop">
    <div class="blur">
      <h3 class="textTop">Venez decouvrire le plus beau et abordable des vehicules du marchés</h3>
    </div>
  </section>

  <section class="ProductListMain">
    <h3 class="categoryTitle">Nos voitures</h3>
    <div v-for="(data) in products">
      <Product v-if="data.vehicleType === 'voiture'" v-bind="data"/>
    </div>
    <h3 class="categoryTitle">Nos Vehicules utilitaires</h3>
    <div v-for="(data) in products">
      <Product v-if="data.vehicleType === 'utilitaire'" v-bind="data"/>
    </div>
    <h3 class="categoryTitle">Nos motos</h3>
    <div v-for="(data) in products">
      <Product v-if="data.vehicleType === 'moto'" v-bind="data"/>
    </div>
  </section>
</template>

<style scoped>
.ProductListTop{
  position: relative;
  height: 90vh;
  background-image: url("./images/productlisttop.avif");
  background-size: cover;
  z-index: 0;
}
.blur{
  position: relative;
  height: 90vh;
  background-color: rgba(0,0,0,0.4);
  z-index: 1;
}
.textTop{
  color: white;
  font-size: 3.5rem;
  margin-left: 3%;
  padding-top: 70vh;
}
.ProductListMain{
  background: linear-gradient(
      45deg,
      #999 5%,
      #fff 10%,
      #ccc 30%,
      #ddd 50%,
      #ccc 70%,
      #fff 80%,
      #999 95%
  );
  padding-left: 15%;
  padding-right: 15%;
}
.categoryTitle{
  padding-top: 2%;
  margin-bottom: 0.5%;
  text-align: center;
  font-size: 3rem;
}
</style>