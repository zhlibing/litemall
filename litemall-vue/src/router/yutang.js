const Tabbar = () => import('@/components/Tabbar/');

export default [
  {
    path: '/yutang',
    name: 'yutang',
    meta: {
      keepAlive: true
    },
    components: {
      default:  () => import('@/views/yutang/tabbar-yutang'),
      tabbar: Tabbar
    }
  }
];
