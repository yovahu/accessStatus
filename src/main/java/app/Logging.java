package app;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Logging {
    //Наименование файла
    public static final String filePath = "Logs";

    public Logging(){}

    //Запись в файл (ведение логов)
    public void writeIntoFile(String text){
        try(FileWriter writer = new FileWriter(filePath, true))
        {
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text + "\n");
            bufferWriter.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    //Получение дата + время
    public String timeOfAction(){
        String dat;
        GregorianCalendar gcalendar = new GregorianCalendar();
        SimpleDateFormat simpDate;
        simpDate = new SimpleDateFormat("HH:mm:ss");
        dat = (gcalendar.get(Calendar.MONTH) + 1) +
                "-" + gcalendar.get(Calendar.DATE) + "-" +
                gcalendar.get(Calendar.YEAR) + " " +
                simpDate.format(gcalendar.getTime());
        return dat;
    }

    //Архивирование в формате ZIP
    public void zipLogs() throws IOException {
        FileOutputStream fos = new FileOutputStream("compressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(filePath);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        byte[]bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fis.close();
        fos.close();
        if(fileToZip.delete()) {
            System.out.print("Logs удалён.");
        }
    }

    //Разархивирование с созданием нового файла
    public void unZipLogs() throws IOException {
        String fileZip = "C:/Users/79240/OZapp/compressed.zip";
        File destDir = new File("C:/Users/79240/OZapp");
        byte[]buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    //Создание нового файла для
    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }

}
