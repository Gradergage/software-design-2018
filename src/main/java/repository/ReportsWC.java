package repository;

import model.ReportWC;

import java.util.ArrayList;
import java.util.List;

public class ReportsWC {
    private static ReportsWC instance;
    private List<ReportWC> reports;

    private ReportsWC() {
        reports = new ArrayList<>();
    }

    public static synchronized ReportsWC getInstance() {
        if (instance == null)
            instance = new ReportsWC();
        return instance;
    }

    public ReportWC getReportById(long id) {
        for (ReportWC n : reports) {
            if (n.getId() == id)
                return n;
        }
        return null;
    }

    public boolean addReport(ReportWC report) {
        if (report != null) {
            report.setId(reports.size());
            reports.add(report);
            return true;
        } else
            return false;
    }

    public boolean updateReport(long id, ReportWC newReport) {
        ReportWC current = getReportById(id);
        if (current != null) {
            current = newReport;
            current.setId(id);
            return true;
        } else return false;

    }

    private boolean seekReport(long id) {
        return getReportById(id) != null;
    }
}
