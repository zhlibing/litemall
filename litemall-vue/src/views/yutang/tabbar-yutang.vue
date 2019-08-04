<template>
    <div class="container">
        <Head></Head>
        <div class="interest" style="margin-top: 40px">
            <div class="titlebox">
                <p class="title">你可能感兴趣的鱼塘</p>
            </div>
            <div class="swiper-container" ref="swiper">
                <div class="swiper-wrapper swiper">
                    <div class="swiper-slide item" v-for="(item,index) in interests" :key="index" @click="toast">
                        <img :src="item.picUrls[0]" alt="" class="img">
                        <div class="name">{{item.title}}</div>
                        <div class="info">
                            <p class="desc">{{item.description}}</p>
                        </div>
                        <button class="add">+ 加入</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="questions">
            <div class="titlebox">
                <p class="title">鱼塘问答</p>
                <span class="ask">去提问</span>
            </div>
            <div class="quesbox" v-for="question in questions" :key="question.id">
                <div class="title">{{question.title}}</div>
                <div class="answer">
                    <span class="da">答</span>
                    <div class="text">{{question.description}}</div>
                    <div class="imgshow" v-if="question.picUrls !== undefined">
                        <img :src="question.picUrls[0]" alt="">
                    </div>
                </div>
                <div class="bottom">
                    <div class="yutang">{{question.title}}</div>
                    <div class="toanswer">去回答</div>
                </div>
            </div>
            <div class="more">
                <span class="gomore">更多</span>
            </div>
        </div>
        <div class="interest">
            <div class="titlebox">
                <p class="title">土豪圈儿都在玩</p>
            </div>
            <div class="swiper-container" ref="tuhao">
                <div class="swiper-wrapper swiper">
                    <div class="swiper-slide item" v-for="(item,index) in tuhao" :key="index" @click="toast">
                        <div class="imgdisplay" v-if="item.picUrls !== undefined">
                            <img :src="item.picUrls[0]" alt=""
                                 class="imgbig">
                        </div>
                        <div class="name">{{'当前人数' + item.currentPeople}}</div>
                        <div class="info">
                            <p class="desc">{{item.description}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="interest">
            <div class="titlebox">
                <p class="title">厉害了我的中国风</p>
            </div>
            <div class="swiper-container" ref="china">
                <div class="swiper-wrapper swiper">
                    <div class="swiper-slide item" v-for="(item,index) in china" :key="index" @click="toast">
                        <div class="imgdisplay" v-if="item.picUrls !== undefined">
                            <img :src="item.picUrls[0]" alt=""
                                 class="imgbig">
                        </div>
                        <div class="name">{{item.title}}</div>
                        <div class="info">
                            <p class="desc">{{item.description}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="interest bottom2 questions">
            <div class="titlebox">
                <p class="title">不可错过的鱼塘</p>
            </div>
            <div class="list" v-for="(item,index) in lists" :key="index">
                <img :src="item.picUrls[0]" alt="" class="image">
                <div class="info">
                    <div class="head">
                        <p class="tit">{{item.content.substring(0,8)+'...'}}</p>
                        <span class="level">{{item.status}}</span>
                    </div>
                    <div class="text">
                        <p class="desc">{{item.content}}</p>
                    </div>
                    <div class="hot">
                        <span>人气{{item.status}}·发布{{item.addTime}}</span>
                    </div>
                </div>
            </div>
            <div class="more">
                <span class="gomore">更多</span>
            </div>
        </div>
    </div>
</template>

<script>
    import {fishPondsList, questionList, groupList, activityList, circleList} from '@/api/api'
    import Swiper from 'swiper'
    import 'swiper/dist/css/swiper.min.css'
    import Head from '@/components/head/Head'

    export default {
        data() {
            return {
                interests: [],
                questions: [],
                tuhao: [],
                china: [],
                lists: [],
                scroll: 0
            }
        },
        methods: {
            handleScroll() {
                this.scroll = document.documentElement && document.documentElement.scrollTop
            },
            toast() {
                this.$toast('小哥哥还没做这个功能哦')
            }
        },
        components: {
            Head,
        },
        mounted() {
            window.addEventListener('scroll', this.handleScroll)
            console.log('mounted', '>>>>YUTANG.vue')
        },
        activated() {
            if (this.scroll >= 0) {
                window.scrollTo(0, this.scroll);
                this.scroll = 0;
                window.addEventListener('scroll', this.handleScroll);
            }
            console.log('activated', '>>>>YUTANG.vue')
        },
        deactivated() {
            window.removeEventListener('scroll', this.handleScroll);
            console.log('deactivated', '>>>>YUTANG.vue')
        },
        created() {
            fishPondsList().then(res => {
                if (res.status === 200) {
                    this.interests = res.data.data.list
                    // console.log(this.interests)
                    this.$nextTick(() => {
                        let swiper = this.$refs.swiper
                        let mySwiper = new Swiper(swiper, {
                            effect: 'coverflow',
                            slidesPerView: 2,
                            centeredSlides: true,
                            observer: true,
                            observeParents: true,
                            autoplay: {
                                delay: 1000
                            },
                            loop: true,
                        })
                    })
                }
            }).catch(err => {
                console.log(err)
            }),
                questionList().then(res => {
                    if (res.status === 200) {
                        this.questions = res.data.data.list
                    }
                }).catch(err => {
                    console.log(err)
                }),
                groupList().then(res => {
                    if (res.status === 200) {
                        this.tuhao = res.data.data.list
                        this.$nextTick(() => {
                            let tuhao = this.$refs.tuhao
                            let mySwiper = new Swiper(tuhao, {
                                effect: 'coverflow',
                                slidesPerView: 3,
                                centeredSlides: true,
                                observer: true,
                                observeParents: true,
                                loop: true,
                            })
                        })
                    }
                }).catch(err => {
                    console.log(err)
                }),
                activityList().then(res => {
                    if (res.status === 200) {
                        this.china = res.data.data.list
                        this.$nextTick(() => {
                            let china = this.$refs.china
                            let mySwiper = new Swiper(china, {
                                effect: 'coverflow',
                                slidesPerView: 3,
                                centeredSlides: true,
                                observer: true,
                                observeParents: true,
                                loop: true,
                            })
                        })
                    }
                }).catch(err => {
                    console.log(err)
                }),
                circleList().then(res => {
                    if (res.status === 200) {
                        this.lists = res.data.data.list
                    }
                }).catch(err => {
                    console.log(err)
                })
        }

    }
</script>

<style lang="scss" scoped>
    .container {
        background-color: #f7f7f7;
        .interest, .questions {
            margin-top: 5px;
            background-color: #fff;
            width: 100%;
            padding: 1rem 1rem;
            box-sizing: border-box;
            .titlebox {
                box-sizing: border-box;
                width: 100%;
                margin-bottom: 0.3rem;
                display: flex;
                .title {
                    flex: 1;
                    font-size: 0.9rem;
                    font-weight: 600;
                    margin-top: -2px;
                    &:before {
                        display: inline-block;
                        content: '';
                        width: 0.2em;
                        height: 1.2rem;
                        background-color: #ffda44;
                        vertical-align: top;
                        margin-right: 0.5rem;
                    }
                }
            }
            .list {
                padding-top: .4rem;
                box-sizing: border-box;
                padding-bottom: .3rem;
                display: flex;
                .image {
                    width: 4rem;
                    height: 4rem;
                    object-fit: cover;
                }
                .info {
                    flex: 1;
                    margin-left: 0.3rem;
                    .head {
                        display: flex;
                        .tit {
                            font-size: 0.8rem;
                            font-weight: 600;
                            flex: 1;
                            margin-top: 5px;
                        }
                        .level {
                            font-size: 0.3rem;
                            width: 2rem;
                            height: 1.2rem;
                            line-height: 1.2rem;
                            background: url('../../assets/images/标签.png') no-repeat;
                            background-size: cover;
                            background-position: 110% 50%;
                            box-sizing: border-box;
                            margin-right: 10px;
                            color: #888;
                        }
                    }
                    .text {
                        margin-top: -15px;
                        font-size: 0.8rem;
                        font-weight: 200;
                        line-height: 1.2rem;
                    }
                    .hot {
                        margin-top: 0.3rem;
                        font-size: 0.33rem;
                        color: #666666;
                    }
                }
            }
            .swiper-container {
                .item {
                    width: 100%;
                    height: auto;
                    padding: .3rem .2rem .2rem .2rem;
                    box-sizing: border-box;
                    margin-bottom: 0.12rem;
                    text-align: center;
                    background: #ffffff;
                    .imgbig {
                        width: 100%;
                        height: 4rem;
                        margin-bottom: 0.2rem;
                    }
                    .name {
                        color: #000000;
                        font-size: 0.6em;
                        font-weight: 600;
                        margin-bottom: 0.2rem;
                    }
                    .desc {
                        color: #888888;
                        font-size: 0.4rem;
                        font-weight: 400;
                        white-space: nowrap;
                        overflow: hidden;
                        text-overflow: ellipsis;
                        margin-bottom: 0.2rem;
                    }
                    .img {
                        width: 3rem;
                        height: 3rem;
                        margin-bottom: 0.2em;
                    }
                    .add {
                        margin-top: 5px;
                        width: 100%;
                        height: 1.2rem;
                        background-color: #ffda44;
                        outline: none;
                        border: none;
                    }
                }
            }
        }
        .questions {
            background-color: #fff;
            .ask {
                font-size: 0.8rem;
                font-weight: 700;
                background: url('../../assets/images/提问.png') no-repeat;
                background-size: 1rem 1rem;
                width: 4rem;
                height: 1rem;
                padding-left: 1.2rem;
                box-sizing: border-box;
            }
            .more {
                width: 100%;
                text-align: right;
                margin-top: 5px;
                .gomore {
                    font-size: 0.6rem;
                    font-weight: 700;
                    background: url('../../assets/images/更多.png') right no-repeat;
                    background-size: 1rem 1rem;
                    width: 5rem;
                    height: .53rem;
                    padding-right: 1.5rem;
                    box-sizing: border-box;
                }
            }
            .quesbox {
                padding-top: 0.4rem;
                padding-bottom: 0.4rem;
                .title {
                    font-size: 0.8rem;
                    font-weight: 600;
                }
                .answer {
                    display: flex;
                    font-size: 0.8rem;
                    font-weight: 200;
                    margin-top: 0.5rem;
                    color: #666666;
                    .da {
                        background-color: #ffda44;
                        display: inline-block;
                        width: 1.5rem;
                        height: 1.5rem;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        font-weight: 600;
                        margin-right: 0.3rem;
                        color: #333333;
                    }
                    .text {
                        vertical-align: middle;
                        flex: 1;
                        font-weight: 500;
                        line-height: 1.2rem;
                    }
                    img {
                        width: 4rem;
                        height: 4rem;
                        margin-left: 0.3em;
                    }
                }
                .bottom {
                    display: flex;
                    margin-top: .5rem;
                    font-size: 0.6rem;
                    .yutang {
                        flex: 1;
                        color: #999;
                    }
                    .toanswer {
                        color: #7dc5eb;
                        font-weight: 700;
                    }
                }
            }
        }

        .bottom2 {
            margin-bottom: 0.1rem;
        }
    }
</style>
