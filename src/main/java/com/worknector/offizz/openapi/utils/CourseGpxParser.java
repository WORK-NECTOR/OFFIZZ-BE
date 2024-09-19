package com.worknector.offizz.openapi.utils;

import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CourseGpxParser {

    public void parseGpxAndUpdate(Course course) throws Exception {
        String gpxUrl = course.getGpxpath();

        Document document = downloadAndParseGpx(gpxUrl);

        NodeList trkptNodes = document.getElementsByTagName("trkpt");
        if (trkptNodes.getLength() > 0) {
            Node trkptNode = trkptNodes.item(0);
            String lat = trkptNode.getAttributes().getNamedItem("lat").getNodeValue();
            String lon = trkptNode.getAttributes().getNamedItem("lon").getNodeValue();

            course.updateLatitude(Double.parseDouble(lat));
            course.updateLongitude(Double.parseDouble(lon));
        }
    }

    private Document downloadAndParseGpx(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        String xmlContent = content.toString();
        xmlContent = removeBom(xmlContent);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));
    }

    private String removeBom(String xmlContent) {
        if (xmlContent.startsWith("\uFEFF")) {
            return xmlContent.substring(1);
        }
        return xmlContent;
    }

    public void parseGpxAndUpdateAll(List<Course> courses) throws Exception {
        for (Course course : courses) {
            parseGpxAndUpdate(course);
        }
    }
}
