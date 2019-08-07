<template>
    <div class="wrapper">
        <ul class="lists">
            <li class="list">
                <label for="" class="detail">起拍价</label>
                <input type="number" placeholder="0.00">
            </li>
            <li class="list">
                <label for="" class="detail">保证金</label>
                <input type="number" placeholder="0.00">
            </li>
            <li class="list">
                <label for="" class="detail">加价幅度</label>
                <input type="number" placeholder="0.00">
            </li>
            <li class="list">
                <label for="" class="detail">开拍时间</label>
                <div class="price">请选择开拍时间</div>
            </li>
            <li class="list">
                <label for="" class="detail">结束时间</label>
                <div class="price">请选择结束时间</div>
            </li>
            <van-cell-group title="分类">
                <van-cell class="order-coupon" :title="type" is-link arrow-direction="down" @click="showList = true"/>
            </van-cell-group>
            <van-popup v-model="showList" position="bottom">
                <van-picker :columns="types" @change="onType"/>
            </van-popup>
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
    import {Field, Picker, Popup, Button} from 'vant';

    export default {
        props: {
            kind: {
                type: String
            },
            types:Array
        },
        data() {
            return {
                oldPrice: '',
                newPrice: '',
                sendPrice: '',
                showList: false,
                type: ''
            }
        },
        methods: {
            tokind() {
                let category = document.querySelector('.category')
                console.log(category)
                category.style.display = 'block'
            },
            onType(picker, value, index) {
                this.type = value
                this.showList = false
            },
        },
        components: {
            [Field.name]: Field,
            [Popup.name]: Popup,
            [Button.name]: Button,
            [Picker.name]: Picker
        }
    }
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
            padding-left 1rem
            padding-right 1rem
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
            input
                outline none
                border none
                margin-left 1rem
                box-sizing border-box
        .bottom
            width 100%
            height 5rem
            background-color #f3f3f3
            padding 1.0rem
            box-sizing border-box
            .info
                font-size 0.9rem
                color #333333
                margin-bottom 0.8rem
            .yutang
                width 100%
                height 2rem
                background-color #fff
                line-height 2rem
                text-align left
                font-size 0.9rem
                font-weight 500
                .big
                    color #000000
                .little
                    color #888888
</style>
