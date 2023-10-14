package com.vorova.service.impl;

import com.vorova.dao.PrisonerDao;
import com.vorova.model.PrisonerModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({
        MockitoExtension.class
})
class PrisonerServiceImplTest {

    @InjectMocks
    PrisonerServiceImpl prisonerService;

    @Mock
    PrisonerDao prisonerDao;

    @Test
    @DisplayName("Check callback by create")
    void createTest() {
        PrisonerModel prisoner = new PrisonerModel();
        doNothing().when(prisonerDao).persist(prisoner);

        prisonerService.create(prisoner);

        verify(prisonerDao).persist(prisoner);
    }

    @Test
    @DisplayName("Prisoner is null for create")
    void createNegativeTest() {
        PrisonerModel prisoner = new PrisonerModel();
        doThrow(new RuntimeException()).when(prisonerDao).persist(prisoner);

        assertThatThrownBy(() -> {
            prisonerService.create(prisoner);
        }).isInstanceOf(RuntimeException.class);

        verify(prisonerDao).persist(prisoner);
    }

    @Test
    @DisplayName("Check callback by update")
    void updateTest() {
        PrisonerModel prisoner = new PrisonerModel();
        doNothing().when(prisonerDao).update(prisoner.getId(), prisoner);

        prisonerService.update(prisoner.getId(), prisoner);

        verify(prisonerDao).update(prisoner.getId(), prisoner);
    }

    @Test
    @DisplayName("Update prisoner with negative primary")
    void updateNegativeTest() {
        PrisonerModel prisoner = new PrisonerModel();
        prisoner.setId(-1L);
        doThrow(RuntimeException.class).when(prisonerDao).update(prisoner.getId(), prisoner);

        assertThatThrownBy(() -> {
            prisonerService.update(prisoner.getId(), prisoner);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Success delete prisoner")
    void deleteTest() {
        Long prisonerId = 3333L;
        doNothing().when(prisonerDao).delete(prisonerId);

        prisonerService.delete(prisonerId);

        verify(prisonerDao).delete(prisonerId);
    }

    @Test
    @DisplayName("Delete prisoner with negative id")
    public void deleteNegativeTest() {
        long negativeId = -1L;
        doThrow(RuntimeException.class).when(prisonerDao).delete(negativeId);

        assertThatThrownBy(() -> {
            prisonerService.delete(negativeId);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Get prisoner")
    void findByIdTest() {
        var gettingPrisoner = new PrisonerModel();
        gettingPrisoner.setId(1L);
        gettingPrisoner.setName("Elmir");
        when(prisonerDao.findById(gettingPrisoner.getId())).thenReturn(Optional.of(gettingPrisoner));

        assertThat(prisonerService.findById(gettingPrisoner.getId())).isEqualTo(gettingPrisoner);
    }

    @Test
    @DisplayName("Get prisoner with negative id")
    void findByIdNegativeTest() {
        var negativeId = -1L;
        when(prisonerDao.findById(negativeId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            prisonerService.findById(negativeId);
        }).isInstanceOf(NoSuchElementException.class);
    }
}