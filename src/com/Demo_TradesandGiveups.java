package com;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Demo_TradesandGiveups extends Mainclass 
{
	@BeforeTest
	public void browser() throws InterruptedException, IOException 
	{
		openbrowser();
		clearExcel();																// Open the excel file, clear the existing data and print current date n time
		tradesandgiveupsModuleActions();
	}

	@Test(priority = 1, description = "Performing login action")
	public void applogin() throws InterruptedException, IOException 
	{
		// try
		// {
		System.out.println("\nPerforming login action");
		updateMethodHeadinginExcel("Performing login action");
		driver.navigate().to(fileread.getDemoURL());
		if (id("login_window_header").isDisplayed()) 
		{
			System.out.println("\tPage loaded successfully");
			updateExcel("Page loaded successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - Page not loaded");
			updateFailedTestResultinExcel("FAIL - Page not loaded");
		}
		id("user_id-inputEl").clear();
		id("user_id-inputEl").sendKeys(fileread.getUsername());
		id("user_pwd-inputEl").clear();
		id("user_pwd-inputEl").sendKeys(fileread.getPassword());
		id("login_button").click();
		WebDriverWait Menubar = new WebDriverWait(driver, 60);
		Menubar.until(ExpectedConditions.elementToBeClickable(By.xpath(Menubutton)));
		if (xpath(Menubutton).isDisplayed()) 
		{
			System.out.println("\tUser logged in successfully");
			updatePassedTestResultinExcel("User logged in successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - Login Action");
			updateFailedTestResultinExcel("FAIL - Login Action");
		}
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 2, description = "Performing Change Password action") 
	public void changePasswordaction() throws Exception 
	{
		// try
		// {
		System.out.println("\nPerforming Change Password action");
		updateMethodHeadinginExcel("Performing Change Password action");
		clickUserMenubar();															// Click Menu bar
		clickSettings(); 															// Click Setting button
		resetpassword(); 															// Reset Password
		xpath(Menubutton).click();
		logout();																	// logout the session
		id("user_id-inputEl").clear();
		id("user_id-inputEl").sendKeys(fileread.getUsername());
		id("user_pwd-inputEl").clear();
		id("user_pwd-inputEl").sendKeys(fileread.getNewPassword());
		id("login_button-btnEl").click();
		WebDriverWait Menubar = new WebDriverWait(driver, 60);
		Menubar.until(ExpectedConditions.elementToBeClickable(By.xpath(Menubutton)));
		if (xpath(Menubutton).isDisplayed()) 
		{
			System.out.println("\tUser logged in successfully");
			updateExcel("User logged in successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - Login action");
			updateFailedTestResultinExcel("FAIL - Login action");
		}
		clickUserMenubar();															// Click Menu bar
		clickSettings();															// Click Setting button
		if (xpath(Settingwindow).isDisplayed()) 									// Click Change Passwords option
		{
			xpath(ChangePassword).click();
			id("old_password-inputEl").clear();
			id("old_password-inputEl").sendKeys(fileread.getNewPassword());
			id("new_password-inputEl").clear();
			id("new_password-inputEl").sendKeys(fileread.getOldPassword());
			id("confirm_new_password-inputEl").clear();
			id("confirm_new_password-inputEl").sendKeys(fileread.getOldPassword());
			xpath(ChangePassword_Submitbutton).click();
			WebDriverWait ResetToastmess = new WebDriverWait(driver, 60);
			ResetToastmess.until(ExpectedConditions.elementToBeClickable(By.xpath(ResetPassword_Toastmess)));
			if (xpath(ResetPassword_Toastmess).isDisplayed()) 
			{
				String Alertmess = xpath(ResetPassword_Toastcontent).getText();
				if (Alertmess.equals("Your Password has been changed successfully.")) 
				{
					System.out.println("\tPassword rechanged successfully");
					updatePassedTestResultinExcel("Password rechanged successfully");
				} 
				else 
				{
					System.out.println("\tFAIL - Unable to change the password");
					updateFailedTestResultinExcel("FAIL - Unable to change the password");
				}
			} 
			else 
			{
				System.out.println("\tFAIL - Change password Toast message is not being loaded");
				updateFailedTestResultinExcel("FAIL - Change password Toast message is not being loaded");
			}
		}
		xpath(CloseSettingwindow).click();											// Close the setting window
		Thread.sleep(2000);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 3, description = "Adding new tab action") 
	public void addNewTab() throws InterruptedException, IOException 
	{
		// try
		// {
		System.out.println("\nAdding new tab action");
		updateMethodHeadinginExcel("Adding new tab action");
		openTradesandGiveups();
		int TotalTab = driver.findElements(By.xpath(Totaltab)).size();							// Get total no.of tab before creating new tab
		System.out.println("\tTotal no.of existing tab before creating new tab = " + TotalTab);
		updateExcel("Total no.of existing tab before creating new tab = " + TotalTab);
		Thread.sleep(5000);
		id("addTradeButton-btnIconEl").click();													// Click Add New tab button in CCp Trade panel
		id("newTradeTabTitle-inputEl").clear();													// New Tap window
		id("newTradeTabTitle-inputEl").sendKeys(fileread.getTabname());
		xpath(Newtab_clickFirstcolumnoption).click();											// Click 1st column
		String selectAll = Keys.chord(Keys.CONTROL, "a");										// Select All the column
		xpath(Newtab_clickFirstcolumnoption).sendKeys(selectAll);
		Thread.sleep(1500);
		xpath(Movetorightwindow).click();														// Click Right arrow to move all the column to right
		id("move-next-trade-btnInnerEl").click();												// Click Next button
		Thread.sleep(2000);
		id("finish-trade-btnInnerEl").click();													// click Finish button
		WebDriverWait Moduleheader = new WebDriverWait(driver, 60);
		Moduleheader.until(ExpectedConditions.elementToBeClickable(By.id("tradeAllocation_header-title-textEl")));
		int TotalTab1 = driver.findElements(By.xpath(Totaltab)).size();							// Get total no.of tab before creating new tab
		System.out.println("\tTotal no.of tab after creating new tab = " + TotalTab1);
		updateExcel("Total no.of tab after creating new tab = " + TotalTab1);
		if (TotalTab1 == TotalTab + 1) 
		{
			System.out.println("\tNew Tab is created");
			updatePassedTestResultinExcel("New Tab is created");
		} 
		else 
		{
			System.out.println("\tFAIL - Unable to create New Tab");
			updateFailedTestResultinExcel("FAIL - Unable to create New Tab");
		}
		Thread.sleep(3500);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 4, description = "Deleting existing tab action") 
	public void deleteExistingTab() throws InterruptedException, IOException 
	{
		// try
		// {
		System.out.println("\nDeleting existing tab");
		updateMethodHeadinginExcel("Deleting existing tab");
		int TotalTab = driver.findElements(By.xpath(Totaltab)).size();							// Get total no.of tab before creating new tab
		System.out.println("\tTotal no.of existing tab before deleting tab = " + TotalTab);
		updateExcel("Total no.of existing tab before deleting tab = " + TotalTab);
		xpath(Deletetab).click();																// Delete Tab
		Thread.sleep(5000);
		int TotalTab1 = driver.findElements(By.xpath(Totaltab)).size();
		System.out.println("\tTotal no.of tab after deleting tab = " + TotalTab1);
		updateExcel("Total no.of tab after deleting tab = " + TotalTab1);
		if (TotalTab1 == TotalTab - 1) 
		{
			System.out.println("	Tab is deleted");
			updatePassedTestResultinExcel("Tab is deleted");
			Thread.sleep(2000);
		} 
		else 
		{
			System.out.println("\tFAIL - Unable to delete the tab");
			updateFailedTestResultinExcel("FAIL - Unable to delete the tab");
		}
		closeTradesandGiveup();																			// Close Trades & Giveups module
		Thread.sleep(3500);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 5, description = "Performing Trade upload action in CCP Trades panel")
	public void uploadCCPTrade() throws InterruptedException, FindFailed, IOException 
	{
		// try
		// {
		System.out.println("\nPerforming Trade upload action in CCP Trades panel");
		updateMethodHeadinginExcel("Performing Trade upload action in CCP Trades panel");
		openTradesandGiveups();																		// Open Trades & Giveup module
		xpath(ClickCCPTrades_Unmatchedtab).click();													// Click Unmatched tap in CCP Trades panel
		int Unmatched = Integer.parseInt(xpath(CCPTrades_TotalUnmatchedTrades).getText().replace("Unmatched (", "").replace(")", ""));				// Get Total no.of Unmatched_Trade in CCP Trades panel before trade upload
		System.out.println("\tTotal no.of Unatched trade in CCP Trades before trade upload = " + Unmatched);
		updateExcel("Total no.of Unatched trade in CCP Trades before trade upload = " + Unmatched);
		xpath(CCPTrades_CSVbutton).click();															// Click CSV icon in CCP Trades panel
		Thread.sleep(3000);
		if (id("trade-upload-window_header-title-textEl").isDisplayed()) 							// Click choose file button
		{
			id("fileUpload-button-fileInputEl").click();
			Thread.sleep(2500);
		} 
		else 
		{
			System.out.println("\tFAIL - Unable to click Choose button");
			updateFailedTestResultinExcel("FAIL - Unable to click Choose button");
		}
		clickDesktopButton();																		// Click Desktop button using Sikuli
		uploadTradeCSV();																			// Upload FO Trade CSV file using Sikuli
		clickOpenButton();																			// click open button using Sikuli
		xpath(CCPTrades_CSVUploadbutton).click();													// Click Upload button
		Thread.sleep(5000);
		xpath(ClickCCPTrades_Unmatchedtab).click();													// Click Unmatched tap in CCP Trades panel
		int Unmatched1 = Integer.parseInt(xpath(ClickCCPTrades_Unmatchedtab).getText().replace("Unmatched (", "").replace(")", ""));				// Get Total no.of Unmatched trade in CCP Trades panel after trade upload
		if (Unmatched1 == Unmatched + 1) 
		{
			System.out.println("\tTotal no.of Unatched trade in CCP Trades after trade upload = " + Unmatched1);
			updateExcel("Total no.of Unatched trade in CCP Trades after trade upload = " + Unmatched1);
			System.out.println("\tTrade uploaded in CCP Trades panel successfully");
			updatePassedTestResultinExcel("Trade uploaded in CCP Trades panel successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - Unable to Upload trades in CCP Trade panel");
			updateFailedTestResultinExcel("FAIL - Unable to Upload trades in CCP Trade panel");
		}
		closeTradesandGiveup();																		// Close Trades & Giveups module
		Thread.sleep(3500);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 6, description = "Performing Accept action in CCP Trades panel")
	public void acceptAction() throws InterruptedException, FindFailed, IOException 
	{
		// try
		// {
		System.out.println("\nPerforming Accept action in CCP Trades panel");
		updateMethodHeadinginExcel("Performing Accept action in CCP Trades panel");
		Thread.sleep(2500);
		openTradesandGiveups();																		// Open Trades & Giveup module
		minimizeFoPanel();																			// Minimize the FO Trades panel
		xpath(ClickCCPTrades_GiveupPendingtab).click();												// Click GiveupPending tap in CCP Trades panel
		int GiveupPending = Integer.parseInt(xpath(CCPTrade_TotalGiveupPendingTrades).getText().replace("GiveupPending (", "").replace(")", ""));				// Get Total no.of GiveupPending trades before Accept action
		System.out.println("\tTotal no.of Giveup Pending trade in CCP Trade before Accept action = " + GiveupPending);
		updateExcel("Total no.of Giveup Pending trade in CCP Trade before Accept action = " + GiveupPending);
		sort_CCPTrades_GiveupPendingtab_LS();														// Sort the Last Touched column of GivepPending tap
		xpath(ClickCCPTrades_Unmatchedtab).click();													// Click Unmatched tap in CCP Trades panel
		int Unmatched_Trade = Integer.parseInt(xpath(ClickCCPTrades_Unmatchedtab).getText().replace("Unmatched (", "").replace(")", ""));						// Get Total no.of Unmatched_Trade in CCP Trades panel before Accept action
		System.out.println("\tTotal no.of Unmatched trade in CCP Trade before Accept action = " + Unmatched_Trade);
		updateExcel("Total no.of Unmatched trade in CCP Trade before Accept action = " + Unmatched_Trade);
		sortUnmatched_LS();																			// Sort the Last Touched column of unmatched tap in CCP Trades panel
		String Unmatched_state_name = xpath(CCPTrades_Unmatahedtab_Tradestate).getText();			// Check the trade state on Unmatched tap in CCP Trades panel
		if (Unmatched_state_name.equals("Unmatched")) 
		{
			Actions CCP_Ridgtclick = new Actions(driver);
			CCP_Ridgtclick.moveToElement(xpath(CCPTrades_Unmatahedtab_Tradestate)).build().perform();
			CCP_Ridgtclick.contextClick(xpath(CCPTrades_Unmatahedtab_Tradestate)).build().perform();
		} 
		else 
		{
			System.out.println("\tFAIL - State not matched to Unmatched");
			updateFailedTestResultinExcel("FAIL - State not matched to Unmatched");
		}
		acceptUsingSikuli();																		// Click the Accept option from context menu bar(using sikuli)
		int Unmatched_Trade1 = Integer.parseInt(xpath(CCPTrades_TotalUnmatchedTrades).getText().replace("Unmatched (", "").replace(")", ""));				// Get Total no.of Unmatched_Trade in CCP Trades panel after Accept action
		Thread.sleep(1500);
		int GiveupPending1 = Integer.parseInt(xpath(CCPTrade_TotalGiveupPendingTrades).getText().replace("GiveupPending (", "").replace(")", ""));			// Get Total no.of GiveupPending trades After Accept action
		Thread.sleep(1500);
		if ((Unmatched_Trade1 == Unmatched_Trade - 1) && (GiveupPending1 == GiveupPending + 1)) 
		{
			System.out.println("\tTotal no.of Giveup Pending trades after Accept action = " + GiveupPending1);
			updateExcel("Total no.of Giveup Pending trades after Accept action = " + GiveupPending1);
			System.out.println("\tTotal no.of Unmatched trade after Accept action = " + Unmatched_Trade1);
			updateExcel("Total no.of Unmatched trade after Accept action = " + Unmatched_Trade1);
			System.out.println("\tAccpet action performed successfully");
			updatePassedTestResultinExcel("Accpet action performed successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - Accept action");
			updateFailedTestResultinExcel("FAIL - Accept action");
		}
		closeTradesandGiveup();																		// Close Trades & Giveups module
		Thread.sleep(3500);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 7, description = "Performing Giveup Action in CCP Trades panel")
	public void giveupAction() throws InterruptedException, FindFailed, IOException {
		// try
		// {
		System.out.println("\nPerforming Giveup Action in CCP Trades panel");
		updateMethodHeadinginExcel("Performing Giveup Action in CCP Trades panel");
		openTradesandGiveups();																		// Open Trades & Giveup module
		minimizeFoPanel();																			// Minimize the FO Trades panel
		xpath(ClickGiveups_ClaimPendingtab).click();												// Click ClaimPending tap in Giveups panel
		int ClaimPending = Integer.parseInt(xpath(Giveups_TotalClaimPendingTrades).getText().replace("ClaimPending (", "").replace(")", ""));				// Get total no.of ClaimPending trades in Giveups panel before Giveup action
		System.out.println("\tTotal no.of Claim Pending trade in Giveups panel before Giveup action = " + ClaimPending);
		updateExcel("Total no.of Claim Pending trade in Giveups panel before Giveup action = " + ClaimPending);
		xpath(ClickCCPTrade_Givenuptab).click();													// Click Giveup tap in CCP Trades panel
		int Givenup = Integer.parseInt(xpath(CCPTrades_TotalGivenupTrades).getText().replace("Givenup (", "").replace(")", ""));							// Get the total no.of Givenup trades in CCp Panel before Giveup action
		System.out.println("\tTotal no.of Givenup trade in CCP Trades panel before Giveup action = " + Givenup);
		updateExcel("Total no.of Givenup trade in CCP Trades panel before Giveup action = " + Givenup);
		xpath(ClickCCPTrades_GiveupPendingtab).click();												// Click Givepending tap in CCP Trades
		sort_CCPTrades_GiveupPending_LS();															// Sort last touched column Givepending tap in CCP Trades panel
		int GiveupPending = Integer.parseInt(xpath(CCPTrade_TotalGiveupPendingTrades).getText().replace("GiveupPending (", "").replace(")", ""));			// Get the total no.of GiveupPending trades in CCP Panel before Giveup action
		System.out.println("\tTotal no.of Giveup Pending trade in CCP Trades panel before Giveup action = " + GiveupPending);
		updateExcel("Total no.of Giveup Pending trade in CCP Trades panel before Giveup action = " + GiveupPending);
		String GiveupPending_state_name = xpath(CCPTrades_GiveupPendingtab_Tradestate).getText();	// Check the trade state on GiveupPending tap in CCP Trades panel
		if (GiveupPending_state_name.equals("GiveupPending")) 
		{
			Actions CCP_Ridgtclick = new Actions(driver);
			CCP_Ridgtclick.moveToElement(xpath(CCPTrades_GiveupPendingtab_Tradestate)).build().perform();
			CCP_Ridgtclick.contextClick(xpath(CCPTrades_GiveupPendingtab_Tradestate)).build().perform();
		} 
		else 
		{
			System.out.println("\tFAIL - State not matched to Giveup Pending");
			updateFailedTestResultinExcel("FAIL - State not matched to Giveup Pending");
		}
		Thread.sleep(1500);
		clickGiveups();																				// Click Giveups using Sikuli
		Thread.sleep(3500);
		xpath(CCPTrades_ManualGiveup).click();														// Click Manual Giveup
		Thread.sleep(3500);
		xpath(CCPTrades_ManualGiveup_Applytemplate).click();										// Click Apply template button
		Thread.sleep(2500);
		id("bulkproductUpload-button-fileInputEl").click();											// Click Choose button
		clickDesktopButton();																		// Click Desktop button using sikuli
		manualGiveupUploadTemplate();																// Click Giveup Upload template file using sikuli
		clickOpenButton();																			// click open button(using sikuli)
		id("fileUploadButton-btnInnerEl").click();													// Click Upload button
		Thread.sleep(1500);
		id("manSubmit-btnInnerEl").click();															// Click Submit button
		Thread.sleep(5000);
		xpath(CCPTrade_ConfirmSubmission).click();													// Click Yes button Confirm Submission pop up
		Thread.sleep(5000);
		xpath(ClickGiveups_ClaimPendingtab).click();												// Click ClaimPending tap in Giveups panel
		int ClaimPending1 = Integer.parseInt(xpath(Giveups_TotalClaimPendingTrades).getText().replace("ClaimPending (", "").replace(")", ""));				// Get total no.of ClaimPending trades in Giveups panel after Accept action
		xpath(ClickCCPTrade_Givenuptab).click();													// Click Givenup tap in CCP Trades panel
		int Givenup1 = Integer.parseInt(xpath(CCPTrades_TotalGivenupTrades).getText().replace("Givenup (", "").replace(")", ""));							// Get the total no.of Givenup trades in CCP Panel before Giveup action
		xpath(ClickCCPTrades_GiveupPendingtab).click();												// Click Givepending tap in CCP Trades
		int GiveupPending1 = Integer.parseInt(xpath(CCPTrade_TotalGiveupPendingTrades).getText().replace("GiveupPending (", "").replace(")", ""));			// Get the total no.of GiveupPending trades in CCP Panel before Giveup action
		if ((GiveupPending1 == GiveupPending - 1) && (Givenup1 == Givenup + 1) && (ClaimPending1 == ClaimPending + 1)) 
		{
			System.out.println("\tTotal no.of Claim Pending trade in Giveups before Giveup action = " + ClaimPending1);
			updateExcel("Total no.of Claim Pending trade in Giveups before Giveup action = " + ClaimPending1);
			System.out.println("\tTotal no.of Giveup trade in Givenup before Giveup action = " + Givenup1);
			updateExcel("Total no.of Giveup trade in Givenup before Giveup action = " + Givenup1);
			System.out.println("\tTotal no.of Giveup Pending trade in Giveups before Giveup action = " + GiveupPending1);
			updateExcel("Total no.of Giveup Pending trade in Giveups before Giveup action = " + GiveupPending1);
			System.out.println("\tGiveup action performed successfully");
			updatePassedTestResultinExcel("Giveup action performed successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - Giveup action");
			updateFailedTestResultinExcel("FAIL - Giveup action");
		}
		closeTradesandGiveup();																		// Close Trades & Giveups module
		Thread.sleep(3500);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 8, description = "Performing Cancel Action in CCP Trades panel")
	public void cancelAction() throws InterruptedException, FindFailed, IOException 
	{
		// try
		// {
		System.out.println("\nPerforming Cancel Action in CCP Trades panel");
		updateMethodHeadinginExcel("Performing Cancel Action in CCP Trades panel");
		openTradesandGiveups();																		// Open Trades & Giveup module
		minimizeFoPanel();																			// Minimize the FO Trades panel
		xpath(ClickCCPTrades_GiveupPendingtab).click();												// Click GiveupPending tap in CCP Trades panel
		int GiveupPending = Integer.parseInt(xpath(CCPTrade_TotalGiveupPendingTrades).getText().replace("GiveupPending (", "").replace(")", ""));				// Get Total no.of GiveupPending trades before Cancel action
		System.out.println("\tTotal no.of Giveup Pending trade in CCP Trade before Cancel action = " + GiveupPending);
		updateExcel("Total no.of Giveup Pending trade in CCP Trade before Cancel action = " + GiveupPending);
		xpath(ClickGiveups_Cancelledtab).click();													// Click Cancelled tap in Giveups panel
		int Cancelled = Integer.parseInt(xpath(Giveups_Cancelledtab_Totaltrades).getText().replace("Cancelled (", "").replace(")", ""));						// Get total no.of Cancelled trades in Giveups panel before Cancel action
		System.out.println("\tTotal no.of Cancelled trade in Giveups panel before Cancel action = " + Cancelled);
		updateExcel("Total no.of Cancelled trade in Giveups panel before Cancel action = " + Cancelled);
		xpath(ClickGiveups_ClaimPendingtab).click();												// Click ClaimPending tap in Giveups panel
		int ClaimPending = Integer.parseInt(xpath(Giveups_TotalClaimPendingTrades).getText().replace("ClaimPending (", "").replace(")", ""));					// Get total no.of ClaimPending trades in Giveups panel before Cancel action
		System.out.println("\tTotal no.of Claim Pending trade in Giveups panel before Cancel action = " + ClaimPending);
		updateExcel("Total no.of Claim Pending trade in Giveups panel before Cancel action = " + ClaimPending);
		sort_Giveups_ClaimPending_LS();																// Sort Last touched column in Claim Pending tab of Giveups panel
		String ClaimPending_state_name = xpath(Giveups_ClaimPendingtab_Tradestate).getText();		// Check the trade state on GiveupPending tap in Giveups panel
		if (ClaimPending_state_name.equals("ClaimPending")) 
		{
			Actions CCP_Ridgtclick = new Actions(driver);
			CCP_Ridgtclick.moveToElement(xpath(Giveups_ClaimPendingtab_Tradestate)).build().perform();
			CCP_Ridgtclick.contextClick(xpath(Giveups_ClaimPendingtab_Tradestate)).build().perform();
		} 
		else 
		{
			System.out.println("\tFAIL - State not matched to Claim Pending");
			updateFailedTestResultinExcel("FAIL - State not matched to Claim Pending");
		}
		giveupsPanelCancelAction();																	// Click Cancel menu using sikuli
		xpath(ClickCCPTrades_GiveupPendingtab).click();												// Click GiveupPending tap in CCP Trades panel
		int GiveupPending1 = Integer.parseInt(xpath(CCPTrade_TotalGiveupPendingTrades).getText().replace("GiveupPending (", "").replace(")", ""));				// Get Total no.of GiveupPending trades after Cancel action
		xpath(ClickGiveups_Cancelledtab).click();													// Click Cancelled tap in Giveups panel
		int Cancelled1 = Integer.parseInt(xpath(Giveups_Cancelledtab_Totaltrades).getText().replace("Cancelled (", "").replace(")", ""));						// Get total no.of Cancelled trades in Giveups panel after Cancel action
		xpath(ClickGiveups_ClaimPendingtab).click();												// Click ClaimPending tap in Giveups panel
		int ClaimPending1 = Integer.parseInt(xpath(Giveups_TotalClaimPendingTrades).getText().replace("ClaimPending (", "").replace(")", ""));					// Get total no.of ClaimPending trades in Giveups panel after Cancel action
		if ((GiveupPending1 == GiveupPending + 1) && (Cancelled1 == Cancelled + 1)
				&& (ClaimPending1 == ClaimPending - 1)) 
		{
			System.out.println("\tTotal no.of Giveup Pending trade in CCP Trade after Cancel action = " + GiveupPending1);
			updateExcel("Total no.of Giveup Pending trade in CCP Trade after Cancel action = " + GiveupPending1);
			System.out.println("\tTotal no.of Cancelled trade in Giveups panel after Cancel action = " + Cancelled1);
			updateExcel("Total no.of Cancelled trade in Giveups panel after Cancel action = " + Cancelled1);
			System.out.println("\tTotal no.of Claim Pending trade in Giveups panel after Cancel action = " + ClaimPending1);
			updateExcel("Total no.of Claim Pending trade in Giveups panel after Cancel action = " + ClaimPending1);
			System.out.println("\tCancel action performed successfully");
			updatePassedTestResultinExcel("Cancel action performed successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - Cancel action");
			updateFailedTestResultinExcel("FAIL - Cancel action");
		}
		closeTradesandGiveup();																		// Close Trades & Giveups module
		Thread.sleep(3500);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 9, description = "Performing Trade Amend action in CCP Trades panel")
	public void tradeAmand() throws InterruptedException, FindFailed, IOException 
	{
		// try
		// {
		System.out.println("\nPerforming Trade Amend action in CCP Trades panel");
		updateMethodHeadinginExcel("Performing Trade Amend action in CCP Trades panel");
		openTradesandGiveups();																		// Open Trades & Giveups module
		minimizeFoPanel();																			// Min FO trade panel
		xpath(ClickCCPTrades_Cancelledtab).click();													// Click Cancelled tap in CCP Trades panel
		int Cancelled = Integer.parseInt(xpath(CCPTrades_TotalCancelledTrades).getText().replace("Cancelled (", "").replace(")", ""));
		System.out.println("\tTotal no.of Cancelled Trade in CCP Trades panel before Trade Amend = " + Cancelled);
		updateExcel("Total no.of Cancelled Trade in CCP Trades panel before Trade Amend = " + Cancelled);
		Thread.sleep(1500);
		sortCCPTrades_Cancelledtab_LS();															// Sort Last touched in Cancelled tap in CCP Trades panel
		xpath(ClickCCPTrades_Unmatchedtab).click();													// Click Unmatched tab in CCP Trades panel
		int Unmatched = Integer.parseInt(xpath(CCPTrades_TotalUnmatchedTrades).getText().replace("Unmatched (", "").replace(")", ""));
		System.out.println("\tTotal no.of Unmatched Trade in CCP Trades panel before Trade Amend = " + Unmatched);
		updateExcel("Total no.of Unmatched Trade in CCP Trades panel before Trade Amend = " + Unmatched);
		sortUnmatched_LS();																			// Sort Last touched in Unmatched tap in CCP Trades panel
		String ClientAcc = xpath(CCPTrades_Unmatchedtab_ClientAcc).getText();						// Get the Client A/C of first trade before Trade Amend action
		System.out.println("\tClient A/C of Unmatched trade before trade Amend = " + ClientAcc);
		updateExcel("Client A/C of Unmatched trade before trade Amend = " + ClientAcc);
		Actions action = new Actions(driver);														// Right click action in Unmatched tap
		action.moveToElement(xpath(CCPTrades_Unmatchedtab_Rightclick)).build().perform();
		action.contextClick(xpath(CCPTrades_Unmatchedtab_Rightclick)).build().perform();
		tradeAmendUsingSikuli();																	// Click Trade Amend using Sikuli
		id("amendAccount-trigger-foo").click();														// Open Trade Properties window and Click Edit icon
		Thread.sleep(2500);
		if (id("account-tree-window_header-title-textEl").isDisplayed()) 							// Open Account window
		{
			xpath(CCPTrades_TradeAmend_Accwindow).click();
			id("account-tree-submit-btnInnerEl").click();											// Click Submit button
			id("amend_trade-btnInnerEl").click();													// Click Amend button
			Thread.sleep(5000);
		} 
		else 
		{
			System.out.println("\tFAIL - Account window is not opened");
			updateFailedTestResultinExcel("FAIL - Account window is not opened");
		}
		xpath(ClickCCPTrades_Unmatchedtab).click();													// Click Unmatched tab in CCP Trades panel
		int Unmatched1 = Integer.parseInt(xpath(CCPTrades_TotalUnmatchedTrades).getText().replace("Unmatched (", "").replace(")", ""));				// Get the total no.of Unmatched trade after Trade Amend
		String ClientAcc1 = xpath(CCPTrades_Unmatchedtab_ClientAcc).getText();						// Get the Client A/C of first trade after Trade Amend action
		xpath(ClickCCPTrades_Cancelledtab).click();													// Click Cancelled tap in CCP Trades panel after Trade Amend
		int Cancelled1 = Integer.parseInt(xpath(ClickCCPTrades_Cancelledtab).getText().replace("Cancelled (", "").replace(")", ""));				// Get total no.of Cancelled trade after Trade Amend action
		if ((Unmatched1 == Unmatched) && (Cancelled1 == Cancelled + 1) && (ClientAcc1.equals("AAA 001"))) 
		{
			System.out.println("\tTotal no.of Cancelled Trade in CCP Trades panel after Trade Amend = " + Cancelled1);
			updateExcel("Total no.of Cancelled Trade in CCP Trades panel after Trade Amend = " + Cancelled1);
			System.out.println("\tTotal no.of Unmatched Trade in CCP Trades panel after Trade Amend = " + Unmatched1);
			updateExcel("Total no.of Unmatched Trade in CCP Trades panel after Trade Amend = " + Unmatched1);
			System.out.println("\tClent A/C of Unmatched trade after trade Amend = " + ClientAcc1);
			updateExcel("Clent A/C of Unmatched trade after trade Amend = " + ClientAcc1);
			System.out.println("\tTrade Amend performed successfully");
			updatePassedTestResultinExcel("Trade Amend performed successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - Trade Amend");
			updateFailedTestResultinExcel("FAIL - Trade Amend");
		}
		closeTradesandGiveup();																		// Close Trades & Giveups module
		Thread.sleep(3500);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 10, description = "Performing Trade Split action in CCP Trades panel")
	public void tradeSplitaction() throws InterruptedException, FindFailed, IOException 
	{
		// try
		// {
		System.out.println("\nPerforming Trade Split action in CCP Trades panel");
		updateMethodHeadinginExcel("Performing Trade Split action in CCP Trades panel");
		openTradesandGiveups();																		// Open Trades & Giveups module
		minimizeFoPanel();																			// Min FO trade panel
		xpath(ClickCCPTrades_Cancelledtab).click();													// Click Cancelled tap in CCP Trades panel
		int Cancelled = Integer.parseInt(xpath(CCPTrades_TotalCancelledTrades).getText().replace("Cancelled (", "").replace(")", ""));
		System.out.println("\tTotal no.of Cancelled Trade in CCP Trades panel before Trade Split = " + Cancelled);
		updateExcel("Total no.of Cancelled Trade in CCP Trades panel before Trade Split = " + Cancelled);
		Thread.sleep(1500);	
		sortCCPTrades_Cancelledtab_LS();															// Sort Last touched in Cancelled tap in CCP Trades panel
		xpath(ClickCCPTrades_GiveupPendingtab).click();												// Click Giveup Pending tap in CCP Trades panel
		int GiveupPending = Integer.parseInt(xpath(CCPTrade_TotalGiveupPendingTrades).getText().replace("GiveupPending (", "").replace(")", ""));				// Get total no.of Giveup Pending trades before tradesplit action
		System.out.println("\tTotal no.of GiveupPending Trade in CCP Trades panel before Trade Split = " + GiveupPending);
		updateExcel("Total no.of GiveupPending Trade in CCP Trades panel before Trade Split = " + GiveupPending);
		Thread.sleep(1500);
		sort_CCPTrades_GiveupPendingtab_LS();														// Sort Last touched in Giveup Pending tap in CCP Trades panel
		Thread.sleep(2500);
		Actions action = new Actions(driver);														// Right click action in Giveup Pending tab
		action.moveToElement(xpath(CCPTrades_GiveupPendingtab_Rightclick)).build().perform();
		action.contextClick(xpath(CCPTrades_GiveupPendingtab_Rightclick)).build().perform();
		tradeSplit();																				// Click Trade Split using Sikuli
		xpath(CCPTrades_AddSplitbutton).click();													// Click Add Split button
		Thread.sleep(8000);
		xpath(CCPTrades_TradeSplit_ApplyTemplatebutton).click();									// Click Apply template button
		Thread.sleep(1500);	
		id("bulkUploadSplits-button-fileInputEl").click();											// Click choose file button
		clickDesktopButton();																		// Click Desktop button using sikuli
		tradeSplitUploadTemplate();																	// Click Trade Split Upload template using Sikuli
		clickOpenButton();																			// click open button(using sikuli)
		id("button_upload_splits_trade-btnInnerEl").click();										// Click Upload button
		Thread.sleep(1500);
		id("clear_ccptrades_split_submit-btnInnerEl").click();										// Click Submit button
		xpath(CCPTrade_ConfirmSubmission).click();													// Click Yes button in Confirmation pop up
		Thread.sleep(5000);
		xpath(ClickCCPTrades_Cancelledtab).click();													// Click Cancelled tap in CCP Trade panel after Trade Split
		int Cancelled1 = Integer.parseInt(xpath(CCPTrades_TotalCancelledTrades).getText().replace("Cancelled (", "").replace(")", ""));						// Get the total no.of Cancelled trades in CCP Trades panel after Trade Split
		xpath(ClickCCPTrades_GiveupPendingtab).click();												// Click Giveup Pending tap in CCP Trades panel after Trade Split action
		int GiveupPending1 = Integer.parseInt(xpath(CCPTrade_TotalGiveupPendingTrades).getText().replace("GiveupPending (", "").replace(")", ""));			// Get total no.of Giveup Pending trades before tradesplit action
		if ((Cancelled1 == Cancelled + 1) && (GiveupPending1 == GiveupPending + 1)) 
		{
			System.out.println("\tTotal no.of Cancelled Trade in CCP Trades panel before Trade Split = " + Cancelled1);
			updateExcel("Total no.of Cancelled Trade in CCP Trades panel before Trade Split = " + Cancelled1);
			System.out.println("\tTotal no.of GiveupPending Trade in CCP Trades panel before Trade Split = " + GiveupPending1);
			updateExcel("Total no.of GiveupPending Trade in CCP Trades panel before Trade Split = " + GiveupPending1);
			System.out.println("\tTrade Split action performed successfully");
			updatePassedTestResultinExcel("Trade Split action performed successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - Trade Split");
			updateFailedTestResultinExcel("FAIL - Trade Split");
		}
		closeTradesandGiveup();																		// Close Trades & Giveups module
		Thread.sleep(3500);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 11, description = "Performing Open or Close action in CCP Trades panel")
	public void openorcloseAction() throws InterruptedException, FindFailed, IOException 
	{
		// try
		// {
		System.out.println("\nPerforming Open or Close action in CCP Trades panel");
		updateMethodHeadinginExcel("Performing Open or Close action in CCP Trades panel");
		openTradesandGiveups();																	// Open Trades & Giveup module
		xpath(ClickCCPTrades_Cancelledtab).click();												// Click Cancelled tap in CCP Trades panel
		int Cancelled = Integer.parseInt(xpath(CCPTrades_TotalCancelledTrades).getText().replace("Cancelled (", "").replace(")", ""));					// Get Total no.of Cancelled trade in CCP Trades panel before Open/close action
		System.out.println("\tTotal no.of Unmatched trade in CCP Trade before Accept action = " + Cancelled);
		updateExcel("Total no.of Unmatched trade in CCP Trade before Accept action = " + Cancelled);
		xpath(ClickCCPTrades_Unmatchedtab).click();												// Click Unmatched tap in CCP Trades panel
		int Unmatched = Integer.parseInt(xpath(CCPTrades_TotalUnmatchedTrades).getText().replace("Unmatched (", "").replace(")", ""));					// Get Total no.of Unmatched_Trade in CCP Trades panel before Open/close action
		System.out.println("\tTotal no.of Unmatched trade in CCP Trade before Accept action = " + Unmatched);
		updateExcel("Total no.of Unmatched trade in CCP Trade before Accept action = " + Unmatched);
		sortUnmatched_LS();																		// Sort the Last Touched column of unmatched tap in CCP Trades panel
		String OpenorClose = xpath(CCPTrades_Unmatchedtab_OpenorClosestate).getText();			// Check the trade state on Unmatched tap in CCP Trades panel
		System.out.println("\tOpen or Close state = " + OpenorClose);
		updateExcel("Open or Close state = " + OpenorClose);
		if (OpenorClose.equals("Open")) 
		{
			String Unmatched_state_name = xpath(CCPTrades_Unmatahedtab_Tradestate).getText();
			if (Unmatched_state_name.equals("Unmatched")) {
				Actions CCP_Ridgtclick = new Actions(driver);
				CCP_Ridgtclick.moveToElement(xpath(CCPTrades_Unmatchedtab_Rightclick)).build().perform();
				CCP_Ridgtclick.contextClick(xpath(CCPTrades_Unmatchedtab_Rightclick)).build().perform();
			} 
			else 
			{
				System.out.println("\tFAIL - State not matched to Unmatched");
				updateFailedTestResultinExcel("FAIL - State not matched to Unmatched");
			}
			openorclose();																		// Open or Close action using sikuli
			xpath(CCPTrades_OpenorClose_Yesconfirmation).click();								// Click Yes button in confirmation pop up
			Thread.sleep(5000);
			xpath(ClickCCPTrades_Cancelledtab).click();											// Click Cancelled tap in CCP Trades panel
			int Cancelled1 = Integer.parseInt(xpath(CCPTrades_TotalCancelledTrades).getText().replace("Cancelled (", "").replace(")", ""));				// Get Total no.of Cancelled trade in CCP Trades panel after Open/close action
			xpath(ClickCCPTrades_Unmatchedtab).click();											// Click Unmatched tap in CCP Trades panel
			int Unmatched1 = Integer.parseInt(xpath(CCPTrades_TotalUnmatchedTrades).getText().replace("Unmatched (", "").replace(")", ""));				// Get Total no.of Unmatched_Trade in CCP Trades panel after Open/close action
			String OpenorClose1 = xpath(CCPTrades_Unmatchedtab_OpenorClosestate).getText();		// Check the trade state on Unmatched tap in CCP Trades panel
			if ((Cancelled1 == Cancelled + 1) && (Unmatched1 == Unmatched) && (OpenorClose1.equals("Close"))) 
			{
				System.out.println("\tOpen or Close state = " + OpenorClose1);
				updateExcel("Open or Close state = " + OpenorClose1);
				System.out.println("\tTotal no.of Unmatched trade in CCP Trade before Accept action = " + Cancelled1);
				updateExcel("Total no.of Unmatched trade in CCP Trade before Accept action = " + Cancelled1);
				System.out.println("\tTotal no.of Unmatched trade in CCP Trade before Accept action = " + Unmatched1);
				updateExcel("Total no.of Unmatched trade in CCP Trade before Accept action = " + Unmatched1);
				System.out.println("\tOpen or Close action performed successfully");
				updatePassedTestResultinExcel("Open or Close action performed successfully");
			} 
			else 
			{
				System.out.println("\tFAIL - Open or Close action");
				updateFailedTestResultinExcel("FAIL - Open or Close action");
			}
			closeTradesandGiveup();																// Close Trades & Giveups module
		} 
		else 
		{
			String Unmatched_state_name = xpath(CCPTrades_Unmatahedtab_Tradestate).getText();
			if (Unmatched_state_name.equals("Unmatched")) 
			{
				Actions CCP_Ridgtclick = new Actions(driver);
				CCP_Ridgtclick.moveToElement(xpath(CCPTrades_Unmatchedtab_Rightclick)).build().perform();
				CCP_Ridgtclick.contextClick(xpath(CCPTrades_Unmatchedtab_Rightclick)).build().perform();
			} 
			else 
			{
				System.out.println("\tFAIL - State not matched to Unmatched");
				updateFailedTestResultinExcel("FAIL - State not matched to Unmatched");
			}
			openorclose();																		// Open or Close action using sikuli
			xpath(CCPTrades_OpenorClose_Yesconfirmation).click();								// Click Yes button in confirmation pop up
			Thread.sleep(5000);
			xpath(ClickCCPTrades_Cancelledtab).click();											// Click Cancelled tap in CCP Trades panel
			int Cancelled1 = Integer.parseInt(xpath(CCPTrades_TotalCancelledTrades).getText().replace("Cancelled (", "").replace(")", ""));				// Get Total no.of Cancelled trade in CCP Trades panel after Open/close action
			xpath(ClickCCPTrades_Unmatchedtab).click();											// Click Unmatched tap in CCP Trades panel
			int Unmatched1 = Integer.parseInt(xpath(CCPTrades_TotalUnmatchedTrades).getText().replace("Unmatched (", "").replace(")", ""));				// Get Total no.of Unmatched_Trade in CCP Trades panel after Open/close action
			String OpenorClose1 = xpath(CCPTrades_Unmatchedtab_OpenorClosestate).getText();		// Check the trade state on Unmatched tap in CCP Trades panel
			if ((Cancelled1 == Cancelled + 1) && (Unmatched1 == Unmatched) && (OpenorClose1.equals("Open"))) 
			{
				System.out.println("\tOpen or Close state = " + OpenorClose1);
				updateExcel("Open or Close state = " + OpenorClose1);
				System.out.println("\tTotal no.of Unmatched trade in CCP Trade before Accept action = " + Cancelled1);
				updateExcel("Total no.of Unmatched trade in CCP Trade before Accept action = " + Cancelled1);
				System.out.println("\tTotal no.of Unmatched trade in CCP Trade before Accept action = " + Unmatched1);
				updateExcel("Total no.of Unmatched trade in CCP Trade before Accept action = " + Unmatched1);
				System.out.println("\tOpen or Close action performed successfully");
				updatePassedTestResultinExcel("Open or Close action performed successfully");
			} 
			else 
			{
				System.out.println("\tFAIL - Open or Close");
				updateFailedTestResultinExcel("FAIL - Open or Close");
			}
			closeTradesandGiveup();																// Close Trades & Giveups module
			Thread.sleep(3500);
		}
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@Test(priority = 12, description = "Performing Auto matching in CCP Trade panel")
	public void ccpTradeAutoMatch() throws InterruptedException, FindFailed, IOException 
	{
		// try
		// {
		System.out.println("\nPerforming Auto matching in CCP Trade panel");
		updateMethodHeadinginExcel("Performing Auto matching in CCP Trade panel");
		openTradesandGiveups();																	// Open Trades & Giveup module
		minimizeGiveupspanel();																	// Minimize Giveups Panel
		xpath(CCPTrades_CSVbutton).click();														// Click CSV icon in CCP Trades panel
		Thread.sleep(3000);
		if (id("trade-upload-window_header-title-textEl").isDisplayed()) 						// Click choose file button
		{
			id("fileUpload-button-fileInputEl").click();
			Thread.sleep(2500);
		} 
		else 
		{
			System.out.println("\tFAIL - Unable to click Choose button");
			updateFailedTestResultinExcel("FAIL - Unable to click Choose button");
		}
		clickDesktopButton();																	// Click Desktop button using Sikuli
		uploadTradeCSV();																		// Upload FO Trade CSV file using Sikuli
		clickOpenButton();																		// click open button using Sikuli
		xpath(CCPTrades_CSVUploadbutton).click();												// Click Upload button
		Thread.sleep(5000);
		xpath(ClickCCPTrades_Unmatchedtab).click();												// Click Unmatched tap in CCP Trades panel
		int Unmatched = Integer.parseInt(xpath(CCPTrades_TotalUnmatchedTrades).getText().replace("Unmatched (", "").replace(")", ""));				// Get Total no.of Unmatched trade in CCP Trades panel
		System.out.println("\tTotal no.of Unatched trade in CCP Trades before Auto match = " + Unmatched);
		updateExcel("Total no.of Unatched trade in CCP Trades before Auto match = " + Unmatched);
		xpath(FOTrades_Matchedtab).click();														// Click Matched tap in Fo Trades
		Thread.sleep(1500);
		sortMatched_LS();																		// Sort Last touched column in FO Trades panel
		int Matched = Integer.parseInt(xpath(FOTrades_Matchedtab).getText().replace("Matched (", "").replace(")", ""));								// Get Total no.of Matched trade in FO Trades panel before auto match
		System.out.println("\tTotal no.of Matched trade in FO Trades before Auto match = " + Matched);
		updateExcel("Total no.of Matched trade in FO Trades before Auto match = " + Matched);
		xpath(FOTrades_CSVbutton).click();														// Click CSV icon in FO Trades panel
		Thread.sleep(3000);
		if (id("trade-upload-window_header").isDisplayed()) 									// Click choose file button
		{
			id("fileUpload-button-fileInputEl").click();
			Thread.sleep(2500);
		}
		clickDesktopButton();
		uploadTradeCSV();																		// Upload FO Trade CSV file using Sikuli
		clickOpenButton();																		// click open button using Sikuli
		xpath(CCPTrades_CSVUploadbutton).click();												// Click Upload button
		Thread.sleep(5000);
		int Matched1 = Integer.parseInt(xpath(FOTrades_Matchedtab).getText().replace("Matched (", "").replace(")", ""));						// Get Total no.of Matched trade in FO Trades panel after auto match
		xpath(ClickCCPTrades_Unmatchedtab).click();												// Click Unmatched tap in CCP Trades panel
		int Unmatched1 = Integer.parseInt(xpath(CCPTrades_TotalUnmatchedTrades).getText().replace("Unmatched (", "").replace(")", ""));			// Get Total no.of Unmatched trade in CCP Trades panel after auto match
		if ((Unmatched1 == Unmatched - 1) && Matched1 == Matched + 1) 
		{
			System.out.println("\tTotal no.of Unatched trade in CCP Trades after Auto match = " + Unmatched1);
			updateExcel("Total no.of Unatched trade in CCP Trades after Auto match = " + Unmatched1);
			System.out.println("\tTotal no.of Matched trade in FO Trades after Auto match = " + Matched1);
			updateExcel("Total no.of Matched trade in FO Trades after Auto match = " + Matched1);
			System.out.println("\tAuto Matched action performed successfully");
			updatePassedTestResultinExcel("Auto Matched action performed successfully");
		} 
		else 
		{
			System.out.println("\tFAIL - CCP Trade Auto match");
			updateFailedTestResultinExcel("FAIL - CCP Trade Auto match");
		}
		closeTradesandGiveup();																		// Close Trades & Giveups module
		Thread.sleep(3500);
		// }
		// catch(Exception e)
		// {
		// System.out.println("Test case fail due to this exception" +e);
		// }
	}

	@AfterMethod
	public void tearDown(ITestResult result) 
	{
		// Here will compare if test is failing then only it will enter into if
		// condition
		if (ITestResult.FAILURE == result.getStatus()) 
		{
			try 
			{
				// Create refernce of TakesScreenshot
				TakesScreenshot ts = (TakesScreenshot) driver;
				// Call method to capture screenshot
				File source = ts.getScreenshotAs(OutputType.FILE);
				// Copy files to specific location here it will save all
				// screenshot in our project home directory and
				// result.getName() will return name of test case so that
				// screenshot name will be same
				FileUtils.copyFile(source, new File("C:\\Users\\alex_raj\\workspace\\Clear\\Screenshot\\" + result.getName() + ".png"));
				System.out.println("Screenshot taken");
			} 
			catch (Exception e) 
			{
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
	}

//	@AfterTest
//	public void closeBrowser()
//	{
//		driver.close();
//	}

}
