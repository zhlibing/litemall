<template>
    <div class="marquee">
        <div class="title">
            <img src="../../assets/images/jiangjinDetail.png" alt="marqueeTitle.png">
            <span>最新中奖纪录</span>
        </div>
        <div class="winnList">
            <div class="listCon" :style="{marginTop:totop+'px',}" ref="listCon">
                <div class="list" v-for="(item,index) in winUserListt" :key="index">
                    <div class="name">{{item.name.slice(0,3)}}***</div>
                    <div style="flex: 1 1 0%;"></div>
                    <div class="lottery">
                        购买<span :lotteryCode="item.name">{{item.name}}</span>
                    </div>
                    <div style="flex: 1 1 0%;"></div>
                    <div class="bonus">
                        喜中<span>{{item.name}}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        props: ['winUserListt'],
        data() {
            return {
                totop: 0,
                sumtop: 0,
            }
        },
        mounted() {
            setInterval(() => {
                this.totop--;
            }, 60);
        },
        beforeUpdate() {
            this.sumtop = this.$refs.listCon.clientHeight * (-1);
        },
        watch: {
            totop() {
                if (this.totop == this.sumtop) {
                    this.totop = 0;
                }
            }
        },
    }
</script>
<style scoped>
    .marquee {
        margin-top: 1rem;
        padding-bottom: 1rem;
        margin-left: 0.6rem;
    }

    .marquee .title {
        height: 1.875rem;
        border-radius: .9375rem;
        background-color: #f0f0f0;
        margin-left: 10px;
        margin-right: 10px;
    }

    .marquee .title img {
        margin-top: -.325rem;
        float: left;
        width: 1.5rem;
        height: 1.5rem;
        margin-right: 10px;
    }

    .marquee .title span {
        color: #666;
        font-size: .875rem;
    }

    .marquee .winnList {
        background-color: #fff;
        border-radius: .9375rem;
        height: 11.25rem;
        overflow: hidden;
        padding: .625rem 0;
    }

    .marquee .winnList .list {
        display: flex;
        flex-direction: row;
        align-items: center;
        border-bottom: 1px solid #e5e5e5;
        margin: 0 .625rem;
        height: 45px;
    }

    .marquee .winnList .list > .name {
        width: 6em;
        font-size: .875rem;
        color: #666;
    }

    .marquee .winnList .list > .lottery {
        width: 8em;
        font-size: .875rem;
        color: #666;
    }

    .marquee .winnList .list .bonus {
        width: 8em;
        font-size: .875rem;
        color: red;
    }
</style>
