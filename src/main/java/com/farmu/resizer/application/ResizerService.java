package com.farmu.resizer.application;

import com.farmu.resizer.infraestructure.ResizeDTO;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class ResizerService {

    /**
     * It resize a image from a width y height passed by parameter
     * @param resizeDTO dto with width, height and image
     * @return the image resized
     */

    public byte[] resize (ResizeDTO resizeDTO) {
        byte[] decodedBytes = Base64.getDecoder().decode(resizeDTO.getImage());
        InputStream is = new ByteArrayInputStream(decodedBytes);

        try {
            BufferedImage originalImage = ImageIO.read(is);
            Image resizedImg = originalImage.getScaledInstance(resizeDTO.getWidth(), resizeDTO.getHeight(), Image.SCALE_SMOOTH);

            BufferedImage bufferedImg = new BufferedImage(resizedImg.getWidth(null),
                    resizedImg.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);
            bufferedImg.getGraphics().drawImage(resizedImg, 0, 0, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImg, "png", baos);
            return baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
