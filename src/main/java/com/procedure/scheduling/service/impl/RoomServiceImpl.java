package com.procedure.scheduling.service.impl;

import com.procedure.scheduling.dto.RoomDto;
import com.procedure.scheduling.dto.converter.BaseConverter;
import com.procedure.scheduling.repository.RoomRepository;
import com.procedure.scheduling.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private BaseConverter converter;

    @Override
    public List<RoomDto> list() {

        return converter.convertList(repository.findAll(), RoomDto.class);
    }
}
