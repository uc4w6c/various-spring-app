package com.apigateway.mavenlambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.Map;

@Component
public class Hello implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent input) {
        Map<String, String> queryStringParameter = input.getQueryStringParameters();

        String id = queryStringParameter.get("id");
        String name = null;

        if( id.equals("11111") ) {
            name = "\"Taro\"";
        } else {
            name = "\"Nanashi\"";
        }

        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setStatusCode(200);
        responseEvent.setBody("{\"name\":" + name + "}");
        return responseEvent;
    }
}
