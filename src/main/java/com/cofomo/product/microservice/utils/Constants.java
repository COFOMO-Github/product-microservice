package com.cofomo.productmicroservice.utils;

import org.springframework.stereotype.Component;


@Component
public class Constants {

    /*********************
     *      PATH
     ***********************/

    public static final String PRODUCT_URL = "/product";
    public static final String JSON_TEST_DATA_FILE_PATH="src/test/resources/cucumber/json_data/";

    /*******************
        TABLE NAME
     ********************/

    public static final String PRODUCT = "PRODUCT";


    /*********************
     * COLUMN NAME
     ***********************/

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String PRICE = "PRICE";
    public static final String FOURNISSEUR_ID = "FOURNISSEUR_ID";

    /*********************
     * Messages Exceptions
     ***********************/

    public static final String MESSG_ERR = "No record of type %s and with id %s is present in the database";

}
