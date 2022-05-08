import os

app_env = {
    'spring.datasource.url' : 'SB_DATASOURCE_URL',
    'spring.datasource.username' : 'SB_DATASOURCE_USERNAME',
    'spring.datasource.password' : 'SB_DATASOURCE_PASSWORD',
    'spring.jpa.database' : 'SB_JPA_DATABASE',
    'spring.jpa.properties.hibernate.dialect' : 'SB_JPA_PROPERTIES_HIBERNATE_DIALECT',
    'spring.jpa.show-sql' : 'SB_JPA_SHOW_SQL',
    'server.port' : 'SERVER_PORT',
    'logging.config' : 'LOGGING_CONFIG',
    'spring.security.oauth2.client.registration.google.client-id' : 'GOOGLE_CLIENT_ID',
    'spring.security.oauth2.client.registration.google.client-secret' : 'GOOGLE_CLIENT_SECRET',
    'spring.security.oauth2.client.registration.google.scope' : 'GOOGLE_SCOPE',
    'spring.security.oauth2.client.provider.line.authorization-uri' : 'LINE_AUTHORIZATION_URI',
    'spring.security.oauth2.client.provider.line.token-uri' : 'LINE_TOKEN_URI',
    'spring.security.oauth2.client.provider.line.user-info-uri' : 'LINE_USER_INFO_URI',
    'spring.security.oauth2.client.provider.line.user-info-authentication-method' : 'LINE_USER_INFO_AUTHENTICATION_METHOD',
    'spring.security.oauth2.client.provider.line.user-name-attribute' : 'LINE_USER_NAME_ATTRIBUTE',
    'spring.security.oauth2.client.provider.line.jwk-set-uri' : 'LINE_JWK_SET_URI',
    'spring.security.oauth2.client.registration.line.provider' : 'LINE_PROVIDER',
    'spring.security.oauth2.client.registration.line.client-id' : 'LINE_CLIENT_ID',
    'spring.security.oauth2.client.registration.line.client-secret' : 'LINE_CLIENT_SECRET',
    'spring.security.oauth2.client.registration.line.client-authentication-method' : 'LINE_CLIENT_AUTHENTICATION_METHOD',
    'spring.security.oauth2.client.registration.line.authorization-grant-type' : 'LINE_AUTHORIZATION_GRANT_TYPE',
    'spring.security.oauth2.client.registration.line.redirect-uri' : 'LINE_REDIRECT_URI',
    'spring.security.oauth2.client.registration.line.scope' : 'LINE_SCOPE',
    'spring.security.oauth2.client.registration.line.client-name' : 'LINE_CLIENT_NAME'
}

application_props = ''
for key, value in app_env.items():
    application_props = application_props + key + '=' + os.environ[value] + '\n'

f = open("./src/main/resources/application-prd.properties", 'w')
f.write(application_props)
f.close()