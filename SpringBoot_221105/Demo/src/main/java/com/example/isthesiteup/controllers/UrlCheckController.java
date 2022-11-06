package com.example.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController 
{
    //private
    private final String SITE_IS_UP     = "Site Is Up";
    private final String SITE_IS_DOWN   = "Site Is Down";
    private final String iNCORRECT_URL  = "URL Is Incorrect";

    //public
    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url)
    {
        String szReturnMessage;
        int nResponseCodeCategory;
        szReturnMessage = "";
        try 
        {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
           
            nResponseCodeCategory = conn.getResponseCode()/100;
            if(nResponseCodeCategory != 2)
            {
                szReturnMessage = SITE_IS_DOWN;
            }
            else
            {
                szReturnMessage = SITE_IS_UP;
            }
        } 
        catch (MalformedURLException e) 
        {
            szReturnMessage = iNCORRECT_URL;
        } 
        catch (IOException e) 
        {
            szReturnMessage = SITE_IS_DOWN;
        }

        return szReturnMessage;
    }   
}
