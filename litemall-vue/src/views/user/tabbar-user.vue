<template>
    <div class="tabbar-user">
        <user-header :viewMeCount="viewMeCountp"
                     :meViewCount="meViewCountp"
                     :collectMeCount="collectMeCountp"
                     :meCollectCount="meCollectCountp"
                     :isLogin="isLogin"/>
        <activity-group/>
        <order-group/>
        <coupon-group/>
        <user-module/>
        <floatbutton></floatbutton>
    </div>
</template>

<script>
    import userHeader from './tabbar-user-header';
    import orderGroup from './tabbar-user-order';
    import activityGroup from './tabbar-user-activity';
    import couponGroup from './tabbar-user-coupon';
    import userModule from './tabbar-user-module';
    import floatbutton from '@/components/head/floatbutton'
    import {userRefresh} from '@/api/api'

    export default {
        data() {
            return {
                isLogin: false,
                viewMeCountp: '0',
                meViewCountp: '0',
                collectMeCountp: '0',
                meCollectCountp: '0',
            };
        },

        activated() {
            this.getLoginStatus();
            userRefresh().then(res => {
                if (res.status === 200) {
                    this.meViewCountp = res.data.data.meViewCount || '0';
                    this.meCollectCountp = res.data.data.meCollectCount || '0';
                    this.collectMeCountp = res.data.data.collectMeCount || '0';
                    this.viewMeCountp = res.data.data.viewMeCount || '0';
                }
            }).catch(err => {
                console.log(err)
            })
        },

        methods: {
            getLoginStatus() {
                this.isLogin =
                    !!localStorage.getItem('Authorization');
            }
        },

        components: {
            floatbutton,
            [userHeader.name]: userHeader,
            [activityGroup.name]: activityGroup,
            [orderGroup.name]: orderGroup,
            [couponGroup.name]: couponGroup,
            [userModule.name]: userModule
        }
    };
</script>


<style scoped lang="scss">
    .tabbar-user {
        > div {
            margin-bottom: 0px;
        }
        &__quit {
            border: 0;
            border-radius: 0;
        }
    }
</style>
