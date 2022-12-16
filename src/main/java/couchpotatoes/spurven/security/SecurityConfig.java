package couchpotatoes.spurven.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import couchpotatoes.spurven.security.error.CustomOAuth2AccessDeniedHandler;
import couchpotatoes.spurven.security.error.CustomOAuth2AuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//,jsr250Enabled = true)

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    System.out.println("CALLED");
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .cors().and()
            .csrf((csrf) -> csrf.ignoringAntMatchers("/auth/login"))
            .csrf().disable()
            .httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            //REF: https://mflash.dev/post/2021/01/19/error-handling-for-spring-security-resource-server/
            .exceptionHandling((exceptions) -> exceptions
                    .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint())
                    .accessDeniedHandler(new CustomOAuth2AccessDeniedHandler())
            )
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(authenticationConverter());

    http.authorizeHttpRequests((authorize) -> authorize
            .antMatchers(HttpMethod.POST, "/auth/login").permitAll()

              /*.antMatchers(HttpMethod.GET, "/users/**").hasAuthority("ADMIN")
              .antMatchers(HttpMethod.GET, "/contacts").hasAuthority("ADMIN")
              .antMatchers(HttpMethod.GET, "/expenses").hasAuthority("ADMIN")
              .antMatchers(HttpMethod.GET, "/events").hasAuthority("ADMIN")*/
            .antMatchers("/error").permitAll()
            //.antMatchers("/", "/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/users/").hasAuthority("ADMIN")
                .anyRequest().authenticated());

    return http.build();
  }

  @Bean
  public JwtAuthenticationConverter authenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }

  /* Initialize static value "secret" */
  @Value("${app.secret-key}")
  private String secretKey;
  public static String tokenSecret;

  @Value("${app.secret-key}")
  public void setStaticValue(String secretKey) {
    SecurityConfig.tokenSecret = secretKey;
  }
  /* End of Initialize static value "secret" */

  @Bean
  public JwtEncoder jwtEncoder() throws JOSEException {
    SecretKey key = new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
    JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<SecurityContext>(key);
    return new NimbusJwtEncoder(immutableSecret);
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    SecretKey originalKey = new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(originalKey).build();
    return jwtDecoder;
  }


  //TBD --> IS THIS THE RIGHT WAY
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
          throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }


}
