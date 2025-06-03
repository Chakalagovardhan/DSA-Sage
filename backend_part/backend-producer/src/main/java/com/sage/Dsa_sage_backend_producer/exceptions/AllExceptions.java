package com.sage.Dsa_sage_backend_producer.exceptions;


public class AllExceptions {

    public static class kafkaPushException extends RuntimeException
    {
        public kafkaPushException(String str)
        {
            super(str);
        }
    }
}
