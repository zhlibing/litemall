<template>
    <div class="container">
        <appbar titleText="发布"></appbar>
        <div class="inputcontainer">
            <div class="inputtitle border-1px">
                <input type="text" v-model="title" placeholder="标题 品类品牌型号都是买家喜欢搜索的">
            </div>
            <div class="inputdetail border-1px">
                <textarea type="text" v-model="desc" placeholder="描述一下你的闲置"></textarea>
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
            <div class="border">
                <div class="topname">
                    <router-link to="/publish/sell/price" tag="div" class="item">
                        <span>开个价</span>
                    </router-link>
                    <router-link to="/publish/sell/uction" tag="div" class="item">
                        <span>拍卖</span>
                    </router-link>
                    <div class="item" @click="noprice">不谈钱</div>
                </div>
            </div>
            <router-view :kind="kind" ref="price"></router-view>
        </div>
        <div class="footer">
            <button class="fabu" @click="publish">确定发布</button>
        </div>
        <div class="category" ref="category">
            <div class="head">
                <div class="back" @click="hidden">
                    <img src="../../../assets/images/X.png" alt="">
                </div>
                <div class="title">类目</div>
            </div>
            <div class="wrapper">
                <div class="item border-1px" v-for="(item,index) in category" :key="index" @click="selectItem(item)">
                    {{item.name}}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import util from '../../../assets/utils/utils.js'
    import appbar from '@/components/head/appbar'

    export default {
        data() {
            return {
                category: [],
                title: '',
                desc: '',
                hasPhoto: true,
                imgUrls: [],
                kind: ''

            }
        },
        watch: {
            imgUrls: 'showAddpic'
        },
        methods: {
            publish() {
                let oldPrice = this.$refs.price.oldPrice
                let newPrice = this.$refs.price.newPrice
                let sendPrice = this.$refs.price.sendPrice
                if (this.title == '') {
                    this.$toast('请输入发布的标题')
                    return
                }
                if (this.desc == '') {
                    this.$toast('描述一下宝贝吧')
                    return
                }
                if (this.imgUrls == '') {
                    console.log('上传几张宝贝图片吧~~如果无法上传，请确定是否开启拍照权限，如果仍无效果，请移步其他浏览器')
                    return
                }
                if (newPrice == '') {
                    this.$toast('请输入价格')
                    return
                }
                if (oldPrice == '') {
                    this.$toast('请输入原价')
                    return
                }
                if (sendPrice == '') {
                    this.$toast('邮费不能为空')
                    return
                }
                if (this.kind == '') {
                    this.$toast('请选择商品类型')
                    return
                }
                console.log('发布成功，去看看吧！').then(action => {
                    let obj = {}
                    obj.title = this.title
                    obj.desc = this.desc
                    obj.imgUrls = this.imgUrls
                    obj.newPrice = newPrice
                    obj.oldPrice = oldPrice
                    obj.sendPrice = sendPrice
                    obj.del = true
                    obj.kind = this.kind
                    obj.time = util.formatDate.format(new Date(), 'yyyy-MM-dd hh:mm')
                    this.$store.dispatch('setFabunum')
                    this.$store.dispatch('setFabuinfo', obj)
                    this.$router.push('/my')
                    this.$store.dispatch('setCurIndex', 4)
                    console.log(obj)
                });
            },
            selectItem(item) {
                this.kind = item.name
                this.hidden()
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
            hidden() {
                let category = this.$refs.category
                category.style.display = 'none'
            },
            fileInput(e) {
                let files = e.target.files
                if (!files.length) return
                this.createImage(files, e)
            },
            createImage(files, e) {
                console.log(files, '>>>>')
                this.imgUrls.push(files[0])
                console.log(this.imgUrls)
            },
            delImage(index) {
                this.imgUrls.splice(index, 1)
            },
            addPic() {
                let file = this.$refs.file
                file.click()
            },
            noprice() {
                this.$toast('帖子仅能在鱼塘发布，你附近没有鱼塘，去别的地方转转吧~')
            }
        },
        mounted() {
        },
        components: {
            appbar,
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
                border-bottom: 1px solid #ededed;
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
                border-bottom: 1px solid #ededed;
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
                    height 2rem
                    position relative
                    margin-bottom 1rem
                    img
                        width 2rem
                        height 2rem
                    .cancelimg
                        width 0.66rem
                        height 0.66rem
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
            margin-top 1rem
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
        .category
            position fixed
            top 0
            left 0
            right 0
            bottom 0
            z-index 100
            background-color #f7f7f7
            width 100%
            height 100%
            display none
            .wrapper
                width 100%
                height 22rem
                overflow-y scroll
                .item
                    width 100%
                    padding-left 0.3rem
                    box-sizing border-box
                    border-1px(#f1f1f1)
                    height 1.5rem
                    line-height 1.5rem
                    font-size 0.3rem
                    color #333333

    .head
        position relative
        background-color #f3f3f3
        width 100%
        height 1.6rem
        display flex
        justify-content center
        align-items center
        .back
            position absolute
            left .2rem
            top .4rem
            width 1rem
            height 1rem
            img
                width .8rem
                height .8rem
        .title
            font-size .4rem
            font-weight 700
</style>
