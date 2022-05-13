package br.com.pix.prototype.infrastructure.components.pix;

import br.com.pix.prototype.domain.components.PixComponent;
import br.com.pix.prototype.domain.entities.KeyType;
import br.com.pix.prototype.domain.entities.User;
import br.com.pix.prototype.domain.enums.Erros;
import br.com.pix.prototype.domain.exceptions.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;

@Component
public class PixComponentImpl implements PixComponent {


    private String regex = "[+-]?\\d*(\\.\\d+)?";

    @Override
    public void verifyUser(User user) {
        verifyKeyType(user);

        verifyAccountNumber(user.getAccountNumber());
        verifyAgencyNumber(user.getAgencyNumber());

        verifyccountType(user.getAccountType());
        user.setActivationTime(Calendar.getInstance().getTime().hashCode());
        if (isNullOrEmpty(user.getId())) {
            int hashCode = user.hashCode() + user.getActivationTime();
            user.setId(String.valueOf(Math.abs(hashCode)));
        }
    }



    private void verifyKeyType(User user) {

        Optional<KeyType> optionalKeyType = Arrays.stream(KeyType.values()).filter(keyType -> keyType.equals(user.getKeyType())).findFirst();

        if (optionalKeyType.isPresent()) {

            KeyType keyType = optionalKeyType.get();

            if (keyType.equals(KeyType.CPF)) {
                verifyCpf(user);
            }
            if (keyType.equals(KeyType.CNPJ)) {
                verifyCnpj(user);
            }
            if (keyType.equals(KeyType.EMAIL)) {
                verifyEmail(user.getKeyValue());
            }
            if (keyType.equals(KeyType.ALEATORY_KEY)) {
                verifyAleatoryKey(user.getKeyValue());
            }
            if (keyType.equals(KeyType.PHONE)) {
                veryfyPhone(user.getKeyValue());
                String numberPhone = user.getKeyValue().replace("+", "");
                user.setKeyValue(numberPhone);
            }

        } else {
            throw new ServiceException(Erros.ERROR_014);
        }
    }

    private void verifyccountType(String accountType) {
        if (isNullOrEmpty(accountType) || (!accountType.contains("CORRENTE") && !accountType.contains("POUPANÇA"))) {
            throw new ServiceException(Erros.ERROR_007);
        }
    }

    private void verifyAgencyNumber(String agencyNumber) {
        if (isNullOrEmpty(agencyNumber) || !agencyNumber.matches(regex) || agencyNumber.length() > 4) {
            throw new ServiceException(Erros.ERROR_008);
        }
    }

    private void verifyAccountNumber(String accountNumber) {
        if (isNullOrEmpty(accountNumber) || !accountNumber.matches(regex) || accountNumber.length() > 8) {
            throw new ServiceException(Erros.ERROR_009);
        }
    }


    private void veryfyPhone(String numberPhone) {
        boolean symbol = numberPhone.startsWith("+");
        String number = numberPhone.replace("+", "");

        if (isNullOrEmpty(numberPhone) || !symbol || !number.matches(regex) || (number.length() > 11 && number.length() < 15)) {
            throw new ServiceException(Erros.ERROR_002);
        }

    }

    private void verifyEmail(String email) {
        if (isNullOrEmpty(email) || !email.contains("@") || email.length() > 77 || email.length() < 2) {
            throw new ServiceException(Erros.ERROR_003);
        }

    }


    private void verifyCpf(User user) {
        String cpf = user.getKeyValue();

        if (isNullOrEmpty(cpf) || !cpf.matches(regex) || cpf.length() > 11) {
            throw new ServiceException(Erros.ERROR_004);
        }
        if (!user.getPersonType().equals("F")) {
            throw new ServiceException(Erros.ERROR_010);
        }

    }

    private void verifyCnpj(User user) {
        String cnpj = user.getKeyValue();

        if (isNullOrEmpty(cnpj) || cnpj.length() > 14 || !cnpj.matches(regex)) {
            throw new ServiceException(Erros.ERROR_005);
        }

        if (!user.getPersonType().equals("J")) {
            throw new ServiceException(Erros.ERROR_010);
        }

    }

    private void verifyAleatoryKey(String aleatoryKey) {

        if (isNullOrEmpty(aleatoryKey) || aleatoryKey.length() > 36) {
            throw new ServiceException(Erros.ERROR_006);
        }


    }


    public boolean isNullOrEmpty(Object o) {
        return (o == null || "".equals(o.toString().trim()));
    }
}
