<template>
    <div class="text-center" style="margin-top: 10%">
        <p>页面不见了哦</p>
        <p>404. 抱歉，您访问的资源不存在。</p>
        <a @click="$router.back()">返回上一级/首页</a>
    </div>
</template>

<script>
    export default {
        name: "NotFound"
    }
</script>

<style scoped>

</style>