package com.example.app.base;

import com.example.app.config.ConfigLoader;
import com.example.app.core.http.TokenContext;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
public abstract class BaseApiTest {

    @BeforeClass
    public void setUpApi() {
        RestAssured.baseURI = ConfigLoader.get("base.url");
    }

    @AfterMethod(alwaysRun = true)
    public void clearToken() {TokenContext.clear();}
}
