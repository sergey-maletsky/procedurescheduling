package com.procedure.scheduling.dto.converter;

import com.procedure.scheduling.dto.RoomDto;
import com.procedure.scheduling.model.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoToRoomConverter implements Converter<RoomDto, Room> {

    @Override
    public Room convert(RoomDto dto) {

        Room room = new Room();
        room.setName(dto.getName());

        return room;
    }
}
