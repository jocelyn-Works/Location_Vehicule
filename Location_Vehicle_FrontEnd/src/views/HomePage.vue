<template>
  <div class="home-page">
    <!-- Hero Section -->
    <div class="hero">
      <div class="hero-content">
        <h1>AutoLoc</h1>
        <p>Trouvez la voiture parfaite, où que vous soyez.</p>
        <form class="search-form" @submit.prevent="searchVehicles">
          <div class="input-group">
            <label for="location">Lieu</label>
            <input type="text" id="location" v-model="searchCriteria.location" placeholder="Entrez une ville" />
          </div>
          <div class="input-group">
            <label for="startDate">Début</label>
            <input type="date" id="startDate" v-model="searchCriteria.startDate" />
          </div>
          <div class="input-group">
            <label for="endDate">Fin</label>
            <input type="date" id="endDate" v-model="searchCriteria.endDate" />
          </div>
          <button type="submit" class="search-button">Rechercher</button>
        </form>
      </div>
    </div>

    <!-- Features Section -->
    <section class="features">
      <h2>Pourquoi nous choisir ?</h2>
      <div class="feature-list">
        <div class="feature-item">
          <i class="fas fa-car"></i>
          <p>Large choix de véhicules modernes</p>
        </div>
        <div class="feature-item">
          <i class="fas fa-map-marker-alt"></i>
          <p>Disponibilité dans plusieurs villes</p>
        </div>
        <div class="feature-item">
          <i class="fas fa-money-bill-wave"></i>
          <p>Prix compétitifs</p>
        </div>
        <div class="feature-item">
          <i class="fas fa-star"></i>
          <p>Service client 5 étoiles</p>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "HomePage",
  data() {
    return {
      searchCriteria: {
        location: "",
        startDate: "",
        endDate: "",
      },
    };
  },
  methods: {
    searchVehicles() {
      console.log("Recherche en cours pour :", this.searchCriteria);
      alert(
          `Recherche pour ${this.searchCriteria.location} du ${this.searchCriteria.startDate} au ${this.searchCriteria.endDate}`
      );
    },
  },
};
</script>

<style scoped>
/* Global Styles */
.home-page {
  margin: 0;
  padding: 0;
  font-family: 'Arial', sans-serif;
}

/* Hero Section */
.hero {
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
  url('https://source.unsplash.com/1600x900/?car,rent') center/cover no-repeat;
  height: 100vh;
  color: white;
}

.hero-content {
  max-width: 600px;
}

.hero h1 {
  font-size: 3rem;
  margin-bottom: 20px;
}

.hero p {
  font-size: 1.2rem;
  margin-bottom: 30px;
}

.search-form {
  display: flex;
  gap: 15px;
  flex-wrap: wrap;
  justify-content: center;
}

.input-group {
  display: flex;
  flex-direction: column;
}

.input-group label {
  font-size: 0.9rem;
  margin-bottom: 5px;
}

.input-group input {
  padding: 10px;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
}

.search-button {
  padding: 10px 20px;
  background: #f39c12;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  cursor: pointer;
  transition: background 0.3s ease;
}

.search-button:hover {
  background: #e67e22;
}

/* Features Section */
.features {
  text-align: center;
  padding: 50px 20px;
  background: #f9f9f9;
}

.features h2 {
  font-size: 2rem;
  margin-bottom: 30px;
}

.feature-list {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 20px;
}

.feature-item {
  background: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
  flex: 1;
  max-width: 200px;
}

.feature-item i {
  font-size: 2rem;
  color: #f39c12;
  margin-bottom: 10px;
}

.feature-item p {
  font-size: 1rem;
}
</style>