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
    .lobby-item .funs-wrap .funs-list .fun + .fun {
        border-left: .0625rem solid #dbdbdb;
    }

    .lobby-item {
        height: 7.8125rem;
        background-color: #fff;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -webkit-flex-direction: column;
        -ms-flex-direction: column;
        flex-direction: column;
    }

    .lobby-item .item-content {
        display: flex;
        -webkit-box-orient: horizontal;
        -webkit-box-direction: normal;
        -webkit-flex-direction: row;
        -ms-flex-direction: row;
        flex-direction: row;
        -webkit-box-flex: 1;
        -webkit-flex: 1;
        -ms-flex: 1;
        flex: 1;
        -webkit-box-align: stretch;
        -webkit-align-items: stretch;
        -ms-flex-align: stretch;
        align-items: stretch;
        border-bottom: .0625rem solid #d4d4d4;
        padding: 0 .625rem;
    }

    .lobby-item .item-content .lottery-logo {
        display: flex;
        -webkit-box-pack: center;
        -webkit-justify-content: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
    }

    .lobby-item .item-content .lottery-logo img {
        width: 3.75rem;
        height: 3.75rem;
    }

    .lobby-item .item-content .item-info {
        display: flex;
        -webkit-flex: 1;
        -ms-flex: 1;
        flex: 1;
        -webkit-box-orient: vertical;
        -webkit-flex-direction: column;
        -ms-flex-direction: column;
        flex-direction: column;
        padding: .625rem 0 .625rem .625rem;
    }

    .lobby-item .item-content .item-info > .info-row {
        display: flex;
        -webkit-flex: 1;
        -ms-flex: 1;
        flex: 1;
        -webkit-box-orient: horizontal;
        -webkit-flex-direction: row;
        -ms-flex-direction: row;
        flex-direction: row;
        -webkit-box-pack: justify;
        -webkit-justify-content: space-between;
        -ms-flex-pack: justify;
        justify-content: space-between;
        -webkit-box-align: center;
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
    }

    .lobby-item .item-content .item-info .nums {
        margin-top: -5px;
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: horizontal;
        -webkit-box-direction: normal;
        -webkit-flex-direction: row;
        -ms-flex-direction: row;
        flex-direction: row;
        -webkit-box-pack: start;
        -webkit-justify-content: flex-start;
        -ms-flex-pack: start;
        justify-content: flex-start;
    }

    .lobby-item .item-content .item-info > .info-row .lottery-name {
        font-size: .9375rem;
        color: #000;
    }

    .lobby-item .item-content .item-info > .info-row .arrow-right {
        width: .5625rem;
        height: .9375rem;
    }

    .lobby-item .item-content .item-info .nums > span {
        font-size: .875rem;
        color: #db3d3c;
        padding-right: .1875rem;
    }

    .lobby-item .item-content .item-info > .info-row .issue-no {
        font-size: .6875rem;
        color: #666;
    }

    .lobby-item .item-content .item-info > .info-row .item-cd {
        font-size: .6875rem;
        color: red;
    }

    .lobby-item .funs-wrap {
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        height: 2.25rem;
        padding: .375rem;
        background-color: #ffffff;
    }

    .lobby-item .funs-wrap .funs-list,
    .lobby-item .funs-wrap .funs-list .fun {
        -webkit-box-flex: 1;
        -webkit-flex: 1;
        -ms-flex: 1;
        flex: 1;
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: horizontal;
        -webkit-box-direction: normal;
        -webkit-flex-direction: row;
        -ms-flex-direction: row;
        flex-direction: row;
    }

    .lobby-item .funs-wrap .funs-list .fun {
        -webkit-box-pack: center;
        -webkit-justify-content: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
    }

    .lobby-item .funs-wrap .funs-list .fun .fun-logo {
        height: .9375rem;
        width: .9375rem;
    }

    .lobby-item .funs-wrap .funs-list .fun span {
        font-size: .75rem;
        color: #333;
        padding-left: .5rem;
    }
</style>
