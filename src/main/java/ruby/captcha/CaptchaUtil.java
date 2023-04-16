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
        int width = 300;
        int height = 50;

        if (captchaProperties.getSize() != null) {
            width = captchaProperties.getSize().width();
            height = captchaProperties.getSize().height();
        }

        return createCaptcha(width, height);
    }

    public Captcha createCaptcha(int width, int height) {
        Integer answerLength = captchaProperties.getAnswerLength();
        answerLength = answerLength == null ? 5 : answerLength;

        Captcha.Builder builder = new Captcha.Builder(width, height)
                .addText(new NumbersAnswerProducer(answerLength))
                .addBackground(new GradiatedBackgroundProducer())
                .addBorder();

        int noiseCount = 3;
        if (captchaProperties.getNoise() != null) {
            noiseCount = captchaProperties.getNoise();
        }
        for (int i = 0; i < noiseCount; i++) {
            builder = builder.addNoise();
        }

        return builder.build();
    }

    public AudioCaptcha createAudioCaptcha(Captcha captcha) {
        return new AudioCaptcha.Builder()
                .addAnswer(captcha::getAnswer)
                .addVoice()
                .addNoise()
                .build();
    }
}
