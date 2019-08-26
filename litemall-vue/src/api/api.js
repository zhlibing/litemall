import request from '@/utils/request'
import axios from 'axios'

const IndexUrl= 'wx/home/index'; //首页数据接口
export function getHome() {
  return request({
    url: IndexUrl,
    method: 'get'
  })
}

const CatalogList='wx/catalog/index'; //分类目录全部分类数据接口
export function catalogList() {
  return request({
    url: CatalogList,
    method: 'get'
  })
}

const CatalogCurrent='wx/catalog/current'; //分类目录当前分类数据接口
export function catalogCurrent(query) {
  return request({
    url: CatalogCurrent,
    method: 'get',
    params: query
  })
}

const AuthLoginByWeixin='wx/auth/login_by_weixin'; //微信登录


const AuthLoginByAccount='wx/auth/login'; //账号登录
export function authLoginByAccount(data) {
  return request({
    url: AuthLoginByAccount,
    method: 'post',
    data
  })
}
const AuthLogout='wx/auth/logout'; //账号登出
export function authLogout() {
  return request({
    url: AuthLogout,
    method: 'post'
  })
}
const AuthInfo='wx/auth/info'; //用户信息
export function authInfo() {
  return request({
    url: AuthInfo,
    method: 'get'
  })
}
const AuthProfile='wx/auth/profile'; //账号修改
export function authProfile(data) {
  return request({
    url: AuthProfile,
    method: 'post',
    data
  })
}
const AuthRegister='wx/auth/register'; //账号注册
const AuthReset='wx/auth/reset'; //账号密码重置
export function authReset(data) {
  return request({
    url: AuthReset,
    method: 'post',
    data
  })
}
const AuthRegisterCaptcha='wx/auth/regCaptcha'; //注册验证码
export function authRegisterCaptcha(data) {
  return request({
    url: AuthRegisterCaptcha,
    method: 'post',
    data
  })
}
const AuthCaptcha='wx/auth/captcha'; //验证码
export function authCaptcha(data) {
  return request({
    url: AuthCaptcha,
    method: 'post',
    data
  })
}

const GoodsCount='wx/goods/count'; //统计商品总数
export function goodsCount() {
  return request({
    url: GoodsCount,
    method: 'get'
  })
}
export const GoodsList='wx/goods/list'; //获得商品列表
export function goodsList(query) {
  return request({
    url: GoodsList,
    method: 'get',
    params: query
  })
}
const GoodsCategory='wx/goods/category'; //获得分类数据
export function goodsCategory(query) {
  return request({
    url: GoodsCategory,
    method: 'get',
    params: query
  })
}
const GoodsDetail='wx/goods/detail'; //获得商品的详情
export function goodsDetail(query) {
  return request({
    url: GoodsDetail,
    method: 'get',
    params: query
  })
}
const GoodsRelated='wx/goods/related'; //商品详情页的关联商品（大家都在看）

const BrandList='wx/brand/list'; //品牌列表
export function brandList(query) {
  return request({
    url: BrandList,
    method: 'get',
    params: query
  })
}
const BrandDetail='wx/brand/detail'; //品牌详情
export function brandDetail(query) {
  return request({
    url: BrandDetail,
    method: 'get',
    params: query
  })
}

const CartList='wx/cart/index'; //获取购物车的数据
export function cartList(query) {
  return request({
    url: CartList,
    method: 'get',
    params: query
  })
}
const CartAdd='wx/cart/add'; // 添加商品到购物车
export function cartAdd(data) {
  return request({
    url: CartAdd,
    method: 'post',
    data
  })
}
const CartFastAdd='wx/cart/fastadd'; // 立即购买商品
export function cartFastAdd(data) {
  return request({
    url: CartFastAdd,
    method: 'post',
    data
  })
}
const CartFastAddActivity='wx/cart/fastaddactivity'; // 立即购买商品
export function cartFastAddActivity(data) {
    return request({
        url: CartFastAddActivity,
        method: 'post',
        data
    })
}
const CartUpdate='wx/cart/update'; // 更新购物车的商品
export function cartUpdate(data) {
  return request({
    url: CartUpdate,
    method: 'post',
    data
  })
}
const CartDelete='wx/cart/delete'; // 删除购物车的商品
export function cartDelete(data) {
  return request({
    url: CartDelete,
    method: 'post',
    data
  })
}
const CartChecked='wx/cart/checked'; // 选择或取消选择商品
export function cartChecked(data) {
  return request({
    url: CartChecked,
    method: 'post',
    data
  })
}
const CartGoodsCount='wx/cart/goodscount'; // 获取购物车商品件数
export function cartGoodsCount() {
  return request({
    url: CartGoodsCount,
    method: 'get'
  })
}
const CartCheckout='wx/cart/checkout'; // 下单前信息确认
export function cartCheckout(query) {
  return request({
    url: CartCheckout,
    method: 'get',
    params: query
  })
}

const CollectList='wx/collect/list'; //收藏列表
export function collectList(query) {
  return request({
    url: CollectList,
    method: 'get',
    params: query
  })
}

const CollectmeList='wx/collect/listme'; //收藏列表
export function collectmeList(query) {
    return request({
        url: CollectmeList,
        method: 'get',
        params: query
    })
}

const CollectAddOrDelete='wx/collect/addordelete'; //添加或取消收藏
export function collectAddOrDelete(data) {
  return request({
    url: CollectAddOrDelete,
    method: 'post',
    data
  })
}
const CommentList='wx/comment/list'; //评论列表
const CommentCount='wx/comment/count'; //评论总数
const CommentSave='wx/comment/save'; //发表评论
export function commentSave(data) {
    return request({
        url: CommentSave,
        method: 'post',
        data
    })
}

const CommentDelete='wx/comment/delete'; //发表评论
export function commentDelete(data) {
    return request({
        url: CommentDelete,
        method: 'post',
        data
    })
}

const CommentListall='wx/comment/listall'; //专题列表
export function commentListall(query) {
    return request({
        url: CommentListall,
        method: 'get',
        params: query
    })
}

const TopicList='wx/topic/list'; //专题列表
export function topicList(query) {
  return request({
    url: TopicList,
    method: 'get',
    params: query
  })
}
const TopicDetail='wx/topic/detail'; //专题详情
export function topicDetail(query) {
  return request({
    url: TopicDetail,
    method: 'get',
    params: query
  })
}
const TopicRelated='wx/topic/related'; //相关专题
export function topicRelated(query) {
  return request({
    url: TopicRelated,
    method: 'get',
    params: query
  })
}

const SearchIndex='wx/search/index'; //搜索关键字
const SearchResult='wx/search/result'; //搜索结果
const SearchHelper='wx/search/helper'; //搜索帮助
const SearchClearHistory='wx/search/clearhistory'; //搜索历史清楚

const AddressList='wx/address/list'; //收货地址列表
export function addressList(query) {
  return request({
    url: AddressList,
    method: 'get',
    params: query
  })
}

const AddressDetail='wx/address/detail'; //收货地址详情
export function addressDetail(query) {
  return request({
    url: AddressDetail,
    method: 'get',
    params: query
  })
}
const AddressSave='wx/address/save'; //保存收货地址
export function addressSave(data) {
  return request({
    url: AddressSave,
    method: 'post',
    data
  })
}
const AddressDelete='wx/address/delete'; //保存收货地址
export function addressDelete(data) {
  return request({
    url: AddressDelete,
    method: 'post',
    data
  })
}

const ExpressQuery='wx/express/query'; //物流查询

const OrderSubmit='wx/order/submit'; // 提交订单
export function orderSubmit(data) {
  return request({
    url: OrderSubmit,
    method: 'post',
    data
  })
}
const OrderPrepay='wx/order/prepay'; // 订单的预支付会话
export function orderPrepay(data) {
  return request({
    url: OrderPrepay,
    method: 'post',
    data
  })
}
export const OrderList='wx/order/list'; //订单列表
export function orderList(query) {
  return request({
    url: OrderList,
    method: 'get',
    params: query
  })
}
const OrderDetail='wx/order/detail'; //订单详情
export function orderDetail(query) {
  return request({
    url: OrderDetail,
    method: 'get',
    params: query
  })
}
const OrderCancel='wx/order/cancel'; //取消订单
export function orderCancel(data) {
  return request({
    url: OrderCancel,
    method: 'post',
    data
  })
}
const OrderRefund='wx/order/refund'; //退款取消订单
export function orderRefund(data) {
  return request({
    url: OrderRefund,
    method: 'post',
    data
  })
}
const OrderDelete='wx/order/delete'; //删除订单
export function orderDelete(data) {
  return request({
    url: OrderDelete,
    method: 'post',
    data
  })
}
const OrderConfirm='wx/order/confirm'; //确认收货
export function orderConfirm(data) {
  return request({
    url: OrderConfirm,
    method: 'post',
    data
  })
}
const OrderGoods='wx/order/goods'; // 代评价商品信息
const OrderComment='wx/order/comment'; // 评价订单商品信息

const FeedbackAdd='wx/feedback/submit'; //添加反馈
export function feedbackAdd(data) {
  return request({
    url: FeedbackAdd,
    method: 'post',
    data
  })
}

const FootprintList='wx/footprint/list'; //足迹列表
export function footprintList() {
    return request({
        url: FootprintList,
        method: 'get'
    })
}
const FootprintDelete='wx/footprint/delete'; //删除足迹
export function footprintDelete(data) {
    return request({
        url: FootprintDelete,
        method: 'post',
        data
    })
}

const UserFormIdCreate='wx/formid/create'; //用户FromId，用于发送模版消息

const GrouponList='wx/groupon/list'; //团购列表
export function grouponList(query) {
  return request({
    url: GrouponList,
    method: 'get',
    params: query
  })
}
const GroupOn='wx/groupon/query'; //团购API-查询
const GroupOnMy='wx/groupon/my'; //团购API-我的团购
const GroupOnDetail='wx/groupon/detail'; //团购API-详情
const GroupOnJoin='wx/groupon/join'; //团购API-详情

const CouponList='wx/coupon/list'; //优惠券列表
export function couponList(query) {
  return request({
    url: CouponList,
    method: 'get',
    params: query
  })
}
export const CouponMyList='wx/coupon/mylist'; //我的优惠券列表
export function couponMyList(query) {
  return request({
    url: CouponMyList,
    method: 'get',
    params: query
  })
}
const CouponSelectList='wx/coupon/selectlist'; //当前订单可用优惠券列表
export function couponSelectList(query) {
  return request({
    url: CouponSelectList,
    method: 'get',
    params: query
  })
}
const CouponReceive='wx/coupon/receive'; //优惠券领取
export function couponReceive(data) {
  return request({
    url: CouponReceive,
    method: 'post',
    data
  })
}
const CouponExchange='wx/coupon/exchange'; //优惠券兑换

const StorageUpload='wx/storage/upload'; //图片上传,
export function storageUpload(file) {
    var formdata = new FormData();// 创建form对象
    formdata.append('file', file , file.name);// 通过append向form对象添加数据,可以通过append继续添加数据
    return request({
        url: StorageUpload,
        method: 'post',
        data:formdata,
        // headers: {'Content-Type':'multipart/form-data;boundary=1111'}//不需要的参数
    })
}

const UserIndex='wx/user/index'; //个人页面用户相关信息
export function userIndex() {
  return request({
    url: UserIndex,
    method: 'get'
  })
}

const UserDetails='wx/user/details'; //个人页面用户相关信息
export function userDetails(query) {
    return request({
        url: UserDetails,
        method: 'get',
        params: query
    })
}

const UserDeleteItem='wx/user/deleteItem'; //个人页面用户相关信息
export function userDeleteItem(query) {
    return request({
        url: UserDeleteItem,
        method: 'get',
        params: query
    })
}

const UserCircleListByUser='wx/circle/listByUser'; //个人页面用户相关信息
export function userCircleListByUser(query) {
    return request({
        url: UserCircleListByUser,
        method: 'get',
        params: query
    })
}

const UserCreditListByUser='wx/credit/listByUser'; //个人页面用户相关信息
export function userCreditListByUser(query) {
    return request({
        url: UserCreditListByUser,
        method: 'get',
        params: query
    })
}

const UserScoreListByUser='wx/score/listByUser'; //个人页面用户相关信息
export function userScoreListByUser(query) {
    return request({
        url: UserScoreListByUser,
        method: 'get',
        params: query
    })
}

const UserFishpondsListByUser='wx/fishponds/listByUser'; //个人页面用户相关信息
export function userFishpondsListByUser(query) {
    return request({
        url: UserFishpondsListByUser,
        method: 'get',
        params: query
    })
}

const UserQuestionListByUser='wx/question/listByUser'; //个人页面用户相关信息
export function userQuestionListByUser(query) {
    return request({
        url: UserQuestionListByUser,
        method: 'get',
        params: query
    })
}

const UserActivityListByUser='wx/activity/listByUser'; //个人页面用户相关信息
export function userActivityListByUser(query) {
    return request({
        url: UserActivityListByUser,
        method: 'get',
        params: query
    })
}

const UserActivityListjoin = 'wx/activity/listjoin'; //个人页面用户相关信息
export function userActivityListjoin(query) {
    return request({
        url: UserActivityListjoin,
        method: 'get',
        params: query
    })
}

const ActivityListUserJoin = 'wx/activity/listuserjoin'; //个人页面用户相关信息
export function activityListUserJoin(query) {
    return request({
        url: ActivityListUserJoin,
        method: 'get',
        params: query
    })
}

const UserFishpondsListjoin = 'wx/fishponds/listjoin'; //个人页面用户相关信息
export function userFishpondsListjoin(query) {
    return request({
        url: UserFishpondsListjoin,
        method: 'get',
        params: query
    })
}

const UserGroupListjoin = 'wx/group/listjoin'; //个人页面用户相关信息
export function userGroupListjoin(query) {
    return request({
        url: UserGroupListjoin,
        method: 'get',
        params: query
    })
}

const UserGroupListByUser='wx/group/listByUser'; //个人页面用户相关信息
export function userGroupListByUser(query) {
    return request({
        url: UserGroupListByUser,
        method: 'get',
        params: query
    })
}

const UserRefresh='wx/user/refresh'; //个人页面用户相关信息
export function userRefresh(query) {
    return request({
        url: UserRefresh,
        method: 'get',
        params: query
    })
}

const IssueList='wx/issue/list'; //帮助信息
export function issueList() {
  return request({
    url: IssueList,
    method: 'get'
  })
}

export function getList(api, query) {
  return request({
    url: api,
    method: 'get',
    params: query
  })
}

const FishPondsList='wx/fishponds/list'; //鱼塘页取5个鱼塘
export function fishPondsList() {
    return request({
        url: FishPondsList,
        method: 'get'
    })
}

const FishPondsDetail='wx/fishponds/detail'; //鱼塘详细信息
export function fishPondsDetail(query) {
    return request({
        url: FishPondsDetail,
        method: 'get',
        params: query
    })
}

const FishpondsSave='wx/fishponds/save'; //保存
export function fishpondsSave(data) {
    return request({
        url: FishpondsSave,
        method: 'post',
        data
    })
}

const ScoreSave='wx/score/save'; //保存
export function scoreSave(data) {
    return request({
        url: ScoreSave,
        method: 'post',
        data
    })
}

const CreditSave='wx/credit/save'; //保存
export function creditSave(data) {
    return request({
        url: CreditSave,
        method: 'post',
        data
    })
}

const FishpondsDelete='wx/fishponds/delete'; //删除
export function fishpondsDelete(data) {
    return request({
        url: FishpondsDelete,
        method: 'post',
        data
    })
}

const QuestionList='wx/question/list'; //鱼塘页取5个鱼塘
export function questionList() {
    return request({
        url: QuestionList,
        method: 'get'
    })
}

const QuestionDetail='wx/question/detail'; //鱼塘详细信息
export function questionDetail(query) {
    return request({
        url: QuestionDetail,
        method: 'get',
        params: query
    })
}

const QuestionSave='wx/question/save'; //保存
export function questionSave(data) {
    return request({
        url: QuestionSave,
        method: 'post',
        data
    })
}

const QuestionDelete='wx/question/delete'; //删除
export function questionDelete(data) {
    return request({
        url: QuestionDelete,
        method: 'post',
        data
    })
}

const QuestionRefresh='wx/question/refresh'; //删除
export function questionRefresh(data) {
    return request({
        url: QuestionRefresh,
        method: 'post',
        data
    })
}

const GroupList='wx/group/list'; //鱼塘页取5个鱼塘
export function groupList() {
    return request({
        url: GroupList,
        method: 'get'
    })
}

const GroupDetail='wx/group/detail'; //鱼塘详细信息
export function groupDetail(query) {
    return request({
        url: GroupDetail,
        method: 'get',
        params: query
    })
}

const GroupSave='wx/group/save'; //保存
export function groupSave(data) {
    return request({
        url: GroupSave,
        method: 'post',
        data
    })
}

const GroupDelete='wx/group/delete'; //删除
export function groupDelete(data) {
    return request({
        url: GroupDelete,
        method: 'post',
        data
    })
}

const ActivityList='wx/activity/list'; //鱼塘页取5个鱼塘
export function activityList() {
    return request({
        url: ActivityList,
        method: 'get'
    })
}

const ActivityListall='wx/activity/listall'; //专题列表
export function activityListall(query) {
    return request({
        url: ActivityListall,
        method: 'get',
        params: query
    })
}

const QuestionListall='wx/question/listall'; //专题列表
export function questionListall(query) {
    return request({
        url: QuestionListall,
        method: 'get',
        params: query
    })
}

const CircleListall='wx/circle/listall'; //专题列表
export function circleListall(query) {
    return request({
        url: CircleListall,
        method: 'get',
        params: query
    })
}

const ActivityJoin='wx/activity/join'; //保存
export function activityJoin(data) {
    return request({
        url: ActivityJoin,
        method: 'post',
        data
    })
}

const ActivityJustice='wx/activity/justice'; //保存
export function activityJustice(data) {
    return request({
        url: ActivityJustice,
        method: 'post',
        data
    })
}

const ActivityQuit='wx/activity/quit'; //保存
export function activityQuit(data) {
    return request({
        url: ActivityQuit,
        method: 'post',
        data
    })
}

const GroupListall='wx/group/listall'; //专题列表
export function groupListall(query) {
    return request({
        url: GroupListall,
        method: 'get',
        params: query
    })
}

const GroupJoin='wx/group/join'; //保存
export function groupJoin(data) {
    return request({
        url: GroupJoin,
        method: 'post',
        data
    })
}

const GroupQuit='wx/group/quit'; //保存
export function groupQuit(data) {
    return request({
        url: GroupQuit,
        method: 'post',
        data
    })
}

const FishpondsListall='wx/fishponds/listall'; //专题列表
export function fishpondsListall(query) {
    return request({
        url: FishpondsListall,
        method: 'get',
        params: query
    })
}

const FishpondsJoin='wx/fishponds/join'; //保存
export function fishpondsJoin(data) {
    return request({
        url: FishpondsJoin,
        method: 'post',
        data
    })
}

const FishpondsQuit='wx/fishponds/quit'; //保存
export function fishpondsQuit(data) {
    return request({
        url: FishpondsQuit,
        method: 'post',
        data
    })
}

const ActivityDetail='wx/activity/detail'; //鱼塘详细信息
export function activityDetail(query) {
    return request({
        url: ActivityDetail,
        method: 'get',
        params: query
    })
}

const ActivitySave='wx/activity/save'; //保存
export function activitySave(data) {
    return request({
        url: ActivitySave,
        method: 'post',
        data
    })
}

const ActivityDelete='wx/activity/delete'; //删除
export function activityDelete(data) {
    return request({
        url: ActivityDelete,
        method: 'post',
        data
    })
}

const CircleList='wx/circle/list'; //鱼塘页取5个鱼塘
export function circleList() {
    return request({
        url: CircleList,
        method: 'get'
    })
}

const CircleDetail='wx/circle/detail'; //鱼塘详细信息
export function circleDetail(query) {
    return request({
        url: CircleDetail,
        method: 'get',
        params: query
    })
}

const CircleSave='wx/circle/save'; //保存
export function circleSave(data) {
    return request({
        url: CircleSave,
        method: 'post',
        data
    })
}

const CircleDelete='wx/circle/delete'; //删除
export function circleDelete(data) {
    return request({
        url: CircleDelete,
        method: 'post',
        data
    })
}

const CircleRefresh='wx/circle/refresh'; //删除
export function circleRefresh(data) {
    return request({
        url: CircleRefresh,
        method: 'post',
        data
    })
}

const TypeObjList='wx/typeobj/list'; //鱼塘页取5个鱼塘
export function typeObjList() {
    return request({
        url: TypeObjList,
        method: 'get'
    })
}
export const REFUND_LIST = '';