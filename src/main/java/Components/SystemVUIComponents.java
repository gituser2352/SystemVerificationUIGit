package Components;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import resources.base;

public class SystemVUIComponents extends base{
	public WebDriver driver;
	public static Logger log=LogManager.getLogger(base.class.getName());	
	public SystemVUIComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	base b = new base();
		
	
	public void ClosePopup() throws IOException, InterruptedException {
		//driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
		//Accept cookie
		b.click("btnAcceptcookie");
		//Capturing screenshot before closing popup
		//	b.getScreenshot("PopupScreenshot");
	/*		try {
				b.click("closeregionpopup");
				log.info("Closed the popup Successfully");
				System.out.println("Closed the popup Successfully");
				b.getScreenshot("afterpopupclosed");
				
			} catch (Exception e) {

				log.error("No such popup to close "+e);
				System.out.println("No such popup to close "+e);
				b.getScreenshot("Nosuchpopup");
			}*/
			Thread.sleep(2000);
	}

	//Verify header elements
	public void verifyHeaderElements() throws SQLException, IOException {
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		String[] ExpectedHeaderelemnts=null;
		String datbasename=prop.getProperty("datbasename");
		String querytoexecute=prop.getProperty("querytoexecute");
		
		ExpectedHeaderelemnts=GetdbRecords(datbasename, querytoexecute);
		
		for(int i=0;i<5;i++)
		{
		System.out.println("Expected Header element"+i+"is "+ExpectedHeaderelemnts[i]);	
		}
		
		String ActualHeaderelements[]={"abc","abc","abc","abc","abc"};
		ActualHeaderelements[0]=b.getelementtext("Headerelement1");
		ActualHeaderelements[1]=b.getelementtext("Headerelement2");
		ActualHeaderelements[2]=b.getelementtext("Headerelement3");
		ActualHeaderelements[3]=b.getelementtext("Headerelement4");
		ActualHeaderelements[4]=b.getelementtext("Headerelement5");
		
		String Highlighelement="Headerelement";
		int j=1;
		//verify if the text of each header element is displayed correctly or not
		for(int i=0;i<5;i++)
		{
			try {
				
				Assert.assertTrue(ActualHeaderelements[i].equalsIgnoreCase(ExpectedHeaderelemnts[i]));
				Highlighelement=Highlighelement+j;
				System.out.println("Element to highlight is"+Highlighelement);
				b.drawHighlight(Highlighelement);
				log.info("Text is displayed correctly for the the element "+ExpectedHeaderelemnts[i]);
				System.out.println("Text is displayed correctly for the the element "+ExpectedHeaderelemnts[i]);
				Highlighelement="Headerelement";
				j++;
			} catch (Exception e) {

				log.error("Text is displayed wrongly for the the element "+ExpectedHeaderelemnts[i]+e);
				System.out.println("Text is displayed wrongly for the the element "+ExpectedHeaderelemnts[i]);
			}

			
		}
		
		}	
	
	//Verify Insights Sub link
	public void VerifyInsightslink() throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
		//Click Insights link
		b.drawHighlight("lnkInsights");
		b.click("lnkInsights");
				
		//Get expected text
		String Expectedtext=prop.getProperty("InsightspageExpectedtext");
		System.out.println("Expected text is "+Expectedtext);
		Thread.sleep(3000);
		
		//Capture screenshot of result
		b.getScreenshot("Insightslinkpage");
		//verify if navgated to our offers page correctly or not
		String Actualtext = b.getelementtext("eltInsightspageresult");
		
		System.out.println("Actual text is"+Actualtext);
		
		try {
			Assert.assertTrue(Actualtext.contains(Expectedtext));
			log.info("Text is displayed correctly for Insights link page. ");
			System.out.println("Text is displayed correctly for Insights link page. ");
			b.drawHighlight("Companiespageresult");
		} catch (Exception e) {

			log.error("Text is displayed wrongly for Insights link page "+e);
			System.out.println("Text is displayed wrongly for Insights link page ");
		}	
		
		
	}

	//Verify About-Awards Sub link
	public void VerifyAwardsSublink() throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
		//Click About link
	//	b.click("lnkAbout");
		
		//Click Awards link
	//	b.drawHighlight("lnkAwards");
	//	b.click("lnkAwards");
			
		b.mouseoverandclick("lnkAbout", "lnkAwards");
		//Get expected text
		String Expectedtext=prop.getProperty("AwardspageExpectedtext");
		System.out.println("Expected text is "+Expectedtext);
		Thread.sleep(3000);
		
		//Capture screenshot of result
		b.getScreenshot("Awardslinkpage");
		//verify if navgated to our offers page correctly or not
		String Actualtext = b.getelementtext("eltAwardspageresult");
		
		System.out.println("Actual text is"+Actualtext);
		
		try {
			Assert.assertTrue(Actualtext.contains(Expectedtext));
			log.info("Text is displayed correctly for Awards Sub link page. ");
			System.out.println("Text is displayed correctly for Awards Sub link page. ");
			b.drawHighlight("eltAwardspageresult");
		} catch (Exception e) {

			log.error("Text is displayed wrongly for Awards Sub link page "+e);
			System.out.println("Text is displayed wrongly for Awards Sub link page ");
		}	
		Thread.sleep(3000);
		
	}

}
