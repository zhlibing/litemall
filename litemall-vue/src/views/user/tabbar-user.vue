<template>
  <div class="tabbar-user">
    <user-header :isLogin="isLogin"/>
    <order-group/>
    <coupon-group/>
    <user-module/>
    <floatbutton></floatbutton>
  </div>
</template>

<script>
import userHeader from './tabbar-user-header';
import orderGroup from './tabbar-user-order';
import couponGroup from './tabbar-user-coupon';
import userModule from './tabbar-user-module';
import floatbutton from '@/components/head/floatbutton'

export default {
  data() {
    return {
      isLogin: false
    };
  },

  activated() {
    this.getLoginStatus();
  },

  methods: {
    getLoginStatus() {
      this.isLogin =
        !!localStorage.getItem('Authorization');
    }
  },

  components: {
      floatbutton,
    [userHeader.name]: userHeader,
    [orderGroup.name]: orderGroup,
    [couponGroup.name]: couponGroup,
    [userModule.name]: userModule
  }
};
</script>


<style scoped lang="scss">
.tabbar-user {
  > div {
    margin-bottom: 0px;
  }
  &__quit {
    border: 0;
    border-radius: 0;
  }
}
</style>
