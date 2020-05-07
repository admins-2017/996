package com.kang.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 验证测试类
 */
@SpringBootTest
public class ValidationTest {

    /**
     * 验证器对象
     */
    private Validator validator;

    /**
     * 待验证对象
     */
    private UserInfo userInfo;

//    @Test
//    public void test(){
//        /**
//         * 初始化验证器
//         */
//        validator = Validation.buildDefaultValidatorFactory().getValidator();
//
//        List<UserInfo> friends = new ArrayList<>(10);
//        LocalDateTime date = LocalDateTime.of(2018,3,15,1,1,0);
//
//        /**
//         * 初始化验证对象
//         */
//        userInfo = new UserInfo("123456","kang","123456"
//                ,"106790675926@qq.com","15149333955",80, date,friends);
//
//        UserInfo userInfo2 = new UserInfo("123456","kang","123456"
//                ,"106790675926@qq.com","15149333955",70, date,friends);
//
//        friends.add(userInfo2);
//
//        /**
//        * 使用验证器验证
//         */
//        Set<ConstraintViolation<UserInfo>> userSet = validator.validate(userInfo);
//
//        userSet.forEach(item -> System.out.println(item.getMessage()));
//
//    }
//
//    @Test
//    public void testGroupValidation(){
//        validator = Validation.buildDefaultValidatorFactory().getValidator();
//        List<UserInfo> friends = new ArrayList<>(10);
//        LocalDateTime date = LocalDateTime.of(2018,3,15,1,1,0);
//
//        /**
//         * 初始化验证对象
//         */
//        userInfo = new UserInfo(null,"kang","123456"
//                ,null,"15149333955",80, date,friends);
//        /**
//         * 验证注册分组
//         */
//        Set<ConstraintViolation<UserInfo>> validate = validator.validate(userInfo, UserInfo.RegisterGroup.class);
//
//        /**
//         * 验证登录分组
//         */
//        Set<ConstraintViolation<UserInfo>> validate2 = validator.validate(userInfo, UserInfo.RegisterGroup.class);
//
//        validate.forEach(i -> System.out.println(i.getMessage()));
//
//    }

    /**
     * 验证多个组时排序
     */
    @Test
    public void testGroupValidation2(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        List<UserInfo> friends = new ArrayList<>(10);
        LocalDateTime date = LocalDateTime.of(2018,3,15,1,1,0);

        /**
         * 初始化验证对象
         */
        userInfo = new UserInfo("kang","12323",
                "1067905926@qq.com","15149333955",66,date,friends,null);
        /**
         * 验证注册分组
         */
        Set<ConstraintViolation<UserInfo>> validate = validator.validate(userInfo,UserInfo.Group.class);

        validate.forEach(i -> System.out.println(i.getMessage()));

    }

    /**
     * 对方法入参进行校验
     */
    @Test
    public void paramValidation() throws NoSuchMethodException {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        //获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        //待验证对象
        UserInfoService userInfoService = new UserInfoService();
        //待验证方法
        Method setUserInfo = userInfoService.getClass().getMethod("setUserInfo", UserInfo.class);
        //获取方法输入参数
        Object [] params = new Object[]{new UserInfo()};

        //对方法的输入参数进行校验
        Set<ConstraintViolation<Object>> constraintViolations = executableValidator.validateParameters(
                userInfoService,setUserInfo,params);

        constraintViolations.forEach(item -> System.out.println(item.getMessage()));
    }

}
