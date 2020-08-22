<template>
    <v-footer padless>
        <v-sheet width="100%" class="text-center elevation-8">
            <v-card-text>
                ©{{ new Date().getFullYear() }} — <strong>苗锦洲</strong>
            </v-card-text>
        </v-sheet>
    </v-footer>
</template>

<script>
    export default {
        name: "Footer"
    }
</script>

<style scoped>

</style>