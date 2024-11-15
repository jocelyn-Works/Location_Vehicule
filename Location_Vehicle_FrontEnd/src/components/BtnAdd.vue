<script>
export default{
  name: "BtnAdd",
  props: ['txtBtn']
}
</script>

<template>
  <button class="addBtn" >{{txtBtn}}</button>
</template>

<style scoped>
.addBtn{
  background-color: green;
  color: black;
  padding: 1%;
  border-radius: 10px;
  border-color:black;
  border-width: 2px;
  font-family: "Montserrat", sans-serif;
}
</style>