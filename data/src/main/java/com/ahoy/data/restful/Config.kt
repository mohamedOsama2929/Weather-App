package com.ahoy.data.restful


object Config {
    const val BASE_URL = "https://api.weatherapi.com/v1/"
    const val FORECAST = "forecast.json"
    const val SEARCH = "search.json"

    const val ID = "id"
    const val PATH_ID = "{$ID}"
    const val REQUEST_ID = "requestId"
    const val STORAGE_TYPE = "storageType"
    const val PARENT_SERVICE_ID = "parentServiceId"
    const val IS_PUBLIC = "isPublic"
    const val DATA_SOURCE_BASE_URL = "dataSourceBaseUrl"
    const val DATA_SOURCE_URL = "dataSourceUrl"
    const val LEAVE_TYPE = "LeaveType"
    const val SCHEDULE_CODE = "ScheduleCode"
    const val START_DATE = "StartDate"
    const val END_DATE = "EndDate"
    const val LEAVE_TYPE_CODE = "LeaveTypeCode"
    const val KEYWORD = "keyword"
    const val BUSINESS_UNIT_NAME = "businessUnitName"

    const val PATH_CASCADING_BASE_URL = "{$DATA_SOURCE_BASE_URL}/"
    const val PATH_REQUEST_ID = "{$REQUEST_ID}"
    const val PATH_STORAGE_TYPE = "{$STORAGE_TYPE}"
    const val PATH_PARENT_SERVICE_ID = "{$PARENT_SERVICE_ID}"
    const val PATH_LIST_OF_VALUES_DATA_SOURCE_URL = "{$DATA_SOURCE_URL}"

    const val Leaves = "leaves/leave-balance"
    const val UpcomingVacations = "leaves/upcoming-holidays-and-vacations"
    const val TeamAbsence = "leaves/team-absence"
    const val LEAVES_EMPLOYEE_CALENDAR = "leaves/employee-calendar"
    const val GetLeavesConfig = "leaves/leaves-config"
    const val HOLIDAY_CALENDAR = "leaves/holiday-calendar"
    const val GET_LEAVE_CONFIG = "leaves/leaves-config"
    const val GET_LEAVE_DAYS = "leaves/requesting-days"
    const val LEAVES_EMPLOYEE_SEARCH = "leaves/search-users"
    const val ADD_EMPLOYEE_LEAVE = "leaves/add-request"


    const val NEWS = "News"
    const val BOOKMARKED_NEWS = "News/GetBookmarkedNews"
    const val NEWS_DETAILS = "News/"
    const val CHANGE_FAVORITE_NEWS_STATUS = "News/BookmarkNews/"

    const val ANNOUNCEMENT = "Announcement"
    const val ANNOUNCEMENT_DETAILS = "Announcement/"
    const val BOOKMARKED_ANNOUNCEMENT = "Announcement/GetBookmarkedAnnouncment"
    const val CHANGE_FAVORITE_ANNOUNCEMENT_STATUS = "Announcement/BookmarkAnnouncement/"

    const val INTERNAL_NEWS = "InternalNews"
    const val BOOKMARKED_INTERNAL_NEWS = "InternalNews/GetBookmarkedNews"
    const val INTERNAL_NEWS_DETAILS = "InternalNews".plus("/").plus(PATH_ID)
    const val CHANGE_INTERNAL_FAVORITE_NEWS_STATUS = "InternalNews/BookmarkNews/".plus(PATH_ID)

    const val EVENT_LIST = "Event/GetEventGrid"
    const val EVENT_DETAILS = "Event/Get/"

    const val OFFERS_LIST = "Offers/HomeOffersList"
    const val OFFER_DETAILS = "Offers/OfferDetails"
    const val OFFERS_CATEGORIES = "Offers/OfferCategories"
    const val USER_PROFILE = "UserProfile/User/me"
    const val PAYROLL_INFO = "UserProfileDetails/GetPayrollInfo"
    const val CONTACT_ADDRESS = "UserProfileDetails/GetContactAndAddress"
    const val EMERGENCY_CONTACT = "UserProfileDetails/GetEmergencyContact"
    const val CERTIFICATES = "UserProfileDetails/GetCertificates"
    const val DEPENDANTS = "UserProfileDetails/GetDependents"
    const val EDUCATION = "UserProfileDetails/GetEducationHistory"
    const val WORK_EXPERIENCE = "UserProfileDetails/GetWorkExperience"
    const val EMPLOYMENT_DETAILS = "UserProfileDetails/EmploymentDetails"

    //    const val GET_ATTACHMENTS_USER_PROFILE_DETAILS = "UserProfileDetails/GetAttachment"
    const val GET_ATTACHMENTS_USER_PROFILE_DETAILS = "v2/UserProfileDetails/GetAttachment"


    const val LOGIN = "login"
    const val OTP_TOKEN = "token"
    const val REFRESH_TOKEN = "token/refresh"

    const val SERVICE_CATEGORIES = "Categories/GetAll"
    const val SERVICE_SUBCATEGORIES = "Categories/GetBySubCategory"
    const val SUBSERVICES =
        "SelfServices/GetSubServicesByServiceId".plus("/").plus(PATH_PARENT_SERVICE_ID)
    const val GET_ALL_SERVICES = "SelfServices/GetAll"
    const val FAVORITE_SERVICES = "SelfServices/GetUserFavoriteServices"
    const val ADD_FAVORITE_SERVICE = "UserServices/Add"

    const val SERVICES_SEARCH = "SelfServices/Search"
    const val EMPLOYEE_SEARCH = "Users/GetUsers"
    const val EMPLOYEE_SEARCH_IAM = "UserProfileDetails/user-info/{mangerName}"
    const val GET_LIST_BY_CODE = "List/GetByListCode/{code}"

    // cooperate performance
    const val KPI_CODE = "List/GetByListCode/{KPICodes}"
    const val KPI_DEPARTMENT = "lookups/departments"


    const val EMPLOYEE_PROFILE_VIEW = "UserProfile/User/GetUserProfileFromAd/{email}"

    const val MY_TASKS = "Tasks/GetAllPaged"
    const val TASK_DETAILS = "Tasks/GetById".plus("/").plus(PATH_ID)
    const val EXECUTE_ACTION = "Tasks/ExecuteAction"

    const val MY_REQUESTS = "Request/GetAllPaged"
    const val REQUEST_SERVICE = "SelfServices/RequestService".plus("/").plus(PATH_ID)
    const val INITIATE_REQUEST = "Request/InitiateRequest".plus("/").plus(PATH_ID)
    const val REOPEN_REQUEST = "Request/ReopenRequest"
    const val GET_REQUEST_BY_ID_STATUS = "Request/GetByStatusId".plus("/").plus(PATH_ID)
    const val GET_TASK_BY_ID_STATUS = "Tasks/GetUserTasksAsync".plus("/").plus(PATH_ID)
    const val VALIDATE_SERVICE = "SelfServices/ValidateService".plus("/").plus(PATH_ID)
    const val VALIDATE_COMPLAINS_SERVICE =
        "employee-evaluation/validate-employee-grievance-and-complains".plus("/")
            .plus(PATH_ID)
    const val GET_EMPLOYEE_GRIEVANCE_AND_COMPLAINS =
        "employee-evaluation/employee-evaluations".plus("/").plus(PATH_ID)

    const val SUBMIT_ADDITIONAL_DATA = "Request/SubmitRequestAdditionalForm"

    const val REQUEST_DETAILS = "Request/GetById".plus("/").plus(PATH_ID)
    const val REQUEST_COMMENTS = "RequestComments/GetByRequestId".plus("/").plus(PATH_REQUEST_ID)
    const val ADD_MESSAGE = "RequestComments/Add"
    const val WITHDRAW_REQUEST = "Request/WithdrawRequest"

    const val GET_NOTIFICATIONS_LIST = "GetNotificationList"
    const val SET_NOTIFICATION_AS_OPEN = "SetNotificationAsOpen".plus("/").plus(PATH_ID)
    const val UnOpendNotificationCount = "UnOpendNotificationCount"
    const val VISIBLE_SERVICES = "SelfServices/GetVisibleServices"

    const val UPLOAD_FILE = "FileManager/UploadFiles".plus("/").plus(PATH_STORAGE_TYPE)
    const val EMPLOYEE_DETAILS_BY_ID = "UserProfileDetails/EmployeeDetailsById"
    const val BUSINESS_UNIT = "UserProfileDetails/GetBusinessUnitsList"
    const val DEPARTMENTS = "UserProfileDetails/GetDepartmentsList"
    const val ADD_UPDATE_PORTAL = "delegations/add-update-portal"


    // Delegated Persons List

    const val GET_DELEGATION_LIST = "delegations/user-delegations"

    // Assignees
    const val GET_ASSIGNEES_LIST = "Tasks/task-assignee/"
    const val IS_ASSIGN_USER = "isAssignUser"

    // register Unregister device

    const val REGISTER_DEVICE = "NotificationRegister/register-device"
    const val UNREGISTER_DEVICE = "NotificationRegister/unregister-device"

    const val EDIT_SERVICES_RESUBMIT_REQUEST = "Request/ResubmitRequest"

    /** Career List **/
    const val CareerList = "job/search-portal"
    const val CAREER_DETAILS = "job/for-portal".plus("/").plus(PATH_ID)

    const val CAREER_LIST = "job/search-portal"
    const val CAREER_JOP_OLD_YEAR = "job/old-year"
    const val JOB_APPLICATION = "job-application"

    // Reassign Delegation
    const val REASSIGN_DELEGATION = "delegations/Delegate"
    const val ATTENDANCE = "UserProfileDetails/GetAttendance"

    // IAM Service
    const val VALIDATE_DIRECT_MANAGER =
        "UserProfileDetails/validate-user".plus("/").plus(PATH_ID)
    const val USER_INFO =
        "UserProfileDetails/users-info".plus("/").plus(PATH_ID)

    const val IAM_BUSINESS_UNIT = "UserProfileDetails/business-units-list"
    const val IAM_DEPARTMENTS = "UserProfileDetails/departments-list"
    const val LOCATIONS = "UserProfileDetails/locations"
    const val DDL_BUSINESS_UNIT = "List/GetByListCode".plus("/").plus(PATH_ID)
    const val DEPENDENTS_FOR_EDUCATION = "UserProfileDetails/GetDependentsForEducationAllowance"

    //professional certificate
    const val CERTIFICATE_LIST = "List/GetByListCode/CertificateList"

}