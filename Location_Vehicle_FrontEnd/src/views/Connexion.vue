<template>
  <div class="login-container">
    <div class="form-container">
      <h2>Connexion</h2>
      <form @submit.prevent="handleSubmit">

        <!-- Email -->
        <div class="input-group">
          <label for="email">Email</label>
          <input type="email" v-model="form.email" id="email" placeholder="Entrez votre email" required />
        </div>

        <!-- Mot de passe -->
        <div class="input-group">
          <label for="password">Mot de passe</label>
          <input type="password" v-model="form.password" id="password" placeholder="Entrez votre mot de passe" required />
        </div>

        <!-- Bouton de connexion -->
        <button type="submit" class="btn">Se connecter</button>

        <div><RouterLink :to="{ name: 'inscription' }">Inscription</RouterLink></div>

        <!-- Messages de succès / erreur -->
        <div v-if="successMessage" class="success">
          Connexion réussie ! Vous êtes maintenant connecté.
        </div>
        <div v-if="errorMessage" class="error">
          Email ou mot de passe incorrect. Veuillez réessayer.
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      form: {
        email: '',
        password: '',
      },
      successMessage: false,
      errorMessage: false,
    };
  },
  methods: {
    async handleSubmit() {
      try {
        this.successMessage = false;
        this.errorMessage = false;


        const payload = {
          email: this.form.email,
          password: this.form.password,
        };


        const response = await axios.post('http://localhost:9191/api/login', payload, {
          withCredentials: true,  // Permet l'envoi et la réception de cookies
        });


        if (response.status === 200) {
          this.successMessage = true;

          this.$router.push('/catalog');
        }
      } catch (error) {
        // Si erreur (mauvais identifiants)
        this.errorMessage = true;
        console.log(error);
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f4f7fc;
}

.form-container {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

h2 {
  text-align: center;
  font-family: 'Roboto', sans-serif;
  margin-bottom: 20px;
}

.input-group {
  margin-bottom: 15px;
}

.input-group label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.input-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
}

.input-group input:focus {
  outline: none;
  border-color: #3498db;
}

.error {
  color: red;
  font-size: 14px;
}

.success {
  color: green;
  font-size: 14px;
  margin-top: 10px;
}

.btn {
  background-color: #3498db;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 5px;
  width: 100%;
  cursor: pointer;
}

.btn:hover {
  background-color: #2980b9;
}
</style>
