package lk.ijse.pharmacymanagementsystem.controller.components.product;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pharmacymanagementsystem.dto.item.*;
import lk.ijse.pharmacymanagementsystem.model.BatchModel;
import lk.ijse.pharmacymanagementsystem.model.BillModel;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

public class OutOfStockController implements Initializable {
    @FXML
    private TableColumn<OutOfStockTM, Integer> avaQTY_Col;
    @FXML
    private TableColumn<OutOfStockTM, Integer> batchNo_Col;
    @FXML
    private TableColumn<OutOfStockTM, String> desc_Col;
    @FXML
    private TableColumn<OutOfStockTM, Integer> itemCode_Col;
    @FXML
    private TableColumn<OutOfStockTM, String> supplier_Col;
    @FXML
    private TableView<OutOfStockTM> outOfStock_tbl;

    private final ObservableList<OutOfStockTM> outOfStockList = FXCollections.observableArrayList();
    private final BatchModel batchModel = new BatchModel();
    private final ItemModel itemModel = new ItemModel();
    private final BillModel billModel = new BillModel();

    // Meta WhatsApp Business API Configuration
    private static final String WHATSAPP_API_URL = "https://graph.facebook.com/v21.0/YOUR_PHONE_NUMBER_ID/messages";
    private static final String ACCESS_TOKEN = "YOUR_PERMANENT_ACCESS_TOKEN";
    private static final String RECIPIENT_PHONE = "94777883956"; // Without + or whatsapp: prefix

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemCode_Col.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        desc_Col.setCellValueFactory(new PropertyValueFactory<>("description"));
        batchNo_Col.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        avaQTY_Col.setCellValueFactory(new PropertyValueFactory<>("availableQty"));
        supplier_Col.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        loadTable();
    }

    private void loadTable() {
        try {
            outOfStockList.clear();
            ArrayList<BatchDTO> outOfStockBatches = batchModel.getOutOfStocks();

            if (outOfStockBatches == null || outOfStockBatches.isEmpty()) {
                outOfStock_tbl.getItems().clear();
                outOfStock_tbl.setPlaceholder(new Label("All items are in stock"));
                return;
            }

            for (BatchDTO batchDTO : outOfStockBatches) {
                ItemDTO item = itemModel.getItem(batchDTO.getItem_code());
                BillDTO billDTO = billModel.getBillById(batchDTO.getBill_id());

                OutOfStockTM outOfStockTM = new OutOfStockTM(
                        batchDTO.getItem_code(),
                        item.getDescription(),
                        batchDTO.getBatch_number(),
                        batchDTO.getAvailable_qty(),
                        billDTO.getCompany_name()
                );
                outOfStockList.add(outOfStockTM);
            }
            outOfStock_tbl.setItems(outOfStockList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleSendMessage(ActionEvent event) {
        try {
            if (outOfStockList.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "No Data", "There are no out of stock items to send.");
                return;
            }

            // Step 1: Generate PDF
            String pdfPath = generatePDF();

            // Step 2: Upload PDF to Meta's servers
            String mediaId = uploadMediaToMeta(pdfPath);

            // Step 3: Send WhatsApp message with PDF
            sendWhatsAppWithPDF(mediaId);

            showAlert(Alert.AlertType.INFORMATION, "Success",
                    "Out of stock report sent to WhatsApp (+94 777883956) successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to send message: " + e.getMessage());
        }
    }

    private String generatePDF() throws DocumentException, IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String pdfPath = "reports/out_of_stock_" + timestamp + ".pdf";

        File directory = new File("reports");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
        document.open();

        // Title
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Out of Stock Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Date
        Font dateFont = new Font(Font.FontFamily.HELVETICA, 10);
        Paragraph date = new Paragraph("Generated: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), dateFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingAfter(20);
        document.add(date);

        // Table
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        float[] columnWidths = {1.5f, 3f, 1.5f, 1.5f, 2.5f};
        table.setWidths(columnWidths);

        // Headers
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
        String[] headers = {"Item Code", "Description", "Batch No", "Available Qty", "Supplier"};

        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(new BaseColor(52, 73, 94));
            cell.setPadding(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        // Data
        Font cellFont = new Font(Font.FontFamily.HELVETICA, 9);
        for (OutOfStockTM item : outOfStockList) {
            table.addCell(createCell(String.valueOf(item.getItemCode()), cellFont));
            table.addCell(createCell(item.getDescription(), cellFont));
            table.addCell(createCell(String.valueOf(item.getBatchNumber()), cellFont));
            table.addCell(createCell(String.valueOf(item.getAvailableQty()), cellFont));
            table.addCell(createCell(item.getSupplier(), cellFont));
        }

        document.add(table);

        // Summary
        Paragraph summary = new Paragraph("\nTotal Out of Stock Items: " + outOfStockList.size(),
                new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        summary.setSpacingBefore(20);
        document.add(summary);

        document.close();
        return pdfPath;
    }

    private PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    private String uploadMediaToMeta(String pdfPath) throws IOException {
        // Meta WhatsApp Media Upload API
        String uploadUrl = "https://graph.facebook.com/v21.0/YOUR_PHONE_NUMBER_ID/media";

        File pdfFile = new File(pdfPath);
        String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();

        HttpURLConnection conn = (HttpURLConnection) new URL(uploadUrl).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true)) {

            // Add messaging_product field
            writer.append("--").append(boundary).append("\r\n");
            writer.append("Content-Disposition: form-data; name=\"messaging_product\"\r\n\r\n");
            writer.append("whatsapp\r\n");

            // Add file field
            writer.append("--").append(boundary).append("\r\n");
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
                    .append(pdfFile.getName()).append("\"\r\n");
            writer.append("Content-Type: application/pdf\r\n\r\n");
            writer.flush();

            // Write file content
            Files.copy(pdfFile.toPath(), os);
            os.flush();

            writer.append("\r\n");
            writer.append("--").append(boundary).append("--\r\n");
            writer.flush();
        }

        // Read response
        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getString("id"); // Media ID
            }
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    errorResponse.append(line);
                }
                throw new IOException("Media upload failed: " + errorResponse.toString());
            }
        }
    }

    private void sendWhatsAppWithPDF(String mediaId) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(WHATSAPP_API_URL).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Create JSON payload
        JSONObject message = new JSONObject();
        message.put("messaging_product", "whatsapp");
        message.put("recipient_type", "individual");
        message.put("to", RECIPIENT_PHONE);
        message.put("type", "document");

        JSONObject document = new JSONObject();
        document.put("id", mediaId);
        document.put("caption", "Out of Stock Report - " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        document.put("filename", "out_of_stock_report.pdf");

        message.put("document", document);

        // Send request
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = message.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode != 200 && responseCode != 201) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    errorResponse.append(line);
                }
                throw new IOException("WhatsApp message failed: " + errorResponse.toString());
            }
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}