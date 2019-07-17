package com.ajou.TwotoSix.unitTest2;

import com.ajou.TwotoSix.unitTest2.repository.MockRepository;
import com.ajou.TwotoSix.unitTest2.service.MockService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;


public class SubjectTest {

    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;

}