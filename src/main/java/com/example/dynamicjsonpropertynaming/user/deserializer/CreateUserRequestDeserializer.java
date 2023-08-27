package com.example.dynamicjsonpropertynaming.user.deserializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import com.example.dynamicjsonpropertynaming.user.dto.CreateUserRequest;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import lombok.Getter;
import lombok.Setter;

import static org.springframework.web.context.request.RequestContextHolder.getRequestAttributes;

@Getter @Setter
@Component
@ConfigurationProperties("user.dynamic-json-property")
public class CreateUserRequestDeserializer extends StdDeserializer<CreateUserRequest> {

    private Map<String,String> createUser;

    public CreateUserRequestDeserializer() { super(CreateUserRequest.class); }

    @Override
    public CreateUserRequest deserialize(JsonParser parser, DeserializationContext context)
    throws IOException, JacksonException {

        final JsonNode jsonNode = parser.getCodec().readTree(parser);
        final Iterator<Entry<String, JsonNode>> fields = jsonNode.fields();

        final RequestAttributes requestAttributes = getRequestAttributes();
        final CreateUserRequest createUserRequest = new CreateUserRequest();
        while (fields.hasNext()) {

            final Entry<String, JsonNode> field = fields.next();

            final String fieldName = field.getKey();
            final String fieldValue = field.getValue().asText(null);

            // 성능을 위해 리플렉션은 지양한다
            if (CreateUserRequest.Fields.lastName.equals(createUser.get(fieldName))) {

                if (requestAttributes!=null)
                requestAttributes.setAttribute(CreateUserRequest.Fields.lastName, fieldName, RequestAttributes.SCOPE_REQUEST);
                createUserRequest.setLastName(fieldValue);
            }

            if (CreateUserRequest.Fields.firstName.equals(createUser.get(fieldName))) {

                if (requestAttributes!=null)
                requestAttributes.setAttribute(CreateUserRequest.Fields.firstName, fieldName, RequestAttributes.SCOPE_REQUEST);
                createUserRequest.setFirstName(fieldValue);
            }

            if (CreateUserRequest.Fields.phoneNumber.equals(createUser.get(fieldName))) {

                if (requestAttributes!=null)
                requestAttributes.setAttribute(CreateUserRequest.Fields.phoneNumber, fieldName, RequestAttributes.SCOPE_REQUEST);
                createUserRequest.setPhoneNumber(fieldValue);
            }
        }

        return createUserRequest;
    }
}
