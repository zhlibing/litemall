<template>
    <div class="quesbox" v-if="item!==undefined">
        <div class="title">{{item.title || item.name}}</div>
        <div class="answer" @click="itemClick(item.id,item.type)">
            <span class="da">{{(item.content || item.description || item.brief).substring(0, 1)}}</span>
            <div class="text">{{item.content || item.description || item.brief}}</div>
            <div class="imgshow" v-if="item.picUrls !== undefined&&item.picUrls.length>0||item.picUrl!=undefined">
                <img :src="item.picUrl||item.picUrls[0]" alt="">
            </div>
        </div>
        <div class="bottom">
            <div class="yutang">{{"关联"}}</div>
            <div class="toanswer">去瞅一下</div>
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
                if (type != undefined) {
                    this.$router.push(`/items/publishdetails/${id}/${type}`);
                } else {
                    this.$router.push(`/items/detail/${id}`);
                }
            }
        }
    }
</script>
<style lang="scss" scoped>
    .quesbox {
        padding-top: 0.4rem;
        padding-bottom: 0.4rem;
        margin-left: 1rem;
        margin-right: 1rem;
        border-bottom: 1px solid #f7f7f7;
        .title {
            font-size: 0.8rem;
            font-weight: 400;
        }
        .answer {
            display: flex;
            font-size: 0.6rem;
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
                width: 5rem;
                height: 3rem;
                margin-left: 0.3em;
                object-fit: cover;
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
