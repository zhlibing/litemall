<template>
    <div class="lobby-item" v-if="item!==undefined">
        <a class="item-content"
           @click="itemClick(item.info.id,item.info.type)">
            <div class="lottery-logo">
                <img :src="item.info.picUrls[0]"
                     style="object-fit: cover">
                <div class="layerchird" v-if="countdownTime=='已结束'">
                    <span>已结束</span>
                </div>
            </div>
            <div class="item-info">
                <div class="info-row">
                    <p class="lottery-name">
                        {{item.info.title}}
                    </p>
                    <img src="../../assets/images/right.png" class="arrow-right"/>
                </div>
                <div class="nums">
                    <span>{{item.info.description}}</span>
                </div>
                <div class="info-row">
                    <span class="issue-no">奖金：</span>
                    <span class="item-cd">{{'￥' + item.info.reword}}</span>
                </div>
                <span>{{countdownTime}}</span>
            </div>
        </a>
        <div class="funs-wrap">
            <div class="funs-list">
                <div class="fun" @click="showOne">
                    <img src="../../assets/images/trend.png" class="fun-logo"/>
                    <span>头号玩家</span>
                </div>
                <div class="fun" @click="showTwo">
                    <img src="../../assets/images/rule.png" class="fun-logo"/>
                    <span>玩法规则</span>
                </div>
                <div class="fun" @click="showThree">
                    <img src="../../assets/images/shouCang.png" class="fun-logo"/>
                    <span>加入收藏</span>
                </div>
            </div>
        </div>
        <div class="arrow-wrap">
            <div class="arrow-list">
                <div class="arrow">
                    <img src="../../assets/images/下箭头red.png" class="fun-logo" v-show="isShowOne"/>
                </div>
                <div class="arrow">
                    <img src="../../assets/images/下箭头red.png" class="fun-logo" v-show="isShowTwo"/>
                </div>
                <div class="arrow">
                    <img src="../../assets/images/下箭头red.png" class="fun-logo" v-show="isShowThree"/>
                </div>
            </div>
        </div>
        <div class="fun-info" v-show="isShowOne">
            <div v-for="(itemc,index) in users" :key="index">
                <linkuser :item="itemc"></linkuser>
            </div>
        </div>
        <div class="fun-info" v-show="isShowTwo">
            <div v-for="(itemc,index) in JSON.parse(item.info.rule)" :key="index">
                <span>{{itemc.label + itemc.model}}</span>
            </div>
        </div>
        <div class="fun-info" v-show="isShowThree">
            <span>排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜排行榜</span>
        </div>
    </div>
</template>
<script>
    import linkuser from '../../components/itemadapter/linkuser'

    export default {
        props: {
            item: Object,
        },
        data() {
            return {
                isShowOne: false,
                isShowTwo: false,
                isShowThree: false,
                users: [],
                countdownTime: ''
            };
        },
        mounted() {
            if (this.item.publishUser != undefined) {
                let keyUser = {}
                keyUser = this.item.publishUser
                keyUser.key = true;
                this.users.push(keyUser);
                this.users.push(...this.item.joinUsers);
            }
            if (this.item.joinUsers != undefined && this.item.joinUsers.length > 0) {
                this.isShowOne = true
            }
            this.share_data_time(this.item.info.activityTime)
        },
        methods: {
            itemClick(id, type) {
                this.$router.push(`/items/publishdetails/${id}/${type}`);
            },
            showOne() {
                this.$emit('onShowClick')
                if (this.isShowOne) {
                    this.isShowOne = false
                } else {
                    this.isShowOne = true
                    this.isShowTwo = false
                    this.isShowThree = false
                }
            },
            showTwo() {
                this.$emit('onShowClick')
                if (this.isShowTwo) {
                    this.isShowTwo = false
                } else {
                    this.isShowOne = false
                    this.isShowTwo = true
                    this.isShowThree = false
                }
            },
            showThree() {
                this.$emit('onShowClick')
                if (this.isShowThree) {
                    this.isShowThree = false
                } else {
                    this.isShowOne = false
                    this.isShowTwo = false
                    this.isShowThree = true
                }
            },
            share_data_time(time) {
                setTimeout(() => {
                    this.share_data_time(time)
                }, 1000);
                //毕业时间
                let BirthDay = new Date(time);
                //获取当前时间
                let today = new Date();
                let timeold = (BirthDay.getTime() - today.getTime()); //总豪秒数
                let secondsold = Math.floor(timeold / 1000);          //总秒数
                let e_daysold = timeold / (24 * 60 * 60 * 1000);
                let daysold = Math.floor(e_daysold);                 //相差天数
                let e_hrsold = (e_daysold - daysold) * 24;
                let hrsold = Math.floor(e_hrsold);                   //相差小时数
                let e_minsold = (e_hrsold - hrsold) * 60;
                let minsold = Math.floor(e_minsold)                   //相差分钟数
                let seconds = Math.floor((e_minsold - minsold) * 60);  //相差秒数
                //将所获取的时间拼接到一起，再把值显示到页面
                if (daysold > 0) {
                    this.countdownTime = ("剩余:" + daysold + "天" + hrsold + "小时" + minsold + "分" + seconds + "秒")
                } else {
                    this.countdownTime = ("已结束")
                }
            }
        },
        components: {
            linkuser,
        }
    }
</script>
<style lang="scss" scoped>
    .lobby-item {
        /*min-height: 7.8125rem;*/
        background-color: #fff;
        display: flex;
        flex-direction: column;
        padding: 0 15px;
    }

    .item-content {
        display: flex;
        flex-direction: row;
        flex: 1;
        border-bottom: .0625rem solid #f7f7f7;
        .lottery-logo {
            display: flex;
            justify-content: center;
            align-items: center;
            img {
                width: 10rem;
                height: 6.5rem;
            }
            .layerchird {
                width: 10rem;
                height: 6.5rem;
                background-color: #000;
                filter: Alpha(Opacity=60);
                opacity: 0.6;
                position: absolute;
                text-align: center;
                padding-top: 20px;
                span {
                    height: 4rem;
                    color: #fff;
                    line-height: 4rem;
                    font-size: 1rem;
                    font-weight: 600;
                }
            }
        }
        .item-info {
            display: flex;
            flex: 1;
            flex-direction: column;
            padding-left: 0.625rem;
            .info-row {
                display: flex;
                flex: 1;
                flex-direction: row;
                justify-content: space-between;
                align-items: center;
                .lottery-name {
                    font-size: 15px;
                    font-weight: 400;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    display: -webkit-box;
                    -webkit-box-orient: vertical;
                    -webkit-line-clamp: 1;
                    margin-right: 10px;
                }
                .arrow-right {
                    width: .5625rem;
                    height: .9375rem;
                }
                .issue-no {
                    font-size: .7875rem;
                    color: red;
                }
                .item-cd {
                    font-size: .7875rem;
                    color: red;
                    margin-right: 5px;
                    margin: 10px 0;
                }
            }
            .nums {
                margin-top: -5px;
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                overflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 2;
                margin-right: 10px;
                span {
                    font-size: 13px;
                    color: #666;
                    padding-right: .1875rem;
                }
            }
        }
    }

    .lobby-item .funs-wrap .funs-list .fun + .fun {
        border-left: .0625rem solid #f7f7f7;
    }

    .funs-wrap {
        padding: .5rem;
        background-color: #ffffff;
    }

    .funs-list {
        flex: 1;
        display: flex;
        justify-content: center;
    }

    .fun {
        flex: 1;
        display: flex;
        justify-content: center;
        .fun-logo {
            height: .9375rem;
            width: .9375rem;
        }
        span {
            font-size: .75rem;
            color: #333;
            padding-left: .5rem;
        }
    }

    .arrow-wrap {
        background-color: #ffffff;
    }

    .arrow-list {
        flex: 1;
        display: flex;
    }

    .arrow {
        flex: 1;
        display: flex;
        justify-content: center;
        .fun-logo {
            padding: 0;
            height: 1rem;
            width: 1rem;
        }
    }

    .fun-info {
        padding: 5px 20px;
        font-size: .9rem;
        font-weight: 400;
        line-height: 25px;
        z-index: 99;
    }
</style>
