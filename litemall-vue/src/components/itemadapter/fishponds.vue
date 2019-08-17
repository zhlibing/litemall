<template>
    <div class="lobby-item" v-if="item!==undefined">
        <a class="item-content"
           @click="itemClick(item.activityInfo.id||item.FishPondsInfo.id||item.GroupInfo.id,item.activityInfo.type||item.FishPondsInfo.type||item.GroupInfo.type)">
            <div class="lottery-logo">
                <img :src="item.activityInfo.picUrls[0]"
                     style="object-fit: cover">
            </div>
            <div class="item-info">
                <div class="info-row">
                    <p class="lottery-name">
                        {{item.activityInfo.title || item.FishPondsInfo.title || item.GroupInfo.title}}
                    </p>
                    <img src="../../assets/images/right.png" class="arrow-right"/>
                </div>
                <div class="nums">
                    <span>{{item.activityInfo.description || item.FishPondsInfo.description || item.GroupInfo.description}}</span>
                </div>
                <div class="info-row">
                    <span class="issue-no">奖金：</span>
                    <span class="item-cd">{{'￥' + item.activityInfo.reword}}</span>
                </div>
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
            <div v-for="(itemc,index) in JSON.parse(item.activityInfo.rule)" :key="index">
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
                users: []
            };
        },
        mounted() {
            let keyUser = {}
            keyUser = this.item.publishUser
            keyUser.key = true;
            this.users.push(keyUser);
            this.users.push(...this.item.joinUsers);
            if (this.item.joinUsers.length > 0) {
                this.isShowOne = true
            }
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
    }

    .item-content {
        display: flex;
        flex-direction: row;
        flex: 1;
        border-bottom: .0625rem solid #f7f7f7;
        padding: 0 .625rem;
        .lottery-logo {
            display: flex;
            justify-content: center;
            align-items: center;
            img {
                width: 4rem;
                height: 4rem;
                margin-left: 10px;
            }
        }
        .item-info {
            display: flex;
            flex: 1;
            flex-direction: column;
            padding: .625rem 0 .625rem .625rem;
            .info-row {
                display: flex;
                flex: 1;
                flex-direction: row;
                justify-content: space-between;
                align-items: center;
                margin-top: 5px;
                .lottery-name {
                    font-size: .94rem;
                    font-weight: 400;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    display: -webkit-box;
                    -webkit-box-orient: vertical;
                    -webkit-line-clamp: 2;
                    margin-right: 10px;
                }
                .arrow-right {
                    width: .5625rem;
                    height: .9375rem;
                    margin-right: 5px;
                }
                .issue-no {
                    font-size: .7875rem;
                    color: #666;
                }
                .item-cd {
                    font-size: .7875rem;
                    color: red;
                    margin-right: 5px;
                    margin-top: 5px;
                }
            }
            .nums {
                margin-top: -5px;
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                span {
                    font-size: .9rem;
                    color: #888;
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
