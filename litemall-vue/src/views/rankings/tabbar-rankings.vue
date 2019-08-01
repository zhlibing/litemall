<template>
    <div class="root">
        <lottery-tab @onTabIndex="toTabIndex"></lottery-tab>
        <lotteryList @onToLotteryIndex="toLotteryIndex" :itemClass="itemClass"></lotteryList>
    </div>
</template>
<script>
    import lotteryList from '@/components/tab/list';
    import LotteryTab from '@/components/tab/tab'

    export default {
        components: {lotteryList, LotteryTab},
        data() {
            return {
                a: 1,
                itemClass: {
                    "actorId": 1,
                    "actor": "吕艳婷",
                    "actorEn": "Yanting Lv",
                    "actorImg": "http://img5.mtime.cn/ph/2019/07/16/164144.93834954_1280X720X2.jpg",
                    "roleName": "儿童哪吒",
                    "roleImg": ""
                },
            };
        },
        methods: {
            toLotteryIndex(lotteryCode) {
                this.$router.push({name: 'buyLotteryIndex', params: {lotteryCode: lotteryCode}})
            },
            toTabIndex(index) {
                this.itemClass.roleName = this.itemClass.roleName+index;
                console.log(index, '>>>>toLotteryIndex')
            }
        },

    }
</script>
<style scoped>
    .root {
        background: #f7f7f7;
    }
</style>
