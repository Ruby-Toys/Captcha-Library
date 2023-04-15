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

    public record Size(Integer defaultWidth, Integer defaultHeight) { }
}
