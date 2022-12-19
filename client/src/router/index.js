import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login'

import Staff from '@/views/Staff'

import AdminMenu from "@/components/AdminMenu";
import DataRecord from "@/views/AdminViews/DataRecord";
import ManageOne from "@/views/AdminViews/ManageOne";
import AddStaff from "@/views/AdminViews/AddStaff";
import UpdateOne from "@/views/AdminViews/UpdateOne";
import ManageTwo from "@/views/AdminViews/ManageTwo";
import AddManager from "@/views/AdminViews/AddManager";
import UpdateTwo from "@/views/AdminViews/UpdateTwo";
import AddAdmin from "@/views/AdminViews/AddAdmin";
import DepartmentManage from "@/views/AdminViews/DepartmentManage";
import AddDepartment from "@/views/AdminViews/AddDepartment";
import UpdateDepartment from "@/views/AdminViews/UpdateDepartment";
import RecordOne from "@/views/AdminViews/RecordOne";
import UpdateStaffRecord from "@/views/AdminViews/UpdateStaffRecord";
import RecordTwo from "@/views/AdminViews/RecordTwo";
import UpdateManagerRecord from "@/views/AdminViews/UpdateManagerRecord";
import EvaluateOne from "@/views/AdminViews/EvaluateOne";
import EvaluateTwo from "@/views/AdminViews/EvaluateTwo";
import ChangeOne from "@/views/AdminViews/ChangeOne";
import ChangeTwo from "@/views/AdminViews/ChangeTwo";
import DocumentShare from "@/views/AdminViews/DocumentShare";
import Mail from "@/views/AdminViews/Mail";

import StaffMenu from "@/components/StaffMenu";
import StaffInformation from "@/views/StaffViews/StaffInformation";
import StaffAttendance from "@/views/StaffViews/StaffAttendance";
import StaffSalary from "@/views/StaffViews/StaffSalary";
import StaffDocument from "@/views/StaffViews/StaffDocument";
import StaffMail from "@/views/StaffViews/StaffMail";

import ManagerMenu from "@/components/ManagerMenu";
import ManagerInformation from "@/views/ManagerViews/ManagerInformation";
import ManagerAttendance from "@/views/ManagerViews/ManagerAttendance";
import AttendanceManage from "@/views/ManagerViews/AttendanceManage";
import UpdateRecord from "@/views/ManagerViews/UpdateRecord";
import ManagerSalary from "@/views/ManagerViews/ManagerSalary";
import DepartmentSalary from "@/views/ManagerViews/DepartmentSalary";
import ManagerDocument from "@/views/ManagerViews/ManagerDocument";
import ManagerMail from "@/views/ManagerViews/ManagerMail";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: Login
    },
    {
        path: '/Staff',
        component: Staff
    },
    {
        path: '/AdminMenu',
        component: AdminMenu,
        children: [
            {
                path: "/DataRecord",
                component: DataRecord,
            },
            {
                path: "/ManageOne",
                component: ManageOne,
            },
            {
                path: "/AddStaff",
                component: AddStaff,
            },
            {
                path: "/UpdateOne",
                component: UpdateOne,
            },
            {
                path: "/ManageTwo",
                component: ManageTwo,
            },
            {
                path: "/AddManager",
                component: AddManager,
            },
            {
                path: "/UpdateTwo",
                component: UpdateTwo,
            },
            {
                path:"/AddAdmin",
                component: AddAdmin,
            },
            {
                path: "/DepartmentManage",
                component: DepartmentManage,
            },
            {
                path: "/AddDepartment",
                component: AddDepartment,
            },
            {
                path: "/UpdateDepartment",
                component: UpdateDepartment
            },
            {
                path: "/RecordOne",
                component: RecordOne,
            },
            {
                path: "/UpdateStaffRecord",
                component: UpdateStaffRecord,
            },
            {
                path: "/RecordTwo",
                component: RecordTwo,
            },
            {
                path:"/UpdateManagerRecord",
                component:UpdateManagerRecord,
            },
            {
                path: "/EvaluateOne",
                component: EvaluateOne,
            },
            {
                path: "/EvaluateTwo",
                component: EvaluateTwo,
            },
            {
                path: "/ChangeOne",
                component: ChangeOne,
            },
            {
                path: "/ChangeTwo",
                component: ChangeTwo,
            },
            {
                path: "/DocumentShare",
                component: DocumentShare,
            },
            {
                path: "/Mail",
                component: Mail,
            }
        ]
    },
    {
        path: '/StaffMenu',
        component: StaffMenu,
        children: [
            {
                path: "/StaffInformation",
                component: StaffInformation,
            },
            {
                path: "/StaffAttendance",
                component: StaffAttendance,
            },
            {
                path: "/StaffSalary",
                component: StaffSalary,
            },
            {
                path: "/StaffDocument",
                component: StaffDocument,
            },
            {
                path: "/StaffMail",
                component: StaffMail,
            }
        ],
    },
    {
        path: "/ManagerMenu",
        component: ManagerMenu,
        children: [
            {
                path: "/ManagerInformation",
                component: ManagerInformation,
            },
            {
                path: "/ManagerAttendance",
                component: ManagerAttendance,
            },

            {
                path: "/AttendanceManage",
                component: AttendanceManage,
            },
            {
                path: "/UpdateRecord",
                component: UpdateRecord,
            },
            {
                path: "/ManagerSalary",
                component: ManagerSalary,
            },
            {
                path: "/DepartmentSalary",
                component: DepartmentSalary,
            },
            {
                path: "/ManagerDocument",
                component: ManagerDocument,
            },
            {
                path: "/ManagerMail",
                component: ManagerMail,
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next)=>{
    console.log(to);
    let token = localStorage.getItem("id");
    if(token || to.path === "/") {
        next();
    }
    else {
        next("/");
    }
})

export default router
