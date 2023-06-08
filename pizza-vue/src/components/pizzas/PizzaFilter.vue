<script>
import axios from 'axios'
export default {
    name: "PizzaFilter",
    props: { name: String },
    data() {
        return {

        }
    },

    methods: {
        filterPizza(name) {

        }
    }
}
</script>

<template>
    <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Input pizza name">
        <button class="btn btn-outline-secondary" type="button" id="button-addon2">Search</button>
    </div>
</template>

<script lang="scss" scoped></script>