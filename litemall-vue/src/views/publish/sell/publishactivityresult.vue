<template>
    <div class="container">
        <div v-show="!isShowSelect">
            <appbar titleText="上传结果"></appbar>
            <div class="inputcontainer">
                <div class="inputdetail border-1px">
                    <textarea type="text" v-model="desc" placeholder="想说点啥..."></textarea>
                </div>
            </div>
            <div class="image-list">
                <div class="list-img" @click="addPic" v-show="hasPhoto">
                    <img src="../../../assets/images/相机.png" alt="">
                    <span class="choosephoto">请选择或者拍照上传照片</span>
                    <input ref="file" type="file" hidden accept="image/jpeg,image/jpg,image/png" capture="camera"
                           @change="fileInput">
                </div>
                <ul class="list-ul" v-show="!hasPhoto">
                    <li class="list-li" v-for="(url, index) in imgUrls" :key="index">
                        <img :src="url" alt="">
                        <span class="cancelimg" @click="delImage(index)"></span>
                    </li>
                    <li class="list-li-add">
                        <span class="add-img" @click.stop="addPic"></span>
                    </li>
                </ul>
            </div>
            <div class="inputweight">
                <label for="">TA的钓鱼总重量</label>
                <input type="number" v-model="weight" placeholder="单位斤">
            </div>
            <div class="inputmore">
                <label for="" style="color: red">TA比我</label>
                <div class="select">
                    <input type="radio" v-model="isWin" value="2"/>多
                    <input type="radio" v-model="isWin" value="1" style="margin-left: 50px"/>少
                </div>
            </div>
            <div class="bottom" @click="showSelect">
                <p class="info">请选择一个参加比赛的选手，不能是自己</p>
                <div class="yutang" v-if="itemf.user==undefined">
                    <span class="little">钓鱼比赛需要选择对手进行裁判哦</span>
                </div>
                <userSelectItem :item="itemf" v-if="itemf.user!=undefined"
                                style="background-color: white;margin-bottom: 70px"></userSelectItem>
            </div>
            <div class="footer">
                <button class="fabu" @click="publish">确定发布</button>
            </div>
        </div>
        <userSelect @selectItem="selectItem" :itemId="itemId" :userId="userId" v-show="isShowSelect"></userSelect>
    </div>
</template>

<script>
    import appbar from '@/components/head/appbar'
    import {
        activityJustice,
        commentSave,
        storageUpload,
    } from '@/api/api'
    import {EventBus} from '../../../utils/event-bus'
    import _ from 'lodash';
    import userSelectItem from '../../../components/itemadapter/userSelectItem'
    import userSelect from '../../../views/items/userSelect-list/indexSelect.vue'

    export default {
        props: {},
        data() {
            return {
                desc: '',
                hasPhoto: true,
                imgUrls: [],
                type: '',
                itemId: '',
                userId: '',
                itemf: {},
                isShowSelect: false,
                weight: '',
                isWin: -1,
            }
        },
        watch: {
            imgUrls: 'showAddpic'
        },
        methods: {
            publish() {
                if (!this.weight) {
                    this.$toast('请输入他的钓鱼总重量')
                    return
                }
                if (this.isWin < 0) {
                    this.$toast('请选择多还是少哦')
                    return
                }
                let obj = {}
                if (this.isWin == 2) {
                    obj.content = this.desc + '...我输给@' + this.itemf.user.username + '，他太厉害了~'
                } else {
                    obj.content = this.desc + '...我赢啦@' + this.itemf.user.username + '，快来挑战吧~'
                }
                obj.picUrls = this.imgUrls
                obj.type = this.type
                obj.valueId = this.itemId

                let activityUser = {}
                activityUser.activityId = this.itemId
                activityUser.userId = this.itemf.user.id
                activityUser.isWin = this.isWin
                activityUser.weight = this.weight
                activityUser.fromUserId = this.userId

                commentSave(obj).then(res => {
                    console.log(res, '>>>>commentSave')
                    if (res.status === 200) {
                        this.goBack()
                        EventBus.$emit("commentSave", {
                            num: '99',
                            deg: '00'
                        });
                    }
                });

                activityJustice(activityUser).then(res => {
                    console.log(res, '>>>>activityUser')
                    if (res.status === 200) {
                    }
                });
            },
            showAddpic() {
                if (this.imgUrls.length >= 1) {
                    this.hasPhoto = false
                } else {
                    this.hasPhoto = true
                }
            },
            goBack() {
                this.$router.back(-1)
            },
            fileInput(e) {
                let files = e.target.files
                if (!files.length) return
                console.log(files, '>>>>>>fileinput')
                this.toUpload(files[0])
            },
            delImage(index) {
                this.imgUrls.splice(index, 1)
            },
            addPic() {
                let file = this.$refs.file
                file.click()
            },
            toUpload(file) {
                console.log(file, '>>>>>toUpload')
                storageUpload(file).then(res => {
                    console.log(res, '>>>>>storageUpload')
                    if (res.status === 200) {
                        if (res.data.errno == 0) {
                            this.imgUrls.push(res.data.data.url)
                        }
                    }
                });
            },
            selectItem(item) {
                this.itemf = item
                this.isShowSelect = false
            },
            showSelect() {
                if (this.isShowSelect) {
                    this.isShowSelect = false
                } else {
                    this.isShowSelect = true
                }
            }
        },
        mounted() {
            if (_.has(this.$route.params, 'type')) {
                this.type = this.$route.params.type;
                this.itemId = this.$route.params.itemId;
                this.userId = this.$route.params.userId;
            }
        },
        components: {
            appbar,
            userSelectItem,
            userSelect
        }
    }
</script>

<style lang="stylus" scoped>
    .router-link-active
        border-radius 1rem
        height 2rem
        line-height 2rem
        background-color #ffda44

    .container
        width 100%
        height 100%
        background-color #ffffff
        .inputcontainer
            width 100%
            .inputdetail
                width 100%
                border-1px(#f8f8f8)
                border-bottom: 1px solid #f7f7f7;
                textarea
                    border none
                    outline none
                    width 100%
                    height 7rem
                    padding-left 1rem
                    box-sizing border-box
                    font-size 0.9rem
                    rows 6
                    vertical-align top
        .image-list
            width 100%
            height 100%
            background-color #f8f8f8
            .list-img
                display flex
                height 7rem
                flex-direction column
                align-items center
                justify-content center
                img
                    width 3rem
                    height 3rem
                    display block
                    margin-bottom 0.5rem
                .choosephoto
                    font-size 0.8rem
                    color #888888
            .list-ul
                width 100%
                height auto
                padding .8rem .8rem
                background-color #f8f8f8
                box-sizing border-box
                display flex
                flex 1
                list-style none
                flex-wrap wrap
                li
                    width 25%
                    height 4rem
                    position relative
                    margin-bottom 1rem
                    img
                        width 4rem
                        height 4rem
                    .cancelimg
                        width 1.5rem
                        height 1.5rem
                        border-radius 50%
                        display block
                        background url('../../../assets/images/X.png') no-repeat
                        background-size cover
                        position absolute
                        top -0.23rem
                        left -0.13rem
                .list-li-add
                    display flex
                    align-items center
                    justify-content flex-start
                    .add-img
                        width 2rem
                        height 2rem
                        display block
                        border 1px solid #333333
                        background url('../../../assets/images/add.png') no-repeat
                        background-size 1rem 1rem
                        background-position center
        .inputweight
            width 100%
            display flex
            align-items left
            justify-content space-between
            margin-bottom 10px
            padding-left 15px
            label
                flex 3
            input
                flex 5
                outline none
                border none
                margin-left 0rem
                box-sizing border-box
            img
                width 20px
                height 20px
        .inputmore
            width 100%
            display flex
            align-items left
            justify-content space-between
            margin-bottom 10px
            padding-left 15px
            label
                flex 3
            .select
                flex 5
                display flex
                input
                    outline none
                    border none
                    margin-left 0rem
                    box-sizing border-box
            img
                width 20px
                height 20px
        .bottom
            width 100%
            background-color #f3f3f3
            box-sizing border-box
            padding 0 15px
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

        .footer
            width 100%
            box-sizing border-box
            padding .2rem .2rem
            position fixed
            bottom 0
            height 3rem
            display flex
            align-items center
            justify-content center
            background-color #fff
            .fabu
                width 100%
                height 100%
                outline none
                border none
                color #ffffff
                background-color red
</style>
