package ai.chat2db.server.web.start.config.oauth;

import cn.dev33.satoken.log.SaLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * satoken Log printing
 *
 * @author Shi Yi
 */
@Slf4j
@Component
public class SaLogForSlf4j implements SaLog {
    @Override
    public void trace(String str, Object... args) {
        log.trace(str, args);
    }

    @Override
    public void debug(String str, Object... args) {
        log.debug(str, args);
    }

    @Override
    public void info(String str, Object... args) {
        log.info(str, args);
    }

    @Override
    public void warn(String str, Object... args) {
        log.trace(str, args);
    }

    @Override
    public void error(String str, Object... args) {
        log.error(str, args);
    }

    @Override
    public void fatal(String str, Object... args) {
        log.error(str, args);
    }
}
