package com.procedure.scheduling.repository;

import com.procedure.scheduling.ProcedureSchedulingStarter;
import com.procedure.scheduling.model.Room;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProcedureSchedulingStarter.class)
public class RoomRepositoryTest {

    @MockBean
    RoomRepository roomRepository;

    @Before
    public void setUp() {
        Room room = new Room();
        room.setName("ROOM 1");

        Mockito.when(roomRepository.findByName(room.getName()))
                .thenReturn(room);
    }

    @Test
    public void findRoomByName() {
        String name = "ROOM 1";
        Room found = roomRepository.findByName(name);

        assertThat(found.getName())
                .isEqualTo(name);
    }
}
