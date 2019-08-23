<template>
    <div>
        <van-cell-group>
            <van-cell title="我的活动" isLink>
                <router-link to="/user/join/0" class="text-desc">全部活动</router-link>
            </van-cell>
        </van-cell-group>
        <van-row class="order_status">
            <van-col span="6">
                <div class="order_status_icon" @click="$router.push({path: '/user/join/1'})">
                    <van-icon name="daifukuan"/>
                </div>
                <div>未开始</div>
            </van-col>
            <van-col span="6">
                <div class="order_status_icon" @click="$router.push({path: '/user/join/2'})">
                    <van-icon name="daifahuo"/>
                </div>
                <div>准备中</div>
            </van-col>
            <van-col span="6">
                <div class="order_status_icon" @click="$router.push({path: '/user/join/3'})">
                    <van-icon name="daifahuo"/>
                </div>
                <div>进行中</div>
            </van-col>
            <van-col span="6">
                <div class="order_status_icon" @click="$router.push({path: '/user/join/4'})">
                    <van-icon name="wuliu"/>
                </div>
                <div>裁判中</div>
            </van-col>
            <van-col span="6" style="margin-top: 10px">
                <div class="order_status_icon" @click="$router.push({path: '/user/join/5'})">
                    <van-icon name="shouhouguanli"/>
                </div>
                <div>已完成</div>
            </van-col>
        </van-row>
    </div>
</template>

<script>
    import {Row, Col} from 'vant';
    import {userIndex} from '@/api/api';

    export default {
        name: 'activity-group',

        data() {
            return {};
        },
        created() {
            this.init();
        },
        methods: {
            init() {
            }
        },
        components: {
            [Row.name]: Row,
            [Col.name]: Col
        }
    };
</script>


<style scoped lang="scss">
    @import '../../assets/scss/mixin';

    .order_status {
        background-color: #fff;
        text-align: center;
        padding: 10px 0;
        font-size: 12px;
        border-bottom: 1px solid #f7f7f7;
        border-top: 1px solid #f7f7f7;

        > div {
            @include one-border;
            &::after {
                top: 50%;
                left: 50%;
                border-bottom: 0;
                border-right: 1px solid $border-color;
                height: 150%;
                transform: scale(0.5) translate3d(-50%, -50%, 0);
                transform-origin: 0 0;
            }
            &:last-child::after {
                border: 0;
            }
        }

        .order_status_icon {
            position: relative;
            width: 36px;
            height: 36px;
            border-radius: 50%;
            display: inline-block;
            i {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate3d(-50%, -50%, 0);
                font-size: 24px;
                color: #000;
            }
        }
    }
</style>
