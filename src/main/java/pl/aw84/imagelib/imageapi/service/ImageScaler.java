package pl.aw84.imagelib.imageapi.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import pl.aw84.imagelib.imageapi.entity.Storage;

public class ImageScaler {
    private Storage originalStorage;
    private String baseDir;
    private int width;
    private int height;

    public ImageScaler(String baseDir, Storage originalStorage, int width, int height) {
        this.originalStorage = originalStorage;
        this.baseDir = baseDir;
        this.width = width;
        this.height = height;
    }

    public ByteArrayOutputStream getScaledImage() throws FileNotFoundException, IOException {
        try (FileInputStream fin = new FileInputStream(this.baseDir + "/" + originalStorage.getRelativePath())) {

            BufferedImage originalImage = ImageIO.read(fin);
            int newW, newH;
            if (originalImage.getWidth() < originalImage.getHeight()) {
                newH = this.height;
                newW = (int) (this.width
                        * (double) ((double) originalImage.getWidth() / (double) originalImage.getHeight()));
            } else {
                newW = this.width;
                newH = (int) (this.height
                        * (double) ((double) originalImage.getHeight() / (double) originalImage.getWidth()));

            }
            java.awt.Image scaledInstance = originalImage.getScaledInstance(newW, newH,
                    java.awt.Image.SCALE_SMOOTH);

            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

            BufferedImage bi = convertToBufferedImage(scaledInstance, originalImage.getType());
            System.err.println("Scaled image width: " + bi.getWidth());
            ImageIO.write(bi, "jpg", byteOutput);

            System.err.println("Image length: " + byteOutput.size());

            return byteOutput;
        }
    }

    public static BufferedImage convertToBufferedImage(java.awt.Image img, int type) {

        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                type);

        Graphics2D graphics2D = bi.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.dispose();

        return bi;
    }
}
