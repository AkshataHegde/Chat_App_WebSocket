package com.highpeak.chat.datastore.Repository;

import com.highpeak.chat.datastore.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel,Long> {

    List<UserModel> findByUserIdInAndIsActiveTrueAndIsDeletedFalse(List<Long> userIdList);

    Optional<UserModel> findByUserIdAndIsActiveTrueAndIsDeletedFalse(Long userId);
}
