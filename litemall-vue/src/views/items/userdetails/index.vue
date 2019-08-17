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
        </div>
        <div v-for="(item,index) in circles" :key="index" style="margin-left: 15px;margin-right: 15px;margin-top: 20px">
            <circleItem :item="item"></circleItem>
        </div>
        <is-empty v-if="circles.length === 0">TA很懒，啥都没写~</is-empty>
    </div>
</template>

<script>
    import appbar from '@/components/head/appbar'
    import {
        userDetails,
        collectAddOrDelete,
    } from '@/api/api';
    import {getLocalStorage} from '@/utils/local-storage';
    import circleItem from '../../../components/itemadapter/circleItem.vue'
    import IsEmpty from '@/components/is-empty/';

    export default {
        props: {
            userId: [String, Number],
        },
        data() {
            return {
                user: {},
                title: '个人空间',
                loginUserId: '',
                circles: []
            }
        },
        methods: {
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
        },
        computed: {},
        created() {
            console.log(this.userId, '>>>>>>loginUserId')
            this.getUserInfo();
            userDetails({id: this.userId}).then(res => {
                console.log(res, '>>>userDetails')
                this.user = res.data.data;
                this.circles = res.data.data.circles
            });
        },
        mounted() {
            document.documentElement.scrollTop = document.body.scrollTop = 0
        },
        components: {
            appbar,
            circleItem,
            [IsEmpty.name]: IsEmpty,
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
            .focuse
                width 100px
                text-align center
                color: #fff
                font-weight: 500;
                padding: 2px 5px;
                background-color #7dc5eb
                border: 0.04rem solid #7dc5eb;
            .focuseinfo
                color red
                font-size 0.8rem
                font-weight 400
                margin-top 0.6rem
</style>
