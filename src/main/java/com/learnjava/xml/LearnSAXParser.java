package com.learnjava.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;

/**
 *
 * @author vikmalik
 */
public class LearnSAXParser {
    public static void main(String[] args) {
        
        XMLReader parser = new SAXParser();
        try {
            InputStream inputStream = LearnSAXParser.class.getClassLoader()
                    .getResourceAsStream("com/learnjava/java/xml/XML101.xml");

            parser.setContentHandler(new MyContentHandler());

            parser.parse(new InputSource(inputStream));
            
            System.out.println("XML parsed Successuflly");
        } catch (IOException | SAXException ex) {
            Logger.getLogger(LearnSAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static class MyContentHandler implements ContentHandler{

        @Override
        public void setDocumentLocator(Locator locator) {
            System.out.println("setDocumentLocator(): called");
        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println("startDocument(): called");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("endDocument(): called");
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            System.out.println("startPrefixMapping(): called");
        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
            System.out.println("endPrefixMapping(): called");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            System.out.printf("startElement(): called :: uri=%s, localName=%s, qName=%s \n", uri, localName, qName );
            
            
            
            for (int i = 0; i < atts.getLength(); i++) {
                String attributeName = atts.getLocalName(i);
                String attributeValue = atts.getValue(i);
                System.out.printf("\t Attribute: %s = %s \n", attributeName, attributeValue);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            System.out.printf("endElement(): called :: localName = %s \n", localName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch, start, length);
            if(str.trim().length() > 0){
                System.out.printf("\t\tcharacters(): called :: %s\n", str);
            }
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
            System.out.println("ignorableWhitespace(): called");
        }

        @Override
        public void processingInstruction(String target, String data) throws SAXException {
            System.out.println("processingInstruction(): called");
        }

        @Override
        public void skippedEntity(String name) throws SAXException {
            System.out.println("skippedEntity(): called");
        }
        
    }
}