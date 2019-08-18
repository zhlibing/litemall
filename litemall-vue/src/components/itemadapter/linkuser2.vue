<template>
    <div class="box" v-if="item!==undefined">
        <div class="answer" @click="itemClick(item.user.id)">
            <div class="imgshow">
                <img :src="item.user.avatar" alt="">
            </div>
            <div class="content">
                <div class="name">{{item.user.nickname || item.user.username}}</div>
                <div class="score" v-if="item.key==undefined">{{'10战8胜 胜率80% 王者'}}</div>
                <div class="score" v-else>{{'成功举办10场活动'}}</div>
            </div>
        </div>
        <div class="bottom">
            <div class="yutang">{{"人气" + item.collectCount}}</div>
        </div>
    </div>
</template>
<script>
    import {ImagePreview} from 'vant';
    import {
        collectAddOrDelete,
    } from '@/api/api';

    export default {
        props: {
            item: Object,
        },
        methods: {
            previewimage(list, index) {
                ImagePreview(list, index);
            },
            itemClick(id) {
                this.$router.push(`/items/userdetails/${id}`);
            },
            addCollect() {
                collectAddOrDelete({valueId: this.item.user.id, type: '10'}).then(res => {
                    if (this.item.userHasCollect === 1) {
                        this.item.userHasCollect = 0;
                        this.item.collectCount -= 1
                    } else {
                        this.item.userHasCollect = 1;
                        this.item.collectCount += 1;
                        this.$toast({
                            message: '关注成功',
                            duration: 1500
                        });
                    }
                });
            },
        }
    }
</script>
<style lang="scss" scoped>
    .box {
        margin-top: 0.5rem;
        margin-left: 0.2rem;
        margin-right: 0.2rem;
        padding-bottom: 0.5rem;
        border-bottom: 1px solid #f7f7f7;
        .answer {
            display: flex;
            font-size: 0.6rem;
            font-weight: 200;
            color: #666666;
            justify-content: center;
            .imgshow {
                img {
                    width: 2rem;
                    height: 2rem;
                    border-radius: 50%;
                }
            }
            .content {
                flex: 1;
                display: flex;
                flex-direction: column;
                .name {
                    font-weight: 500;
                    margin-left: 10px;
                    line-height: 1.2rem;
                    font-size: 0.9rem;
                    color: #7dc5eb;
                }
                .score {
                    margin-top: 5px;
                    font-weight: 500;
                    margin-left: 10px;
                    line-height: 1.2rem;
                    font-size: 0.9rem;
                    color: #555555;
                }
            }
            .da {
                background-color: #ffda44;
                border-radius: 10%;
                display: inline-block;
                width: 3rem;
                height: 1rem;
                display: flex;
                margin-top: 5px;
                justify-content: center;
                align-items: center;
                font-weight: 400;
                color: #333333;
            }
        }
        .bottom {
            display: flex;
            font-size: 0.6rem;
            margin-top: 0.2rem;
            .yutang {
                flex: 1;
                color: #999;
            }
            .toanswer {
                color: #7dc5eb;
                font-weight: 500;
                padding: 0 5px;
                border: 0.04rem solid #7dc5eb;
            }
        }
    }
</style>
