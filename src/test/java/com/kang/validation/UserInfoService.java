package com.kang.validation;

import javax.validation.Valid;

public class UserInfoService {

    /**
     * 作为输入参数校验
     * @param userInfo
     */
    public void setUserInfo(@Valid UserInfo userInfo){

    }

    /**
     * 作为输出参数进行校验
     * @return
     */
    public UserInfo getUserInfo(){

        UserInfo userInfo = new UserInfo();

        return userInfo;
    }

    /**
     * 默认构造函数
     */
    public UserInfoService(){

    }

    /**
     * 接收UserInfo作为参数的构造函数
     */
    public UserInfoService(UserInfo userInfo){

    }

}
