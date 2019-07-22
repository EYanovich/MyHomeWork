package junitcucumber;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"features/Vk.feature", "features/Mail.feature"})
public class Tests {
}
