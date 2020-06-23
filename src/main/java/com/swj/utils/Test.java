package com.swj.utils;

import com.aspose.words.Document;
import com.aspose.words.License;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author wink~
 * @date 2020/4/1 - 15:13
 */
public class Test {
    public static void main(String[] args) {
        String licenseStr = "<License>\n" +
            "  <Data>\n" +
            "    <Products>\n" +
            "      <Product>Aspose.Total for Java</Product>\n" +
            "      <Product>Aspose.Words for Java</Product>\n" +
            "    </Products>\n" +
            "    <EditionType>Enterprise</EditionType>\n" +
            "    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n" +
            "    <LicenseExpiry>20991231</LicenseExpiry>\n" +
            "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
            "  </Data>\n" +
            "  <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n" +
            "</License>";
        try {
            InputStream license = new ByteArrayInputStream(licenseStr.getBytes("UTF-8"));
            License asposeLic = new License();
            asposeLic.setLicense(license);
            Document document = new Document("/root/test/CustomToc.docx");
            document.updateFields();
            document.save("/root/test/target/CustomToc.docx");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
