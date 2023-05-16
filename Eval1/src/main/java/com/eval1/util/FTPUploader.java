package com.eval1.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class FTPUploader {

    //Absolute path
    private InputStream inStream;
    private String server = "127.0.0.1";
    private int port = 21;
    private String user;
    private String password;

    private FTPClient ftpClient = new FTPClient();

    public FTPUploader(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public FTPUploader(String server, String user, String password) {
        this.server = server;
        this.user = user;
        this.password = password;
    }

    public FTPUploader(InputStream inStream, String user, String password) {
        this.inStream = inStream;
        this.user = user;
        this.password = password;
    }


    public FTPUploader(InputStream inStream, String server, String user, String password) {
        this.inStream = inStream;
        this.server = server;
        this.user = user;
        this.password = password;
    }

    public FTPUploader(InputStream inStream, String server, int port, String user, String password) {
        this.inStream = inStream;
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public boolean connect () throws IOException {
        ftpClient.connect(server, port);
        ftpClient.login(user, password);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        return true;
    }

    public boolean makeDirectory(String[] dirs) throws IOException {
        for (String dir : dirs
             ) {
            if(!dir.trim().isEmpty()){
                //if it is exist
                if(ftpClient.changeWorkingDirectory(dir)){
                    continue;
                }
                //if it doesn t exist
                if(!ftpClient.makeDirectory(dir)){
                    throw new IOException("Unable to create remote directory "+ dir +" error = "+ftpClient.getReplyString());
                }
                if(!ftpClient.changeWorkingDirectory(dir)){
                    throw new IOException("Unable to change into newly created remote directory '" + dir + "'.  error='" + ftpClient.getReplyString()+"'");
                }
            }
        }
        // go back to the root
        for(int i = 0; i < dirs.length; i++){
            ftpClient.changeWorkingDirectory("../");
        }
        return true;
    }

    // we can add the directory in the filename
    public boolean send(String fileName) throws IOException {
//        create directory if it doesn t exist
        String[] paths = fileName.split("/");
        String[] dirs = new String[paths.length - 1];
        System.arraycopy(paths, 0, dirs, 0, dirs.length) ;
        makeDirectory(dirs);
        if(ftpClient.storeFile(fileName, inStream)){
            System.out.println("The file is uploaded");
            return true;
        }
        return false;
    }

    public boolean delete(String filePath) throws IOException {
        if(ftpClient.deleteFile(filePath)){
            System.out.println("File deleted");
            return true;
        }
        return false;
    }

    public boolean disconnect() throws IOException {
        ftpClient.logout();
        ftpClient.disconnect();
        return true;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }
}
