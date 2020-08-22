import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router/router'
import url from './utils/api'
import util from './utils/util'
import axios from 'axios'


//请求返回拦截，把数据返回到页面之前做些什么...
axios.interceptors.response.use((response) => {
    //特殊错误处理，状态为10时为登录超时
    let errorCode = response.data.errorCode;
    let errorMsg = response.data.errorMsg;
    if (errorCode === 2001 || errorCode === 2002 || errorCode === 2004 || errorCode === 2005 || errorCode === 2006 || errorCode === 2009) {
        sessionStorage.removeItem("state");
        alert(errorMsg);
        router.replace({path: "/admin/login"})
        //其余错误状态处理
    } else if (errorCode !== 200) {
        alert(errorMsg);
        return response
        //请求成功
    } else {
        //将我们请求到的信息返回页面中请求的逻辑
        return response
    }
}, function (error) {
    console.log(error);
    return Promise.reject(error)
});
axios.defaults.withCredentials = true; //意思是携带cookie信息,保持session的一致性

Vue.prototype.axios = axios;
Vue.prototype.API = url;
Vue.prototype.UTIL = util;
Vue.config.productionTip = false;


router.beforeEach((to, from, next) => {
        let path = to.path;
        if (path === "/admin/login") {
            next()
        } else {
            if (sessionStorage.getItem("state") === "logined") {
                next()
            } else {
                next({path: "/admin/login"})
            }
        }
    }
);

new Vue({
    vuetify,
    router,
    render: h => h(App),
}).$mount('#app');
