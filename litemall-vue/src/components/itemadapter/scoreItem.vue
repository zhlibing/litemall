<template>
    <div class="list" v-if="item!==undefined">
        <div class="line">
            <span>.</span>
        </div>
        <div class="time">
            <span>{{item.info.addTime}}</span>
        </div>
        <div class="desc">
            <span>{{item.info.name.substring(0, 8) + '...'}}</span>
        </div>
        <div class="score">
            <span>{{item.info.score}}</span>
        </div>
    </div>
</template>
<script>
    import {ImagePreview} from 'vant';

    export default {
        props: {
            item: Object,
        },
        methods: {}
    }
</script>
<style lang="scss" scoped>
    .list {
        display: flex;
        flex-direction: row;
        background-color: #FFFFFF;
        border-top: 1px solid #f7f7f7;
        .line {
            margin-left: 10px;
            span {
                margin-left: -14px;
                margin-top: -25px;
                line-height: 5px;
                font-size: 50px;
                color: #ffda44;
            }
            &:before {
                display: inline-block;
                content: '';
                width: 1px;
                height: 100%;
                background-color: #ffda44;
                vertical-align: top;
                margin-right: 0.5rem;
            }
        }
        .time {
            flex: 4;
            padding: 10px;

        }
        .desc {
            flex: 2;
            padding: 10px;
        }
        .score {
            flex: 1;
            padding: 10px;
        }
    }
</style>
