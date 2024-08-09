package com.shvg.sudokujourney.service.impl;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.shvg.sudokujourney.service.FirestoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirestoreServiceImpl implements FirestoreService {

    private final Firestore firestoreDb;

    @Override
    public DocumentReference getDocument(String collectionName, String documentId) {
        return firestoreDb.collection(collectionName).document(documentId);
    }

    @Override
    public CollectionReference getCollection(String collectionName) {
        return firestoreDb.collection(collectionName);
    }

}
