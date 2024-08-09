package com.shvg.sudokujourney.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    private static final String SERVICE_ACCOUNT_KEY_PATH = "./secrets/serviceAccountKey.json";

    @Bean
    public Firestore initialize() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(ResourceUtils.getFile(SERVICE_ACCOUNT_KEY_PATH));

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        return FirestoreClient.getFirestore();
    }

}
