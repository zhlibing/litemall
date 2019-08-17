<template>
    <div class="brand-info" v-if="item!==undefined" @click="itemClick(item.FishPondsInfo.id,item.FishPondsInfo.type)">
        <div class="name">
            <img class="img"
                 :src="item.FishPondsInfo.picUrls[0]"
                 background-size="cover"/>
            <div class="info-box">
                <div class="txt">{{item.FishPondsInfo.title}}</div>
                <div class="line"></div>
                <div class="price">{{'￥' + item.FishPondsInfo.reword}}元起</div>
            </div>
        </div>
        <div class="desc">
            {{item.FishPondsInfo.description}}
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
        .name {
            width: 100%;
            height: 180px;
            position: relative;

            .img {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 180px;
                object-fit: cover;
            }

            .info-box {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 180px;
                text-align: center;
                display: flex;
                justify-content: center;
                align-items: center;
                display: block;
                background-color: rgba(0, 0, 0, 0.4);

                .txt {
                    margin-top: 60px;
                    height: 25px;
                    font-size: 25px;
                    color: #fff;
                }

                .line {
                    margin: 0 auto;
                    margin-top: 16px;
                    display: block;
                    height: 2px;
                    width: 300px;
                    background: #fff;
                }
                .price {
                    height: 25px;
                    font-size: 25px;
                    color: #fff;
                }
            }
        }
        .desc {
            background: #fff;
            width: 100%;
            height: auto;
            overflow: hidden;
            padding: 25px 20px;
            font-size: 20px;
            color: #666;
            line-height: 20px;
            text-align: center;
        }
    }
</style>
