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
                        <img src="http://yanxuan.nosdn.127.net/dae4d6e89ab8a0cd3e8da026e4660137.png" alt="" class="img">
                        <div class="name">{{item.title}}</div>
                        <div class="info">
                            <p class="desc">{{item.desc}}</p>
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
                    <div class="text">{{question.answer}}</div>
                    <div class="imgshow" v-if="question.img !== undefined">
                        <img src="http://yanxuan.nosdn.127.net/dae4d6e89ab8a0cd3e8da026e4660137.png" alt="">
                    </div>
                </div>
                <div class="bottom">
                    <div class="yutang">{{question.yutang}}</div>
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
                        <div class="imgdisplay" v-if="item.img !== undefined">
                            <img src="http://yanxuan.nosdn.127.net/e8bf0cf08cf7eda21606ab191762e35c.png" alt=""
                                 class="imgbig">
                        </div>
                        <div class="name">{{item.title}}</div>
                        <div class="info">
                            <p class="desc">{{item.desc}}</p>
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
                        <div class="imgdisplay" v-if="item.img !== undefined">
                            <img src="http://yanxuan.nosdn.127.net/e8bf0cf08cf7eda21606ab191762e35c.png" alt=""
                                 class="imgbig">
                        </div>
                        <div class="name">{{item.title}}</div>
                        <div class="info">
                            <p class="desc">{{item.desc}}</p>
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
                <img src="../../assets/images/avatar_default.png" alt="" class="image">
                <div class="info">
                    <div class="head">
                        <p class="tit">{{item.title}}</p>
                        <span class="level">{{item.level}}</span>
                    </div>
                    <div class="text">
                        <p class="desc">{{item.desc}}</p>
                    </div>
                    <div class="hot">
                        <span>人气{{item.renqi}}·发布{{item.fabu}}</span>
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
    import {getInterests, getQuestions, getTuhao, getChina, getLists, ERR_OK} from '@/api/data'
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
            getInterests().then(res => {
                if (res.status === ERR_OK) {
                    this.interests = res.data.interests
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
                getQuestions().then(res => {
                    if (res.status === ERR_OK) {
                        this.questions = res.data.questions
                        // console.log(this.questions)
                    }
                }).catch(err => {
                    console.log(err)
                }),
                getTuhao().then(res => {
                    if (res.status === ERR_OK) {
                        this.tuhao = res.data.tuhao
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
                getChina().then(res => {
                    if (res.status === ERR_OK) {
                        this.china = res.data.chinas
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
                getLists().then(res => {
                    if (res.status === ERR_OK) {
                        this.lists = res.data.lists
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
                    width: 2.5rem;
                    height: 2.5rem;
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
                            margin-top: 8px;
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
                background: url('../../assets/images/search.png') no-repeat;
                background-size: 0.5rem 0.5rem;
                width: 4rem;
                height: .53rem;
                padding-left: 1.2rem;
                box-sizing: border-box;
            }
            .more {
                width: 100%;
                text-align: right;
                .gomore {
                    font-size: 0.45rem;
                    font-weight: 700;
                    background: url('../../assets/images/search.png') right no-repeat;
                    background-size: 0.5rem 0.5rem;
                    width: 3rem;
                    height: .53rem;
                    padding-left: 1.2rem;
                    padding-right: 0.7rem;
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
            margin-bottom: 2rem;
        }
    }
</style>
