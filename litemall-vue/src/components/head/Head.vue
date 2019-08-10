<template>
    <div class="header">
        <div class="scanner">
            <img src="../../assets/images/二维码.png" alt="">
            <input type="file" name="file" capture="camera"/>
        </div>
        <router-link to="/items/search" class="search">
            <input type="search" placeholder="搜索宝贝/鱼塘/用户">
        </router-link>
        <router-link to="/items">
            <div class="category" @click="">
                <img src="../../assets/images/分类.png" alt="">
            </div>
        </router-link>
        <router-view></router-view>
    </div>
</template>
<script>
    export default {}
</script>
<style lang="scss" scoped>
    .header {
        z-index: 200;
        height: 2.75rem;
        width: 100%;
        background-color: #fff;
        position: fixed;
        top: 0;
        display: flex;
        align-items: center;
        border-bottom: 1px solid #f7f7f7;
        .scanner {
            width: 25px;
            height: 25px;
            margin-left: 1rem;
            margin-right: 0.5rem;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            img {
                width: 20px;
                height: 20px;
            }
            input {
                display: none;
            }
        }
        .search {
            flex: 1;
            display: flex;
            align-items: center;
            background-color: #F8F9FB;
            border-radius: 5rem;
            /*border: 0.04rem solid #cccccc;*/
            input {
                background: url('../../assets/images/search.png') no-repeat .26rem 50%;
                background-size: 25px 25px;
                border-radius: .11rem;
                padding-left: 35px;
                outline: none;
                height: 30px;
                border: none;
            }
        }
        .category {
            width: 25px;
            height: 25px;
            margin-left: 0.5rem;
            margin-right: 1rem;
            display: flex;
            align-items: center;
            img {
                width: 20px;
                height: 20px;
            }
        }
    }
</style>
