package br.com.pix.prototype.domain.exceptions;

import br.com.pix.prototype.domain.enums.Erros;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException{

    private String codeError;
    private String message;

    private static final long serialVersionUID=1L;

    public ServiceException(Erros erros){
        super(erros.getCode());
        this.codeError= erros.getCode();
        this.message=erros.getMessage();
    }


}
