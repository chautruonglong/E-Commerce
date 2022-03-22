package com.fpt.mock.util;

import com.google.common.io.Files;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {

    @Value("${fpt.mock.externalResources}")
    private String externalResources;

    public String getFileExtension(String filename) {
        String fileExtension = Files.getFileExtension(filename);
        if(fileExtension.equals("")) {
            return "jpg";
        }
        return fileExtension;
    }

    public String renameFile(String filename) {
        String fileExtension = getFileExtension(filename);
        return UUID.randomUUID() + "." + fileExtension;
    }

    public String writeToDisk(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);

        String fileExtension = getFileExtension(imageUrl);
        String filename = renameFile(imageUrl);
        String pathname = externalResources.substring("file:".length()) + "/products/";

        java.nio.file.Files.createDirectories(Paths.get(pathname));

        BufferedImage bufferedImage = ImageIO.read(url);
        ImageIO.write(bufferedImage, fileExtension, new File(pathname + filename));

        return filename;
    }
}
