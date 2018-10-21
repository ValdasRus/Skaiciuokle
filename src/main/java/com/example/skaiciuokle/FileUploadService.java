package com.example.skaiciuokle;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

public class FileUploadService {

    private static final String TXT_EXTENSION = "txt";

    public List<String> fromFile(MultipartFile[] uploadedFiles) {
        List<String> data = new ArrayList<>();
        try {
            for (MultipartFile uploadedFile : uploadedFiles) {
                File file = getConvertedFile(uploadedFile);
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    String[] splitted = scan.nextLine().split("\\s+");
                    data.addAll(Arrays.asList(splitted));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public File getConvertedFile(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    public void toFile(MultipartFile[] files) {
        if (!isNotTxt(files)) {
            try {
                FileWriter fileWriterAd = new FileWriter("prasidedantysAG.txt");
                FileWriter fileWriterDg = new FileWriter("prasidedantysHN.txt");
                FileWriter fileWriterGo = new FileWriter("prasidedantysOU.txt");
                FileWriter fileWriterOz = new FileWriter("prasidedantysVZ.txt");
                for (Map.Entry<String, Integer> wordCount : getWordCountList(files)) {
                    if (wordCount.getKey().toLowerCase().charAt(0) <= 'g') {
                        fileWriterAd.write(wordCount.getKey() + " yra " + wordCount.getValue() + " vnt. " + System.getProperty("line.separator"));
                    } else if (wordCount.getKey().toLowerCase().charAt(0) >= 'h' && wordCount.getKey().toLowerCase().charAt(0) <= 'n') {
                        fileWriterDg.write(wordCount.getKey() + " yra " + wordCount.getValue() + " vnt. " + System.getProperty("line.separator"));
                    } else if (wordCount.getKey().toLowerCase().charAt(0) >= 'o' && wordCount.getKey().toLowerCase().charAt(0) <= 'u') {
                        fileWriterGo.write(wordCount.getKey() + " yra " + wordCount.getValue() + " vnt. " + System.getProperty("line.separator"));
                    } else if (wordCount.getKey().toLowerCase().charAt(0) >= 'v' && wordCount.getKey().toLowerCase().charAt(0) <= 'z') {
                        fileWriterOz.write(wordCount.getKey() + " yra " + wordCount.getValue() + " vnt. " + System.getProperty("line.separator"));
                    }
                }
                fileWriterAd.close();
                fileWriterDg.close();
                fileWriterGo.close();
                fileWriterOz.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {System.out.println("Either you forgot to select, or selected item is not a valid *.txt file");}

    }

    public List<Map.Entry<String, Integer>> getWordCountList(MultipartFile[] files) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : fromFile(files)) {
            Integer count = wordCount.get(word);
            wordCount.put(word, (count == null) ? 1 : count + 1);
        }

        Set<Map.Entry<String, Integer>> entrySet = wordCount.entrySet();
        List<Map.Entry<String, Integer>> wordCountList = new ArrayList<>(entrySet);
        for (Map.Entry<String, Integer> entry : wordCountList) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        return wordCountList;
    }

    public boolean isNotTxt(MultipartFile[] files) {
        for (MultipartFile file : files) {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!extension.equals(TXT_EXTENSION)) {
                return true;
            }
        }
        return false;
    }
}
