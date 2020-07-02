package object;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lero on 2020/7/2.
 */
public class JDomOutput {
    public static void main(String[] args) throws IOException {

        Document document = new Document();
        Element peole = new Element("people");
        document.addContent(peole);

        //Comment rootComment = new Comment("将数据从程序输出到XML中");
        //peole.addContent(rootComment);

        Element person1 = new Element("person");
        peole.addContent(person1);
        //person1.setAttribute("id","001");

        //Attribute person1_gender = new Attribute("gender","male");
        //person1.setAttribute(person1_gender);

        Element person1_name = new Element("name");
        person1_name.setText("刘德华");
        person1.addContent(person1_name);

        Element person1_address = new Element("address");
        person1_address.setText("香港");
        person1.addContent(person1_address);

        Element person2 = new Element("person");
        peole.addContent(person2);
        //person2.setAttribute("id","002");

        //Attribute person2_gender = new Attribute("gender","male");
       // person2.setAttribute(person2_gender);

        Element person2_name = new Element("name");
        person2_name.setText("林志颖22");
        person2.addContent(person2_name);

        Element person2_address = new Element("address");
        person2_address.setText("台湾");
        person2.addContent(person2_address);

        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");
        format.setIndent("    ");

        XMLOutputter out = new XMLOutputter(format);
        out.output(document, new FileOutputStream("jdom.xml"));

    }
}
