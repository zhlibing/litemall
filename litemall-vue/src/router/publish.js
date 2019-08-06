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
    },
    {
        path: '/publish/sell',
        redirect: '/publish/sell/price',
        name: 'sell',
        components: {
            default: () => import('@/views/publish/sell/sell'),
        },
        children: [
            {
                path: 'price',
                component: () => import('@/views/publish/sell/price'),
            },
            {
                path: 'uction',
                component: () => import('@/views/publish/sell/uction'),
            }
        ]
    }
];
