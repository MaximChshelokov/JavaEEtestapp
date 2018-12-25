package com.epam.javaee.rest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/messages")
public class I18nController {
    private static final String UTF8 = ";charset=utf-8";
    @GET
    @Produces(MediaType.APPLICATION_JSON + UTF8)
    public Response getMessages(@QueryParam("lang") String lang) {
        return Response.ok(getMessagesMap(lang)).build();
    }

    private Map<String, String> getMessagesMap(String lang) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.forLanguageTag(lang));
        Map<String, String> map = new HashMap<>();
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, bundle.getString(key));
        }
        return map;
    }
}
