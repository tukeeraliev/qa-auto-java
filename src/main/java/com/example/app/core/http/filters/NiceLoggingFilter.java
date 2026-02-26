package com.example.app.core.http.filters;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class NiceLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification req,
                           FilterableResponseSpecification resSpec,
                           FilterContext ctx) {

        long start = System.currentTimeMillis();

        // request dump ДО выполнения запроса
        String requestDump = buildRequestDump(req);

        Response response = ctx.next(req, resSpec);

        long ms = System.currentTimeMillis() - start;

        // короткий лог в консоль
        System.out.printf("[API] %s %s -> %d (%d ms)%n",
                req.getMethod(),
                req.getURI(),
                response.getStatusCode(),
                ms
        );

        // вложения в Allure (без AspectJ)
        attachText("Request", requestDump);
        attachText("Response", buildResponseDump(response));

        return response;
    }

    private String safeBody(Object body) {
        if (body == null) return "";
        if (body instanceof String s) return s;
        if (body instanceof char[] ch) return new String(ch);
        if (body instanceof byte[] b) return new String(b, StandardCharsets.UTF_8);
        return body.toString();
    }

    private String buildRequestDump(FilterableRequestSpecification req) {
        String headers = req.getHeaders() == null ? "" :
                req.getHeaders().asList().stream()
                        .map(h -> h.getName() + ": " + h.getValue())
                        .collect(Collectors.joining("\n"));

        String body = safeBody(req.getBody());

        return "➡️ REQUEST\n"
                + req.getMethod() + " " + req.getURI() + "\n\n"
                + "HEADERS:\n" + headers + "\n\n"
                + "BODY:\n" + body;
    }

    private String buildResponseDump(Response response) {
        String headers = response.getHeaders() == null ? "" :
                response.getHeaders().asList().stream()
                        .map(h -> h.getName() + ": " + h.getValue())
                        .collect(Collectors.joining("\n"));

        String body = response.getBody() == null ? "" : response.getBody().asString();

        return "⬅️ RESPONSE\n"
                + "STATUS: " + response.getStatusLine() + "\n\n"
                + "HEADERS:\n" + headers + "\n\n"
                + "BODY:\n" + body;
    }

    private void attachText(String name, String content) {
        Allure.addAttachment(
                name,
                "text/plain",
                new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)),
                ".txt"
        );
    }
}
