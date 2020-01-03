<template>
    <div class="app-container">
        <!--工具条-->
        <el-row>
            <el-col :span="24" class="toolbar">
                <el-form :inline="true" :model="filters" ref="filterForm">
                    <el-form-item label="应用id" prop="appId">
                        <el-input v-model="filters.appId"></el-input>
                    </el-form-item>
                    <el-form-item label="接口地址" prop="apiUrl">
                        <el-input v-model="filters.apiUrl"></el-input>
                    </el-form-item>
                    <el-form-item label="接口方法" prop="requestMethod">
                        <el-input v-model="filters.requestMethod"></el-input>
                    </el-form-item>
                    <el-form-item label="" prop="requestHeader">
                        <el-input v-model="filters.requestHeader"></el-input>
                    </el-form-item>
                    <el-form-item label="请求参数" prop="requestParams">
                        <el-input v-model="filters.requestParams"></el-input>
                    </el-form-item>
                    <el-form-item label="响应状态码" prop="responseStatus">
                        <el-input v-model="filters.responseStatus"></el-input>
                    </el-form-item>
                    <el-form-item label="相应内容" prop="responseBody">
                        <el-input v-model="filters.responseBody"></el-input>
                    </el-form-item>
                    <el-form-item label="" prop="spentTime">
                        <el-input v-model="filters.spentTime"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="handleSearch('filterForm')">查询</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="24" class="toolbar">
                <el-form :inline="true">
                    <el-form-item>
                        <el-button type="primary" @click="handleAdd">创建</el-button>
                        <el-button type="primary" @click="handleBatch">批量操作</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <!--列表开始-->
        <el-table :data="pagination.content" highlight-current-row v-loading="isLoading" border
                  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column type="index" label="NO" width="80"></el-table-column>
            <el-table-column show-overflow-tooltip prop="appId" label="应用id" width="150"></el-table-column>
            <el-table-column show-overflow-tooltip prop="apiUrl" label="接口地址" width="150"></el-table-column>
            <el-table-column show-overflow-tooltip prop="requestMethod" label="接口方法" width="150"></el-table-column>
            <el-table-column show-overflow-tooltip prop="requestHeader" label="" width="150"></el-table-column>
            <el-table-column show-overflow-tooltip prop="requestParams" label="请求参数" width="150"></el-table-column>
            <el-table-column show-overflow-tooltip prop="responseStatus" label="响应状态码" width="150"></el-table-column>
            <el-table-column show-overflow-tooltip prop="responseBody" label="相应内容" width="150"></el-table-column>
            <el-table-column show-overflow-tooltip prop="spentTime" label="" width="150"></el-table-column>
            <el-table-column show-overflow-tooltip prop="createTime" label="调用时间" width="150"></el-table-column>
            <el-table-column label="操作" width="350" fixed="right">
                <template slot-scope="scope">
                    <el-button @click="handleEdit(scope.$index, scope.row)" type="primary">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--列表结束-->
        <!--分页开始-->
        <div class="pagination" v-if='pagination.totalElements > 0'>
            <el-pagination layout="total, sizes, prev, pager, next, jumper" @size-change="handleSizeChange"
                           @current-change="handleCurrentChange" :current-page.sync="pagination.page"
                           :page-size="size" :page-sizes="[10, 20, 50, 100]"
                           :total="pagination.totalElements" :disabled="isLoading">
            </el-pagination>
        </div>

        <!--新建/编辑/详情区-->
        <section>
            <el-dialog :title="formObj.title" :visible.sync="formObj.formVisible" :close-on-click-modal="false">
                <el-form :inline="true" :model="formObj.formModel" :rules="invokeapilogRule" label-width="110px"
                         ref="invokeapilogForm">
                    <table class="productListTable productListTable-col4">
                        <tr>
                            <td>
                                <el-form-item label="应用id" prop="appId">
                                    <el-input type="text" v-model="formObj.formModel.appId" placeholder="应用id"/>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="接口地址" prop="apiUrl">
                                    <el-input type="text" v-model="formObj.formModel.apiUrl" placeholder="接口地址"/>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="接口方法" prop="requestMethod">
                                    <el-input type="text" v-model="formObj.formModel.requestMethod" placeholder="接口方法"/>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="" prop="requestHeader">
                                    <el-input type="text" v-model="formObj.formModel.requestHeader" placeholder=""/>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="请求参数" prop="requestParams">
                                    <el-input type="text" v-model="formObj.formModel.requestParams" placeholder="请求参数"/>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="响应状态码" prop="responseStatus">
                                    <el-input type="text" v-model="formObj.formModel.responseStatus"
                                              placeholder="响应状态码"/>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="相应内容" prop="responseBody">
                                    <el-input type="text" v-model="formObj.formModel.responseBody" placeholder="相应内容"/>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <el-form-item label="" prop="spentTime">
                                    <el-input type="text" v-model="formObj.formModel.spentTime" placeholder=""/>
                                </el-form-item>
                            </td>
                        </tr>
                    </table>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="handleClose('invokeapilogForm')">取消</el-button>
                    <el-button type="primary" @click="handleSubmit('invokeapilogForm')">提交</el-button>
                </div>
            </el-dialog>
        </section>

    </div>
</template>
<script>
    import http from '../../axios/http';
    export default {
        name: 'invokeapilog',
        data() {
            return {
                heightNum: 0,
                filters: {
                    appId: '',
                    apiUrl: '',
                    requestMethod: '',
                    requestHeader: '',
                    requestParams: '',
                    responseStatus: '',
                    responseBody: '',
                    spentTime: '',
                },
                pagination: {
                    totalElements: 0,
                    page: 1,
                    content: []
                },
                size: 20,
                isLoading: false,
                selecteds: [],//列表选中列
                //新增和编辑Obj
                formObj: {
                    title: '',
                    type: '1',//1新增，2编辑
                    action: '',
                    formModel: {},
                    formVisible: false//编辑界面是否显示
                },

                handleSelectionRowIndex: 0,
                invokeapilogRule: {}
            }
        },
        methods: {
            tableRowClassName(row, index) {
                if (row.warning === '1') {
                    console.log("index：" + index);
                    return 'info-row';
                }
                return '';
            },
            //分页查询
            loadPagination() {
                const self = this;
                let params = {
                    page: this.pagination.page === 0 ? 1 : this.pagination.page,
                    size: this.size === 0 ? 20 : this.size
                };
                if (this.sort) {
                    params.sort = this.sort;
                }
                //  应用id
                if (this.filters.appId && this.filters.appId !== '') {
                    params['search_eq_appId '] = this.filters.appId.trim();
                }
                //  接口地址
                if (this.filters.apiUrl && this.filters.apiUrl !== '') {
                    params['search_eq_apiUrl '] = this.filters.apiUrl.trim();
                }
                //  接口方法
                if (this.filters.requestMethod && this.filters.requestMethod !== '') {
                    params['search_eq_requestMethod '] = this.filters.requestMethod.trim();
                }
                //
                if (this.filters.requestHeader && this.filters.requestHeader !== '') {
                    params['search_eq_requestHeader '] = this.filters.requestHeader.trim();
                }
                //  请求参数
                if (this.filters.requestParams && this.filters.requestParams !== '') {
                    params['search_eq_requestParams '] = this.filters.requestParams.trim();
                }
                //  响应状态码
                if (this.filters.responseStatus && this.filters.responseStatus !== '') {
                    params['search_eq_responseStatus '] = this.filters.responseStatus.trim();
                }
                //  相应内容
                if (this.filters.responseBody && this.filters.responseBody !== '') {
                    params['search_eq_responseBody '] = this.filters.responseBody.trim();
                }
                //
                if (this.filters.spentTime && this.filters.spentTime !== '') {
                    params['search_eq_spentTime '] = this.filters.spentTime.trim();
                }
                self.isLoading = true;
                http.get("api/invokeapilog", {params: params}).then(response => {
                    console.log(JSON.stringify(response));
                    response.page = response.number + 1;
                    this.isLoading = false;
                    this.pagination = response;
                });
            },
            //查询条件查询
            handleSearch(ref) {
                this.$refs[ref].validate((valid) => {
                    if (valid) {
                        this.handleCurrentChange(1);
                    }
                });
            },
            handleClick(tab) {

            },
            //新建页面
            handleAdd() {
                this.formObj = {
                    title: '-创建',
                    formVisible: true,
                    type: '1',//1新增，2编辑
                    formModel: {},
                };
            },
            handleSelectionChange(val){
                this.selecteds = val;
                console.log("handleSelectionChange");
                console.log("批量勾选的值：" + JSON.stringify(val));
            },
            //批量操作
            handleBatch(){
                if (this.selecteds.length == 0) {
                    this.$alert('请重新选择一条数据！', '提示', {
                        confirmButtonText: '确定',
                    });
                    return;
                } else {
                    this.$alert(JSON.stringify(this.selecteds));
                }
            },
            //编辑页面
            handleEdit(index, row){
                console.log("时间：" + JSON.stringify(row))
                this.formObj = {
                    title: '-编辑',
                    type: '2',//0，详情，1新增，2编辑
                    formVisible: true,
                    formModel: Object.assign({}, row),
                };
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.size = val;
                this.loadPagination();
            },
            handleCurrentChange(val) {
                //正在加载数据
                if (this.isLoading) {
                    return;
                }
                window.console.log('this.pagination.page:' + val);
                this.pagination.page = val;
                window.console.log('排序动作:' + this.sort);
                this.loadPagination();
            },
            handleClose(ref) {//关闭表单
                this.formObj.formVisible = false;
                this.$refs[ref].resetFields();
                this.loadPagination();
            },
            //保存与修改
            handleSubmit(ref){
                this.$refs[ref].validate((valid) => {
                    let url = "api/invokeapilog";
                    let method = "post";
                    if (this.formObj.formModel.id) {
                        method = "put";
                        url = "api/invokeapilog";
                    }
                    console.log(JSON.stringify(this.formObj.formModel));
                    if (valid) {
                        http.postOrPut(url, method, this.formObj.formModel).then(response => {
                            if (response.code === '00000') {
                                this.formObj.formVisible = false;
                                this.$refs[ref].resetFields();
                                this.loadPagination();
                            } else {
                                console.log(JSON.stringify(response.message))
                            }
                        }).catch(function (error) {
                            console.log(JSON.stringify(error));
                        });
                    } else {
                        this.$refs[ref].resetFields();
                        this.isLoading = false;
                    }
                });
            },
            // 计算表格高度
            initData(){
                let self = this;
                // 2.0版本特殊处理
                setTimeout(function () {
                    self.heightNum = self.utils.calcTableHeight();
                }, 10);
                // 响应窗口大小改变
                window.onresize = function () {
                    self.heightNum = self.utils.calcTableHeight();
                };
            }
        },
        filters: {
            auditFilter: function (row) {
                if (!row) return ''
                let list = row.piclist;
                let num = 0;
                for (let i = 0; i < list.length; i++) {
                    let info = list[i];
                    if (info.auditStatus != 1) {
                        num = num + 1;
                    }
                }
                return num;
            }
        },
        mounted() {
            this.initData();
            this.loadPagination();
        }
    }
</script>
<style>
    .takManagementInfo {
        line-height: 36px;
    }

</style>
