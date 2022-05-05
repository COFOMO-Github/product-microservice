package com.cofomo.product.microservice.utils;

import org.springframework.stereotype.Component;


@Component
public class Constants {

    /*********************
     *      PATH
     ***********************/

    public static final String PRODUCT_URL = "/product";
    public static final String JSON_TEST_DATA_FILE_PATH = "src/test/resources/cucumber/json_data/";

    /*******************
     TABLE NAME
     ********************/

    public static final String PRODUCT = "PRODUCT";
    public static final String USER = "USER_TABLE";


    /*********************
     * COLUMN NAME
     ***********************/

    /*** PRODUCT ***/
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String PRICE = "PRICE";
    public static final String REFPDT = "REFPDT";
    public static final String REFFRS = "REFFRS";

    /*** USER ***/

    public static final String USERNAME = "USERNAME";

    public static final String PASSWORD = "PASSWORD";
    /*********************
     * Messages Exceptions
     ***********************/

    public static final String MESSG_ERR = "No record of type %s and with id %s is present in the database";

}
