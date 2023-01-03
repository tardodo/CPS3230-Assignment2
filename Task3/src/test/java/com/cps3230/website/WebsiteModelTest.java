package com.cps3230.website;

import com.cps3230.website.enums.WebsiteStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class WebsiteModelTest implements FsmModel {

    private WebDriver driver = new ChromeDriver();
    private Website sut = new Website(driver);

    private WebsiteStates webState = WebsiteStates.LOGGED_OUT;
    private int numOfAlerts = 0;

    @Override
    public WebsiteStates getState() {
        return webState;
    }

    @Override
    public void reset(boolean b) {
        if(b){
            try {
                sut.deleteFromAPI();
            }catch (Exception ignored){}
            driver.quit();
            driver = new ChromeDriver();
            sut = new Website(driver);
        }

        webState = WebsiteStates.LOGGED_OUT;
        numOfAlerts = 0;
    }

    public boolean goodLoginGuard(){
        return getState().equals(WebsiteStates.LOGGED_OUT) || getState().equals(WebsiteStates.ILLEGAL_DELETE);
    }
    public @Action void goodLogin(){
        sut.navToLogin();
        sut.login("c483fe67-d39d-429a-9afa-273d26d2fe35");

        webState = WebsiteStates.LOGGED_IN;
        boolean loggedIn = false;

        //Asserts
        try{
            sut.getEventsLog();
            sut.updateAlerts();
            loggedIn = sut.isLoggedIn();
        }catch (Exception ignored){}

        Assertions.assertEquals(numOfAlerts, sut.alertList.size());
        Assertions.assertTrue(loggedIn);
    }

    public boolean logoutGuard(){
        return getState().equals(WebsiteStates.LOGGED_IN);
    }
    public @Action void logout(){
        sut.navToLogin();

        webState = WebsiteStates.LOGGED_OUT;
        boolean loggedIn = true;

        try{
            loggedIn = sut.isLoggedIn();
        }catch (Exception ignored){}

        //Asserts
        Assertions.assertFalse(loggedIn);
    }

    public boolean viewAlertsGuard(){
        return getState().equals(WebsiteStates.LOGGED_IN);
    }
    public @Action void viewAlerts(){
        sut.goToAlerts();

        webState = WebsiteStates.LOGGED_IN;

        //Asserts
        boolean loggedIn = false;

        try{
            sut.getEventsLog();
            sut.updateAlerts();
            loggedIn = sut.isLoggedIn();
        }catch (Exception ignored){}

        //Asserts
        Assertions.assertEquals(numOfAlerts, sut.alertList.size());
        Assertions.assertTrue(loggedIn);
    }

    public boolean createAlertGuard(){
        return getState().equals(WebsiteStates.LOGGED_IN) || getState().equals(WebsiteStates.LOGGED_OUT);
    }
    public @Action void createAlert(){
        if(getState().equals(WebsiteStates.LOGGED_IN)){
            webState = WebsiteStates.LOGGED_IN;
        }else {
            webState = WebsiteStates.ILLEGAL_CREATE;
        }

        try {
            sut.uploadToAPI();
            numOfAlerts++;
        }catch (Exception ignored){}

        //Asserts
        try{
            sut.getEventsLog();
            sut.updateAlerts();
        }catch (Exception ignored){}

        Assertions.assertEquals(numOfAlerts, sut.alertList.size());
    }

    public boolean deleteAlertsGuard(){
        return (getState().equals(WebsiteStates.LOGGED_IN) && numOfAlerts > 0) || (getState().equals(WebsiteStates.LOGGED_IN) && numOfAlerts == 0) || (getState().equals(WebsiteStates.OVERFLOW)) || (getState().equals(WebsiteStates.ILLEGAL_CREATE)) || getState().equals(WebsiteStates.LOGGED_OUT);
    }
    public @Action void deleteAlerts(){
        if(getState().equals(WebsiteStates.LOGGED_IN)){
            if(numOfAlerts > 0) webState = WebsiteStates.LOGGED_IN;
            else webState = WebsiteStates.BAD_DELETE;
        }else if(getState().equals(WebsiteStates.OVERFLOW)){
            webState = WebsiteStates.LOGGED_IN;
        }else{
            webState = WebsiteStates.ILLEGAL_DELETE;
        }

        try {
            sut.deleteFromAPI();
        }catch (Exception ignored){}
        numOfAlerts = 0;

        //Asserts
        try{
            sut.getEventsLog();
            sut.updateAlerts();
        }catch (Exception ignored){}
        Assertions.assertEquals(numOfAlerts, sut.alertList.size());
    }

    public boolean badCreateGuard(){
        return getState().equals(WebsiteStates.LOGGED_IN);
    }
    public @Action void badCreate(){
        webState = WebsiteStates.OVERFLOW;

        try {
            sut.uploadToAPI();
            sut.uploadToAPI();
            sut.uploadToAPI();
            sut.uploadToAPI();
            sut.uploadToAPI();
            sut.uploadToAPI();
            numOfAlerts+=6;
        }catch (Exception ignored){}

        //Asserts
        try{
            sut.getEventsLog();
            sut.updateAlerts();
        }catch (Exception ignored){}

        Assertions.assertEquals(numOfAlerts, sut.alertList.size());
    }

    @Test
    public void WebsiteModelRunner() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\farad\\Documents\\chromedriver_win32\\chromedriver.exe");
        final GreedyTester tester = new GreedyTester(new WebsiteModelTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.
        tester.generate(500); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }
}
