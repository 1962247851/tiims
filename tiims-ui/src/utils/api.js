// const PER_PAGE_SIZE = 10;
const API_BASE_URL = "https://api.ordinaryroad.top:711";
// const API_BASE_URL = "https://localhost:711";
const INSERT = "/insert";
const DELETE = "/delete";
const UPDATE = "/update";
const QUERY = "/query";
const CLASS = API_BASE_URL + "/class";
const TEACHER = API_BASE_URL + "/teacher";
const STUDENT = API_BASE_URL + "/student";
const WORK = API_BASE_URL + "/work";
const CAPTCHA_IMAGE = API_BASE_URL + "/captchaImage";
const USER = API_BASE_URL + "/user";

const URL = {
    //验证码接口
    CAPTCHA_IMAGE: CAPTCHA_IMAGE,

    //用户接口
    USER_LOGIN: USER + "/login",
    USER_LOGOUT: USER + "/logout",

    //班级信息接口
    INSERT_CLASS: CLASS + INSERT,
    DELETE_CLASS: CLASS + DELETE + "?id=",
    UPDATE_CLASS: CLASS + UPDATE,
    QUERY_ALL_CLASSES: CLASS + QUERY,
    QUERY_CLASS: CLASS + QUERY + "?id=",
    QUERY_CLASSES_BY_TEACHER_ID: CLASS + "/queryByTeacherId?teacherId=",
    QUERY_CLASSES_BY_STUDENT_ID: CLASS + "/queryByStudentId?studentId=",
    INSERT_TEACHERS: CLASS + "/insertTeachers?id=",
    INSERT_STUDENTS: CLASS + "/insertStudents?id=",
    DELETE_TEACHERS: CLASS + "/deleteTeachers?id=",
    DELETE_STUDENTS: CLASS + "/deleteStudents?id=",

    //学生信息接口
    INSERT_STUDENT: STUDENT + INSERT,
    DELETE_STUDENT: STUDENT + DELETE + "?id=",
    UPDATE_STUDENT: STUDENT + UPDATE,
    QUERY_ALL_STUDENTS: STUDENT + QUERY,
    QUERY_STUDENT: STUDENT + QUERY + "?id=",
    QUERY_ALL_STUDENTS_BY_CLASS_ID: CLASS + "/queryStudents?page=0&size=999999999&classId=",

    //师资信息接口
    INSERT_TEACHER: TEACHER + INSERT,
    DELETE_TEACHER: TEACHER + DELETE + "?id=",
    UPDATE_TEACHER: TEACHER + UPDATE,
    QUERY_ALL_TEACHERS: TEACHER + QUERY,
    QUERY_TEACHER: TEACHER + QUERY + "?id=",
    QUERY_ALL_TEACHERS_BY_CLASS_ID: CLASS + "/queryTeachers?page=0&size=999999999&classId=",

    //出勤信息接口
    INSERT_WORK: WORK + INSERT,
    DELETE_WORK: WORK + DELETE + "?id=",
    UPDATE_WORK: WORK + UPDATE,
    QUERY_ALL_WORKS: WORK + QUERY,
    QUERY_WORK: WORK + QUERY + "?id=",
};
module.exports = {
    API_BASE_URL: API_BASE_URL,
    URL: URL
};