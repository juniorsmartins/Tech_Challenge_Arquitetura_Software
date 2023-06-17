package com.techchallenge.devnet.core.domain.base.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public final class QRCodeGenerator {

  public static String localParaArmazenarQRCodes = "D:\\AreaEstudo\\PosTech-Fiap-Alura\\ArquiteturaDeSoftware\\TechChallenge-Soat\\devnet\\devnet\\qrcode_store\\";
  public static String sufixoDoNomeDaImagemDoQRCode = "-QRCODE.png";

//  public String pegarQrCode() {
//
//    String linkedin ="https://www.linkedin.com/in/juniorsmartins/";
//    String github ="https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software";
//    String imagePath = "src/main/resources/static/img/QRCode.png";
//
//    byte[] image = new byte[0];
//    try {
//
//      // Generate and Return Qr Code in Byte Array
//      image = this.getImagemQrCode(linkedin,500,500);
//
//      // Generate and Save Qr Code Image in static/image folder
//      this.gerarImagemQrCode(github,500,500,imagePath);
//
//    } catch (WriterException | IOException e) {
//      e.printStackTrace();
//    }
//    // Convert Byte Array into Base64 Encode String
//    String qrcode = Base64.getEncoder().encodeToString(image);

//    ModelAndView modelAndView = new ModelAndView();
//    modelAndView.addObject("linkedin",linkedin);
//    modelAndView.addObject("github",github);
//    modelAndView.addObject("qrcode",qrcode);
//
//    Model model = (Model) modelAndView.getModel();

//    ModelAndView modelAndView = new ModelAndView();
//    Model model = (Model) modelAndView.getModel();
//    model.addAttribute("linkedin",linkedin);
//    model.addAttribute("github",github);
//    model.addAttribute("qrcode",qrcode);
//
//    return qrcode;
//  }

//  private void gerarImagemQrCode(String text, int width, int height, String filePath) throws WriterException, IOException {
//
//    QRCodeWriter qrCodeWriter = new QRCodeWriter();
//    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
//    Path path = FileSystems.getDefault().getPath(filePath);
//    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
//  }
//
//  private byte[] getImagemQrCode(String text, int width, int height) throws WriterException, IOException {
//
//    QRCodeWriter qrCodeWriter = new QRCodeWriter();
//    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
//    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
//    MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);
//    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
//    byte[] pngData = pngOutputStream.toByteArray();
//    return pngData;
//  }

  public static void gerarQRCode(Pedido pedido) throws WriterException, IOException {

    String nomeDoQRCode = localParaArmazenarQRCodes + pedido.getId() + sufixoDoNomeDaImagemDoQRCode;
    var qrCodeWriter = new QRCodeWriter();

    BitMatrix bitMatrix = qrCodeWriter.encode("ID: " + pedido.getId() + "\n" +
      "Valor do Pagamento: " + pedido.getPrecoTotal(),
      BarcodeFormat.QR_CODE, 400, 400); // Aqui é permitido colocar todas as informações necessárias no QRCode

    Path caminho = FileSystems.getDefault().getPath(nomeDoQRCode);
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", caminho);
  }
}

