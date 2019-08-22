<template>
    <div class="container">
        <appbar titleText="我的战队"></appbar>
        <van-pull-refresh v-model="loading4" @refresh="onRefresh()">
            <van-list v-model="loading4"
                      :finished="finished4"
                      :immediate-check="false"
                      finished-text="没有更多了"
                      @load="getGroupList"
                      v-if="list4.length>0">
                <div v-for="(item,index) in list4" :key="index"
                     style="margin-top: 5px">
                    <groupItem :item="item"></groupItem>
                </div>
            </van-list>
            <is-empty v-if="list4.length==0">TA很懒，啥都没写~</is-empty>
        </van-pull-refresh>
    </div>
</template>

<script>
    import appbar from '@/components/head/appbar'
    import {
        userGroupListjoin,
        collectAddOrDelete,
    } from '@/api/api';
    import {getLocalStorage} from '@/utils/local-storage';
    import groupItem from '../../../components/itemadapter/groupItem.vue'
    import IsEmpty from '@/components/is-empty/';
    import {Panel, Card, List, PullRefresh} from 'vant';

    export default {
        props: {
            userId: [String, Number],
        },
        data() {
            return {
                user: {},
                title: '个人空间',
                loginUserId: '',
                limit: 10,

                list4: [],
                page4: 0,
                loading4: false,
                finished4: false,

            }
        },
        methods: {
            init4() {
                this.page4 = 0;
                this.list4 = [];
                this.getGroupList();
            },
            //下拉刷新
            onRefresh() {
                setTimeout(() => {
                    this.init4()
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
            getGroupList() {
                userGroupListjoin({
                    userId: this.userId,
                    page: this.page4,
                    limit: this.limit,
                    type: 7
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
            this.init4()
        },
        mounted() {
            document.documentElement.scrollTop = document.body.scrollTop = 0
        },
        components: {
            appbar,
            groupItem,
            [IsEmpty.name]: IsEmpty,
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
