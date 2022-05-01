import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateSpringBootProps {
    public static void main(String[] args) {
        String[] appEnvAttributes = {
            "spring.datasource.url",
            "spring.datasource.username",
            "spring.datasource.password",
            "spring.jpa.database",
            "spring.jpa.properties.hibernate.dialect",
            "spring.jpa.show-sql",
            "server.port",
            "logging.config",
            "spring.security.oauth2.client.registration.google.client-id",
            "spring.security.oauth2.client.registration.google.client-secret", 
            "spring.security.oauth2.client.registration.google.scope", 
            "spring.security.oauth2.client.provider.line.authorization-uri",
            "spring.security.oauth2.client.provider.line.token-uri",
            "spring.security.oauth2.client.provider.line.user-info-uri",
            "spring.security.oauth2.client.provider.line.user-info-authentication-method",
            "spring.security.oauth2.client.provider.line.user-name-attribute",
            "spring.security.oauth2.client.provider.line.jwk-set-uri",
            "spring.security.oauth2.client.registration.line.provider",
            "spring.security.oauth2.client.registration.line.client-id",
            "spring.security.oauth2.client.registration.line.client-secret",
            "spring.security.oauth2.client.registration.line.client-authentication-method",
            "spring.security.oauth2.client.registration.line.authorization-grant-type",
            "spring.security.oauth2.client.registration.line.redirect-uri",
            "spring.security.oauth2.client.registration.line.scope",
            "spring.security.oauth2.client.registration.line.client-name"
        };
        String[] appEnvValues = {
            "SB_DATASOURCE_URL",
            "SB_DATASOURCE_USERNAME",
            "SB_DATASOURCE_PASSWORD",
            "SB_JPA_DATABASE",
            "SB_JPA_PROPERTIES_HIBERNATE_DIALECT",
            "SB_JPA_SHOW_SQL",
            "SERVER_PORT",
            "LOGGING_CONFIG",
            "GOOGLE_CLIENT_ID",
            "GOOGLE_CLIENT_SECRET",
            "GOOGLE_SCOPE",
            "LINE_AUTHORIZATION_URI",
            "LINE_TOKEN_URI",
            "LINE_USER_INFO_URI",
            "LINE_USER_INFO_AUTHENTICATION_METHOD",
            "LINE_USER_NAME_ATTRIBUTE",
            "LINE_JWK_SET_URI",
            "LINE_PROVIDER",
            "LINE_CLIENT_ID",
            "LINE_CLIENT_SECRET",
            "LINE_CLIENT_AUTHENTICATION_METHOD",
            "LINE_AUTHORIZATION_GRANT_TYPE",
            "LINE_REDIRECT_URI",
            "LINE_SCOPE",
            "LINE_CLIENT_NAME"
        };

        makePropertyFile("../src/main/resources/application-prd.properties", appEnvAttributes, appEnvValues);
    }

    private static String combineEnvAttributeAndValue(String[] attributes, String[] values) {
        String result = "";
        for(int i = 0; i < values.length; i++) {
            if(i == values.length - 1) {
                result = result + attributes[i] + "=" + System.getenv(values[i]);
            } else {
                result = result + attributes[i] + "=" + System.getenv(values[i]) + "\n";
            }
        }

        return result;
    }

    private static void makePropertyFile(String filePath, String[] attributes, String[] values){
        try {
            File file = new File(filePath);
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);

            writer.write(combineEnvAttributeAndValue(attributes, values));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}