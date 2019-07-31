<template>
    <div class="tab">
        <div class="container">
            <span class="arrow left" @click="left">
                <img src="../../assets/images/right.png" :class="{'disable':a==0}"
                     style="-moz-transform: rotate(180deg);
                            -webkit-transform: rotate(180deg);
                            transform: rotate(180deg);
                            filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=2);">
            </span>
            <span class="arrow right" @click="right">
                <img src="../../assets/images/right.png" :class="{'disable':a==-200}">
            </span>
            <div class="wrap">
                <div class="scroll" ref="scroll">
                    <div class="item">
                        <span @click="changeLottery(1)" :class="{selected:lotteryCode==1}">热门</span>
                        <span @click="changeLottery(2)" :class="{selected:lotteryCode==2}">附近</span>
                        <span @click="changeLottery(3)" :class="{selected:lotteryCode==3}">推荐</span>
                    </div>
                    <div class="item">
                        <span @click="changeLottery(4)" :class="{selected:lotteryCode==4}">新手村</span>
                        <span @click="changeLottery(5)" :class="{selected:lotteryCode==5}">入门</span>
                        <span @click="changeLottery(6)" :class="{selected:lotteryCode==6}">进阶</span>
                    </div>
                    <div class="item">
                        <span @click="changeLottery(7)" :class="{selected:lotteryCode==7}">高阶</span>
                        <span @click="changeLottery(8)" :class="{selected:lotteryCode==8}">SS阶</span>
                        <span @click="changeLottery(9)" :class="{selected:lotteryCode==9}">SSS阶</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                a: 0,
                lotteryCode: 1
            }
        },
        methods: {
            left() {
                if (this.a == 0) {
                    this.a = 0;
                } else {
                    this.a += 100;
                    this.$refs.scroll.style.marginLeft = this.a + '%';
                }
            },
            right() {
                if (this.a == -200) {
                    this.a = -200
                } else {
                    this.a -= 100;
                    this.$refs.scroll.style.marginLeft = this.a + '%';
                }
            },
            changeLottery(a) {
                this.lotteryCode = a;
            }
        }
    }
</script>
<style lang="scss" scoped>
    .tab {
        width: 100%;
        height: 36px;
        position: relative;
        border-bottom: 1px solid #d9d9d9;
        .container {
            padding: 0 32px;
            height: 100%;
            .arrow {
                position: absolute;
                top: 3px;
                z-index: 2;
                width: 32px;
                height: 100%;
                line-height: 32px;
                cursor: pointer;
                background-color: transparent;
                text-align: center;
                color: rgba(0, 0, 0, .43);
                transition: color .3s ease;
                .disable {
                    color: rgba(0, 0, 0, .25)
                }
                img {
                    margin-top: 5px;
                    height: 20px;
                    width: 10px;
                }
            }
            .left {
                left: 0;
            }
            .right {
                right: 0;
            }
            .wrap {
                width: 100%;
                height: 100%;
                overflow: hidden;
                .scroll {
                    width: 300%;
                    height: 100%;
                    margin-left: 0;
                    transition: .5s;
                    .item {
                        width: 33.3333%;
                        float: left;
                        height: 100%;
                        display: flex;
                        flex-direction: row;
                        span {
                            display: flex;
                            flex: 1;
                            justify-content: center;
                            align-items: center;
                            font-size: 14px;
                        }
                        .selected {
                            color: #db3d3c;
                            position: relative;
                            flex-direction: column;
                            line-height: 28px;
                            &:after {
                                content: "";
                                width: 68px;
                                height: 2px;
                                background-color: #db3d3c;

                            }
                        }
                    }
                }
            }
        }
    }
</style>

