package stepDefinitions;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepsDefsEspecialidades {

	private final String IP_ADDRESS = System.getProperty("environment");

	private final String BASE_URL = "http://" + IP_ADDRESS + ":4200/petclinic";

	private WebDriver driver;

	@Given("Selecciono la opcion especialidades")
	public void selecciono_la_opcion_especialidades() throws MalformedURLException {

		if (IP_ADDRESS.equalsIgnoreCase("localhost")) {
			// Local
			System.setProperty("webdriver.chrome.driver",
					"/home/dany/Desktop/2021/g7/chromedriver_linux64/chromedriver");
			driver = new ChromeDriver();
		} else {
			// Remoto
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(BrowserType.CHROME);
			dc.setCapability("platform", Platform.LINUX);
			dc.setCapability("project", "E2E PetClinic");
			dc.setCapability("name", "Feature: Crear especialidad");
			dc.setCapability("build", "1.0.0");
			dc.setCapability("tz", "America/Lima");
			driver = new RemoteWebDriver(new URL("http://" + IP_ADDRESS + ":4444/wd/hub"), dc);
		}

		driver.get(BASE_URL);
		driver.findElement(By.xpath("/html/body/app-root/div[1]/nav/div/ul/li[5]/a")).click();
	}
	
	@When("Le doy clic en Agregar")
	public void le_doy_clic_en_Agregar() {
		driver.findElement(By.xpath("/html/body/app-root/app-specialty-list/div/div/div/button[2]")).click();
	}

	@When("Ingreso el nombre de la especialidad con valor {string}")
	public void ingreso_el_nombre_de_la_especialidad_con_valor(String nombre) {
		driver.findElement(By.id("name")).sendKeys(nombre);
	}

	@When("Le doy clic en Guardar")
	public void le_doy_clic_en_Guardar() {
		driver.findElement(By.xpath("//*[@id=\"specialty\"]/div[2]/div/button")).click();
	}

	@Then("Se muestra a lista de especialidades")
	public void se_muestra_a_lista_de_especialidades() {
		Assert.assertEquals(driver.findElements(By.xpath("//*[@id=\"specialties\"]/tbody")).size(), 1);
		driver.quit();
	}

}
