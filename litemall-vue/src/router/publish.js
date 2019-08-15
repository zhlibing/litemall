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
        redirect: '/publish/sell/batfree',
        name: 'sell',
        components: {
            default: () => import('@/views/publish/sell/sell'),
        },
        children: [
            {
                path: 'batfree/:type',
                props: true,
                component: () => import('@/views/publish/sell/batfree'),
            },
            {
                path: 'batpk/:type',
                props: true,
                component: () => import('@/views/publish/sell/batpk'),
            }
        ]
    },
    {
        path: '/publish/publishcomment',
        name: 'publishcomment',
        components: {
            default: () => import('@/views/publish/sell/publishcomment'),
        }
    }
];
