package repository;

import model.PaymentDocument;

import java.util.ArrayList;
import java.util.List;

public class PaymentDocuments {
    private static PaymentDocuments instance;
    private List<PaymentDocument> documents;

    private PaymentDocuments() {
        documents = new ArrayList<>();
    }

    public static synchronized PaymentDocuments getInstance() {
        if (instance == null)
            instance = new PaymentDocuments();
        return instance;
    }

    public PaymentDocument getDocumentById(long id) {
        for (PaymentDocument n : documents) {
            if (n.getId() == id)
                return n;
        }
        return null;
    }

    public boolean addDocument(PaymentDocument document) {
        if (document != null) {
            document.setId(documents.size());
            documents.add(document);
            return true;
        } else
            return false;
    }

    public boolean updateDocument(long id, PaymentDocument newDocument) {
        PaymentDocument current = getDocumentById(id);
        if (current != null) {
            current = newDocument;
            current.setId(id);
            return true;
        } else return false;

    }

    private boolean seekDocument(long id) {
        return getDocumentById(id) != null;
    }
}
