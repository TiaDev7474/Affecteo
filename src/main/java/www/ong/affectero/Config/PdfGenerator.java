package www.ong.affectero.Config;
import com.itextpdf.kernel.pdf.PdfDocument;
import  com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import www.ong.affectero.Model.Assignment;
import www.ong.affectero.Model.Employee;

import java.sql.SQLException;

public class PdfGenerator {

    private String filepath;
    public PdfGenerator(String filepath){
        this.filepath = filepath;
    }
    public void customMessageToSend(Document document,Integer numAffectation) throws SQLException {
        Assignment assignment = Assignment.getOne(numAffectation);
        Employee employee = Employee.getOne(assignment.getNumEmployee());
        String text = "Arreté N° " + assignment.getNumAffectation() + " du " + assignment.getDayOfAffectation();
        Text title = new Text(text)
                .setBold()
                .setFontSize(16)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(new Paragraph(title));
        String paragraph = employee.getCivility() + " " + employee.getFirstname() + " " + employee.getLastname() +
                " , qui occupe le poste  " + employee.getPoste() + " à " + assignment.getPrevLocation() + " , est affecté à " + assignment.getNewLocation() +
                " pour compter de la date de prise de service " + assignment.getDayOfService() + ".";
        document.add(new Paragraph(paragraph));
        document.add(new Paragraph("Le present communiqué sera enregistrer et communiqué partout ou besoin sera"));
        document.close();
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        String body = employee.getCivility() + " " + employee.getFirstname()+ " ,\n" + "Je tiens à vous informer que vous serez transféré dans une nouvelle ville le " + assignment.getDayOfService() + "."+
                " Veuillez trouver ci joint un Pdf contenant les details de votre transfert ";
        sendEmailNotification.sendWithAttachment("rakotorisonlandry@gmail.com","Job Location Transfer",body ,filepath);
    }
     public void generatePdf(Integer numAffectation){
        try{
            PdfWriter writer = new PdfWriter(filepath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            customMessageToSend(document, numAffectation);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

