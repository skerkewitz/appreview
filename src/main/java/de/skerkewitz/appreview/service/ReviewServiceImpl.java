package de.skerkewitz.appreview.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation.
 *
 * @author skerkewitz, 2016-03-20
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Log logger = LogFactory.getLog(ReviewServiceImpl.class);

    @Override
    public List<ReviewEntry> fetchEntries(int appId) {
        /* Fetch the entries for each country and merge the result into one list. */
        List<ReviewEntry> resultList = new ArrayList<>();
        for (AppStoreCC appStoreCC : AppStoreCC.values()) {
            try {
                resultList.addAll(fetchEntriesInCountry(appId, appStoreCC));
            } catch (IOException | SAXException | ParserConfigurationException | XPathExpressionException e) {
                logger.error("Could not parse list for " + appStoreCC + " because of: " + e, e);
            }
        }

        /* Sort the list by date. */
        Collections.sort(resultList, (o1, o2) -> o1.date.compareTo(o2.date));
        return resultList;
    }

    private List<ReviewEntry> fetchEntriesInCountry(int appId, AppStoreCC appStoreCC) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {

        final Document doc = fetchDocument(appId, appStoreCC);
        final EntryParser parser = new EntryParser();
        final NodeList nodeList = parser.parseEntries(doc);

        logger.info("Found " + nodeList.getLength() + " entries for app " + appId + " in " + appStoreCC);
        final List<ReviewEntry> resultList = new ArrayList<>(nodeList.getLength());
        /* Skip first element as it is not a user review.*/
        for (int i = 1; i < nodeList.getLength(); i++) {
            try {
                resultList.add(parser.parseEntry(nodeList.item(i), appStoreCC));
            } catch (ParseException | XPathExpressionException e) {
                logger.error("Could not parse element at index " + i + " because of: " + e, e);
            }
        }

        return resultList;
    }

    private Document fetchDocument(int appId, AppStoreCC appStoreCC) throws ParserConfigurationException, SAXException, IOException {
        final URL url = buildUrl(appId, appStoreCC);

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(url.openStream());
    }

    private URL buildUrl(int appId, AppStoreCC appStoreCC) throws MalformedURLException {
        return new URL("https://itunes.apple.com/" + appStoreCC.getCC()
                + "/rss/customerreviews/id=" + appId + "/sortBy=mostRecent/xml?l=en");
    }

}
