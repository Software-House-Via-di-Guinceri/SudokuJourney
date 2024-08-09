package com.shvg.sudokujourney.service.database.impl;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Filter;
import com.google.cloud.firestore.Firestore;
import com.shvg.sudokujourney.model.dto.UserDto;
import com.shvg.sudokujourney.model.entity.UserEntity;
import com.shvg.sudokujourney.service.FirestoreService;
import com.shvg.sudokujourney.service.database.UserService;
import com.shvg.sudokujourney.util.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {

    private final Firestore firestoreDb;
    private final EntityMapper entityMapper;
    private final CollectionReference userCollection;

    public UserServiceImpl(Firestore firestoreDb, EntityMapper entityMapper) {
        this.firestoreDb = firestoreDb;
        this.entityMapper = entityMapper;
        userCollection = firestoreDb.collection(UserEntity.COLLECTION_NAME);
    }

    @Override
    public void save(UserDto dto) {
        UserEntity userEntity = entityMapper.userToEntity(dto);
        userCollection.document(dto.getId()).set(userEntity);
    }

    @Override
    public UserDto getById(String id) {
        UserDto output = null;
        try {
            DocumentSnapshot document = userCollection.document(id).get().get();
            if (document.exists()) output = entityMapper.userToDto(document.toObject(UserEntity.class));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    @Override
    public UserDto getByUsername(String username) {
        UserDto output = null;
        try {
            DocumentSnapshot document = firestoreDb.collection(UserEntity.COLLECTION_NAME).whereEqualTo(UserAttributesEnum.USERNAME.name, username)
                    .get().get().getDocuments().get(0);
            if (document.exists()) output = entityMapper.userToDto(document.toObject(UserEntity.class));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    @Override
    public List<UserDto> getAll() {
        try {
            return userCollection.get().get().toObjects(UserEntity.class)
                    .stream().map(entityMapper::userToDto).toList();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        userCollection.document(id).delete();
    }

    private enum UserAttributesEnum {

        ID("id"),
        FIRST_NAME("first_name"),
        LAST_NAME("last_name"),
        EMAIL("email"),
        USERNAME("username");

        public final String name;

        UserAttributesEnum(String name) {
            this.name = name;
        }

    }

}
