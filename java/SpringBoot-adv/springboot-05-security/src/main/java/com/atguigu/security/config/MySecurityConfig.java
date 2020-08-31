package com.atguigu.security.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    // 定义授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);

        // 定制请求的授权规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启自动配置的登录功能。效果：如果没有登录，没有权限，就会来到登录页面。
        // 1、/login 来到默认登录页，可通过 loginPage 访问指定登录页
        // 2、若登录失败，重定向到 /login?error
        // 3、更多详细规定
        // 4、默认 post 形式的 /login 代表处理登录
        // 5、一旦定制 loginPage，那么默认 loginPage 的 post 请求就是处理登录（不需要自己写 controller 处理，由后台自动处理），而 /login 的 post 请求失效
        // 6、可以通过 loginProcessingUrl() 方法来指定处理 post 登录请求的地址
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");

        // 开启自动配置的注销功能
        // 1、访问 /logout 表示用户注销，清空 session
        // 2、注销成功默认会跳转到 /login?logout 页面，这个页面一般就是 /login 页面，但会有一些关于注销的提示信息
        // 3、可通过 logoutSuccessUrl 方法设置注销后跳转的页面
        http.logout().logoutSuccessUrl("/");    // 注销成功后来到首页

        // 开启“记住我”功能
        // 登录成功以后，将 cookie 发给浏览器保存，以后访问页面带上这个 cookie，只要通过检查就可以免登录
        // 点击“注销”会删除 cookie
        // 默认是读取页面传来的 "remember-me" 参数，可通过 rememberMeParameter 方法来修改页面的参数名称
        http.rememberMe().rememberMeParameter("remember");
    }

    // 定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        auth.inMemoryAuthentication().withUser("zhangsan").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password("123456").roles("VIP1", "VIP3");
    }
}
