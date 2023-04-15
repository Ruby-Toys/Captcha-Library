package ruby.captcha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaptchaVO {

    private Integer width;
    private Integer height;

    public boolean hasSize() {
        return width != null && height != null;
    }
}
