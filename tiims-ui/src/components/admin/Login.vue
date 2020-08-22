<template>
    <v-app>
        <v-main>
            <v-img src="../../assets/tiims_bg.png" style="width: 100%; height: 100%">
                <v-container class="fill-height" fluid>
                    <v-row align="center" justify="center">
                        <v-col cols="12" sm="8" md="4">
                            <v-card class="elevation-12">
                                <v-toolbar color="primary" dark flat>
                                    <v-toolbar-title>培训机构信息管理系统</v-toolbar-title>
                                </v-toolbar>
                                <v-card-text>
                                    <v-text-field label="用户名" prepend-icon="mdi-account" type="text"
                                                  v-model="username"/>
                                    <v-text-field label="密码" prepend-icon="mdi-lock" type="password"
                                                  v-model="password"/>
                                    <div class="d-flex ma-0 pa-0">
                                        <v-text-field class="d-inline-flex" label="验证码" hide-details
                                                      prepend-icon="mdi-lock" v-model="code"
                                                      @keydown="codeKeydown"/>
                                        <v-img class="d-inline-flex ms-5 me-5" :height="50"
                                               :src="src" @click="getCaptchaImage" contain>
                                            <template v-slot:placeholder>
                                                <v-row class="fill-height ma-0" align="center" justify="center">
                                                    <v-progress-circular indeterminate color="primary"/>
                                                </v-row>
                                            </template>
                                        </v-img>
                                    </div>
                                </v-card-text>
                                <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn color="primary" @click="login">登录</v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-col>
                    </v-row>

                </v-container>
            </v-img>
        </v-main>
    </v-app>
</template>

<script>
    export default {
        name: "Login",
        props: {
            source: String
        },
        data: function () {
            return {
                username: "",
                password: "",
                code: "",
                uuid: "",
                src: ""
            }
        },
        mounted() {
            if (sessionStorage.getItem("state") === "logined") {
                this.$router.replace({path: "/admin/home"})
            } else {
                this.getCaptchaImage()
            }
        },
        methods: {
            login() {
                if (this.username.length === 0 || this.password.length === 0 || this.code.length === 0) {
                    alert("请补全输入")
                } else {
                    let _this = this
                    this.axios.post(this.API.URL.USER_LOGIN + "?username=" + this.username + "&password=" + this.password + "&code=" + this.code + "&uuid=" + this.uuid)
                        .then(value => {
                            let data = value.data
                            if (data.success) {
                                alert("登录成功")
                                sessionStorage.setItem("state", "logined")
                                sessionStorage.setItem("username", _this.username)
                                this.$router.replace({path: "/admin/home"})
                            } else {
                                this.getCaptchaImage()
                            }
                        }, reason => {
                            alert(reason)
                            console.log(reason)
                        })
                }
            },
            getCaptchaImage() {
                this.code = ""
                let _this = this
                this.axios.get(this.API.URL.CAPTCHA_IMAGE).then(value => {
                    let data = value.data;
                    if (data.success) {
                        _this.src = "data:image/jpeg;base64," + data.data.img
                        _this.uuid = data.data.uuid
                    }
                    // console.log(value)
                }, reason => {
                    console.log(reason)
                })
            },
            codeKeydown(res) {
                if (res.which === 13) {
                    this.login()
                }
            }
        }
    }
</script>

<style scoped>

</style>