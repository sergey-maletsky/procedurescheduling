package com.procedure.scheduling.repository;

import com.procedure.scheduling.model.Room;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Nullable
    Room findByName(@NotNull String name);
}
