(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d230533"],{ec98:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pa-3"},[a("v-data-table",{staticClass:"elevation-2",attrs:{items:t.classes,loading:t.loading,search:t.search,headers:t.headers,"sort-by":"id","show-expand":""},scopedSlots:t._u([{key:"top",fn:function(){return[a("v-toolbar",{attrs:{flat:""}},[a("v-text-field",{staticClass:"me-2",attrs:{"append-icon":"mdi-magnify",label:"搜索",clearable:"","single-line":"","hide-details":""},model:{value:t.search,callback:function(e){t.search=e},expression:"search"}}),a("v-btn",{attrs:{color:"primary",disabled:t.loading,icon:""},on:{click:t.refresh}},[t.loading?a("v-progress-circular",{attrs:{color:"primary",size:"18",width:"2",indeterminate:""}}):a("v-icon",[t._v("mdi-refresh")])],1),a("v-btn",{attrs:{color:"primary",dark:"",icon:""},on:{click:function(e){t.dialog=!0}}},[a("v-icon",[t._v("mdi-plus")])],1)],1),a("v-divider",{staticClass:"mx-4",attrs:{inset:"",vertical:""}}),a("v-spacer"),a("v-dialog",{attrs:{"max-width":"500px",persistent:""},model:{value:t.dialog,callback:function(e){t.dialog=e},expression:"dialog"}},[a("v-card",[a("v-card-title",[a("span",{staticClass:"headline"},[t._v(t._s(t.formTitle))])]),a("v-card-text",[a("v-container",[a("v-row",[a("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[a("v-text-field",{attrs:{label:"班级收费/人（￥）"},model:{value:t.editedItem.cost,callback:function(e){t.$set(t.editedItem,"cost",e)},expression:"editedItem.cost"}})],1),a("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[a("v-text-field",{attrs:{type:"number",label:"班级人数"},model:{value:t.editedItem.number,callback:function(e){t.$set(t.editedItem,"number",e)},expression:"editedItem.number"}})],1),a("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[a("v-text-field",{attrs:{type:"number",label:"补习科目门数"},model:{value:t.editedItem.lessons,callback:function(e){t.$set(t.editedItem,"lessons",e)},expression:"editedItem.lessons"}})],1),a("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[a("v-text-field",{attrs:{label:"班级性质"},model:{value:t.editedItem.nature,callback:function(e){t.$set(t.editedItem,"nature",e)},expression:"editedItem.nature"}})],1)],1)],1)],1),a("v-card-actions",[a("v-spacer"),a("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:t.close}},[t._v("取消")]),a("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:t.save}},[t._v("保存")])],1)],1)],1)]},proxy:!0},{key:"item.actions",fn:function(e){var s=e.item;return[a("v-btn",{attrs:{icon:"",small:"",color:"warning"},on:{click:function(e){return t.editItem(s)}}},[a("v-icon",[t._v("mdi-pencil")])],1),a("v-btn",{attrs:{icon:"",small:"",color:"error"},on:{click:function(e){return t.deleteItem(s)}}},[a("v-icon",[t._v("mdi-delete")])],1)]}},{key:"expanded-item",fn:function(e){var s=e.headers,i=e.item;return[a("td",{staticClass:"ps-5 pe-5 pt-3 pb-3",attrs:{colspan:s.length}},[a("v-card",{attrs:{elevation:"1"}},[a("v-data-table",{attrs:{headers:t.teacherHeaders,items:i.teachers},scopedSlots:t._u([{key:"top",fn:function(){return[a("v-toolbar",{attrs:{flat:""}},[a("v-toolbar-title",[t._v(t._s(i.nature)+"的师资信息")]),a("v-spacer"),a("v-btn",{attrs:{icon:""},on:{click:function(e){return t.getTeachers(i)}}},[a("v-icon",[t._v("mdi-refresh")])],1),a("v-btn",{attrs:{icon:""},on:{click:function(e){t.teacherDialog=!0}}},[a("v-icon",[t._v("mdi-plus")])],1),a("v-dialog",{attrs:{"max-width":"500px",persistent:""},model:{value:t.teacherDialog,callback:function(e){t.teacherDialog=e},expression:"teacherDialog"}},[a("v-card",[a("v-card-title",[a("span",{staticClass:"headline"},[t._v("添加教师")])]),a("v-card-text",[a("v-container",[a("v-row",[a("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[a("v-text-field",{attrs:{label:"教师id"},model:{value:t.ctRelation.teacherId,callback:function(e){t.$set(t.ctRelation,"teacherId",e)},expression:"ctRelation.teacherId"}})],1)],1)],1)],1),a("v-card-actions",[a("v-spacer"),a("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:function(e){t.teacherDialog=!1}}},[t._v("取消 ")]),a("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:function(e){return t.insertTeacher(i)}}},[t._v("添加 ")])],1)],1)],1)],1),a("v-divider")]},proxy:!0},{key:"item.degree",fn:function(e){var s=e.item;return[a("v-chip",{attrs:{color:0===s.degree?"primary":"accent"}},[t._v(t._s(t.listDegrees[s.degree]))])]}},{key:"item.delete",fn:function(e){var s=e.item;return[a("v-btn",{attrs:{icon:"",small:"",color:"error"},on:{click:function(e){return t.deleteTeacher(i,s)}}},[a("v-icon",[t._v("mdi-delete")])],1)]}}],null,!0)})],1),a("v-card",{staticClass:"mt-3",attrs:{elevation:"1"}},[a("v-data-table",{attrs:{headers:t.studentHeaders,items:i.students},scopedSlots:t._u([{key:"top",fn:function(){return[a("v-toolbar",{attrs:{flat:""}},[a("v-toolbar-title",[t._v(t._s(i.nature)+"的学生信息")]),a("v-spacer"),a("v-btn",{attrs:{icon:""},on:{click:function(e){return t.getStudents(i)}}},[a("v-icon",[t._v("mdi-refresh")])],1),a("v-btn",{attrs:{icon:""},on:{click:function(e){t.studentDialog=!0}}},[a("v-icon",[t._v("mdi-plus")])],1),a("v-dialog",{attrs:{"max-width":"500px",persistent:""},model:{value:t.studentDialog,callback:function(e){t.studentDialog=e},expression:"studentDialog"}},[a("v-card",[a("v-card-title",[a("span",{staticClass:"headline"},[t._v("添加学生")])]),a("v-card-text",[a("v-container",[a("v-row",[a("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[a("v-text-field",{attrs:{label:"学生id"},model:{value:t.csRelation.studentId,callback:function(e){t.$set(t.csRelation,"studentId",e)},expression:"csRelation.studentId"}})],1)],1)],1)],1),a("v-card-actions",[a("v-spacer"),a("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:function(e){t.studentDialog=!1}}},[t._v("取消 ")]),a("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:function(e){return t.insertStudent(i)}}},[t._v("添加 ")])],1)],1)],1)],1),a("v-divider")]},proxy:!0},{key:"item.delete",fn:function(e){var s=e.item;return[a("v-btn",{attrs:{icon:"",small:"",color:"error"},on:{click:function(e){return t.deleteStudent(i,s)}}},[a("v-icon",[t._v("mdi-delete")])],1)]}}],null,!0)})],1)],1)]}}])})],1)},i=[],n=(a("c975"),a("a434"),{name:"Class",data:function(){return{listDegrees:["班主任","助教","任课教师"],teacherDialog:!1,ctRelation:{teacherId:0,classId:0},studentDialog:!1,csRelation:{studentId:0,classId:0},classes:[],loading:!0,search:"",dialog:!1,editedItem:{},editedIndex:-1,defaultItem:{},headers:[{text:"序号",value:"id"},{text:"班级收费/人",value:"cost"},{text:"班级人数",value:"number"},{text:"补习科目门数",value:"lessons"},{text:"班级性质",value:"nature"},{text:"操作",value:"actions"},{text:"更多信息",value:"data-table-expand"}],teacherHeaders:[{text:"序号",value:"id"},{text:"姓名",value:"name"},{text:"职称",value:"degree"},{text:"薪资/月（￥）",value:"salary"},{text:"联系方式",value:"phone"},{text:"删除",value:"delete"}],studentHeaders:[{text:"序号",value:"id"},{text:"姓名",value:"name"},{text:"家长联系方式",value:"phone"},{text:"删除",value:"delete"}]}},computed:{formTitle:function(){return-1===this.editedIndex?"添加":"编辑"}},watch:{dialog:function(t){t||this.close()}},methods:{save:function(){-1===this.editedIndex?this.insert(this.editedItem):this.update(this.editedItem)},insert:function(t){var e=this;this.axios.put(this.API.URL.INSERT_CLASS,t).then((function(t){var a=t.data;a.success&&(e.classes.push(a.data),e.close())}))},update:function(t){var e=this;this.axios.post(this.API.URL.UPDATE_CLASS,t).then((function(t){var a=t.data;a.success&&(Object.assign(e.classes[e.editedIndex],a.data),e.close())}))},deleteItem:function(t){var e=this;if(confirm("确定删除课程：\n"+t.id)){var a=this.classes.indexOf(t);this.axios.delete(this.API.URL.DELETE_CLASS+t.id).then((function(t){var s=t.data;s.success&&(alert("删除成功"),e.classes.splice(a,1))}))}},editItem:function(t){this.editedIndex=this.classes.indexOf(t),this.editedItem=Object.assign({},t),this.dialog=!0},close:function(){var t=this;this.dialog=!1,this.$nextTick((function(){t.editedItem=Object.assign({},t.defaultItem),t.editedIndex=-1}))},refresh:function(){this.getClasses()},getClasses:function(){this.loading=!0;var t=this;this.axios.get(this.API.URL.QUERY_ALL_CLASSES).then((function(e){t.loading=!1;var a=e.data;a.success&&(t.classes=a.data)}),(function(){t.loading=!1}))},getTeachers:function(t){this.axios.get(this.API.URL.QUERY_ALL_TEACHERS_BY_CLASS_ID+t.id).then((function(e){var a=e.data;a.success&&(t.teachers=a.data)}))},getStudents:function(t){this.axios.get(this.API.URL.QUERY_ALL_STUDENTS_BY_CLASS_ID+t.id).then((function(e){var a=e.data;a.success&&(t.students=a.data.students)}))},insertTeacher:function(t){var e=this;this.axios.put(this.API.URL.INSERT_TEACHERS+t.id+"&teachers="+[this.ctRelation.teacherId]).then((function(a){var s=a.data;s.success&&(t.teachers=s.data,e.teacherDialog=!1)}))},insertStudent:function(t){var e=this;this.axios.put(this.API.URL.INSERT_STUDENTS+t.id+"&students="+[this.csRelation.studentId]).then((function(a){var s=a.data;s.success&&(t.students=s.data,e.studentDialog=!1)}))},deleteTeacher:function(t,e){var a=this;this.axios.delete(this.API.URL.DELETE_TEACHERS+t.id+"&teachers="+[e.id]).then((function(s){var i=s.data;i.success&&(t.teachers.splice(t.teachers.indexOf(e),1),a.studentDialog=!1)}))},deleteStudent:function(t,e){var a=this;this.axios.delete(this.API.URL.DELETE_STUDENTS+t.id+"&students="+[e.id]).then((function(s){var i=s.data;i.success&&(t.students.splice(t.students.indexOf(e),1),a.studentDialog=!1)}))}},created:function(){this.getClasses()}}),c=n,r=a("2877"),l=a("6544"),o=a.n(l),d=a("8336"),u=a("b0af"),v=a("99d9"),h=a("cc20"),f=a("62ad"),m=a("a523"),x=a("8fea"),p=a("169a"),b=a("ce7e"),g=a("132d"),I=a("490a"),_=a("0fd9"),k=a("2fa4"),S=a("8654"),T=a("71d9"),C=a("2a7f"),D=Object(r["a"])(c,s,i,!1,null,"0a3a3a03",null);e["default"]=D.exports;o()(D,{VBtn:d["a"],VCard:u["a"],VCardActions:v["a"],VCardText:v["b"],VCardTitle:v["c"],VChip:h["a"],VCol:f["a"],VContainer:m["a"],VDataTable:x["a"],VDialog:p["a"],VDivider:b["a"],VIcon:g["a"],VProgressCircular:I["a"],VRow:_["a"],VSpacer:k["a"],VTextField:S["a"],VToolbar:T["a"],VToolbarTitle:C["a"]})}}]);
//# sourceMappingURL=chunk-2d230533.63649448.js.map