<script>

</script>

<template>
  <section class="header">
    <div class="logo"><a href="/public"><img src="./icons/logo.png" alt="Logo"></a></div>
    <div><a href="/public">Acceuil</a></div>
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
  position: relative;
  width: 150px;
  height: 150px;
  border-radius: 50%;
  z-index: 3;
}
</style>