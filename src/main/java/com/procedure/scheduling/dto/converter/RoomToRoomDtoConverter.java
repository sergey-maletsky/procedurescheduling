package com.procedure.scheduling.dto.converter;

import com.procedure.scheduling.dto.RoomDto;
import com.procedure.scheduling.model.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomToRoomDtoConverter implements Converter<Room, RoomDto> {

    @Override
    public RoomDto convert(Room room) {

        RoomDto dto = new RoomDto();
        dto.setName(room.getName());

        return dto;
    }
}
