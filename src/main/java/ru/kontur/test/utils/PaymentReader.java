package ru.kontur.test.utils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * Class for working with payment files
 */
public class PaymentReader {

    private static final String CONTRACTOR = "Плательщик";
    private static final String DESCRIPTION = "НазначениеПлатежа";
    private static final String SUM = "Сумма";
    private static final String DATE = "ДатаПоступило";
    private static final String NUMBER = "Номер";

    private static HashMap<String, String> paymentMap = new HashMap<>();


    /**
     * Constructor reads payment from file <aPathname>
     * @param aPathname path to the file
     */
    public PaymentReader(@Nonnull String aPathname) {
        try {
            File file = new File(aPathname);
            FileInputStream stream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(stream, "windows-1251");
            Properties properties = new Properties();
            try {
                properties.load(reader);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                stream.close();
            }

            Enumeration enuKeys = properties.keys();

            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                paymentMap.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get record value by key
     * @param aKey  record key as String
     * @return record value as String
     */
    public String getValueOf(@Nonnull String aKey) {
        return paymentMap.get(aKey);
    }

    public String getContractor() {
        return paymentMap.get(CONTRACTOR);
    }

    public String getSum() {
        return paymentMap.get(SUM).replace(".", ",");
    }

    public String getTax() {
        return "0,00";
    }

    public String getDescription() {
        return paymentMap.get(DESCRIPTION);
    }

    public String getDate() {
        return paymentMap.get(DATE);
    }

    public String getDocumentNumber() {
        return paymentMap.get(NUMBER);
    }
}
