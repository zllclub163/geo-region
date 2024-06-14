package com.ww.geo.region.util;

import com.github.luben.zstd.Zstd;
import com.github.luben.zstd.ZstdInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * resources.GEO_DATA_COUNTRY.tar.zstd文件生成与解压工具类
 */
public class SourceDataUtils {

    public static void main(String[] args) throws IOException {
        //compressFileZstd("C:\\workspace-wanway\\geo-region\\src\\main\\resources\\GEO_DATA_CHINA", "C:\\workspace-wanway\\geo-region\\src\\main\\resources", 99);
        //压缩
        String folderPath = "C:\\Users\\zllclub\\Downloads\\geo\\first";
        parkTarThenZstd(folderPath);

        //解压
        String zstd = "C:\\workspace\\workspace-wanway\\common-utils\\lbs\\target\\classes\\GEO_DATA_COUNTRY.tar.zstd";
        unZstdThenUnTar(zstd);

    }

    private static void parkTarThenZstd(String folderPath) throws IOException {
        String parkTarPath = parkTar(folderPath);
        compressFileZstd(parkTarPath, folderPath.substring(0, folderPath.lastIndexOf("\\")), 99);
        new File(parkTarPath).delete();
    }

    private static void unZstdThenUnTar(String zstdFile) throws IOException {
        decompressZstd(zstdFile);
        String tarPath = zstdFile.replace(".zstd", "");
        unTarFile(tarPath);
        new File(tarPath).delete();
    }

    private static long compressFileZstd(String inFile, String outFolder, int compressionLevel) throws IOException {
        File file = new File(inFile);
        String path = file.getName() + ".zstd";
        File outFile = new File(outFolder, path);
        long numBytes = 0l;
        ByteBuffer inBuffer = ByteBuffer.allocateDirect(1000 * 1024 * 1024);//要被压缩的字节缓冲区
        ByteBuffer compressedBuffer = ByteBuffer.allocateDirect(1000 * 1024 * 1024);//压缩后放置到该缓冲区
        try (RandomAccessFile inRaFile = new RandomAccessFile(file, "r");//读取文件
             RandomAccessFile outRaFile = new RandomAccessFile(outFile, "rw");
             FileChannel inChannel = inRaFile.getChannel();//通道
             FileChannel outChannel = outRaFile.getChannel()) {
            inBuffer.clear();
            while (inChannel.read(inBuffer) > 0) {//当文件还有字节未压缩时
                inBuffer.flip();//反转缓冲区的读写模式
                compressedBuffer.clear();
                //将 inBuffer的0-inBuffer.limit()压缩到compressedBuffer的0-compressedBuffer.capacity()。
                long compressedSize = Zstd.compressDirectByteBuffer(compressedBuffer, 0, compressedBuffer.capacity(), inBuffer, 0, inBuffer.limit(), compressionLevel);
                numBytes += compressedSize;
                compressedBuffer.position((int) compressedSize);//
                compressedBuffer.flip();
                outChannel.write(compressedBuffer);//把压缩后得到的缓冲区写入文件输出通道
                inBuffer.clear();
            }
        }
        System.out.println("压缩zstd文件完成,路径:" + outFolder + "\\" + path);
        return numBytes;
    }

    public static String parkTar(String folderPath) {
        File[] files = new File(folderPath).listFiles();
        pack(files, new File(folderPath + ".tar"));
        return folderPath + ".tar";
    }

    private static File pack(File[] sources, File target) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(target);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        TarArchiveOutputStream os = new TarArchiveOutputStream(out);
        for (File file : sources) {
            try {
                TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(file);
                tarArchiveEntry.setName(file.getName());
                os.putArchiveEntry(tarArchiveEntry);
                IOUtils.copy(new FileInputStream(file), os);
                os.closeArchiveEntry();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (os != null) {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return target;
    }

    /**
     * 解压tar包
     *
     * @param tarPath tar包路径
     * @throws IOException
     */
    public static void unTarFile(String tarPath) throws IOException {
        if (!tarPath.endsWith(".tar")) {
            return;
        }
        String unTarPath = tarPath.replace(".tar", "");
        new File(unTarPath).mkdir();
        TarArchiveEntry tae = null;
        TarArchiveInputStream tais = new TarArchiveInputStream(Files.newInputStream(new File(tarPath).toPath()));
        while ((tae = tais.getNextTarEntry()) != null) {
            String dir = unTarPath + File.separator + tae.getName();
            File dirFile = new File(dir);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dirFile));
            int count;
            byte data[] = new byte[1024];
            while ((count = tais.read(data, 0, 1024)) != -1) {
                bos.write(data, 0, count);
            }
            bos.close();
        }
        tais.close();
    }


    public static void decompressZstd(String afterCompressFile) {
        File file = new File(afterCompressFile); //待解压文件
        File out = new File(afterCompressFile.replace(".zstd", ""));  //解压后文件

        byte[] buffer = new byte[1024 * 1024 * 8];
        FileInputStream fi = null;
        FileOutputStream fo = null;
        ZstdInputStream zs = null;
        try {
            fo = new FileOutputStream(out);
            fi = new FileInputStream(file.getPath());
            zs = new ZstdInputStream(fi); //将文件输入流复制到zs
            while (true) {
                int count = zs.read(buffer, 0, buffer.length);//zs中重写了read方法，该方法包含解压过程，将0-buffer.length读入buffer
                if (count == -1) {
                    break;
                }
                fo.write(buffer, 0, count);//将buffer中的0-count写入文件输出流
            }
            fo.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zs != null) {
                try {
                    zs.close();
                } catch (Exception x) {
                }
            }
            if (fi != null) {
                try {
                    fi.close();
                } catch (Exception x) {
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (Exception x) {
                }
            }
        }
    }

}