<template>
    <div class="item">
        <div class="header_img"
             :style="`background-image: url(${comment.avatar})`"></div>
        <div class="comment">
            <div class="nickname">
                <span class="name">{{comment.nickname}}</span>
            </div>
            <div class="text">{{comment.content}}</div>
            <div class="imgbox"
                 v-show="comment.picList.length>0">
                <img alt=""
                     v-if="comment.picList.length== 1"
                     :src="comment.picList[0]"
                     :class="{releaseimgone:true}" @click="previewimage(comment.picList,0)"/>
                <img alt=""
                     v-else-if="comment.picList.length>= 2&&Math.round(comment.picList.length%2)==0"
                     v-for="(value,imgIndex) in comment.picList"
                     :key="imgIndex"
                     :src="value"
                     :class="{releaseimgtwo:true}" @click="previewimage(comment.picList,imgIndex)"/>
                <img alt=""
                     v-else-if="comment.picList.length>= 2&&Math.round(comment.picList.length%2)!=0"
                     v-for="(value,imgIndex) in comment.picList"
                     :key="imgIndex"
                     :src="value"
                     :class="{releaseimgthree:true}"
                     @click="previewimage(comment.picList,imgIndex)"/>
            </div>
            <div class="time">{{comment.addTime}}</div>
        </div>
    </div>
</template>
<script>
    import {ImagePreview} from 'vant';

    export default {
        props: {
            comment: Object,
        },
        methods: {
            previewimage(list, index) {
                ImagePreview(list, index);
            },
        }
    }
</script>
<style lang="scss" scoped>
    .item {
        padding: 15px;
        display: flex;
        border-bottom: dashed 1px #eee;
        .header_img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-size: 100%;
        }
        .comment {
            padding-left: 15px;
            flex: 1;
            display: flex;
            flex-direction: column;
            .nickname {
                padding-top: 5px;
                padding-bottom: 10px;
                .name {
                    padding-right: 10px;
                    font-weight: 400;
                }
            }
            .comment_title {
                padding-bottom: 5px;
                font-weight: bold;
            }
            .text {
                line-height: 22px;
                font-weight: 300;
            }
            .imgbox {
                margin-top: 0.5rem;
                .releaseimgone {
                    width: 65%;
                    float: left;
                    height: auto;
                    object-fit: contain;
                }
                .releaseimgtwo {
                    width: 47%;
                    margin-right: 0.1066666667rem;
                    margin-bottom: 0.1066666667rem;
                    height: 4rem;
                    object-fit: contain;
                }
                .releaseimgthree {
                    width: 30%;
                    margin-right: 0.1066666667rem;
                    margin-bottom: 0.1066666667rem;
                    height: 3rem;
                    object-fit: contain;
                }
            }
            .time {
                margin-top: 0.5rem;
                line-height: 18px;
                color: $font-color-gray;
                text-align: right;
            }
        }
    }
</style>
