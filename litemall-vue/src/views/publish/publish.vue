<template>
    <div class="anim" :class="{'active':active}">
        <div class="head">
            <h2 class="guide">闲鱼赚钱指南</h2>
            <ul class="navbar">
                <li class="li">
                    <p class="title">卖什么更赚</p>
                    <p class="tip">教你卖什么更赚钱</p>
                </li>
                <li class="li">
                    <p class="title">拍照相机</p>
                    <p class="tip">看看家里的东西值多少钱</p>
                </li>
                <li class="li">
                    <p class="title">淘宝一键转卖</p>
                    <p class="tip">剁手多了回回血</p>
                </li>
            </ul>
        </div>
        <div class="content">
            <div class="type" @click="tosell">
                <div class="image">
                    <img src="../../assets/images/收纳盒.png" alt="">
                </div>
                <p class="explain">卖闲置</p>
            </div>
            <div class="type">
                <div class="image">
                    <img src="../../assets/images/房子.png" alt="">
                </div>
                <p class="explain">出租房屋</p>
            </div>
            <div class="type">
                <div class="image">
                    <img src="../../assets/images/头像.png" alt="">
                </div>
                <p class="explain">发布服务</p>
            </div>
        </div>
        <div class="closebox">
            <span class="close" @click="goBack"></span>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                active: false
            }
        },
        computed: {},
        mounted() {
            this.active = false
        },
        methods: {
            tosell() {
                this.$router.push({
                    path: '/sell'
                })
            },
            goBack() {
                this.active = true
                setTimeout(()=> {
                    this.$router.back(-1)
                },500)
            },
        },
    }
</script>

<style lang="stylus" scoped>
.anim
    animation enter 0.5s
    &.active
        animation out 1s
    @keyframes enter
        0%
            transform scale(0)
            opacity 0
        100%
            transform scale(1)
            opacity 1
    @keyframes out
        0%
            transform scale(1)
        100%
            transform scale(0)
            opacity 0
    .head
        width 100%
        .guide
            margin-top 2rem
            font-size 1rem
            width 100%
            color #000000
            padding-left 1rem
            box-sizing border-box
        .navbar
            margin-top 1rem
            width 100%
            padding-left 1rem
            display flex
            justify-content space-around
            list-style none
            .li
                margin-right 1rem
                .title
                    font-size 0.9rem
                    font-weight 500
                    color #000
                    margin-bottom 0.5rem
                    &:after
                        content ''
                        display inline-block
                        height 0.5rem
                        width 0.5rem
                        border-width: 2px 2px 0 0;
                        border-color: #ffda44;
                        border-style: solid;
                        transform: rotate(45deg);
                        position: relative;
                        margin-left: 0.2rem;
                .tip
                    font-size 0.8rem
                    color #888888

    .content
        width 100%
        display flex
        margin-top 5rem
        transition all .7s ease-in
        .type
            flex 1
            display flex
            flex-direction column
            align-items center
            .image
                width 4rem
                height 4rem
                background-color #ffda44
                border-radius 50%
                display flex
                justify-content center
                align-items center
                img
                    width 3rem
                    height 3rem
                    vertical-align middle
            .explain
                margin-top 1rem
                color #333333
                font-size 0.8rem

    .closebox
        width 4rem
        height 4rem
        position fixed
        bottom 10px
        left 43%
        display flex
        justify-content center
        align-items center
        .close
            width 4rem
            height 4em
            background url('../../assets/images/X.png') no-repeat
            background-size 3rem 3rem

</style>
