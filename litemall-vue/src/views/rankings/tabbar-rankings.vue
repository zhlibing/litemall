<template>
    <div class="root">
        <lottery-tab @onTabIndex="toTabIndex"></lottery-tab>
        <van-pull-refresh v-model="loading" @refresh="onRefresh" style="margin-top: 10px">
            <van-list v-model="loading"
                      :finished="finished"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getBrandList" v-show="index==0">
                <div v-for="(brand, index) in list"
                     :key="index"
                     @click="itemClick0(brand.id)">
                    <lotteryList :itemClass="itemClass" style="margin-bottom: 30px"></lotteryList>
                </div>
            </van-list>
        </van-pull-refresh>
        <van-pull-refresh v-model="loading1" @refresh="onRefresh" style="margin-top: 10px">
            <van-list v-model="loading1"
                      :finished="finished1"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getCommentListall" v-show="index==1" style="margin-top: 30px">
                <div v-for="(comment, index) in list1"
                     :key="index"
                     @click="itemClick1(comment.info.id,comment.info.type)"
                     style="background-color: #ffffff;margin-top: 5px">
                    <commentItem :comment="comment"></commentItem>
                </div>
            </van-list>
        </van-pull-refresh>
        <is-empty v-if="index>1">暂无内容</is-empty>
        <floatbutton></floatbutton>
    </div>
</template>
<script>
    import lotteryList from '@/components/itemadapter/fishponds';
    import LotteryTab from '@/components/tab/tab'
    import floatbutton from '@/components/head/floatbutton'
    import {brandList, commentListall} from '@/api/api';
    import {PullRefresh, List} from 'vant';
    import Vue from 'vue'
    import commentItem from '../../components/itemadapter/commentItem.vue'
    import IsEmpty from '@/components/is-empty/';

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
                index: '',
                limit: 10,

                list: [],
                page: 0,
                loading: false,
                finished: false,

                list1: [],
                page1: 0,
                loading1: false,
                finished1: false,
            };
        },

        created() {
            this.init();
            this.init1();
        },

        methods: {
            toTabIndex(index) {
                this.index = index
                console.log(index, '>>>>toLotteryIndex')
            },
            init() {
                this.page = 0;
                this.list = [];
                this.getBrandList();
            },
            init1() {
                this.page1 = 0;
                this.list1 = [];
                this.getCommentListall();
            },
            //下拉刷新
            onRefresh() {
                setTimeout(() => {
                    if (this.index == 0) {
                        this.init()
                    }
                    if (this.index == 1) {
                        this.init1()
                    }
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
            getCommentListall() {
                this.page1++;
                commentListall({
                    page: this.page1,
                    limit: this.limit
                }).then(res => {
                    this.list1.push(...res.data.data.list);
                    this.loading1 = false;
                    this.finished1 = res.data.data.page >= res.data.data.pages;
                });
            },
            itemClick0(id) {
                this.$router.push(`/items/brand/${id}`);
            },
            itemClick1(id, type) {
                if (type != undefined) {
                    this.$router.push(`/items/publishdetails/${id}/${type}`);
                } else {
                    this.$router.push(`/items/detail/${id}`);
                }
            }
        },
        components: {lotteryList, LotteryTab, floatbutton, [List.name]: List, commentItem, [IsEmpty.name]: IsEmpty,},
    }
</script>
<style scoped>
    .root {
        background: #f7f7f7;
    }
</style>
