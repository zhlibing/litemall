<template>
    <div class="goods_brand_list">
        <appbar titleText="选择鱼塘"></appbar>
        <van-pull-refresh v-model="loading" @refresh="onRefresh">
            <van-list v-model="loading"
                      :finished="finished"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getFishpondsListall">
                <div v-for="(item, index) in list"
                     :key="index">
                    <fishpondsSelectItem :item="item" :index="index" :selectedIndex="selectedIndex"
                                         @onItemSelect="onItemSelect"></fishpondsSelectItem>
                </div>
            </van-list>
        </van-pull-refresh>
    </div>
</template>

<script>
    import {fishpondsListall} from '@/api/api';
    import {PullRefresh, List} from 'vant';
    import Vue from 'vue'
    import appbar from '@/components/head/appbar'
    import fishpondsSelectItem from '@/components/itemadapter/fishpondsSelectItem';

    Vue.use(PullRefresh)
    export default {
        data() {
            return {
                list: [],
                page: 0,
                limit: 10,
                loading: false,
                finished: false,
                selectedIndex: -1
            };
        },

        created() {
            this.init();
        },

        methods: {
            init() {
                this.page = 0;
                this.list = [];
                this.getFishpondsListall();
            },
            //下拉刷新
            onRefresh() {
                setTimeout(() => {
                    this.init()
                }, 500);
            },
            getFishpondsListall() {
                this.page++;
                fishpondsListall({
                    page: this.page,
                    limit: this.limit
                }).then(res => {
                    this.list.push(...res.data.data.list);
                    this.loading = false;
                    this.finished = res.data.data.page >= res.data.data.pages;
                });
            },
            onItemSelect(item, index) {
                this.selectedIndex = index
            }
        },

        components: {
            appbar,
            [List.name]: List,
            fishpondsSelectItem
        }
    };
</script>

<style lang="scss" scoped>
    .goods_brand_list {
        background-color: white;
        padding: 20px;
    }
</style>