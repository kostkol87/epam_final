package JSTL;

import DAO.Entities.Direction;
import DAO.Utils.Directions;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Custom JSTL tag, generates html table filled by all available directions
 */
public class DirectionsTag extends TagSupport {
    private static final Logger log = Logger.getLogger(DirectionsTag.class);
    public static final int ON_PAGE = 25;
    private int admin;
    private int page;
    private StringBuilder tagView;
    private String locale;
    private ResourceBundle localedDict;


    /**
     * this is required tag
     *
     * @param page - current page (used by paginator)
     */
    public void setPage(int page) {
        this.page = page;
    }

    public void setAdmin(int isAdmin) {
        this.admin = isAdmin;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        List<Direction> directions = Directions.getDirections();
        if (locale == "" || locale == null) {
            this.setLocale("ru_RU");
        }
        String[] sLocale = locale.split("_");
        Locale myLocale = new Locale(sLocale[0], sLocale[1]);
        int count = directions.size();
        tagView = new StringBuilder();
        String rmTitle = "";
        String rmButton1 = "";
        String rmButton2 = "";
        localedDict = ResourceBundle.getBundle("local", myLocale);

        if (admin == 3) {
            rmTitle = "<td> " + localedDict.getString("Remove") + "  </td>";
            rmButton1 = "<td><button onclick=\"accept(";
            rmButton2 = ")\"> " + localedDict.getString("Remove") + " </button></td></tr>";
        }
        try {

            tagView.append("<table border=\"1\">" +
                    "<tr>" +
                    "<td>" + localedDict.getString("Departure") + "</td>" +
                    "<td> " + localedDict.getString("Destination") + " </td>" +
                    "<td> " + localedDict.getString("DepTime") + " </td>" +
                    "<td> " + localedDict.getString("DestTime") + " </td>" +
                    "<td> " + localedDict.getString("Price") + " </td>" +
                    "<td> " + localedDict.getString("Capacity") + " /<br> " + localedDict.getString("placesLeft") + " </td>" +
                    "<td> " + localedDict.getString("Buy") + " </td>" + rmTitle +
                    "<tr>");

            tagView.append("</tr>");
            for (int i = (page - 1) * 25; i < ON_PAGE * page; i++) {
                if (i == count) break;
                if (directions.get(i) == null) {
                    continue;
                }
                tagView.append("<tr>" +
                        "<td> " + directions.get(i).getDeparture() + " </td>" +
                        "<td> " + directions.get(i).getDestination() + " </td>" +
                        "<td> " + directions.get(i).getDepTime() + " </td>" +
                        "<td> " + directions.get(i).getDestTime() + " </td>" +
                        "<td> " + directions.get(i).getBasicPrice() + " </td>" +
                        "<td> " + directions.get(i).getCapacity() + "/" + directions.get(i).getLeftPlaces() + " </td>" +
                        (directions.get(i).getLeftPlaces() > 0 ?
                                "<td>  <input type='button' value=\"" + localedDict.getString("Buy") +
                                        " \" onclick=\"window.location = '/newOrder?id=" + directions.get(i).getId() + "'\" </td>" :
                                "<td></td>"));
                if (admin == 3) {
                    tagView.append(rmButton1 + directions.get(i).getId() + rmButton2);
                }

            }
            tagView.append("</table>");
            pageContext.getOut().print(tagView);
        } catch (IOException | IndexOutOfBoundsException e) {
            log.warn("troubles in Directions JST tag");
            e.printStackTrace();

        }
        return SKIP_BODY;
    }
}