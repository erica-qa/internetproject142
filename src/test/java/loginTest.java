// 1 - Bibliotecas/Imports

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// 2 - Classe
public class loginTest {

//2.1 - Atributos
    private WebDriver driver;
    
//2.2 - Funções e Métodos

@BeforeEach
public void iniciar() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://the-internet.herokuapp.com/login"); //Acessa a página de login do site "The Internet" (objeto do teste)
}

@AfterEach
public void finalizar() {
    driver.quit();
}

@Test @Order(1)
// Teste Positivo
public void caminhoFeliz() {
    driver.findElement(By.id("username")).sendKeys("tomsmith"); //Encontra e preenche o texto "tomsmith" na TextBox "Username"
    driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!"); //Encontra e preenche o texto "SuperSecretPassword!" na TextBox "Username"
    driver.findElement(By.cssSelector(".fa")).click(); //Encontra e clica no Botão "Login"
    assertEquals("Secure Area", driver.findElement(By.cssSelector("h2")).getText()); //Confirma se o login foi efetuado corretamente, verificando o texto da próxima página > https://the-internet.herokuapp.com/secure
    driver.findElement(By.cssSelector(".icon-2x")).click(); //Encontra e clica no Botão "Logout"  
    assertEquals("You logged out of the secure area!\n×", driver.findElement(By.id("flash")).getText()); //Confirma se o logout foi efetuado corretamente, verificando o texto da mensagem na página > https://the-internet.herokuapp.com/login
}

/*           TESTES NEGATIVOS
Teste       1	2	3	4	5	6	7	8
Usuário 	S	N	N	B	B	B	S	N
Senha	    N	S	N	B	S	N	B	B
Obs.: S=Certo; N=Errado e B=Em branco
*/
@Test @Order(2)
// Teste Negativo 01 - Usuário certo, senha errada
public void loginNegativo1() {
    driver.findElement(By.id("username")).sendKeys("tomsmith"); //Preenche o texto "tomsmith" na TextBox "Username"
    driver.findElement(By.id("password")).sendKeys("Negativo1"); //Preenche o texto "Negativo1" na TextBox "Password"
    driver.findElement(By.cssSelector(".fa")).click(); //Encontra e clica no Botão "Login"
    assertEquals("Your password is invalid!\n×", driver.findElement(By.id("flash")).getText()); // Confirmar mensagem de erro
}

@Test @Order(3)
// Teste Negativo 02 - Usuário errado, senha certa
public void loginNegativo2() {
    driver.findElement(By.id("username")).sendKeys("Negativo2"); //Preenche o texto "Negativo2" na TextBox "Username"
    driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!"); //Preenche o texto "SuperSecretPassword!" na TextBox "Password"
    driver.findElement(By.cssSelector(".fa")).click(); //Encontra e clica no Botão "Login"
    assertEquals("Your username is invalid!\n×", driver.findElement(By.id("flash")).getText()); // Confirmar mensagem de erro
}

@Test @Order(4)
// Teste Negativo 03 - Usuário errado, senha errada
public void loginNegativo3() {
    driver.findElement(By.id("username")).sendKeys("Negativo3"); //Preenche o texto "Negativo3" na TextBox "Username"
    driver.findElement(By.id("password")).sendKeys("Negativo3"); //Preenche o texto "Negativo3" na TextBox "Password"
    driver.findElement(By.cssSelector(".fa")).click(); //Encontra e clica no Botão "Login"
    assertEquals("Your username is invalid!\n×", driver.findElement(By.id("flash")).getText()); // Confirmar mensagem de erro
}

@Test @Order(5)
// Teste Negativo 04 - Usuário em branco, senha em branco
public void loginNegativo4() {
    driver.findElement(By.id("username")).sendKeys(""); //Deixa a TextBox "Username" em branco
    driver.findElement(By.id("password")).sendKeys(""); //Deixa a TextBox "Password" em branco
    driver.findElement(By.cssSelector(".fa")).click(); //Encontra e clica no Botão "Login"
    assertEquals("Your username is invalid!\n×", driver.findElement(By.id("flash")).getText()); // Confirmar mensagem de erro
}

@Test @Order(6)
// Teste Negativo 05 - Usuário em branco, senha em certa
public void loginNegativo5() {
    driver.findElement(By.id("username")).sendKeys(""); //Deixa a TextBox "Username" em branco
    driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!"); //Preenche o texto "SuperSecretPassword!" na TextBox "Password"
    driver.findElement(By.cssSelector(".fa")).click(); //Encontra e clica no Botão "Login"
    assertEquals("Your username is invalid!\n×", driver.findElement(By.id("flash")).getText()); // Confirmar mensagem de erro
}

@Test @Order(7)
// Teste Negativo 06 - Usuário em branco, senha errada
public void loginNegativo6() {
    driver.findElement(By.id("username")).sendKeys(""); //Deixa a TextBox "Username" em branco
    driver.findElement(By.id("password")).sendKeys("Negativo06"); //Preenche o texto "Negativo06" na TextBox "Password"
    driver.findElement(By.cssSelector(".fa")).click(); //Encontra e clica no Botão "Login"
    assertEquals("Your username is invalid!\n×", driver.findElement(By.id("flash")).getText()); // Confirmar mensagem de erro
}

@Test @Order(8)
// Teste Negativo 07 - Usuário certo, senha em branco
public void loginNegativo7() {
    driver.findElement(By.id("username")).sendKeys("tomsmith"); //Preenche o texto "tomsmith" na TextBox "Username"
    driver.findElement(By.id("password")).sendKeys(""); //Deixa a TextBox "Password" em branco
    driver.findElement(By.cssSelector(".fa")).click(); //Encontra e clica no Botão "Login"
    assertEquals("Your password is invalid!\n×", driver.findElement(By.id("flash")).getText()); // Confirmar mensagem de erro
}

@Test @Order(9)
// Teste Negativo 08 - Usuário errado, senha em branco
public void loginNegativo8() {
    driver.findElement(By.id("username")).sendKeys("Negativo8"); //Preenche o texto "Negativo8" na TextBox "Username"
    driver.findElement(By.id("password")).sendKeys(""); //Deixa a TextBox "Password" em branco
    driver.findElement(By.cssSelector(".fa")).click(); //Encontra e clica no Botão "Login"
    assertEquals("Your username is invalid!\n×", driver.findElement(By.id("flash")).getText()); // Confirmar mensagem de erro
}

}