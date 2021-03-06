package io.dropwizard.jdbi.jersey;

import io.dropwizard.jersey.errors.LoggingExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

/**
 * Iterates through SQLExceptions to log all causes
 */
@Provider
public class LoggingSQLExceptionMapper extends LoggingExceptionMapper<SQLException> {
    LoggingSQLExceptionMapper(Logger logger) {
        super(logger);
    }

    public LoggingSQLExceptionMapper() {
        this(LoggerFactory.getLogger(LoggingSQLExceptionMapper.class));
    }

    @Override
    @SuppressWarnings("Slf4jFormatShouldBeConst")
    protected void logException(long id, SQLException exception) {
        final String message = formatLogMessage(id, exception);
        for (Throwable throwable : exception) {
            logger.error(message, throwable);
        }
    }
}
