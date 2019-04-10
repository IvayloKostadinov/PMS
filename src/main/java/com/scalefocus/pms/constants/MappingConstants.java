// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.constants;

public final class MappingConstants {
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String LOGIN_POST = "/loginPost";
    public static final String HOME = "/home";
    public static final String LOGIN_FAILED = "/error-loginFailed";
    public static final String GET_PARTNERS_MAP = "/partners";
    public static final String LOGOUT = "/logout";
    public static final String NEW_TASK_FORM = "/task/{id}";
    public static final String ADD_NEW_TASK = "/add-new-task";
    public static final String SHOW_ALL_MEETING_TASKS = "/meeting/{id}/tasks";
    public static final String PROFILE = "/profile";
    public static final String CREATE_PROJECT = "/project/create";
    public static final String NEW_PROJECT_FORM = "/project/new";
    public static final String PROJECTS = "/projects";
    public static final String UPDATE_PROJECT_FORM = "/projects/{id}/update";
    public static final String UPDATE_PROJECT = "/project/update";
    public static final String DELETE_PROJECT = "/projects/{id}/delete";
    public static final String CONTACT_US = "/contactUs";
    public static final String ABOUT_US = "/aboutUs";
    public static final String NEW_MEETING_FORM = "/meeting/{projectId}/new";

    public static final String DOCUMENTATION = "/project/{id}/documentation/show";
    public static final String DOCUMENTATION_FORM = "project/documentation/show";
    public static final String SAVE_DOCUMENTATION = "/project/documentation/create";
    public static final String ALL_USERS_PAGE = "users/all-users-page";
    public static final String DELETE_USER = "users/{id}/delete";
    public static final String EDIT_USER = "users/{id}/edit";
    public static final String PROFILE_USER = "users/{id}/profile";
    public static final String EDIT_PROFILE_UDER = "users/profile/edit";
    public static final String NEWS_SHOW = "/news/show";
    public static final String ADD_NEWS_FORM = "/news/add";
    public static final String CREATE_NEWS = "/news/create";
    public static final String DELETE_NEWS = "/news/{id}/delete";
    public static final String EDIT_NEWS = "/news/{id}/edit";
    public static final String PROJECT = "/projects/{id}";

    public static final String CREATE_MEETING = "/meeting/create";
    public static final String MEETINGS_GET_ALL = "/meeting/{projectId}/getAll";

    private MappingConstants() {
    }
}
