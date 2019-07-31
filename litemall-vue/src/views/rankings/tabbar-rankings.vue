<template>
    <div class="root">
        <lottery-tab></lottery-tab>
        <lotteryList @onToLotteryIndex="toLotteryIndex"></lotteryList>
    </div>
</template>
<script>
    import lotteryList from '@/components/tab/list';
    import LotteryTab from '@/components/tab/tab'

    export default {
        components: {lotteryList, LotteryTab},
        data() {
            return {
                a: 1
            };
        },
        methods: {
            toLotteryIndex(lotteryCode) {
                this.$router.push({name: 'buyLotteryIndex', params: {lotteryCode: lotteryCode}})
            }
        },

    }
</script>
<style scoped>
    .root {
        background: #F5F5F9;
    }
</style>
