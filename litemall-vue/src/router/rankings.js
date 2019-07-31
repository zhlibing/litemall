const Tabbar = () => import('@/components/Tabbar/');

export default [
    {
        path: '/rankings',
        name: 'rankings',
        meta: {
            keepAlive: true
        },
        components: {
            default: () => import('@/views/rankings/tabbar-rankings'),
            tabbar: Tabbar
        }
    }
];
