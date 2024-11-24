<template>
  <div class="app-container">
    <!-- 表基本信息 -->
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>表基本信息</span>
        </div>
      </template>
      <el-form :model="tableInfo" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="表名称" prop="tableName">
              <el-input v-model="tableInfo.tableName" placeholder="请输入表名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="表描述" prop="tableComment">
              <el-input v-model="tableInfo.tableComment" placeholder="请输入表描述" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>

    <!-- 字段设计 -->
    <el-card class="box-card" style="margin-top: 10px">
      <template #header>
        <div class="card-header">
          <span>字段设计</span>
          <el-button type="primary" @click="handleAddColumn">添加字段</el-button>
        </div>
      </template>
      <el-table :data="columnList" style="width: 100%">
        <el-table-column label="字段名称" prop="columnName">
          <template #default="scope">
            <el-input v-model="scope.row.columnName" placeholder="字段名称" />
          </template>
        </el-table-column>
        <el-table-column label="字段类型" prop="columnType">
          <template #default="scope">
            <el-select v-model="scope.row.columnType" placeholder="字段类型">
              <el-option v-for="(item, index) in dataTypeList" 
                :key="index" 
                :label="item.label" 
                :value="item.value">
              </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="字段长度" prop="columnLength" width="180">
          <template #default="scope">
            <el-input-number v-model="scope.row.columnLength" :min="0" />
          </template>
        </el-table-column>
        <el-table-column label="允许为空" prop="isNull" width="100">
          <template #default="scope">
            <el-checkbox v-model="scope.row.isNull" />
          </template>
        </el-table-column>
        <el-table-column label="主键" prop="isPk" width="80">
          <template #default="scope">
            <el-checkbox v-model="scope.row.isPk" />
          </template>
        </el-table-column>
        <el-table-column label="自增" prop="isIncrement" width="80">
          <template #default="scope">
            <el-checkbox v-model="scope.row.isIncrement" />
          </template>
        </el-table-column>
        <el-table-column label="默认值" prop="defaultValue">
          <template #default="scope">
            <el-input v-model="scope.row.defaultValue" placeholder="默认值" />
          </template>
        </el-table-column>
        <el-table-column label="注释" prop="columnComment">
          <template #default="scope">
            <el-input v-model="scope.row.columnComment" placeholder="注释" />
          </template>
        </el-table-column>
        <el-table-column label="外键" width="80">
          <template #default="scope">
            <el-checkbox v-model="scope.row.isFk" @change="handleFkChange(scope.row)" />
          </template>
        </el-table-column>
        <el-table-column label="关联表" v-if="hasAnyFk" prop="fkTableName">
          <template #default="scope">
            <el-select 
              v-if="scope.row.isFk"
              v-model="scope.row.fkTableName" 
              placeholder="选择关联表"
              @change="handleFkTableChange(scope.row)"
            >
              <el-option 
                v-for="table in tableList" 
                :key="table.tableName"
                :label="table.tableName"
                :value="table.tableName">
              </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="关联字段" v-if="hasAnyFk" prop="fkColumnName">
          <template #default="scope">
            <el-select 
              v-if="scope.row.isFk"
              v-model="scope.row.fkColumnName" 
              placeholder="选择关联字段"
            >
              <el-option 
                v-for="column in getTableColumnsFromCache(scope.row.fkTableName)" 
                :key="column.columnName"
                :label="column.columnName"
                :value="column.columnName">
              </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="删除规则" v-if="hasAnyFk" prop="fkDeleteRule">
          <template #default="scope">
            <el-select 
              v-if="scope.row.isFk"
              v-model="scope.row.fkDeleteRule" 
              placeholder="选删除规则"
            >
              <el-option label="CASCADE" value="CASCADE" />
              <el-option label="SET NULL" value="SET NULL" />
              <el-option label="NO ACTION" value="NO ACTION" />
              <el-option label="RESTRICT" value="RESTRICT" />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="120">
          <template #default="scope">
            <el-button type="danger" link @click="handleDeleteColumn(scope.$index)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 索引设计 -->
    <el-card class="box-card" style="margin-top: 10px">
      <template #header>
        <div class="card-header">
          <span>索引设计</span>
          <el-button type="primary" @click="handleAddIndex">添加索引</el-button>
        </div>
      </template>
      <el-table :data="indexList" style="width: 100%">
        <el-table-column label="索引名称" prop="indexName">
          <template #default="scope">
            <el-input v-model="scope.row.indexName" placeholder="索引名称" />
          </template>
        </el-table-column>
        <el-table-column label="索引字段" prop="columnNames">
          <template #default="scope">
            <el-select v-model="scope.row.columnNames" multiple placeholder="选择字段">
              <el-option 
                v-for="item in columnList" 
                :key="item.columnName"
                :label="item.columnName"
                :value="item.columnName">
              </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="索引类型" prop="indexType">
          <template #default="scope">
            <el-select v-model="scope.row.indexType" placeholder="索引类型">
              <el-option label="普通索引" value="NORMAL" />
              <el-option label="唯一索引" value="UNIQUE" />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="120">
          <template #default="scope">
            <el-button type="danger" link @click="handleDeleteIndex(scope.$index)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 操作按钮 -->
    <div class="action-buttons" style="margin-top: 20px; text-align: center;">
      <el-button type="primary" @click="handlePreviewSql">预览SQL</el-button>
      <el-button type="success" @click="handleGenerateSql">生成SQL</el-button>
    </div>

    <!-- SQL预览对话框 -->
    <el-dialog title="SQL预览" v-model="sqlPreviewVisible" width="60%">
      <el-input
        v-model="sqlPreview"
        type="textarea"
        :rows="15"
        readonly
      />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="sqlPreviewVisible = false">关闭</el-button>
          <el-button type="primary" @click="handleCopySql">
            复制SQL
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { previewSql, downloadSql, getTableColumns as getTableColumnsApi } from "@/api/tool/sql"
import { listDbTable } from "@/api/tool/gen"  // 引入获取表列表的API
import { loadAllParams } from "@/api/page"
// 表基本信息
const tableInfo = reactive({
  tableName: '',
  tableComment: ''
})

// 字段列表
const columnList = ref([])

// 索引列表
const indexList = ref([])

// SQL预览相关
const sqlPreviewVisible = ref(false)
const sqlPreview = ref('')

// MySQL数据类型列表
const dataTypeList = [
  { label: 'int', value: 'int' },
  { label: 'bigint', value: 'bigint' },
  { label: 'varchar', value: 'varchar' },
  { label: 'char', value: 'char' },
  { label: 'text', value: 'text' },
  { label: 'datetime', value: 'datetime' },
  { label: 'decimal', value: 'decimal' },
  // 可以继续添加其他数据类型
]

// 数据库表列表
const tableList = ref([])
// 表字段缓存
const tableColumnsCache = reactive({})

// 是否有任何外键字段
const hasAnyFk = computed(() => {
  return columnList.value.some(col => col.isFk)
})

// 获取所有数据库表
const getTableList = async () => {
  try {
    const res = await listDbTable(loadAllParams)
    if (res.code === 200) {
      tableList.value = res.rows
    }
  } catch (error) {
    console.error('获取表列表失败:', error)
  }
}

// 获取表的字段列表
const getTableColumnsFromCache = (tableName) => {
  if (!tableName) return []
  return tableColumnsCache[tableName] || []
}

// 处理外键变更
const handleFkChange = (row) => {
  if (!row.isFk) {
    row.fkTableName = ''
    row.fkColumnName = ''
    row.fkDeleteRule = ''
  }
}

// 处理关联表变更
const handleFkTableChange = async (row) => {
  row.fkColumnName = ''
  try {
    const res = await getTableColumnsApi(row.fkTableName)
    if (res.code === 200) {
      tableColumnsCache[row.fkTableName] = res.data
    } else {
      ElMessage.error(res.msg || '获取表字段失败')
    }
  } catch (error) {
    console.error('获取表字段失败:', error)
    ElMessage.error('获取表字段失败')
  }
}

// 添加字段
const handleAddColumn = () => {
  columnList.value.push({
    columnName: '',
    columnType: '',
    columnLength: 0,
    isNull: true,
    isPk: false,
    isIncrement: false,
    defaultValue: '',
    columnComment: '',
    isFk: false,
    fkTableName: '',
    fkColumnName: '',
    fkDeleteRule: ''
  })
}

// 删除字段
const handleDeleteColumn = (index) => {
  columnList.value.splice(index, 1)
}

// 添加索引
const handleAddIndex = () => {
  indexList.value.push({
    indexName: '',
    columnNames: [],
    indexType: 'NORMAL'
  })
}

// 删除索引
const handleDeleteIndex = (index) => {
  indexList.value.splice(index, 1)
}

// 修改validateData方法
const validateData = () => {
  if (!tableInfo.tableName) {
    ElMessage.warning('请输入表名称')
    return false
  }
  if (columnList.value.length === 0) {
    ElMessage.warning('请至少添加一个字段')
    return false
  }
  
  // 创建一个新的数组来存储处理后的数据
  const processedColumns = columnList.value.map(column => {
    const processedColumn = { ...column }  // 创建副本，不修改原始数据
    
    // 验证字段名称
    if (!processedColumn.columnName) {
      ElMessage.warning('请输入字段名称')
      return false
    }
    
    // 验证字段类型
    if (!processedColumn.columnType) {
      ElMessage.warning('请选择字段类型')
      return false
    }
    
    // 验证字段长度
    if (processedColumn.columnType === 'varchar' || processedColumn.columnType === 'char') {
      if (!processedColumn.columnLength || processedColumn.columnLength <= 0) {
        ElMessage.warning(`字段 ${processedColumn.columnName} 的长度必须大于0`)
        return false
      }
    }
    
    // 处理默认值
    if (processedColumn.defaultValue) {
      if (processedColumn.columnType === 'varchar' || processedColumn.columnType === 'char') {
        // 检查是否已经有引号，如果没有才添加
        if (!processedColumn.defaultValue.startsWith("'") && !processedColumn.defaultValue.endsWith("'")) {
          processedColumn.defaultValue = `'${processedColumn.defaultValue}'`
        }
      }
    }
    
    return processedColumn
  })
  
  if (processedColumns.includes(false)) {
    return false
  }
  
  return processedColumns
}

// 修改handlePreviewSql方法
const handlePreviewSql = async () => {
  const processedColumns = validateData()
  if (!processedColumns) {
    return
  }
  try {
    // 构造请求数据，使用处理后的列数据
    const data = {
      tableName: tableInfo.tableName,
      tableComment: tableInfo.tableComment,
      columns: processedColumns,
      indexes: indexList.value
    }
    
    // 调用预览接口
    const res = await previewSql(data)
    if (res.code === 200) {
      sqlPreview.value = res.data
      sqlPreviewVisible.value = true
    } else {
      ElMessage.error(res.msg || '预览失败')
    }
  } catch (error) {
    console.error('SQL预览失败:', error)
    ElMessage.error('SQL预览失败')
  }
}

// 修改handleGenerateSql方法
const handleGenerateSql = async () => {
  const processedColumns = validateData()
  if (!processedColumns) {
    return
  }
  try {
    // 构造请求数据，使用处理后的列数据
    const data = {
      tableName: tableInfo.tableName,
      tableComment: tableInfo.tableComment,
      columns: processedColumns,
      indexes: indexList.value
    }
    
    // 调用下载接口
    const res = await downloadSql(data)
    // 创建blob对象
    const blob = new Blob([res], { type: 'application/x-sql' })
    // 创建下载链接
    const url = window.URL.createObjectURL(blob)
    const link = document.createElement('a')
    link.href = url
    link.setAttribute('download', `${tableInfo.tableName}.sql`)
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
    window.URL.revokeObjectURL(url)
    
    ElMessage.success('SQL生成成功')
  } catch (error) {
    console.error('SQL生成失败:', error)
    ElMessage.error('SQL生成失败')
  }
}

// 复制SQL
const handleCopySql = () => {
  navigator.clipboard.writeText(sqlPreview.value).then(() => {
    ElMessage.success('复制成功')
  }).catch(() => {
    ElMessage.error('复制失败')
  })
}

// 在组件挂载时获取表列表
onMounted(() => {
  getTableList()
})
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>