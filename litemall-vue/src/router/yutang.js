const Tabbar = () => import('@/components/Tabbar/');
const ListQuestion = () => import('@/views/yutang/question-list');
const ListCircle = () => import('@/views/yutang/circle-list');

export default [
    {
        path: '/yutang',
        name: 'yutang',
        meta: {
            keepAlive: true
        },
        components: {
            default: () => import('@/views/yutang/tabbar-yutang'),
            tabbar: Tabbar
        }
    },
    {
        path: '/yutang/question-list',
        name: 'ListQuestion',
        component: ListQuestion
    },
    {
        path: '/yutang/circle-list',
        name: 'ListCircle',
        component: ListCircle
    },
];
