<template>
    <div class="pa-3">
        <v-data-table :items="classes" :loading="loading" :search="search"
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
                                        <v-text-field v-model="editedItem.cost" label="班级收费/人（￥）"/>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field type="number" v-model="editedItem.number" label="班级人数"/>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field type="number" v-model="editedItem.lessons" label="补习科目门数"/>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field v-model="editedItem.nature" label="班级性质"/>
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

            <template v-slot:item.actions="{ item }">
                <v-btn icon small @click="editItem(item)" color="warning">
                    <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon small @click="deleteItem(item)" color="error">
                    <v-icon>mdi-delete</v-icon>
                </v-btn>
            </template>

            <template v-slot:expanded-item="{headers, item}">
                <td :colspan="headers.length" class="ps-5 pe-5 pt-3 pb-3">
                    <v-card elevation="1">
                        <v-data-table :headers="teacherHeaders" :items="item.teachers">

                            <template v-slot:top>
                                <v-toolbar flat>
                                    <v-toolbar-title>{{item.nature}}的师资信息</v-toolbar-title>
                                    <v-spacer/>
                                    <v-btn icon @click="getTeachers(item)">
                                        <v-icon>mdi-refresh</v-icon>
                                    </v-btn>
                                    <v-btn icon @click="teacherDialog = true">
                                        <v-icon>mdi-plus</v-icon>
                                    </v-btn>
                                    <v-dialog v-model="teacherDialog" max-width="500px" persistent>
                                        <v-card>
                                            <v-card-title>
                                                <span class="headline">添加教师</span>
                                            </v-card-title>

                                            <v-card-text>
                                                <v-container>
                                                    <v-row>
                                                        <v-col cols="12" sm="6" md="4">
                                                            <v-text-field v-model="ctRelation.teacherId" label="教师id"/>
                                                        </v-col>
                                                    </v-row>
                                                </v-container>
                                            </v-card-text>

                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn color="blue darken-1" text @click="teacherDialog=false">取消
                                                </v-btn>
                                                <v-btn color="blue darken-1" text @click="insertTeacher(item)">添加
                                                </v-btn>
                                            </v-card-actions>
                                        </v-card>
                                    </v-dialog>
                                </v-toolbar>
                                <v-divider/>
                            </template>

                            <template v-slot:item.degree="{ item }">
                                <v-chip :color="item.degree===0?'primary':'accent'">{{listDegrees[item.degree]}}</v-chip>
                            </template>

                            <template v-slot:item.delete="{ item:teacher }">
                                <v-btn icon small @click="deleteTeacher(item,teacher)" color="error">
                                    <v-icon>mdi-delete</v-icon>
                                </v-btn>
                            </template>

                        </v-data-table>
                    </v-card>

                    <v-card class="mt-3" elevation="1">
                        <v-data-table :headers="studentHeaders" :items="item.students">

                            <template v-slot:top>
                                <v-toolbar flat>
                                    <v-toolbar-title>{{item.nature}}的学生信息</v-toolbar-title>
                                    <v-spacer/>
                                    <v-btn icon @click="getStudents(item)">
                                        <v-icon>mdi-refresh</v-icon>
                                    </v-btn>
                                    <v-btn icon @click="studentDialog = true">
                                        <v-icon>mdi-plus</v-icon>
                                    </v-btn>
                                    <v-dialog v-model="studentDialog" max-width="500px" persistent>
                                        <v-card>
                                            <v-card-title>
                                                <span class="headline">添加学生</span>
                                            </v-card-title>

                                            <v-card-text>
                                                <v-container>
                                                    <v-row>
                                                        <v-col cols="12" sm="6" md="4">
                                                            <v-text-field v-model="csRelation.studentId" label="学生id"/>
                                                        </v-col>
                                                    </v-row>
                                                </v-container>
                                            </v-card-text>

                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn color="blue darken-1" text @click="studentDialog=false">取消
                                                </v-btn>
                                                <v-btn color="blue darken-1" text @click="insertStudent(item)">添加
                                                </v-btn>
                                            </v-card-actions>
                                        </v-card>
                                    </v-dialog>
                                </v-toolbar>
                                <v-divider/>
                            </template>

                            <template v-slot:item.delete="{ item:student }">
                                <v-btn icon small @click="deleteStudent(item,student)" color="error">
                                    <v-icon>mdi-delete</v-icon>
                                </v-btn>
                            </template>

                        </v-data-table>
                    </v-card>
                </td>
            </template>

        </v-data-table>
    </div>
</template>

<script>
    export default {
        name: "Class",
        data() {
            return {
                listDegrees: ["班主任", "助教", "任课教师"],

                teacherDialog: false,
                ctRelation: {
                    teacherId: 0,
                    classId: 0
                },

                studentDialog: false,
                csRelation: {
                    studentId: 0,
                    classId: 0
                },

                classes: [],
                loading: true,
                search: "",
                dialog: false,
                editedItem: {},
                editedIndex: -1,
                defaultItem: {},

                headers: [
                    {text: '序号', value: 'id'},
                    {text: '班级收费/人', value: 'cost'},
                    {text: '班级人数', value: 'number'},
                    {text: '补习科目门数', value: 'lessons'},
                    {text: '班级性质', value: 'nature'},
                    {text: '操作', value: 'actions'},
                    {text: '更多信息', value: 'data-table-expand'},
                ],

                teacherHeaders: [
                    {text: '序号', value: 'id'},
                    {text: '姓名', value: 'name'},
                    {text: '职称', value: 'degree'},
                    {text: '薪资/月（￥）', value: 'salary'},
                    {text: '联系方式', value: 'phone'},
                    {text: '删除', value: 'delete'},
                ],
                studentHeaders: [
                    {text: '序号', value: 'id'},
                    {text: '姓名', value: 'name'},
                    {text: '家长联系方式', value: 'phone'},
                    {text: '删除', value: 'delete'},
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
                this.axios.put(this.API.URL.INSERT_CLASS, item).then(value => {
                    let data = value.data;
                    if (data.success) {
                        this.classes.push(data.data);
                        this.close()
                    }
                })
            },
            update(item) {
                this.axios.post(this.API.URL.UPDATE_CLASS, item).then(value => {
                    let data = value.data;
                    if (data.success) {
                        Object.assign(this.classes[this.editedIndex], data.data);
                        this.close()
                    }
                })
            },
            deleteItem(item) {
                if (confirm("确定删除课程：\n" + item.id)) {
                    const index = this.classes.indexOf(item);
                    this.axios.delete(this.API.URL.DELETE_CLASS + item.id).then(result => {
                        let data = result.data;
                        if (data.success) {
                            alert("删除成功");
                            this.classes.splice(index, 1)
                        }
                    })
                }
            },
            editItem(item) {
                this.editedIndex = this.classes.indexOf(item);
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
                this.getClasses()
            },
            getClasses() {
                this.loading = true;
                let _this = this;
                this.axios.get(this.API.URL.QUERY_ALL_CLASSES)
                    .then(function (result) {
                        _this.loading = false;
                        let data = result.data;
                        if (data.success) {
                            _this.classes = data.data
                        }
                    }, () => {
                        _this.loading = false;
                    })
            },
            getTeachers(item) {
                this.axios.get(this.API.URL.QUERY_ALL_TEACHERS_BY_CLASS_ID + item.id)
                    .then(function (result) {
                        let data = result.data;
                        if (data.success) {
                            item.teachers = data.data;
                        }
                    })
            },
            getStudents(item) {
                this.axios.get(this.API.URL.QUERY_ALL_STUDENTS_BY_CLASS_ID + item.id)
                    .then(function (result) {
                        let data = result.data;
                        if (data.success) {
                            item.students = data.data.students;
                        }
                    })
            },
            insertTeacher(item) {
                let _this = this;
                this.axios.put(this.API.URL.INSERT_TEACHERS + item.id + "&teachers=" + [this.ctRelation.teacherId])
                    .then(function (result) {
                        let data = result.data;
                        if (data.success) {
                            item.teachers = data.data;
                            _this.teacherDialog = false
                        }
                    })
            },
            insertStudent(item) {
                let _this = this;
                this.axios.put(this.API.URL.INSERT_STUDENTS + item.id + "&students=" + [this.csRelation.studentId])
                    .then(function (result) {
                        let data = result.data;
                        if (data.success) {
                            item.students = data.data;
                            _this.studentDialog = false
                        }
                    })
            },
            deleteTeacher(item, teacher) {
                let _this = this;
                this.axios.delete(this.API.URL.DELETE_TEACHERS + item.id + "&teachers=" + [teacher.id])
                    .then(function (result) {
                        let data = result.data;
                        if (data.success) {
                            item.teachers.splice(item.teachers.indexOf(teacher), 1);
                            _this.studentDialog = false
                        }
                    })
            },
            deleteStudent(item, student) {
                let _this = this;
                this.axios.delete(this.API.URL.DELETE_STUDENTS + item.id + "&students=" + [student.id])
                    .then(function (result) {
                        let data = result.data;
                        if (data.success) {
                            item.students.splice(item.students.indexOf(student), 1);
                            _this.studentDialog = false
                        }
                    })
            },

        },
        created() {
            this.getClasses()
        }
    }
</script>

<style scoped>

</style>