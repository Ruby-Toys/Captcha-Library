package ruby.captcha;

import lombok.RequiredArgsConstructor;
import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.text.producer.NumbersAnswerProducer;

@RequiredArgsConstructor
public class CaptchaUtil {

    private final CaptchaProperties captchaProperties;

    public Captcha createCaptcha() {
        Integer width = captchaProperties.getSize().defaultWidth();
        Integer height = captchaProperties.getSize().defaultHeight();
        return createCaptcha(width, height);
    }

    public Captcha createCaptcha(int width, int height) {
        Integer answerLength = captchaProperties.getAnswerLength();
        answerLength = answerLength == null ? 5 : answerLength;

        return new Captcha.Builder(width, height)
                .addText(new NumbersAnswerProducer(answerLength))
                .addNoise().addNoise().addNoise()
                .addBackground(new GradiatedBackgroundProducer())
                .addBorder()
                .build();
    }

    public AudioCaptcha createAudioCaptcha(Captcha captcha) {
        return new AudioCaptcha.Builder()
                .addAnswer(captcha::getAnswer)
                .addVoice()
                .addNoise()
                .build();
    }
}
