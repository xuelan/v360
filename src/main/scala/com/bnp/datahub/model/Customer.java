//package com.bnp.datahub.model;
//
//import java.util.List;
//
//public class Customer{
//    private String idRpCustomer;
//    private String idTlm;
//    private String lbCiv;
//    private String firstNm;
//    private String lastNm;
//    private String usedNm;
//    private String lbDtBirth;
//    private String lbBirthPlace;
//    private String lbEmail;
//    private String lbProfsn;
//    private String lbNtnlty;
//    private String adrsNum;
//    private String getLbAdrs1;
//    private String lbAdrs2;
//    private String cp;
//    private String lbCity;
//    private String lbCntry;
//    private List<Card> listTypCard;
//    private List<Account> listTypAcc;
//    private List<Contract> listTypCntr;
//
//    public Customer() {
//    }
//
//    public Customer(String idRpCustomer, String idTlm, String lbCiv, String firstNm, String lastNm, String usedNm, String lbDtBirth, String lbBirthPlace, String lbEmail, String lbProfsn, String lbNtnlty, String adrsNum, String getLbAdrs1, String lbAdrs2, String cp, String lbCity, String lbCntry, List<Card> listTypCard, List<Account> listTypAcc, List<Contract> listTypCntr) {
//        this.idRpCustomer = idRpCustomer;
//        this.idTlm = idTlm;
//        this.lbCiv = lbCiv;
//        this.firstNm = firstNm;
//        this.lastNm = lastNm;
//        this.usedNm = usedNm;
//        this.lbDtBirth = lbDtBirth;
//        this.lbBirthPlace = lbBirthPlace;
//        this.lbEmail = lbEmail;
//        this.lbProfsn = lbProfsn;
//        this.lbNtnlty = lbNtnlty;
//        this.adrsNum = adrsNum;
//        this.getLbAdrs1 = getLbAdrs1;
//        this.lbAdrs2 = lbAdrs2;
//        this.cp = cp;
//        this.lbCity = lbCity;
//        this.lbCntry = lbCntry;
//        this.listTypCard = listTypCard;
//        this.listTypAcc = listTypAcc;
//        this.listTypCntr = listTypCntr;
//    }
//
//    public String getIdRpCustomer() {
//        return idRpCustomer;
//    }
//
//    public void setIdRpCustomer(String idRpCustomer) {
//        this.idRpCustomer = idRpCustomer;
//    }
//
//    public String getIdTlm() {
//        return idTlm;
//    }
//
//    public void setIdTlm(String idTlm) {
//        this.idTlm = idTlm;
//    }
//
//    public String getLbCiv() {
//        return lbCiv;
//    }
//
//    public void setLbCiv(String lbCiv) {
//        this.lbCiv = lbCiv;
//    }
//
//    public String getFirstNm() {
//        return firstNm;
//    }
//
//    public void setFirstNm(String firstNm) {
//        this.firstNm = firstNm;
//    }
//
//    public String getLastNm() {
//        return lastNm;
//    }
//
//    public void setLastNm(String lastNm) {
//        this.lastNm = lastNm;
//    }
//
//    public String getUsedNm() {
//        return usedNm;
//    }
//
//    public void setUsedNm(String usedNm) {
//        this.usedNm = usedNm;
//    }
//
//    public String getLbDtBirth() {
//        return lbDtBirth;
//    }
//
//    public void setLbDtBirth(String lbDtBirth) {
//        this.lbDtBirth = lbDtBirth;
//    }
//
//    public String getLbBirthPlace() {
//        return lbBirthPlace;
//    }
//
//    public void setLbBirthPlace(String lbBirthPlace) {
//        this.lbBirthPlace = lbBirthPlace;
//    }
//
//    public String getLbEmail() {
//        return lbEmail;
//    }
//
//    public void setLbEmail(String lbEmail) {
//        this.lbEmail = lbEmail;
//    }
//
//    public String getLbProfsn() {
//        return lbProfsn;
//    }
//
//    public void setLbProfsn(String lbProfsn) {
//        this.lbProfsn = lbProfsn;
//    }
//
//    public String getLbNtnlty() {
//        return lbNtnlty;
//    }
//
//    public void setLbNtnlty(String lbNtnlty) {
//        this.lbNtnlty = lbNtnlty;
//    }
//
//    public String getAdrsNum() {
//        return adrsNum;
//    }
//
//    public void setAdrsNum(String adrsNum) {
//        this.adrsNum = adrsNum;
//    }
//
//    public String getGetLbAdrs1() {
//        return getLbAdrs1;
//    }
//
//    public void setGetLbAdrs1(String getLbAdrs1) {
//        this.getLbAdrs1 = getLbAdrs1;
//    }
//
//    public String getLbAdrs2() {
//        return lbAdrs2;
//    }
//
//    public void setLbAdrs2(String lbAdrs2) {
//        this.lbAdrs2 = lbAdrs2;
//    }
//
//    public String getCp() {
//        return cp;
//    }
//
//    public void setCp(String cp) {
//        this.cp = cp;
//    }
//
//    public String getLbCity() {
//        return lbCity;
//    }
//
//    public void setLbCity(String lbCity) {
//        this.lbCity = lbCity;
//    }
//
//    public String getLbCntry() {
//        return lbCntry;
//    }
//
//    public void setLbCntry(String lbCntry) {
//        this.lbCntry = lbCntry;
//    }
//
//    public List<Card> getListTypCard() {
//        return listTypCard;
//    }
//
//    public void setListTypCard(List<Card> listTypCard) {
//        this.listTypCard = listTypCard;
//    }
//
//    public List<Account> getListTypAcc() {
//        return listTypAcc;
//    }
//
//    public void setListTypAcc(List<Account> listTypAcc) {
//        this.listTypAcc = listTypAcc;
//    }
//
//    public List<Contract> getListTypCntr() {
//        return listTypCntr;
//    }
//
//    public void setListTypCntr(List<Contract> listTypCntr) {
//        this.listTypCntr = listTypCntr;
//    }
//}
