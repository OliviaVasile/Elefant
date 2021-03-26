package reports;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.util.List;
import java.util.Map;

public class CustomTestReporter implements IReporter {
    PrintWriter reportWriter = null;

    @Override
    public void generateReport(List<XmlSuite> xml, List<ISuite> suites, String outDir) {
        //      open file to write the results
        File targetFile = new File("test-results/MyHTMLTestReport.html");

        try {
            reportWriter = new PrintWriter(new BufferedWriter(new FileWriter(targetFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        reportWriter.println("<!DOCTYPE html>");
        reportWriter.println("<html><head><title>Test Run Report</title></head>");
        reportWriter.println("<body>");
        reportWriter.println("<h2>This is my custom test report</h2>");

        writeResults(suites);

        reportWriter.println("</body>");
        reportWriter.println("</html>");
        reportWriter.flush();
        reportWriter.close();
    }

    public void writeResults(List<ISuite> suites) {
        int passed = 0, failed = 0, skipped = 0, total = 0;
        String testName = null;

        /* Iterate over each suite */
        for (ISuite suite : suites) {
            /* Get all results from the current suite */
            Map<String, ISuiteResult> suiteResults = suite.getResults();

            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();

                /* Get all passed test scenarios */
                reportWriter.println("<h3>This are passed test cases:</h3> <ul style=\"list-style-type:disc\">");
                for (ITestResult r : tc.getPassedTests().getAllResults()) {
                    System.out.println(r.getName());
                    reportWriter.println("<li>" + r.getName() + "</li>");
                    passed++;

                }

                /* Get all failed test scenarios */
                reportWriter.println("</ul><h3>This are failed test cases:</h3> <ul style=\"list-style-type:disc\">");
                if (tc.getFailedTests().getAllResults().size() > 0)
                    for (ITestResult r : tc.getFailedTests().getAllResults()) {
                        reportWriter.println("<li>" + r.getName() + "</li>");
                        failed++;
                    }
                else
                    reportWriter.println("<li>None</li>");

                /* Get all skipped test scenarios */
                reportWriter.println("</ul><h3>This are skipped test cases:</h3><ul style=\"list-style-type:disc\">");
                if (tc.getSkippedTests().getAllResults().size() > 0)
                    for (ITestResult r : tc.getSkippedTests().getAllResults()) {
                        reportWriter.println("<p>" + r.getName() + "</p>");
                        skipped++;
                    }
                else
                    reportWriter.println("<li>None</li>");

            }
        }
        total = passed + failed + skipped;
        reportWriter.println("</ul><h3>Total tests:" + total + "</h3>");
        reportWriter.println("<h3>Passed tests:" + passed + "</h3>");
        reportWriter.println("<h3>Failed tests:" + failed + "</h3>");
        reportWriter.println("<h3>Skipped tests:" + skipped + "</h3>");
    }
}

