import http from './public'
// 登陆
export const userLogin = (params) => {
  return http.postRequest(`/users/login`, params)
}
// 退出登陆
export const loginOut = (params) => {
  return http.postRequest(`/customer/loginOut`, params)
}
// 用户信息
export const userInfo = (params) => {
  return http.postRequest(`/customer/userInfo`, params)
}
// 注册账号
export const register = (params) => {
  return http.postRequest(`/customer/register`, params)
}
// 上传图片
export const upload = (params) => {
  return http.postRequest(`/users/upload`, params)
}
// 修改头像
export const updateheadimage = (params) => {
  return http.postRequest(`/users/updateheadimage`, params)
}
// 首页接口
export const productHome = (params) => {
  return http.postRequest(`/goods/productHome`, params)
}
// 获取可用的广告内容列表
export const queryAdContent = (params) => {
  return http.postRequest(`/ad/queryAdContent`, params)
}
// 获取可用的广告内容列表
export const queryAdContentS = (params) => {
  return http.postRequest(`/ad/queryAdContentS`, params)
}
export const captchaInit = (params) => {
  return http.getRequest(`/common/captcha/init`, params)
}

