<template>
    <div class="container" v-if="user.user!==undefined">
        <appbar :titleText="user.user.nickname||user.user.username||title"></appbar>
        <div class="details">
        </div>
        <div class="top">
            <div class="info">
                <div class="username">{{user.user.username}}</div>
                <div class="ta">{{user.user.addTime + ' '}}{{"等级:" + user.user.userLevel}}</div>
            </div>
            <div class="avatarbox">
                <img :src="user.user.avatar" alt="" class="avatar">
            </div>
        </div>
        <div class="focuseinfobox" @click="addCollect">
            <div class="focuse"
                 v-if="user.userHasCollect==0||user.userHasCollect==undefined">关注
            </div>
            <div class="focuse" v-else>已关注</div>
            <div class="focuseinfo">{{"粉丝："+user.collectCount}}</div>
            <span class="desc">TA很懒，啥都没写~</span>
        </div>
        <van-cell-group class="creditinfo">
            <van-cell icon="cart" title="实名认证" isLink>
                <router-link to="" class="text-desc">未认证</router-link>
            </van-cell>
        </van-cell-group>
        <van-cell-group class="creditinfo">
            <van-cell icon="cart" title="信用分" isLink>
                <router-link to="" class="text-desc">1000（极好）</router-link>
            </van-cell>
        </van-cell-group>
        <van-tabs v-model="activeIndex"
                  :swipe-threshold="5"
                  sticky
                  @click="handleTabClick" style="margin-top: 10px">
            <van-tab v-for="(tabTitle, tabIndex) in tabTitles"
                     :title="tabTitle"
                     :key="tabIndex">
                <van-pull-refresh v-model="loading0" @refresh="onRefresh(tabIndex)" v-if="tabIndex==0">
                    <van-list v-model="loading0"
                              :finished="finished0"
                              :immediate-check="false"
                              finished-text="没有更多了"
                              @load="getCircleList">
                        <div v-for="(item,index) in list0" :key="index"
                             style="margin-top: 5px">
                            <circleItem :item="item.info"></circleItem>
                        </div>
                    </van-list>
                    <is-empty v-if="list0.length==0">TA很懒，啥都没写~</is-empty>
                </van-pull-refresh>
                <van-pull-refresh v-model="loading1" @refresh="onRefresh(tabIndex)" v-if="tabIndex==1">
                    <van-list v-model="loading1"
                              :finished="finished1"
                              :immediate-check="false"
                              finished-text="没有更多了"
                              @load="getQuestionList">
                        <div v-for="(item,index) in list1" :key="index"
                             style="margin-top: 5px">
                            <questionItem :item="item.info"></questionItem>
                        </div>
                    </van-list>
                    <is-empty v-if="list1.length==0">TA很懒，啥都没写~</is-empty>
                </van-pull-refresh>
                <van-pull-refresh v-model="loading2" @refresh="onRefresh(tabIndex)" v-if="tabIndex==2">
                    <van-list v-model="loading2"
                              :finished="finished2"
                              :immediate-check="false"
                              finished-text="没有更多了"
                              @load="getActivityList">
                        <div v-for="(item,index) in list2" :key="index"
                             style="margin-top: 5px">
                            <activityItem :item="item"></activityItem>
                        </div>
                    </van-list>
                    <is-empty v-if="list2.length==0">TA很懒，啥都没写~</is-empty>
                </van-pull-refresh>
                <van-pull-refresh v-model="loading3" @refresh="onRefresh(tabIndex)" v-if="tabIndex==3">
                    <van-list v-model="loading3"
                              :finished="finished3"
                              :immediate-check="false"
                              finished-text="没有更多了"
                              @load="getFishpondsList">
                        <div v-for="(item,index) in list3" :key="index"
                             style="margin-top: 5px">
                            <fishpondsItem :item="item"></fishpondsItem>
                        </div>
                    </van-list>
                    <is-empty v-if="list3.length==0">TA很懒，啥都没写~</is-empty>
                </van-pull-refresh>
                <van-pull-refresh v-model="loading4" @refresh="onRefresh(tabIndex)" v-if="tabIndex==4">
                    <van-list v-model="loading4"
                              :finished="finished4"
                              :immediate-check="false"
                              finished-text="没有更多了"
                              @load="getGroupList">
                        <div v-for="(item,index) in list4" :key="index"
                             style="margin-top: 5px">
                            <groupItem :item="item"></groupItem>
                        </div>
                    </van-list>
                    <is-empty v-if="list4.length==0">TA很懒，啥都没写~</is-empty>
                </van-pull-refresh>
            </van-tab>
        </van-tabs>
    </div>
</template>

<script>
    import appbar from '@/components/head/appbar'
    import {
        userCircleListByUser,
        userQuestionListByUser,
        userActivityListByUser,
        userGroupListByUser,
        userFishpondsListByUser,
        userDetails,
        collectAddOrDelete,
    } from '@/api/api';
    import {getLocalStorage} from '@/utils/local-storage';
    import fishpondsItem from '../../../components/itemadapter/fishpondsItem.vue'
    import activityItem from '../../../components/itemadapter/fishponds.vue'
    import groupItem from '../../../components/itemadapter/groupItem.vue'
    import questionItem from '../../../components/itemadapter/questionItem.vue'
    import circleItem from '../../../components/itemadapter/circleItem.vue'
    import IsEmpty from '@/components/is-empty/';
    import {Tab, Tabs, Panel, Card, List, PullRefresh} from 'vant';

    export default {
        props: {
            userId: [String, Number],
            active: {
                type: [String, Number],
                default: 0
            }
        },
        data() {
            return {
                user: {},
                title: '个人空间',
                loginUserId: '',
                activeIndex: Number(this.active),
                tabTitles: ['心情', '问答', '活动', '鱼塘', '战队'],
                limit: 10,

                list0: [],
                page0: 0,
                loading0: false,
                finished0: false,

                list1: [],
                page1: 0,
                loading1: false,
                finished1: false,

                list2: [],
                page2: 0,
                loading2: false,
                finished2: false,

                list3: [],
                page3: 0,
                loading3: false,
                finished3: false,

                list4: [],
                page4: 0,
                loading4: false,
                finished4: false
            }
        },
        methods: {
            init0() {
                this.page0 = 0;
                this.list0 = [];
                this.getCircleList();
            },
            init1() {
                this.page1 = 0;
                this.list1 = [];
                this.getQuestionList();
            },
            init2() {
                this.page2 = 0;
                this.list2 = [];
                this.getActivityList();
            },
            init3() {
                this.page3 = 0;
                this.list3 = [];
                this.getFishpondsList();
            },
            init4() {
                this.page4 = 0;
                this.list4 = [];
                this.getGroupList();
            },
            //下拉刷新
            onRefresh(index) {
                setTimeout(() => {
                    if (index == 0) {
                        this.init0()
                    }
                    if (index == 1) {
                        this.init1()
                    }
                    if (index == 2) {
                        this.init2()
                    }
                    if (index == 3) {
                        this.init3()
                    }
                    if (index == 4) {
                        this.init4()
                    }
                }, 500);
            },
            getUserInfo() {
                const infoData = getLocalStorage(
                    'userId'
                );
                this.loginUserId = infoData.userId || "";
                console.log(infoData, '>>>>>>loginUserId')
            },
            addCollect() {
                collectAddOrDelete({valueId: this.user.user.id, type: '10'}).then(res => {
                    if (this.user.userHasCollect === 1) {
                        this.user.userHasCollect = 0;
                        this.user.collectCount -= 1
                    } else {
                        this.user.userHasCollect = 1;
                        this.user.collectCount += 1;
                        this.$toast({
                            message: '关注成功',
                            duration: 1500
                        });
                    }
                });
            },
            getCircleList() {
                userCircleListByUser({
                    userId: this.userId,
                    page: this.page0,
                    limit: this.limit
                }).then(res => {
                    console.log(res, '>>>userListByUser')
                    this.list0.push(...res.data.data.list)
                    this.loading0 = false;
                    this.finished0 = res.data.data.page >= res.data.data.pages;
                });
            },
            getQuestionList() {
                userQuestionListByUser({
                    userId: this.userId,
                    page: this.page1,
                    limit: this.limit
                }).then(res => {
                    console.log(res, '>>>userListByUser')
                    this.list1.push(...res.data.data.list)
                    this.loading1 = false;
                    this.finished1 = res.data.data.page >= res.data.data.pages;
                });
            },
            getActivityList() {
                userActivityListByUser({
                    userId: this.userId,
                    page: this.page2,
                    limit: this.limit
                }).then(res => {
                    console.log(res, '>>>userListByUser')
                    this.list2.push(...res.data.data.list)
                    this.loading2 = false;
                    this.finished2 = res.data.data.page >= res.data.data.pages;
                });
            },
            getFishpondsList() {
                userFishpondsListByUser({
                    userId: this.userId,
                    page: this.page3,
                    limit: this.limit
                }).then(res => {
                    console.log(res, '>>>userListByUser')
                    this.list3.push(...res.data.data.list)
                    this.loading3 = false;
                    this.finished3 = res.data.data.page >= res.data.data.pages;
                });
            },
            getGroupList() {
                userGroupListByUser({
                    userId: this.userId,
                    page: this.page4,
                    limit: this.limit
                }).then(res => {
                    console.log(res, '>>>userListByUser')
                    this.list4.push(...res.data.data.list)
                    this.loading4 = false;
                    this.finished4 = res.data.data.page >= res.data.data.pages;
                });
            },
            handleTabClick() {
                console.log(this.activeIndex)
            },
        },
        computed: {},
        created() {
            console.log(this.userId, '>>>>>>loginUserId')
            this.getUserInfo();
            this.init0()
            this.init1()
            this.init2()
            this.init3()
            this.init4()
            userDetails({id: this.userId}).then(res => {
                console.log(res, '>>>userDetails')
                this.user = res.data.data;
            });
        },
        mounted() {
            document.documentElement.scrollTop = document.body.scrollTop = 0
        },
        components: {
            appbar,
            circleItem,
            groupItem,
            fishpondsItem,
            activityItem,
            questionItem,
            [IsEmpty.name]: IsEmpty,
            [Tab.name]: Tab,
            [Tabs.name]: Tabs,
            [Panel.name]: Panel,
            [Card.name]: Card,
            [List.name]: List,
            [PullRefresh.name]: PullRefresh,
        }
    }
</script>

<style lang="stylus" scoped>
    .container
        width 100%
        background-color #f7f7f7
        .details
            width 100%
            height 35%
            padding 1rem 1rem
            box-sizing border-box
            background url('../../../assets/images/grzx2.jpg') no-repeat
            background-size: cover;
        .top
            width 100%
            height 3rem
            display flex
            align-items center
            margin-top -25px
            padding-right 10px
            margin-bottom 20px
            .avatarbox
                width 4rem
                height 4rem
                .avatar
                    width 4rem
                    height 4rem
                    border-radius 10%
            .info
                margin-right 0.6rem
                flex 1
                display flex
                flex-direction column
                text-align right
                .username
                    max-height: 50px;
                    color #fff
                    font-size 1rem
                    font-weight 600
                    margin-bottom 0.8rem
                    overflow: hidden;
                    text-overflow: ellipsis;
                    display: -webkit-box;
                    -webkit-line-clamp: 1;
                    -webkit-box-orient: vertical;
                .ta
                    color #666666
                    font-size 0.9rem
        .focuseinfobox
            display flex
            flex-direction column
            margin-top -70px
            margin-left 10px
            margin-bottom 20px
            .focuse
                width 100px
                text-align center
                color: #fff
                font-weight: 500;
                padding: 2px 5px;
                background-color #7dc5eb
                border: 0.04rem solid #7dc5eb;
            .focuseinfo
                color #581868
                font-size 0.8rem
                font-weight 400
                margin-top 0.6rem
            .desc
                margin-top 15px
                font-size 15px
                font-weight 300

        .creditinfo
            margin-top 1px

</style>
