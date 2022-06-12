package com.example.demo.service.business;

import java.io.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class Base64ToImages {

    public void decodeImageFromBase64(String base64String, String imagePath) throws IOException {
        String part[] = base64String.split(",");
        byte[] data = Base64.getDecoder().decode(part[1]);

        try (OutputStream stream = new FileOutputStream(imagePath)) {
            stream.write(data);
        }
    }

    public String encodeImageToBase64(String relativePath) throws IOException {
        String path = getImagePath(relativePath);
        File file = new File(path);
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            String base64String= new String(Base64.getEncoder().encode(bytes), "UTF-8");
            String header = "data:image/jpeg;base64,";
            return header + base64String;
        } catch (IOException e) { e.printStackTrace(); }

        return "";
    }

    public Set<String> loadImages(Set<String> images) throws IOException {
        Set<String> base64Images = new HashSet<String>();
        for (String image: images) {
            String base64Image = encodeImageToBase64(image);
            base64Images.add(base64Image);
        }
        return base64Images;
    }
//Obrati paznju mozda ces menjati
    private String getImagePath(String relativePath) {
        String basePath = new File("images/").getAbsolutePath();
        String[] paths = relativePath.split("/"); //path is like /images/cottage/cottageNameIndex.jpg
        StringBuilder path = new StringBuilder().append(basePath).append("/").append(paths[2]).append("/").append(paths[3]);
        return path.toString();
    }
}
