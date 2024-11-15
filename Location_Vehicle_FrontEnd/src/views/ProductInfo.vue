<script>
import product from "../components/Product.vue";

export default {
  name: "ProductInfo",
  computed: {
    product() {
      return product
    }
  },
  mounted:function () {
    this.getData()
  },
  data(){
    return {
      product: {},
      loading: false,
      error: null,
    };
  },
  methods:{
    getData(){
      this.loading = true;

      fetch(`http://127.0.0.1:8080/api/vehicle/${this.$route.params.id}`)
          .then(response => response.json())
          .then(data => this.product = data, this.loading = false)
          .then(log => console.log(log))
          .catch(err => this.error = err)
    }
  }
}
</script>

<template>
  <section class="productInfo">
    <div class="productFiche">
      <div class="productNameInfos">
        <div>{{product.vehicleRegistration}}</div>
        <div>{{product.vehicleType}}</div>
      </div>
      <div class="productModelInfos">
        <div>Marque : {{product.brand}}</div>
        <div>Model :  {{product.model}}</div>
        <div>Couleur : {{product.color}}</div>
      </div>
      <div class="productData">
        <div>Kilometrage : {{product.traveledKm}}</div>
        <div v-if="product.vehicleType==='moto'">Cylindre : {{product.cylinder}}</div>
        <div v-if="product.vehicleType==='utilitaire'">Volume : {{product.volume}}</div>
      </div>
      <div class="productPrice">
        <div>Prix au kilometre : {{product.kmPrice}} €</div>
        <div>Prix de la reservation : {{product.reservationPrice}}</div>
        <div>Chevaux fiscaux : {{product.taxHorse}}</div>
      </div>

    </div>
  </section>
</template>

<style scoped>
.productInfo{
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
  height: 100vh;
  padding-top: 3%;
  padding-bottom: 9%;
}
.productFiche{
  margin-right: 15%;
  margin-left: 15%;
  padding: 3%;
  height: 100%;
  background-color: white;
  border-radius: 15px;
}
</style>