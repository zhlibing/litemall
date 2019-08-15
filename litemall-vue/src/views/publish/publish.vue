<template>
    <div class="anim" :class="{'active':active}">
        <div class="head">
            <h2 class="guide">渔者赚钱指南</h2>
            <ul class="navbar">
                <li class="li">
                    <p class="title">一键发布</p>
                    <p class="tip">发布活动资源等，自由组合搭配</p>
                </li>
                <li class="li">
                    <p class="title">自由参加</p>
                    <p class="tip">玩法多样，资源丰富，自由参与</p>
                </li>
                <li class="li">
                    <p class="title">智能公证</p>
                    <p class="tip">诚信比赛，信用系统一路伴您</p>
                </li>
            </ul>
        </div>
        <div class="content">
            <div class="type" @click="tosell(0)">
                <div class="image">
                    <img src="../../assets/images/收纳盒.png" alt="">
                </div>
                <p class="explain">自由场</p>
            </div>
            <div class="type" @click="tosell(1)">
                <div class="image">
                    <img src="../../assets/images/房子.png" alt="">
                </div>
                <p class="explain">PK赛</p>
            </div>
            <div class="type" @click="tosell(2)">
                <div class="image">
                    <img src="../../assets/images/头像.png" alt="">
                </div>
                <p class="explain">挑战赛</p>
            </div>
        </div>
        <div class="content">
            <div class="type" @click="tosell(3)">
                <div class="image">
                    <img src="../../assets/images/收纳盒.png" alt="">
                </div>
                <p class="explain">渔场</p>
            </div>
            <div class="type" @click="tosell(4)">
                <div class="image">
                    <img src="../../assets/images/房子.png" alt="">
                </div>
                <p class="explain">心情</p>
            </div>
            <div class="type" @click="tosell(5)">
                <div class="image">
                    <img src="../../assets/images/头像.png" alt="">
                </div>
                <p class="explain">问答</p>
            </div>
        </div>
        <div class="content">
            <div class="type" @click="tosell(6)">
                <div class="image">
                    <img src="../../assets/images/收纳盒.png" alt="">
                </div>
                <p class="explain">战队</p>
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
            tosell(type) {
                if (type==0){
                    this.$router.push({
                        path: '/publish/sell/batfree/8',
                        params: {index: type}
                    })
                }
                if (type==1){
                    this.$router.push({
                        path: '/publish/sell/batpk/8',
                        params: {index: type}
                    })
                }
                if (type==2){
                    this.$router.push({
                        path: '/publish/sell/batchallenge/8',
                        params: {index: type}
                    })
                }
                if (type==3){
                    this.$router.push({
                        path: '/publish/sell/publishfishponds/5',
                        params: {index: type}
                    })
                }
                if (type==4){
                    this.$router.push({
                        path: '/publish/sell/publishcircle/4',
                        params: {index: type}
                    })
                }
                if (type==5){
                    this.$router.push({
                        path: '/publish/sell/publishquestion/6',
                        params: {index: type}
                    })
                }
                if (type==6){
                    this.$router.push({
                        path: '/publish/sell/publishgroup/7',
                        params: {index: type}
                    })
                }

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
        padding-bottom 1rem
        .guide
            font-size 1rem
            width 100%
            color #000000
            padding-left 1rem
            box-sizing border-box
        .navbar
            margin-top 0rem
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
        margin-top 0rem
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
