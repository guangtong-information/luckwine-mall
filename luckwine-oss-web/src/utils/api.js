import axios from 'axios';
import { getStore, setStore } from './storage';
import { router } from '../router/index';
import { Message } from 'iview';
import Cookies from 'js-cookie';
// 统一请求路径前缀
let base = '/oss';

/**
 *     private String appName;
 *     private String channelCode;
 *     private String traceId;
 */
export const setCommonParams = (params) => {
  let data = {
      appName: "luckwine-oss-web",
      channelCode: "02",
      traceId: generateUUID()
  };
  for (let it in params) {
    data[it] = params[it];
  }
  return data;
};

export const generateUUID = () => {
  let d = new Date().getTime();
  let uuid = 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    let r = (d + Math.random()*16)%16 | 0;
    d = Math.floor(d/16);
    return (c=='x' ? r : (r&0x7|0x8)).toString(16);
  });
  return uuid;
};

// 超时设定
axios.defaults.timeout = 10000;

axios.interceptors.request.use(config => {
    return config;
}, err => {
    Message.error('请求超时');
    return Promise.resolve(err);
});

// http response 拦截器
axios.interceptors.response.use(response => {
    const data = response.data;

    // 根据返回的code值来做不同的处理(和后端约定)
    switch (data.code) {
        case 401:
            // 未登录 清除已登录状态
            Cookies.set('userInfo', '');
            setStore('accessToken', '');
            router.push('/login');
            break;
        case 403:
            // 没有权限
            if (data.message !== null) {
                Message.error(data.message);
            } else {
                Message.error("未知错误");
            }
            break;
        case 500:
            // 错误
            if (data.message !== null) {
                Message.error(data.message);
            } else {
                Message.error("未知错误");
            }
            break;
        default:
            return data;
    }

    return data;
}, (err) => { 
    // 返回状态码不为200时候的错误处理
    Message.error(err.toString());
    return Promise.resolve(err);
});

export const getRequest = (url, params) => {
    let accessToken = getStore('accessToken');
    return axios({
        method: 'get',
        url: `${base}${url}`,
        params: setCommonParams(params),
        headers: {
            'accessToken': accessToken
        }
    });
};

export const postRequest = (url, params) => {
    let accessToken = getStore("accessToken");
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: setCommonParams(params),
        transformRequest: [function (data) {
            let ret = '';
            for (let it in data) {
                if(data[it].length != 0){
                    ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&';
                }
            }
            return ret;
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'accessToken': accessToken
        }
    });
};

export const postJsonRequest = (url, params) => {
  let accessToken = getStore("accessToken");
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: setCommonParams(params),
    // transformRequest: [function (data) {
    //   let ret = '';
    //   for (let it in data) {
    //     if(data[it].length != 0){
    //       ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&';
    //     }
    //   }
    //   return ret;
    // }],
    headers: {
      'Content-Type': 'application/json',
      'accessToken': accessToken
    }
  });
};

export const putRequest = (url, params) => {
    let accessToken = getStore("accessToken");
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: setCommonParams(params),
        transformRequest: [function (data) {
            let ret = '';
            for (let it in data) {
                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&';
            }
            return ret;
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'accessToken': accessToken
        }
    });
};

export const deleteRequest = (url, params) => {
    let accessToken = getStore('accessToken');
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        params: setCommonParams(params),
        headers: {
            'accessToken': accessToken
        }
    });
};

export const uploadFileRequest = (url, params) => {
    let accessToken = getStore('accessToken');
    return axios({
        method: 'post',
        url: `${base}${url}`,
        params: setCommonParams(params),
        headers: {
            'accessToken': accessToken
        }
    });
};
