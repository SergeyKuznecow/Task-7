package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;// поле для хранения обьекта успешного входа

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {//Переопределяет метод для настройки HTTP-безопасности.
        http
                //Начинает настройку авторизации запросов.
                .authorizeRequests()
      //  Разрешает доступ к корневой странице (/) и странице индекса (/index) всем пользователям.
                .antMatchers("/", "/index").permitAll()
       // Требует аутентификации для всех остальных запросов.
                .anyRequest().authenticated()

                .and()
    //    Настраивает форму входа и указывает обработчик успешного входа (successUserHandler).
                .formLogin().successHandler(successUserHandler)
   //     Разрешает доступ к форме входа всем пользователям.
                .permitAll()
                .and()
//        Настраивает выход из системы и разрешает доступ к нему всем пользователям.
                .logout()
                .permitAll();
    }

    // аутентификация inMemory
    @Bean
    @Override
    public UserDetailsService userDetailsService() {//Переопределяет метод для настройки сервиса деталей пользователя.
        UserDetails user =
                User.withDefaultPasswordEncoder()//Создает пользователя с использованием стандартного кодировщика паролей.
                        .username("user") //Устанавливает имя пользователя.
                        .password("user")
                        .roles("USER")
                        .build();// Создает объект UserDetails.

        return new InMemoryUserDetailsManager(user);//Возвращает сервис деталей пользователя, который хранит пользователей в памяти.
    }
}