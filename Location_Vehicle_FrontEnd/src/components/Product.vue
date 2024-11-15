<script>
import BtnAdd from "@/components/BtnAdd.vue";

export default {
  components: {BtnAdd},
  name: 'Product',
  props: {
    id: Number,
    brand: String,
    color: String,
    cylinder: Number,
    kmPrice: Number,
    model: String,
    reservationPrice: Number,
    taxHorse: Number,
    traveledKm: Number,
    vehicleRegistration: String,
    vehicleType: String,
    volume: Number,
  },
  data() {
    return {
      item: {
        id: this.id,
        brand: this.brand,
        color: this.color,
        cylinder: this.cylinder,
        kmPrice: this.kmPrice,
        model: this.model,
        reservationPrice: this.reservationPrice,
        taxHorse: this.taxHorse,
        traveledKm: this.traveledKm,
        vehicleRegistration: this.vehicleRegistration,
        vehicleType: this.vehicleType,
        volume: this.volume,

      }
    }
  }
}
</script>

<template>
    <RouterLink :to="`/product/${ id }`" class="marginProduct">
      <section class="product">
        <div class="productInfos">
          <div>Marque : {{brand}}</div>
          <div>Model :  {{model}}</div>
          <div>Couleur : {{color}}</div>
        </div>
        <div class="productInfos">
          <div>Kilometrage : {{traveledKm}}</div>
          <div v-if="vehicleType==='moto'">Cylindre : {{cylinder}}</div>
          <div v-if="vehicleType==='utilitaire'">Volume : {{volume}}</div>
        </div>
        <BtnAdd class="btnAdd" txtBtn="En savoir plus"></BtnAdd>
      </section>
    </RouterLink>
</template>

<style scoped>
.product{
  border: 1px solid rgba(0,0,0,0.3);
  border-radius: 10px;
  box-shadow: 2px 4px rgba(0,0,0,0.1);
  padding: 1%;
}
.productInfos{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 1.5rem;
}
.btnAdd{
  margin-left: 45%;
  margin-right: 45%;
}
</style>