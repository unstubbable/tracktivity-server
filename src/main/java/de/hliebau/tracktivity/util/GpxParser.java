package de.hliebau.tracktivity.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.hliebau.tracktivity.domain.Activity;
import de.hliebau.tracktivity.domain.Track;

@Service
public class GpxParser {

	private SAXParser saxParser;

	public GpxParser() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	public Activity createActivity(File gpxFile) {
		InputSource input = new InputSource(gpxFile.toURI().toASCIIString());
		return createActivity(input);
	}

	public Activity createActivity(InputSource is) {
		if (saxParser == null) {
			return null;
		}
		Activity activity = new Activity(new Track());
		try {
			saxParser.parse(is, new GpxHandler(activity));
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return activity;
	}

	public Activity createActivity(InputStream in) {
		InputSource input = new InputSource(in);
		return createActivity(input);
	}

}
