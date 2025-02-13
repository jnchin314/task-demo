package com.demo.todo.util;

import com.demo.todo.entity.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StringToStatusConverterTest {

    @InjectMocks
    StringToStatusConverter stringToStatusConverter;

    @Test
    void givenStatusAsString_whenConvert_thenConvertStatus() {
        Status status = stringToStatusConverter.convert("cReated");
        assertEquals(status, Status.CREATED);
        status = stringToStatusConverter.convert("In_PRogress");
        assertEquals(status, Status.IN_PROGRESS);
        status = stringToStatusConverter.convert("doNe");
        assertEquals(status, Status.DONE);
    }

    @Test
    void givenBadStatus_whenConvert_thenNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->stringToStatusConverter.convert("some_status"));
        assertEquals(exception.getMessage(), "No enum constant com.demo.todo.entity.Status.SOME_STATUS");

    }
}