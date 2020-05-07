package com.kang.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 验证对象类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    /**
     * 分组验证
     * 登录场景
     */
    public interface loginGroup{

    }

    /**
     * 分组验证
     * 注册场景
     */
    public interface RegisterGroup{

    }

    /**
     * 组排序场景
     * 定义多组顺序 依次验证分组 先验证登录组，在验证注册组，最后对为进行分组的进行验证
     * 如果验证不通过则跳出验证
     */
    @GroupSequence({
            RegisterGroup.class,
            loginGroup.class,
            Default.class
    })
    public interface Group{

    }




    /**
     * 用户名
     * NotEmpty 可以验证空字符串 但是不能验证字符串中有空格 " "
     */
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    /**
     * 用户密码
     * NotBlank 可以验证空字符串（包括字符串中有空格） 可以自动去掉字符串中的空格
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码不能低于6位，不能多于20位")
    private String passWord;

    /**
     * 邮箱
     * 加入注册分组，在注册时邮箱不能为空
     */
    @NotBlank(message = "邮箱不能为空", groups =RegisterGroup.class )
    @Email(message = "填写的邮箱格式有误")
    private String email;

    /**
     * 手机号
     */

    private String phone;

    /**
     * 年龄
     *
     * min max 对数值进行验证
     */
    @Min(value = 18,message = "未成年禁止入内")
    @Max(value = 65 ,message = "您这么大岁数就别来了")
    private Integer age;

    /**
     * 生日
     * past 验证 日期不能为现在或未来时间
     */
    @Past(message = "日期填写错误,请重新填写")
    private LocalDateTime birthday;

    /**
     * 好友列表
     * Size 进行验证集合的长度
     * @Valid 级联验证 验证list中的对象属性
     */
    @Size(min = 1,message = "不能少于一个好友")
    private List<@Valid UserInfo> friends;

    /**
     * 用户id
     * NotNull 不可以验证空字符串 “”
     * groups 设置为登录分组的验证 登录时验证id不能为空
     */
    @NotNull(message = "用户id不能为空" ,groups = loginGroup.class)
    private String userId;
}
