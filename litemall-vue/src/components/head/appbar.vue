<template>
    <nav class="header" v-if="titleText!==undefined">
        <div class="fixedcontent" :style="mystyle">
            <slot name='left' v-show="left!=null">
                <div class="left" @click='leftclick'>
                    <img :src="left!=null?left:left_url" alt="">
                </div>
            </slot>
            <slot name='title'>
                <div class="title">
                    <span class="titleText">{{titleText.length > 8 ? titleText.substring(0, 8) + '...' : titleText}}</span>
                </div>
            </slot>
            <slot name='right'>
                <div class="right" @click='rightclick'>
                    <img :src="right" alt="" v-show="right!=null">
                </div>
            </slot>
        </div>
    </nav>
</template>
<script>
    import goback from '../../assets/images/goback.png'

    export default {
        props: {
            // 左边配置
            left: String,
            // 标题配置
            titleText: String,
            //右边配置
            right: String,
            //左默认点击事件
            overrideleftclick: Boolean,
            mystyle: {}
        },
        data() {
            return {
                left_url: goback,
            }
        },
        methods: {
            leftclick() {
                this.$emit('leftclick');
                if (!this.overrideleftclick) {
                    this.goBack()
                }
            },
            rightclick() {
                this.$emit('rightclick');
            },
            goBack() {
                this.$router.back(-1)
            },
        },
        components: {
            goback,
        }
    }
</script>
<style scoped lang="scss">
    .header {
        height: 2.75rem;
    }

    .fixedcontent {
        height: 2.75rem;
        width: 100%;
        position: fixed;
        z-index: 10;
        top: 0;
        left: 0;
        background-color: #ffffff;
        display: flex;
        flex-direction: row;
        align-items: center;
        color: #000000;
    }

    .left {
        flex: 1;
        display: flex;
        align-items: center;
        padding: 0 .625rem;
        font-size: .875rem;
        img {
            width: 1.5rem;
            height: 1.5rem;
        }
    }

    .title {
        -webkit-box-flex: 2;
        -webkit-flex: 2;
        -ms-flex: 2;
        flex: 2;
        display: flex;
        justify-content: center;
        -webkit-flex-direction: row;
        -ms-flex-direction: row;
        flex-direction: row;
    }

    .titleText {
        font-size: 1.25rem;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 1;
        overflow: hidden;
    }

    .right {
        display: flex;
        flex: 1;
        justify-content: flex-end;
        padding: 0 .625rem;
        font-size: .875rem;
        img {
            width: 1.5rem;
            height: 1.5rem;
        }
    }
</style>
