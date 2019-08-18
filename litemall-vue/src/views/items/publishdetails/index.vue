<template>
    <div class="container" v-if="news!==undefined&&news.info!==undefined">
        <appbar :titleText="news.info.content||news.info.description||title"></appbar>
        <div class="details">
            <div class="top" @click="goUserDetails(news.publishUser.user.id)">
                <div class="avatarbox">
                    <img :src="news.publishUser.user.avatar" alt="" class="avatar">
                </div>
                <div class="info">
                    <div class="username">{{news.publishUser.user.username}}</div>
                    <div class="ta">{{news.info.addTime + ' '}}{{"等级:" + news.publishUser.user.userLevel}}</div>
                </div>
                <div class="focuseinfobox">
                    <div class="focuseinfo">{{"粉丝：" + news.publishUser.collectCount}}</div>
                    <div class="focuse"
                         v-if="news.publishUser.userHasCollect==0||news.publishUser.userHasCollect==undefined">关注
                    </div>
                    <div class="focuse" v-else>已关注</div>
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
            <CircleProgress v-if="false"
                            ref="$circle"
                            class="progress"
                            key="animation-model"
                            :isAnimation="true"
                            :isRound="true"
                            :width="width"
                            :radius="radius"
                            :progress="progress"
                            :barColor="barColor"
                            :duration="duration"
                            :delay="delay"
                            :timeFunction="timeFunction"
                            :backgroundColor="backgroundColor"/>
            <horProgress v-if="news.joinUsers!=undefined&&news.joinUsers.length>0"
                         :label="label"
                         :text="text"
                         :height="10"
                         :color="barColor"
                         :percentage="news.joinUsers.length * 100 / 5"/>
            <div>
                <div class="jion" v-for="(item,index) in news.joinUsers" :key="index">
                    <linkuser :item="item" style="margin-left: -30px"></linkuser>
                </div>
            </div>
            <div class="collect" v-if="news.collectCount>0">
                <div>{{news.collectCount + "人喜欢"}}</div>
                <img src="../../../assets/images/赞1.png">
                <div class="collect" v-for="(item,index) in news.collect.data" :key="index"
                     @click="goUserDetails(item.user.id)">
                    <img :src="item.user.avatar">
                </div>
                <img src="../../../assets/images/省略号.png" style="margin-left: 10px">
            </div>
        </div>
        <div class="messagebox">
            <p class="msg border-1px">{{'留言 ' + news.comment.data.length + ' 条'}}</p>
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
                <img src="../../../assets/images/赞.png" alt="" v-if="news.userHasCollect==0">
                <img src="../../../assets/images/赞1.png" alt="" v-else>
                <span class="star">收藏</span>
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
        circleRefresh,
        questionRefresh
    } from '@/api/api';
    import {getLocalStorage} from '@/utils/local-storage';
    import linkuser from '../../../components/itemadapter/linkuser'
    import CircleProgress from '../../../components/progress-bar/circle-progress.vue'
    import horProgress from '../../../components/progress-bar/progress-bar.vue'

    export default {
        props: {
            itemId: [String, Number],
            type: [String, Number],
        },
        data() {
            return {
                userHasJoin: -1,
                news: {},
                title: '详情',
                userId: '',
                avatar: '',

                label: "",
                text: "5人开始",
                percentage: 0,

                width: 200,
                radius: 20,
                progress: 70,
                duration: 1000,
                delay: 20,
                barColor: '#F2AE57',
                backgroundColor: '#FFE8CC',
                timeFunction: 'cubic-bezier(0.99, 0.01, 0.22, 0.94)',
                intervalTimer: null, // 持续模式，循环计时器
                currentTime: 0, // 当前时间
                durationTime: 60, // 持续模式总时长
            }
        },
        methods: {
            goUserDetails(id) {
                this.$router.push(`/items/userdetails/${id}`);
            },
            deleteComment(index) {
                this.news.comment.data.splice(index, 1)
                console.log(index, 'deleteComment')
            },
            getUserInfo() {
                const infoData = getLocalStorage(
                    'userId',
                    'avatar'
                );
                this.userId = infoData.userId || "";
                this.avatar = infoData.avatar || "";
                console.log(infoData, '>>>>>>userId')
            },
            addCollect() {
                collectAddOrDelete({valueId: this.itemId, type: this.type}).then(res => {
                    if (this.news.userHasCollect === 1) {
                        this.news.userHasCollect = 0;
                        this.news.collectCount -= 1;
                        Array.prototype.indexOf = function (val) {
                            for (var i = 0; i < this.length; i++) {
                                if (this[i] == val) return i;
                            }
                            return -1;
                        };
                        Array.prototype.remove = function (val) {
                            var index = this.indexOf(val);
                            if (index > -1) {
                                this.splice(index, 1);
                            }
                        };
                        let index = -1
                        for (var i = 0; i < this.news.collect.data.length; i++) {
                            if (this.news.collect.data[i].user.id == this.userId)
                                index = i
                        }
                        this.news.collect.data.splice(index, 1)
                    } else {
                        this.news.userHasCollect = 1;
                        this.news.collectCount += 1;
                        this.news.collect.data.splice(0, 0, {"user": {"id": this.userId, "avatar": this.avatar}})
                        this.$toast({
                            message: '收藏成功',
                            duration: 1500
                        });
                    }
                });
            },
            join() {
                if (this.type == 4) {
                    let obj = {}
                    obj.id = this.itemId
                    circleRefresh(obj).then(res => {
                        console.log(res, '>>>circleRefresh')
                        if (res.status === 200 && res.data.errno == 0) {
                            this.$toast({
                                message: '擦亮成功',
                                duration: 1500
                            });
                        }
                    });
                }
                if (this.type == 5) {
                    let obj = {}
                    obj.id = this.itemId
                    if (this.userHasJoin == 0) {
                        fishpondsJoin(obj).then(res => {
                            console.log(res, '>>>fishpondsJoin')
                            if (res.status === 200 && res.data.errno == 0) {
                                this.userHasJoin = 1
                                this.news.joinUsers.splice(0, 0, {"user": {"id": this.userId, "avatar": this.avatar}})
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
                                let index = -1
                                for (var i = 0; i < this.news.joinUsers.length; i++) {
                                    if (this.news.joinUsers[i].user.id == this.userId)
                                        index = i
                                }
                                this.news.joinUsers.splice(index, 1)
                                this.$toast({
                                    message: '退出成功',
                                    duration: 1500
                                });
                            }
                        });
                    }
                }
                if (this.type == 6) {
                    let obj = {}
                    obj.id = this.itemId
                    questionRefresh(obj).then(res => {
                        console.log(res, '>>>questionRefresh')
                        if (res.status === 200 && res.data.errno == 0) {
                            this.$toast({
                                message: '擦亮成功',
                                duration: 1500
                            });
                        }
                    });
                }
                if (this.type == 7) {
                    let obj = {}
                    obj.id = this.itemId
                    if (this.userHasJoin == 0) {
                        groupJoin(obj).then(res => {
                            console.log(res, '>>>groupJoin')
                            if (res.status === 200 && res.data.errno == 0) {
                                this.userHasJoin = 1
                                this.news.joinUsers.splice(0, 0, {"user": {"id": this.userId, "avatar": this.avatar}})
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
                                let index = -1
                                for (var i = 0; i < this.news.joinUsers.length; i++) {
                                    if (this.news.joinUsers[i].user.id == this.userId)
                                        index = i
                                }
                                this.news.joinUsers.splice(index, 1)
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
                                this.news.joinUsers.splice(0, 0, {"user": {"id": this.userId, "avatar": this.avatar}})
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
                                let index = -1
                                for (var i = 0; i < this.news.joinUsers.length; i++) {
                                    if (this.news.joinUsers[i].user.id == this.userId)
                                        index = i
                                }
                                this.news.joinUsers.splice(index, 1)
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
                    this.label = "当前" + this.news.joinUsers.length + '人'
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
                    this.label = "当前" + this.news.joinUsers.length + '人'
                });
            }
            if (this.type == 8) {
                activityDetail({id: this.itemId}).then(res => {
                    console.log(res, '>>>circleDetail')
                    this.news = res.data.data;
                    this.userHasCollect = res.data.data.userHasCollect
                    this.userHasJoin = res.data.data.userHasJoin
                    this.label = "当前" + this.news.joinUsers.length + '人'
                });
            }
        },
        mounted() {
            document.documentElement.scrollTop = document.body.scrollTop = 0
        },
        components: {
            appbar,
            commentItem,
            linkuser,
            CircleProgress,
            horProgress
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
                        color #7dc5eb
                        font-size 0.9rem
                        font-weight 500
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
                .focuseinfobox
                    display flex
                    flex-direction column
                    .focuseinfo
                        color red
                        font-size 0.8rem
                        font-weight 400
                        margin-bottom 0.4rem
                    .focuse
                        text-align center
                        color: #7dc5eb
                        font-weight: 500;
                        padding: 0 5px;
                        border: 0.04rem solid #7dc5eb;
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
            .collect
                margin-top 0.4rem
                display inline-block
                span
                    color #666
                    padding-top -10px
                img
                    width 25px
                    height 25px
                    border-radius 50%
        .messagebox
            margin-top 5px
            background-color #fff
            padding .1rem 0rem
            .msg
                font-size 15px
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
