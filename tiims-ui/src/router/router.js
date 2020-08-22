import Vue from 'vue'
import VueRouter from 'vue-router'
import Header from "../components/Header"
import Footer from "../components/Footer"

Vue.use(VueRouter);

export const constantRouters = [
    {
        path: "",
        redirect: "/admin"
    },
    {
        path: "/admin",
        redirect: "/admin/home"
    },
    {
        path: "/admin/home",
        components: {
            header: Header,
            default: () => import("../components/admin/Home"),
            footer: Footer
        },
        meta: {
            title: "首页"
        }
    },
    {
        path: "/admin/class",
        components: {
            header: Header,
            default: () => import("../components/admin/Class"),
            footer: Footer
        },
        meta: {
            title: "班级管理"
        }
    },
    {
        path: "/admin/student",
        components: {
            header: Header,
            default: () => import("../components/admin/Student"),
            footer: Footer
        },
        meta: {
            title: "学生信息"
        }
    },
    {
        path: "/admin/teacher",
        components: {
            header: Header,
            default: () => import("../components/admin/Teacher"),
            footer: Footer
        },
        meta: {
            title: "师资信息"
        }
    },
    {
        path: "/admin/work",
        components: {
            header: Header,
            default: () => import("../components/admin/Work"),
            footer: Footer
        },
        meta: {
            title: "出勤信息"
        }
    },
    {
        path: "/admin/login",
        component: () => import("../components/admin/Login")
    },
    {
        path: "*",
        component: () => import("../components/NotFound"),
    },
    {
        path: "/404",
        name: "404",
        component: () => import("../components/NotFound"),
    }
];


export default new VueRouter({
    routes: constantRouters,
    scrollBehavior(to, from, saveTop) {
        if (saveTop) {
            return saveTop;
        } else {
            return {x: 0, y: 0}
        }
    }
})