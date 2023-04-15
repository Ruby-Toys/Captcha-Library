package ruby.captcha;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaProperties captchaProperties;
    private final CaptchaUtil captchaUtil;

    @GetMapping("${captcha.url}")
    public void createCaptcha(CaptchaVO captchaVO, HttpServletRequest request, HttpServletResponse response) {
        Captcha captcha = captchaVO.hasSize() ?
                captchaUtil.createCaptcha(captchaVO.getWidth(), captchaVO.getHeight())
                : captchaUtil.createCaptcha();
        AudioCaptcha audioCaptcha = captchaUtil.createAudioCaptcha(captcha);

        request.getSession().setAttribute(captchaProperties.getAttributeName(), captcha);

        CaptchaServletUtilCustom.writeImage(response, captcha.getImage());
        CaptchaServletUtilCustom.writeAudio(response, audioCaptcha.getChallenge());
    }
}
