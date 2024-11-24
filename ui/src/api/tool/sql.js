import request from '@/utils/request'

// 预览SQL
export function previewSql(data) {
  return request({
    url: '/tool/sql/preview',
    method: 'post',
    data: data
  })
}

// 下载SQL
export function downloadSql(data) {
  return request({
    url: '/tool/sql/download',
    method: 'post',
    data: data,
    responseType: 'blob'
  })
}

// 获取表字段列表
export function getTableColumns(tableName) {
  return request({
    url: '/tool/sql/column/' + tableName,
    method: 'get'
  })
} 