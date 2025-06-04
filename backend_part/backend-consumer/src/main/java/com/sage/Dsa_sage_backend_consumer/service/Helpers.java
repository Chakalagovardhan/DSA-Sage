package com.sage.Dsa_sage_backend_consumer.service;



import java.util.Map;

public interface Helpers {

    public String chatResponse(String str);

    public byte[] generatePdf(String str);

    public String StringMaker(Map<String,Integer> reponse);

}
