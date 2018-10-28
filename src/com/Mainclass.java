package com;

 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.BLUE;
import org.apache.poi.hssf.util.HSSFColor.YELLOW;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import configfile.ConfigFileReader;

public class Mainclass {
	public static WebDriver driver;
	ConfigFileReader fileread = new ConfigFileReader();

	public static String Menubutton 										= "//body[@id='ext-element-2']/div[1]/div[2]/div[1]/div[1]/a[1]";
	public static String Logoutbutton 										= "//div[contains(@id,'toolbar')]/a[2]//span[2][contains(.,'Logout')]";
	public static String Settingbutton 										= "//body[@id='ext-element-2']/div[5]/div[3]//a[1]/span/span/span[2]";
	public static String Settingwindow 										= "//body[@id='ext-element-2']/div[6]/div[2]";
	public static String ChangePassword 									= "//div[@id='wallpaper-tree-body']/div[1]/div[2]/table[2]/tbody/tr/td/div/span";
	public static String ChangePassword_Submitbutton 						= "//div[@id='password-reset-window']/div[4]//a[1]/span/span/span[2]";
	public static String ResetPassword_Toastmess 							= "//div[@id='password-reset-window']/following-sibling::div/div[3]";
	public static String ResetPassword_Toastcontent 						= "//div[@id='password-reset-window']/following-sibling::div/div[3]/div/div";
	public static String CloseSettingwindow 								= "//a[contains(@id,'button')]/span/span/span[2][contains(.,'OK')]";

	public static String OpenTradesandGiveups 								= "//body[@id='ext-element-2']/div/div/div[2]/ul/li[2]/div";
	public static String Maximize_TradesandGiveups 							= "//div[@id='tradeAllocation_header-targetEl']/div[3]/div";
	public static String CloseTradesandGiveups 								= "//div[@id='tradeAllocation_header-targetEl']/div[4]/div";
	public static String MinimizeFOTradesPanel 								= "//div[@id='fo-trades-Vbox_header-targetEl']/div[2]/div";
	public static String MinimizeGiveupsPanel 								= "//div[@id='allocationVbox_header-targetEl']/div[2]/div";
	public static String MinimizeAllocatedTicketpanel 						= "//div[@id='subclaimsVbox_header-targetEl']/div[2]/div";
	public static String CCPTrades_GiveupPendingtab_lasttouched 			= "//div[@id='tradePanel-body']/div[2]/div[1]/div/div/div[11]//span";
	public static String CCPTrades_Unmatchedtab_lasttouched 				= "//div[@id='tradePanel-body']/div[1]/div[1]/div/div/div[11]//span";
	public static String CCPTrades_Cancelledtab_lasttouched 				= "//div[@id='tradePanel-body']/div[4]/div[1]/div/div/div[11]//span";
	public static String FOTrades_Matched_lasttouched 						= "//div[@id='fo-trade-panel-body']/div[1]/div/div/div/div[12]//span";

	public static String Totaltab 											= "//div[@id='tradePanel']/div[1]/div/div[2]//a";
	public static String Newtab_clickFirstcolumnoption 						= "//div[contains(@id,'boundlist')]/div[1]/ul/li[1]";
	public static String Movetorightwindow 									= "//div[@id='trade-itemselector-field']/div[1]/div/div/div/div[2]//a[3]/span/span/span[1]";
	public static String Deletetab 											= "//div[@id='tradePanel']/div[1]/div/div[2]//a[6]/span[2]";

	//CCPTrades panel
	public static String ClickCCPTrades_Unmatchedtab 						= "//div[@id='tradePanel']/div[1]/div/div[2]/div/a[1]/span/span/span[2]";
	public static String CCPTrades_TotalUnmatchedTrades 					= "//div[@id='tradePanel']/div[1]/div/div[2]//a[1]/span/span/span[2]";
	public static String CCPTrades_Unmatahedtab_Tradestate 					= "//div[@id='tradePanel-body']/div/div[2]/div/div[3]/table[1]/tbody[1]/tr/td[9]/div";
	public static String CCPTrades_CSVbutton 								= "//div[@id='tradeVbox_header-targetEl']/div[3]/div";
	public static String CCPTrades_CSVUploadbutton 							= "//div[@id='trade-upload-window-body']/div[1]/div[2]//a[1]/span/span/span[2]";
	public static String ClickCCPTrades_GiveupPendingtab 					= "//div[@id='tradePanel']/div[1]/div/div[2]/div/a[2]/span/span/span[2]";
	public static String CCPTrade_TotalGiveupPendingTrades 					= "//div[@id='tradePanel']/div[1]/div/div[2]//a[2]/span/span/span[2]";
	public static String CCPTrades_GiveupPendingtab_Tradestate 				= "//div[@id='tradePanel-body']/div[2]/div[2]/div[1]/div[3]/table[1]/tbody/tr/td[9]/div";
	public static String ClickCCPTrade_Givenuptab							= "//div[@id='tradePanel']/div[1]/div/div[2]//a[3]/span/span/span[2]";
	public static String CCPTrades_TotalGivenupTrades 						= "//div[@id='tradePanel']/div[1]/div/div[2]//a[3]/span/span/span[2]";
	public static String CCPTrades_ManualGiveup								= "//a/span[contains(@id,'menuitem')][contains(.,'Manual')]";
	public static String CCPTrades_ManualGiveup_Applytemplate   			= "//a/span/span/span[2][contains(.,'Apply Template')]";
	public static String CCPTrade_ConfirmSubmission							= "//a/span/span/span[2][contains(.,'Yes')]";
	public static String ClickCCPTrades_Cancelledtab 						= "//div[@id='tradePanel']/div[1]/div/div[2]//a[4]/span/span/span[2]";
	public static String CCPTrades_TotalCancelledTrades						= "//div[@id='tradePanel']/div[1]/div/div[2]//a[4]/span/span/span[2]";	
	public static String CCPTrades_Unmatchedtab_ClientAcc					= "//div[@id='tradePanel-body']/div[1]/div[2]/div[1]/div[3]/table[1]//td[3]/div";
	public static String CCPTrades_Unmatchedtab_Rightclick					= "//div[@id='tradePanel-body']/div[1]/div[2]/div[1]/div[3]/table[1]//td[9]/div";
	public static String CCPTrades_TradeAmend_Accwindow 					= "//div[@id='account-tree-panel-body']/div[1]//table[1]/tbody/tr/td/div/div[2]";	
	public static String CCPTrades_GiveupPendingtab_Rightclick				= "//div[@id='tradePanel-body']/div[2]/div[2]/div[1]/div[3]/table[1]//td[9]/div";
	public static String CCPTrades_AddSplitbutton							= "//div[@id='clear_ccptrades_split_panel_target-targetEl']/div[1]/div/div/div//a[2]/span/span/span[2]";
	public static String CCPTrades_TradeSplit_ApplyTemplatebutton			= "//div[@id='clear_ccptrades_split_window-innerCt']//a[contains(.,'Apply Template')]"; 
	public static String CCPTrades_Unmatchedtab_OpenorClosestate 			= "//div[@id='tradePanel-body']/div/div[2]/div/div[3]/table[1]/tbody[1]/tr/td[13]/div";
	public static String CCPTrades_OpenorClose_Yesconfirmation				= "//div[contains(@id,'messagebox')]/a[2]/span/span/span[2]";

	//FO Trades panel
	public static String FOTrades_Matchedtab								= "//div[@id='fo-trade-panel']/div[1]/div/div[2]//a[1]/span/span/span[2]";
	public static String FOTrades_CSVbutton									= "//div[@id='fo-trades-Vbox_header-targetEl']/div[3]/div"; 

	//Giveups Panel
	public static String ClickGiveups_ClaimPendingtab						= "//div[@id='allocationPanel']/div[1]/div/div[2]//a[1]/span/span/span[2]";
	public static String Giveups_TotalClaimPendingTrades					= "//div[@id='allocationPanel']/div[1]/div/div[2]//a[1]/span/span/span[2]";
	public static String Giveups_ClaimPendingtab_Tradestate 				= "//div[@id='allocationPanel-body']/div[1]/div[2]//table[1]//td[9]/div";
	public static String ClickGiveups_Cancelledtab							= "//div[@id='allocationPanel']/div[1]/div/div[2]//a[2]/span/span/span[2]";
	public static String Giveups_Cancelledtab_Totaltrades					= "//div[@id='allocationPanel']/div[1]/div/div[2]//a[2]/span/span/span[2]";
	public static String Giveups_ClaimPendingtab_statelasttouched			= "//div[@id='allocationPanel-body']/div[1]/div/div/div/div[11]//span";

	//Claims Panel
	public static String OpenClaims 										= "//body[@id='ext-element-2']/div/div/div[2]/ul/li[1]/div";
	public static String MaximizeClaims 									= "//div[@id='trade-blotter_header-targetEl']/div[3]/div";
	public static String CloseClaims 										= "//div[@id='trade-blotter_header-targetEl']/div[4]/div";
	public static String ClientAllocation_CSVbutton 						= "//div[@id='client-trades-Vbox_header-targetEl']/div[3]/div";
	public static String Claims_ClaimPendingtab								= "//div[@id='tradeBlotter']/div[1]/div/div[2]//a[1]/span/span/span[2]";
	public static String Claims_ClaimPendingtab_Tradestate					= "//div[@id='tradeBlotter-body']/div[1]/div[2]/div[1]/div[3]/table[1]//td[9]/div";
	public static String Claims_CSVbutton 									= "//div[@id='claimsVbox_header-targetEl']/div[3]/div";
	public static String Claims_CSVUploadbutton								= "//div[@id='claim-import-window-body']/div[1]/div[2]//a[1]/span/span/span[2]";
	public static String Claims_Bookedtab									= "//div[@id='tradeBlotter']/div[1]/div/div[2]//a[2]/span/span/span[2]";
	public static String Claims_Bookedtab_lasttouched 						= "//div[@id='tradeBlotter-body']/div[2]/div[1]/div/div/div[11]//span";
	public static String Claims_Bookedtab_Tradestate						= "//div[@id='tradeBlotter-body']/div[2]/div[2]/div/div[3]/table[1]//td[9]/div";
	public static String Claims_Refusedtab									= "//div[@id='tradeBlotter']/div[1]/div/div[2]//a[3]/span/span/span[2]";
	public static String Claims_Allocatedtab								= "//div[@id='tradeBlotter']/div[1]/div/div[2]//a[4]/span/span/span[2]";
	public static String Claims_Allocateaction 								= "//div[contains(@id,'menu')]/div[9]/a/span[contains(@id,'menuitem')][contains(.,'Allocate')]";
	public static String Claims_AvreageAllocation							= "//a/span[contains(@id,'menuitem')][contains(.,'Average')]";
	public static String Claims_AvreageAllocation_Applytemplate				= "//div[contains(.,'Average Price Allocation')]/following-sibling::div//a[contains(@id,'button')]/span/span/span[2][contains(.,'Apply Template')]";

	//Client Allocation Panel
	public static String ClientAllocation_Minimize							= "//div[@id='client-trades-Vbox_header-title']/following-sibling::div[1]/div";
	public static String ClientAllocation_Matchedtab						= "//div[@id='client-trade-panel']/div[1]/div/div[2]//a[1]/span/span/span[2]";
	public static String ClientAllocation_CSVuploadbutton 					= "//div[@id='claim-upload-window-body']/div[1]/div[2]//a[1]/span/span/span[2]";

	//Allocated Tickets panel
	public static String Allocatedticket_Bookedtab							= "//div[@id='subClaimsPanel']/div[1]/div/div[2]//a/span/span/span[2]";


	//Method for xpath
	public static WebElement xpath(String xpath)
	{
		return driver.findElement(By.xpath(xpath));
	}

	public static WebElement id(String id)
	{
		return driver.findElement(By.id(id));
	}

	public void openbrowser() throws IOException
	{
		System.setProperty("webdriver.chrome.driver",fileread.getchromeDriverPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		Runtime.getRuntime().exec(fileread.getProxy());
	}

	public void login() throws InterruptedException, IOException
	{
		driver.navigate().to(fileread.getDemoURL());
		if(id("login_form-innerCt").isDisplayed())
		{
			System.out.println("\tPage loaded successfully");
			updateExcel("Page loaded successfully");
		}
		else
		{
			System.out.println("\tFAIL - Page not loaded");
			updateExcel("FAIL - Page not loaded");
		}
		id("user_id-inputEl").clear();
		id("user_id-inputEl").sendKeys(fileread.getUsername());
		id("user_pwd-inputEl").clear();
		id("user_pwd-inputEl").sendKeys(fileread.getPassword());
		id("login_button-btnEl").click();
		WebDriverWait Menubar = new WebDriverWait(driver, 60);
		Menubar.until(ExpectedConditions.elementToBeClickable(By.xpath(Menubutton)));
		if(xpath(Menubutton).isDisplayed())
		{
			System.out.println("\tUser logged in successfully");
			updateExcel("User logged in successfully");
		}
		else
		{
			System.out.println("\tFAIL - Unable to login");
			updateExcel("FAIL - Unable to login");
		}
	}

	//Logout
	public void logout() throws InterruptedException, IOException
	{
		xpath(Logoutbutton).click();
		WebDriverWait loginwindow = new WebDriverWait(driver, 60);
		loginwindow.until(ExpectedConditions.elementToBeClickable(By.id("login_window_header")));
		if(id("login_window_header").isDisplayed())
		{
			System.out.println("\tUser logged out successfully");
			updateExcel("User logged out successfully");
		}
		else
		{
			System.out.println("\tFAIL - Unable to logout");
			updateExcel("FAIL - Unable to logout");
		}

	}

	//Click User menu bar
	public void clickUserMenubar() throws InterruptedException
	{
		xpath(Menubutton).click();
		//		WebDriverWait Menubarwindow = new WebDriverWait(driver, 60);
		//		Menubarwindow.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tradeAllocation']/following-sibling::div[2]/div[1]")));
	}

	//Click Setting menu 
	public void clickSettings() throws InterruptedException
	{
		xpath(Settingbutton).click();
		Thread.sleep(2500);
	}

	//Reset Password
	public void resetpassword() throws InterruptedException, IOException
	{
		if(xpath(Settingwindow).isDisplayed())
		{
			xpath(ChangePassword).click();
			if(id("password-reset-window_header-title-textEl").isDisplayed())
			{
				id("old_password-inputEl").clear();
				id("old_password-inputEl").sendKeys(fileread.getOldPassword());
				id("new_password-inputEl").clear();
				id("new_password-inputEl").sendKeys(fileread.getNewPassword());
				id("confirm_new_password-inputEl").clear();
				id("confirm_new_password-inputEl").sendKeys(fileread.getNewPassword());
				xpath(ChangePassword_Submitbutton).click();
				//				Thread.sleep(6000);
			}     
			WebDriverWait Toastmess = new WebDriverWait(driver, 60);
			Toastmess.until(ExpectedConditions.elementToBeClickable(By.xpath(ResetPassword_Toastmess)));
			if(xpath(ResetPassword_Toastmess).isDisplayed())
			{
				String Alertmess = xpath(ResetPassword_Toastcontent).getText();
				if(Alertmess.equals("Your Password has been changed successfully."))
				{
					System.out.println("\tPassword changed successfully");
					updateExcel("Password changed successfully");
				}
				else
				{
					System.out.println("\tFAIL - Unable to change the Password");
					updateExcel("FAIL - Unable to change the Password");
				}
			}
			else
			{
				System.out.println("\tFAIL - Change password Toast message is not being displayed");
				updateExcel("FAIL - Change password Toast message is not being displayed");
			}
		}
		else
		{
			System.out.println("\tFAIL - Setting window not being displayed");
			updateExcel("FAIL - Setting window not being displayed");
		}
		//Close the setting window
		xpath(CloseSettingwindow).click();
		WebDriverWait Menubar = new WebDriverWait(driver, 60);
		Menubar.until(ExpectedConditions.elementToBeClickable(By.xpath(Menubutton)));
		//		Thread.sleep(7000);
	}

	//Open Trades & Giveups Module
	public void openTradesandGiveups() throws InterruptedException, IOException
	{
		xpath(OpenTradesandGiveups).click();
		WebDriverWait TradesandGiveupsmodule = new WebDriverWait(driver, 60);
		TradesandGiveupsmodule.until(ExpectedConditions.elementToBeClickable(By.id("tradeAllocation_header-title-textEl")));
		if(id("tradeAllocation_header-title-textEl").isDisplayed())
		{
			System.out.println("\tTrades & Giveups Module opened successfully");
			updateExcel("Trades & Giveups Module opened successfully");
			//Maximize Trades & Giveups module
			xpath(Maximize_TradesandGiveups).click();
		}
	}
	//Close Trades & Giveups module
	public void closeTradesandGiveup() throws InterruptedException
	{
		xpath(CloseTradesandGiveups).click();
		WebDriverWait CloseTradesandGiveupsmodule = new WebDriverWait(driver, 60);
		CloseTradesandGiveupsmodule.until(ExpectedConditions.elementToBeClickable(By.xpath(OpenTradesandGiveups)));
		//		Thread.sleep(2500);
	}

	//Minimize FO Trades panel
	public void minimizeFoPanel() throws InterruptedException
	{
		xpath(MinimizeFOTradesPanel).click();
		Thread.sleep(500);	
	}

	//Minimize Giveups Panel
	public void minimizeGiveupspanel() throws InterruptedException
	{
		xpath(MinimizeGiveupsPanel).click();
		Thread.sleep(500);
	}

	//Open Claims module
	public void openClaimsmodule() throws InterruptedException, IOException
	{
		xpath(OpenClaims).click();
		WebDriverWait Claimsmodule = new WebDriverWait(driver, 60);
		Claimsmodule.until(ExpectedConditions.elementToBeClickable(By.id("trade-blotter_header-title-textEl")));
		//		Thread.sleep(5000);
		if(id("trade-blotter_header-title-textEl").isDisplayed())
		{
			System.out.println("\tClaims Module opened successfully");
			updateExcel("Claims Module opened successfully");
			//Maximize Claims module
			xpath(MaximizeClaims).click();
		}
		Thread.sleep(1500);
	}

	//Close Claims module
	public void closeClaims() throws InterruptedException
	{
		xpath(CloseClaims).click();
		WebDriverWait CloseClaimsmodule = new WebDriverWait(driver, 60);
		CloseClaimsmodule.until(ExpectedConditions.elementToBeClickable(By.xpath(OpenClaims)));
		//		Thread.sleep(5000);
	}		

	//Minimize Allocated Ticket panel
	public void minimizeAllocatedTicketPanel() throws InterruptedException
	{
		xpath(MinimizeAllocatedTicketpanel).click();
		Thread.sleep(500);
	}

	//Sort last touched of Giveup Pending tap in CCP Trade panel
	public void sort_CCPTrades_GiveupPendingtab_LS() throws InterruptedException
	{
		xpath(CCPTrades_GiveupPendingtab_lasttouched).click();
		Thread.sleep(500);
		xpath(CCPTrades_GiveupPendingtab_lasttouched).click();	
	}

	//Sort last touched of Unmatched tap in CCP Trade panel
	public void sortUnmatched_LS() throws InterruptedException
	{
		xpath(CCPTrades_Unmatchedtab_lasttouched).click();
		Thread.sleep(500);
		xpath(CCPTrades_Unmatchedtab_lasttouched).click();
	}	


	//Sort last touched of Cancelled tap in CCP Trade panel
	public void sortCCPTrades_Cancelledtab_LS() throws InterruptedException
	{
		xpath(CCPTrades_Cancelledtab_lasttouched).click();
		Thread.sleep(500);
		xpath(CCPTrades_Cancelledtab_lasttouched).click();
	}	

	//Sort last touched of matched tap in FO Trade panel
	public void sortMatched_LS() throws InterruptedException
	{
		xpath(FOTrades_Matched_lasttouched).click();
		Thread.sleep(500);
		xpath(FOTrades_Matched_lasttouched).click();
	}	

	//Sort last touched column Givepending tap in CCP Trades panel
	public void sort_CCPTrades_GiveupPending_LS() throws InterruptedException
	{
		xpath(CCPTrades_GiveupPendingtab_lasttouched).click();
		Thread.sleep(500);
		xpath(CCPTrades_GiveupPendingtab_lasttouched).click();
	}

	//Sort Last touched column in Claim Pending tab of Giveups panel	
	public void sort_Giveups_ClaimPending_LS() throws InterruptedException
	{
		xpath(Giveups_ClaimPendingtab_statelasttouched).click();
		Thread.sleep(500);
		xpath(Giveups_ClaimPendingtab_statelasttouched).click();
	}

	//Sort Last Touched in Booked tap in Claims panel	
	public void sort_Claims_Booked_ls() throws InterruptedException
	{
		xpath(Claims_Bookedtab_lasttouched).click();
		Thread.sleep(500);
		xpath(Claims_Bookedtab_lasttouched).click();
	}

	//Click the Accept option from context menu bar(using sikuli)
	public void acceptUsingSikuli() throws FindFailed, InterruptedException
	{
		Screen Acceptaction  = new Screen();
		Pattern Acceptaction_image = new Pattern(fileread.getFilepath()+"\\Accept action.png");
		Acceptaction.wait(Acceptaction_image, 10);
		Acceptaction.click(Acceptaction_image);	
		Thread.sleep(5000);
	}

	//Trade amend action using sikuli
	public void tradeAmendUsingSikuli() throws FindFailed, InterruptedException
	{
		Screen TradeAmend = new Screen();
		Pattern TradeAmend_image = new Pattern(fileread.getFilepath()+"\\Trade Amend.png");
		TradeAmend.wait(TradeAmend_image, 10);
		TradeAmend.click(TradeAmend_image);
		Thread.sleep(7000);
	}	

	//Click Desktop button (using sikuli)
	public void clickDesktopButton() throws FindFailed, InterruptedException
	{
		Screen Desktopbutton  = new Screen();
		Pattern Desktopbutton_image = new Pattern(fileread.getFilepath()+"\\Desktop button.PNG");
		Desktopbutton.wait(Desktopbutton_image, 10);
		Desktopbutton.click(Desktopbutton_image);	
		Thread.sleep(2000);
	}

	//Upload FO Trade CSV file(using sikuli)
	public void uploadTradeCSV() throws FindFailed, InterruptedException
	{
		Screen UploadFOcsvfile  = new Screen();
		Pattern UploadFOcsvfile_image = new Pattern(fileread.getFilepath()+"\\FO trade upload.PNG");
		UploadFOcsvfile.wait(UploadFOcsvfile_image, 10);
		UploadFOcsvfile.click(UploadFOcsvfile_image);	
		Thread.sleep(2000);		
	}

	//Upload Claims Trade CSV file(using sikuli)
	public void uploadClaimsTradeCSV() throws FindFailed, InterruptedException
	{
		Screen UploadClaimscsvfile  = new Screen();
		Pattern UploadClaimscsvfile_image = new Pattern(fileread.getFilepath()+"\\Claims Trade upload.PNG");
		UploadClaimscsvfile.wait(UploadClaimscsvfile_image, 10);
		UploadClaimscsvfile.click(UploadClaimscsvfile_image);	
		Thread.sleep(2000);		
	}	

	//click open button(using sikuli)
	public void clickOpenButton() throws FindFailed, InterruptedException
	{
		Screen Openbutton  = new Screen();
		Pattern Openbutton_image = new Pattern(fileread.getFilepath()+"\\Open button.PNG");
		Openbutton.wait(Openbutton_image, 10);
		Openbutton.click(Openbutton_image);	
		Thread.sleep(2000);
	}

	//click Affirm(using sikuli)
	public void clickAffirm() throws FindFailed, InterruptedException
	{
		Screen Openbutton  = new Screen();
		Pattern Openbutton_image = new Pattern(fileread.getFilepath()+"\\Affirm.png");
		Openbutton.wait(Openbutton_image, 10);
		Openbutton.click(Openbutton_image);	
		Thread.sleep(2000);
	}	

	//click Reject(using sikuli)
	public void clickReject() throws FindFailed, InterruptedException
	{
		Screen Openbutton  = new Screen();
		Pattern Openbutton_image = new Pattern(fileread.getFilepath()+"\\Reject.png");
		Openbutton.wait(Openbutton_image, 10);
		Openbutton.click(Openbutton_image);	
		Thread.sleep(2000);
	}	

	//Click Giveups option using sikuli	
	public void clickGiveups() throws FindFailed
	{
		Screen giveupAction  = new Screen();
		Pattern giveupAction_image = new Pattern(fileread.getFilepath()+"\\Giveup Action.png");
		giveupAction.wait(giveupAction_image, 10);
		giveupAction.click(giveupAction_image);
	}

	//Click Giveup Upload template file using sikuli	
	public void manualGiveupUploadTemplate() throws FindFailed, InterruptedException
	{
		Screen giveupUploadTemplate  = new Screen();
		Pattern giveupUploadTemplate_image = new Pattern(fileread.getFilepath()+"\\Giveup Upload template.PNG");
		giveupUploadTemplate.wait(giveupUploadTemplate_image, 10);
		giveupUploadTemplate.click(giveupUploadTemplate_image);	
		Thread.sleep(2000);	
	}

	//Click Cancel option in Giveups panel
	public void giveupsPanelCancelAction() throws FindFailed, InterruptedException
	{
		Screen Cancel = new Screen();
		Pattern Cancel_image = new Pattern(fileread.getFilepath()+"\\Cancel.png");
		Cancel.wait(Cancel_image, 10);
		Cancel.click(Cancel_image);
		Thread.sleep(3000);	
	}

	//Trade Split action using Sikuli
	public void tradeSplit() throws FindFailed, InterruptedException, IOException
	{
		Screen TradeSplit = new Screen();
		Pattern TradeSplit_image = new Pattern(fileread.getFilepath()+"\\Trade Split.png");
		TradeSplit.wait(TradeSplit_image, 10);
		TradeSplit.click(TradeSplit_image);
		Thread.sleep(3000); 
	}

	//Click Trade Split Upload template using Sikuli
	public void tradeSplitUploadTemplate() throws FindFailed, InterruptedException
	{
		Screen tradeSplitTemplate  = new Screen();
		Pattern tradeSplitTemplate_image = new Pattern(fileread.getFilepath()+"\\Trade Split template.png");
		tradeSplitTemplate.wait(tradeSplitTemplate_image, 10);
		tradeSplitTemplate.click(tradeSplitTemplate_image);	
		Thread.sleep(2000);	
	}

	//Open or Close action using sikuli	
	public void openorclose() throws FindFailed, InterruptedException
	{
		Screen openorclose = new Screen();
		Pattern openorclose_image = new Pattern(fileread.getFilepath()+"\\Open or Close.png");
		openorclose.wait(openorclose_image, 10);
		openorclose.click(openorclose_image);
		Thread.sleep(3000);	
	}

	//Upload Client Allocation CSV file
	public void ClientAllocationCSVfile() throws FindFailed
	{
		Screen ClientAllocationAutoMatch  = new Screen();
		Pattern ClientAllocationAutoMatch_image = new Pattern(fileread.getFilepath()+"\\Client Allocation Auto Match.PNG");
		ClientAllocationAutoMatch.wait(ClientAllocationAutoMatch_image, 10);
		ClientAllocationAutoMatch.click(ClientAllocationAutoMatch_image);
	}

	//Click CSV button in Client Allocation panel	
	public void clickClientAllocationCSV() throws InterruptedException
	{
		xpath(ClientAllocation_CSVbutton).click();
		WebDriverWait CA_CSVbutton = new WebDriverWait(driver, 60);
		CA_CSVbutton.until(ExpectedConditions.elementToBeClickable(By.id("claim-upload-window_header")));
		//		Thread.sleep(2000);
		if(id("claim-upload-window_header").isDisplayed())
		{
			id("claimUpload-button-fileInputEl").click();
		}
	}

	//Method for clear the existing value in excel file	
	public void clearExcel() throws IOException
	{
		File file = new File(fileread.getReportpath());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet =workBook.getSheet("Sheet1");
		XSSFRow row = null;
		XSSFCell cell = null;

		//Get the last row of the Excel file 
		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: " +rowCount);

		for(int i=0;i<=rowCount;i++) 
		{
			row = sheet.getRow(i);
			sheet.removeRow(row);
		}

		//Get the current Date and time and print in the excel file 
		
		int rowCount1 = sheet.getLastRowNum();
		
		row = sheet.createRow(rowCount1);
		cell = row.createCell(0);
		cell.setCellValue("Title");
		cell = row.createCell(1);
		cell.setCellValue("Clear Demo Automation Test Report");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		row = sheet.createRow(rowCount1+1);
		cell = row.createCell(0);
		cell.setCellValue("Date and execution time");
		cell = row.createCell(1);
//		String date1 = "Date and execution time = "+(dateFormat.format(date));
		cell.setCellValue(dateFormat.format(date));

		row = sheet.createRow(rowCount1+2);
		cell = row.createCell(0);
		cell.setCellValue(" ");
		
		CellStyle style = workBook.createCellStyle();
		Font font = workBook.createFont();
        font.setColor(IndexedColors.BLUE.getIndex());
        style.setFont(font);
		row = sheet.createRow(rowCount1+3);
		cell = row.createCell(0);
		cell.setCellValue("BLUE - Indicates Test case name");
		cell.setCellStyle(style);
		
		CellStyle style1 = workBook.createCellStyle();
		Font font1 = workBook.createFont();
        font1.setColor(IndexedColors.GREEN.getIndex());
        style1.setFont(font1);
		row = sheet.createRow(rowCount1+4);
		cell = row.createCell(0);
		cell.setCellValue("GREEN - Indicates Passed Test case result");
		cell.setCellStyle(style1);
		
		CellStyle style2 = workBook.createCellStyle();
		Font font2 = workBook.createFont();
        font2.setColor(IndexedColors.RED.getIndex());
        style2.setFont(font2);
		row = sheet.createRow(rowCount1+5);
		cell = row.createCell(0);
		cell.setCellValue("RED - Indicates FAILED Test case result");
		cell.setCellStyle(style2);
		
		row = sheet.createRow(rowCount1+6);
		cell = row.createCell(0);
		cell.setCellValue(" ");

		FileOutputStream output = new FileOutputStream(new File(fileread.getReportpath()));
		workBook.write(output);
		System.out.println("Row count: " +sheet.getLastRowNum());
		workBook.close();
	}	

	//Trades & Giveups module action
	public void tradesandgiveupsModuleActions() throws IOException
	{
		File file = new File(fileread.getReportpath());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet =workBook.getSheet("Sheet1");
		XSSFRow row = null;
		XSSFCell cell = null;
		
		int rowCount = sheet.getLastRowNum();																		//Get the last row of the excel file
		row = sheet.createRow(rowCount+1);																			//create new row 
		cell = row.createCell(0);																					//Create new column for the corresponding row
		cell.setCellValue("Trades & Giveups module - Please find the test result below");
		
		FileOutputStream output = new FileOutputStream(new File(fileread.getReportpath()));
		workBook.write(output);
		workBook.close();
	}

	//Claims module action
	public void claimsModuleActions() throws IOException
	{
		File file = new File(fileread.getReportpath());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet =workBook.getSheet("Sheet1");
		XSSFRow row = null;
		
		int rowCount = sheet.getLastRowNum();																		//Get the last row of the excel file
		row = sheet.createRow(rowCount+1);																			//create new row 
		XSSFCell cell = row.createCell(0);																			//Create new column for the corresponding row
		cell.setCellValue("Claims module - Please find the test result below");
		
		FileOutputStream output = new FileOutputStream(new File(fileread.getReportpath()));
		workBook.write(output);
		workBook.close();
	}

	//Method for update the test execution output in the excel file	
	public void updateExcel(String Printvalue) throws IOException
	{
		File file = new File(fileread.getReportpath());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet =workBook.getSheet("Sheet1");
		XSSFRow row = null;
		int rowCount = sheet.getLastRowNum();																		//Get the last row of the excel file
		row = sheet.createRow(rowCount+1);																			//create new row 
		XSSFCell cell = row.createCell(1);																			//Create new column for the corresponding row
		cell.setCellValue(Printvalue);																				//Print the execution output value in excel file

		FileOutputStream output = new FileOutputStream(new File(fileread.getReportpath()));
		workBook.write(output);
		workBook.close();
	}	
	
	//Method for update the Test case heading in different format
	public void updateMethodHeadinginExcel(String Printvalue) throws IOException
	{
		File file = new File(fileread.getReportpath());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet =workBook.getSheet("Sheet1");
		XSSFRow row = null;
		
		CellStyle style = workBook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    Font font = workBook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
		
		int rowCount = sheet.getLastRowNum();																		//Get the last row of the excel file
		row = sheet.createRow(rowCount+1);																			//create new row 
		XSSFCell cell = row.createCell(0);																			//Create new column for the corresponding row
		cell.setCellValue(Printvalue);																				//Print the execution output value in excel file
		cell.setCellStyle(style);

		FileOutputStream output = new FileOutputStream(new File(fileread.getReportpath()));
		workBook.write(output);
		workBook.close();
	}
	
	//Method for update passed result in Excel in different format
	public void updatePassedTestResultinExcel(String Printvalue) throws IOException
	{
		File file = new File(fileread.getReportpath());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet =workBook.getSheet("Sheet1");
		XSSFRow row = null;
		
		CellStyle style = workBook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    Font font = workBook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
		
		int rowCount = sheet.getLastRowNum();																		//Get the last row of the excel file
		row = sheet.createRow(rowCount+1);																			//create new row 
		XSSFCell cell = row.createCell(1);																			//Create new column for the corresponding row
		cell.setCellValue(Printvalue);																				//Print the execution output value in excel file
		cell.setCellStyle(style);

		FileOutputStream output = new FileOutputStream(new File(fileread.getReportpath()));
		workBook.write(output);
		workBook.close();
	}
	
	//Method for update failed result in Excel in different format
	public void updateFailedTestResultinExcel(String Printvalue) throws IOException
	{
		File file = new File(fileread.getReportpath());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet =workBook.getSheet("Sheet1");
		XSSFRow row = null;
		
		CellStyle style = workBook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    Font font = workBook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
		
		int rowCount = sheet.getLastRowNum();																		//Get the last row of the excel file
		row = sheet.createRow(rowCount+1);																			//create new row 
		XSSFCell cell = row.createCell(1);																			//Create new column for the corresponding row
		cell.setCellValue(Printvalue);																				//Print the execution output value in excel file
		cell.setCellStyle(style);

		FileOutputStream output = new FileOutputStream(new File(fileread.getReportpath()));
		workBook.write(output);
		workBook.close();
	}
}
