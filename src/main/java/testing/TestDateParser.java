package testing;

import java.sql.Date;
import java.time.LocalDate;

import util.DbUtil;
import util.ServletHTMLUtil;

public class TestDateParser {

    public static void main(String[] args) {
        java.util.Date jutilDate = new java.util.Date();
        System.out.println("Java Util Date:" + jutilDate);
        System.out.println("SQL Date:" + DbUtil.jutilToSqlData(jutilDate));

        Date sqlDate = Date.valueOf(LocalDate.now());
        System.out.println("SQL Date:" + sqlDate);
        System.out.println("Java Util Date:" + DbUtil.sqlToJUtilData(sqlDate));

        sqlDate = Date.valueOf(LocalDate.of(1988, 07, 30));
        System.out.println("SQL Date:" + sqlDate);
        System.out.println("Java Util Date:" + DbUtil.sqlToJUtilData(sqlDate));

    	System.out.println();
    	System.out.println("isValid - dd/MM/yyyy with 2013 09 25 = " + ServletHTMLUtil.isDateStrValidFormat("yyyy MM dd", "20130925"));
        System.out.println("isValid - dd/MM/yyyy with 25/09/2013 = " + ServletHTMLUtil.isDateStrValidFormat("dd/MM/yyyy", "25/09/2013"));
        System.out.println("isValid - dd/MM/yyyy with 25/09/2013 12:13:50 = " + ServletHTMLUtil.isDateStrValidFormat("dd/MM/yyyy", "25/09/2013  12:13:50"));

        Date date = ServletHTMLUtil.getDate("25.9.2013");
        System.out.println(date);
    }

	/* moved to ServletHTMLUtil
	 * private static Date getDate(String dataStr) {
        String[] supportedDateFormats = {// getDate("2013-09-25");
        						"yyyy-MM-dd",
						        // getDate("2013/09/25");
        						"yyyy/MM/dd",
						        // getDate("20130925");
        						"yyyyMMdd",
						        // getDate("2013.09.25");
        						"yyyy.MM.dd",
						        // getDate("2013 09 25");
        						"yyyy MM dd",
						
						        // getDate("25.09.2013");
        						"dd.MM.yyyy",
						        // getDate("25-09-2013");
        						"dd-MM-yyyy",
						        // getDate("25/09/2013");
        						"dd/MM/yyyy",
						        //! getDate("25 09 2013");
        						"dd MM yyyy",
						        // getDate("25092013");
        						"ddMMyyyy"
        						};
        for (String format : supportedDateFormats) {
        	if (ServletHTMLUtil.isDateStrValidFormat(format, dataStr))
        		return ServletHTMLUtil.getDate(format, dataStr);
        }
        
		return null;
	}
	 */

    /* moved to ServletHTMLUtil
     * public static boolean isDateStrValidFormat(String format, String dateStr) {
        java.util.Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(dateStr);
            if (!dateStr.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
           // not neede! ex.printStackTrace();
        }
        return date != null;
    }*/

    /* moved to DbUtil
     * public static Date jutilToSqlData(java.util.Date jutilDate) {
        Date sqlDate = new Date(jutilDate.getTime());
        return sqlDate;
      }
    
    public static java.util.Date sqlToJUtilData(Date sqlDate) {
        java.util.Date jutilDate = new java.util.Date(sqlDate.getTime());
        return jutilDate;
      }
     */

}
