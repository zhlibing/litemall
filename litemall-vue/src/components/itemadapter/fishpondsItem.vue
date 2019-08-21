<template>
    <div class="brand-info" v-if="item!==undefined" @click="itemClick(item.info.id,item.info.type)">
        <div class="name">
            <img class="img"
                 :src="item.info.picUrls[0]"
                 background-size="cover"/>
            <div class="info-box">
                <div class="txt">{{item.info.title}}</div>
                <div class="line"></div>
                <div class="price">{{'￥' + item.info.reword}}元起</div>
                <div class="activity" v-if="item.publishUser!=undefined">
                    <div class="acinfo">
                        <img :src="item.publishUser.user.avatar"/>
                    </div>
                    <div class="acinfo">
                        <img :src="item.publishUser.user.avatar"/>
                    </div>
                    <div class="acinfo">
                        <img :src="item.publishUser.user.avatar"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="desc" v-if="item.publishUser!=undefined">
            <img :src="item.publishUser.user.avatar"/>
            <div class="loc">
                <span>{{item.info.description}}</span>
                <span class="span2">北京市黄埔屯中大街888号人民大会堂一楼888室VIP回踏</span>
            </div>
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
            return {};
        },
        mounted() {
        },
        methods: {
            itemClick(id, type) {
                this.$router.push(`/items/publishdetails/${id}/${type}`);
            },
        },
        components: {}
    }
</script>
<style lang="scss" scoped>
    .brand-info {
        padding: 0 15px;
        background-color: #fff;
        .name {
            width: 100%;
            height: 130px;
            position: relative;
            .img {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 130px;
                object-fit: cover;
            }

            .info-box {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 130px;
                text-align: center;
                display: flex;
                justify-content: center;
                align-items: center;
                display: block;
                background-color: rgba(0, 0, 0, 0.4);

                .txt {
                    margin-top: 10px;
                    height: 20px;
                    font-size: 15px;
                    color: #fff;
                }

                .line {
                    margin: 0 auto;
                    margin-top: 10px;
                    display: block;
                    height: 2px;
                    width: 300px;
                    background: #fff;
                }
                .price {
                    height: 15px;
                    font-size: 13px;
                    margin-top: 10px;
                    color: #fff;
                }
                .activity {
                    width: 100%;
                    display: flex;
                    margin-top: 10px;
                    .acinfo {
                        flex: 1;
                        img {
                            height: 3rem;
                            width: 3rem;
                            border-radius: 10%;
                        }
                    }
                }
            }
        }
        .desc {
            display: flex;
            background: #fff;
            width: 100%;
            height: auto;
            overflow: hidden;
            padding: 10px 10px;
            font-size: 15px;
            color: #666;
            line-height: 20px;
            text-align: left;
            img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                margin-right: 10px;
            }
            .loc {
                display: flex;
                flex-direction: column;
                span {
                    height: 20px;
                    font-size: 13px;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    display: -webkit-box;
                    -webkit-box-orient: vertical;
                    -webkit-line-clamp: 1;
                }
                .span2 {
                    color: #1e6abc;
                    font-size: 13px;
                    margin-top: 5px;
                    background: url('../../assets/images/定位.png') no-repeat;
                    background-size: 1.1rem 1.1rem;
                    padding-left: 20px;
                }
            }
        }
    }
</style>
