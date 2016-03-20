package de.skerkewitz.appreview.service;

import org.w3c.dom.Node;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Class to parse a single review entry.
 *
 * @author skerkewitz, 2016-03-16
 */
public class EntryParser {

    final XPathFactory xPathfactory = XPathFactory.newInstance();
    final XPathExpression exprId;
    final XPathExpression exprTitle;
    final XPathExpression exprAuthor;
    final XPathExpression exprRating;
    final XPathExpression exprText;

    public EntryParser() throws XPathExpressionException {
        exprId = xPathfactory.newXPath().compile("./id");
        exprTitle = xPathfactory.newXPath().compile("./title");
        exprAuthor = xPathfactory.newXPath().compile("./author/name");
        exprRating = xPathfactory.newXPath().compile("./rating");
        exprText = xPathfactory.newXPath().compile("./content[@type='text']");
    }

    public ReviewEntry parseEntry(Node node, AppStoreCC appStoreCC) throws XPathExpressionException {
//            public final Date date;
        Double id = (Double) exprId.evaluate(node, XPathConstants.NUMBER);
        String title = (String) exprTitle.evaluate(node, XPathConstants.STRING);
        String name = (String) exprAuthor.evaluate(node, XPathConstants.STRING);
        Double rating = (Double) exprRating.evaluate(node, XPathConstants.NUMBER);
        String text = (String) exprText.evaluate(node, XPathConstants.STRING);
        return new ReviewEntry(id.intValue(), appStoreCC, null, name, rating.intValue(), null, title, text);
    }
}
