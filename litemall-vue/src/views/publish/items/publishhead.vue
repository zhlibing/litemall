<template>
    <div class="container">
        <div v-show="!isShowSelect">
            <appbar :titleText="appbartitle"></appbar>
            <div class="inputcontainer">
                <div class="inputtitle border-1px">
                    <input type="text" v-model="title" placeholder="标题 品类品牌型号都是买家喜欢搜索的">
                </div>
                <div class="inputdetail border-1px">
                    <textarea type="text" v-model="desc" placeholder="内容 描述一下你的活动吧"></textarea>
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
            <div class="goodinfo">
                <batfree :item="itemf" ref="batfree" @showSelect="showSelect" v-if="index==0"></batfree>
            </div>
            <div class="footer">
                <button class="fabu" @click="publish">确定发布</button>
            </div>
        </div>
        <fishpondsSelect @selectItem="selectItem" v-show="isShowSelect"></fishpondsSelect>
    </div>
</template>

<script>
    import appbar from '@/components/head/appbar'
    import {
        circleSave,
        storageUpload,
        activitySave,
        fishpondsSave,
        groupSave,
        questionSave
    } from '@/api/api'
    import {EventBus} from '../../../utils/event-bus'
    import _ from 'lodash';
    import batfree from '../components/batfree.vue'
    import fishpondsSelect from '../../../views/items/fishpondsSelect-list/indexSelect.vue'

    export default {
        data() {
            return {
                appbartitle: '',
                title: '',
                desc: '',
                hasPhoto: true,
                imgUrls: [],
                itemf: {},
                isShowSelect: false,
                index: '',
            }
        },
        watch: {
            imgUrls: 'showAddpic'
        },
        methods: {
            publish() {
                if (this.title == '') {
                    this.$toast('请输入发布的标题')
                    return
                }
                if (this.desc == '') {
                    this.$toast('描述一下宝贝吧')
                    return
                }
                let obj = {}
                obj.title = this.title
                obj.content = this.desc
                obj.description = this.desc
                obj.collectionCount = '1'
                obj.limited = '1'
                obj.km = '1'
                obj.picUrls = this.imgUrls
                if (this.itemf.FishPondsInfo == undefined) {
                    this.$toast('请选择一个鱼塘吧')
                    return
                }
                if (this.itemf.FishPondsInfo != undefined) {
                    obj.fishpondsId = this.itemf.FishPondsInfo.id
                }
                obj.type = this.$refs.batfree.type
                    || this.$refs.batpk.type
                    || this.$refs.batchallenge.type
                    || this.$refs.publishquestion.type
                    || this.$refs.publishcircle.type
                    || this.$refs.publishgroup.type
                    || this.$refs.publishfishponds.type
                if (obj.type == 4) {
                    circleSave(obj).then(res => {
                        if (res.status === 200) {
                            this.goBack()
                            EventBus.$emit("circleSave", {
                                num: '99',
                                deg: '00'
                            });
                        }
                    });
                }
                if (obj.type == 5) {
                    fishpondsSave(obj).then(res => {
                        if (res.status === 200) {
                            this.goBack()
                            EventBus.$emit("circleSave", {
                                num: '99',
                                deg: '00'
                            });
                        }
                    });
                }
                if (obj.type == 6) {
                    questionSave(obj).then(res => {
                        if (res.status === 200) {
                            this.goBack()
                            EventBus.$emit("circleSave", {
                                num: '99',
                                deg: '00'
                            });
                        }
                    });
                }
                if (obj.type == 7) {
                    groupSave(obj).then(res => {
                        if (res.status === 200) {
                            this.goBack()
                            EventBus.$emit("circleSave", {
                                num: '99',
                                deg: '00'
                            });
                        }
                    });
                }
                if (obj.type == 8) {
                    activitySave(obj).then(res => {
                        if (res.status === 200) {
                            this.goBack()
                            EventBus.$emit("circleSave", {
                                num: '99',
                                deg: '00'
                            });
                        }
                    });
                }
                if (obj.type == 11) {
                }
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
                console.log(item, '-------232')
                this.itemf = item
                this.isShowSelect = false
            },
            showSelect() {
                console.log('-------showSelect')
                if (this.isShowSelect) {
                    this.isShowSelect = false
                } else {
                    this.isShowSelect = true
                }
            }
        },
        mounted() {
            if (_.has(this.$route.params, 'index')) {
                this.index = this.$route.params.index;
                this.appbartitle = "发布" + this.$route.params.title;
            }
        },
        components: {
            appbar,
            batfree,
            fishpondsSelect
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
        .inputcontainer
            width 100%
            .inputtitle
                width 100%
                border-1px(#f8f8f8)
                border-bottom: 1px solid #f7f7f7;
                input
                    border none
                    outline none
                    width 100%
                    height 2rem
                    padding-left 1rem
                    box-sizing border-box
                    font-size 0.9rem
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
                        object-fit cover
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

        .goodinfo
            width 100%
            padding-top 1rem
            box-sizing border-box
            margin-bottom 2rem
            .border
                width 90%
                margin 0 auto
                border 1px solid #f3f3f3
                border-radius .6rem
                height 2rem
                .topname
                    display flex
                    height 2rem
                    align-items center
                    justify-content center
                    flex 1
                    .item
                        width 30%
                        text-align center
                        color #000000
                        font-size 0.9rem

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
