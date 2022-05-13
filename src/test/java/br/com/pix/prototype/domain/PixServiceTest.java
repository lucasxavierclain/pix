package br.com.pix.prototype.domain;

import br.com.pix.prototype.domain.components.PixComponent;
import br.com.pix.prototype.domain.entities.KeyType;
import br.com.pix.prototype.domain.entities.User;
import br.com.pix.prototype.domain.exceptions.ServiceException;
import br.com.pix.prototype.infrastructure.repository.PixRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PixServiceTest {

    @InjectMocks
    PixService service;

    @Mock
    PixRepository pixRepository;

    @Mock
    PixComponent pixComponent;

    @Test
    public void testAddUserByCpf() {
        User userMock = userMock(KeyType.CPF, "F", "12345", "CORRENTE", "1234", "11111");
        when(pixRepository.save(any())).thenReturn(userMock);
        assertEquals("should be equals", service.addUser(userMock).getKeyValue(), "12345");
    }
    @Test
    public void testAddUserByEmail(){
        User userMock = userMock(KeyType.EMAIL, "F", "teste@gmail.com", "CORRENTE", "1234", "11111");
        when(pixRepository.save(any())).thenReturn(userMock);
        assertEquals("should be equals", service.addUser(userMock).getKeyValue(), "teste@gmail.com");
    }

    @Test
    public void testAddUserByPhone()  {
        User userMock = userMock(KeyType.PHONE, "F", "+93848388222", "CORRENTE", "1234", "11111");
        when(pixRepository.save(any())).thenReturn(userMock);
        assertEquals("should be equals", service.addUser(userMock).getKeyValue(), "+93848388222");
    }

    @Test
    public void testListUser(){

        User userMock = userMock(KeyType.PHONE, "F", "+93848388222", "CORRENTE", "1234", "11111");
        userMock.setId("3333");
        Optional<User> optionalUser = Optional.of(userMock);
        when(pixRepository.findById(any())).thenReturn(optionalUser);
        assertEquals("should be equals", service.listUser(userMock.getId()).getKeyValue(), "+93848388222");
    }

    @Test
    public void testModifyUser(){
        User userMock = userMock(KeyType.PHONE, "F", "+93848388222", "CORRENTE", "1234", "11111");
        userMock.setId("3333");
        Optional<User> optionalUser = Optional.of(userMock);
        when(pixRepository.findById(any())).thenReturn(optionalUser);
        when(pixRepository.save(any())).thenReturn(userMock);
        assertEquals("should be equals", service.modifyUser(userMock).getKeyValue(), "+93848388222");
    }

    private User userMock(KeyType keyType, String personType, String keyValue, String accountType,String agencyNumber,
                          String accountNumber   ){
        User user = new User();
        user.setKeyType(keyType);
        user.setPersonType(personType);
        user.setKeyValue(keyValue);
        user.setAccountType(accountType);
        user.setAgencyNumber(agencyNumber);
        user.setAccountNumber(accountNumber);
        return user;
    }
}