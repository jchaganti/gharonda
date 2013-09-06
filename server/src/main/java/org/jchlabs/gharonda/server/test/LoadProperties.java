package org.jchlabs.gharonda.server.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import org.jchlabs.gharonda.domain.model.Neightbourhood;
import org.jchlabs.gharonda.domain.model.Notifierprofiles;
import org.jchlabs.gharonda.domain.model.Properties;
import org.jchlabs.gharonda.domain.model.Serviceproviderdetails;
import org.jchlabs.gharonda.domain.model.Users;
import org.jchlabs.gharonda.domain.pom.dao.ContentitemsDAO;
import org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO;
import org.jchlabs.gharonda.domain.pom.dao.NeightbourhoodDAO;
import org.jchlabs.gharonda.domain.pom.dao.NotifierprofilesDAO;
import org.jchlabs.gharonda.domain.pom.dao.PropertiesDAO;
import org.jchlabs.gharonda.domain.pom.dao.ServiceproviderdetailsDAO;
import org.jchlabs.gharonda.domain.pom.dao.UsersDAO;
import org.jchlabs.gharonda.shared.rpc.CommonConstants;


public class LoadProperties {

	private static String[] words;
	private static List<String[]> geodata = new ArrayList<String[]>(SuburbsData1.geodata1.length);

	private Random random = new Random(System.currentTimeMillis());
	static {
		// random words
		String string = "Period originality and assured modern style form a fabulous combination throughout this fully renovated, gloriously presented Victorian. Stained glass entrance and elegant marble tiled hall establish a refined tone for luxurious living featuring surround sound, beautiful adjoining dining and large living/entertaining area opening to charming garden. Four bedrooms, one featuring spa en suite and sauna, central bathroom and separate laundry/third shower accommodate in consummate comfort, separate study adds flexibility to an exceptional floor-plan. Quality modern kitchen. Six OFPs, heating, air-conditioning, broadband and Foxtel connection complement sublime surroundings. Superb European Oak parquetry floors (with Jarrah inlays) throughout, video security, infra red surveillance and garage enhance this AAA Toorak address. Land: 635sq m (6,838sq ft).";
		StringTokenizer stringTokenizer = new StringTokenizer(string);
		Set<String> set = new HashSet<String>();
		while (stringTokenizer.hasMoreTokens()) {
			set.add(stringTokenizer.nextToken());
		}
		words = set.toArray(new String[0]);
		// geodata
		for (int i = 0; i < SuburbsData1.geodata1.length; i++) {
			geodata.add(SuburbsData1.geodata1[i]);
		}

	}

	public LoadProperties() {
		ContentitemsDAO cDAO = (ContentitemsDAO) ContentitemsDAO.getInstance();
		UsersDAO uDAO = (UsersDAO) UsersDAO.getInstance();
		PropertiesDAO pDAO = (PropertiesDAO) PropertiesDAO.getInstance();

		EmailfrequenciesDAO eDAO = (EmailfrequenciesDAO) EmailfrequenciesDAO.getInstance();
		NotifierprofilesDAO nDAO = (NotifierprofilesDAO) NotifierprofilesDAO.getInstance();
		ServiceproviderdetailsDAO sDAO = (ServiceproviderdetailsDAO) ServiceproviderdetailsDAO.getInstance();

		NeightbourhoodDAO neDAO = (NeightbourhoodDAO) NeightbourhoodDAO.getInstance();
		Notifierprofiles n = new Notifierprofiles();
		nDAO.save(n);

		Serviceproviderdetails s = new Serviceproviderdetails();
		s.setBusinessType(1);
		// s.setPhone(new Integer((random.nextInt(10) * 1101) + random.nextInt(10)).toString());
		// s.setFax(new Integer((random.nextInt(10) * 1101) + random.nextInt(10)).toString());
		s.setWebsite("www.gemrental.com");

		sDAO.save(s);

		Users u = new Users(null, "john.wayne@gmail.com", "d", "John", "Wayne");

		u.setNotifierprofiles(n);
		u.setServiceproviderdetails(s);
		uDAO.save(u);

		for (int i = 0; i < 1200; i++) {

			Properties p = new Properties();

			p.setUser(u);
			p.setAddrNum(randomAddress_num());
			p.setStreetName(randomAddress_street());
			String[] geoCode = randomGeoCode();
			p.setSuburb(geoCode[0] + "sub");
			p.setCity(geoCode[0]);
			p.setState(geoCode[1]);
			p.setLat(Double.parseDouble(geoCode[2]));
			p.setLng(Double.parseDouble(geoCode[3]));
			p.setBathRooms(randomInt(4));
			p.setBuildDate("19" + i);
			p.setDescription(randomText(random.nextInt(words.length - 1)));
			p.setTitle(randomText(random.nextInt(10)));
			p.setStreetName(randomAddress_street());
			p.setTimeStamp(System.currentTimeMillis() + i);
			p.setCreated(System.currentTimeMillis() - random.nextInt(10) * 86400000);
			p.setHomeLoan(new Integer(random.nextInt(2)));
			p.setFloor(new Integer(random.nextInt(19)));
			p.setPostCode(new Integer((random.nextInt(10) * 500) + random.nextInt(10)).toString());
			p.setIsactive(new Integer(1));
			p.setIsFeatured(new Integer(1));
			p.setHeat(random.nextInt(13));
			p.setPType(random.nextInt(19));
			p.setView(random.nextInt(7));
			p.setBedRooms(random.nextInt(19));
			p.setContentitems(null);
			p.setPrice(new Long(randomPrice()).intValue());
			p.setSqrft(random.nextInt(250));
			p.setCurrency(1);
			p.setWipState(2);
			p.setPMode(i % 2 + 1);
			int[] amenities = new int[CommonConstants.NUM_AMENITIES];
			Arrays.fill(amenities, 0);
			int num = random.nextInt(CommonConstants.NUM_AMENITIES);
			amenities[num] = 1;
			p.setAmenity1(num);

			num = random.nextInt(CommonConstants.NUM_AMENITIES);
			amenities[num] = 1;
			p.setAmenity2(num);

			num = random.nextInt(CommonConstants.NUM_AMENITIES);
			amenities[num] = 1;
			p.setAmenity3(num);

			num = random.nextInt(CommonConstants.NUM_AMENITIES);
			amenities[num] = 1;
			p.setAmenity4(num);

			pDAO.save(p);

		}

		for (int i = 0; i < 1200; i++) {
			Neightbourhood n1 = new Neightbourhood();
			String[] geoCode = randomGeoCode();
			int cat = random.nextInt(8);
			n1.setCategory(cat + 1);
			n1.setLat(Double.parseDouble(geoCode[2]) + 1.1723);
			n1.setLng(Double.parseDouble(geoCode[3]) + 2.1123);
			neDAO.save(n1);
		}

	}

	public static void main(String[] strings) {
		new LoadProperties();
	}

	private String[] randomGeoCode() {

		return geodata.get(this.random.nextInt(417));
	}

	private String randomText(int wordsCount) {

		String randomText = "";
		for (int i = 0; i < wordsCount; i++) {
			randomText += " " + words[this.random.nextInt(words.length)];
		}
		return randomText;
	}

	private String randomAddress_streetType() {
		String[] streetType = new String[] { "street", "line", "road", "avenue", "highway" };
		return streetType[this.random.nextInt(streetType.length)];
	}

	private String randomAddress_street() {
		String[] streetNames = new String[] { "Pine", "Miller", "Pacific", "Gloucester", "Janka Krala" };
		return streetNames[this.random.nextInt(streetNames.length)];
	}

	private String randomAddress_num() {
		return this.random.nextInt(4) + "/" + this.random.nextInt(560);
	}

	private long randomPrice() {
		return this.random.nextInt(2000) * 1000;
	}

	private int randomInt(int i) {
		return this.random.nextInt(i);
	}

	private long randomTimestamp() {
		return System.currentTimeMillis();
	}

}
