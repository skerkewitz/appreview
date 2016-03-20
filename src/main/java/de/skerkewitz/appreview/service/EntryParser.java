package de.skerkewitz.appreview.service;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class to parse a single review entry.
 *
 * @author skerkewitz, 2016-03-16
 */
public class EntryParser {

    private final XPathFactory xPathfactory = XPathFactory.newInstance();
    private final XPathExpression exprId;
    private final XPathExpression exprTitle;
    private final XPathExpression exprAuthor;
    private final XPathExpression exprRating;
    private final XPathExpression exprText;
    private final XPathExpression exprDate;
    private final XPathExpression exprVersion;
    private final XPathExpression exprEntries;

    public EntryParser() throws XPathExpressionException {
        exprEntries = xPathfactory.newXPath().compile("//entry");

        exprId = xPathfactory.newXPath().compile("./id");
        exprDate = xPathfactory.newXPath().compile("./updated");
        exprVersion = xPathfactory.newXPath().compile("./version");
        exprTitle = xPathfactory.newXPath().compile("./title");
        exprAuthor = xPathfactory.newXPath().compile("./author/name");
        exprRating = xPathfactory.newXPath().compile("./rating");
        exprText = xPathfactory.newXPath().compile("./content[@type='text']");
    }

    public ReviewEntry parseEntry(Node node, AppStoreCC appStoreCC) throws XPathExpressionException, ParseException {

        final Double id = (Double) exprId.evaluate(node, XPathConstants.NUMBER);
        final String date = (String) exprDate.evaluate(node, XPathConstants.STRING);
        final String version = (String) exprVersion.evaluate(node, XPathConstants.STRING);
        final String title = (String) exprTitle.evaluate(node, XPathConstants.STRING);
        final String name = (String) exprAuthor.evaluate(node, XPathConstants.STRING);
        final Double rating = (Double) exprRating.evaluate(node, XPathConstants.NUMBER);
        final String text = (String) exprText.evaluate(node, XPathConstants.STRING);

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return new ReviewEntry(id.intValue(), appStoreCC, sdf.parse(date), name, rating.intValue(), version, title, text);
    }

    public NodeList parseEntries(Object doc) throws XPathExpressionException {
            return (NodeList) exprEntries.evaluate(doc, XPathConstants.NODESET);
    }
}
