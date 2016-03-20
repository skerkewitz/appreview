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
import javax.xml.xpath.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tropper on 19/03/16.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Log logger = LogFactory.getLog(ReviewServiceImpl.class);

    @Override
    public List<ReviewEntry> fetchEntries(int appId) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
        /* Fetch the entries for each country and merge the result into one list. */
        List<ReviewEntry> resultList = new ArrayList<>();
        for (AppStoreCC appStoreCC : AppStoreCC.values()) {
            resultList.addAll(fetchEntriesInCountry(appId, appStoreCC));
        }

        return resultList;
    }

    public List<ReviewEntry> fetchEntriesInCountry(int appId, AppStoreCC appStoreCC) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
        return fetchDocument(appId, appStoreCC);
    }

    private URL buildUrl(int appId, AppStoreCC appStoreCC) throws MalformedURLException {
        return new URL("https://itunes.apple.com/" + appStoreCC.getCC() + "/rss/customerreviews/id=" + appId + "/sortBy=mostRecent/xml?l=en");
    }

    private List<ReviewEntry> fetchDocument(int appId, AppStoreCC appStoreCC) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        final URL url = buildUrl(appId, appStoreCC);

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.parse(url.openStream());

        final XPathFactory xPathfactory = XPathFactory.newInstance();
        final XPathExpression exprEntries = xPathfactory.newXPath().compile("//entry");
        final NodeList nl = (NodeList) exprEntries.evaluate(doc, XPathConstants.NODESET);

        final EntryParser parser = new EntryParser();

        logger.info("Found " + nl.getLength() + " entries for app " + appId + " in " + appStoreCC);
        final List<ReviewEntry> resultList = new ArrayList<>(nl.getLength());
        for (int i = 1; i < nl.getLength(); i++) {

            try {
                resultList.add(parser.parseEntry(nl.item(i), appStoreCC));
            } catch (XPathExpressionException e) {
                logger.error("Could not parse element at index " + i + " because of: " + e, e);
                e.printStackTrace();
            }
        }

        return resultList;
    }
}
