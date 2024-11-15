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
  <section>
    <h3>Voiture</h3>
    <div v-for="(data) in products">
      <Product v-if="data.vehicleType === voiture" v-bind="data"/>
    </div>
    <h3>Utilitaire</h3>
    <div v-for="(data) in products">
      <Product v-if="data.vehicleType === utilitaire" v-bind="data"/>
    </div>
    <h3>Moto</h3>
    <div v-for="(data) in products">
      <Product v-if="data.vehicleType === moto" v-bind="data"/>
    </div>
  </section>
</template>

<style scoped>

</style>