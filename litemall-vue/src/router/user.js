const tab_user = () => import('@/views/user/tabbar-user');
const UserCollect = () => import('@/views/user/module-collect');
const UserCollectUser = () => import('@/views/user/module-collect-user');
const UserCollectUserMe = () => import('@/views/user/module-collect-user-me');
const UserUserZan = () => import('@/views/user/module-zan');
const UserUserJoin = () => import('@/views/user/module-join');
const UserUserGroup = () => import('@/views/user/module-group');
const UserUserFishponds = () => import('@/views/user/module-fishponds');
const UserAddress = () => import('@/views/user/module-address');
const UserAddressEdit = () => import('@/views/user/module-address-edit');
const UserServer = () => import('@/views/user/module-server');
const UserHelp = () => import('@/views/user/module-help');
const UserFeedback = () => import('@/views/user/module-feedback');

const UserInformation = () => import('@/views/user/user-information-set');
const UserInfo_SetMobile = () => import('@/views/user/user-information-set/set-mobile');
const UserInfo_SetNickname = () => import('@/views/user/user-information-set/set-nickname');
const UserInfo_SetPassword = () => import('@/views/user/user-information-set/set-password');

const UserOrderList = () => import('@/views/user/order-list');
const UserCouponList = () => import('@/views/user/coupon-list');
const UserRefundList = () => import('@/views/user/refund-list');

const Tabbar = () => import('@/components/Tabbar/');

export default [
  {
    path: '/user',
    name: 'user',
    meta: {
      keepAlive: true
    },
    components: { default: tab_user, tabbar: Tabbar }
  },
  {
    path: '/user/collect',
    name: 'collect',
    meta: {
      login: true
    },
    component: UserCollect
  },
    {
        path: '/user/collectUser/:userId',
        name: 'collectUser',
        meta: {
            login: true
        },
        component: UserCollectUser
    },
    {
        path: '/user/collectUserMe/:userId',
        name: 'collectUserMe',
        meta: {
            login: true
        },
        component: UserCollectUserMe
    },
    {
        path: '/user/zan',
        name: 'userZan',
        meta: {
            login: true
        },
        component: UserUserZan
    },
    {
        path: '/user/join',
        name: 'userJoin',
        meta: {
            login: true
        },
        component: UserUserJoin
    },
    {
        path: '/user/fishponds',
        name: 'userFishponds',
        meta: {
            login: true
        },
        component: UserUserFishponds
    },
    {
        path: '/user/group',
        name: 'userGroup',
        meta: {
            login: true
        },
        component: UserUserGroup
    },
  {
    path: '/user/address',
    name: 'address',
    meta: {
      login: true
    },
    component: UserAddress
  },
  {
    path: '/user/address/edit',
    name: 'address-edit',
    props: true,
    meta: {
      login: true
    },
    component: UserAddressEdit
  },
  {
    path: '/user/server',
    name: 'user-server',
    component: UserServer
  },
  {
    path: '/user/help',
    name: 'user-help',
    component: UserHelp
  },
  {
    path: '/user/feedback',
    name: 'user-feedback',
    component: UserFeedback
  },  
  {
    path: '/user/information',
    name: 'user-information',
    meta: {
      login: true
    },
    component: UserInformation
  },
  {
    path: '/user/information/setMobile',
    name: 'user-info-setMobile',
    component: UserInfo_SetMobile
  },
  {
    path: '/user/information/setNickname',
    name: 'user-info-setNickname',
    component: UserInfo_SetNickname
  },
  {
    path: '/user/information/setPassword',
    name: 'user-info-setPassword',
    component: UserInfo_SetPassword
  },
  {
    path: '/user/order/list/:active',
    name: 'user-order-list',
    props: true,
    component: UserOrderList
  },
  {
    path: '/user/coupon/list/:active',
    name: 'user-coupon-list',
    props: true,
    component: UserCouponList
  },
  {
    path: '/user/refund/list',
    name: 'user-refund-list',
    component: UserRefundList
  }
];
