const Tabbar = () => import('@/components/Tabbar/');

export default [
    {
        path: '/publish',
        name: 'publish',
        meta: {
            login: true
        },
        components: {
            default: () => import('@/views/publish/publish'),
        }
    }
];
