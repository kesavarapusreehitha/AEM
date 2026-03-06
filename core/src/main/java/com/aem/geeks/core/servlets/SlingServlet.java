package com.aem.geeks.core.servlets;

public @interface SlingServlet {

    String paths();

    String methods();

    String extensions();

}
