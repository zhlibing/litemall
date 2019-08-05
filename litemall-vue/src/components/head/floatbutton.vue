<template>
    <div class="float_b">
        <div class="outbox">
            <span class="open" @click=""></span>
        </div>
    </div>
</template>
<script>
    export default {}
</script>
<style lang="scss" scoped>
    .float_b {
        z-index: 200;
        height: 2.75rem;
        width: 2.75rem;
        position: fixed;
        bottom: 60px;
        right: 20px;
        display: flex;
        align-items: center;
        .outbox {
            width: 2.75rem;
            height: 2.75rem;
            border-radius: 50%;
            margin: 0.15rem auto;
            display: block;
            z-index: 500;
            .open {
                width: 2.75rem;
                height: 2.75rem;
                display: block;
                border-radius: 50%;
                margin: 0.1rem auto;
                background: #ffda44 url('../../assets/images/add.png') no-repeat;
                background-size: 1.2rem 1.2rem;
                background-position: 50% 50%;
            }
        }
    }
</style>
