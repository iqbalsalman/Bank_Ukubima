package iqbal.salman.bank.ukubima.bankukubima.Configurision;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from security.users where username=?")
                .authoritiesByUsernameQuery("select u.username as username , r.authority as \"authority\" from security.users  u inner join security.user_roles ur \n" +
                        "                        on(u.user_id=ur.user_id) inner join security.roles r on(ur.role_id=r.role_id) where u.username=?")
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/registration").permitAll()
                .antMatchers("/user/submit").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and().csrf().disable().formLogin()
                .loginPage("/user/login").failureUrl("/user/login?error=true")
                .defaultSuccessUrl("/user/admin")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/user/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "classpath:/static/", "/css/**", "/js/**", "/images/**");
    }

    @Bean
    public LayoutDialect thymeleafLayoutDialect() {
        return new LayoutDialect();

    }
}
