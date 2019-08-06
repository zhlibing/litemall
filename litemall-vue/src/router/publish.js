const Tabbar = () => import('@/components/Tabbar/');

export default [
  {
    path: '/publish',
    name: 'publish',
    meta: {
      keepAlive: true,
        login: true
    },
    components: {
      default:  () => import('@/views/publish/publish'),
    }
  }
];
