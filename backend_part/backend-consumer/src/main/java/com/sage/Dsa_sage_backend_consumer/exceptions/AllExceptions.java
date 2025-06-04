package com.sage.Dsa_sage_backend_consumer.exceptions;



public class AllExceptions {

    public static class MailSendingError extends RuntimeException
    {
        public MailSendingError(String str)
        {
            super(str);
        }
    }
}
