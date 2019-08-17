<template>
    <div class="pricebox">
        <div class="price">
            <div class="inputprice">
                <label for="">报名费用：</label>
                <input type="number" v-model="fee" placeholder="默认50元">
            </div>
            <div class="inputprice">
                <label for="">人数限制：</label>
                <input type="number" v-model="limited" placeholder="默认5人">
            </div>
            <div class="inputprice">
                <label for="">奖金分成：</label>
                <input type="number" v-model="reword" placeholder="默认总报名费的80%">
            </div>
            <div class="inputprice">
                <label for="">信用要求：</label>
                <input type="number" v-model="credit" placeholder="默认600分以上">
            </div>
            <div class="inputprice">
                <label for="">比赛时间：</label>
                <input type="datetime-local" v-model="activityTime">
            </div>
            <div class="addpop" v-if="isShowPop">
                <input type="text" v-model="customInput" placeholder="请添加属性名称">
            </div>
            <div class="inputprice" style="background-color: white;padding: 5px 0" @click="addPop">
                <label for="">添加自定义选项</label>
                <img src="../../../assets/images/add.png" v-if="!isShowPop"/>
                <img src="../../../assets/images/confirm.png" v-else=""/>
            </div>
        </div>
        <div class="bottom" @click="showSelectFishponds">
            <p class="info">请选择或者添加鱼塘</p>
            <div class="yutang" v-if="item.FishPondsInfo==undefined">
                <span class="little">钓鱼比赛需要选择鱼塘进行哦</span>
            </div>
            <fishpondsSelectItem :item="item" v-if="item.FishPondsInfo!=undefined"
                                 style="background-color: white;margin-bottom: 70px"></fishpondsSelectItem>
        </div>
    </div>
</template>

<script>
    import {Field, Picker, Popup, Button} from 'vant';
    import {EventBus} from '../../../utils/event-bus'
    import fishpondsSelectItem from '@/components/itemadapter/fishpondsSelectItem';

    export default {
        props: {
            item: Object
        },
        data() {
            return {
                fee: '',
                limited: '',
                reword: '',
                credit: '',
                activityTime: '',
                customInput: '',
                type: 8,
                isShowPop: false
            }
        },
        mounted() {
        },
        beforeDestroy() {
        },
        methods: {
            showSelectFishponds() {
                this.$emit('showSelect')
            },
            addPop() {
                if (this.isShowPop) {
                    this.isShowPop = false
                    if (this.customInput) {
                        console.log('有输入')
                    } else {
                        console.log('没有输入')
                    }
                    this.customInput = ''
                } else {
                    this.isShowPop = true
                }
            }
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
                justify-content space-between
                margin-bottom 10px
                label
                    flex 2
                input
                    flex 5
                    outline none
                    border none
                    margin-left 0rem
                    box-sizing border-box
                img
                    width 20px
                    height 20px
            .addpop
                width 100%
                display flex
                align-items left
                justify-content space-between
                margin-bottom 10px
                input
                    flex 5
                    outline none
                    border none
                    margin-left 0rem
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
