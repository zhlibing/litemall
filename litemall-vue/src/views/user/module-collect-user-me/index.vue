<template>
    <div class="user_collect">
        <appbar titleText="关注我的"></appbar>
        <van-list v-model="loading"
                  :finished="finished"
                  :immediate-check="false"
                  finished-text="没有更多了"
                  @load="getCollectMeList">
            <van-card v-for="(item, i) in list"
                      :key="i"
                      :desc="item.brief"
                      :title="item.user.nickname"
                      :thumb="item.user.avatar"
                      :price="item.user.status"
                      :origin-price="item.user.username"
                      @click="itemClick(item.user.id)">
                <div slot="footer">
                </div>
            </van-card>
        </van-list>

        <is-empty v-if="list.length === 0">没有粉丝关注呢</is-empty>

    </div>
</template>

<script>
    import {collectmeList, collectAddOrDelete} from '@/api/api';
    import IsEmpty from '@/components/is-empty/';
    import {Card, Search, List} from 'vant';
    import scrollFixed from '@/mixin/scroll-fixed';
    import appbar from '@/components/head/appbar'

    export default {
        mixins: [scrollFixed],

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
            init() {
                this.page = 0;
                this.list = [];
                this.getCollectMeList()
            },
            getCollectMeList() {
                this.page++;
                collectmeList({type: 10, page: this.page, limit: this.limit}).then(res => {
                    this.list.push(...res.data.data.list);
                    this.loading = false;
                    this.finished = res.data.data.page >= res.data.data.pages;
                });
            },
            itemClick(id) {
                console.log(id, 'userdetails')
                this.$router.push(`/items/userdetails/${id}`);
            }
        },

        components: {
            appbar,
            [Search.name]: Search,
            [IsEmpty.name]: IsEmpty,
            [List.name]: List,
            [Card.name]: Card
        }
    };
</script>

<style lang="scss" scoped>
</style>
