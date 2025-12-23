package com.api.framework.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.api.framework.constants.Constants.BASE_URL;
import static io.restassured.config.LogConfig.logConfig;

public class RequestSpecificationFactory {

    private static final RestAssuredConfig logOnlyFailedConfig = new RestAssuredConfig()
            .logConfig(logConfig()
                    .enableLoggingOfRequestAndResponseIfValidationFails()
                    .enablePrettyPrinting(true));

    public static RequestSpecification getDefaultRequestSpecification() {
        return new RequestSpecBuilder()
                .setConfig(logOnlyFailedConfig)
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .addFilter(new LoggingFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }

    private static class LoggingFilter implements Filter {
        private static final Logger logger = LoggerFactory.getLogger(RequestSpecificationFactory.class);

        @Override
        public Response filter(FilterableRequestSpecification requestSpec,
                               FilterableResponseSpecification responseSpec,
                               FilterContext ctx) {

            logger.info("Sending request...: {}", requestSpec.getMethod() + " " + requestSpec.getURI());
            final Response response = ctx.next(requestSpec, responseSpec);
            logger.info("Received response: {}", response.getStatusLine() +
                    ", response time " + response.getTimeIn(TimeUnit.MILLISECONDS) + "ms");
            return response;
        }
    }
}
