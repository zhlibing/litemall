import axios from 'axios'

export const HOST = 'https://www.easy-mock.com/mock/5b596a1c2d340a0cf1673504/fallowfish'

export const HOST_LOCAL = '/BBLServer/'

export const HOST_FILE_LOCAL = '/BBLFiLeServer/'

export const PERFIX_IMAGE = 'http://127.0.0.1:8080/BBLFiLeServer/upload/'

export const ERR_OK = 200;

export function getSearchresult() {
  const url = HOST + '/search/results'
  return axios.get(url)
}

export function getCategory() {
  const url = HOST + '/category'
  return axios.get(url)
}

export function getIndexbanner() {
  const url = HOST + '/indexbanner'
  return axios.get(url)
}

export function getIconlist() {
  const url = HOST + '/iconlist'
  return axios.get(url)
}

export function getTypelist() {
  const url = HOST + '/typelist'
  return axios.get(url)
}

export function getSmBanner() {
  const url = HOST + '/smallbanner'
  return axios.get(url)
}

export function getIndexNav() {
  const url = HOST + '/indexnav'
  return axios.get(url)
}

export function getBanner() {
  const url = HOST + '/banner'
  return axios.get(url)
}

export function getInterests() {
  const url = HOST + '/interests'
  return axios.get(url)
}

export function getQuestions() {
  const url = HOST + '/questions'
  return axios.get(url)
}

export function getTuhao() {
  const url = HOST + '/tuhao'
  return axios.get(url)
}

export function getChina() {
  const url = HOST + '/china'
  return axios.get(url)
}

export function getLists() {
  const url = HOST + '/lists'
  return axios.get(url)
}

export function doLogin(params, params1) {
  const url = HOST_LOCAL + 'login.do'
  //第一种写法
  console.log(params, 'params***')
  //第二种写法
  console.log(params1, 'params%%%%')
  return axios.post(url, params, {
    headers: {
      Authorization: 'Bearer 5llcq3GiwABUg-Fxs...',
      Accept: 'application/json',
      dataType: 'JSONP',//重点在这里，加上这个属性就可以跨域请求了
    }
  })
}

export function doRegist(params) {
  const url = HOST_LOCAL + 'regsiter.do'
  return axios.post(url, params, {
    headers: {
      Authorization: 'Bearer 5llcq3GiwABUg-Fxs...',
      Accept: 'application/json',
      dataType: 'JSONP',
    }
  })
}

export function doFindcheckphoto(params) {
  const url = HOST_LOCAL + 'findcheckphoto.do'
  return axios.post(url, params, {
    headers: {
      Authorization: 'Bearer 5llcq3GiwABUg-Fxs...',
      Accept: 'application/json',
      dataType: 'JSONP',
    }
  })
}

export function doFindcomplanitpagelist(params) {
  const url = HOST_LOCAL + 'findcomplanitpagelist.do'
  return axios.post(url, params, {
    headers: {
      Authorization: 'Bearer 5llcq3GiwABUg-Fxs...',
      Accept: 'application/json',
      dataType: 'JSONP',
    }
  })
}

export function doFindhavealook(params) {
  const url = HOST_LOCAL + 'findhavealook.do'
  return axios.post(url, params, {
    headers: {
      Authorization: 'Bearer 5llcq3GiwABUg-Fxs...',
      Accept: 'application/json',
      dataType: 'JSONP',
    }
  })
}

// map.put("username", dao.queryUserByNewTime().getUsername());
// map.put("pagenumber", String.valueOf(PageNumber));
export function doFindvisiter(params) {
  const url = HOST_LOCAL + 'findvisiter.do'
  return axios.post(url, params, {
    headers: {
      Authorization: 'Bearer 5llcq3GiwABUg-Fxs...',
      Accept: 'application/json',
      dataType: 'JSONP',
    }
  })
}

// map.put("username", dao.queryUserByNewTime().getUsername());
// map.put("touser", toUser);
export function doUpdatevisiter(params) {
  const url = HOST_LOCAL + 'updatevisiter.do'
  return axios.post(url, params, {
    headers: {
      Authorization: 'Bearer 5llcq3GiwABUg-Fxs...',
      Accept: 'application/json',
      dataType: 'JSONP',
    }
  })
}

//上传图片保存路径
export function doUpheadimage(params) {
  const url = HOST_LOCAL + 'upheadimage.do'
  return axios.post(url, params, {
    headers: {
      Authorization: 'Bearer 5llcq3GiwABUg-Fxs...',
      Accept: 'application/json',
      dataType: 'JSONP',
    }
  })
}

//上传图片
export function doUpload(params, file) {
  const url = HOST_FILE_LOCAL + 'fileupload.do'
  var formdata = new FormData();// 创建form对象
  formdata.append('img', file, params.name);// 通过append向form对象添加数据,可以通过append继续添加数据
  console.log(params.name, '>>>name')
  let config = {
    headers: {'Content-Type': 'multipart/form-data'}
  };
  return axios.post(url, formdata, config)
}

export const news = [
  {
    "id": "0",
    "avatar": "./static/1.png",
    "name": "天涯海角",
    "time": "12分钟前来过",
    "price": "￥125.10",
    "img": ["./static/01.png"],
    "desc": "小番茄定制2017新款显瘦竖条纹半生裙高腰纽扣开叉雪纺中长款半裙",
    "add": "来自郑州",
    "kind": "鱼塘|网红衣服鱼塘",
    "star": "点赞",
    "num": "15",
    "freight": "0"
  }, {
    "id": "1",
    "avatar": "./static/1.png",
    "name": "天涯海角",
    "time": "12分钟前来过",
    "price": "￥125.10",
    "img": ["./static/01.png", "./static/01.png"],
    "desc": "小番茄定制2017新款显瘦竖条纹半生裙高腰纽扣开叉雪纺中长款半裙",
    "add": "来自郑州",
    "kind": "鱼塘|网红衣服鱼塘",
    "star": "点赞",
    "num": "15",
    "freight": "0"
  }, {
    "id": "2",
    "avatar": "./static/2.png",
    "name": "糖果xt90",
    "time": "1小时前来过",
    "price": "￥80",
    "img": ["./static/02.png", "./static/02.png", "./static/02.png", "./static/02.png", "./static/02.png", "./static/02.png", "./static/02.png"],
    "desc": "买一送一：欧洲力淑女包，感兴趣的给我留言哟",
    "add": "来自北京",
    "kind": "鱼塘|给闲置的衣服寻找有缘",
    "freight": "10"
  }, {
    "id": "3",
    "avatar": "./static/2.png",
    "name": "糖果xt90",
    "time": "1小时前来过",
    "price": "￥80",
    "img": ["./static/02.png", "./static/02.png", "./static/02.png", "./static/02.png", "./static/02.png", "./static/02.png", "./static/02.png", "./static/02.png"],
    "desc": "买一送一：欧洲力淑女包，感兴趣的给我留言哟",
    "add": "来自北京",
    "kind": "鱼塘|给闲置的衣服寻找有缘",
    "freight": "10"
  }]

export const iconlist = [
  {
    "id": "1",
    "src": "../../static/phone.png",
    "title": "闲鱼精选",
    "desc": "这里有好东西"
  },
  {
    "id": "2",
    "src": "../../static/jingang.png",
    "title": "拍卖",
    "desc": "变形金刚5"
  },
  {
    "id": "3",
    "src": "../../static/car.png",
    "title": "同城",
    "desc": "选闲置更靠谱"
  },
  {
    "id": "4",
    "src": "../../static/house.png",
    "title": "我要租房",
    "desc": "三分钟租到房"
  }
]

export const typelist = [
  {
    "id": "1",
    "title": "奢侈品",
    "desc": "1890个新宝贝入塘",
    "src": "./static/shechi.jpg"
  },
  {
    "id": "2",
    "title": "法院卖货",
    "desc": "手机数码真心便宜",
    "src": "./static/car.jpg"
  },
  {
    "id": "3",
    "title": "奢电脑发烧友",
    "desc": "262个新宝贝入塘",
    "src": "./static/computer.jpg"
  },
  {
    "id": "4",
    "title": "距开始9小时",
    "desc": "蔡氏古琴制作技艺",
    "src": "./static/guqin.jpg"
  },
  {
    "id": "5",
    "title": "[技能]会摄影",
    "desc": "爱宠的写真，约吗",
    "src": "./static/pet.jpg"
  },
  {
    "id": "6",
    "title": "实拍穿搭",
    "desc": "今日实拍已上新",
    "src": "./static/cloth.jpg"
  }
]

export const smallbanner = [
  {
    "id": "1",
    "src": "./static/ad1.webp"
  },
  {
    "id": "2",
    "src": "./static/ad2.png"
  }
]

export const indexbanner = [
  {
    "id": "1",
    "src": "./static/banner1.jpg"
  },
  {
    "id": "2",
    "src": "./static/banner2.jpg"
  },
  {
    "id": "3",
    "src": "./static/banner3.jpg"
  },
  {
    "id": "4",
    "src": "./static/banner4.jpg"
  }
]

export const recoms = [
  {
    "title": "长江学院鱼塘",
    "members": 393,
    "num": 16,
    "produ": "想要宝贝置顶的可以私聊我",
    "destance": 291,
    "images": [
      {
        "img": "./static/img1.png"
      },
      {
        "img": "./static/img2.png"
      },
      {
        "img": "./static/img3.png"
      }
    ]
  },
  {
    "title": "华瑞锦城鱼塘",
    "members": 60,
    "num": 3,
    "produ": "[专治各种疑问]鱼塘是什么？",
    "destance": 319,
    "images": [
      {
        "img": "./static/img4.png"
      },
      {
        "img": "./static/img5.png"
      },
      {
        "img": "./static/img6.png"
      }
    ]
  },
  {
    "title": "范家新村鱼塘",
    "members": 91,
    "num": 6,
    "produ": "想要宝贝置顶的可以私聊我",
    "destance": 418,
    "images": [
      {
        "img": "./static/img7.png"
      },
      {
        "img": "./static/img8.png"
      },
      {
        "img": "./static/img9.png"
      }
    ]
  }
]

export const nears = [
  {
    "avatar": "./static/3.png",
    "name": "流浪在天际",
    "time": "7小时前来过",
    "price": "￥100",
    "img": "./static/03.png",
    "desc": "席梦思床垫1.8米x2米 95新，换了乳胶，上门自提",
    "add": "来自南昌",
    "kind": "鱼塘|塘山镇",
    "star": "点赞",
    "num": "2"
  },
  {
    "avatar": "./static/4.png",
    "name": "万莉佳",
    "time": "8小时前来过",
    "price": "￥80",
    "img": "./static/04.png",
    "desc": "绑带细跟真皮凉鞋，清鞋柜，300多买来的，穿过两次，9.5新，34码，鞋跟10厘米左右，鞋子多，便宜处理",
    "add": "来自南昌",
    "kind": "鱼塘|众鑫城上城"
  }
]

export const interests = [
  {
    "id": "1",
    "img": "./static/oushili.png",
    "title": "欧时力欧",
    "desc": "正在举办塘主安利活动"
  },
  {
    "id": "2",
    "img": "./static/jk.png",
    "title": "JK红领巾广场",
    "desc": "天啊~这鱼塘怎么火爆"
  },
  {
    "id": "3",
    "img": "./static/dog.png",
    "title": "爱狗狗",
    "desc": "塘主推送了好东西"
  },
  {
    "id": "4",
    "img": "./static/hanfu.png",
    "title": "汉服",
    "desc": "塘主推送了好多美腻的汉服"
  }
]

export const questions = [
  {
    "id": "1",
    "title": "杭州有什么好玩的？求介绍",
    "img": "./static/hangzhou.png",
    "answer": "现在不适合去，要秋天或夏天去，冬天，夏天太晒了！因为主要是赏湖，好大太阳",
    "yutang": "青年路社区鱼塘"
  },
  {
    "id": "2",
    "title": "这个是什么品种，会分支出去吗？",
    "img": "./static/duorou.png",
    "answer": "多肉植物",
    "yutang": "多肉花盆鱼塘"
  },
  {
    "id": "3",
    "title": "鱼塘是什么",
    "answer": "其实塘主养的就是那些鱼，人鱼",
    "yutang": "中塘镇鱼塘"
  }
]

export const tuhao = [
  {
    "id": "1",
    "img": "./static/sky.png",
    "title": "METALBUILD",
    "desc": "主打metalbuild系列，当然其他系列也有哟"
  },
  {
    "id": "2",
    "img": "./static/wawa.png",
    "title": "珍妮娃娃",
    "desc": "天啊，这鱼塘什么也没有介绍····"
  },
  {
    "id": "3",
    "img": "./static/hangpai.png",
    "title": "多轴航拍FPV",
    "desc": "多轴航拍FPV鱼塘是各类航模的聚集地"
  },
  {
    "id": "4",
    "title": "更多",
    "desc": "玩具收藏"
  }
]

export const chinas = [
  {
    "id": "1",
    "img": "./static/gongfu.png",
    "title": "武术",
    "desc": "天啊~这鱼塘什么介绍都没有"
  },
  {
    "id": "2",
    "img": "./static/guqin.png",
    "title": "缦学古琴",
    "desc": "缦学堂，一个互联网古琴学堂"
  },
  {
    "id": "3",
    "img": "./static/zihua.png",
    "title": "子画收藏",
    "desc": "天啊~这个鱼塘什么介绍都没介绍"
  },
  {
    "id": "4",
    "img": "./static/minzu.png",
    "title": "民族风穿搭girl",
    "desc": "时尚、趣味、精巧、别致--民族style"
  },
  {
    "id": "5",
    "title": "更多",
    "desc": "最炫国风"
  }
]

export const discusslists = [
  {
    "id": "1",
    "img": "./static/fugu.png",
    "level": "LV.8",
    "title": "复古胶片机",
    "desc": "给胶片爱好者一片自留地，以摄会友，欢迎大家来逛逛！！！",
    "renqi": 454659,
    "fabu": 3035
  },
  {
    "id": "2",
    "img": "./static/cat.png",
    "level": "LV.7",
    "title": "咪咪哒の幸福喵生",
    "desc": "塘主推送了八字脸大眼睛超呆萌 小奶猫 免费领养 仅限上海，快去领取",
    "renqi": 535994,
    "fabu": 4134
  },
  {
    "id": "3",
    "img": "./static/shoes.png",
    "level": "LV.30",
    "title": "公主鞋",
    "desc": "塘主推送了迪奥Dior新款单鞋凉鞋字母鞋34-40，快去看看吧",
    "renqi": 673208,
    "fabu": 23607
  },
  {
    "id": "4",
    "img": "./static/zara.png",
    "level": "LV.11",
    "title": "ZARA 潮流前线",
    "desc": "塘主送了#其他品牌#男朋友风 破洞牛仔九分裤",
    "renqi": 1967730,
    "fabu": 55948
  },
  {
    "id": "5",
    "img": "./static/cat.png",
    "level": "LV.7",
    "title": "咪咪哒の幸福喵生",
    "desc": "塘主推送了八字脸大眼睛超呆萌 小奶猫 免费领养 仅限上海，快去领取",
    "renqi": 535994,
    "fabu": 4134
  },
  {
    "id": "6",
    "img": "./static/shoes.png",
    "level": "LV.30",
    "title": "公主鞋",
    "desc": "塘主推送了迪奥Dior新款单鞋凉鞋字母鞋34-40，快去看看吧",
    "renqi": 673208,
    "fabu": 23607
  },
  {
    "id": "7",
    "img": "./static/zara.png",
    "level": "LV.11",
    "title": "ZARA 潮流前线",
    "desc": "塘主送了#其他品牌#男朋友风 破洞牛仔九分裤",
    "renqi": 1967730,
    "fabu": 55948
  }
]

export const yutangbanner = [
  {
    "id": "1",
    "src": "./static/banner1.jpg"
  },
  {
    "id": "2",
    "src": "./static/ad1.webp"
  },
  {
    "id": "3",
    "src": "./static/ad2.png"
  }
]

export const friendCircel = [
  {
    "commit": [
      {
        "commit": [],
        "content": "你家的",
        "headimg": "",
        "id": 68,
        "nickname": "你真逗丶",
        "photo": "",
        "replayuser": "",
        "sex": "",
        "time": "2017-02-19 18:06:29",
        "type": "",
        "username": "asdfg",
        "zan": []
      },
      {
        "commit": [],
        "content": "哈哈哈",
        "headimg": "",
        "id": 70,
        "nickname": "你真逗丶",
        "photo": "",
        "replayuser": "",
        "sex": "",
        "time": "2017-02-19 19:46:53",
        "type": "",
        "username": "asdfg",
        "zan": []
      }
    ],
    "content": "随缘。。。",
    "headimg": "2017tiaozhan201702171754275427.jpg",
    "id": 156,
    "nickname": "锅盖头",
    "photo": ["2017tiaozhan2017021717545154518074380.jpg"],
    "replayuser": "",
    "sex": "女",
    "time": "2017-02-17 17:54:52",
    "type": "1",
    "username": "2017tiaozhan",
    "zan": [
      {
        "commit": [],
        "content": "",
        "headimg": "",
        "id": 96,
        "nickname": "你真逗丶",
        "photo": "",
        "replayuser": "",
        "sex": "",
        "time": "2017-02-19 18:06:21",
        "type": "",
        "username": "asdfg",
        "zan": []
      },
      {
        "commit": [],
        "content": "",
        "headimg": "",
        "id": 95,
        "nickname": "锅盖头",
        "photo": "",
        "replayuser": "",
        "sex": "",
        "time": "2017-02-17 17:56:47",
        "type": "",
        "username": "2017tiaozhan",
        "zan": []
      }
    ]
  },
  {
    "commit": [],
    "content": "将来和你一起到这里看看风景！",
    "headimg": "xiaowang201907191230213021.jpg",
    "id": 155,
    "nickname": "思君如痴",
    "photo": ["xiaowang201702070927062763595510.jpg", "xiaowang201702070927062765254771.jpg", "xiaowang201702070927062769747742.jpg", "xiaowang201702070927062760326443.jpg", "xiaowang201702070927062765369204.jpg", "xiaowang201702070927062765699945.jpg", "xiaowang201702070927062769471056.jpg", "xiaowang201702070927062768518887.jpg", "xiaowang201702070927062766514308.jpg"],
    "replayuser": "",
    "sex": "男",
    "time": "2017-02-07 09:27:08",
    "type": "1",
    "username": "xiaowang",
    "zan": [
      {
        "commit": [],
        "content": "",
        "headimg": "",
        "id": 92,
        "nickname": "思君如痴",
        "photo": "",
        "replayuser": "",
        "sex": "",
        "time": "2017-02-07 09:28:45",
        "type": "",
        "username": "ysdn",
        "zan": []
      }
    ]
  },
  {
    "commit": [
      {
        "commit": [],
        "content": "会的！",
        "headimg": "",
        "id": 66,
        "nickname": "思君如痴",
        "photo": "",
        "replayuser": "",
        "sex": "",
        "time": "2017-02-07 09:22:40",
        "type": "",
        "username": "xiaowang",
        "zan": []
      }
    ],
    "content": "好想遇到心目中的他，你在这里吗？",
    "headimg": "35745804652747820170207003307337.jpg",
    "id": 154,
    "nickname": "情定三生",
    "photo": ["357458046527478201702070053075376851040.jpg", "357458046527478201702070053075370001511.jpg", "357458046527478201702070053075374604622.jpg", "357458046527478201702070053075372139983.jpg", "357458046527478201702070053075373745044.jpg", "357458046527478201702070053075376130915.jpg", "357458046527478201702070053075371255236.jpg", "357458046527478201702070053075372139367.jpg", "357458046527478201702070053075377758048.jpg"],
    "replayuser": "",
    "sex": "女",
    "time": "2017-02-07 00:53:10",
    "type": "1",
    "username": "357458046527478",
    "zan": [
      {
        "commit": [],
        "content": "",
        "headimg": "",
        "id": 93,
        "nickname": "思君如痴",
        "photo": "",
        "replayuser": "",
        "sex": "",
        "time": "2017-02-07 09:28:51",
        "type": "",
        "username": "ysdn",
        "zan": []
      },
      {
        "commit": [],
        "content": "",
        "headimg": "",
        "id": 90,
        "nickname": "思君如痴",
        "photo": "",
        "replayuser": "",
        "sex": "",
        "time": "2017-02-07 09:22:20",
        "type": "",
        "username": "xiaowang",
        "zan": []
      }
    ]
  }
]

// var str = "["xiaowang201702070927062763595510.jpg", "xiaowang201702070927062765254771.jpg", "xiaowang201702070927062769747742.jpg", "xiaowang201702070927062760326443.jpg", "xiaowang201702070927062765369204.jpg", "xiaowang201702070927062765699945.jpg", "xiaowang201702070927062769471056.jpg", "xiaowang201702070927062768518887.jpg", "xiaowang201702070927062766514308.jpg"]"
// export const demo=JSON.parse(str)

export const moviedetail = {
  "image": "http://img5.mtime.cn/mt/2019/07/17/173708.85525823_1280X720X2.jpg",
  "titleCn": "哪吒之魔童降世",
  "titleEn": "Ne Zha",
  "rating": "8.5",
  "scoreCount": "361",
  "year": "2019",
  "content": "天地灵气孕育出一颗能量巨大的混元珠，元始天尊将混元珠提炼成灵珠和魔丸，灵珠投胎为人，助周伐纣时可堪大用；而魔丸则会诞出魔王，为祸人间。元始天尊启动了天劫咒语，3年后天雷将会降临，摧毁魔丸。太乙受命将灵珠托生于陈塘关李靖家的儿子哪吒身上。然而阴差阳错，灵珠和魔丸竟然被掉包。本应是灵珠英雄的哪吒却成了混世大魔王。调皮捣蛋顽劣不堪的哪吒却徒有一颗做英雄的心。然而面对众人对魔丸的误解和即将来临的天雷的降临，哪吒是否命中注定会立地成魔？他将何去何从？",
  "type": [
    "动画"
  ],
  "runTime": "110分钟",
  "url": "http://movie.mtime.com/251525/",
  "wapUrl": "http://movie.wap.mtime.com/251525/",
  "isEggHunt": true,
  "commonSpecial": "哪吒闹海惹上龙宫三太子",
  "hotRanking": 1,
  "weekBoxOffice": "",
  "totalBoxOffice": "",
  "weekBoxOfficeUnit": "",
  "totalBoxOfficeUnit": "",
  "director": {
    "directorId": 1720746,
    "directorName": "饺子",
    "directorNameEn": "Jiaozi",
    "directorImg": "http://img5.mtime.cn/ph/2019/07/24/104447.22727406_1280X720X2.jpg"
  },
  "actorList": [
    {
      "actorId": 2381311,
      "actor": "吕艳婷",
      "actorEn": "Yanting Lv",
      "actorImg": "http://img5.mtime.cn/ph/2019/07/16/164144.93834954_1280X720X2.jpg",
      "roleName": "儿童哪吒",
      "roleImg": ""
    },
    {
      "actorId": 2342902,
      "actor": "囧森瑟夫",
      "actorEn": "",
      "actorImg": "http://img5.mtime.cn/ph/2019/07/23/114848.91830225_1280X720X2.jpg",
      "roleName": "少年哪吒",
      "roleImg": ""
    }
  ],
  "isTicket": true,
  "showCinemaCount": 109,
  "showtimeCount": 1335,
  "showDay": 1564128000,
  "style": {
    "ifLeadPage": 0,
    "leadUrl": "",
    "leadImag": "http://img2.mtime.cn/mg/.jpg"
  },
  "is3D": true,
  "isIMAX": false,
  "isIMAX3D": true,
  "isDMAX": true,
  "festivals": [],
  "awards": [],
  "totalWinAward": 0,
  "totalNominateAward": 0,
  "directors": [
    "饺子"
  ],
  "actors": [
    "吕艳婷",
    "囧森瑟夫"
  ],
  "release": {
    "location": "中国",
    "date": "2019-7-26"
  },
  "imageCount": 41,
  "images": [
    "http://img5.mtime.cn/pi/2019/01/17/113939.99601440_1280X720X2.jpg",
    "http://img5.mtime.cn/pi/2019/01/17/113946.67427273_1280X720X2.jpg",
    "http://img5.mtime.cn/pi/2019/01/17/113955.37404041_1280X720X2.jpg",
    "http://img5.mtime.cn/pi/2019/01/17/114001.40595012_1280X720X2.jpg"
  ],
  "video": "http://vfx.mtime.cn/Video/2019/05/30/mp4/190530103807409887.mp4",
  "videoId": 74938,
  "videoCount": 3,
  "videos": [
    {
      "url": "http://vfx.mtime.cn/Video/2019/05/30/mp4/190530103807409887.mp4",
      "image": "http://img5.mtime.cn/mg/2019/07/10/211455.51589593.jpg",
      "length": 73,
      "title": "哪吒之魔童降世 定档预告",
      "videoId": 74938
    },
    {
      "url": "http://vfx.mtime.cn/Video/2019/01/17/mp4/190117093552073173.mp4",
      "image": "http://img5.mtime.cn/mg/2019/01/16/221103.40530963.jpg",
      "length": 30,
      "title": "哪吒之魔童降世 先导预告片",
      "videoId": 73442
    },
    {
      "url": "http://vfx.mtime.cn/Video/2019/07/23/mp4/190723112556037086.mp4",
      "image": "http://img5.mtime.cn/mg/2019/07/23/112542.36553347.jpg",
      "length": 69,
      "title": "哪吒之魔童降世 哪吒大圣联动短片",
      "videoId": 75608
    }
  ],
  "personCount": 8
}

export const hotcomment = {
  "comments": [
    {
      "commentCount": 6,
      "content": "激动~赞爆~了不起！昨天下午，《哪吒之魔童降世》的点映活动，我又见证了国漫的一座新高峰勇猛崛起！为何以“勇猛崛起”来预测其不久的将来呢？我想，主要是因为《哪吒之魔童降世》的主题具有斗争性，内容具有叛逆性，形式具有革新性，出品应有较高的难度。可是，斗天斗地斗命运并不算最大的本事，燃烧和炸裂的背后还藏有许多巧妙的、柔软的心思，既有火的意志，也有水的精气，这才是重点所在！迈入新...",
      "headurl": "http://img32.mtime.cn/up/2013/06/25/110141.60460178_128X128.jpg",
      "id": 8193982,
      "isWantSee": false,
      "location": "",
      "modifyTime": 1563037200,
      "nickname": "火天骑士",
      "rating": 8.8,
      "title": "这个哪吒非一般！"
    },
    {
      "commentCount": 11,
      "content": "2015年，《大圣归来》横空出世。直到四年后的夏天，真正能够接过“大圣”衣钵的继承者才隐隐露出头角——他项戴乾坤圈，身披混天绫，手持火尖枪，脚踩风火轮而来。《哪吒之魔童降世》，听起来像部毫不走心极易扑街的暑期档圈钱动画。此前的预告片剪得也非常粗放，甚至连剧照都找不到两张。但一如当年悄无声息又大爆的“大圣”，“哪吒”极可能在观众毫无预期的情况下，突然奉上惊喜。从成品质量上，从情感上，从故事完整...",
      "headurl":
        "http://img32.mtime.cn/up/2013/08/13/094117.18753126_128X128.jpg",
      "id":
        8194380,
      "isWantSee":
        false,
      "location":
        "",
      "modifyTime":
        1563544080,
      "nickname":
        "evenc伊文西",
      "rating":
        7.2,
      "title":
        "四年后，“大圣”的继承者终于出现了"
    },
    {
      "commentCount":
        2,
      "content":
        "“二次元”一个喜欢的人称之为精神食粮，不喜欢的人则怒斥中二病的异次元空间。孩提时，谁不曾饮过二次元的“圣水”；长大后，还有多少人依旧甘之如饴呢？曾几何时，自己奉二次元为自己毕生的致爱，并打算全身心投入其中。也曾经像个愤青，对当下的国漫有恨铁不成钢态度，哀其不幸怒其不争。时过境迁，如今的自己早已很少提笔画画，但依旧保有一颗为“二次元”跳动的心。2015年的“大圣”，2016年的...",
      "headurl":
        "http://img22.mtime.cn/up/2010/06/27/212804.84469759_128X128.jpg",
      "id":
        8194236,
      "isWantSee":
        false,
      "location":
        "",
      "modifyTime":
        1563376020,
      "nickname":
        "KooRi",
      "rating":
        7.1,
      "title":
        "吒儿不渣（轻微剧透）"
    }
    ,
    {
      "commentCount":
        1,
      "content":
        "首先特别感谢@极客观影团 #极客观影团#，在这段影片乏味的期待，带我们能够提前观看#电影哪吒之魔童降世#。在这个暑期，已经有太多的电影因为各种各样、不明不白的原因撤档或者不知所踪，一直也不想讨论这个问题，只是因为作为一个普通的小老百姓，实在不知道真相，也不可能知道真相，当然沉默也不是没有观点，毕竟每个人的表达方式都不同。现在想知道，前段时间有些朋友表明...",
      "headurl":
        "http://img32.mtime.cn/up/2016/06/26/161504.15839163_128X128.jpg",
      "id":
        8194019,
      "isWantSee":
        false,
      "location":
        "",
      "modifyTime":
        1563107275,
      "nickname":
        "天行者aiken",
      "rating":
        9,
      "title":
        "还好，我们还有哪吒～"
    }
    ,
    {
      "commentCount":
        0,
      "content":
        "看到网上的评分这么高，去看之前还在努力调整心态，担心期待越高失望越大，结果…发现我想多了！！赤裸裸的暑期爆款，强烈安利推荐！男女老幼大人小孩不容错过之国产文化精品！本作的画面和视觉效果从一开篇就让我眼前一亮，而且直到影片结束都没有暗下来…波澜壮阔的世界观感、恢弘大气的战斗场面、生动细致的人物形象都让我为其叫好！除了画面外，本作的内容也是相当出...",
      "headurl":
        "http://img32.mtime.cn/up/2015/05/11/023322.76609986_128X128.jpg",
      "id":
        8194637,
      "isWantSee":
        false,
      "location":
        "",
      "modifyTime":
        1563894798,
      "nickname":
        "洋洋洒洒览天下",
      "rating":
        9.2,
      "title":
        "眼前一亮！就再也没暗了"
    }
    ,
    {
      "commentCount":
        0,
      "content":
        "文/梦里诗书基于哪吒传说的颠覆式改编，《哪吒之魔童降世》充沛的剧情构思下，电影有着打动人心的羁绊，有着催人泪目的亲情，有着关乎自我认知的觉醒，更还有着哪吒“我命由我不由天”热血的反叛意识，这部足以让人为之瞠目的国产动画，令人见证的不再仅是国漫的崛起，而是国漫拥有了真正能与好莱坞动画佳作相媲美的灵魂。1979年，大胆打破原著桎梏的《哪吒闹海》，在画风与音乐的完美结合中上演了一场饱含反叛意识，挑...",
      "headurl":
        "http://img32.mtime.cn/up/2014/10/27/120847.82885479_128X128.jpg",
      "id":
        8194575,
      "isWantSee":
        false,
      "location":
        "",
      "modifyTime":
        1563843960,
      "nickname":
        "梦里诗书",
      "rating":
        9,
      "title":
        "他是哪吒 他不只是哪吒"
    }
    ,
    {
      "commentCount":
        0,
      "content":
        "经过一轮点映后，国产动画片《哪吒之魔童降世》收获了来自第一批观众的好评，眼下的豆瓣评分是8.8分。同样，在我心里，它是一部可以和几年前的动画片《大圣归来》相媲美的佳作，一样的燃爆感人，一样的故事坚实，一样的视效惊人，一样的好笑动人。好作品从来都是稀少的。好的国产动画片似乎更为难得。除了硬件上的技术落后于美国和日本这个客观原因以外，我们的动画片在艺术软实力方面如主题立意、剧作结构、人物叙事等同...",
      "headurl":
        "http://img5.mtime.cn/up/2017/04/26/101446.47135076_128X128.jpg",
      "id":
        8194573,
      "isWantSee":
        false,
      "location":
        "",
      "modifyTime":
        1563840300,
      "nickname":
        "柯瓦斯基",
      "rating":
        0,
      "title":
        "《哪吒之魔童降世》：用爱感化魔性"
    }
    ,
    {
      "commentCount":
        1,
      "content":
        "预告片放出时被群嘲，指其“毁经典要向全国人民谢罪”。点映后却好评如潮，被无数人“墙裂”推荐。如今还没正式上映，仅点映票房已近6000万。这部影片就是饺子导演的《哪吒之魔童降世》。5年前的《大圣归来》给长年疲软的国漫市场，注入了一股强劲的活力。凭着“自来水”口口相传，原本籍籍无名的《大圣归来》口碑、票房一路攀高，最后收获了956亿票房。5年后，同样由“十月文化”出品的《哪吒之魔童降世》，还未..",
      "headurl":
        "http://img5.mtime.cn/up/2018/10/18/163042.80633153_128X128.jpg",
      "id":
        8194534,
      "isWantSee":
        false,
      "location":
        "",
      "modifyTime":
        1563778260,
      "nickname":
        "山鬼光影",
      "rating":
        8,
      "title":
        "燃爆了！最帅哪吒，没有之一！"
    }
  ],
  "totalCount":
    8
}

export const comment = {
  "cts": [
    {
      "tweetId": 47983128,
      "ce": "其实有很多老的连环画作品是原创的。解放前中国有一部超级英雄类的作品，叫《七星子》，很明显受到了欧美的蝙蝠侠，或是佐罗的影响，他劫富济贫，以暴制暴，既有老派武侠情怀，也有现代的漫画风格。值得挖掘的东西太多了，只怕很多人眼光有限。",
      "ceimg": "",
      "ca": "younghappy",
      "cd": 1563950728000,
      "lcd": 1563979528,
      "caimg": "http://img2.mtime.cn/u/190/228190/ddd173d3-be4c-49c2-bb4b-2658e60bd95d/48X48.jpg",
      "cal": "北京",
      "commentCount": 0,
      "cr": -1
    },
    {
      "tweetId": 47983082,
      "ce": "这次的音乐确实很不错，人物动作很流畅，看起来很爽快，造型也比较深刻，看完很过瘾，推荐",
      "ceimg": "",
      "ca": "橙酒Modern",
      "cd": 1563949902000,
      "lcd": 1563978702,
      "caimg": "http://img32.mtime.cn/up/2013/02/05/214838.23681091_48X48.jpg",
      "cal": "大连",
      "commentCount": 0,
      "cr": 8.7
    },
    {
      "tweetId": 47983020,
      "ce": "对哪吒故事的重新演绎还是站得住脚的，能看到很多星爷电影的影子，比如大话西游、长江七号",
      "ceimg": "",
      "ca": "燕雪宜",
      "cd": 1563948894000,
      "lcd": 1563977694,
      "caimg": "http://img2.mtime.cn/u/1771/1873771/99f8b7e5-92a4-4f11-b2a1-cdc76f0ea75b/48X48.jpg",
      "cal": "北京",
      "commentCount": 1,
      "cr": 8.7
    },
    {
      "tweetId": 47982893,
      "ce": "怎么豆瓣评分比时光网还高",
      "ceimg": "",
      "ca": "xiao-罗",
      "cd": 1563945216000,
      "lcd": 1563974016,
      "caimg": "http://img22.mtime.cn/up/2012/02/01/225159.46331622_48X48.jpg",
      "cal": "佩思",
      "commentCount": 0,
      "cr": -1
    },
    {
      "tweetId": 47982456,
      "ce": "期待已久果然炸裂！",
      "ceimg": "",
      "ca": "为了忘却的纪念",
      "cd": 1563936329000,
      "lcd": 1563965129,
      "caimg": "http://img32.mtime.cn/up/2012/12/16/184604.74893611_48X48.jpg",
      "cal": "北京",
      "commentCount": 0,
      "cr": 9
    },
    {
      "tweetId": 47982266,
      "ce": "真.国漫崛起系列",
      "ceimg": "",
      "ca": "断歌",
      "cd": 1563934120000,
      "lcd": 1563962920,
      "caimg": "http://img32.mtime.cn/up/2014/05/23/220108.88468857_48X48.jpg",
      "cal": "泉州",
      "commentCount": 0,
      "cr": 9
    },
    {
      "tweetId": 47980665,
      "ce": "出乎意料的精彩，同一个故事不同的演绎，但又不失记忆中的神话故事影子，关键是居然看见了中国神话故事的宇宙即将开启！实在太令人振奋了！故事通过一颗混元珠延炼后，变为灵珠和魔丸，被阴差阳错附与两主角。这个故事的哪吒和敖丙焕然新形象，被压制魔性的哪吒和冲破限制的哪吒，形象反差太有冲击力。敖丙也太帅了吧！完全颠覆了我对龙王三太子的丑恶印象！少不了的亲情和友情在故事叙述中更丰富了不同角色。总之，期待姜子牙！",
      "ceimg": "",
      "ca": "老娜的五脏六腑",
      "cd": 1563892867000,
      "lcd": 1563921667,
      "caimg": "http://img5.mtime.cn/up/2019/06/14/171054.16945012_48X48.jpg",
      "cal": "上海",
      "commentCount": 0,
      "cr": 9
    },
    {
      "tweetId": 47980411,
      "ce": "🐂👃！点赞👍太乙真人川普逗死我了",
      "ceimg": "",
      "ca": "大熊兔兔ocu",
      "cd": 1563890432000,
      "lcd": 1563919232,
      "caimg": "http://img2.mtime.cn/images/default/head_48X48.gif",
      "cal": "",
      "commentCount": 0,
      "cr": 10
    },
    {
      "tweetId": 47980259,
      "ce": "太好看了吧😭😭中国动画电影一次比一次惊艳😭起来了啊，爱了爱了",
      "ceimg": "",
      "ca": "Severus·Snape_",
      "cd": 1563889093000,
      "lcd": 1563917893,
      "caimg": "http://img32.mtime.cn/up/2013/11/08/223302.49865954_48X48.jpg",
      "cal": "东京",
      "commentCount": 0,
      "cr": 10
    },
    {
      "tweetId": 47979981,
      "ce": "闻到鸣人和伊鲁卡的味道。",
      "ceimg": "",
      "ca": "丹尼尔·王",
      "cd": 1563884019000,
      "lcd": 1563912819,
      "caimg": "http://img2.mtime.cn/u/1876/2219876/c49e1aee-93b8-44b7-b3d6-b4fec55fd7ca/48X48.jpg",
      "cal": "拉萨",
      "commentCount": 0,
      "cr": -1
    },
    {
      "tweetId": 47979617,
      "ce": "整体比较出色，可以看出主创团队的良苦用心，全新改编，剧本新颖、造型独特、配音精准、动作流畅、画面华丽，“国漫崛起”有些夸张，但至少国漫有了个新高度。20190723英皇首映礼",
      "ceimg": "http://img5.mtime.cn/Tweet/2019/07/23/175340.91838626.jpg",
      "ca": "乱步道尔",
      "cd": 1563875622000,
      "lcd": 1563904422,
      "caimg": "http://img32.mtime.cn/up/2012/10/09/021512.57477818_48X48.jpg",
      "cal": "北京",
      "commentCount": 0,
      "cr": 7.9
    },
    {
      "tweetId": 47979348,
      "ce": "这个绝对值得期待！哪吒的长相丑萌丑萌的，很有创意！",
      "ceimg": "",
      "ca": "老幸运lucky",
      "cd": 1563871892000,
      "lcd": 1563900692,
      "caimg": "http://img5.mtime.cn/up/2018/10/04/001813.32917370_48X48.jpg",
      "cal": "西安",
      "commentCount": 0,
      "cr": 10
    },
    {
      "tweetId": 47979323,
      "ce": "下一部搞个牛魔王的主题电影，然后就可以再把哪吒和大圣组个队再来一部...",
      "ceimg": "",
      "ca": "邪易云",
      "cd": 1563871477000,
      "lcd": 1563900277,
      "caimg": "http://img22.mtime.cn/up/2010/05/16/194433.25430215_48X48.jpg",
      "cal": "成都",
      "commentCount": 1,
      "cr": -1
    },
    {
      "tweetId": 47979289,
      "ce": "牛批",
      "ceimg": "",
      "ca": "时光用户_1505019052060",
      "cd": 1563870744000,
      "lcd": 1563899544,
      "caimg": "http://img2.mtime.cn/images/default/head_48X48.gif",
      "cal": "",
      "commentCount": 0,
      "cr": 10
    },
    {
      "tweetId": 47978860,
      "ce": "又给一群屎真香的看客提供了原料！整天碰瓷西游记，就孙悟空、哪吒那点破事折腾来折腾去，脑残们的钱真是好骗",
      "ceimg": "",
      "ca": "xcjp",
      "cd": 1563858428000,
      "lcd": 1563887228,
      "caimg": "http://img2.mtime.cn/images/default/head_48X48.gif",
      "cal": "香格里拉",
      "commentCount": 4,
      "cr": 1
    },
    {
      "tweetId": 47978168,
      "ce": "真的很好看，相信我",
      "ceimg": "",
      "ca": "伟大的郝幽玥",
      "cd": 1563810222000,
      "lcd": 1563839022,
      "caimg": "http://img2.mtime.cn/images/default/head_48X48.gif",
      "cal": "",
      "commentCount": 0,
      "cr": 10
    },
    {
      "tweetId": 47977860,
      "ce": "又是一部傻逼骗钱国漫",
      "ceimg": "",
      "ca": "吴带当风",
      "cd": 1563804307000,
      "lcd": 1563833107,
      "caimg": "http://img22.mtime.cn/up/2010/09/09/085303.19616628_48X48.jpg",
      "cal": "杭州",
      "commentCount": 16,
      "cr": 2
    },
    {
      "tweetId": 47977790,
      "ce": "梗超多，剧情流畅，打斗精彩，配乐棒。扣了一分是因为有一个地方有点意外，感觉没必要加。",
      "ceimg": "",
      "ca": "nine南",
      "cd": 1563802789000,
      "lcd": 1563831589,
      "caimg": "http://img5.mtime.cn/up/2017/07/25/214418.25863326_48X48.jpg",
      "cal": "",
      "commentCount": 2,
      "cr": 9
    },
    {
      "tweetId": 47977682,
      "ce": "必须十分，上映以后必须二刷！！",
      "ceimg": "",
      "ca": "Aaaaaagnes",
      "cd": 1563800012000,
      "lcd": 1563828812,
      "caimg": "http://img32.mtime.cn/up/2014/08/02/120452.28255333_48X48.jpg",
      "cal": "北京",
      "commentCount": 0,
      "cr": 10
    },
    {
      "tweetId": 47977632,
      "ce": "我想说，作为一部动画电影作品，的确是达到了高标准。也引起了热议，包括从国学角度和教育角度都有一些思考，期待封神宇宙的东方式崛起。同时我也有苛刻的意见：对于好莱坞电影和日系动画的崇拜和模仿始终存在；一些不良对白和动作对于儿童观众还是有一些不好的影响，年轻创作者应该考虑暑期市场的注意事项。",
      "ceimg": "",
      "ca": "声色画报",
      "cd": 1563798243000,
      "lcd": 1563827043,
      "caimg": "http://img22.mtime.cn/up/2010/08/12/113437.98575048_48X48.jpg",
      "cal": "纽约",
      "commentCount": 0,
      "cr": 8.2
    }
  ],
  "tpc": 6,
  "totalCount": 102,
  "totalCommentCount": 231
}
