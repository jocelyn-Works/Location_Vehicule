<template>
  <div class="signup-container">
    <div class="form-container">
      <h2>Inscription</h2>
      <form @submit.prevent="handleSubmit">

        <!-- Prénom -->
        <div class="input-group">
          <label for="firstName">Prénom</label>
          <input type="text" v-model="form.firstName" id="firstName" placeholder="Entrez votre prénom" required />
        </div>

        <!-- Nom -->
        <div class="input-group">
          <label for="lastName">Nom</label>
          <input type="text" v-model="form.lastName" id="lastName" placeholder="Entrez votre nom" required />
        </div>

        <!-- Email -->
        <div class="input-group">
          <label for="email">Email</label>
          <input type="email" v-model="form.email" id="email" placeholder="Entrez votre email" required />
          <span v-if="emailError" class="error">L'email est déjà utilisé.</span>
        </div>

        <!-- Date de naissance -->
        <div class="input-group">
          <label for="birthDate">Date de naissance</label>
          <input type="date" v-model="form.birthDate" id="birthDate" required />
        </div>

        <!-- Code de permis -->
        <div class="input-group">
          <label for="permitCode">Code de permis</label>
          <input type="text" v-model="form.permitCode" id="permitCode" placeholder="Entrez votre code de permis" required />
        </div>

        <!-- Date d'obtention -->
        <div class="input-group">
          <label for="dateOfObtaining">Date d'obtention du permis</label>
          <input type="date" v-model="form.dateOfObtaining" id="dateOfObtaining" required />
        </div>

        <!-- Mot de passe -->
        <div class="input-group">
          <label for="password">Mot de passe</label>
          <input type="password" v-model="form.password" id="password" placeholder="Créez un mot de passe" required />
        </div>

        <button type="submit" class="btn">S'inscrire</button>

        <div><RouterLink :to="{ name: 'connexion' }">Connexion</RouterLink></div>
        <!-- Messages de succès et d'erreur -->
        <div v-if="successMessage" class="success">
          Utilisateur inscrit avec succès !
        </div>
        <div v-if="errorMessage" class="error">
          Une erreur s'est produite. Veuillez réessayer.
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      form: {
        firstName: "",
        lastName: "",
        email: "",
        birthDate: "",
        permitCode: "",
        dateOfObtaining: "",
        password: "",
        role: ["user"], // Rôle par défaut
      },
      emailError: false, // Pour afficher l'erreur email
      successMessage: false, // Message de succès
      errorMessage: false, // Message d'erreur générique
    };
  },
  methods: {
    async handleSubmit() {
      try {
        // Réinitialiser les messages
        this.successMessage = false;
        this.errorMessage = false;
        this.emailError = false;

        // Préparer les données à envoyer
        const payload = { ...this.form };

        console.log(payload);  // Afficher l'objet pour déboguer

        // Envoyer la requête POST
        const response = await axios.post("http://localhost:9191/api/signup", payload);

        if (response.status === 200) {
          this.successMessage = true;
          // Réinitialiser le formulaire après un succès
          this.form = {
            firstName: "",
            lastName: "",
            email: "",
            birthDate: "",
            permitCode: "",
            dateOfObtaining: "",
            password: "",
            role: ["user"], // Garder le rôle par défaut
          };
        }
      } catch (error) {
        console.log(error);  // Affiche l'erreur complète dans la console

        if (error.response && error.response.data) {
          // Vérifier l'erreur spécifique de l'email déjà utilisé
          if (error.response.data.message === "Error: Email is already in use!") {
            this.emailError = true;
          } else {
            this.errorMessage = true;
          }
        } else {
          // En cas d'autres erreurs
          this.errorMessage = true;
        }
      }
    },
  },
};
</script>

<style scoped>
.signup-container {
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

.input-group input,
.input-group select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
}

.input-group input:focus,
.input-group select:focus {
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
