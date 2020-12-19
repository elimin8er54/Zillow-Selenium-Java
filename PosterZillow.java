//OWNER WANTED A BOT TO POST ON Craigslist, Zillow and Apartments in 5 days. 
//This is 1 of 12 files which also included an auto updater and config file to save information
//As well as setting up schedules for posting

package automationFramework5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class PosterZillow implements PostingSetup {
	// (4/23/18)
	// CHANGE ARRAYLISTS TO LIST OF MAPS OR JSONArray OF JSONObjects IF THE OWNER LETS ME
	// (4/24/18)
	// HAVE THE SERVER GENERATE JSON AND PASS TO THIS APP INSTEAD OF QUERYING FROM THE JAVA APP DIRECTLY
	// (4/25/18)
	// CHANGE ALL THREAD SLEEPS TO ACTUALLY WAIT FOR SOMETHING TO LOAD ON THE THE PAGE
	// ALSO MAKE A FUNCTION FOR HANDLING LOOKING FOR WHAT TO WAIT FOR (LESS CODE THIS WAY)
	// (4/28/18)
	// OWNER IS JUST HAPPY IT WORKS AND HAS TOLD ME NOT TO CLEAN UP THE CODE.
	// (5/07/20)
	// ZILLOW, CL, and APARTMENTS NOW CHARGE FOR POSTING RENTALS. PROGRAM IS OBSOLETE NOW
	List<String> amenity_space_openlayout = new ArrayList<String>();
	List<String> amenity_space_officeden = new ArrayList<String>();
	List<String> amenity_space_largebedrooms = new ArrayList<String>();
	List<String> amenity_space_onesmallbedroom = new ArrayList<String>();
	List<String> amenity_space_cozy = new ArrayList<String>();
	List<String> amenity_space_walkinclosets = new ArrayList<String>();
	List<String> amenity_space_highceilings = new ArrayList<String>();
	List<String> amenity_space_loft = new ArrayList<String>();
	List<String> amenity_kitchen_eatin = new ArrayList<String>();
	List<String> amenity_kitchen_gallery = new ArrayList<String>();
	List<String> amenity_kitchen_dishwasher = new ArrayList<String>();
	List<String> amenity_kitchen_disposal = new ArrayList<String>();
	List<String> amenity_kitchen_microwave = new ArrayList<String>();
	List<String> amenity_kitchen_pantry = new ArrayList<String>();
	List<String> amenity_kitchen_breakfastbar = new ArrayList<String>();
	List<String> amenity_kitchen_modern = new ArrayList<String>();
	List<String> amenity_kitchen_amplecounterspace = new ArrayList<String>();
	List<String> amenity_kitchen_stainlesssteelapplications = new ArrayList<String>();
	List<String> amenity_kitchen_granitecountertops = new ArrayList<String>();
	List<String> amenity_utility_heatinc = new ArrayList<String>();
	List<String> amenity_utility_hotwaterinc = new ArrayList<String>();
	List<String> amenity_utility_electricityinc = new ArrayList<String>();
	List<String> amenity_utility_electricheat = new ArrayList<String>();
	List<String> amenity_utility_electricstove = new ArrayList<String>();
	List<String> amenity_utility_gasheat = new ArrayList<String>();
	List<String> amenity_utility_gasstove = new ArrayList<String>();
	List<String> amenity_utility_gasinc = new ArrayList<String>();
	List<String> amenity_utility_oilheat = new ArrayList<String>();
	List<String> amenity_utility_cablenetready = new ArrayList<String>();
	List<String> amenity_utility_allutilitiesinc = new ArrayList<String>();
	List<String> amenity_utility_totalmonthlyavg = new ArrayList<String>();
	List<String> amenity_utility_heatmonthylavg = new ArrayList<String>();
	List<String> amenity_interiorfeature_fireplaceworking = new ArrayList<String>();
	List<String> amenity_interiorfeature_fireplacedec = new ArrayList<String>();
	List<String> amenity_interiorfeature_ceilingfan = new ArrayList<String>();
	List<String> amenity_interiorfeature_gutrenovation = new ArrayList<String>();
	List<String> amenity_interiorfeature_recentrenovation = new ArrayList<String>();
	List<String> amenity_interiorfeature_ac = new ArrayList<String>();
	List<String> amenity_interiorfeature_exposedbrick = new ArrayList<String>();
	List<String> amenity_interiorfeature_deleaded = new ArrayList<String>();
	List<String> amenity_interiorfeature_freshlypainted = new ArrayList<String>();
	List<String> amenity_view_showswell = new ArrayList<String>();
	List<String> amenity_view_showspoorly = new ArrayList<String>();
	List<String> amenity_view_showsmessy = new ArrayList<String>();
	List<String> amenity_view_helpfultenants = new ArrayList<String>();
	List<String> amenity_view_dontaskquestions = new ArrayList<String>();
	List<String> amenity_view_amazingview = new ArrayList<String>();
	List<String> amenity_view_cityview = new ArrayList<String>();
	List<String> amenity_view_facesstreet = new ArrayList<String>();
	List<String> amenity_view_facescourtyard = new ArrayList<String>();
	List<String> amenity_view_waterviews = new ArrayList<String>();
	List<String> amenity_laundry_inbuilding = new ArrayList<String>();
	List<String> amenity_laundry_inunit = new ArrayList<String>();
	List<String> amenity_laundry_hookups = new ArrayList<String>();
	List<String> amenity_laundry_nearby = new ArrayList<String>();
	List<String> amenity_laundry_free = new ArrayList<String>();
	List<String> amenity_laundry_laundryroom = new ArrayList<String>();
	List<String> amenity_laundry_onsamefloor = new ArrayList<String>();
	List<String> amenity_laundry_priceperload = new ArrayList<String>();
	List<String> amenity_luxury_luxuryapartment = new ArrayList<String>();
	List<String> amenity_luxury_exercisefacilities = new ArrayList<String>();
	List<String> amenity_luxury_centralac = new ArrayList<String>();
	List<String> amenity_luxury_doorman = new ArrayList<String>();
	List<String> amenity_luxury_jacuzzi = new ArrayList<String>();
	List<String> amenity_luxury_sauna = new ArrayList<String>();
	List<String> amenity_luxury_furnished = new ArrayList<String>();
	List<String> amenity_structure_type = new ArrayList<String>();
	List<String> amenity_structure_familycount = new ArrayList<String>();
	List<String> amenity_floor_level = new ArrayList<String>();
	List<String> amenity_floor_elevator = new ArrayList<String>();
	List<String> amenity_floor_hardwood = new ArrayList<String>();
	List<String> amenity_floor_ceramictile = new ArrayList<String>();
	List<String> amenity_floor_wtowcarpet = new ArrayList<String>();
	List<String> amenity_floor_wtwcarpethardwood = new ArrayList<String>();
	List<String> amenity_floor_marblefloors = new ArrayList<String>();
	List<String> amenity_floor_floorthrough = new ArrayList<String>();
	List<String> amenity_parking_freestreetpermit = new ArrayList<String>();
	List<String> amenity_parking_freestreetnopermit = new ArrayList<String>();
	List<String> amenity_parking_permitunknown = new ArrayList<String>();
	List<String> amenity_parking_offstreetspaceinc = new ArrayList<String>();
	List<String> amenity_parking_garagespaceinc = new ArrayList<String>();
	List<String> amenity_parking_offstreetspacerent = new ArrayList<String>();
	List<String> amenity_parking_garagespacerent = new ArrayList<String>();
	List<String> amenity_parking_offstreetrent = new ArrayList<String>();
	List<String> amenity_parking_garagerent = new ArrayList<String>();
	List<String> amenity_pet_dogs = new ArrayList<String>();
	List<String> amenity_pet_cats = new ArrayList<String>();
	List<String> amenity_pet_smallpets = new ArrayList<String>();
	List<String> amenity_pet_petsnegotiable = new ArrayList<String>();
	List<String> amenity_pet_weightrestrictions = new ArrayList<String>();
	List<String> amenity_pet_dogsneg = new ArrayList<String>();
	List<String> amenity_pet_catsneg = new ArrayList<String>();
	List<String> amenity_pet_nopets = new ArrayList<String>();
	List<String> amenity_pet_unsure = new ArrayList<String>();
	List<String> amenity_extraroom_alcove = new ArrayList<String>();
	List<String> amenity_extraroom_attic = new ArrayList<String>();
	List<String> amenity_extraroom_atticstorage = new ArrayList<String>();
	List<String> amenity_extraroom_diningroom = new ArrayList<String>();
	List<String> amenity_extraroom_extrastorage = new ArrayList<String>();
	List<String> amenity_extraroom_basementstorage = new ArrayList<String>();
	List<String> amenity_extraroom_finishedbasement = new ArrayList<String>();
	List<String> amenity_extraroom_masterbedroom = new ArrayList<String>();
	List<String> amenity_extraroom_sunroom = new ArrayList<String>();
	List<String> amenity_exteriorfeature_porch = new ArrayList<String>();
	List<String> amenity_exteriorfeature_balcony = new ArrayList<String>();
	List<String> amenity_exteriorfeature_yard = new ArrayList<String>();
	List<String> amenity_exteriorfeature_fenceinyard = new ArrayList<String>();
	List<String> amenity_exteriorfeature_patio = new ArrayList<String>();
	List<String> amenity_exteriorfeature_swimmingpool = new ArrayList<String>();
	List<String> amenity_exteriorfeature_roofdeck = new ArrayList<String>();
	List<String> amenity_noisefactor_owneroccupied = new ArrayList<String>();
	List<String> amenity_noisefactor_liveinsuper = new ArrayList<String>();
	List<String> amenity_noisefactor_professionalbuilding = new ArrayList<String>();
	List<String> amenity_noisefactor_walktoshoprestaurants = new ArrayList<String>();
	List<String> amenity_noisefactor_bandfriendly = new ArrayList<String>();
	List<String> amenity_noisefactor_wantsquietpeople = new ArrayList<String>();
	List<String> amenity_noisefactor_locatedabovebar = new ArrayList<String>();
	List<String> amenity_noisefactor_gradprofonly = new ArrayList<String>();
	List<String> amenity_windowslighting_recessedlights = new ArrayList<String>();
	List<String> amenity_windowslighting_skylights = new ArrayList<String>();
	List<String> amenity_windowslighting_newwindows = new ArrayList<String>();
	List<String> amenity_windowslighting_baywindows = new ArrayList<String>();
	List<String> amenity_windowslighting_sunny = new ArrayList<String>();
	List<String> amenity_windowslighting_trackinglighting = new ArrayList<String>();
	List<String> amenity_windowslighting_southernexposure = new ArrayList<String>();
	List<String> amenity_utility_heatsource = new ArrayList<String>();
	List<String> amenity_parking_tandemgarage = new ArrayList<String>();
	List<String> amenity_parking_tandemoffstreet = new ArrayList<String>();
	List<String> amenity_parking_stickerrequired = new ArrayList<String>();
	List<String> amenity_interiorfeature_splitstyle = new ArrayList<String>();

	// Database stores 1 as true and 0 as false. So we compare 1 and 0

	public ArrayList<String> get_exterior(int index) {

		ArrayList<String> selectexteriorvalues = new ArrayList<String>();
		if (amenity_exteriorfeature_porch.get(index).equals("1")) {
			selectexteriorvalues.add("Porch");
		}
		if (amenity_exteriorfeature_balcony.get(index).equals("1")) {
			selectexteriorvalues.add("Balcony");
		}
		if (amenity_exteriorfeature_yard.get(index).equals("1")) {
			selectexteriorvalues.add("Yard");
		}
		if (amenity_exteriorfeature_fenceinyard.get(index).equals("1")) {
			selectexteriorvalues.add("Fenced in Yard");
		}
		if (amenity_exteriorfeature_patio.get(index).equals("1")) {
			selectexteriorvalues.add("Patio");
		}
		if (amenity_exteriorfeature_swimmingpool.get(index).equals("1")) {
			selectexteriorvalues.add("Swimming Pool");
		}
		if (amenity_exteriorfeature_roofdeck.get(index).equals("1")) {
			selectexteriorvalues.add("Roof Deck");
		}

		return selectexteriorvalues;

	}

	public ArrayList<String> get_space(int index) {

		ArrayList<String> selectspacevalues = new ArrayList<String>();
		if (amenity_space_openlayout.get(index).equals("1")) {
			selectspacevalues.add("Open Layout");
		}
		if (amenity_space_officeden.get(index).equals("1")) {
			selectspacevalues.add("Office/Den");
		}
		if (amenity_space_largebedrooms.get(index).equals("1")) {
			selectspacevalues.add("Large Bedrooms");
		}
		if (amenity_space_cozy.get(index).equals("1")) {
			selectspacevalues.add("Cozy");
		}
		if (amenity_space_walkinclosets.get(index).equals("1")) {
			selectspacevalues.add("Walk in Closet");
		}
		if (amenity_space_highceilings.get(index).equals("1")) {
			selectspacevalues.add("High Ceilings");
		}
		if (amenity_space_loft.get(index).equals("1")) {
			selectspacevalues.add("Loft");
		}

		return selectspacevalues;

	}

	public ArrayList<String> get_kitchen(int index) {

		ArrayList<String> selectkitchenvalues = new ArrayList<String>();
		if (amenity_kitchen_eatin.get(index).equals("1")) {
			selectkitchenvalues.add("Eat In Kitchen");
		}
		if (amenity_kitchen_gallery.get(index).equals("1")) {
			selectkitchenvalues.add("Gallery");
		}
		if (amenity_kitchen_dishwasher.get(index).equals("1")) {
			selectkitchenvalues.add("Dishwasher");
		}
		if (amenity_kitchen_disposal.get(index).equals("1")) {
			selectkitchenvalues.add("Disposal");
		}
		if (amenity_kitchen_microwave.get(index).equals("1")) {
			selectkitchenvalues.add("Microwave");
		}
		if (amenity_kitchen_pantry.get(index).equals("1")) {
			selectkitchenvalues.add("Pantry");
		}
		if (amenity_kitchen_breakfastbar.get(index).equals("1")) {
			selectkitchenvalues.add("Breakfast Bar");
		}
		if (amenity_kitchen_modern.get(index).equals("1")) {
			selectkitchenvalues.add("Modern");
		}
		if (amenity_kitchen_amplecounterspace.get(index).equals("1")) {
			selectkitchenvalues.add("Ample Counter Space");
		}
		if (amenity_kitchen_stainlesssteelapplications.get(index).equals("1")) {
			selectkitchenvalues.add("Stainless Steel Applications");
		}
		if (amenity_kitchen_granitecountertops.get(index).equals("1")) {
			selectkitchenvalues.add("Granite Counter Tops");
		}

		return selectkitchenvalues;

	}

	public ArrayList<String> get_utility(int index) {

		ArrayList<String> selectutilityvalues = new ArrayList<String>();
		if (amenity_utility_electricityinc.get(index).equals("1")) {
			selectutilityvalues.add("Electricity Included");
		}
		if (amenity_utility_electricheat.get(index).equals("1")) {
			selectutilityvalues.add("Electric Heat");
		}
		if (amenity_utility_electricstove.get(index).equals("1")) {
			selectutilityvalues.add("Electric Stove");
		}
		if (amenity_utility_gasheat.get(index).equals("1")) {
			selectutilityvalues.add("Gas Heat");
		}
		if (amenity_utility_gasstove.get(index).equals("1")) {
			selectutilityvalues.add("Gas Stove");
		}
		if (amenity_utility_gasinc.get(index).equals("1")) {
			selectutilityvalues.add("Gas Included");
		}
		if (amenity_utility_oilheat.get(index).equals("1")) {
			selectutilityvalues.add("Oil Heat");
		}
		if (amenity_utility_cablenetready.get(index).equals("1")) {
			selectutilityvalues.add("Cable and Net Ready");
		}
		return selectutilityvalues;

	}

	public ArrayList<String> get_interior(int index) {

		ArrayList<String> selectinteriorvalues = new ArrayList<String>();
		if (amenity_interiorfeature_fireplaceworking.get(index).equals("1")) {
			selectinteriorvalues.add("Working Fire Place");
		}
		if (amenity_interiorfeature_fireplacedec.get(index).equals("1")) {
			selectinteriorvalues.add("Decorative Fire Place");
		}
		if (amenity_interiorfeature_ceilingfan.get(index).equals("1")) {
			selectinteriorvalues.add("Ceiling Fan");
		}
		if (amenity_interiorfeature_gutrenovation.get(index).equals("1")) {
			selectinteriorvalues.add("Gut Renovation");
		}
		if (amenity_interiorfeature_recentrenovation.get(index).equals("1")) {
			selectinteriorvalues.add("Recent Renovation");
		}
		if (amenity_interiorfeature_splitstyle.get(index).equals("1")) {
			selectinteriorvalues.add("Split Style");
		}
		if (amenity_interiorfeature_ac.get(index).equals("1")) {
			selectinteriorvalues.add("AC");
		}

		return selectinteriorvalues;

	}

	public ArrayList<String> get_laundry(int index) {

		ArrayList<String> selectlaundryvalues = new ArrayList<String>();
		if (amenity_laundry_inbuilding.get(index).equals("1")) {
			selectlaundryvalues.add("Laundry In Building");
		}
		if (amenity_laundry_inunit.get(index).equals("1")) {
			selectlaundryvalues.add("Laundry In Unit");
		}
		if (amenity_laundry_hookups.get(index).equals("1")) {
			selectlaundryvalues.add("Laundry Hookups");
		}
		if (amenity_laundry_nearby.get(index).equals("1")) {
			selectlaundryvalues.add("Laundry Nearby");
		}
		if (amenity_laundry_free.get(index).equals("1")) {
			selectlaundryvalues.add("Free Laundry");
		}
		if (amenity_laundry_laundryroom.get(index).equals("1")) {
			selectlaundryvalues.add("Laundry Room");
		}
		if (amenity_laundry_onsamefloor.get(index).equals("1")) {
			selectlaundryvalues.add("Laundry On The Same Floor");
		}
		return selectlaundryvalues;

	}

	public ArrayList<String> get_view(int index) {

		ArrayList<String> selectviewvalues = new ArrayList<String>();
		if (amenity_view_cityview.get(index).equals("1")) {
			selectviewvalues.add("City View");
		}
		if (amenity_view_facesstreet.get(index).equals("1")) {
			selectviewvalues.add("Faces Street");
		}
		if (amenity_view_facescourtyard.get(index).equals("1")) {
			selectviewvalues.add("Faces Courtyard");
		}
		if (amenity_view_waterviews.get(index).equals("1")) {
			selectviewvalues.add("Water Views");
		}

		return selectviewvalues;

	}

	public ArrayList<String> get_luxury(int index) {

		ArrayList<String> selectluxuryvalues = new ArrayList<String>();
		if (amenity_luxury_luxuryapartment.get(index).equals("1")) {
			selectluxuryvalues.add("Luxury Apartment");
		}
		if (amenity_luxury_exercisefacilities.get(index).equals("1")) {
			selectluxuryvalues.add("Excercise Facilities");
		}
		if (amenity_luxury_centralac.get(index).equals("1")) {
			selectluxuryvalues.add("Central AC");
		}
		if (amenity_luxury_doorman.get(index).equals("1")) {
			selectluxuryvalues.add("Doorman");
		}
		if (amenity_luxury_jacuzzi.get(index).equals("1")) {
			selectluxuryvalues.add("Jacuzzi");
		}
		if (amenity_luxury_sauna.get(index).equals("1")) {
			selectluxuryvalues.add("Sauna");
		}
		if (amenity_luxury_furnished.get(index).equals("1")) {
			selectluxuryvalues.add("Furnished");
		}

		return selectluxuryvalues;

	}

	public ArrayList<String> get_floor(int index) {

		ArrayList<String> selectfloorvalues = new ArrayList<String>();
		if (amenity_floor_elevator.get(index).equals("1")) {
			selectfloorvalues.add("Elevator");
		}
		if (amenity_floor_hardwood.get(index).equals("1")) {
			selectfloorvalues.add("Hardwood");
		}
		if (amenity_floor_ceramictile.get(index).equals("1")) {
			selectfloorvalues.add("Ceramic Tile");
		}
		if (amenity_floor_wtowcarpet.get(index).equals("1")) {
			selectfloorvalues.add("Wall to Wall Carpet");
		}
		if (amenity_floor_wtwcarpethardwood.get(index).equals("1")) {
			selectfloorvalues.add("Wall to Wall Carpet and Hardwood");
		}
		if (amenity_floor_marblefloors.get(index).equals("1")) {
			selectfloorvalues.add("Marble Floors");
		}
		return selectfloorvalues;

	}

	public ArrayList<String> get_windowlighting(int index) {

		ArrayList<String> selectwindowlightingvalues = new ArrayList<String>();
		if (amenity_windowslighting_recessedlights.get(index).equals("1")) {
			selectwindowlightingvalues.add("Recessed Lights");
		}
		if (amenity_windowslighting_skylights.get(index).equals("1")) {
			selectwindowlightingvalues.add("Sky Lights");
		}
		if (amenity_windowslighting_newwindows.get(index).equals("1")) {
			selectwindowlightingvalues.add("New Windows");
		}
		if (amenity_windowslighting_baywindows.get(index).equals("1")) {
			selectwindowlightingvalues.add("Bay Windows");
		}
		if (amenity_windowslighting_trackinglighting.get(index).equals("1")) {
			selectwindowlightingvalues.add("Tracking Lighting");
		}
		if (amenity_windowslighting_southernexposure.get(index).equals("1")) {
			selectwindowlightingvalues.add("Southern Exposure");
		}

		return selectwindowlightingvalues;

	}

	public ArrayList<String> get_parking(int index) {

		ArrayList<String> selectparkingvalues = new ArrayList<String>();
		if (amenity_parking_freestreetpermit.get(index) != null) {
			if (amenity_parking_freestreetpermit.get(index).equals("1")) {
				selectparkingvalues.add("Free On Street Parking with Permit");
			}
		}
		if (amenity_parking_freestreetnopermit.get(index) != null) {
			if (amenity_parking_freestreetnopermit.get(index).equals("1")) {
				selectparkingvalues.add("Free On Street Parking - No Permit");
			}
		}
		if (amenity_parking_offstreetspaceinc.get(index) != null) {
			if (amenity_parking_offstreetspaceinc.get(index).equals("1")) {
				selectparkingvalues.add("Off Street Space Included");
			}
		}
		if (amenity_parking_garagespaceinc.get(index) != null) {
			if (amenity_parking_garagespaceinc.get(index).equals("1")) {
				selectparkingvalues.add("Garage Space Included");
			}
		}

		return selectparkingvalues;

	}

	public ArrayList<String> get_extraroom(int index) {

		ArrayList<String> selectextraroomvalues = new ArrayList<String>();
		if (amenity_extraroom_alcove.get(index).equals("1")) {
			selectextraroomvalues.add("Alcove");
		}
		if (amenity_extraroom_attic.get(index).equals("1")) {
			selectextraroomvalues.add("Attic");
		}
		if (amenity_extraroom_atticstorage.get(index).equals("1")) {
			selectextraroomvalues.add("Attic Storage");
		}
		if (amenity_extraroom_diningroom.get(index).equals("1")) {
			selectextraroomvalues.add("Dining Room");
		}
		if (amenity_extraroom_extrastorage.get(index).equals("1")) {
			selectextraroomvalues.add("Extra Storage");
		}
		if (amenity_extraroom_basementstorage.get(index).equals("1")) {
			selectextraroomvalues.add("Basement Storage");
		}
		if (amenity_extraroom_finishedbasement.get(index).equals("1")) {
			selectextraroomvalues.add("Finished Basement");
		}
		if (amenity_extraroom_masterbedroom.get(index).equals("1")) {
			selectextraroomvalues.add("Master Bedroom");
		}
		if (amenity_extraroom_sunroom.get(index).equals("1")) {
			selectextraroomvalues.add("Sunroom");
		}

		return selectextraroomvalues;

	}

	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

	// Trick Zillow into thinking we are human by typing one char at a time

	public void TypeInField(String xpath, String value, WebDriver driver) {
		String val = value;
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();

		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			element.sendKeys(s);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private int theloop = 0;
	private WebDriver driver;
	private String currenturl;
	private ArrayList<String> list;
	private int currentindex;

	// WE DON'T HAVE A CHOICE BUT TO PASS THE PASSWORD SINCE WE HAVE TO AUTO LOGIN
	// FOR THE AGENT
	public PosterZillow(String[] pid, String uname, String upass, JTextField propertyinput, JCheckBox chinButton,
			int browserchoice, JCheckBox randomnumber, String firefoxprofile, String chromeprofile,
			JCheckBox googlelogin) {
		PosterReport report;
		String[] property_ID = pid;
		String pidquery = " ( ";

		for (int pcount = 0; pcount < property_ID.length; pcount++) {
			pidquery += "property_id = " + property_ID[pcount] + "";
			if (pcount < property_ID.length - 1)
				pidquery += " OR ";
		}
		pidquery += " ) ";
		String ppidquery = "";
		for (int ppcount = 0; ppcount < property_ID.length; ppcount++) {
			ppidquery += "photo_propertyid  = " + property_ID[ppcount] + "";
			if (ppcount < property_ID.length - 1)
				ppidquery += " OR ";
		}
		String transpoquery = "";
		for (int ppcount = 0; ppcount < property_ID.length; ppcount++) {
			transpoquery += "tp_property_property_id  = " + property_ID[ppcount] + "";
			if (ppcount < property_ID.length - 1)
				transpoquery += " OR ";
		}

		String user_name = uname;
		String user_password = upass;

		String db_name = "";
		String db_zillow_User = "";
		String db_zillow_Pass = "";
		String db_zillow_custom_id = "";

		List<String> db_propertyid = new ArrayList<String>();
		List<String> db_street = new ArrayList<String>();
		List<String> db_street_number = new ArrayList<String>();
		List<String> db_city = new ArrayList<String>();

		List<String> db_zip_code = new ArrayList<String>();
		List<String> db_rent = new ArrayList<String>();
		List<Integer> db_room_for_rent = new ArrayList<Integer>();
		List<String> db_square_feet = new ArrayList<String>();
		List<String> db_unit = new ArrayList<String>();
		List<String> db_bedroom = new ArrayList<String>();
		List<String> db_bathroom = new ArrayList<String>();
		List<String> db_day = new ArrayList<String>();
		List<String> db_year = new ArrayList<String>();
		List<String> db_month = new ArrayList<String>();

		List<String> db_photo_file_edit = new ArrayList<String>();
		List<String> db_photo_file = new ArrayList<String>();
		List<String> db_photo_property = new ArrayList<String>();
		Statement st = null;
		ResultSet rs = null;
		Statement st2 = null;
		ResultSet rs2 = null;
		Statement st3 = null;
		ResultSet rs3 = null;
		Statement st4 = null;
		ResultSet rs4 = null;
		Connection conn = null;

		// OMMITING MYSQL QUERY FOR SECURITY REASONS

		try {
			// All the variables that will be assigned MySQL info.

			// create our mysql database connection
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "";
			Class.forName(myDriver);
			// THIS IS A NONO. SERVER SHOULD BE QUERYING AND PASSING AS JSON NOT THE APP
			myUrl = "jdbc:mysql://hostname/db";

			conn = (Connection) DriverManager.getConnection(myUrl, "", "");

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String photo_query = "SELECT * FROM photo  WHERE " + ppidquery;

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			// request.getRequestDispatcher("/result.jsp").forward(request, response);
		}
		driver = null;

		if (browserchoice == 2) {
			System.setProperty("webdriver.edge.driver", exePathEdge);
			driver = new EdgeDriver();
		} else if (browserchoice == 3) {
			System.setProperty("webdriver.ie.driver", exePathIE);
			driver = new InternetExplorerDriver();
		} else if (browserchoice == 1) {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("disable-infobars");

			System.setProperty("webdriver.chrome.driver", exePathChrome2);
			driver = new ChromeDriver(chromeOptions);
		}

		else if (browserchoice == 0) {

			new DesiredCapabilities();
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("pageLoadStrategy", "eager");
			FirefoxOptions opt = new FirefoxOptions();
			opt.merge(caps);

			System.setProperty("webdriver.gecko.driver", exePathFirefox);
			driver = new FirefoxDriver(opt);
		}

		driver.manage().window().setSize(new Dimension(1360, 800));
		report = new PosterReport("Zillow Post Report", db_propertyid);

		list = new ArrayList<String>();
		currentindex = 0;
		currenturl = "";
		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {

				if (currenturl.equals(driver.getCurrentUrl()) && currentindex == theloop) {
					report.addUnsuccessfull_posts(db_propertyid.get(currentindex));
					timer.cancel();
					driver.quit();

					String[] stockArr = new String[list.size()];
					stockArr = list.toArray(stockArr);

					new PosterZillow(stockArr, uname, upass, propertyinput, chinButton, browserchoice, randomnumber,
							firefoxprofile, chromeprofile, googlelogin);

				}
				currentindex = theloop;
				currenturl = driver.getCurrentUrl();
			}

		}, 10000, 400000);

		for (theloop = 0; theloop < db_propertyid.size(); theloop++) {
			report.set_current_id(db_propertyid.get(theloop));
			report.update();
			try {
				if (!db_propertyid.get(theloop).equals("162949") || !db_propertyid.get(theloop).equals("163060")) {

					if (theloop == 0) {

						driver.get("https://www.zillow.com/user/acct/login/");
						try {
							Thread.sleep(8000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						((JavascriptExecutor) driver).executeScript("window.focus();");

						if (driver.findElements(By.xpath("//h4[@id='uuid']")).size() != 0) {

							WebDriverWait wait = new WebDriverWait(driver, 99990);
							By addItem = (By.id("reg-login-email"));
							wait.until(ExpectedConditions.presenceOfElementLocated(addItem));
						}
						if (!googlelogin.isSelected()) {
							if (!driver.findElement(By.tagName("body")).getText().contains("Agent Hub")) {
								driver.findElement(By.id("reg-login-email")).click();
								driver.findElement(By.id("reg-login-email")).clear();
								driver.findElement(By.id("reg-login-email")).sendKeys(db_zillow_User);

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								driver.findElement(By.id("inputs-password")).click();
								driver.findElement(By.id("inputs-password")).clear();
								driver.findElement(By.id("inputs-password")).sendKeys(db_zillow_Pass);

								try {
									Thread.sleep(5000);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}
								driver.findElement(By.xpath("//input[@value='Sign in']")).click();

								try {
									Thread.sleep((int) (Math.random() * 4000 + 1000));
								} catch (InterruptedException e) {

									e.printStackTrace();
								}

							}
						} else {
							String winHandleBefore = driver.getWindowHandle();

							// Perform the click operation that opens new window

							driver.findElement(By.xpath(
									"//div[@class='z-google-g-logo z-google-g-logo_badge social-icon-google-g']"))
									.click();
							try {
								Thread.sleep(4000);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
							for (String winHandle : driver.getWindowHandles()) {
								driver.switchTo().window(winHandle);
							}

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}

							driver.findElements(By.xpath("//input")).get(0).sendKeys(db_zillow_User);
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
							driver.findElement(By.xpath("//span[@class='CwaK9']")).click();
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
							driver.findElements(By.xpath("//input")).get(1).sendKeys(db_zillow_Pass);
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
							driver.findElement(By.xpath("//span[@class='CwaK9']")).click();
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}

							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}

							driver.switchTo().window(winHandleBefore);
						}
					}
					driver.get("https://www.zillow.com/rental-manager/properties");

					JavascriptExecutor js1 = (JavascriptExecutor) driver;

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

					String zillow_address = db_street_number.get(theloop) + " " + db_street.get(theloop) + ", "
							+ db_city.get(theloop) + ", " + "MA" + " " + db_zip_code.get(theloop);

					String initials = "";

					initials = db_zillow_custom_id;

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

					js1.executeScript(
							"document.querySelector(\"[class='Link Link-secondary PropertyCard-heading-add-property']\").click();");

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

					driver.findElement(By.cssSelector(".Autocomplete-InputContainer > div > .Input-container > .Input"))
							.sendKeys(zillow_address);

					String[] stringArray = propertyinput.getText().replaceAll("\\s+", "").split(",");
					list = new ArrayList<String>(Arrays.asList(stringArray));
					list.remove(db_propertyid.get(theloop));

					String res = String.join(",", list);

					propertyinput.setText(res);

					boolean atleastOneAlpha = db_unit.get(theloop).matches(".*[a-zA-Z]+.*");
					String sendUnit = "";
					if (!randomnumber.isSelected()) {
						if (atleastOneAlpha) {
							sendUnit = db_unit.get(theloop);

						} else {
							if (initials.length() > 1)
								sendUnit = initials.substring(0, 1) + db_unit.get(theloop);
							else
								sendUnit = initials + db_unit.get(theloop);
						}
					} else {
						sendUnit = Integer.toString(((int) (Math.random() * 300)));
					}

					driver.findElement(By.cssSelector(".SpacerRow > div > .Input-container > .Input"))
							.sendKeys(sendUnit);

					Select propertytype = new Select(driver.findElement(By.cssSelector(".Select-element")));
					propertytype.selectByIndex(2);

					if (db_room_for_rent.get(theloop) == 1) {
						driver.findElement(By.xpath("//div[@class='Checkbox-box']")).click();
					}

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					if (driver.findElements(By.xpath("//button[@class='Button Button-primary Button-full']"))
							.size() != 0) {

						driver.findElement(By.xpath("//button[@class='Button Button-primary Button-full']"))
								.sendKeys(Keys.ENTER);
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

					if (driver.findElements(By.xpath("//button[@class='Button Button-primary Button-full']"))
							.size() != 0) {

						driver.findElement(By.xpath("//button[@class='Button Button-primary Button-full']"))
								.sendKeys(Keys.ENTER);
					}

					WebDriverWait waitmain = new WebDriverWait(driver, 40);

					By addItemmain = By.xpath("//input[@name='price']");

					// get the "Add Item" element
					waitmain.until(ExpectedConditions.presenceOfElementLocated(addItemmain));

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

					JavascriptExecutor jse = (JavascriptExecutor) driver;

					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath("//input[@name='price']")));

					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

					if (driver.findElement(By.xpath("//input[@name='price']")).getAttribute("value").isEmpty()) {

						driver.findElement(By.xpath("//input[@name='price']")).clear();
						driver.findElement(By.xpath("//input[@name='price']")).sendKeys(db_rent.get(theloop));

						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						driver.findElement(By.xpath("//select/option[@value='1y']")).click();
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						driver.findElement(By.xpath("//input[@name='sqft']")).click();

						driver.findElement(By.xpath("//textarea[@name='description']"))
								.sendKeys("VIDEO TOUR AVAILABLE!!");

						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						String index_start = "0";
						switch (db_bedroom.get(theloop).toLowerCase()) {
						case "0":
							index_start = "0";
							break;
						case "1":
							index_start = "1";
							break;
						case "2":
							index_start = "2";
							break;
						case "3":
							index_start = "3";
							break;
						case "4":
							index_start = "4";
							break;
						case "5":
							index_start = "5";
							break;
						case "6":
							index_start = "6";
							break;
						case "7":
							index_start = "7";
							break;
						case "8":
							index_start = "8";
							break;
						}
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						Select bedrooms = new Select(driver.findElement(By.xpath("//select[@name='beds']")));
						bedrooms.selectByValue(index_start);
						driver.findElement(By.xpath("//input[@name='sqft']")).click();

						String index2_start = "1";
						switch (db_bathroom.get(theloop)) {
						case "0":
							index2_start = "1";
							break;
						case "1":
							index2_start = "1.5";
							break;
						case "2":
							index2_start = "2";
							break;
						case "3":
							index2_start = "2.5";
							break;
						case "4":
							index2_start = "3";
							break;
						case "5":
							index2_start = "3.5";
							break;
						case "6":
							index2_start = "4";
							break;
						case "7":
							index2_start = "4.5";
							break;
						case "8":
							index2_start = "5";
							break;
						case "9":
							index2_start = "5";
							break;
						case "10":
							index2_start = "5";
							break;
						}
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						Select bathrooms = new Select(driver.findElement(By.xpath("//select[@name='baths']")));
						bathrooms.selectByValue(index2_start);
						driver.findElement(By.xpath("//input[@name='sqft']")).click();

						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						if (db_square_feet.get(theloop) != null) {
							if (!db_square_feet.get(theloop).equals("")) {
								try {

									float square_feet = Float.parseFloat(db_square_feet.get(theloop));
									int square_feet_int = Math.round(square_feet);

									if (square_feet_int > 0) {
										driver.findElement(By.xpath("//input[@name='sqft']")).clear();
										driver.findElement(By.xpath("//input[@name='sqft']"))
												.sendKeys(db_square_feet.get(theloop));
									}
								} catch (NumberFormatException e) {
									// It's OK to ignore "e" here because returning a default value is the
									// documented behaviour on invalid input.

								}
							}
						}

						int index5 = Integer.parseInt(db_month.get(theloop));

						String db_month_word = "";
						switch (db_month.get(theloop)) {
						case "1":
							db_month_word = "January";
							break;
						case "2":
							db_month_word = "February";
							break;
						case "3":
							db_month_word = "March";
							break;
						case "4":
							db_month_word = "April";
							break;
						case "5":
							db_month_word = "May";
							break;
						case "6":
							db_month_word = "June";
							break;
						case "7":
							db_month_word = "July";
							break;
						case "8":
							db_month_word = "August";
							break;
						case "9":
							db_month_word = "September";
							break;
						case "10":
							db_month_word = "October";
							break;
						case "11":
							db_month_word = "November";
							break;
						case "12":
							db_month_word = "December";
							break;
						}

						boolean clicked = false;

						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						int db_new_day = Integer.parseInt(db_day.get(theloop));

						Date dates = new Date();
						Calendar cal = Calendar.getInstance();
						cal.setTime(dates);
						int year = cal.get(Calendar.YEAR);
						int month = cal.get(Calendar.MONTH);
						int day = cal.get(Calendar.DAY_OF_MONTH);

						Date date1 = null;
						try {
							date1 = new SimpleDateFormat("dd/MM/yyyy")
									.parse(db_day.get(theloop) + "/" + index5 + "/" + db_year.get(theloop));
						} catch (ParseException e1) {

							e1.printStackTrace();
						}
						Date date2 = null;
						try {
							date2 = new SimpleDateFormat("dd/MM/yyyy").parse(day + "/" + (month + 1) + "/" + year);
						} catch (ParseException e1) {

							e1.printStackTrace();
						}

						if (date1.getTime() < date2.getTime()) {
							driver.findElement(By.xpath("//a[contains(text(), 'Set to available now')]")).click();
						} else {
							driver.findElement(By.xpath("//input[@name='Date Available']")).click();
							while (clicked == false) {
								String dayyear = driver.findElement(By.cssSelector(".DayPicker-Caption > div"))
										.getText();

								if (!dayyear.contains(db_month_word) || !dayyear.contains(db_year.get(theloop))) {

									driver.findElement(
											By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']"))
											.click();

								} else if (dayyear.contains(db_month_word) && dayyear.contains(db_year.get(theloop))) {
									if (!driver.findElements(By.xpath(
											"//div[@class='DayPicker-Day' and contains(text(), '" + db_new_day + "')]"))
											.isEmpty()) {
										driver.findElement(
												By.xpath("//div[@class='DayPicker-Day' and contains(text(), '"
														+ db_new_day + "')]"))
												.click();
									} else {
										driver.findElement(By.xpath(
												"//div[@class='DayPicker-Day DayPicker-Day--selected DayPicker-Day--today' and contains(text(), '"
														+ db_new_day + "')]"))
												.click();
									}
									clicked = true;
								}
								try {
									Thread.sleep(300);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}
							}
						}
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						if (chinButton.isSelected())
							driver.findElement(By.xpath(
									"//div[@class='Text Text-body Checkbox-label' and contains(text(), 'Hide property address on listing')]"))
									.click();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						WebElement contact_name = driver.findElement(By.xpath("//input[@name='contactName']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contact_name);

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						// CONTACT NAME
						driver.findElement(By.xpath("//input[@name='contactName']")).clear();
						TypeInField("//input[@name='contactName']", db_name, driver);
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						driver.findElement(By.xpath("//input[@name='contactName']")).clear();
						TypeInField("//input[@name='contactName']", db_name, driver);
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						driver.findElement(By.xpath("//div[@data-test='contact-broker']")).click();

						// MOVE SCREEN

						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
								driver.findElement(By.xpath("//div[@data-test='property-amenities-laundry-none']")));

						driver.findElement(By.xpath("//div[@data-test='property-amenities-laundry-none']")).click();

						if (amenity_laundry_nearby.get(theloop).equals("1")) {

							driver.findElement(By.xpath("//div[@data-test='property-amenities-laundry-none']")).click();
						}

						if (amenity_laundry_hookups.get(theloop).equals("1")) {

							driver.findElement(By.xpath("//div[@data-test='property-amenities-laundry-none']")).click();
						}
						if (amenity_laundry_inunit.get(theloop).equals("1")) {

							driver.findElement(By.xpath("//div[@data-test='property-amenities-laundry-in-unit']"))
									.click();
						}
						if (amenity_laundry_inbuilding.get(theloop).equals("1")) {

							driver.findElement(By.xpath("//div[@data-test='property-amenities-laundry-in-building']"))
									.click();
						}
						if (amenity_laundry_free.get(theloop).equals("1")) {

							driver.findElement(By.xpath("//div[@data-test='property-amenities-laundry-in-building']"))
									.click();
						}
						if (amenity_laundry_laundryroom.get(theloop).equals("1")) {

							driver.findElement(By.xpath("//div[@data-test='property-amenities-laundry-in-building']"))
									.click();
						}
						if (amenity_laundry_onsamefloor.get(theloop).equals("1")) {

							driver.findElement(By.xpath("//div[@data-test='property-amenities-laundry-in-building']"))
									.click();
						}

						if (amenity_luxury_centralac.get(theloop).equals("1")) {
							driver.findElement(By
									.xpath("//div[@class='Text Text-body Checkbox-label' and contains(text(), 'A/C')]"))
									.click();
						}
						if (amenity_exteriorfeature_balcony.get(theloop).equals("1")) {
							driver.findElement(By.xpath(
									"//div[@class='Text Text-body Checkbox-label' and contains(text(), 'Balcony or deck')]"))
									.click();
						}
						if (amenity_luxury_furnished.get(theloop).equals("1")) {
							driver.findElement(By.xpath(
									"//div[@class='Text Text-body Checkbox-label' and contains(text(), 'Furnished')]"))
									.click();
						}
						if (amenity_floor_hardwood.get(theloop).equals("1")) {
							driver.findElement(By.xpath(
									"//div[@class='Text Text-body Checkbox-label' and contains(text(), 'Hardwood floor')]"))
									.click();
						}

						if (amenity_parking_offstreetspaceinc.get(theloop) != null) {
							if (Integer.parseInt(amenity_parking_offstreetspaceinc.get(theloop)) == 1) {
								driver.findElement(By.xpath(
										"//div[@class='Text Text-body Checkbox-label' and contains(text(), 'Off-street parking')]"))
										.click();

							}
						}

						if (amenity_parking_garagespaceinc.get(theloop) != null) {
							if (Integer.parseInt(amenity_parking_garagespaceinc.get(theloop)) == 1) {

								driver.findElement(By.xpath(
										"//div[@class='Text Text-body Checkbox-label' and contains(text(), 'Garage parking')]"))
										.click();
							}
						}

						boolean dog_ok_start = false;
						boolean cat_ok_start = false;
						boolean dog_small_start = false;
						boolean dog_large_start = false;

						if (amenity_pet_nopets.get(theloop).equals("1")
								|| amenity_pet_unsure.get(theloop).equals("1")) {
							dog_ok_start = false;
							cat_ok_start = false;
						}

						if (amenity_pet_cats.get(theloop).equals("1") || amenity_pet_catsneg.get(theloop).equals("1")) {
							cat_ok_start = true;
						}

						if (amenity_pet_dogs.get(theloop).equals("1") || amenity_pet_dogsneg.get(theloop).equals("1")) {
							dog_ok_start = true;
						}

						if (amenity_pet_petsnegotiable.get(theloop).equals("1")) {
							dog_ok_start = true;
							cat_ok_start = true;
						}

						if (amenity_pet_smallpets.get(theloop).equals("1")
								|| amenity_pet_weightrestrictions.get(theloop).equals("1")) {
							dog_small_start = true;
						}

						if (dog_ok_start == false && cat_ok_start == false) {
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							driver.findElement(By.xpath(
									"//div['@class=Text Text-body Checkbox-label' and contains(text(), 'No pets allowed')]"))
									.click();

						}

						if (dog_ok_start == true) {
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}

							driver.findElement(By.xpath(
									"//div['@class=Text Text-body Checkbox-label' and contains(text(), 'Large dogs OK')]"))
									.click();
							if (dog_small_start == true) {
								try {
									Thread.sleep(300);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}

								driver.findElement(By.xpath(
										"//div['@class=Text Text-body Checkbox-label' and contains(text(), 'Small dogs OK')]"))
										.click();
							}
							if (dog_large_start == true) {
								try {
									Thread.sleep(300);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}

								driver.findElement(By.xpath(
										"//div['@class=Text Text-body Checkbox-label' and contains(text(), 'Large dogs OK')]"))
										.click();
							}
						}

						if (cat_ok_start == true) {
							driver.findElement(By
									.xpath("//div['@class=Text-body Checkbox-label' and contains(text(), 'Cats OK')]"))
									.click();
						}

						WebElement amementies_input = driver
								.findElement(By.xpath("//input[@data-test='amenities-add-input']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
								amementies_input);
						List<String> selectspacevalues = get_space(theloop);
						List<String> selectkitchenvalues = get_kitchen(theloop);
						List<String> selectutilityvalues = get_utility(theloop);
						List<String> selectinteriorvalues = get_interior(theloop);
						List<String> selectviewvalues = get_view(theloop);
						List<String> selectlaundryvalues = get_laundry(theloop);
						List<String> selectluxuryvalues = get_luxury(theloop);
						List<String> selectfloorvalues = get_floor(theloop);
						List<String> selectparkingvalues = get_parking(theloop);
						List<String> selectextraroomvalues = get_extraroom(theloop);
						List<String> selectwindowlightingvalues = get_windowlighting(theloop);
						List<String> selectexteriorvalues = get_exterior(theloop);

						selectspacevalues.addAll(selectkitchenvalues);
						selectspacevalues.addAll(selectutilityvalues);
						selectspacevalues.addAll(selectinteriorvalues);
						selectspacevalues.addAll(selectlaundryvalues);
						selectspacevalues.addAll(selectfloorvalues);
						selectspacevalues.addAll(selectextraroomvalues);
						selectspacevalues.addAll(selectwindowlightingvalues);
						selectspacevalues.addAll(selectviewvalues);
						selectspacevalues.addAll(selectluxuryvalues);
						selectspacevalues.addAll(selectparkingvalues);
						selectspacevalues.addAll(selectexteriorvalues);

						String zillow_ameneties_tmp = String.join(", ", selectspacevalues);

						String[] zillow_ameneties = zillow_ameneties_tmp.split(",");

						for (int i = 0; i < zillow_ameneties.length; i++) {
							if (!zillow_ameneties[i].contains("Grad") && !zillow_ameneties[i].contains("Located")
									&& !zillow_ameneties[i].contains("Wants") && !zillow_ameneties[i].contains("Shows")
									&& !zillow_ameneties[i].contains("Tenants")
									&& !zillow_ameneties[i].contains("Amazing Views")
									&& !zillow_ameneties[i].contains("A/C")
									&& !zillow_ameneties[i].contains("Balcony / Deck")
									&& !zillow_ameneties[i].contains("Furnished")
									&& !zillow_ameneties[i].contains("Hardwood Floor")) {

								driver.findElement(By.xpath("//input[@data-test='amenities-add-input']")).clear();

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								TypeInField("//input[@data-test='amenities-add-input']", zillow_ameneties[i], driver);

								driver.findElement(By.xpath("//button[@data-test='amenities-add-button']")).click();

							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						WebElement pics = driver
								.findElement(By.xpath("//div[@class='PhotoUpload-dropzone-container']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pics);

						final WebElement imageSelect = (WebElement) driver
								.findElement(By.xpath("//input[@name='sourceFile']"));
						String js = "arguments[0].style.display = 'inline';";
						String jsmultiple = "arguments[0].removeAttribute('multiple');";
						jse.executeScript(js, imageSelect);
						jse.executeScript(jsmultiple, imageSelect);

						ArrayList<String> image2 = new ArrayList<String>();

						for (int i = 0; i < db_photo_file.size(); i++) {

							if (db_photo_property.get(i).equals(db_propertyid.get(theloop))) {
								String imageUrl = "";
								if (db_photo_file_edit.get(i) == null) {
									imageUrl = "http://www.previewbostonrealty.com/images/properties/"
											+ db_photo_file.get(i);
								} else {
									imageUrl = "http://www.previewbostonrealty.com/login/property_images_edit/"
											+ db_photo_file_edit.get(i);

								}

								image2.add(db_photo_file.get(i));

								String destinationFile = "images" + imageDirectory + db_photo_file.get(i);

								try {
									saveImage(imageUrl, destinationFile);
								} catch (IOException e) {

									e.printStackTrace();
								}

							}
						}

						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						for (int i = 0; i < image2.size(); i++) {

							if (System.getProperty("user.dir") + imageDirectory + "images" + imageDirectory
									+ image2.get(i) != null) {

								try {

									imageSelect.sendKeys(System.getProperty("user.dir") + imageDirectory + "images"
											+ imageDirectory + image2.get(i));

								} catch (org.openqa.selenium.StaleElementReferenceException ex) {

									imageSelect.sendKeys(System.getProperty("user.dir") + imageDirectory + "images"
											+ imageDirectory + image2.get(i));
								}

								try {
									Thread.sleep(5000);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}

							}
						}

						String jsa = "arguments[0].style.visibility = 'none';";

						jse.executeScript(jsa, imageSelect);

						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						if (driver.findElements(By
								.xpath("//div[@class='Text Text-body Radio-label' and contains(text(), 'I will man')]"))
								.size() != 0) {
							WebElement flipper_name = driver.findElement(By.xpath(
									"//div[@class='Text Text-body Radio-label' and contains(text(), 'I will man')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
									flipper_name);
							driver.findElements(By.xpath(
									"//div[@class='Text Text-body Radio-label' and contains(text(), 'I will man')]"))
									.get(0).click();

						}

						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						if (driver.findElements(By.xpath(
								"//div[@class='StickyListingBanner-col']//button[@class='Button Button-primary']"))
								.size() != 0) {

							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
									driver.findElements(By.xpath(
											"//div[@class='StickyListingBanner-col']//button[@class='Button Button-primary']"))
											.get(0));
							new Actions(driver).moveToElement(driver.findElements(By.xpath(
									"//div[@class='StickyListingBanner-col']//button[@class='Button Button-primary']"))
									.get(0)).perform();

							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}

							driver.findElements(By.xpath(
									"//div[@class='StickyListingBanner-col']//button[@class='Button Button-primary']"))
									.get(0).click();
						}
					}
					try {
						Thread.sleep(15000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					if (driver.findElements(By.xpath(
							"//div['@class=Text Text-body' and contains(text(), 'unable to activate this listing')]"))
							.size() != 0) {
						driver.navigate().refresh();
						try {
							Thread.sleep(15000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						WebElement contact_name = driver.findElement(By.xpath("//input[@name='contactName']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contact_name);

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// CONTACT NAME
						driver.findElement(By.xpath("//input[@name='contactName']")).clear();

						TypeInField("//input[@name='contactName']", db_name.substring(0, db_name.length() - 1), driver);

						driver.findElement(By.xpath("//input[@name='contactPhone']")).click();
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						// ZILLOW IS HORRIBLE AND DOSNT SAVE ON THE FIRST ATTEMPT
						driver.findElement(By.xpath("//input[@name='contactName']")).click();

						driver.findElement(By.xpath("//input[@name='contactName']")).clear();
						TypeInField("//input[@name='contactName']", db_name, driver);
						driver.findElement(By.xpath("//input[@name='contactPhone']")).click();
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
								driver.findElements(By.xpath(
										"//div[@class='StickyListingBanner-col']//button[@class='Button Button-primary']"))
										.get(0));

						driver.findElements(By.xpath(
								"//div[@class='StickyListingBanner-col']//button[@class='Button Button-primary']"))
								.get(0).sendKeys(Keys.ENTER);
						try {
							Thread.sleep(15000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

					}

					report.set_count(report.get_count() + 1);
					report.update();

				}
			} catch (Exception e) {

			}
		}
		// Close the driver
		driver.quit();
	}
}
