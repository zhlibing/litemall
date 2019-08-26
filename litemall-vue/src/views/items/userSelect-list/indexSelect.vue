<template>
    <div class="goods_brand_list">
        <appbar titleText="选择鱼塘"
                :right="right_url"
                :overrideleftclick="true"
                @leftclick="goBack"
                @rightclick="confirm"></appbar>
        <van-pull-refresh v-model="loading" @refresh="onRefresh">
            <van-list v-model="loading"
                      :finished="finished"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getActivityUserList">
                <div v-for="(item, index) in list"
                     :key="index">
                    <userSelectItem :item="item" :index="index" :selectedIndex="selectedIndex" :userId="userId"
                                    @onItemSelect="onItemSelect"></userSelectItem>
                </div>
            </van-list>
        </van-pull-refresh>
    </div>
</template>

<script>
    import {activityListUserJoin} from '@/api/api';
    import {PullRefresh, List} from 'vant';
    import Vue from 'vue'
    import appbar from '@/components/head/appbar'
    import userSelectItem from '@/components/itemadapter/userSelectItem';
    import confirm from '../../../assets/images/confirm.png'

    Vue.use(PullRefresh)
    export default {
        props: {
            itemId: [String, Number],
            userId: [String, Number],
        },
        data() {
            return {
                list: [],
                page: 0,
                limit: 10,
                loading: false,
                finished: false,
                selectedIndex: -1,
                right_url: '',
                item: {},
            };
        },

        created() {
            this.onRefresh()
        },

        methods: {
            init() {
                this.page = 0;
                this.list = [];
                this.getActivityUserList();
            },
            //下拉刷新
            onRefresh() {
                setTimeout(() => {
                    this.init()
                }, 500);
            },
            getActivityUserList() {
                this.page++;
                activityListUserJoin({
                    id: this.itemId,
                    page: this.page,
                    limit: this.limit
                }).then(res => {
                    this.list.push(...res.data.data.list);
                    this.loading = false;
                    this.finished = res.data.data.page >= res.data.data.pages;
                });
            },
            onItemSelect(item, index) {
                console.log(item, index)
                this.item = item
                if (this.selectedIndex != index) {
                    this.selectedIndex = index
                    this.right_url = confirm
                } else {
                    this.selectedIndex = -1
                    this.right_url = ''
                }
            },
            confirm() {
                if (this.selectedIndex != -1) {
                    this.$emit('selectItem', this.item);
                } else {
                    this.$router.push({
                        path: '/publish/sell/publishfishponds/5',
                        params: {index: 3}
                    })
                }
            },
            goBack() {
                this.$emit('selectItem', this.item);
            },
        },

        components: {
            appbar,
            [List.name]: List,
            userSelectItem
        }
    };
</script>

<style lang="scss" scoped>
    .goods_brand_list {
        background-color: white;
        padding: 20px;
    }
</style>