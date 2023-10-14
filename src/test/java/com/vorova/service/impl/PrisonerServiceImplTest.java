package com.vorova.service.impl;

import com.vorova.dao.impl.PrisonerDaoImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({
    MockitoExtension.class
})
class PrisonerServiceImplTest {

    @InjectMocks
    PrisonerServiceImpl prisonerService;

    @Mock
    PrisonerDaoImpl prisonerDao;

    @Test
    @DisplayName("check callback for prisonerDao")
    void createPositiveTest() {
//        PrisonerModel prisoner = new PrisonerModel();
//        Mockito.doNothing().when(prisonerDao).persist(prisoner);
//        prisonerService.create(prisoner);
//        Mockito.verify(prisonerDao).persist(prisoner);
    }

    @Test
    void createNegativeTest() {
//        PrisonerModel prisoner = new PrisonerModel();
//        Mockito.doThrow(new SQLException()).doNothing().when(prisonerDao).persist(prisoner);
//
//        prisonerService.create(prisoner);
//        Mockito.verify(prisonerDao).persist(prisoner);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }
}