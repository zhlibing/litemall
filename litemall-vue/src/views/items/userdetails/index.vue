<template>
    <div class="container" v-if="user!==undefined">
        <appbar :titleText="user.nickname||user.username||title"></appbar>
        <div class="details">
        </div>
        <div class="top">
            <div class="info">
                <div class="username">{{user.username}}</div>
                <div class="ta">{{user.addTime + ' '}}{{"等级:" + user.userLevel}}</div>
            </div>
            <div class="avatarbox">
                <img :src="user.avatar" alt="" class="avatar">
            </div>
        </div>
    </div>
</template>

<script>
    import appbar from '@/components/head/appbar'
    import {
        userDetails,
    } from '@/api/api';
    import {getLocalStorage} from '@/utils/local-storage';

    export default {
        props: {
            userId: [String, Number],
        },
        data() {
            return {
                user: {},
                title: '个人空间',
                loginUserId: ''
            }
        },
        methods: {
            getUserInfo() {
                const infoData = getLocalStorage(
                    'userId'
                );
                this.loginUserId = infoData.loginUserId || "";
                console.log(infoData, '>>>>>>loginUserId')
            },
        },
        computed: {},
        created() {
            this.getUserInfo();
            userDetails({id: this.userId}).then(res => {
                console.log(res, '>>>userDetails')
                this.user = res.data.data.user;
            });
        },
        mounted() {
            document.documentElement.scrollTop = document.body.scrollTop = 0
        },
        components: {
            appbar,
        }
    }
</script>

<style lang="stylus" scoped>
    .container
        width 100%
        background-color #f7f7f7
        .details
            width 100%
            min-height 35%
            padding 1rem 1rem
            box-sizing border-box
            background url('../../../assets/images/grzx.jpg') no-repeat
            background-size: cover;
        .top
            width 100%
            height 3rem
            display flex
            align-items center
            margin-top -25px
            padding-right 10px
            .avatarbox
                width 4rem
                height 4rem
                .avatar
                    width 4rem
                    height 4rem
                    border-radius 50%
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
                    font-size 0.8rem
</style>
