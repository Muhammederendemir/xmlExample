import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test {

    public static void main(String[] args) {

        Document document = null;
        try {
            //XML dosyasının yolunu gösterdim.
            String path = "C:\\Users\\Win10\\Desktop\\github\\XML-Example\\src\\main\\resources\\xmlExample.xml";
            document = getDocument(path);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex);
        }

        Element lessons = document.getDocumentElement();
        // Burada "lessonları" ekrana yazdırılıyor.
        // getNodeName elementin adını almak için kullanulır
        System.out.println("Element Değeri : " + lessons.getNodeName());
        //Şimdi Root element olan şeyin bir attribu değerini yazdıralım.

        String title= getSingleChildText(lessons,"title");
        //String title = lessons.getElementsByTagName("title").item(0).getTextContent();
        System.out.println("Baslık : " + title);
        System.out.println("- - - - - - - - - - - - - - - -");



        NodeList teacherList = lessons.getElementsByTagName("teacher");
        for (int i = 0; i < teacherList.getLength(); i++) {
            Element teacher = (Element) teacherList.item(i);
            String artistNumber= teacher.getAttribute("number");//ozellik adı giriyorum ve ögretmen numarasını alıyorum
            String artistName = getSingleChildText(teacher, "name");//ogretmen ısımlerını alıyorum

            System.out.print(artistNumber+  " --> " + artistName + "\n");//numara ve ogretmenın ısmını yazıyorum ve alt satıra gecıyorum

            NodeList lessonList= teacher.getElementsByTagName("lesson");//dersleri liste cekiyorum

            for (int j = 0; j < lessonList.getLength(); j++) {
                Element option = (Element) lessonList.item(j);
                String lessonNo=option.getAttribute("lessonNo");
                String lesson=option.getTextContent();
                System.out.println("Lesson No: " + lessonNo+ " -" + lesson);
                //ders numarsı ve ısımlerı yazılıyor
            }
            System.out.println("- - - - - - - - - - - - - - - - -");
        }

    }

    public static String getSingleChildText(Element element, String tagName) {
        //Bu metod ile XML tagının içinde sadece bir kez geçen
        //elementlerin değerini alıyoruz.
        String body = element.getElementsByTagName(tagName).item(0).getTextContent();
        return body;
    }

    public static Document getDocument(String uri) throws ParserConfigurationException, SAXException, IOException {
        //Xml dosyasının dosya konumu(path)ı parametre olarak gönderilip Xml doyası document olarak donduruluyor.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(uri);
        return document;
    }

}
