<template>
    <div class="list" v-if="item!==undefined&&item.info!=undefined">
        <div class="layer">
            <img :src="item.info.avatar!=undefined?item.info.avatar:default_avatar" alt=""
                 class="image">
        </div>
        <div class="info" @click="itemClick(item)">
            <div class="head">
                <p class="tit">{{item.info.username.substring(0, 8) + '...'}}</p>
                <img class="select" type="checkbox" src="../../assets/images/selected_yes.png"
                     v-if="selectedIndex==index">
                <img class="select" type="checkbox" src="../../assets/images/selected_no.png" v-else>
            </div>
            <div class="text">
                <p class="desc">{{item.info.nickname}}</p>
            </div>
            <div class="hot">
                <span>等级：{{item.info.userLevel}}</span>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        props: {
            item: Object,
            index: 0,
            selectedIndex: 0
        },
        data() {
            return {}
        },
        methods: {
            itemClick(item) {
                this.$emit('onItemSelect', item, this.index)
            }
        },
        components: {}
    }
</script>
<style lang="scss" scoped>
    .list {
        padding-top: .5rem;
        box-sizing: border-box;
        padding-bottom: .5rem;
        display: flex;
        border-bottom: 1px solid #f7f7f7;
        .layer {
            position: relative;
            .image {
                width: 4rem;
                height: 4rem;
                object-fit: cover;
                position: absolute;
            }
        }
        .info {
            flex: 1;
            margin-left: 4.6rem;
            .head {
                display: flex;
                .tit {
                    font-size: 0.9rem;
                    font-weight: 400;
                    flex: 1;
                    margin-top: 0px;
                }
                .select {
                    width: 1.5rem;
                    height: 1.5rem;
                    background-size: cover;
                }
            }
            .text {
                max-height: 50px;
                margin-top: -15px;
                font-size: 0.8rem;
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
