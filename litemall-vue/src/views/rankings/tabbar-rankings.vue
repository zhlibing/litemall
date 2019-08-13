<template>
    <div class="root">
        <lottery-tab @onTabIndex="toTabIndex"></lottery-tab>
        <van-pull-refresh v-model="loading" @refresh="onRefresh" style="margin-top: 10px">
            <van-list v-model="loading"
                      :finished="finished"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getActivityListall" v-show="index==0" style="margin-top: 20px">
                <div v-for="(item, index) in list"
                     :key="index">
                    <lotteryList :item="item" @onShowClick="onShowClick"
                                 style="margin-top: 5px"></lotteryList>
                </div>
            </van-list>
        </van-pull-refresh>
        <van-pull-refresh v-model="loading1" @refresh="onRefresh" style="margin-top: 10px">
            <van-list v-model="loading1"
                      :finished="finished1"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getFishpondsListall" v-show="index==1" style="margin-top: 10px">
                <div v-for="(item, index) in list1"
                     :key="index">
                    <fishpondsItem :item="item" @onShowClick="onShowClick"
                                   style="margin-top: 5px"></fishpondsItem>
                </div>
            </van-list>
        </van-pull-refresh>
        <van-pull-refresh v-model="loading2" @refresh="onRefresh" style="margin-top: 10px">
            <van-list v-model="loading2"
                      :finished="finished2"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getGroupListall" v-show="index==2" style="margin-top: 10px">
                <div v-for="(item, index) in list2"
                     :key="index">
                    <groupItem :item="item" @onShowClick="onShowClick"
                               style="margin-top: 5px"></groupItem>
                </div>
            </van-list>
        </van-pull-refresh>
        <van-pull-refresh v-model="loading3" @refresh="onRefresh" style="margin-top: 0px">
            <van-list v-model="loading3"
                      :finished="finished3"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getCommentListall" v-show="index==3" style="margin-top: 30px">
                <div v-for="(comment, index) in list3"
                     :key="index"
                     style="background-color: #ffffff;margin-top: 5px">
                    <commentItem :comment="comment"></commentItem>
                    <linkobj :item="comment.info"></linkobj>
                </div>
            </van-list>
        </van-pull-refresh>
        <is-empty v-if="index>3">暂无内容</is-empty>
        <floatbutton></floatbutton>
    </div>
</template>
<script>
    import lotteryList from '@/components/itemadapter/fishponds';
    import fishpondsItem from '@/components/itemadapter/fishpondsItem';
    import groupItem from '@/components/itemadapter/groupItem';
    import LotteryTab from '@/components/tab/tab'
    import floatbutton from '@/components/head/floatbutton'
    import {activityListall, groupListall, fishpondsListall, commentListall} from '@/api/api';
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

                list2: [],
                page2: 0,
                loading2: false,
                finished2: false,

                list3: [],
                page3: 0,
                loading3: false,
                finished3: false,
            };
        },

        created() {
            this.init();
            this.init1();
            this.init2();
            this.init3();
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
                this.getFishpondsListall();
            },
            init2() {
                this.page2 = 0;
                this.list2 = [];
                this.getGroupListall();
            },
            init3() {
                this.page3 = 0;
                this.list3 = [];
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
                    if (this.index == 2) {
                        this.init2()
                    }
                    if (this.index == 3) {
                        this.init3()
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
            getFishpondsListall() {
                this.page1++;
                fishpondsListall({
                    page: this.page1,
                    limit: this.limit
                }).then(res => {
                    this.list1.push(...res.data.data.list);
                    this.loading1 = false;
                    this.finished1 = res.data.data.page >= res.data.data.pages;
                });
            },
            getGroupListall() {
                this.page2++;
                groupListall({
                    page: this.page2,
                    limit: this.limit
                }).then(res => {
                    this.list2.push(...res.data.data.list);
                    this.loading2 = false;
                    this.finished2 = res.data.data.page >= res.data.data.pages;
                });
            },
            getCommentListall() {
                this.page3++;
                commentListall({
                    page: this.page3,
                    limit: this.limit
                }).then(res => {
                    this.list3.push(...res.data.data.list);
                    this.loading3 = false;
                    this.finished3 = res.data.data.page >= res.data.data.pages;
                });
            },
            itemClick(id, type) {
                this.$router.push(`/items/publishdetails/${id}/${type}`);
            },
        },
        components: {
            lotteryList,
            fishpondsItem,
            groupItem,
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
