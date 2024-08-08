package com.shvg.sudokujourney.service.impl;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.shvg.sudokujourney.model.dto.UserDto;
import com.shvg.sudokujourney.model.entity.UserEntity;
import com.shvg.sudokujourney.service.FirebaseService;
import com.shvg.sudokujourney.util.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class FirebaseServiceImpl implements FirebaseService {

    private final Firestore firestoreDb;
    private final EntityMapper entityMapper;

    @Override
    public void saveUser(UserDto dto) {
        UserEntity userEntity = entityMapper.userToEntity(dto);
        firestoreDb.collection(UserEntity.COLLECTION_NAME).document(userEntity.getId()).set(userEntity);
    }

    @Override
    public void updateUser(UserDto dto) {
        UserEntity userEntity = entityMapper.userToEntity(dto);
        firestoreDb.collection(UserEntity.COLLECTION_NAME).document(userEntity.getId()).set(userEntity);
    }

    @Override
    public UserDto getUserById(String id) {
        UserDto output = null;
        try {
            DocumentSnapshot document = firestoreDb.collection(UserEntity.COLLECTION_NAME).document(id).get().get();
            if (document.exists()) output = entityMapper.userToDto(document.toObject(UserEntity.class));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    @Override
    public List<UserDto> getAllUsers() {
        try {
            return firestoreDb.collection(UserEntity.COLLECTION_NAME).get().get().toObjects(UserEntity.class)
                    .stream().map(entityMapper::userToDto).toList();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(String id) {
        firestoreDb.collection(UserEntity.COLLECTION_NAME).document(id).delete();
    }
}
