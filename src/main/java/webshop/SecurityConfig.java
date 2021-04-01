package webshop;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity

class SecurityConfig  {

    protected void configure(HttpSecurity http) throws Exception {
http.cors()         //kell az angularral valo osszekapcsolodashoz
        .and().csrf().disable().
        authorizeRequests().antMatchers("/authenticate", "/v2/api-docs/**",
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/webjars/**" ,
        "/swagger.json").
        permitAll().anyRequest();
          //Spring Sec is not building a session
        //http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);     //ask to use the filterchain, works for each request

    }
}
