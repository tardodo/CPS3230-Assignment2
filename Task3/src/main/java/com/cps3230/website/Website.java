package com.cps3230.website;

import com.google.gson.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Website {

    public WebDriver driver;

    private String currentLog;

    public JsonArray alertList;

    public Website(WebDriver newDriver){

        driver = newDriver;
        driver.get("https://www.marketalertum.com/");
        currentLog = "";
        alertList = new JsonArray();
    }

    public void navToLogin(){
        List<WebElement> topBarOptions = driver.findElements(By.tagName("a"));
        WebElement login = topBarOptions.get(3);
        login.click();

    }

    public boolean login(String credentials){
        WebElement inputField = driver.findElement(By.id("UserId"));
        inputField.sendKeys(credentials);
        inputField.submit();
        String status = driver.getCurrentUrl();

        return status.contains("/Alerts/List");
    }

    public void goToAlerts(){
        List<WebElement> topBarOptions = driver.findElements(By.tagName("a"));
        WebElement alerts = topBarOptions.get(2);
        alerts.click();
    }

    public void uploadToAPI() throws Exception{
        Gson gson = new Gson();
        TechAlert alert = new TechAlert();
        String jsonProduct = gson.toJson(alert);

        URL url = new URL("https://api.marketalertum.com/Alert");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonProduct.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();
    }

    public void deleteFromAPI() throws Exception{

        URL url = new URL("https://api.marketalertum.com/Alert?userId=c483fe67-d39d-429a-9afa-273d26d2fe35");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json");


        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();
    }

    public void getEventsLog() throws Exception{

        URL url = new URL("https://api.marketalertum.com/EventsLog/c483fe67-d39d-429a-9afa-273d26d2fe35");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");


        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        currentLog = prettyPrint(content);
    }

    private static String prettyPrint(StringBuffer jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(jsonString.toString());
        String prettyJson = gson.toJson(je);

        return prettyJson;
    }

    public void updateAlerts(){
        int eventType;
        JsonElement jelement = JsonParser.parseString(currentLog);
        JsonArray  eventList = jelement.getAsJsonArray();
        if(eventList.size() > 0){
            JsonObject jObj = eventList.get(eventList.size() - 1).getAsJsonObject();
            jObj = jObj.get("systemState").getAsJsonObject();
            JsonArray alerts = jObj.get("alerts").getAsJsonArray();
            alertList = alerts;
        }
    }

    public boolean isLoggedIn() throws Exception {
        URL url = new URL("https://api.marketalertum.com/EventsLog/c483fe67-d39d-429a-9afa-273d26d2fe35/login-status");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");


        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        String neatContent = prettyPrint(content);
        JsonObject jObj = JsonParser.parseString(neatContent).getAsJsonObject();

        return jObj.get("isLoggedInOnWebsite").getAsBoolean();
    }
}
