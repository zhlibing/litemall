<template>
    <div class="container" v-if="news!==undefined&&news.info!==undefined">
        <appbar :titleText="news.info.content||news.info.description||title"></appbar>
        <div class="details">
            <div class="top">
                <div class="avatarbox">
                    <img :src="news.user.avatar" alt="" class="avatar">
                </div>
                <div class="info">
                    <div class="username">{{news.user.username}}</div>
                    <div class="ta">{{news.info.addTime + ' '}}{{"等级:" + news.user.userLevel}}</div>
                </div>
                <div class="pricebox">
                    <div class="price">{{news.info.type}}</div>
                </div>
            </div>
            <div class="descbox">
                <p class="desc">{{news.info.content || news.info.description}}</p>
            </div>
            <div class="img">
                <a href="javascript:;" v-for="picUrl in news.info.picUrls" style="display: inline-block">
                    <img :src="picUrl" alt="">
                </a>
            </div>
        </div>
        <div class="messagebox">
            <p class="msg border-1px">留言</p>
            <div class="list2n" v-for="(comment,index) in news.comment.data" :key="index"
                 v-show="news.comment.data!=undefined&&news.comment.data.length>0">
                <commentItem :comment="comment" :userId="userId" @deleteComment="deleteComment(index)"></commentItem>
            </div>
            <div class="comment" v-show="news.comment.data==undefined||news.comment.data.length==0">
                <img class="bg" src="../../../assets/images/is_empty.png" alt="">
                <p class="nomsg">还没有人留言，还不快来抢沙发</p>
            </div>
        </div>
        <div class="bottom">
            <div class="message" @click="toPublishComment">
                <img src="../../../assets/images/留言.png" alt="">
                <span class="leabmsg">留言</span>
            </div>
            <div class="givestar" @click="addCollect">
                <img src="../../../assets/images/赞.png" alt="" v-if="userHasCollect==0">
                <img src="../../../assets/images/赞1.png" alt="" v-else>
                <span class="star">点赞</span>
            </div>
            <div class="buy" @click="join" v-if="userHasJoin==-1">帮他擦一擦</div>
            <div class="buy" @click="join" v-else-if="userHasJoin==0">加入</div>
            <div class="buy" @click="join" v-else>退出</div>
        </div>
    </div>
</template>

<script>
    import appbar from '@/components/head/appbar'
    import commentItem from '../../../components/itemadapter/commentItem.vue'
    import {
        circleDetail,
        activityDetail,
        groupDetail,
        fishPondsDetail,
        questionDetail,
        collectAddOrDelete,
        activityJoin,
        activityQuit,
        groupJoin,
        groupQuit,
        fishpondsJoin,
        fishpondsQuit,
    } from '@/api/api';
    import {getLocalStorage} from '@/utils/local-storage';

    export default {
        props: {
            itemId: [String, Number],
            type: [String, Number],
        },
        data() {
            return {
                userHasCollect: 0,
                userHasJoin: -1,
                news: {},
                title: '详情',
                userId: ''
            }
        },
        methods: {
            deleteComment(index) {
                this.news.comment.data.splice(index, 1)
                console.log(index, 'deleteComment')
            },
            getUserInfo() {
                const infoData = getLocalStorage(
                    'userId'
                );
                this.userId = infoData.userId || "";
                console.log(infoData, '>>>>>>userId')
            },
            addCollect() {
                collectAddOrDelete({valueId: this.itemId, type: this.type}).then(res => {
                    if (this.news.userHasCollect === 1) {
                        this.news.userHasCollect = 0;
                        this.userHasCollect = 0;
                    } else {
                        this.news.userHasCollect = 1;
                        this.userHasCollect = 1;
                        this.$toast({
                            message: '收藏成功',
                            duration: 1500
                        });
                    }
                });
            },
            join() {
                if (this.type == 5) {
                    let obj = {}
                    obj.id = this.itemId
                    if (this.userHasJoin == 0) {
                        fishpondsJoin(obj).then(res => {
                            console.log(res, '>>>fishpondsJoin')
                            if (res.status === 200 && res.data.errno == 0) {
                                this.userHasJoin = 1
                                this.$toast({
                                    message: '加入成功',
                                    duration: 1500
                                });
                            }
                        });
                    } else {
                        fishpondsQuit(obj).then(res => {
                            console.log(res, '>>>fishpondsQuit')
                            if (res.status === 200 && res.data.errno == 0) {
                                this.userHasJoin = 0
                                this.$toast({
                                    message: '退出成功',
                                    duration: 1500
                                });
                            }
                        });
                    }
                }
                if (this.type == 7) {
                    let obj = {}
                    obj.id = this.itemId
                    if (this.userHasJoin == 0) {
                        groupJoin(obj).then(res => {
                            console.log(res, '>>>groupJoin')
                            if (res.status === 200 && res.data.errno == 0) {
                                this.userHasJoin = 1
                                this.$toast({
                                    message: '加入成功',
                                    duration: 1500
                                });
                            }
                        });
                    } else {
                        groupQuit(obj).then(res => {
                            console.log(res, '>>>groupQuit')
                            if (res.status === 200 && res.data.errno == 0) {
                                this.userHasJoin = 0
                                this.$toast({
                                    message: '退出成功',
                                    duration: 1500
                                });
                            }
                        });
                    }
                }
                if (this.type == 8) {
                    let obj = {}
                    obj.id = this.itemId
                    if (this.userHasJoin == 0) {
                        activityJoin(obj).then(res => {
                            console.log(res, '>>>activityJoin')
                            if (res.status === 200 && res.data.errno == 0) {
                                this.userHasJoin = 1
                                this.$toast({
                                    message: '加入成功',
                                    duration: 1500
                                });
                            }
                        });
                    } else {
                        activityQuit(obj).then(res => {
                            console.log(res, '>>>activityQuit')
                            if (res.status === 200 && res.data.errno == 0) {
                                this.userHasJoin = 0
                                this.$toast({
                                    message: '退出成功',
                                    duration: 1500
                                });
                            }
                        });
                    }
                }
            },
            toPublishComment() {
                this.$router.push({name: 'publishcomment', params: {type: this.type, itemId: this.itemId}})
            }
        },
        computed: {},
        created() {
            this.getUserInfo();
            if (this.type == 4) {
                circleDetail({id: this.itemId}).then(res => {
                    console.log(res, '>>>circleDetail')
                    this.news = res.data.data;
                    this.userHasCollect = res.data.data.userHasCollect
                    this.userHasJoin = -1
                });
            }
            if (this.type == 5) {
                fishPondsDetail({id: this.itemId}).then(res => {
                    console.log(res, '>>>circleDetail')
                    this.news = res.data.data;
                    this.userHasCollect = res.data.data.userHasCollect
                    this.userHasJoin = res.data.data.userHasJoin
                });
            }
            if (this.type == 6) {
                questionDetail({id: this.itemId}).then(res => {
                    console.log(res, '>>>circleDetail')
                    this.news = res.data.data;
                    this.userHasCollect = res.data.data.userHasCollect
                    this.userHasJoin = -1
                });
            }
            if (this.type == 7) {
                groupDetail({id: this.itemId}).then(res => {
                    console.log(res, '>>>circleDetail')
                    this.news = res.data.data;
                    this.userHasCollect = res.data.data.userHasCollect
                    this.userHasJoin = res.data.data.userHasJoin
                });
            }
            if (this.type == 8) {
                activityDetail({id: this.itemId}).then(res => {
                    console.log(res, '>>>circleDetail')
                    this.news = res.data.data;
                    this.userHasCollect = res.data.data.userHasCollect
                    this.userHasJoin = res.data.data.userHasJoin
                });
            }
        },
        mounted() {
            document.documentElement.scrollTop = document.body.scrollTop = 0
        },
        components: {
            appbar,
            commentItem,
        }
    }
</script>

<style lang="stylus" scoped>
    .container
        width 100%
        background-color #f7f7f7
        .details
            width 100%
            min-height 50%
            padding 0.5rem 1rem
            box-sizing border-box
            background-color #fff
            .top
                width 100%
                height 3rem
                display flex
                align-items center
                margin-top 1rem
                .avatarbox
                    width 3rem
                    height 3rem
                    .avatar
                        width 3rem
                        height 3rem
                        border-radius 50%
                .info
                    margin-left 0.6rem
                    flex 1
                    display flex
                    flex-direction column
                    .username
                        max-height: 50px;
                        color #000000
                        font-size 0.9rem
                        font-weight 600
                        margin-bottom 0.4rem
                        overflow: hidden;
                        text-overflow: ellipsis;
                        display: -webkit-box;
                        -webkit-line-clamp: 2;
                        -webkit-box-orient: vertical;
                        -webkit-line-clamp: 2;
                    .ta
                        color #777777
                        font-size 0.8rem
                .pricebox
                    .price
                        color red
                        font-size 0.8rem
                        font-weight 400
            .descbox
                width 100%
                margin-top 3rem
                .desc
                    font-size 0.9rem
                    color #000000
                    line-height 1rem
                    font-weight 500
            .img
                width 100%
                margin-top 0.4rem
                img
                    width 100%
        .messagebox
            margin-top 0.3rem
            background-color #fff
            padding .3rem 0rem
            .msg
                font-size 0.9rem
                color #000000
                font-weight 600
                padding-left 1rem
                height 1rem
                line-height 1rem
                border-1px(#f8f8f8)
                &:before
                    content ''
                    display inline-block
                    width 0.13rem
                    height 1rem
                    background-color #ffda44
                    margin-right 0.5rem
                    vertical-align baseline
            .list2n {
                &:nth-child(2n) {
                    background-color: #f5f5f5;
                }
            }
            .comment
                width 100%
                display flex
                flex-direction column
                align-items center
                padding .5rem 0
                .nomsg
                    margin-top 1rem
                    font-size 0.8rem
                    color #888888
        .bottom
            position fixed
            bottom 0
            left 0
            right 0
            height 2.6rem
            display flex
            align-items center
            background-color #ffffff
            z-index 100
            box-sizing border-box
            .message, .givestar, .buy
                display flex
                flex-direction column
            .message
                flex 1
                align-items center
                font-size 0.8rem
                font-weight 500
                img
                    width 1.2rem
                    height 1.2rem
            .givestar
                flex 2
                font-size 0.8rem
                font-weight 500
                img
                    width 1.2rem
                    height 1.2rem
            .buy
                flex 1
                background-color #d81e06
                height 2rem
                line-height 2rem
                text-align center
                color #ffffff
                font-size 0.9rem

</style>
