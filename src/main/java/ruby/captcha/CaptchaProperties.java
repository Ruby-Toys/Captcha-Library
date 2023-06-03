package ruby.captcha;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("captcha")
public class CaptchaProperties {

    private final String url;
    private final String attributeName;
    private final Integer answerLength;
    private final Size size;
    private final Integer noise;
    private final int sessionTimeout;

    public record Size(int width, int height) { }
}
