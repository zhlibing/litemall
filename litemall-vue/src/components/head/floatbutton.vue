<template>
    <div class="float_b">
        <div class="outbox">
            <span class="open" @click=""></span>
        </div>
        <span class="van-tabbar-item">发布</span>
    </div>
</template>
<script>
    export default {}
</script>
<style lang="scss" scoped>
    .float_b {
        z-index: 2999;
        height: 3rem;
        width: 3rem;
        position: fixed;
        bottom: 20px;
        center: true;
        display: flex;
        align-items: center;
        margin-left: 43%;
        pointer-events: auto;
        flex-direction: column;
        .outbox {
            width: 3rem;
            height: 3rem;
            border-radius: 50%;
            margin: 0.18rem auto;
            background-color: #ffffff;
            display: block;
            z-index: 500;
            .open {
                width: 2.5rem;
                height: 2.5rem;
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
