<template>
    <div class="container" @scroll="scrollGet($event)">
        <Head></Head>
        <floatbutton></floatbutton>
        <div class="interest" style="margin-top: 40px">
            <div class="titlebox">
                <p class="title">你可能感兴趣的鱼场</p>
            </div>
            <div class="swiper-container" ref="swiper">
                <div class="swiper-wrapper swiper">
                    <div class="swiper-slide item" v-for="(item,index) in interests" :key="index">
                        <img :src="item.picUrls[0]" alt="" class="img" @click="itemClick(item.id,item.type)">
                        <div class="name">{{item.title}}</div>
                        <div class="info">
                            <p class="desc">{{item.description}}</p>
                        </div>
                        <button class="add">+ 加入</button>
                    </div>
                </div>
            </div>
            <is-empty v-if="interests.length==0">很遗憾，啥都没有~</is-empty>
        </div>
        <div class="questions">
            <div class="titlebox">
                <p class="title">有问题问一问</p>
                <span class="ask">去提问</span>
            </div>
            <div v-for="(item,index) in questions" :key="index">
                <question :item="item"></question>
            </div>
            <is-empty v-if="questions.length==0">很遗憾，啥都没有~</is-empty>
            <div class="more" @click="goQuestionList">
                <span class="gomore">更多</span>
            </div>
        </div>
        <div class="interest">
            <div class="titlebox">
                <p class="title">土豪圈儿战队</p>
            </div>
            <div class="swiper-container" ref="tuhao">
                <div class="swiper-wrapper swiper">
                    <div class="swiper-slide item" v-for="(item,index) in tuhao" :key="index">
                        <div class="imgdisplay" v-if="item.picUrls !== undefined" @click="itemClick(item.id,item.type)">
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
            <is-empty v-if="tuhao.length==0">很遗憾，啥都没有~</is-empty>
        </div>
        <div class="interest">
            <div class="titlebox">
                <p class="title">热门人气活动</p>
            </div>
            <div class="swiper-container" ref="china">
                <div class="swiper-wrapper swiper">
                    <div class="swiper-slide item" v-for="(item,index) in china" :key="index">
                        <div class="imgdisplay" v-if="item.picUrls !== undefined" @click="itemClick(item.id,item.type)">
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
            <is-empty v-if="china.length==0">很遗憾，啥都没有~</is-empty>
        </div>
        <div class="interest bottom2 questions">
            <div class="titlebox">
                <p class="title">渔儿都在说</p>
            </div>
            <div v-for="(item,index) in lists" :key="index">
                <circleItem :item="item"></circleItem>
            </div>
            <is-empty v-if="lists.length==0">很遗憾，啥都没有~</is-empty>
            <div class="more" @click="goCircleList">
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
    import floatbutton from '@/components/head/floatbutton'
    import avatar from '../../assets/images/store_default.png'
    import {EventBus} from '../../utils/event-bus'
    import question from '../../components/itemadapter/questionItem.vue'
    import circleItem from '../../components/itemadapter/circleItem.vue'
    import IsEmpty from '@/components/is-empty/';

    export default {
        data() {
            return {
                interests: [],
                questions: [],
                tuhao: [],
                china: [],
                lists: [],
                scroll: 0,
                default_avatar: avatar
            }
        },
        methods: {
            scrollGet(e) {
                console.log(e.srcElement.scrollTop, e.target.scrollTop)
                this.scroll = e.srcElement.scrollTop
            },
            refreshCircle() {
                circleList().then(res => {
                    if (res.status === 200) {
                        this.lists = res.data.data.list
                    }
                }).catch(err => {
                    console.log(err)
                })
            },
            itemClick(id, type) {
                this.$router.push(`/items/publishdetails/${id}/${type}`);
            },
            goQuestionList() {
                this.$router.push(`/yutang/question-list`);
            },
            goCircleList() {
                this.$router.push(`/yutang/circle-list`);
            },
        },
        components: {
            Head,
            floatbutton,
            avatar,
            question,
            circleItem,
            [IsEmpty.name]: IsEmpty,
        },
        mounted() {
            EventBus.$on("circleSave", ({num, deg}) => {
                this.refreshCircle()
                console.log(num, deg, '>>>>refreshCircle')
                this.$nextTick(() => {
                    console.log(num, deg, '>>>>refreshCircle_$nextTick')
                })
            })
        },
        beforeDestroy() {
            console.log('beforeDestroy', '>>>>YUTANG.vue')
            EventBus.$off('circleSave')
        },
        activated() {
            if (this.scroll >= 0) {
                document.querySelector(".container").scrollTo(0, this.scroll);
                this.scroll = 0;
            }
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
                                slidesPerView: 2,
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
                                slidesPerView: 2,
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
                this.refreshCircle()
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
            box-sizing: border-box;
            .titlebox {
                box-sizing: border-box;
                width: 100%;
                display: flex;
                padding: 15px 15px;
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
            .swiper-container {
                margin: 0px 15px;
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
                        height: 5rem;
                        margin-bottom: 0.2rem;
                        object-fit: cover;
                    }
                    .name {
                        color: #000000;
                        font-size: 15px;
                        font-weight: 400;
                        white-space: nowrap;
                        overflow: hidden;
                        text-overflow: ellipsis;
                        margin-bottom: 0.2rem;
                    }
                    .desc {
                        color: #888888;
                        font-size: 13px;
                        font-weight: 400;
                        white-space: nowrap;
                        overflow: hidden;
                        text-overflow: ellipsis;
                        margin-bottom: 0.2rem;
                    }
                    .img {
                        width: 100%;
                        height: 5rem;
                        margin-bottom: 0.2em;
                        object-fit: cover;
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
                font-weight: 600;
                background: url('../../assets/images/提问.png') no-repeat;
                background-size: 1.1rem 1.1rem;
                width: 4rem;
                height: 1rem;
                padding-left: 1.4rem;
                box-sizing: border-box;
            }
            .more {
                width: 100%;
                text-align: right;
                padding: 15px;
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
        }

        .bottom2 {
            margin-bottom: 0.1rem;
        }
    }
</style>
