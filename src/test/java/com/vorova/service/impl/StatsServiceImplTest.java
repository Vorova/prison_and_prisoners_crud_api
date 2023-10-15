package com.vorova.service.impl;

import com.vorova.dao.PrisonDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({
    MockitoExtension.class
})
class StatsServiceImplTest {

    @InjectMocks
    private StatsServiceImpl statsService;

    @Mock
    private PrisonDao prisonDao;
    @Test
    @DisplayName("Check callback for prisonDao and prisonerDao")
    void getTest() {
        when(prisonDao.findAll()).thenReturn(new ArrayList<>());

        statsService.get();

        verify(prisonDao).findAll();
    }
}