package com;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Demo_Claims extends Mainclass{
	@BeforeTest
	public void browser() throws InterruptedException, IOException
	{
//		openbrowser();
		clearExcel();																			//Open the excel file, clear the existing data and print current date n time 
		claimsModuleActions();
	}

	@Test(priority=1, description="Performing login action")
	public void applogin() throws InterruptedException, IOException
	{
		//		try
		//		{
		System.out.println("\nPerforming login action");
		updateMethodHeadinginExcel("Performing login action");
		driver.navigate().to(fileread.getDemoURL());
		if(id("login_window_header").isDisplayed())
		{
			System.out.println("\tPage loaded successfully");
			updateFailedTestResultinExcel("Page loaded successfully");
		}
		else
		{
			System.out.println("\tFAIL - Page not loaded");
			updateFailedTestResultinExcel("FAIL - Page not loaded");
		}
		id("user_id-inputEl").clear();
		id("user_id-inputEl").sendKeys("alex1");
		id("user_pwd-inputEl").clear();
		id("user_pwd-inputEl").sendKeys("Demo@demo1");
		id("login_button").click();
		Thread.sleep(10000);
		if(xpath(Menubutton).isDisplayed())
		{
			System.out.println("\tUser logged in successfully");
			updatePassedTestResultinExcel("User logged in successfully");
			Thread.sleep(2000);
		}
		else
		{
			System.out.println("\tFAIL - Unable to login the site");
			updateFailedTestResultinExcel("FAIL - Unable to login the site");
			Thread.sleep(2000);
		}  	
		//		}
		//		catch(Exception e)
		//		{
		//			System.out.println("Test case fail due to this exception" +e);
		//		}
	}

	@Test(priority=2, description ="Performing trade upload action in Claims panel")
	public void uploadClaimsTrade() throws InterruptedException, FindFailed, IOException
	{
		//			try
		//			{
		System.out.println("\nPerforming trade upload action in Claims panel");
		updateMethodHeadinginExcel("Performing trade upload action in Claims panel");
		openClaimsmodule();																															//Open Claims module
		xpath(Claims_ClaimPendingtab).click();																										//Click ClaimPending panel in Claims panel
		int ClaimPending = Integer.parseInt(xpath(Claims_ClaimPendingtab).getText().replace("ClaimPending (", "").replace(")", ""));				//Get Total no.of trades in Claim Pending in Claims panel before trade upload	
		System.out.println("\tTotal no.of Claim Pending trade in Claims before trade upload = "+ClaimPending);	
		updateExcel("Total no.of Claim Pending trade in Claims before trade upload = "+ClaimPending);
		xpath(Claims_CSVbutton).click();																											//Click CSV icon in Claims panel
		Thread.sleep(3000);
		if(id("claim-import-window_header-title-textEl").isDisplayed())																				//Click choose file button
		{
			id("ImportClaim-button-fileInputEl").click();
			Thread.sleep(2500);
		}	
		clickDesktopButton();																														//Click Desktop button using Sikuli
		uploadClaimsTradeCSV();																														//Upload Claims Trade CSV file(using sikuli)	
		clickOpenButton();																															//click open button using Sikuli
		xpath(Claims_CSVUploadbutton).click();																										//Click Upload button
		Thread.sleep(5000);		
		int ClaimPending1 = Integer.parseInt(xpath(Claims_ClaimPendingtab).getText().replace("ClaimPending (", "").replace(")", ""));				//Get Total no.of trades in Claim Pending in Claims panel after trade upload
		if(ClaimPending1==ClaimPending+3)
		{
			System.out.println("\tTotal no.of Claim Pending trade in Claims after trade upload = "+ClaimPending1);
			updateExcel("Total no.of Claim Pending trade in Claims after trade upload = "+ClaimPending1);
			System.out.println("\tTrade uploaded in Claims panel successfully");
			updatePassedTestResultinExcel("Trade uploaded in Claims panel successfully");
		}
		else
		{
			System.out.println("\tFAIL - Unable to upload the trades in Claims panel");
			updateFailedTestResultinExcel("FAIL - Unable to upload the trades in Claims panel");
		}
		closeClaims();  																															//Close Claims Module
		//			}
		//			catch(Exception e)
		//			{
		//				System.out.println("Test case fail due to this exception" +e);
		//			}
	}

	@Test(priority=3, description="Performing Affirm action in Claims panel")
	public void affirmAction() throws InterruptedException, FindFailed, IOException
	{
		//			try
		//			{
		System.out.println("\nPerforming Affirm action in Claims panel");
		updateMethodHeadinginExcel("Performing Affirm action in Claims panel");
		Thread.sleep(1500);
		openClaimsmodule();																														//Open Claims module
		xpath(Claims_Bookedtab).click();																										//Click Booked tap in Claims panel
		int Booked = Integer.parseInt(xpath(Claims_Bookedtab).getText().replace("Booked (", "").replace(")", ""));								//Get Total no.of trades in Booked Trades in Claims panel before Affirm
		System.out.println("\tTotal no.of Booked trade in Claims before Affirm = "+Booked);
		updateExcel("Total no.of Booked trade in Claims before Affirm = "+Booked);
		Thread.sleep(1500);
		xpath(Claims_ClaimPendingtab).click();																									//Click ClaimPending panel in Claims panel
		int ClaimPending = Integer.parseInt(xpath(Claims_ClaimPendingtab).getText().replace("ClaimPending (", "").replace(")", ""));			//Get Total no.of trades in Claim Pending in Claims panel before Affirm	
		System.out.println("\tTotal no.of Claim Pending trade in Claims before Affirm = "+ClaimPending);		
		updateExcel("Total no.of Claim Pending trade in Claims before Affirm = "+ClaimPending);
		Thread.sleep(1500);
		String State_ClaimPending = xpath(Claims_ClaimPendingtab_Tradestate).getText();															//Check the trade state on ClaimPending tap in CCP Trades panel
		if(State_ClaimPending.equals("ClaimPending"))
		{
			Actions CCP_Ridgtclick = new Actions(driver);
			CCP_Ridgtclick.moveToElement(xpath(Claims_ClaimPendingtab_Tradestate)).build().perform();
			CCP_Ridgtclick.contextClick(xpath(Claims_ClaimPendingtab_Tradestate)).build().perform();
		}	
		else
		{
			System.out.println("\tState not matched to Claim Pending - Test case fail");
			updateFailedTestResultinExcel("State not matched to Claim Pending - Test case fail");
		}
		clickAffirm();																																//click Affirm(using sikuli)
		xpath(Claims_Bookedtab).click();																											//Click Booked tap in Claims panel
		int Booked1 = Integer.parseInt(xpath(Claims_Bookedtab).getText().replace("Booked (", "").replace(")", ""));									//Get Total no.of trades in Booked Trades in Claims panel after Affirm	
		Thread.sleep(1500);
		xpath(Claims_ClaimPendingtab).click();																										//Click ClaimPending panel in Claims panel
		int ClaimPending1 = Integer.parseInt(xpath(Claims_ClaimPendingtab).getText().replace("ClaimPending (", "").replace(")", ""));				//Get Total no.of trades in Claim Pending in Claims panel after Affirm		
		if((ClaimPending1==ClaimPending-1)&&(Booked1==Booked+1))
		{
			System.out.println("\tTotal no.of Booked trade in Claims after Affirm = "+Booked1);
			updateExcel("Total no.of Booked trade in Claims after Affirm = "+Booked1);
			System.out.println("\tTotal no.of Claim Pending trade in Claims after Affirm = "+ClaimPending1);	
			updateExcel("Total no.of Claim Pending trade in Claims after Affirm = "+ClaimPending1);
			System.out.println("\tAffirm Action performed successfully");
			updatePassedTestResultinExcel("Affirm Action performed successfully");
		}
		else
		{
			System.out.println("\tFAIL - Unable to perform Affirm action");
			updateFailedTestResultinExcel("FAIL - Unable to perform Affirm action");
		}
		closeClaims();  																															//Close Claims Module
		Thread.sleep(5000);
		//			}
		//			catch(Exception e)
		//			{
		//				System.out.println("Test case fail due to this exception" +e);
		//			}
	}

	@Test(priority=4, description="Performing Reject action in Claims panel")
	public void rejectAction() throws InterruptedException, FindFailed, IOException
	{
		//			try
		//			{
		System.out.println("\nPerforming Reject action in Claims panel");
		updateMethodHeadinginExcel("Performing Reject action in Claims panel");
		openClaimsmodule();																															//Open Claims module
		xpath(Claims_Refusedtab).click();																											//Click Refused tap in Claims panel
		int Refused = Integer.parseInt(xpath(Claims_Refusedtab).getText().replace("Refused (", "").replace(")", ""));								//Get Total no.of trades in Refused Trades in Claims panel before Reject	
		System.out.println("\tTotal no.of Refused trade in Claims before Reject = "+Refused);	
		updateExcel("Total no.of Refused trade in Claims before Reject = "+Refused);
		Thread.sleep(1500);
		xpath(Claims_ClaimPendingtab).click();																										//Click ClaimPending panel in Claims panel
		int ClaimPending = Integer.parseInt(xpath(Claims_ClaimPendingtab).getText().replace("ClaimPending (", "").replace(")", ""));				//Get Total no.of trades in Claim Pending in Claims panel before Reject	
		System.out.println("\tTotal no.of Claim Pending trade in Claims before Reject = "+ClaimPending);	
		updateExcel("Total no.of Claim Pending trade in Claims before Reject = "+ClaimPending);
		Thread.sleep(1500);
		String State_ClaimPending = xpath(Claims_ClaimPendingtab_Tradestate).getText();																//Check the trade state on ClaimPending tap in CCP Trades panel
		if(State_ClaimPending.equals("ClaimPending"))
		{
			Actions CCP_Ridgtclick = new Actions(driver);
			CCP_Ridgtclick.moveToElement(xpath(Claims_ClaimPendingtab_Tradestate)).build().perform();
			CCP_Ridgtclick.contextClick(xpath(Claims_ClaimPendingtab_Tradestate)).build().perform();
		}	
		else
		{
			System.out.println("\tFAIL - State not matched to Claim Pending");
			updateFailedTestResultinExcel("FAIL - State not matched to Claim Pending");
		}
		clickReject();																																//click Reject(using sikuli)
		xpath(Claims_Refusedtab).click();																											//Click Refused tap in Claims panel
		int Refused1 = Integer.parseInt(xpath(Claims_Refusedtab).getText().replace("Refused (", "").replace(")", ""));								//Get Total no.of trades in Refused Trades in Claims panel after Reject	
		Thread.sleep(1500);
		xpath(Claims_ClaimPendingtab).click();																										//Click ClaimPending panel in Claims panel
		int ClaimPending1 = Integer.parseInt(xpath(Claims_ClaimPendingtab).getText().replace("ClaimPending (", "").replace(")", ""));				//Get Total no.of trades in Claim Pending in Claims panel after Reject			
		if((ClaimPending1==ClaimPending-1)&&(Refused1==Refused+1))
		{
			System.out.println("\tTotal no.of Refused trade in Claims after Reject = "+Refused1);	
			updateExcel("Total no.of Refused trade in Claims after Reject = "+Refused1);
			System.out.println("\tTotal no.of Claim Pending trade in Claims after Reject = "+ClaimPending1);	
			updateExcel("Total no.of Claim Pending trade in Claims after Reject = "+ClaimPending1);
			System.out.println("\tReject Action performed successfully");
			updatePassedTestResultinExcel("Reject Action performed successfully");
		}
		else
		{
			System.out.println("\tFAIL - Reject action");
			updateFailedTestResultinExcel("FAIL - Reject action");
		}
		closeClaims();	  																													//Close Claims Module
		Thread.sleep(5000);
		//			}
		//			catch(Exception e)
		//			{
		//				System.out.println("Test case fail due to this exception" +e);
		//			}
	}

	@Test(priority=5, description="Performing Allocate action in Claims panel")
	public void allocateAction() throws InterruptedException, FindFailed, IOException
	{
		//			try
		//			{
		System.out.println("\nPerforming Allocate action in Claims panel");
		updateMethodHeadinginExcel("Performing Allocate action in Claims panel");
		openClaimsmodule();																													//Open Claims module
		xpath(ClientAllocation_Minimize).click();																							//Minimize Client Allocation panel
		Thread.sleep(1500);
		xpath(Allocatedticket_Bookedtab).click();																							//Click Booked tap in Allocated Tickets panel before Allocate action
		int AT_Booked = Integer.parseInt(xpath(Allocatedticket_Bookedtab).getText().replace("Booked (", "").replace(")", ""));				//Get Total no.of Booked Trades in Allocates Tickets panel before Allocate action
		System.out.println("\tTotal no.of Booked trade in Allocated Tickets panel before Allocation = "+AT_Booked);
		updateExcel("Total no.of Booked trade in Allocated Tickets panel before Allocation = "+AT_Booked);
		xpath(Claims_Allocatedtab).click();																									//Click Allocated tap in Claims panel before Allocate action
		int Allocated = Integer.parseInt(xpath(Claims_Allocatedtab).getText().replace("Allocated (", "").replace(")", ""));					//Get Total no.of Allocated Trades in Claims panel before Allocate action
		System.out.println("\tTotal no.of Allocated trade in Claims panel before Allocation = "+Allocated);	
		updateExcel("Total no.of Allocated trade in Claims panel before Allocation = "+Allocated);
		xpath(Claims_Bookedtab).click();																									//Click Booked tap in Claims panel before Allocate action
		int Booked = Integer.parseInt(xpath(Claims_Bookedtab).getText().replace("Booked (", "").replace(")", ""));							//Get Total no.of Booked Trades in Claims panel before Allocate action
		System.out.println("\tTotal no.of Booked trade in Claims panel before Allocation = "+Booked);	
		updateExcel("Total no.of Booked trade in Claims panel before Allocation = "+Booked);
		sort_Claims_Booked_ls();																											//Sort Last Touched in Booked tap in Claims panel
		String Booked_State = xpath(Claims_Bookedtab_Tradestate).getText();																	//Check the trade state on Booked tap in Claims panel
		Thread.sleep(1500);
		if(Booked_State.equals("Booked"))
		{
			Actions CCP_Ridgtclick = new Actions(driver);
			CCP_Ridgtclick.moveToElement(xpath(Claims_Bookedtab_Tradestate)).build().perform();
			CCP_Ridgtclick.contextClick(xpath(Claims_Bookedtab_Tradestate)).build().perform();
		}	
		else
		{
			System.out.println("\tFAIL - State not matched to Booked");
			updateFailedTestResultinExcel("FAIL - State not matched to Booked");
		}
		Actions CCP_Ridgtclick1 = new Actions(driver);																						//Hover to Allocate menu	
		Thread.sleep(5000);
		CCP_Ridgtclick1.moveToElement(xpath(Claims_Allocateaction)).build().perform();
		Thread.sleep(4500);		
		xpath(Claims_AvreageAllocation).click();																							//Click Average Giveup	
		Thread.sleep(2500);
		xpath(Claims_AvreageAllocation_Applytemplate).click();																				//Click Apply template button
		Thread.sleep(2500);		
		id("bulkClaimAverageUpload-button-fileInputEl").click();																			//Click Choose button
		clickDesktopButton();																												//Click Desktop button using sikuli
		manualGiveupUploadTemplate();																										//Click Giveup Upload template 
		clickOpenButton();																													//click open button(using sikuli)
		id("fileClaimAverageUploadButton-btnInnerEl").click();																				//Click Upload Button
		Thread.sleep(2500);
		id("sub_Avg_Clm_AllocBtn-btnInnerEl").click();																						//Click Submit button
		Thread.sleep(5000);	
		xpath(CCPTrade_ConfirmSubmission).click();																							//Click Yes button Confirm Submission pop up
		Thread.sleep(5000);		
		xpath(Allocatedticket_Bookedtab).click();																								//Click Booked tap in Allocated Tickets panel after Allocate action
		int AT_Booked1 = Integer.parseInt(xpath(Allocatedticket_Bookedtab).getText().replace("Booked (", "").replace(")", ""));					//Get Total no.of Booked Trades in Allocates Tickets panel after Allocate action
		xpath(Claims_Allocatedtab).click();																										//Click Allocated tap in Claims panel after Allocate action
		int Allocated1 = Integer.parseInt(xpath(Claims_Allocatedtab).getText().replace("Allocated (", "").replace(")", ""));					//Get Total no.of Allocated Trades in Claims panel after Allocate action
		xpath(Claims_Bookedtab).click();																										//Click Booked tap in Claims panel after Allocate action
		int Booked1 = Integer.parseInt(xpath(Claims_Bookedtab).getText().replace("Booked (", "").replace(")", ""));								//Get Total no.of Booked Trades in Claims panel after Allocate action
		if((Booked1==Booked-1)&&(Allocated1==Allocated+1)&&(AT_Booked1==AT_Booked+1))	
		{
			System.out.println("\tTotal no.of Booked trade in Allocated Tickets panel after Allocation = "+AT_Booked1);
			updateExcel("Total no.of Booked trade in Allocated Tickets panel after Allocation = "+AT_Booked1);
			System.out.println("\tTotal no.of Allocated trade in Claims panel after Allocation = "+Allocated1);
			updateExcel("Total no.of Allocated trade in Claims panel after Allocation = "+Allocated1);
			System.out.println("\tTotal no.of Booked trade in Claims panel after Allocation = "+Booked1);	
			updateExcel("Total no.of Booked trade in Claims panel after Allocation = "+Booked1);
			System.out.println("\tAllocation action performed successfully");
			updatePassedTestResultinExcel("Allocation action performed successfully");
		}
		else
		{
			System.out.println("\tFAIL - Allocate action");
			updateFailedTestResultinExcel("FAIL - Allocate action");
		}
		closeClaims();	  																															//Close Claims Module
		Thread.sleep(5000);
		//			}
		//			catch(Exception e)
		//			{
		//				System.out.println("Test case fail due to this exception" +e);
		//			}
	}

	@Test(priority=6, description="Performing Auto matching action in Client Allocation panel")
	public void clientAllocationAutoAllocation() throws InterruptedException, FindFailed, IOException
	{
		//			try
		//			{
		System.out.println("\nPerforming Auto matching action in Client Allocation panel");
		updateMethodHeadinginExcel("Performing Auto matching action in Client Allocation panel");
		openClaimsmodule();																															//Open Claims module
		minimizeAllocatedTicketPanel();																												//minimize Allocated ticket panel	
		xpath(Claims_ClaimPendingtab).click();																										//Click ClaimPending panel in Claims panel
		int ClaimPending = Integer.parseInt(xpath(Claims_ClaimPendingtab).getText().replace("ClaimPending (", "").replace(")", ""));				//Get Total no.of trades in Claim Pending in Claims panel before Auto allocation
		System.out.println("\tTotal no.of Claim Pending trade in Claims before Auto allocation = "+ClaimPending);
		updateExcel("Total no.of Claim Pending trade in Claims before Auto allocation = "+ClaimPending);
		xpath(Claims_Allocatedtab).click();																											//Click Allocated tap in Claims panel
		int Allocated = Integer.parseInt(xpath(Claims_Allocatedtab).getText().replace("Allocated (", "").replace(")", ""));							//Get Total no.of trades in Allocated Trades in Claims panel before Auto allocation
		System.out.println("\tTotal no.of Allocated trade in Claims before Auto allocation = "+Allocated);
		updateExcel("Total no.of Allocated trade in Claims before Auto allocation = "+Allocated);
		xpath(ClientAllocation_Matchedtab).click();																									//Click Matched tab in Client Allocation panel
		int Matched = Integer.parseInt(xpath(ClientAllocation_Matchedtab).getText().replace("Matched (", "").replace(")", ""));						//Get Total no.of trades in Matched in Client Allocation before Auto allocation	
		System.out.println("\tTotal no.of Matched trade in Client Allocation before Auto allocation =" + Matched);
		updateExcel("Total no.of Matched trade in Client Allocation before Auto allocation =" + Matched);
		clickClientAllocationCSV();																													//Click CSV button in Client Allocation panel
		clickDesktopButton();																														//Click Desktop button
		ClientAllocationCSVfile();																													//Upload Client Allocation CSV file
		clickOpenButton();																															//click open button
		xpath(ClientAllocation_CSVuploadbutton).click();																							//Click Upload button
		Thread.sleep(5000);
		int ClaimPending1 = Integer.parseInt(xpath(Claims_ClaimPendingtab).getText().replace("ClaimPending (", "").replace(")", ""));				//Get Total no.of trades in Claim Pending in Claims panel after Auto allocation	
		int Allocated1 = Integer.parseInt(xpath(Claims_Allocatedtab).getText().replace("Allocated (", "").replace(")", ""));						//Get Total no.of trades in Allocated in Claims after Auto allocation	
		int Matched1 = Integer.parseInt(xpath(ClientAllocation_Matchedtab).getText().replace("Matched (", "").replace(")", ""));					//Get Total no.of trades in Matched in Client Allocation after Auto allocation	
		if((ClaimPending1==ClaimPending-1)&&(Matched1==Matched+1)&&(Allocated1==Allocated+1))	
		{
			System.out.println("\tTotal no.of Claim Pending trade in Claims after Auto allocation = "+ClaimPending1);
			updateExcel("Total no.of Claim Pending trade in Claims after Auto allocation = "+ClaimPending1);
			System.out.println("\tTotal no.of Allocated trade in Claims after Auto allocation =" + Allocated1);
			updateExcel("Total no.of Allocated trade in Claims after Auto allocation =" + Allocated1);
			System.out.println("\tTotal no.of Matched trade in Client Allocation after Auto allocation =" + Matched1);
			updateExcel("Total no.of Matched trade in Client Allocation after Auto allocation =" + Matched1);
			System.out.println("\tAuto allocation action performed successfully");
			updatePassedTestResultinExcel("Auto allocation action performed successfully");
		}
		else
		{
			System.out.println("\tFAIL - Claims Auto allocation");
			updateFailedTestResultinExcel("FAIL - Claims Auto allocation");
		}
		//Close Claims Module
		closeClaims();  	
		//			}
		//			catch(Exception e)
		//			{
		//				System.out.println("Test case fail due to this exception" +e);
		//			}
	}
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		// Here will compare if test is failing then only it will enter into if condition
		if(ITestResult.FAILURE==result.getStatus())
		{
			try 
			{
				// Create refernce of TakesScreenshot
				TakesScreenshot ts=(TakesScreenshot)driver;
				// Call method to capture screenshot
				File source=ts.getScreenshotAs(OutputType.FILE);
				// Copy files to specific location here it will save all screenshot in our project home directory and
				// result.getName() will return name of test case so that screenshot name will be same
				FileUtils.copyFile(source, new File("C:\\Users\\alex_raj\\workspace\\Clear\\Screenshot\\"+result.getName()+".png"));
				System.out.println("Screenshot taken");
			} 
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}
	}

//	@AfterTest
//	public void closeBrowser()
//	{
//		driver.quit();
//	}

}

