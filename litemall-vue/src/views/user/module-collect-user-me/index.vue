<template>
    <div class="user_collect">
        <appbar titleText="关注我的"></appbar>
        <van-list v-model="loading"
                  :finished="finished"
                  :immediate-check="false"
                  finished-text="没有更多了"
                  @load="getCollectMeList"
                  v-if="list.length>0">
            <div v-for="(itemc,index) in list" :key="index" style="margin: 15px">
                <linkuser :item="itemc"></linkuser>
            </div>
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
    import linkuser from '../../../components/itemadapter/linkuser2'

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
            [Card.name]: Card,
            linkuser
        }
    };
</script>

<style lang="scss" scoped>
    .user_collect {
        background-color: white;
    }
</style>
