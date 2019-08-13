<template>
    <div class="root">
        <lottery-tab @onTabIndex="toTabIndex"></lottery-tab>
        <van-pull-refresh v-model="loading" @refresh="onRefresh" style="margin-top: 10px">
            <van-list v-model="loading"
                      :finished="finished"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getActivityListall" v-show="index==0" style="margin-top: 30px">
                <div v-for="(item, index) in list"
                     :key="index">
                    <lotteryList :item="item" @onShowClick="onShowClick"
                                 style="margin-top: 5px"></lotteryList>
                </div>
            </van-list>
        </van-pull-refresh>
        <van-pull-refresh v-model="loading1" @refresh="onRefresh" style="margin-top: 0px">
            <van-list v-model="loading1"
                      :finished="finished1"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getCommentListall" v-show="index==1" style="margin-top: 30px">
                <div v-for="(comment, index) in list1"
                     :key="index"
                     style="background-color: #ffffff;margin-top: 5px">
                    <commentItem :comment="comment"></commentItem>
                    <linkobj :item="comment.info"></linkobj>
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
    import {activityListall, commentListall} from '@/api/api';
    import {PullRefresh, List} from 'vant';
    import Vue from 'vue'
    import commentItem from '../../components/itemadapter/commentItem.vue'
    import linkobj from '../../components/itemadapter/linkobj.vue'
    import IsEmpty from '@/components/is-empty/';

    Vue.use(PullRefresh)
    export default {
        data() {
            return {
                a: 1,
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
            onShowClick() {
                console.log('>>>>onShowClick')
            },
            toTabIndex(index) {
                this.index = index
                console.log(index, '>>>>toLotteryIndex')
            },
            init() {
                this.page = 0;
                this.list = [];
                this.getActivityListall();
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
            getActivityListall() {
                this.page++;
                activityListall({
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
            itemClick(id, type) {
                this.$router.push(`/items/publishdetails/${id}/${type}`);
            },
        },
        components: {
            lotteryList,
            LotteryTab,
            floatbutton,
            [List.name]: List,
            commentItem,
            [IsEmpty.name]: IsEmpty,
            linkobj,
        },
    }
</script>
<style scoped>
    .root {
        background: #f7f7f7;
    }
</style>
