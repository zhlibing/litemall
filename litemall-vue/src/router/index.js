import Vue from 'vue';
import Router from 'vue-router';
import { getLocalStorage } from '@/utils/local-storage';

import home from './home';
import items from './items';
import rankings from './rankings';
import yutang from './yutang';
import user from './user';
import order from './order';
import login from './login';
import publish from "./publish";

Vue.use(Router);

const RouterModel = new Router({
  routes: [...home, ...yutang, ...user, ...order, ...login,...items,...rankings,...publish]
});

RouterModel.beforeEach((to, from, next) => {
  const { Authorization } = getLocalStorage(
    'Authorization'
  );
  if (!Authorization) {
    if (to.meta.login) {
      next({ name: 'login', query: { redirect: to.name } });
      return;
    }
  }
  next();
});

export default RouterModel;
