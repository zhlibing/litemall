<template>
    <div class="user_module">
        <van-cell-group>
            <van-cell icon="cart" title="购物车" to="/order" isLink/>
            <van-cell icon="shoucang" title="我的鱼塘" to="/user/fishponds" isLink/>
            <van-cell icon="shoucang" title="我的战队" to="/user/group" isLink/>
            <van-cell icon="shoucang" title="我的点赞" to="/user/zan" isLink/>
            <van-cell icon="shoucang" title="收藏商品" to="/user/collect" isLink/>
            <van-cell icon="dingwei" title="收货地址" to="/user/address" isLink/>
            <van-cell icon="kefu" title="服务中心" to="/user/server" isLink/>
        </van-cell-group>
    </div>
</template>

<script>
    export default {
        name: 'user-module'
    };
</script>


<style scoped lang="scss">
    .user_module {
        background-color: #fff;
    }
</style>
