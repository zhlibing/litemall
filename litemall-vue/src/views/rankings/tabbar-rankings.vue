<template>
    <div class="root">
        <lottery-tab @onTabIndex="toTabIndex"></lottery-tab>
        <van-pull-refresh v-model="loading" @refresh="onRefresh" style="margin-top: 10px">
            <van-list v-model="loading"
                      :finished="finished"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getBrandList">
                <div class="brand-info"
                     v-for="(brand, index) in list"
                     :key="index"
                     @click="itemClick(brand.id)">
                    <lotteryList @onToLotteryIndex="toLotteryIndex" :itemClass="itemClass" style="margin-bottom: 30px"></lotteryList>
                </div>
            </van-list>
        </van-pull-refresh>
        <floatbutton></floatbutton>
    </div>
</template>
<script>
    import lotteryList from '@/components/itemadapter/fishponds';
    import LotteryTab from '@/components/tab/tab'
    import floatbutton from '@/components/head/floatbutton'
    import {brandList} from '@/api/api';
    import {PullRefresh, List} from 'vant';
    import Vue from 'vue'

    Vue.use(PullRefresh)
    export default {
        data() {
            return {
                a: 1,
                itemClass: {
                    "actorId": 1,
                    "actor": "吕艳婷",
                    "actorEn": "Yanting Lv",
                    "actorImg": "http://www.dnzhuti.com/uploads/allimg/170503/95-1F503163525.jpg",
                    "roleName": "儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒儿童哪吒",
                    "roleImg": ""
                },
                list: [],
                page: 0,
                limit: 10,
                loading: false,
                finished: false
            };
        },

        created() {
            this.init();
        },

        methods: {
            toLotteryIndex(lotteryCode) {
//                this.$router.push({name: 'buyLotteryIndex', params: {lotteryCode: lotteryCode}})
            },
            toTabIndex(index) {
                this.itemClass.roleName = this.itemClass.roleName + index;
                console.log(index, '>>>>toLotteryIndex')
            },
            init() {
                this.page = 0;
                this.list = [];
                this.getBrandList();
            },
            //下拉刷新
            onRefresh() {
                setTimeout(() => {
                    this.init()
                }, 500);
            },
            getBrandList() {
                this.page++;
                brandList({
                    page: this.page,
                    limit: this.limit
                }).then(res => {
                    this.list.push(...res.data.data.list);
                    this.loading = false;
                    this.finished = res.data.data.page >= res.data.data.pages;
                });
            },
            itemClick(id) {
                this.$router.push(`/items/brand/${id}`);
            }
        },
        components: {lotteryList, LotteryTab, floatbutton, [List.name]: List},
    }
</script>
<style scoped>
    .root {
        background: #f7f7f7;
    }
</style>
