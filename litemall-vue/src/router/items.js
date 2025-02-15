const Tabbar = () => import('@/components/Tabbar/');

export default [
  {
    path: '/items',
    name: 'class',
    meta: {
      keepAlive: true
    },
    components: {
      default:  () => import('@/views/items/tabbar-catalog'),
      // tabbar: Tabbar
    }
  },
  {
    path: '/items/search',
    name: 'search',
      // redirect: '/items/search/new',
    meta: {
      keepAlive: true
    },
    component: () => import('@/views/items/search'),
      children: [
          {
              path: 'new',
              component: () => import('@/components/field'),
              meta: {
                  keepAlive: false
              }
          },
          {
              path: 'comment',
              component: () => import('@/views/items/search/comment'),
              meta: {
                  keepAlive: false
              }
          }
      ]
  },
  {
    path: '/items/detail/:itemId',
    name: 'detail',
    props: true,
    component: () => import('@/views/items/detail')
  },
    {
        path: '/items/publishdetails/:itemId/:type',
        name: 'publishdetails',
        props: true,
        component: () => import('@/views/items/publishdetails')
    },
    {
        path: '/items/userdetails/:userId',
        name: 'userdetails',
        props: true,
        component: () => import('@/views/items/userdetails')
    },
  {
    path: '/items/category',
    name: 'category',
    component: () => import('@/views/items/category'),
    props: route => route.query
  },
  {
    path: '/items/hot',
    name: 'hot',
    component: () => import('@/views/items/hot'),
    props: route => route.query
  },
  {
    path: '/items/new',
    name: 'new',
    component: () => import('@/views/items/new'),
    props: route => route.query
  },
  {
    path: '/items/groupon',
    name: 'groupon',
    component: () => import('@/views/items/groupon'),
    props: route => route.query
  },
  {
    path: '/items/brand/:brandId',
    name: 'brand',
    props: true,
    component: () => import('@/views/items/brand')
  },
  {
    path: '/items/brand-list',
    name: 'brandList',
    component: () => import('@/views/items/brand-list'),
    props: route => route.query
  },
    {
        path: '/items/fishpondsSelect-list',
        name: 'fishpondsSelect',
        component: () => import('@/views/items/fishpondsSelect-list'),
        props: route => route.query
    },
  {
    path: '/items/topic/:topicId',
    name: 'topic',
    props: true,
    component: () => import('@/views/items/topic')
  },
  {
    path: '/items/topic-list',
    name: 'topicList',
    component: () => import('@/views/items/topic-list'),
    props: route => route.query
  }
];
