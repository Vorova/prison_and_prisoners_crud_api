package com.vorova.service.impl;

import com.vorova.dao.PrisonDao;
import com.vorova.dao.impl.PrisonDaoImpl;
import com.vorova.model.PrisonModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({
        MockitoExtension.class
})
class PrisonServiceImplTest {

    @InjectMocks
    PrisonServiceImpl prisonService;

    @Mock
    PrisonDao prisonDao;

    @Test
    @DisplayName("Create prison")
    void createTest() {
        var prison = new PrisonModel();
        doNothing().when(prisonDao).persist(prison);

        prisonService.create(prison);

        verify(prisonDao).persist(prison);
    }

    @Test
    @DisplayName("Create prison. Negative Test")
    void createPrisonIsNullTest() {
        doThrow(new RuntimeException()).when(prisonDao).persist(Mockito.isNull());

        assertThatThrownBy(()->{
            prisonService.create(Mockito.isNull());
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Update prison")
    void updateTest() {
        var prison = new PrisonModel();
        prison.setId(4444L);
        doNothing().when(prisonDao).update(prison.getId(), prison);

        prisonService.update(prison.getId(), prison);

        verify(prisonDao).update(prison.getId(), prison);
    }

    @Test
    @DisplayName("Update prison with negative id")
    void updateNegativeTest() {
        var prison = new PrisonModel();
        prison.setId(-1L);
        doThrow(new RuntimeException()).when(prisonDao).update(prison.getId(), prison);

        assertThatThrownBy(() -> {
            prisonService.update(prison.getId(), prison);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Delete prison")
    void deleteTest() {
        var prisonId = 222L;
        doNothing().when(prisonDao).delete(prisonId);

        prisonService.delete(prisonId);

        verify(prisonDao).delete(prisonId);
    }

    @Test
    @DisplayName("Get prison by id")
    void findByIdTest() {
        var gettingPrison = new PrisonModel();
        gettingPrison.setId(123L);
        when(prisonDao.findById(gettingPrison.getId()))
                .thenReturn(Optional.of(gettingPrison));

        assertThat(prisonDao.findById(gettingPrison.getId())).isEqualTo(Optional.of(gettingPrison));
    }

    @Test
    @DisplayName("Get prison by negative id")
    void findByIdNegativeTest() {
        var negativeId = -1L;
        doThrow(new RuntimeException()).when(prisonDao).findById(negativeId);

        assertThatThrownBy(() ->
            prisonService.findById(negativeId)
        ).isInstanceOf(RuntimeException.class);
    }
}