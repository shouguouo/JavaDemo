package com.swj.io;

import com.google.common.collect.Lists;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfDocumentBase;
import com.spire.xls.Workbook;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shouguouo~
 * @date 2021/2/5 - 21:51
 */
public class ConcatPdf {
    public static void main(String[] args) throws FileNotFoundException {
        String folder = "E:\\Downloads\\0205结汇\\结汇";
        String outputFolder = "C:\\Users\\shouwj25112\\Desktop\\0205结汇";
        File file = new File(folder);
        if (file.exists() && file.isDirectory()) {
            File[] subFolderList = file.listFiles();
            if (subFolderList == null) {
                return;
            }
            for (File subFolder : subFolderList) {
                String output = outputFolder + File.separator + String.format("KUKA(HK) TRADE CO.,LTD  %s.pdf", subFolder.getName());
                // 转换excel为pdf
                changeExcelToPdf(subFolder);
                // 合并pdf
                mergePdf(subFolder, output);
            }
        }
    }

    private static void changeExcelToPdf(File folder) throws FileNotFoundException {
        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }
        List<File> fileList = Lists.newArrayList(files);
        List<File> excelList = fileList.stream().filter(x -> StringUtils.endsWithIgnoreCase(x.getName(), ".xlsx") || StringUtils.endsWithIgnoreCase(x.getName(), ".xls")).collect(Collectors.toList());
        for (File excelFile : excelList) {
            toPdf(excelFile);
        }
    }

    private static void mergePdf(File folder, String output) throws FileNotFoundException {
        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }
        List<File> fileList = Lists.newArrayList(files);
        fileList = fileList.stream().filter(x -> StringUtils.endsWithIgnoreCase(x.getName(), ".pdf")).sorted(Comparator.comparing(File::getName)).collect(Collectors.toList());
        if (fileList.size() == 0) {
            return;
        }
        mergePdf(fileList, output);
    }

    private static void toPdf(File excel) throws FileNotFoundException {
        String pdfPath = excel.getAbsolutePath().replace(".xlsx", ".pdf").replace(".xls", ".pdf");
        Workbook wb = new Workbook();
        wb.loadFromStream(new FileInputStream(excel));
        wb.saveToFile(pdfPath, com.spire.xls.FileFormat.PDF);
    }

    private static void mergePdf(List<File> files, String output) throws FileNotFoundException {
        InputStream[] streams = new FileInputStream[files.size()];
        for (int i = 0; i < files.size(); i++) {
            streams[i] = new FileInputStream(files.get(i));
        }
        PdfDocumentBase doc = null;
        try {
            doc = PdfDocument.mergeFiles(streams);
            doc.save(output, FileFormat.PDF);
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
    }
}
