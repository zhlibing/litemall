<template>
    <div class="wrapper">
        <ul class="lists">
            <li class="list">
                <label for="" class="detail">起拍价</label>
                <div class="price">0.00</div>
            </li>
            <li class="list">
                <label for="" class="detail">保证金</label>
                <div class="price">0.00</div>
            </li>
            <li class="list">
                <label for="" class="detail">加价幅度</label>
                <div class="price">0.00</div>
            </li>
            <li class="list">
                <label for="" class="detail">开拍时间</label>
                <div class="price">请选择开拍时间</div>
            </li>
            <li class="list">
                <label for="" class="detail">结束时间</label>
                <div class="price">请选择结束时间</div>
            </li>
            <li class="list">
                <label for="" class="detail">分类</label>
                <div class="price">请选择分类</div>
            </li>
        </ul>
        <div class="bottom">
            <p class="info">把该宝贝同步到</p>
            <div class="yutang">
                <span class="big">鱼塘</span>
                <span class="little">:加入鱼塘，更多曝光</span>
            </div>
        </div>
    </div>
</template>

<script>
    export default {}
</script>

<style lang="stylus" scoped>
    .wrapper
        width 100%
        display block
        padding 1em
        box-sizing border-box
        .list
            display flex
            height 2rem
            align-items center
            font-size 0.9rem
            color #000000
            font-weight 500
            border-bottom 1px solid #f3f3f3
            position relative
            &:last-child
                border-bottom none
            .detail
                width 4rem
            .price
                margin-left 1rem
                color #666666
        .bottom
            width 100%
            height 5rem
            background-color #f3f3f3
            padding 0.0rem
            box-sizing border-box
            .info
                font-size 0.9rem
                color #888888
                margin-bottom 0.8rem
            .yutang
                width 100%
                height 1rem
                background-color #fff
                line-height 1rem
                text-align left
                font-size 0.9rem
                font-weight 500
                .big
                    color #000000
                .little
                    color #888888
</style>
