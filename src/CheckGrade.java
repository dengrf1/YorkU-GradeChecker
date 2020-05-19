package CheckGrade;

import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class CheckGrade {
	String account;
	String password;
	String course;
	int row;

	public CheckGrade(String account, String password, String course, int row) {
		this.account = account;
		this.password = password;
		this.course = course;
		this.row = row;
	}

	public void get() {
		//Create a new chrome tab silently
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless");
		WebDriver driver = new ChromeDriver(option);
		
		//YorkU login site
		driver.get("https://wrem.sis.yorku.ca/Apps/WebObjects/ydml.woa/wa/DirectAction/document?name=CourseListv1");
		
		//If specified element doesn't show, throw exception. Otherwise, type in username and password and click login button.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			driver.findElement(By.id("mli")).sendKeys(this.account);
			driver.findElement(By.id("password")).sendKeys(this.password);
			driver.findElement(By.name("dologin")).click();
		}
		catch(NoSuchElementException e) {
			driver.quit();
			JOptionPane.showMessageDialog(null, "Slow internet connection");
			throw new IllegalArgumentException();
		}
		
		//Check the user specified element, if value == null, quit chrome and wait. Otherwise, create an alert notify user his grade is posted.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			String grade = driver.findElement(By.xpath(
					"//body/form/center/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/table/tbody/tr/td/table[1]/tbody/tr["
							+ this.row + "]/td[4]"))
					.getText();
			driver.quit();
			if (grade.length() != 10) {
				JOptionPane.showMessageDialog(null, this.course + " grade has been posted " + grade);
				throw new IllegalArgumentException();
			}
		} catch (NoSuchElementException e) {
			driver.quit();
			JOptionPane.showMessageDialog(null, "Login failed");
			throw new IllegalArgumentException();
		}
	}
	
	//Make get() method runnable
	Runnable run = new Runnable() {
		public void run() {
			get();
		}
	};
	
	//some tests
	public static void main(String args[]) {
		
	}
}
