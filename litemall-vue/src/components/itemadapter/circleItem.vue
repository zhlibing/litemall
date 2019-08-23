<template>
    <div class="list" v-if="item!==undefined">
        <div class="layer">
            <img :src="item.picUrls!=undefined?item.picUrls[0]:default_avatar" alt="" class="image">
            <div class="layerchird" v-if="item.picUrls.length-1>0">
                <span>+{{item.picUrls.length - 1}}</span>
            </div>
        </div>
        <div class="info" @click="itemClick(item.id,item.type)">
            <div class="head">
                <p class="tit">{{item.content.substring(0, 8) + '...'}}</p>
                <span class="level">{{item.status + '级'}}</span>
            </div>
            <div class="text">
                <p class="desc">{{item.content}}</p>
            </div>
            <div class="hot">
                <span>人气{{item.status}}·发布{{item.addTime}}</span>
            </div>
        </div>
    </div>
</template>
<script>
    import {ImagePreview} from 'vant';

    export default {
        props: {
            item: Object,
        },
        methods: {
            previewimage(list, index) {
                ImagePreview(list, index);
            },
            itemClick(id, type) {
                this.$router.push(`/items/publishdetails/${id}/${type}`);
            }
        }
    }
</script>
<style lang="scss" scoped>
    .list {
        padding-top: .5rem;
        box-sizing: border-box;
        padding-bottom: .5rem;
        display: flex;
        border-bottom: 1px solid #f7f7f7;
        padding: 15px 15px;
        background-color: #ffffff;
        .layer {
            position: relative;
            .image {
                width: 7rem;
                height: 5rem;
                object-fit: cover;
                position: absolute;
            }
            .layerchird {
                left: 5px;
                bottom: 15px;
                width: 2.5rem;
                height: 2.5rem;
                border-radius: 50%;
                background-color: #000;
                filter: Alpha(Opacity=40);
                opacity: 0.4;
                position: absolute;
                text-align: center;
                span {
                    height: 2.5rem;
                    color: white;
                    line-height: 2.5rem;
                    font-size: 1.5rem;
                    font-weight: 600;
                }
            }
        }
        .info {
            flex: 1;
            margin-left: 7.6rem;
            .head {
                display: flex;
                .tit {
                    font-size: 15px;
                    font-weight: 400;
                    flex: 1;
                    margin-top: 0px;
                }
                .level {
                    font-size: 0.3rem;
                    width: 2rem;
                    height: 1.4rem;
                    line-height: 1.4rem;
                    background: url('../../assets/images/标签.png') no-repeat;
                    background-size: cover;
                    background-position: 110% 10%;
                    padding-left: 0.5rem;
                    box-sizing: border-box;
                    color: #888;
                }
            }
            .text {
                max-height: 50px;
                margin-top: -15px;
                font-size: 13px;
                font-weight: 200;
                line-height: 1.2rem;
                overflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 2;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 2;
            }
            .hot {
                margin-top: 0.3rem;
                font-size: 0.33rem;
                color: #666666;
            }
        }
    }
</style>
