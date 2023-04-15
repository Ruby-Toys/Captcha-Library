package ruby.captcha;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaConfig {

    private final CaptchaProperties captchaProperties;

    @Bean
    public CaptchaUtil captchaUtil() {
        return new CaptchaUtil(captchaProperties);
    }

    @Bean
    public CaptchaController captchaController() {
        return new CaptchaController(captchaProperties, captchaUtil());
    }
}
