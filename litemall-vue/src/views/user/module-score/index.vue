<template>
    <div class="user_collect">
        <appbar titleText="我的积分"></appbar>
        <van-pull-refresh v-model="loading" @refresh="onRefresh()">
            <van-list v-model="loading"
                      :finished="finished"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getScoreList"
                      v-if="list.length>0">
                <van-card v-for="(item, i) in list"
                          :key="i"
                          :desc="item.brief"
                          :title="item.info.name"
                          :price="item.info.score"
                          @click="itemClick(item.goods.id)">
                </van-card>
            </van-list>
            <is-empty v-if="list.length === 0">没有更多</is-empty>
        </van-pull-refresh>
    </div>
</template>

<script>
    import {userScoreListByUser} from '@/api/api';
    import IsEmpty from '@/components/is-empty/';
    import {Card, Search, List, PullRefresh} from 'vant';
    import scrollFixed from '@/mixin/scroll-fixed';
    import appbar from '@/components/head/appbar'

    export default {
        mixins: [scrollFixed],
        props: {
            userId: [String, Number],
        },
        data() {
            return {
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
            //下拉刷新
            onRefresh() {
                setTimeout(() => {
                    this.init()
                }, 500);
            },
            init() {
                this.page = 0;
                this.list = [];
                this.getScoreList()
            },
            getScoreList() {
                this.page++;
                userScoreListByUser({userId: this.userId, page: this.page, limit: this.limit}).then(res => {
                    this.list.push(...res.data.data.list);
                    this.loading = false;
                    this.finished = res.data.data.page >= res.data.data.pages;
                });
            },
            itemClick(id) {
//      this.$router.push(`/items/detail/${id}`);
            }
        },

        components: {
            appbar,
            [Search.name]: Search,
            [IsEmpty.name]: IsEmpty,
            [List.name]: List,
            [Card.name]: Card,
            [PullRefresh.name]: PullRefresh,
        }
    };
</script>

<style lang="scss" scoped>
    .clear_invalid {
        width: 120px;
        color: $font-color-gray;
        border: 1px solid $font-color-gray;
        margin: 0 auto;
        text-align: center;
        padding: 5px 3px;
        margin-top: 20px;
        border-radius: 3px;
    }
</style>
