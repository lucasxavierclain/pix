package br.com.pix.prototype.domain.enums;


public enum Erros {

    ERROR_001("001", "Does not contain pix key"),
    ERROR_002("002", "Irregular number"),
    ERROR_003("003", "Irregular email"),
    ERROR_004("004", "Irregular cpf"),
    ERROR_005("005", "Irregular cnpj"),
    ERROR_006("006", "Irregular aleatory key"),
    ERROR_007("007", "Irregular account type"),
    ERROR_008("008", "Irregular agency number"),
    ERROR_009("009", "Irregular account number"),
    ERROR_010("010", "Irregular person type"),
    ERROR_011("011", "user without registration"),
    ERROR_012("012", "number of accounts exceeded"),
    ERROR_013("013", "Unknown error "),
    ERROR_014("014", "Irregular keyType "),
    ERROR_015("015", "Contain pix key"),
    ERROR_016("016", "Does not contain user");



    private String code;
    private String message;

    Erros(String code, String message){
        this.code = code;
        this.message = message;

    }



    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
