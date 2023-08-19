package com.spring.pdf.manager.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;
import java.io.File;
import org.json.JSONObject;

@RestController
public class OCRController {
 private final String tessDataPath = "C:\\Program Files\\Tesseract-OCR\\tessdata"; // Change this to the directory containing tessdata

 @PostMapping("/validate-pdf-fields")
public ResponseEntity<String> validatePdfFields() 
 {
     // Step 1: Save the uploaded PDF file to a temporary location
	 File imageFile = new File("C:\\Users\\admin\\Downloads\\SHILPA 1.pdf");
     // Step 2: Perform OCR using Tess4J
     Tesseract tesseract = new Tesseract();
     tesseract.setDatapath(tessDataPath); // Set the path to the tessdata directory
     tesseract.setLanguage("eng"); // Specify the language for OCR (e.g., "eng" for English)
     try 
     { 	 
     	String extractedText = tesseract.doOCR(imageFile);// You can now process the extracted text and perform field validation here
     	return new ResponseEntity<>(extractedText, HttpStatus.OK);// Return the extracted text in the response (for demonstration purposes)
     } 
     catch (TesseractException e) 
     {
    	 e.printStackTrace();
         return new ResponseEntity<>("OCR processing failed.", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
 }
}

	
