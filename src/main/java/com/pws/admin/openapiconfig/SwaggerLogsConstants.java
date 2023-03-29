package com.pws.admin.openapiconfig;

public class SwaggerLogsConstants {
    public static final String USER_SAVED_200_SUCCESSFULL = "{\n" +
            "    \"id\": 5,\n" +
            "    \"firstName\": \"Visnu\",\n" +
            "    \"lastName\": \"dfg\",\n" +
            "    \"dob\": \"1970-01-01T00:00:00.012+00:00\",\n" +
            "    \"email\": \"123a@gmail.com\",\n" +
            "    \"phoneNumber\": 999999999122349999,\n" +
            "    \"password\": \"$2a$08$ZXeWJ/4PhMlBR1dGOzAcN.X.axEXoeIHq/U/InmHoCT6b0IPu6KLu\",\n" +
            "    \"isActive\": true,\n" +
            "    \"enabled\": true,\n" +
            "    \"username\": \"123a@gmail.com\",\n" +
            "    \"authorities\": [],\n" +
            "    \"accountNonExpired\": true,\n" +
            "    \"accountNonLocked\": true,\n" +
            "    \"credentialsNonExpired\": true\n" +
            "}";

    public static final String USER_SAVE_500_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T05:15:36.879+00:00\",\n" +
            "    \"status\": 500,\n" +
            "    \"error\": \"Internal Server Error\",\n" +
            "    \"path\": \"/public/user\"\n" +
            "}";

    public static final String USER_SAVE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T05:36:11.444+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/public/user\"\n" +
            "}";
    public static final String USER_GET_BY_ID_200_SUCCESSFUL = "{\n" +
            "    \"id\": 1,\n" +
            "    \"firstName\": \"Visnu\",\n" +
            "    \"lastName\": \"dfg\",\n" +
            "    \"dob\": \"1970-01-01T00:00:00.000+00:00\",\n" +
            "    \"email\": \"a@gmail.com\",\n" +
            "    \"phoneNumber\": 999999999122349999,\n" +
            "    \"password\": \"$2a$08$Mb699MyKIPa7RP9oj3sHN.l1P9S5MIKAhmhtwlBDSA8CyT.QrZ8zC\",\n" +
            "    \"isActive\": true,\n" +
            "    \"enabled\": true,\n" +
            "    \"authorities\": [],\n" +
            "    \"username\": \"a@gmail.com\",\n" +
            "    \"accountNonExpired\": true,\n" +
            "    \"accountNonLocked\": true,\n" +
            "    \"credentialsNonExpired\": true\n" +
            "}";
    public static final String USER_GET_BY_ID_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:41:02.181+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/getUser\"\n" +
            "}";
    public static final String USER_GET_ALL_200_SUCCESSFUL = "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"firstName\": \"Visnu\",\n" +
            "        \"lastName\": \"dfg\",\n" +
            "        \"dob\": \"1970-01-01T00:00:00.000+00:00\",\n" +
            "        \"email\": \"a@gmail.com\",\n" +
            "        \"phoneNumber\": 999999999122349999,\n" +
            "        \"password\": \"$2a$08$Mb699MyKIPa7RP9oj3sHN.l1P9S5MIKAhmhtwlBDSA8CyT.QrZ8zC\",\n" +
            "        \"isActive\": true,\n" +
            "        \"enabled\": true,\n" +
            "        \"authorities\": [],\n" +
            "        \"username\": \"a@gmail.com\",\n" +
            "        \"accountNonExpired\": true,\n" +
            "        \"accountNonLocked\": true,\n" +
            "        \"credentialsNonExpired\": true\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"firstName\": \"Visnu\",\n" +
            "        \"lastName\": \"dfg\",\n" +
            "        \"dob\": \"1970-01-01T00:00:00.000+00:00\",\n" +
            "        \"email\": \"1a@gmail.com\",\n" +
            "        \"phoneNumber\": 999999999122349999,\n" +
            "        \"password\": \"$2a$10$G6JS9BObE8IGpXUDK/ghv.CdvxFvUQbItekWNsFvloDML25YMFBWi\",\n" +
            "        \"isActive\": true,\n" +
            "        \"enabled\": true,\n" +
            "        \"authorities\": [],\n" +
            "        \"username\": \"1a@gmail.com\",\n" +
            "        \"accountNonExpired\": true,\n" +
            "        \"accountNonLocked\": true,\n" +
            "        \"credentialsNonExpired\": true\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 3,\n" +
            "        \"firstName\": \"Visnu\",\n" +
            "        \"lastName\": \"dfg\",\n" +
            "        \"dob\": \"1970-01-01T00:00:00.000+00:00\",\n" +
            "        \"email\": \"2a@gmail.com\",\n" +
            "        \"phoneNumber\": 999999999122349999,\n" +
            "        \"password\": \"$2a$08$9IpQq/9g81ed68XldSJVq.I1UmRY/oUt5drQxwzyKhDaaEe9m5oVq\",\n" +
            "        \"isActive\": true,\n" +
            "        \"enabled\": true,\n" +
            "        \"authorities\": [],\n" +
            "        \"username\": \"2a@gmail.com\",\n" +
            "        \"accountNonExpired\": true,\n" +
            "        \"accountNonLocked\": true,\n" +
            "        \"credentialsNonExpired\": true\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 4,\n" +
            "        \"firstName\": \"Visnu\",\n" +
            "        \"lastName\": \"dfg\",\n" +
            "        \"dob\": \"1970-01-01T00:00:00.000+00:00\",\n" +
            "        \"email\": \"12a@gmail.com\",\n" +
            "        \"phoneNumber\": 999999999122349999,\n" +
            "        \"password\": \"$2a$08$jtV4XS/04nofA17H0mHjieKO4raadWZcEypn8YY6DuoD96xjRFQRW\",\n" +
            "        \"isActive\": true,\n" +
            "        \"enabled\": true,\n" +
            "        \"authorities\": [],\n" +
            "        \"username\": \"12a@gmail.com\",\n" +
            "        \"accountNonExpired\": true,\n" +
            "        \"accountNonLocked\": true,\n" +
            "        \"credentialsNonExpired\": true\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 5,\n" +
            "        \"firstName\": \"Visnu\",\n" +
            "        \"lastName\": \"dfg\",\n" +
            "        \"dob\": \"1970-01-01T00:00:00.000+00:00\",\n" +
            "        \"email\": \"123a@gmail.com\",\n" +
            "        \"phoneNumber\": 999999999122349999,\n" +
            "        \"password\": \"$2a$08$ZXeWJ/4PhMlBR1dGOzAcN.X.axEXoeIHq/U/InmHoCT6b0IPu6KLu\",\n" +
            "        \"isActive\": true,\n" +
            "        \"enabled\": true,\n" +
            "        \"authorities\": [],\n" +
            "        \"username\": \"123a@gmail.com\",\n" +
            "        \"accountNonExpired\": true,\n" +
            "        \"accountNonLocked\": true,\n" +
            "        \"credentialsNonExpired\": true\n" +
            "    }\n" +
            "]";
    public static final String USER_GET_ALL_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:43:29.848+00:00\",\n" +
            "    \"status\": 404,\n" +
            "    \"error\": \"Not Found\",\n" +
            "    \"path\": \"/allUser\"\n" +
            "}";
    public static final String ROLE_GET_ALL_200_SUCCESSFUL = "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"emp\",\n" +
            "        \"isActive\": true\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"employee\",\n" +
            "        \"isActive\": true\n" +
            "    }\n" +
            "]";

    public static final String ROLE_GET_ALL_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T08:32:14.942+00:00\",\n" +
            "    \"status\": 404,\n" +
            "    \"error\": \"Not Found\",\n" +
            "    \"path\": \"/allRole\"\n" +
            "}";


    public static final String USER_UPDATE_200_SUCCESSFUL = "User Data Updated";
    public static final String USER_UPDATE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:46:33.389+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/update/user\"\n" +
            "}";

    public static final String ROLE_UPDATE_200_SUCCESSFUL = "Data Updated successfully";
    public static final String ROLE_UPDATE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T08:33:47.475+00:00\",\n" +
            "    \"status\": 404,\n" +
            "    \"error\": \"Not Found\",\n" +
            "    \"path\": \"/update/rol\"\n" +
            "}";

    public static final String USER_PASSWORD_UPDATE_200_SUCCESSFUL = "password updated successfully";
    public static final String USER_PASSWORD_UPDATE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:49:34.912+00:00\",\n" +
            "    \"status\": 404,\n" +
            "    \"error\": \"Not Found\",\n" +
            "    \"path\": \"/update/user/passwor\"\n" +
            "}";
    public static final String USER_Authentication_UPDATE_200_SUCCESSFUL = "Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxYUBnbWFpbC5jb20iLCJleHAiOjE2Nzg4MTYzNzEsImlhdCI6MTY3ODc4MDM3MX0.dZ80uX6SwOlhqzmVSp-LsVjb9QaGUj7dfJTcDk2dvfM";

    public static final String USER_Authentication_UPDATE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:51:36.532+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/authenticate\"\n" +
            "}";

    public static final String USER_DELETE_200_SUCCESSFUL = "User Deleted Successfully";
    public static final String USER_DELETE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T08:20:52.444+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/delete/user\"\n" +
            "}";

    public static final String ROLE_DELETE_200_SUCCESSFUL = "Role Deleted Succcessfully";

    public static final String ROLE_DELETE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T08:35:56.269+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/delete/role\"\n" +
            "}";

    public static final String ROLE_SAVED_200_SUCCESSFULL = "{   \n" +
            "\n" +
            "  \"name\":\"employee\",\n" +
            "  \"isActive\":true\n" +
            "\n" +
            "}";
    public static final String ROLE_SAVE_400_FAILURE = "   \n" +
            "    \"timestamp\": \"2023-03-14T06:27:46.959+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/role\"\n" +
            "}";
    public static final String ROLE_UPDATED_200_SUCCESSFULL = "{   \n" +
            "    \"id\":1,\n" +
            "    \"name\":\"emp\",\n" +
            "    \"isActive\":true\n" +
            "}";

    public static final String ROLE_SAVE_500_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T06:48:00.687+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/update/role\"\n" +
            "}";
    public static final String MODEL_SAVE_200_SUCCESSFUL = "Data Saved";

    public static final String MODEL_SAVE_400_Failure = "{\n" +
            "    \"timestamp\": \"2023-03-14T06:53:44.713+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/model\"\n" +
            "}";

    public static final String MODEL_UPDATE_200_SUCCESSFUL = "Model Data Modified";

    public static final String MODEL_UPDATE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T06:58:46.910+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/update/model\"\n" +
            "}";

    public static final String MODEL_DELETE_200_SUCCESSFUL = "Model Deleted Succcessfully";

    public static final String MODEL_DELETE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:02:07.674+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/delete/Model\"\n" +
            "}";

    public static final String MODEL_GET_BY_ID_200_SUCCESSFUL =
            "    \"modelId\": 1,\n" +
                    "    \"name\": \"about\",\n" +
                    "    \"isActive\": true\n" +
                    "}";
    public static final String MODEL_GET_BY_ID_400_FAILURE =
            "    \"timestamp\": \"2023-03-14T07:05:11.667+00:00\",\n" +
                    "    \"status\": 400,\n" +
                    "    \"error\": \"Bad Request\",\n" +
                    "    \"path\": \"/getModel\"\n" +
                    "}";

    public static final String MODEL_GET_ALL_200_SUCCESSFUL = "[\n" +
            "    {\n" +
            "        \"modelId\": 1,\n" +
            "        \"name\": \"about\",\n" +
            "        \"isActive\": true\n" +
            "    }\n" +
            "]";
    public static final String MODEL_GET_ALL_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:08:17.343+00:00\",\n" +
            "    \"status\": 404,\n" +
            "    \"error\": \"Not Found\",\n" +
            "    \"path\": \"/allModel\"\n" +
            "}";
    public static final String PERMISSION_SAVE_200_SUCCESSFUL = "Data Saved ";

    public static final String PERMISSION_SAVE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:13:04.027+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/permission\"\n" +
            "}";

    public static final String PERMISSION_UPDATE_200_SUCCESSFUL = "Permission Data Altered";
    public static final String PERMISSION_UPDATE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:18:47.506+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/update/permission\"\n" +
            "}";
    public static final String PERMISSION_GET_BY_ID_SUCCESSFUL = "{\n" +
            "    \"id\": 1,\n" +
            "    \"isActive\": true,\n" +
            "    \"isView\": false,\n" +
            "    \"isAdd\": true,\n" +
            "    \"isUpdate\": true,\n" +
            "    \"isDelete\": true,\n" +
            "    \"model\": {\n" +
            "        \"modelId\": 1,\n" +
            "        \"name\": \"about\",\n" +
            "        \"isActive\": true\n" +
            "    },\n" +
            "    \"role\": {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"emp\",\n" +
            "        \"isActive\": true\n" +
            "    }\n" +
            "} ";
    public static final String PERMISSION_GET_BY_ID_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:20:50.315+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/getPermission\"\n" +
            "}";


    public static final String PERMISSION_DELETE_SUCCESSFUL = "Permission Deleted Succcessfully";
    public static final String PERMISSION_DELETE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:24:37.608+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/delete/permission\"\n" +
            "}";

    public static final String PERMISSION_GET_ALL_SUCCESSFUL = "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"isActive\": true,\n" +
            "        \"isView\": false,\n" +
            "        \"isAdd\": true,\n" +
            "        \"isUpdate\": true,\n" +
            "        \"isDelete\": true,\n" +
            "        \"model\": {\n" +
            "            \"modelId\": 1,\n" +
            "            \"name\": \"about\",\n" +
            "            \"isActive\": true\n" +
            "        },\n" +
            "        \"role\": {\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"emp\",\n" +
            "            \"isActive\": true\n" +
            "        }\n" +
            "    }\n" +
            "]";

    public static final String PERMISSION_GET_ALL_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:23:26.129+00:00\",\n" +
            "    \"status\": 404,\n" +
            "    \"error\": \"Not Found\",\n" +
            "    \"path\": \"/allPermission\"\n" +
            "}";


    public static final String USER_ROLE_X_REF_SAVE_200_SUCCESSFUL = "Data Saved";
    public static final String USER_ROLE_X_REF_SAVE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:28:30.582+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/xref\"\n" +
            "}";

    public static final String USER_ROLE_X_REF_GET_ALL_200_SUCCESSFUL = "[\n" +
            "    {\n" +
            "        \"refId\": 1,\n" +
            "        \"user\": {\n" +
            "            \"id\": 1,\n" +
            "            \"firstName\": \"Visnu\",\n" +
            "            \"lastName\": \"dfg\",\n" +
            "            \"dob\": \"1970-01-01T00:00:00.000+00:00\",\n" +
            "            \"email\": \"a@gmail.com\",\n" +
            "            \"phoneNumber\": 999999999122349999,\n" +
            "            \"password\": \"$2a$08$Mb699MyKIPa7RP9oj3sHN.l1P9S5MIKAhmhtwlBDSA8CyT.QrZ8zC\",\n" +
            "            \"isActive\": true,\n" +
            "            \"enabled\": true,\n" +
            "            \"authorities\": [],\n" +
            "            \"username\": \"a@gmail.com\",\n" +
            "            \"accountNonExpired\": true,\n" +
            "            \"accountNonLocked\": true,\n" +
            "            \"credentialsNonExpired\": true\n" +
            "        },\n" +
            "        \"role\": {\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"emp\",\n" +
            "            \"isActive\": true\n" +
            "        },\n" +
            "        \"isActive\": true\n" +
            "    },\n" +
            "    {\n" +
            "        \"refId\": 2,\n" +
            "        \"user\": {\n" +
            "            \"id\": 1,\n" +
            "            \"firstName\": \"Visnu\",\n" +
            "            \"lastName\": \"dfg\",\n" +
            "            \"dob\": \"1970-01-01T00:00:00.000+00:00\",\n" +
            "            \"email\": \"a@gmail.com\",\n" +
            "            \"phoneNumber\": 999999999122349999,\n" +
            "            \"password\": \"$2a$08$Mb699MyKIPa7RP9oj3sHN.l1P9S5MIKAhmhtwlBDSA8CyT.QrZ8zC\",\n" +
            "            \"isActive\": true,\n" +
            "            \"enabled\": true,\n" +
            "            \"authorities\": [],\n" +
            "            \"username\": \"a@gmail.com\",\n" +
            "            \"accountNonExpired\": true,\n" +
            "            \"accountNonLocked\": true,\n" +
            "            \"credentialsNonExpired\": true\n" +
            "        },\n" +
            "        \"role\": {\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"emp\",\n" +
            "            \"isActive\": true\n" +
            "        },\n" +
            "        \"isActive\": true\n" +
            "    }\n" +
            "] ";
    public static final String USER_ROLE_X_REF_GET_ALL_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:30:43.024+00:00\",\n" +
            "    \"status\": 404,\n" +
            "    \"error\": \"Not Found\",\n" +
            "    \"path\": \"/allUserRoleXRef\"\n" +
            "}";

    public static final String USER_ROLE_X_REF_UPDATE_200_SUCCESSFUL = "UserRoleXRef Data Replaced";
    public static final String USER_ROLE_X_REF_UPDATE_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:35:53.554+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/update/userRoleXref\"\n" +
            "}";

    public static final String USER_ROLE_X_REF_GET_BY_ID_200_SUCCESSFUL = "{\n" +
            "    \"refId\": 2,\n" +
            "    \"user\": {\n" +
            "        \"id\": 1,\n" +
            "        \"firstName\": \"Visnu\",\n" +
            "        \"lastName\": \"dfg\",\n" +
            "        \"dob\": \"1970-01-01T00:00:00.000+00:00\",\n" +
            "        \"email\": \"a@gmail.com\",\n" +
            "        \"phoneNumber\": 999999999122349999,\n" +
            "        \"password\": \"$2a$08$Mb699MyKIPa7RP9oj3sHN.l1P9S5MIKAhmhtwlBDSA8CyT.QrZ8zC\",\n" +
            "        \"isActive\": true,\n" +
            "        \"enabled\": true,\n" +
            "        \"authorities\": [],\n" +
            "        \"username\": \"a@gmail.com\",\n" +
            "        \"accountNonExpired\": true,\n" +
            "        \"accountNonLocked\": true,\n" +
            "        \"credentialsNonExpired\": true\n" +
            "    },\n" +
            "    \"role\": {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"emp\",\n" +
            "        \"isActive\": true\n" +
            "    },\n" +
            "    \"isActive\": true\n" +
            "} ";
    public static final String USER_ROLE_X_REF_GET_BY_ID_400_FAILURE = "{\n" +
            "    \"timestamp\": \"2023-03-14T07:38:11.867+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/getUserRoleXRef\"\n" +
            "}";


}

