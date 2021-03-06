<template>
    <div class="pa-3">
        <v-data-table :items="teachers" :loading="loading" :search="search"
                      :headers="headers" sort-by="id" class="elevation-2" show-expand>
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
                                        <v-text-field v-model="editedItem.name" label="姓名"/>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-menu width="300px" offset-y open-on-hover open-on-focus nudge-top="25">
                                            <template v-slot:activator="{ on, attrs }">
                                                <v-text-field v-model="listDegreeString" label="职称" readonly
                                                              v-bind="attrs" v-on="on"/>
                                            </template>
                                            <v-list>
                                                <v-list-item v-for="(item,index) in listDegrees" v-bind:key="index"
                                                             @click="clickDegreeItem(index)">{{item}}
                                                </v-list-item>
                                            </v-list>
                                        </v-menu>

                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field type="number" v-model="editedItem.salary" label="薪资/月（￥）"/>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field type="number" v-model="editedItem.phone" label="联系方式"/>
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

            <template v-slot:item.degree="{ item }">
                <v-chip :color="item.degree===0?'primary':'accent'">{{listDegrees[item.degree]}}</v-chip>
            </template>

            <template v-slot:item.actions="{ item }">
                <v-btn icon small @click="editItem(item)" color="warning">
                    <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon small @click="deleteItem(item)" color="error">
                    <v-icon>mdi-delete</v-icon>
                </v-btn>
            </template>

            <template v-slot:expanded-item="{headers, item }">
                <td :colspan="headers.length" class="ps-5 pe-5 pt-3 pb-3">
                    <v-card>
                        <v-toolbar elevation="0">
                            <v-toolbar-title>{{item.name}}的班级信息</v-toolbar-title>
                            <v-spacer/>
                            <v-btn icon @click="getClasses(item)">
                                <v-icon>mdi-refresh</v-icon>
                            </v-btn>
                        </v-toolbar>
                        <v-divider/>
                        <v-list-group v-for="tiClass in item.classList" v-bind:key="tiClass.id">
                            <template v-slot:activator>
                                <v-list-item-title>{{tiClass.nature}} - {{tiClass.id}}</v-list-item-title>
                            </template>
                            <v-list-item>
                                <v-text-field disabled label="班级人数" v-model="tiClass.number"/>
                            </v-list-item>
                            <v-list-item>
                                <v-text-field disabled label="补习科目门数" v-model="tiClass.lessons"/>
                            </v-list-item>
                            <v-list-item>
                                <v-text-field disabled label="班级收费/人（￥）" v-model="tiClass.cost"/>
                            </v-list-item>
                        </v-list-group>
                    </v-card>
                </td>
            </template>

        </v-data-table>
    </div>
</template>

<script>
    export default {
        name: "Teacher",
        data() {
            return {
                teachers: [],
                loading: true,
                search: "",
                dialog: false,
                editedItem: {},
                editedIndex: -1,
                defaultItem: {},

                listDegreeString: "班主任",
                listDegrees: ["班主任", "助教", "任课教师"],

                headers: [
                    {text: '序号', value: 'id'},
                    {text: '姓名', value: 'name'},
                    {text: '职称', value: 'degree'},
                    {text: '薪资/月（￥）', value: 'salary'},
                    {text: '联系方式', value: 'phone'},
                    {text: '操作', value: 'actions'},
                    {text: '班级信息', value: 'data-table-expand'},
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
                if (this.editedIndex === -1) {
                    this.insert(this.editedItem)
                } else {
                    this.update(this.editedItem)
                }
            },
            insert(item) {
                this.axios.put(this.API.URL.INSERT_TEACHER, item).then(value => {
                    let data = value.data;
                    if (data.success) {
                        this.teachers.push(data.data);
                        this.close()
                    }
                })
            },
            update(item) {
                this.axios.post(this.API.URL.UPDATE_TEACHER, item).then(value => {
                    let data = value.data;
                    if (data.success) {
                        Object.assign(this.teachers[this.editedIndex], data.data);
                        this.close()
                    }
                })
            },
            deleteItem(item) {
                if (confirm("确定删除教师：\n" + item.id)) {
                    const index = this.teachers.indexOf(item);
                    this.axios.delete(this.API.URL.DELETE_TEACHER + item.id).then(result => {
                        let data = result.data;
                        if (data.success) {
                            alert("删除成功");
                            this.teachers.splice(index, 1)
                        }
                    })
                }
            },
            editItem(item) {
                this.editedIndex = this.teachers.indexOf(item);
                this.editedItem = Object.assign({}, item);
                this.dialog = true;
            },
            close() {
                this.dialog = false;
                this.$nextTick(() => {
                    this.editedItem = Object.assign({}, this.defaultItem);
                    this.editedIndex = -1
                })
            },
            refresh() {
                this.getTeachers()
            },
            getTeachers() {
                this.loading = true;
                let _this = this;
                this.axios.get(this.API.URL.QUERY_ALL_TEACHERS)
                    .then(function (result) {
                        _this.loading = false;
                        let data = result.data;
                        if (data.success) {
                            _this.teachers = data.data
                        }
                    }, () => {
                        _this.loading = false;
                    })
            },
            clickDegreeItem(index) {
                this.listDegreeString = this.listDegrees[index];
                this.editedItem.degree = index
            },
            getClasses(item) {
                this.axios.get(this.API.URL.QUERY_CLASSES_BY_TEACHER_ID + item.id)
                    .then(function (result) {
                        let data = result.data;
                        if (data.success) {
                            item.classList = data.data
                        }
                    })
            }
        },
        created() {
            this.getTeachers()
        }
    }
</script>

<style scoped>

</style>