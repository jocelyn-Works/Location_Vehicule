<script>

</script>

<template>
  <section class="header">
    <div class="logo"><a href="/"><img src="../components/icons/logo.png" alt="Logo"></a></div>
    <div><a href="/">Acceuil</a></div>
    <div><a href="/catalog">Catalogue</a></div>
    <div><a href="/contact">Contact</a></div>
  </section>
</template>

<style scoped>
.header {
  height: 10vh;
  background-color: cornflowerblue;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  font-size: calc(10px + 2vmin);
  color: white;
  font-weight: bold;
}
.logo {
  margin-top: 2%;
  margin-left: -15%;
}
.logo img{
  width: 150px;
  height: 150px;
  border-radius: 50%;
}
</style>