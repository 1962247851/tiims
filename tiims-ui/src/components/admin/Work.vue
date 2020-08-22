<template>
    <div class="pa-3">
        <v-data-table :items="works" :loading="loading" :search="search"
                      :headers="headers" sort-by="id" class="elevation-2">
            <template v-slot:top>
                <v-toolbar flat>
                    <v-text-field v-model="search" append-icon="mdi-magnify" label="搜索" clearable single-line
                                  hide-details class="me-2"/>
                    <v-btn color="primary" :disabled="loading" icon @click="refresh">
                        <v-progress-circular color="primary" size="18" width="2" v-if="loading" indeterminate/>
                        <v-icon v-else>mdi-refresh</v-icon>
                    </v-btn>
                    <v-btn color="primary" dark icon @click="dialog = true">
                        <v-icon>mdi-plus</v-icon>
                    </v-btn>
                </v-toolbar>
                <v-divider class="mx-4" inset vertical></v-divider>
                <v-spacer/>
                <v-dialog v-model="dialog" max-width="500px" persistent>
                    <v-card>
                        <v-card-title>
                            <span class="headline">{{ formTitle }}</span>
                        </v-card-title>

                        <v-card-text>
                            <v-container>
                                <v-row>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-dialog ref="dialog" v-model="dialogDateModal" :return-value.sync="dialogDate"
                                                  persistent width="290px">
                                            <template v-slot:activator="{ on, attrs }">
                                                <v-text-field v-model="dialogDate" label="日期"
                                                              readonly v-bind="attrs" v-on="on"/>
                                            </template>
                                            <v-date-picker v-model="dialogDate" scrollable locale="zh-cn">
                                                <v-spacer/>
                                                <v-btn text color="primary" @click="dialogDateModal = false">取消</v-btn>
                                                <v-btn text color="primary" @click="$refs.dialog.save(dialogDate)">确定
                                                </v-btn>
                                            </v-date-picker>
                                        </v-dialog>

                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field type="number" v-model="editedItem.classId" label="班级序号"/>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field type="number" v-model="editedItem.teacherId" label="助教序号"/>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field type="number" v-model="editedItem.number" label="实到人数"/>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card-text>

                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="close">取消</v-btn>
                            <v-btn color="blue darken-1" text @click="save">保存</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </template>

            <template v-slot:item.date="{ item }">
                <span>{{UTIL.formatTimeTwo(item.date,"Y年M月D日")}}</span>
            </template>

            <template v-slot:item.updateTimestamp="{ item }">
                <span>{{UTIL.formatTimeTwo(item.updateTimestamp,"Y年M月D日 h:m:s")}}</span>
            </template>

            <template v-slot:item.classDetail="{ item }">
                <v-menu open-on-hover>
                    <template v-slot:activator="{ on, attrs }">
                        <div v-bind="attrs" v-on="on">
                            <v-btn rounded elevation="0">{{item.classDetail.nature}}</v-btn>
                        </div>
                    </template>
                    <v-card>
                        <v-card-title>{{item.classDetail.nature}} - {{item.classDetail.id}}</v-card-title>
                        <v-list>
                            <v-list-item>
                                <v-text-field disabled label="班级人数" v-model="item.classDetail.number"/>
                            </v-list-item>
                            <v-list-item>
                                <v-text-field disabled label="补习科目门数" v-model="item.classDetail.lessons"/>
                            </v-list-item>
                            <v-list-item>
                                <v-text-field disabled label="班级收费/人（￥）" v-model="item.classDetail.cost"/>
                            </v-list-item>
                        </v-list>
                    </v-card>
                </v-menu>
            </template>

            <template v-slot:item.teacherDetail="{ item }">
                <v-menu open-on-hover>
                    <template v-slot:activator="{ on, attrs }">
                        <div v-bind="attrs" v-on="on">
                            <v-btn rounded elevation="0">{{item.teacherDetail.name}}</v-btn>
                        </div>
                    </template>
                    <v-card>
                        <v-card-title>{{item.teacherDetail.name}} - {{item.teacherDetail.id}}</v-card-title>
                        <v-list>
                            <v-list-item>
                                <v-text-field disabled label="姓名" v-model="item.teacherDetail.name"/>
                            </v-list-item>
                            <v-list-item>
                                <v-text-field disabled label="联系方式" v-model="item.teacherDetail.phone"/>
                            </v-list-item>
                            <v-list-item>
                                <v-text-field disabled label="薪资/月（￥）" v-model="item.teacherDetail.salary"/>
                            </v-list-item>
                        </v-list>
                    </v-card>
                </v-menu>
            </template>

            <template v-slot:item.percent="{ item }">
                <v-chip :color="(item.percent = (100 * item.number / item.classDetail.number))===100.00?'success':
                item.percent>=60?'primary':'error'">
                    {{item.percent.toFixed(2)}}%
                </v-chip>
            </template>

            <template v-slot:item.actions="{ item }">
                <v-btn icon small @click="editItem(item)" color="warning">
                    <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon small @click="deleteItem(item)" color="error">
                    <v-icon>mdi-delete</v-icon>
                </v-btn>
            </template>

        </v-data-table>
    </div>
</template>

<script>
    export default {
        name: "Work",
        data() {
            return {
                works: [],
                loading: true,
                search: "",
                dialog: false,
                editedItem: {},
                editedIndex: -1,
                defaultItem: {},

                dialogDate: new Date().toISOString().substring(0, 10),
                dialogDateModal: false,

                headers: [
                    {text: '序号', value: 'id'},
                    {text: '日期', value: 'date'},
                    {text: '班级信息', value: 'classDetail'},
                    {text: '当天助教信息', value: 'teacherDetail'},
                    {text: '当天班级实到人数', value: 'number'},
                    {text: '出勤率', value: 'percent'},
                    {text: '更新时间', value: 'updateTimestamp'},
                    {text: '操作', value: 'actions'}
                ],
            }
        },
        computed: {
            formTitle() {
                return this.editedIndex === -1 ? '添加' : '编辑'
            },
        },
        watch: {
            dialog(val) {
                val || this.close()
            },
        },
        methods: {
            save() {
                this.editedItem.date = this.UTIL.ISODateStringToTimestamp(this.dialogDate);
                if (this.editedIndex === -1) {
                    this.insert(this.editedItem)
                } else {
                    this.update(this.editedItem)
                }
            },
            insert(item) {
                this.axios.put(this.API.URL.INSERT_WORK, item).then(value => {
                    let data = value.data;
                    if (data.success) {
                        this.works.push(data.data);
                        this.close()
                    }
                })
            },
            update(item) {
                this.axios.post(this.API.URL.UPDATE_WORK, item).then(value => {
                    let data = value.data;
                    if (data.success) {
                        Object.assign(this.works[this.editedIndex], data.data);
                        this.close()
                    }
                })
            },
            deleteItem(item) {
                if (confirm("确定删除出勤：\n" + item.id)) {
                    const index = this.works.indexOf(item);
                    this.axios.delete(this.API.URL.DELETE_WORK + item.id).then(result => {
                        let data = result.data;
                        if (data.success) {
                            alert("删除成功");
                            this.works.splice(index, 1)
                        }
                    })
                }
            },
            editItem(item) {
                this.editedIndex = this.works.indexOf(item);
                this.editedItem = Object.assign({}, item);
                this.editedItem.classId = this.editedItem.classDetail.id;
                this.editedItem.teacherId = this.editedItem.teacherDetail.id;
                this.dialog = true;
                this.dialogDate = this.UTIL.formatTimeTwo(item.date, "Y-M-D");
            },
            close() {
                this.dialog = false;
                this.$nextTick(() => {
                    this.editedItem = Object.assign({}, this.defaultItem);
                    this.editedIndex = -1
                })
            },
            refresh() {
                this.getWorks()
            },
            getWorks() {
                this.loading = true;
                let _this = this;
                this.axios.get(this.API.URL.QUERY_ALL_WORKS)
                    .then(function (result) {
                        _this.loading = false;
                        let data = result.data;
                        if (data.success) {
                            _this.works = data.data
                        }
                    }, function (error) {
                        _this.loading = false;
                        console.log(error)
                    })
            }
        },
        created() {
            this.getWorks()
        }
    }
</script>

<style scoped>

</style>