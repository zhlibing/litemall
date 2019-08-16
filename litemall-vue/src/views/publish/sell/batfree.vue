<template>
    <div class="pricebox">
        <div class="price">
            <div class="inputprice">
                <label for="">价格</label>
                <input type="number" v-model="newPrice" placeholder="0.00">
            </div>
            <div class="inputprice">
                <label for="">原价</label>
                <input type="number" v-model="oldPrice" placeholder="0.00">
            </div>
            <div class="inputprice">
                <label for="">运费</label>
                <input type="number" v-model="sendPrice" placeholder="0.00">
            </div>
        </div>
        <div class="bottom" @click="goSelectFishponds">
            <p class="info">请选择或者添加鱼塘</p>
            <div class="yutang" v-if="item.FishPondsInfo==undefined">
                <span class="little">钓鱼比赛需要选择鱼塘进行哦</span>
            </div>
            <fishpondsSelectItem :item="item" v-if="item.FishPondsInfo!=undefined"></fishpondsSelectItem>
        </div>
    </div>
</template>

<script>
    import {Field, Picker, Popup, Button} from 'vant';
    import {EventBus} from '../../../utils/event-bus'
    import fishpondsSelectItem from '@/components/itemadapter/fishpondsSelectItem';

    export default {
        props: {
            type: String
        },
        data() {
            return {
                oldPrice: '',
                newPrice: '',
                sendPrice: '',
                item: Object
            }
        },
        mounted() {
            EventBus.$on("selectItem", ({item}) => {
                this.$nextTick(() => {
                    this.item = item
                    this.$set(this.item)
                    console.log(this.item)
                })
            })
        },
        beforeDestroy() {
            console.log("beforeDestroy")
        },
        methods: {
            goSelectFishponds() {
                this.$router.push("/items/fishpondsSelect-list")
            },
        },
        components: {
            [Field.name]: Field,
            [Popup.name]: Popup,
            [Button.name]: Button,
            [Picker.name]: Picker,
            fishpondsSelectItem
        }
    }
</script>

<style lang="stylus" scoped>
    .pricebox
        width 100%
        padding 1em
        .price
            width 100%
            height 6rem
            padding-left 1rem
            padding-right 1rem
            display flex
            align-items center
            justify-content flex-start
            font-size 0.9rem
            flex-wrap wrap
            flex 1
            .inputprice
                width 100%
                display flex
                align-items left
                justify-content left
                input
                    outline none
                    border none
                    margin-left 1rem
                    box-sizing border-box
        .categorybox
            width 100%
            border-top 1px solid #f3f3f3
            box-sizing border-box
            tri-2px(#999)
            display flex
            align-items center
            font-size 0.9rem
            .kind
                color #000000
            .choose
                position absolute
                right 2rem
                color #888888
        .bottom
            width 100%
            height 5rem
            background-color #f3f3f3
            padding 1rem
            box-sizing border-box
            .info
                font-size 0.9rem
                color #333333
                margin-bottom 0.8rem
            .yutang
                width 100%
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
