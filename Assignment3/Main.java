package com.shivam;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.xmlbeans.xml.stream.events.ElementTypeNames;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    static HashSet<String> set = new HashSet();
    static ArrayList<String> list = new ArrayList<String>();

    public static void simpleCrawlerUtil(String url, int depth, XSSFWorkbook allURLs, XSSFWorkbook allPTags, XSSFSheet urlSheet, XSSFSheet pSheet, int rowid1, int rowid2) {
        // Base Case
        if(depth == 50) {
            return;
        }
        System.out.println(url);

        // Create first row for each url in URls file
        XSSFRow row = urlSheet.createRow(rowid1++);
        Cell cell = row.createCell(0);
        cell.setCellValue((String) "For Url: " + (depth + 1));
        cell = row.createCell(1);
        cell.setCellValue((String) url);

        Elements paragraphs = new Elements();
        Elements links = new Elements();

        // get data
        try {
            set.add(url);
            Document doc = Jsoup.connect(url).get();
            Element body = doc.body();
            paragraphs = body.getElementsByTag("p");
            links = doc.select("a[href]");
        } catch (Exception e) {
            System.out.println("Something Went Wrong in the url: " + url);
        }

        // parse data to write to urls file
        for (Element link: links) {

            if(link.text().isEmpty() || link.absUrl("href").isEmpty()) {
                continue;
            }
            list.add(link.absUrl("href"));
            row = urlSheet.createRow(rowid1++);
            cell = row.createCell(0);
            cell.setCellValue((String) link.text());
            cell = row.createCell(1);
            cell.setCellValue((String) link.absUrl("href"));
        }

        // initialise next url
        String nexturl = "";
        for(String link: list) {
            if( nexturl.isEmpty() 
                && !set.contains(link) 
                && !link.contains("#")
                && !link.equals("https://alumni.pec.ac.in/") 
                && !link.equals("http://alumni.pec.ac.in/")
                && !link.equals(url) 
                && (link.contains("https://pec.ac.in") || link.contains("http://pec.ac.in"))) {
                nexturl = link;
            }
        }

        // add space after each url processed
        row = urlSheet.createRow(rowid1++);
        cell = row.createCell(0);
        cell.setCellValue((String) " ");
        cell = row.createCell(1);
        cell.setCellValue((String) " ");

        // save in urls file
        try (FileOutputStream outputStream = new FileOutputStream("./allURLs.xlsx")) {
            allURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: allURLs");
        }

        // Create first row for each url in PTags file
        row = pSheet.createRow(rowid2++);
        cell = row.createCell(0);
        cell.setCellValue((String) "For Url: " + (depth + 1));
        cell = row.createCell(1);
        cell.setCellValue((String) url);

        for(Element paragraph: paragraphs) {
            if(paragraph.text().length() > 0) {
                row = pSheet.createRow(rowid2++);
                cell = row.createCell(0);
                cell.setCellValue((String) "p");
                cell = row.createCell(1);
                cell.setCellValue((String) paragraph.text());
            }
        }
        row = pSheet.createRow(rowid2++);
        cell = row.createCell(0);
        cell.setCellValue((String) " ");
        cell = row.createCell(1);
        cell.setCellValue((String) " ");

        // save in ptags file
        try (FileOutputStream outputStream = new FileOutputStream("./allPTags.xlsx")) {
            allPTags.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: allPTags");
        }

        // call on nexturl
        if(!nexturl.isEmpty()) {
            simpleCrawlerUtil(nexturl, depth+1, allURLs, allPTags, urlSheet, pSheet, rowid1, rowid2);
        }
    }

    public static void simpleCrawler() {
        // Input URL
        System.out.print("Enter Website Url: ");
        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();

        // Create Two excel files -> allURls and allPTags ans respective sheets
        XSSFWorkbook allURLs = new XSSFWorkbook();
        XSSFSheet urlSheet = allURLs.createSheet("sheet");
        XSSFWorkbook allPTags = new XSSFWorkbook();
        XSSFSheet pSheet = allPTags.createSheet("sheet");

        // row track number
        int rowid1 = 0;
        int rowid2 = 0;

        // Create first row of Url File "TEXT:URL"
        XSSFRow row = urlSheet.createRow(rowid1++);
        Cell cell = row.createCell(0);
        cell.setCellValue((String) "Text");
        cell = row.createCell(1);
        cell.setCellValue((String) "URL");

        // Write to first file
        try (FileOutputStream outputStream = new FileOutputStream("./allURLs.xlsx")) {
            allURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: allURLs");
        }

        // Create first row of Url File "Tag: Content"
        row = pSheet.createRow(rowid2++);
        cell = row.createCell(0);
        cell.setCellValue((String) "Tag");
        cell = row.createCell(1);
        cell.setCellValue((String) "Content");

        // Write to second file
        try (FileOutputStream outputStream = new FileOutputStream("./allPTags.xlsx")) {
            allPTags.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: allPTags");
        }

        // Call Recursive Util Function
        simpleCrawlerUtil(url, 0, allURLs, allPTags, urlSheet, pSheet, rowid1, rowid2);
    }

    public static void focusedCrawlerUtil(String url, int depth, XSSFWorkbook allURLs, XSSFWorkbook allPTags, XSSFSheet urlSheet, XSSFSheet pSheet, int rowid1, int rowid2, int count) {
        // Base Case
        if(depth == 600) {
            return;
        }
        System.out.println(url);

        Elements paragraphs = new Elements();
        Elements links = new Elements();

        // get data
        try {
            set.add(url);
            Document doc = Jsoup.connect(url).get();
            Element body = doc.body();
            paragraphs = body.getElementsByTag("p");
            links = doc.select("a[href]");
        } catch (Exception e) {
            System.out.println("Something Went Wrong in the url: " + url);
        }

        // parse data to write to urls file
        for(Element link: links) {
            list.add(link.absUrl("href"));
        }
        if(url.contains("/faculty/")) {

            // Create first row for each url in URls file
            XSSFRow row = urlSheet.createRow(rowid1++);
            Cell cell = row.createCell(0);
            cell.setCellValue((String) "For Url: " + (count));
            cell = row.createCell(1);
            cell.setCellValue((String) url);
            for (Element link : links) {

                if (link.text().isEmpty() || link.absUrl("href").isEmpty()) {
                    continue;
                }
                row = urlSheet.createRow(rowid1++);
                cell = row.createCell(0);
                cell.setCellValue((String) link.text());
                cell = row.createCell(1);
                cell.setCellValue((String) link.absUrl("href"));
            }
            // add space after each url processed
            row = urlSheet.createRow(rowid1++);
            cell = row.createCell(0);
            cell.setCellValue((String) " ");
            cell = row.createCell(1);
            cell.setCellValue((String) " ");
        }

        // initialise next url
        String nexturl = "";
        for(String link: list) {
            if( nexturl.isEmpty() 
                && !set.contains(link) 
                && !link.contains("#")
                && !link.equals("https://alumni.pec.ac.in/") 
                && !link.equals("http://alumni.pec.ac.in/")
                && !link.equals(url) 
                && (link.contains("https://pec.ac.in") || link.contains("http://pec.ac.in"))
                && !link.toLowerCase().contains(".docx") 
                && !link.toLowerCase().contains(".pdf") 
                && !link.toLowerCase().contains(".jpg")
                && !link.toLowerCase().contains(".doc") 
                && !link.toLowerCase().contains(".xlsx")) {
                nexturl = link;
            }
        }

        // save in urls file
        try (FileOutputStream outputStream = new FileOutputStream("./fURLs.xlsx")) {
            allURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: fURLs");
        }

        // Create first row for each url in PTags file
        if(url.contains("/faculty/")) {
            XSSFRow row = pSheet.createRow(rowid2++);
            Cell cell = row.createCell(0);
            cell.setCellValue((String) "For Url: " + (count));
            cell = row.createCell(1);
            count++;
            cell.setCellValue((String) url);
            for(Element paragraph: paragraphs) {
                if(paragraph.text().length() > 0) {
                    row = pSheet.createRow(rowid2++);
                    cell = row.createCell(0);
                    cell.setCellValue((String) "p");
                    cell = row.createCell(1);
                    cell.setCellValue((String) paragraph.text());
                }
            }
            row = pSheet.createRow(rowid2++);
            cell = row.createCell(0);
            cell.setCellValue((String) " ");
            cell = row.createCell(1);
            cell.setCellValue((String) " ");
        }

        // save in ptags file
        try (FileOutputStream outputStream = new FileOutputStream("./fPTags.xlsx")) {
            allPTags.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: fPTags");
        }

        // call on nexturl
        if(!nexturl.isEmpty()) {
            focusedCrawlerUtil(nexturl, depth+1, allURLs, allPTags, urlSheet, pSheet, rowid1, rowid2, count);
        }
    }

    public static void focusedCrawler() {
        // Input URL
        System.out.print("Enter Website Url: ");
        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();

        // Create Two excel files -> allURls and allPTags ans respective sheets
        XSSFWorkbook fURLs = new XSSFWorkbook();
        XSSFSheet urlSheet = fURLs.createSheet("sheet");
        XSSFWorkbook fPTags = new XSSFWorkbook();
        XSSFSheet pSheet = fPTags.createSheet("sheet");

        // row track number
        int rowid1 = 0;
        int rowid2 = 0;

        // Create first row of Url File "TEXT:URL"
        XSSFRow row = urlSheet.createRow(rowid1++);
        Cell cell = row.createCell(0);
        cell.setCellValue((String) "Text");
        cell = row.createCell(1);
        cell.setCellValue((String) "URL");

        // Write to first file
        try (FileOutputStream outputStream = new FileOutputStream("./fURLs.xlsx")) {
            fURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: fURLs");
        }

        // Create first row of Url File "Tag: Content"
        row = pSheet.createRow(rowid2++);
        cell = row.createCell(0);
        cell.setCellValue((String) "Tag");
        cell = row.createCell(1);
        cell.setCellValue((String) "Content");

        // Write to second file
        try (FileOutputStream outputStream = new FileOutputStream("./fPTags.xlsx")) {
            fPTags.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: fPTags");
        }

        // Call Recursive Util Function
        focusedCrawlerUtil(url, 0, fURLs, fPTags, urlSheet, pSheet, rowid1, rowid2, 1);
    }

    public static void downloadableCrawlerUtil(String url, int depth, XSSFWorkbook downloadableURLs, XSSFSheet urlSheet, int rowid) {

        // base case of 600 urls traversed
        if(depth == 600) {
            return;
        }

        System.out.println(url);
        Elements links = new Elements();

        try {

            set.add(url);
            Document doc = Jsoup.connect(url).get();
            Element body = doc.body();
            links = doc.select("a[href]");

        } catch (Exception e) {
            System.out.println("Something Went Wrong in the url: " + url);
        }

        for(Element link: links) {

            // add it to the list
            list.add(link.absUrl("href"));

            // if downloadable, write to file
            if( link.absUrl("href").toLowerCase().contains(".pdf")  ||
                    link.absUrl("href").toLowerCase().contains(".docx") ||
                    link.absUrl("href").toLowerCase().contains(".xlsx") ||
                    link.absUrl("href").toLowerCase().contains(".doc")  ||
                    link.absUrl("href").toLowerCase().contains(".jpeg") ||
                    link.absUrl("href").toLowerCase().contains(".jpg")) {

                XSSFRow row = urlSheet.createRow(rowid++);
                Cell cell = row.createCell(0);
                cell.setCellValue((String) link.absUrl("href"));
            }

        }

        String nexturl = "";
        for(String link: list) {
            if( nexturl.isEmpty()
                    && !set.contains(link)
                    && !link.contains("#")
                    && !link.equals("https://alumni.pec.ac.in/")
                    && !link.equals("http://alumni.pec.ac.in/")
                    && !link.equals(url)
                    && (link.contains("https://pec.ac.in") || link.contains("http://pec.ac.in"))
                    && !link.toLowerCase().contains(".docx")
                    && !link.toLowerCase().contains(".pdf")
                    && !link.toLowerCase().contains(".jpg")
                    && !link.toLowerCase().contains(".doc")
                    && !link.toLowerCase().contains(".xlsx")
                    && !link.toLowerCase().contains(".jpg")) {
                nexturl = link;
                break;
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("./downloadableURLs.xlsx")) {
            downloadableURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: downloadableURLs");
        }

        // call on nexturl
        if(!nexturl.isEmpty()) {
            downloadableCrawlerUtil(nexturl, depth+1, downloadableURLs, urlSheet, rowid);
        }
    }

    public static void downloadableCrawler() {

        // Input URL
        System.out.print("Enter Website Url: ");
        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();

        // Create File --> downloadableURLs
        XSSFWorkbook downloadableURLs = new XSSFWorkbook();

        // Create sheet in above file
        XSSFSheet urlSheet = downloadableURLs.createSheet("sheet");

        // row track number in above file
        int rowid = 0;

        // Initialise top row to "PDFLINKS"
        XSSFRow row = urlSheet.createRow(rowid++);
        Cell cell = row.createCell(0);
        cell.setCellValue((String) "PDFLINKS");

        // Write in above file
        try (FileOutputStream outputStream = new FileOutputStream("./downloadableURLs.xlsx")) {
            downloadableURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: downloadableURLs");
        }

        // call utility function
        downloadableCrawlerUtil(url, 0, downloadableURLs, urlSheet, rowid);
    }

    public static void main(String[] args) {

        while(true) {

            System.out.println("MENU\n1. Simple Crawler\n2. Faculty Focused Crawler\n3. Downloadable Files Crawler");
            System.out.print("Enter Option: ");
            Scanner sc = new Scanner(System.in);
            int op = Integer.parseInt(sc.nextLine());

            if(op == 1) simpleCrawler();

            else if(op == 2) focusedCrawler();

            else if(op == 3) downloadableCrawler();

            else break;
        }
        System.out.print("All Processes Completed");
    }
}