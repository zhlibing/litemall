<template>
    <div class="goods_brand_list">
        <appbar titleText="品牌"></appbar>
        <van-pull-refresh v-model="loading" @refresh="onRefresh">
            <van-list v-model="loading"
                      :finished="finished"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getBrandList">
                <div class="brand-info"
                     v-for="(brand, index) in list"
                     :key="index"
                     @click="itemClick(brand.id)">
                    <div class="name">
                        <img class="img"
                             :src="brand.picUrl"
                             background-size="cover"/>
                        <div class="info-box">
                            <div class="txt">{{brand.name}}</div>
                            <div class="line"></div>
                            <div class="price">{{brand.floorPrice}}元起</div>
                        </div>
                    </div>
                    <div class="desc">
                        {{brand.desc}}
                    </div>
                </div>
            </van-list>
        </van-pull-refresh>
    </div>
</template>

<script>
    import {brandList} from '@/api/api';
    import {PullRefresh, List} from 'vant';
    import Vue from 'vue'
    import appbar from '@/components/head/appbar'

    Vue.use(PullRefresh)
    export default {
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

        components: {
            appbar,
            [List.name]: List
        }
    };
</script>

<style lang="scss" scoped>
    .goods_brand_list {
        .brand-info {
            .name {
                width: 100%;
                height: 180px;
                position: relative;

                .img {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 180px;
                }

                .info-box {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 180px;
                    text-align: center;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    display: block;
                    background-color:rgba(0,0,0,0.4);

                    .txt {
                        margin-top: 60px;
                        height: 25px;
                        font-size: 25px;
                        color: #fff;
                    }

                    .line {
                        margin: 0 auto;
                        margin-top: 16px;
                        display: block;
                        height: 2px;
                        width: 300px;
                        background: #fff;
                    }
                    .price {
                        height: 25px;
                        font-size: 25px;
                        color: #fff;
                    }
                }
            }
            .desc {
                background: #fff;
                width: 100%;
                height: auto;
                overflow: hidden;
                padding: 25px 20px;
                font-size: 20px;
                color: #666;
                line-height: 20px;
                text-align: center;
            }
        }
    }
</style>