//Oyindamola Olaosun C00313475 OOSD Project

package service;

import ui.WelcomeScreen;
/**
 * This is responsible for running the application
 */
public class RunApp {

    /**
     * The application will start at the WelcomeScreen to introduce the user into the interface
     */
    public static void main(String[] args){
        new WelcomeScreen().setVisible(true);
    }
}