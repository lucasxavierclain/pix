package br.com.pix.prototype.domain;

import br.com.pix.prototype.domain.components.PixComponent;
import br.com.pix.prototype.domain.entities.User;
import br.com.pix.prototype.domain.enums.Erros;
import br.com.pix.prototype.domain.exceptions.ServiceException;
import br.com.pix.prototype.infrastructure.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PixService {


    @Autowired
    PixRepository pixRepository;

    @Autowired
    PixComponent pixComponent;



    public User addUser(User user)  {
        pixComponent.verifyUser(user);

        boolean containsPixKey = containsPixKey(user.getId());
        if(containsPixKey){
            throw new ServiceException(Erros.ERROR_015);
        }
        verifyPixKey(user.getKeyValue());
        return pixRepository.save(user);
    }



    public User modifyUser(User user) {

        pixComponent.verifyUser(user);
        boolean containsPixKey = containsPixKey(user.getId());

        if(!containsPixKey){
            throw new ServiceException(Erros.ERROR_001);
        }
        return pixRepository.save(user);
    }



    public User listUser(String id) {
        if(!containsPixKey(id)){
            throw new ServiceException(Erros.ERROR_016);
        }
        User user = pixRepository.findById(id).get();
        return user;
    }

    public boolean containsPixKey(String id){
        Optional<User> userOptional = pixRepository.findById(id);
        if(userOptional.isPresent()){
            return true;
        }
        return false;
    }

    public void verifyPixKey(String key){
        if(pixRepository.findByKeyValue(key).isPresent()){
            throw new ServiceException(Erros.ERROR_015);
        }

    };
}
