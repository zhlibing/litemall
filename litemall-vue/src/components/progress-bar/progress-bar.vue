<template>
    <div class="vue-progress-bar default-theme" style="padding-bottom: 10px">
        <div class="vue-progress-bar__tip" style="padding-bottom: 10px;padding-top: 10px">
            <span class="vue-progress-bar__tiplabel">{{label}}</span>
            <span class="vue-progress-bar__tiptext">{{text}}</span>
        </div>
        <div class="vue-progress-bar__outer">
            <div class="vue-progress-bar__inner" :style="barStyle"></div>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            label: String,
            text: String,
            height: {
                type: Number,
                default: 0,
                required: true,
                validator: val => val >= 0
            },
            color: {
                type: String,
                default: '#456'
            },
            percentage: {
                type: Number,
                default: 0,
                required: true,
                validator: val => val >= 0 && val <= 100
            }
        },
        computed: {
            barStyle() {
                const style = {};
                style.width = this.percentage + '%';
                style.height = this.height + 'px';
                style.backgroundColor = this.color;
                return style;
            }
        }
    }
</script>

<style lang="scss" scoped>
    .vue-progress-bar.default-theme {
        .vue-progress-bar__outer {
            background: #FFE8CC;
        }
    }

    .vue-progress-bar {
        .vue-progress-bar__tiptext {
            float: right;
        }
    }
</style>