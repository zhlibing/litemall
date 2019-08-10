<template>
    <div class="lobby-item" @click="$emit('onToLotteryIndex',21)">
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
                <div class="fun">
                    <img src="../../assets/images/trend.png" class="fun-logo"/>
                    <span>头号玩家</span>
                </div>
                <div class="fun">
                    <img src="../../assets/images/rule.png" class="fun-logo"/>
                    <span>玩法规则</span>
                </div>
                <div class="fun">
                    <img src="../../assets/images/shouCang.png" class="fun-logo"/>
                    <span>加入收藏</span>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        props: {
            itemClass: Object,
        },
        methods: {}
    }
</script>
<style lang="scss" scoped>
    .lobby-item {
        height: 7.8125rem;
        background-color: #fff;
        margin-top: 20px;
        display: flex;
        flex-direction: column;
    }

    .item-content {
        display: flex;
        flex-direction: row;
        flex: 1;
        border-bottom: .0625rem solid #ededed;
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
        border-left: .0625rem solid #ededed;
    }

    .lobby-item .funs-wrap {
        height: 3rem;
        padding: .5rem;
        background-color: #ffffff;
        border-bottom: 5px solid #ededed;
    }

    .lobby-item .funs-wrap .funs-list,
    .lobby-item .funs-wrap .funs-list .fun {
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
</style>
