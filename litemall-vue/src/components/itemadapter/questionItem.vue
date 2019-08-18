<template>
    <div class="quesbox" v-if="item!==undefined" @click="itemClick(item.id,item.type)">
        <div class="title">{{item.title || "暂无标题"}}</div>
        <div class="answer">
            <span class="da">答</span>
            <div class="text">{{item.content || item.description}}</div>
            <div v-if="item.picUrls !== undefined&&item.picUrls.length>0">
                <div class="layerchird">
                    <span v-if="item.picUrls.length-1>0">+{{item.picUrls.length - 1}}</span>
                </div>
                <img :src="item.picUrls[0]" alt="">
            </div>
        </div>
        <div class="bottom">
            <div class="yutang">{{item.title}}</div>
            <div class="toanswer">去回答</div>
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
    .quesbox {
        padding-top: 0.4rem;
        padding-bottom: 0.4rem;
        border-bottom: 1px solid #f7f7f7;
        .title {
            font-size: 15px;
            font-weight: 400;
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
                font-size: 13px;
                font-weight: 500;
                line-height: 1.2rem;
            }
            img {
                width: 7rem;
                height: 5rem;
                margin-top: -2.7rem;
                margin-left: 0.3em;
            }
            .layerchird {
                width: 2.5rem;
                height: 2.5rem;
                margin-left: 4.4rem;
                border-radius: 50%;
                background-color: #000;
                filter: Alpha(Opacity=60);
                opacity: 0.6;
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
</style>
