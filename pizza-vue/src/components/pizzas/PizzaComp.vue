<script>
import axios from 'axios';
const base_url = "http://localhost:8080/api/pizzas"

export default {
    name: "PizzaComp",
    props: { pizza: Object },
    data() {
        return {
            isDeleted: false
        }
    },
    methods: {

        // delete pizza
        deletePizza(id) {
            axios.delete(base_url + `/delete/${id}`)
                .then(res => {
                    console.log(res);
                    this.isDeleted = true
                })
                .catch(e => { console.log(e); })
        }
    },
}
</script>

<template>
    <li v-if="!isDeleted" class="list-group-item d-flex align-items-center justify-content-between">
        <div>
            <h2>{{ pizza.name }}</h2>
            <p>{{ pizza.description }} - {{ pizza.price }} €</p>
        </div>
        <button @click="deletePizza(pizza.id)" class="btn btn-danger ">DELETE</button>
    </li>
</template>

<style scoped></style>