package br.com.nilto.tarefa4all.model;

/**
 * Created by nilto on 21/10/2016.
 */

public class IdList {
    private String idList;
    private String idAux;

    public IdList(String idList, String idAux) {
        this.idList = idList;
        this.idAux = idAux;
    }

    public IdList() {

    }

    public IdList(String idList) {
        this.idList = idList;
    }

    public String getIdAux() {
        return idAux;
    }

    public void setIdAux(String idAux) {
        this.idAux = idAux;
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }


}
