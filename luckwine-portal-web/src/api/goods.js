import http from './public'
// 电脑列表
export const getComputer = (params) => {
  return http.postRequest(`/goods/computer`, params)
}
// 获取购物车列表
export const getCartList = (params) => {
  return http.postRequest(`/users/cartList`, params)
}
// 加入购物车
export const addCart = (params) => {
  return http.postRequest(`/users/addCart`, params)
}
// 批量加入购物车
export const addCartBatch = (params) => {
  return http.postRequest(`/users/addCartBatch`, params)
}
// 删除购物车
export const delCart = (params) => {
  return http.postRequest(`/users/delCart`, params)
}
// 编辑购物车
export const cartEdit = (params) => {
  return http.postRequest(`/users/cartEdit`, params)
}
// 全选
export const editCheckAll = (params) => {
  return http.postRequest(`/users/editCheckAll`, params)
}
// 删除整条购物车
export const cartDel = (params) => {
  return http.postRequest(`/users/cartDel`, params)
}
// 获取用户地址
export const addressList = (params) => {
  return http.postRequest(`/customer/addressList`, params)
}
// 修改收货地址
export const addressUpdate = (params) => {
  return http.postRequest(`/customer/addressUpdate`, params)
}
// 添加收货地址
export const addressAdd = (params) => {
  return http.postRequest(`/customer/addressAdd`, params)
}
// 删除收货地址
export const addressDel = (params) => {
  return http.postRequest(`/customer/addressDel`, params)
}
// 支付
export const payMent = (params) => {
  return http.postRequest(`/users/payMent`, params)
}
// 订单
export const orderList = (params) => {
  return http.postRequest(`/users/orderList`, params)
}
// 商品详情
export const productDet = (params) => {
  return http.postRequest(`/goods/detail`, params)
}
// 删除订单
export const delOrder = (params) => {
  return http.postRequest(`/users/delOrder`, params)
}
