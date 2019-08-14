<template>
    <div class="user_header" :style="{backgroundImage: `url(${background_image})`}">
        <van-icon name="set" class="user_set" @click="toSetting"/>
        <div class="user_avatar" @click="goUserDetails(userId)">
            <img :src="avatar" alt="头像" width="55" height="55">
        </div>
        <div class="user_name">{{nickName}}</div>
        <div class="count">
            <div class="numbox">
                <span class="num">{{'100'}}</span>
                <span class="numname">访客数</span>
            </div>
            <div class="numbox">
                <span class="num">{{'45'}}</span>
                <span class="numname">被赞数</span>
            </div>
            <div class="numbox" @click="toCollectUser(userId)">
                <span class="num">{{'23'}}</span>
                <span class="numname">关注数</span>
            </div>
            <div class="numbox">
                <span class="num">{{'123'}}</span>
                <span class="numname">粉丝数</span>
            </div>
        </div>
    </div>
</template>

<script>
    import avatar_default from '@/assets/images/avatar_default.png';
    import bg_default from '@/assets/images/user_head_bg.png';
    import {getLocalStorage} from '@/utils/local-storage';

    export default {
        name: 'user-header',

        props: {
            isLogin: {
                type: Boolean,
                default: false
            }
        },

        data() {
            return {
                nickName: '昵称',
                avatar: avatar_default,
                userId: '',
                background_image: bg_default
            };
        },

        activated() {
            this.getUserInfo();
        },

        methods: {
            getUserInfo() {
                const infoData = getLocalStorage(
                    'nickName',
                    'avatar',
                    'userId'
                );
                this.avatar = infoData.avatar || avatar_default;
                this.nickName = infoData.nickName || '昵称';
                this.userId = infoData.userId || '';
            },
            toSetting() {
                this.$router.push({name: 'user-information'});
            },
            goUserDetails(id) {
                this.$router.push(`/items/userdetails/${id}`);
            },
            toCollectUser(id){
                this.$router.push(`/user/collectUser/${id}`);
            }
        }
    };
</script>

<style lang="scss" scoped>
    .user_header {
        background-repeat: no-repeat;
        background-size: cover;
        height: 180px;
        text-align: center;
        color: #fff;
        padding-top: 30px;
    }

    .user_set {
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 24px;
    }

    .user_avatar {
        margin-bottom: 10px;
        img {
            border: 0;
            border-radius: 50%;
            object-fit: contain;
        }
    }

    .user_name {
        margin-left: 100px;
        margin-right: 100px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 1;
        overflow: hidden;
    }

    .count {
        display: flex;
        align-items: center;
        margin-top: 30px;
        .numbox {
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 1.3333rem;
            .num {
                color: #ededed;
                font-weight: 600;
                font-size: 0.3rem;
                margin-bottom: 0.06rem;
            }
            .numname {
                color: #ffffff;
                font-size: 0.3rem;
            }
        }
    }
</style>
