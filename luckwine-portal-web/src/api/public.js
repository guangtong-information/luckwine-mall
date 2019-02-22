import axios from 'axios'
// axios.defaults.timeout = 5000
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
let base = '/portal'

export const setCommonParams = (params) => {
  let data = {
    appName: 'luckwine-portal-web',
    channelCode: '01',
    traceId: generateUUID()
  }
  for (let it in params) {
    data[it] = params[it]
  }
  return data
}

export const generateUUID = () => {
  let d = new Date().getTime()
  let uuid = 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    let r = (d + Math.random() * 16) % 16 | 0
    d = Math.floor(d / 16)
    return (c === 'x' ? r : (r & 0x7 | 0x8)).toString(16)
  })
  return uuid
}

export default {
  fetchGet (url, params = {}) {
    return new Promise((resolve, reject) => {
      axios.get(`${base}${url}`, {params}).then(res => {
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  fetchPost (url, params = {}) {
    return new Promise((resolve, reject) => {
      axios.post(`${base}${url}`, params).then(res => {
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  postRequest (url, params) {
    return axios({
      method: 'post',
      url: `${base}${url}`,
      data: setCommonParams(params),
      transformRequest: [function (data) {
        let ret = ''
        for (let it in data) {
          if (data[it].length !== 0) {
            ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
          }
        }
        return ret
      }],
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
  },
  getRequest (url, params) {
    return axios({
      method: 'get',
      url: `${base}${url}`,
      params: setCommonParams(params)
    })
  }
}
