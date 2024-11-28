import request from '@/utils/request'

// 查询测试列表
export function listCat(query) {
  return request({
    url: '/manage/cat/list',
    method: 'get',
    params: query
  })
}

// 查询测试详细
export function getCat(id) {
  return request({
    url: '/manage/cat/' + id,
    method: 'get'
  })
}

// 新增测试
export function addCat(data) {
  return request({
    url: '/manage/cat',
    method: 'post',
    data: data
  })
}

// 修改测试
export function updateCat(data) {
  return request({
    url: '/manage/cat',
    method: 'put',
    data: data
  })
}

// 删除测试
export function delCat(id) {
  return request({
    url: '/manage/cat/' + id,
    method: 'delete'
  })
}
