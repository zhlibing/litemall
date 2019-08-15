<template>
    <div class="user_collect">
        <appbar titleText="我的关注"></appbar>
        <van-list v-model="loading"
                  :finished="finished"
                  :immediate-check="false"
                  finished-text="没有更多了"
                  @load="getCollectList">
            <div v-for="(itemc,index) in list" :key="index" style="margin: 15px">
                <linkuser :item="itemc"></linkuser>
                <div class="footer">
                    <van-button size="mini"
                                icon="lajitong"
                                @click.stop="cancelCollect($event, i,item)">删除
                    </van-button>
                </div>
            </div>
        </van-list>

        <is-empty v-if="list.length === 0">没有关注</is-empty>

    </div>
</template>

<script>
    import {collectList, collectAddOrDelete} from '@/api/api';
    import IsEmpty from '@/components/is-empty/';
    import {Card, Search, List} from 'vant';
    import scrollFixed from '@/mixin/scroll-fixed';
    import appbar from '@/components/head/appbar'
    import linkuser from '../../../components/itemadapter/linkuser'

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
                this.getCollectList()
            },
            getCollectList() {
                this.page++;
                collectList({type: 10, page: this.page, limit: this.limit}).then(res => {
                    this.list.push(...res.data.data.list);
                    this.loading = false;
                    this.finished = res.data.data.page >= res.data.data.pages;
                });
            },
            cancelCollect(event, i, item) {
                this.$dialog.confirm({message: '是否取消关注'}).then(() => {
                    collectAddOrDelete({valueId: item.valueId, type: 10}).then(res => {
                        this.list.splice(i, 1);
                    });
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
    .footer {
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
