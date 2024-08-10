package com.shvg.sudokujourney.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    private static final String SERVICE_ACCOUNT_KEY_PATH = "./secrets/serviceAccountKey.json";

    @Value("${firebase.service.account.keys.type}")
    private String type;
    @Value("${firebase.service.account.keys.project_id}")
    private String projectId;
    @Value("${firebase.service.account.keys.private_key_id}")
    private String privateKeyId;
    @Value("${firebase.service.account.keys.private_key}")
    private String privateKey;
    @Value("${firebase.service.account.keys.client_email}")
    private String clientEmail;
    @Value("${firebase.service.account.keys.client_id}")
    private String clientId;
    @Value("${firebase.service.account.keys.auth_uri}")
    private String authUri;
    @Value("${firebase.service.account.keys.token_uri}")
    private String tokenUri;
    @Value("${firebase.service.account.keys.auth_provider_x509_cert_url}")
    private String authProviderX509CertUrl;
    @Value("${firebase.service.account.keys.client_x509_cert_url}")
    private String clientX509CertUrl;
    @Value("${firebase.service.account.keys.universe_domain}")
    private String universeDomain;

    @Bean
    public Firestore initialize() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(ResourceUtils.getFile(SERVICE_ACCOUNT_KEY_PATH));

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        /*ServiceAccountCredentials.newBuilder()
                .setProjectId(projectId)
                .setPrivateKeyId(privateKeyId)
                .setPrivateKey(ServiceAccountCredentials.)
                .setClientEmail(clientEmail)
                .setClientId(clientId)
                .setTokenServerUri(tokenUri)
                */

        FirebaseApp.initializeApp(options);
        return FirestoreClient.getFirestore();
    }

}
