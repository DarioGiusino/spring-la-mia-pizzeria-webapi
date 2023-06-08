<script>
import axios from 'axios';
import PizzaComp from './components/pizzas/PizzaComp.vue';
import PizzaFilter from './components/pizzas/PizzaFilter.vue';
const base_url = "http://localhost:8080/api/pizzas"

export default {
  name: "App",
  components: { PizzaComp, PizzaFilter },
  data() {
    return {
      pizzas: null,
    }
  },

  methods: {

    // fetch pizza from java be
    fetchPizzas() {
      axios.get(base_url).then(res => {
        console.log(res.data);
        this.pizzas = res.data
      }).catch(e => {
        console.log(e);
      })
    },

  },

  mounted() {
    this.fetchPizzas()
  }
}
</script>

<template>
  <main class="container">
    <h1 class="text-center my-5">Pizza webbe</h1>
    <PizzaFilter />
    <ul class="list-group list-group-flush">
      <PizzaComp v-for="pizza in pizzas" :pizza="pizza" :key="pizza.id" />
    </ul>
  </main>
</template>

<style lang="scss" scoped></style>
