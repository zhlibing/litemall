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
            },
            {
                path: 'batchallenge/:type',
                props: true,
                component: () => import('@/views/publish/sell/batchallenge'),
            },
            {
                path: 'publishgroup/:type',
                props: true,
                component: () => import('@/views/publish/sell/publishgroup'),
            },
            {
                path: 'publishcircle/:type',
                props: true,
                component: () => import('@/views/publish/sell/publishcircle'),
            },
            {
                path: 'publishfishponds/:type',
                props: true,
                component: () => import('@/views/publish/sell/publishfishponds'),
            },
            {
                path: 'publishquestion/:type',
                props: true,
                component: () => import('@/views/publish/sell/publishquestion'),
            },
        ]
    },
    {
        path: '/publish/publishcomment',
        name: 'publishcomment',
        components: {
            default: () => import('@/views/publish/sell/publishcomment'),
        }
    },
    {
        path: '/publish/publishactivityresult',
        name: 'publishactivityresult',
        components: {
            default: () => import('@/views/publish/sell/publishactivityresult'),
        }
    },
    {
        path: '/publish/items/publishhead',
        name: 'publishhead',
        components: {
            default: () => import('@/views/publish/items/publishhead'),
        }
    }
];
