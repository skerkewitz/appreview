package de.skerkewitz.appreview.service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

/**
 * Created by tropper on 19/03/16.
 */
public interface ReviewService {

    List<ReviewEntry> fetchEntries(int appId) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException;
}
