package com.shvg.sudokujourney.service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

public interface FirestoreService {

    DocumentReference getDocument(String collectionName, String documentId);

    CollectionReference getCollection(String collectionName);

}
