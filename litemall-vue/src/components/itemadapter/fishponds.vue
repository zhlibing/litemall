<template>
    <div class="lobby-item" v-if="itemClass!==undefined">
        <a class="item-content">
            <div class="lottery-logo">
                <img :src="itemClass.actorImg" style="object-fit: cover">
            </div>
            <div class="item-info">
                <div class="info-row">
                    <p class="lottery-name">{{itemClass.roleName}}</p>
                    <img src="../../assets/images/right.png" class="arrow-right"/>
                </div>
                <div class="nums">
                    <span>1</span>
                    <span>6</span>
                    <span>5</span>
                    <span>1</span>
                    <span>1</span>
                </div>
                <div class="info-row">
                    <span class="issue-no">累计奖金：</span>
                    <span class="item-cd">￥1000.00</span>
                </div>
            </div>
        </a>
        <div class="funs-wrap">
            <div class="funs-list">
                <div class="fun" @click="showOne">
                    <img src="../../assets/images/trend.png" class="fun-logo"/>
                    <span>头号玩家</span>
                </div>
                <div class="fun" @click="showTwo">
                    <img src="../../assets/images/rule.png" class="fun-logo"/>
                    <span>玩法规则</span>
                </div>
                <div class="fun" @click="showThree">
                    <img src="../../assets/images/shouCang.png" class="fun-logo"/>
                    <span>加入收藏</span>
                </div>
            </div>
        </div>
        <div class="fun-info" v-show="isShowOne">
            <span>规则说明规则说明规则说明规则说明规则说明规则说明规则说明规则说明规则说明规则说明规则说明规则说明</span>
        </div>
        <div class="fun-info" v-show="isShowTwo">
            <span>玩法介绍玩法介绍玩法介绍玩法介绍玩法介绍玩法介绍玩法介绍玩法介绍玩法介绍玩法介绍</span>
        </div>
        <div class="fun-info" v-show="isShowThree">
            <span>排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜</span>
        </div>
    </div>
</template>
<script>
    export default {
        props: {
            itemClass: Object,
        },
        data() {
            return {
                isShowOne: false,
                isShowTwo: false,
                isShowThree: false,
            };
        },
        methods: {
            showOne() {
                this.$emit('onShowClick')
                if (this.isShowOne) {
                    this.isShowOne = false
                } else {
                    this.isShowOne = true
                    this.isShowTwo = false
                    this.isShowThree = false
                }
            },
            showTwo() {
                this.$emit('onShowClick')
                if (this.isShowTwo) {
                    this.isShowTwo = false
                } else {
                    this.isShowOne = false
                    this.isShowTwo = true
                    this.isShowThree = false
                }
            },
            showThree() {
                this.$emit('onShowClick')
                if (this.isShowThree) {
                    this.isShowThree = false
                } else {
                    this.isShowOne = false
                    this.isShowTwo = false
                    this.isShowThree = true
                }
            },
        }
    }
</script>
<style lang="scss" scoped>
    .lobby-item {
        /*min-height: 7.8125rem;*/
        background-color: #fff;
        display: flex;
        flex-direction: column;
    }

    .item-content {
        display: flex;
        flex-direction: row;
        flex: 1;
        border-bottom: .0625rem solid #f7f7f7;
        padding: 0 .625rem;
        .lottery-logo {
            display: flex;
            justify-content: center;
            align-items: center;
            img {
                width: 4rem;
                height: 4rem;
                margin-left: 10px;
            }
        }
        .item-info {
            display: flex;
            flex: 1;
            flex-direction: column;
            padding: .625rem 0 .625rem .625rem;
            .info-row {
                display: flex;
                flex: 1;
                flex-direction: row;
                justify-content: space-between;
                align-items: center;
                .lottery-name {
                    font-size: .9rem;
                    font-weight: 400;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    display: -webkit-box;
                    -webkit-box-orient: vertical;
                    -webkit-line-clamp: 2;
                    margin-right: 10px;
                }
                .arrow-right {
                    width: .5625rem;
                    height: .9375rem;
                    margin-right: 5px;
                }
                .issue-no {
                    font-size: .6875rem;
                    color: #666;
                }
                .item-cd {
                    font-size: .6875rem;
                    color: red;
                    margin-right: 5px;
                    margin-top: 5px;
                }
            }
            .nums {
                margin-top: -5px;
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                span {
                    font-size: .875rem;
                    color: #db3d3c;
                    padding-right: .1875rem;
                }
            }
        }
    }

    .lobby-item .funs-wrap .funs-list .fun + .fun {
        border-left: .0625rem solid #f7f7f7;
    }

    .funs-wrap {
        padding: .5rem;
        background-color: #ffffff;
        border-bottom: 5px solid #f7f7f7;
    }

    .funs-list {
        flex: 1;
        display: flex;
        justify-content: center;
    }

    .fun {
        flex: 1;
        display: flex;
        justify-content: center;
        .fun-logo {
            height: .9375rem;
            width: .9375rem;
        }
        span {
            font-size: .75rem;
            color: #333;
            padding-left: .5rem;
        }
    }

    .fun-info {
        background-color: #f7f7f7;
        padding: 5px 20px;
        font-size: .9rem;
        font-weight: 400;
        line-height: 25px;
        z-index: 99;
    }
</style>
