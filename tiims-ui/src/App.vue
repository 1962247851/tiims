<template>
    <v-app id="app">
        <router-view name="header"/>
        <v-main>
            <v-container fluid class="pa-0">
                <keep-alive>
                    <router-view/>
                </keep-alive>
            </v-container>
        </v-main>
        <router-view name="footer"/>
    </v-app>
</template>

<script>

    export default {
        name: 'App',
        data: vm => ({
            initialDark: vm.$vuetify
                ? vm.$vuetify.theme.dark
                : false
        }),
        beforeDestroy() {
            if (!this.$vuetify) return;
            this.$vuetify.theme.dark = this.initialDark
        },
        created() {
            document.title = "培训机构信息管理系统"
        }
    }
</script>

<style>
</style>
