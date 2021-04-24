package webshop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import webshop.Services.MyUserDetailsService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityConfig(MyUserDetailsService myUserDetailsService){
        this.myUserDetailsService = myUserDetailsService;

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()         //kell az angularral valo osszekapcsolodashoz
                .and().csrf().disable().
                authorizeRequests().antMatchers("/**","/time","/authenticate","image/new", "/v2/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/webjars/**" ,
                "/swagger.json").
                permitAll().anyRequest().authenticated().
                and().sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS);       //Spring Sec is not building a session
         //ask to use the filterchain, works for each request
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //cross.fdsfdfaf
    @Bean
    @Qualifier("cors")
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:63342","http://localhost:4200", "https://farmfalatb.herokuapp.com"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT","PATCH","HEAD","OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}





        /*
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
*/


