import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.lang.model.element.Element;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.*;
import javax.swing.*;
public class main {
    static WebDriver driver = new ChromeDriver();
    static JFrame frame = new JFrame("ASSIGNMENTS");

    public static void initJFrame(List<String> value){
        frame.setSize(300,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel(new GridBagLayout());
        ArrayList<JLabel> assins = new ArrayList<>();
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;

        for (String i: value) {
            JLabel temp = new JLabel(String.valueOf(value));
            assins.add(temp);
        }
        for (int i  = 0; i < assins.size();i++) {
            c.gridy  = i;
            c.gridx  = 0;
            c.weightx = 0.5;
            c.weighty = 0.5;
            panel.add(assins.get(i),c);

        }
        frame.add(panel);
        panel.revalidate();
        frame.revalidate();

    }


    public static void closeTabs(String originalHandle){
        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
    }
    public static void getItems(){
        driver.findElement(By.xpath("//*[@id=\"tile_4440583_17002_-1_4\"]/div/div[1]/div/div/button[2]")).click();
        wait(5);
        driver.findElement(By.xpath("//*[@id=\"tile_4440583_16438_-1_1\"]/div/div[1]/div/div/button[2]")).click();
        wait(9);
        driver.findElement(By.xpath("//*[@id=\"myFilters\"]/ul/li[1]/div/div/div/div[1]/span")).click();
        wait(5);
        driver.findElement(By.xpath("//*[@id=\"myFilters\"]/ul/li[2]/div/div/div/div[1]/span")).click();
        wait(5);



    }
    public static List<String> manageUserDataGrid(){
        WebElement table = driver.findElement(By.xpath("//*[@id=\"fullCalView\"]/div[3]/div/table/tbody/tr/td/div/div/div/div[2]/table"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<String> value = new ArrayList<String>();

        System.out.println(rows.size());

        for (int j=0; j<rows.size(); j++){
            System.out.println(rows.get(j).getText());
            value.add(rows.get(j).getText());
        }
        return value;
    }

    public static void wait(int Seconds){
        try {

            //sleep 5 seconds
            TimeUnit.SECONDS.sleep(Seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void login(){
        String password = "T1i2a3p4";
        driver.findElement(By.id("Username")).sendKeys("hberker19");
        for (int i = 0; i < password.length(); i++) {
            if(password.substring(i,i + 1).equals("3")){
                driver.findElement(By.id("Password")).sendKeys(Keys.NUMPAD3);
            }else{
                driver.findElement(By.id("Password")).sendKeys(password.substring(i,i + 1));
            }
            wait(1);
        }


        wait(1);
        driver.findElement(By.id("loginBtn")).click();
        wait(2);

    }
    public static void main(String[] args){
        String url = "https://cranbrook.myschoolapp.com/app#login";
        driver.get("https://cranbrook.myschoolapp.com/app/student#calendar");
        wait(5);
        String originalHandle = driver.getWindowHandle();
        closeTabs(originalHandle);
        login();
        wait(5);
        driver.findElement(By.id("fc-day-button")).click();
        originalHandle = driver.getWindowHandle();

        //getItems();
        wait(2);
        List<String> value = manageUserDataGrid();
        initJFrame(value);
        // Storing Title name in the String variable

    }

}
